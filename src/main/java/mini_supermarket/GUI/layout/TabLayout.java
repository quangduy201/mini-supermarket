package mini_supermarket.GUI.layout;

import javax.swing.*;
import java.awt.*;

public class TabLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Example JTabbedPane");
        JTabbedPane tabbedPane = new JTabbedPane();
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.tabInsets", new Insets(20, 20, 20, 20));
        UIManager.put("TabbedPane.selected", Color.RED);
        UIManager.put("TabbedPane.contentAreaColor", Color.GRAY);
        tabbedPane.setUI(new CustomTabbedPaneUI());
        tabbedPane.setOpaque(false);
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("This is Tab 1"));
        tabbedPane.addTab("Tab 1", panel1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is Tab 2"));
        tabbedPane.addTab("Tab 2", panel2);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("This is Tab 3"));
        tabbedPane.addTab("Tab 3", panel3);

        frame.add(tabbedPane);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Table Header Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//
//        // Tạo một DefaultTableModel với dữ liệu mẫu
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Column 1");
//        model.addColumn("Column 2");
//        model.addColumn("Column 3");
//        model.addRow(new Object[]{"Data 1", "Data 2", "Data 3"});
//        model.addRow(new Object[]{"Data 4", "Data 5", "Data 6"});
//
//        // Tạo một JTable với DefaultTableModel
//        JTable table = new JTable(model);
//
//        // Tạo một JTableHeader từ JTable
//        JTableHeader header = table.getTableHeader();
//
//        // Thêm JTable và JTableHeader vào JFrame
//        frame.add(header);
//        frame.add(new JScrollPane(table));
//
//        frame.setVisible(true);
//    }
}
