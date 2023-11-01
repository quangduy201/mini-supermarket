package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.LayoutWarningTable;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SaleGUI extends LeftRightLayout {
    private final BottomTopLayout layoutContent;
    private final LeftRightLayout layoutInformation;
    private final BottomTopLayout layoutSearchProduct;
    private final RoundPanel panelLeftSale;
    private final RoundPanel panelRightSale;
    private final RoundPanel panelListProduct;
    private final RoundPanel panelDetailProduct;
    private RoundPanel mainSale;
    private RoundPanel panelSearch;
    private RoundPanel panelDetail;
    private RoundPanel panelProduct;

    private JTextField searchProduct;

    private RoundPanel listProduct;
    private JScrollPane scrollListProduct;
    private RoundPanel[] product;
    private RoundPanel[] panelDetailRightSale;
    private  RoundPanel[] panelDetails;

    private JLabel lbNameProduct;
    private JTextField TextNameProduct;
    private JLabel lbIdProduct;
    private JTextField IdTextProduct;
    private JLabel lbNameStaff;

    private JTextField textNameStaff;
    private JLabel moneyReceived;
    private JTextField containerReceived;
    private JLabel totalAmount;
    private JTextField containerTotalAmount;
    private JLabel change;
    private JTextField containerChange;

    public SaleGUI(List<Function> functions) {
        super(3, 1, 20, 5, 5);
        this.setBackground(null);

        lbNameProduct = new JLabel();
        TextNameProduct = new JTextField();
        lbIdProduct = new JLabel();
        IdTextProduct = new JTextField();
        lbNameStaff = new JLabel();
        textNameStaff = new JTextField();
        moneyReceived = new JLabel();
        containerReceived = new JTextField();
        totalAmount = new JLabel();
        containerTotalAmount = new JTextField();
        change = new JLabel();
        containerChange = new JTextField();

        panelLeftSale = this.getLeftPanel();
        panelRightSale = this.getRightPanel();

        panelLeftSale.setLayout(new BorderLayout());
        layoutContent = new BottomTopLayout(3, 1.3, 20, 5, 0);
        layoutContent.setBackground(new Color(255, 255, 255));
        panelLeftSale.add(layoutContent, BorderLayout.CENTER);

        panelDetailProduct = layoutContent.getTopPanel();
        panelListProduct = layoutContent.getBottomPanel();

        panelDetailProduct.setLayout(new BorderLayout());
        layoutInformation = new LeftRightLayout(1.5, 1, 20, 5, 0);
        layoutInformation.setBackground(new Color(255, 255, 255));
        panelDetailProduct.add(layoutInformation, BorderLayout.CENTER);

        panelProduct = layoutInformation.getLeftPanel();
        panelProduct.setLayout(new BorderLayout());
        layoutSearchProduct = new BottomTopLayout(40, true, 20, 0);
        layoutSearchProduct.setBackground(null);
        panelProduct.add(layoutSearchProduct, BorderLayout.CENTER);


        GridBagConstraints gbcd = new GridBagConstraints();
        gbcd.fill = GridBagConstraints.BOTH;
        gbcd.weightx = 1.0;
        gbcd.weighty = 1.0;
        gbcd.gridx = 0;
        gbcd.gridy = 0;
        gbcd.insets = new Insets(5,0,0,0);
        searchProduct = new JTextField();
        panelSearch = layoutSearchProduct.getTopPanel();
        panelSearch.setLayout(new GridBagLayout());
        searchProduct.setPreferredSize(new Dimension(0, 40));
        panelSearch.add(searchProduct, gbcd);

        panelProduct = layoutSearchProduct.getBottomPanel();
        panelProduct.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;


        int y = 16; // số lượng sản phẩm'
        int size = 100;
        gbc.insets = new Insets(5, 5, 5, 5);
        product = new RoundPanel[y];
        listProduct = new RoundPanel(20);
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.weightx = 1.0;
        gbcPanel.weighty = 1.0;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        gbcPanel.insets = new Insets(0, 0, 5, 5);
        gbcPanel.anchor = GridBagConstraints.PAGE_START;
        listProduct.setPreferredSize(new Dimension(0, (size + 5) * y));
        listProduct.setLayout(new GridBagLayout());
        listProduct.setBackground(new Color(215, 215, 215));
        scrollListProduct = new JScrollPane(listProduct);
        scrollListProduct.setBorder(null);
        scrollListProduct.setBackground(null);
        scrollListProduct.getViewport().setBackground(null);
        scrollListProduct.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollListProduct.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < y; i++) {
            product[i] = new RoundPanel(20);
            product[i].setRadius(20);
            product[i].setPreferredSize(new Dimension(0, size));
            product[i].setBackground(new Color(255, 255, 255));
            listProduct.add(product[i], gbcPanel);
            gbcPanel.gridy++;
        }
        panelProduct.add(scrollListProduct, gbc);

        GridBagConstraints panelgbc = new GridBagConstraints();
        panelgbc.fill = GridBagConstraints.BOTH;
        panelgbc.gridx = 0;
        panelgbc.gridy = 0;
        panelgbc.insets = new Insets(5, 5, 5, 5);

        panelDetail = layoutInformation.getRightPanel();
        panelDetail.setLayout(new GridBagLayout());
        panelDetails = new RoundPanel[7];
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0 -> {
                    panelgbc.weighty = 0.2;
                    lbIdProduct.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    lbIdProduct.setText("Mã sản phẩm:");
                    lbIdProduct.setHorizontalAlignment(SwingConstants.CENTER);
                    lbIdProduct.setPreferredSize(new Dimension(0,30));
                    IdTextProduct.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    IdTextProduct.setText("");
                    IdTextProduct.setPreferredSize(new Dimension(0,30));
                    panelgbc.weightx = 1;
                    panelDetail.add(lbIdProduct, panelgbc);
                    panelgbc.weightx = 2;
                    panelgbc.gridx++;
                    panelDetail.add(IdTextProduct, panelgbc);
                    panelgbc.gridx = 0;
                    panelgbc.gridy++;
                }
                case 1 -> {
                    panelgbc.weighty = 0.2;
                    lbNameProduct.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    lbNameProduct.setText("Tên sản phẩm:");
                    lbNameProduct.setHorizontalAlignment(SwingConstants.CENTER);
                    lbNameProduct.setPreferredSize(new Dimension(0,30));
                    TextNameProduct.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    TextNameProduct.setPreferredSize(new Dimension(0,30));
                    TextNameProduct.setText("");
                    panelgbc.weightx = 1;
                    panelDetail.add(lbNameProduct, panelgbc);
                    panelgbc.weightx = 2;
                    panelgbc.gridx++;
                    panelDetail.add(TextNameProduct, panelgbc);
                    panelgbc.gridx = 0;
                    panelgbc.gridy++;
                }
                case 2, 3, 4, 5 -> {
                    panelgbc.weighty = 0.1;
                    panelgbc.gridwidth = 2;
                    panelDetails[i] = new RoundPanel(20);
                    panelDetails[i].setRadius(20);
                    panelDetails[i].setBackground(new Color(255, 255, 255));
                    panelDetail.add(panelDetails[i], panelgbc);
                    panelgbc.gridy++;
                }
                case 6 -> {
                    panelgbc.weighty = 5;
                    panelDetails[i] = new RoundPanel(20);
                    panelDetails[i].setRadius(20);
                    panelDetails[i].setBackground(new Color(255, 255, 255));
                    panelDetail.add(panelDetails[i], panelgbc);
                }
            }
        }

        panelgbc.gridx = 0;
        panelgbc.gridy = 0;
        panelgbc.gridwidth = 1;
        panelRightSale.setLayout(new GridBagLayout());
        panelDetailRightSale = new RoundPanel[10];
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0 -> {
                    panelgbc.weighty = 0.2;
                    lbNameStaff.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    lbNameStaff.setText("Tên Nhân Viên:");
                    lbNameStaff.setPreferredSize(new Dimension(0,30));
                    textNameStaff.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    textNameStaff.setPreferredSize(new Dimension(0,30));
                    textNameStaff.setText("");
                    panelgbc.weightx = 1;
                    panelRightSale.add(lbNameStaff, panelgbc);
                    panelgbc.weightx = 2;
                    panelgbc.gridx++;
                    panelRightSale.add(textNameStaff, panelgbc);
                    panelgbc.gridy++;
                }
                case 5, 6 -> {
                    panelgbc.weighty = 0.5;
                    panelgbc.gridwidth = 2;
                    panelgbc.gridx = 0;
                    panelDetailRightSale[i] = new RoundPanel(20);
                    panelDetailRightSale[i].setRadius(20);
                    panelDetailRightSale[i].setBackground(new Color(255, 255, 255));
                    panelRightSale.add(panelDetailRightSale[i], panelgbc);
                    panelgbc.gridy++;
                }
                case 1 -> {
                    panelgbc.weighty = 7.2;
                    panelgbc.gridwidth = 2;
                    panelgbc.gridx = 0;
                    panelDetailRightSale[i] = new RoundPanel(20);
                    panelDetailRightSale[i].setRadius(20);
                    panelDetailRightSale[i].setBackground(new Color(255, 255, 255));
                    panelRightSale.add(panelDetailRightSale[i], panelgbc);
                    panelgbc.gridy++;
                    panelgbc.gridwidth = 1;
                }
                case 2 -> {
                    panelgbc.weighty = 0.2;
                    panelgbc.gridx = 0;
                    moneyReceived.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    moneyReceived.setText("Tổng tiền:");
                    moneyReceived.setPreferredSize(new Dimension(0,30));
                    containerReceived.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    containerReceived.setPreferredSize(new Dimension(0,30));
                    containerReceived.setText("");
                    panelgbc.weightx = 1;
                    panelRightSale.add(moneyReceived, panelgbc);
                    panelgbc.weightx = 2;
                    panelgbc.gridx++;
                    panelRightSale.add(containerReceived, panelgbc);
                    panelgbc.gridy++;
                }
                case 3 -> {
                    panelgbc.weighty = 0.2;
                    panelgbc.gridx = 0;
                    totalAmount.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    totalAmount.setText("Tiền Nhận:");
                    totalAmount.setPreferredSize(new Dimension(0,30));
                    containerTotalAmount.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    containerTotalAmount.setPreferredSize(new Dimension(0,30));
                    containerTotalAmount.setText("");
                    panelgbc.weightx = 1;
                    panelRightSale.add(totalAmount, panelgbc);
                    panelgbc.weightx = 2;
                    panelgbc.gridx++;
                    panelRightSale.add(containerTotalAmount, panelgbc);
                    panelgbc.gridy++;
                }
                case 4 -> {
                    panelgbc.weighty = 0.2;
                    panelgbc.gridx = 0;
                    change.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    change.setText("Tiền thừa:");
                    change.setPreferredSize(new Dimension(0, 30));
                    containerChange.setFont(new Font("Time New Romans", Font.PLAIN, 14));
                    containerChange.setPreferredSize(new Dimension(0, 30));
                    containerChange.setText("");
                    panelgbc.weightx = 1;
                    panelRightSale.add(change, panelgbc);
                    panelgbc.weightx = 2;
                    panelgbc.gridx++;
                    panelRightSale.add(containerChange, panelgbc);
                    panelgbc.gridy++;
                }
            }
        }
        change.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LayoutWarningTable layoutWarning = new LayoutWarningTable();
                layoutWarning.setVisible(true);
            }
        });
    }
}
