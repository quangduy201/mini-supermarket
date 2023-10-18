package mini_supermarket.GUI.layout;

import mini_supermarket.DTO.Statistic;
import mini_supermarket.GUI.component.Button;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.module.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LayoutHome extends BottomTopLayout {
    private RoundPanel topPanel;
    private RoundPanel bottomPanel;
    private RoundPanel panelLogOut;
    private RoundPanel panelListFunc;
    private RoundPanel ListFunc;
    private Button[] btnListFunc;
    private JScrollPane scrollListFunc;

    private RoundPanel rightHome;

    private Button btnLogOut;
    public LayoutHome(RoundPanel rightHome) {
        super(150,true,20,5);
        this.rightHome = rightHome;
        setBackground(new Color(240,240,240));
        topPanel = this.getTopPanel();
        bottomPanel = this.getBottomPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(0xF0F0F0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        BottomTopLayout layoutFunction = new BottomTopLayout(50,false,20,5);
        layoutFunction.layoutBackground(new Color(215,215,215));
        bottomPanel.add(layoutFunction, gbc);
        gbc.insets = new Insets(5,5,5,5);
        panelListFunc = layoutFunction.getTopPanel();
        panelLogOut = layoutFunction.getBottomPanel();
        panelLogOut.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        panelListFunc.setLayout(new GridBagLayout());
        int y = 16; // số lượng chức năng'

        btnListFunc = new Button[y];
        ListFunc = new RoundPanel(20);
        ListFunc.setPreferredSize(new Dimension(210, (50 * y) + 10));
        ListFunc.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        ListFunc.setBackground(new Color(215,215,215));
        scrollListFunc = new JScrollPane(ListFunc);
        scrollListFunc.setBorder(null);
        scrollListFunc.getViewport().setBackground(new Color(215,215,215));
        scrollListFunc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollListFunc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < y; i++) {
            btnListFunc[i] = new Button();
            btnListFunc[i].setColor(new Color(243, 240, 240));
            btnListFunc[i].setColorOver(new Color(255, 255, 255));
            btnListFunc[i].setColorClick(new Color(0xB0ADAD));
            btnListFunc[i].setRadius(20);
            btnListFunc[i].setBorderColor(new Color(0xF0F0F0));
            btnListFunc[i].setPreferredSize(new Dimension(235,45));
            int index = i;
            btnListFunc[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    selectRoundPanel(index);
                }
            });
            ListFunc.add(btnListFunc[i]);
        }
        panelListFunc.add(scrollListFunc, gbc);
        btnLogOut = new Button();
        btnLogOut.setColor(new Color(0xFFFFFF));
        btnLogOut.setColorOver(new Color(240,240,240));
        btnLogOut.setRadius(20);
        btnLogOut.setPreferredSize(new Dimension(230,45));
        btnLogOut.setBorderColor(new Color(0xF0F0F0));
        panelLogOut.add(btnLogOut);
        rightHome.add(new HomeGUI());
    }

    private void selectRoundPanel(int index) {
        JPanel panel = switch (index) {
            case 0 -> new HomeGUI();
            case 1 -> new SaleGUI();
            case 2 -> new WarehouseGUI();
            case 3 -> new StatisticGUI();
            case 4 -> new DiscountGUI();
            case 5 -> new BillGUI();
            case 6 -> new ExportGUI();
            case 7 -> new ImportGUI();
            case 8 -> new ProductGUI();
            case 9 -> new CategoryGUI();
            case 10 -> new BrandGUI();
            case 11 -> new SupplierGUI();
            case 12 -> new CustomerGUI();
            case 13 -> new StaffGUI();
            case 14 -> new AccountGUI();
            case 15 -> new DecentralizationGUI();
            default -> null;
        };
        OpenChildForm(panel);
    }

    private void OpenChildForm(JPanel panel) {
        rightHome.removeAll();
        rightHome.add(panel);
        rightHome.repaint();
        rightHome.revalidate();
    }
}
