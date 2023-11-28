package mini_supermarket.GUI.dialog;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.Excel;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Log;
import mini_supermarket.utils.Pair;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

public class ExcelDialog extends JDialog {
    protected boolean cancel;
    protected final JTable table;
    protected final DefaultTableModel model;
    protected final RoundPanel pnlButton;
    protected final JButton btnConfirm;
    protected final JButton btnCancel;
    protected final JButton btnAddRow;
    protected final JButton btnRemoveRow;
    protected final JButton btnPaste;
    protected final JButton btnReadFile;
    protected final List<Pair<String, Excel.Type>> columns;

    public ExcelDialog(List<Pair<String, Excel.Type>> columns, Function<List<String>, Pair<Boolean, String>> runWhenConfirm) {
        super((Frame) null, true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel = true;
                dispose();
            }
        });
        this.getRootPane().registerKeyboardAction(e -> {
            cancel = true;
            dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(MiniSupermarket.main);

        model = new DefaultTableModel();
        table = new JTable(model);
        pnlButton = new RoundPanel();
        btnConfirm = new JButton(I18n.get("components", "excel.button.confirm"));
        btnCancel = new JButton(I18n.get("components", "excel.button.cancel"));
        btnAddRow = new JButton(I18n.get("components", "excel.button.add_row"));
        btnRemoveRow = new JButton(I18n.get("components", "excel.button.remove_row"));
        btnPaste = new JButton(I18n.get("components", "excel.button.paste"));
        btnReadFile = new JButton(I18n.get("components", "excel.button.read_file"));

        this.columns = columns;
        for (Pair<String, Excel.Type> column : columns)
            model.addColumn(column.getFirst());
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        pnlButton.setLayout(new FlowLayout());
        this.add(pnlButton, BorderLayout.SOUTH);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        table.getTableHeader().setReorderingAllowed(false);
        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                Object value = model.getValueAt(row, column);
                Excel.Type type = columns.get(column).getSecond();
                Object castValue;
                try {
                    castValue = Excel.castValue(value, type);
                } catch (ClassCastException exception) {
                    JOptionPane.showMessageDialog(MiniSupermarket.main,
                        I18n.get("messages", "data_table.update.error"),
                        I18n.get("dialog", "title.error"), JOptionPane.ERROR_MESSAGE);
                    castValue = Excel.castValue(null, type);
                    model.setValueAt(castValue, row, column);
                    table.addRowSelectionInterval(row, row);
                }
            }
        });

        pnlButton.add(btnConfirm);
        pnlButton.add(btnCancel);
        pnlButton.add(btnAddRow);
        pnlButton.add(btnRemoveRow);
        pnlButton.add(btnPaste);
        pnlButton.add(btnReadFile);
        addListenerToButtons(runWhenConfirm);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void addListenerToButtons(Function<List<String>, Pair<Boolean, String>> runWhenConfirm) {
        btnConfirm.addActionListener(e -> {
            cancel = false;
            List<List<String>> data = getData();
            if (data == null)
                return;

            boolean hasError = false;
            for (int i = 0; i < data.size(); i++) {
                String messageArgument = data.get(i).toString();
                try {
                    List<String> row = data.get(i);
                    Pair<Boolean, String> result = runWhenConfirm.apply(row);
                    if (!result.getFirst()) {
                        messageArgument = result.getSecond();
                        throw new RuntimeException();
                    }
                    data.remove(i);
                    model.removeRow(i);
                    i--;
                } catch (Exception exception) {
                    hasError = true;
                    table.setRowSelectionInterval(i, i);
                    String message = I18n.get("messages", "excel.import.error.row", i + 1, messageArgument);
                    int choice = SmallDialog.showErrorWhileImporting(this, message);
                    if (choice != 0) break;
                }
            }
            if (!hasError)
                dispose();
        });
        btnCancel.addActionListener(e -> {
            cancel = true;
            refresh();
            dispose();
        });
        btnAddRow.addActionListener(e -> {
            addRow();
            table.clearSelection();
            int row = model.getRowCount() - 1;
            table.addRowSelectionInterval(row, row);
        });
        btnRemoveRow.addActionListener(e -> {
            removeRow();
            table.clearSelection();
        });
        btnPaste.addActionListener(e -> pasteFromClipboard());
        btnReadFile.addActionListener(e -> {
            File file = Excel.openFile();
            if (file == null)
                return;
            Pair<List<List<Object>>, String> result = Excel.importExcel(file, columns);
            if (result.getFirst() == null) {
                JOptionPane.showMessageDialog(this, result.getSecond(),
                    I18n.get("dialog", "title.error"), JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, result.getSecond(),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            table.clearSelection();
            List<List<Object>> data = result.getFirst();
            for (List<Object> datum : data) {
                int row = model.getRowCount();
                addRow();
                for (int j = 0; j < data.get(0).size(); j++) {
                    model.setValueAt(datum.get(j), row, j);
                }
            }
        });
    }

    public void addRow() {
        List<Object> emptyData = new ArrayList<>();
        for (Pair<String, Excel.Type> column : columns) {
            Object datum = Excel.castValue(null, column.getSecond());
            emptyData.add(datum);
        }
        model.addRow(emptyData.toArray());
    }

    public void removeRow() {
        int[] rows = table.getSelectedRows();
        for (int i = rows.length - 1; i >= 0 ; i--)
            model.removeRow(rows[i]);
    }

    public void pasteFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(table);

        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String clipboardData = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                String[] data = clipboardData.split("\n");
                table.clearSelection();

                boolean hasError = false;
                for (String datum : data) {
                    int row = model.getRowCount();
                    String[] values = datum.split("\t");
                    model.addRow(values);
                    for (int j = 0; j < values.length; j++) {
                        Object value = model.getValueAt(row, j);
                        Excel.Type type = columns.get(j).getSecond();
                        Object castValue;
                        try {
                            castValue = Excel.castValue(value, type);
                        } catch (ClassCastException exception) {
                            hasError = true;
                            castValue = Excel.castValue(null, type);
                            model.setValueAt(castValue, row, j);
                            table.addRowSelectionInterval(row, row);
                        }
                    }
                }
                if (hasError) {
                    JOptionPane.showMessageDialog(this,
                        I18n.get("messages", "excel.import.error.paste"),
                        I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    I18n.get("messages", "excel.import.error.paste"),
                    I18n.get("dialog", "title.error"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<List<String>> getData() {
        if (cancel)
            return null;
        List<List<String>> data = new ArrayList<>();
        for (Vector<?> row : model.getDataVector()) {
            List<String> rowData = new ArrayList<>();
            for (Object value : row)
                rowData.add(value.toString().trim());
            data.add(rowData);
        }
        return data;
    }

    public void refresh() {
        model.setRowCount(0);
    }
}
