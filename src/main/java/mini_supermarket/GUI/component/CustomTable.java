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

public class CustomTable extends JScrollPane {
    protected JTable table;
    protected Object[] columnNames;
    protected Integer[] columnWidths;
    protected int lastSelectedRow = -1;
    protected Timer doubleClickTimer;
    protected boolean isDoubleClick;
    protected MouseListener singleClick;
    protected MouseListener doubleClick;

    @SafeVarargs
    public CustomTable(Object[][] data, Object[] columnNames, Integer[] columnWidths, Runnable action,
                     boolean isDoubleClick, Pair<Class<?>, Integer>... customColumns) {
        this(data, columnNames, columnWidths, action, isDoubleClick, false, customColumns);
    }

    @SafeVarargs
    public CustomTable(Object[][] data, Object[] columnNames, Integer[] columnWidths, Runnable action,
                       boolean isDoubleClick, boolean readOnly, Pair<Class<?>, Integer>... customColumns) {
        createTable(data, columnNames, readOnly, customColumns);

        this.columnNames = columnNames;
        this.columnWidths = columnWidths;
        this.isDoubleClick = isDoubleClick;
        setColumnWidths(columnWidths);

        table.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 17));
        table.getTableHeader().setBackground(new Color(150, 150, 150));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        table.setFont(new Font("Roboto", Font.PLAIN, 15));
        table.setAutoCreateRowSorter(false);
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setAction(action, isDoubleClick);

        doubleClickTimer = new Timer(500, e -> doubleClickTimer.stop());
        doubleClickTimer.setRepeats(false);
        this.setViewportView(table);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.setRowCount(0);
        model.setDataVector(data, columnNames);
        setColumnWidths(columnWidths);
    }

    @SafeVarargs
    public final void createTable(Object[][] data, Object[] columnNames, boolean readOnly, Pair<Class<?>, Integer>... customColumns) {
        if (readOnly) {
            table = new JTable(new DefaultTableModel(data, columnNames)) {
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

                @Override
                public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {

                }
            };
        } else {
            table = new JTable(new DefaultTableModel(data, columnNames)) {
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
            };
            table.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1 && row != lastSelectedRow) {
                        table.setRowSelectionInterval(lastSelectedRow, lastSelectedRow);
                    }
                }
            });
        }
    }

    public void setColumnWidths(Integer[] columnWidths) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            if (columnWidths[i] == null) {
                column.setCellRenderer(null);
            } else if (columnWidths[i] > 0) {
                column.setCellRenderer(cellRenderer);
                column.setPreferredWidth(columnWidths[i]);
            } else if (columnWidths[i] < 0) {
                column.setPreferredWidth(-columnWidths[i]);
            } else {
                column.setCellRenderer(cellRenderer);
            }
        }
    }

    public void setAction(Runnable action, boolean isDoubleClick) {
        this.isDoubleClick = isDoubleClick;
        if (isDoubleClick) {
            setDoubleClickAction(action);
            table.removeMouseListener(singleClick);
            table.addMouseListener(doubleClick);
        } else {
            setSingleClickAction(action);
            table.removeMouseListener(doubleClick);
            table.addMouseListener(singleClick);
        }
    }

    public void setSingleClickAction(Runnable action) {
        this.singleClick = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != -1 && row != lastSelectedRow) {
                    lastSelectedRow = row;
                    if (action != null) {
                        action.run();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (table.getSelectedRow() == -1) {
                    lastSelectedRow = -1;
                }
            }
        };
    }

    public void setDoubleClickAction(Runnable action) {
        this.doubleClick = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != -1) {
                    lastSelectedRow = row;
                    if (e.getClickCount() == 2 && action != null) {
                        action.run();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (table.getSelectedRow() == -1) {
                    lastSelectedRow = -1;
                }
            }
        };
    }

    public Object[][] getDataFromSelectedRows() {
        Object[][] data = new Object[table.getSelectedRowCount()][table.getColumnCount()];
        for (int row = 0; row < data.length; row++)
            for (int column = 0; column < data[0].length; column++)
                data[row][column] = table.getValueAt(table.getSelectedRows()[row], column);
        return data;
    }

    public Object[] getDataFromSelectedRow() {
        Object[][] data = getDataFromSelectedRows();
        return data[0];
    }
}
