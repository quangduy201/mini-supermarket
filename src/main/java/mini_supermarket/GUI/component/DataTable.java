package mini_supermarket.GUI.component;

import mini_supermarket.utils.Pair;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DataTable extends JTable {
    protected int lastSelectedRow = -1;
    protected Timer doubleClickTimer;
    protected boolean isDoubleClick;
    protected MouseListener singleClick;
    protected MouseListener doubleClick;

    @SafeVarargs
    public DataTable(Object[][] data, Object[] columnNames, Integer[] columnWidths, Runnable action,
                     boolean isDoubleClick, Pair<Class<?>, Integer>... customColumns) {
        super(new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                for (Pair<Class<?>, Integer> custom : customColumns) {
                    if (columnIndex == custom.getSecond())
                        return custom.getFirst();
                }
                return String.class;
            }
        });

        this.isDoubleClick = isDoubleClick;
        setColumnWidths(columnWidths);

        getTableHeader().setFont(new Font("Roboto", Font.BOLD, 17));
        getTableHeader().setBackground(new Color(150, 150, 150));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);

        setFont(new Font("Roboto", Font.PLAIN, 15));
        setAutoCreateRowSorter(false);
        setRowHeight(25);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setAction(action, isDoubleClick);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != -1 && row != lastSelectedRow) {
                    setRowSelectionInterval(lastSelectedRow, lastSelectedRow);
                }
            }
        });
        doubleClickTimer = new Timer(500, e -> doubleClickTimer.stop());
        doubleClickTimer.setRepeats(false);
    }

    public void setColumnWidths(Integer[] columnWidths) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            if (columnWidths[i] == null) {
                column.setCellRenderer(null);
                continue;
            }
            column.setCellRenderer(cellRenderer);
            if (columnWidths[i] > 0)
                column.setPreferredWidth(columnWidths[i]);
        }
    }

    public void setAction(Runnable action, boolean isDoubleClick) {
        this.isDoubleClick = isDoubleClick;
        if (isDoubleClick) {
            setDoubleClickAction(action);
            removeMouseListener(singleClick);
            addMouseListener(doubleClick);
        } else {
            setSingleClickAction(action);
            removeMouseListener(doubleClick);
            addMouseListener(singleClick);
        }
    }

    public void setSingleClickAction(Runnable action) {
        this.singleClick = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != -1 && row != lastSelectedRow) {
                    lastSelectedRow = row;
                    if (action != null) {
                        action.run();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (getSelectedRow() == -1) {
                    lastSelectedRow = -1;
                }
            }
        };
    }

    public void setDoubleClickAction(Runnable action) {
        this.doubleClick = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != -1) {
                    lastSelectedRow = row;
                    if (e.getClickCount() == 2 && action != null) {
                        action.run();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (getSelectedRow() == -1) {
                    lastSelectedRow = -1;
                }
            }
        };
    }

    public Object[][] getDataFromSelectedRows() {
        Object[][] data = new Object[getSelectedRowCount()][getColumnCount()];
        for (int row = 0; row < data.length; row++)
            for (int column = 0; column < data[0].length; column++)
                data[row][column] = getValueAt(getSelectedRows()[row], column);
        return data;
    }

    public Object[] getDataFromSelectedRow() {
        Object[][] data = getDataFromSelectedRows();
        return data[0];
    }

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        if (isDoubleClick && rowIndex != -1 && rowIndex == lastSelectedRow) {
            if (!doubleClickTimer.isRunning()) {
                doubleClickTimer.start();
            }
        } else {
            super.changeSelection(rowIndex, columnIndex, toggle, extend);
        }
    }
}
