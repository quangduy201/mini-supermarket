package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.Button;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SaleGUI extends JPanel {
    private final LeftRightLayout layoutMainSale;
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

    public SaleGUI() {
        this.setLayout(new BorderLayout());
        layoutMainSale = new LeftRightLayout(3, 1.3, 20, 5, 5);
        this.add(layoutMainSale, BorderLayout.CENTER);

        panelLeftSale = layoutMainSale.getLeftPanel();
        panelRightSale = layoutMainSale.getRightPanel();

        panelLeftSale.setLayout(new BorderLayout());
        layoutContent = new BottomTopLayout(3, 1.3, 20, 5, 0);
        panelLeftSale.add(layoutContent, BorderLayout.CENTER);

        panelDetailProduct = layoutContent.getTopPanel();
        panelListProduct = layoutContent.getBottomPanel();

        panelDetailProduct.setLayout(new BorderLayout());
        layoutInformation = new LeftRightLayout(1, 1, 20, 5, 0);
        panelDetailProduct.add(layoutInformation, BorderLayout.CENTER);

        panelProduct = layoutInformation.getLeftPanel();
        panelProduct.setLayout(new BorderLayout());
        layoutSearchProduct = new BottomTopLayout(100, true, 20, 0);
        layoutSearchProduct.layoutBackground(new Color(215,215,215));
        panelProduct.add(layoutSearchProduct, BorderLayout.CENTER);

        searchProduct = new JTextField();
        searchProduct.setPreferredSize(new Dimension(250, 40));
        panelSearch = layoutSearchProduct.getTopPanel();
        panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelSearch.add(searchProduct, BorderLayout.CENTER);

        panelProduct = layoutSearchProduct.getBottomPanel();
        panelProduct.setLayout(new GridBagLayout());
        panelProduct.setBackground(new Color(1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,0,0,0);
        int y = 16; // số lượng sản phẩm'
        product = new RoundPanel[y];
        listProduct = new RoundPanel(20);
        listProduct.setPreferredSize(new Dimension(210, (50 * y) + 10));
        listProduct.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        listProduct.setBackground(new Color(215, 215, 215));
        scrollListProduct = new JScrollPane(listProduct);
        scrollListProduct.setBorder(null);
        scrollListProduct.getViewport().setBackground(new Color(115, 215, 215));
        scrollListProduct.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollListProduct.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < y; i++) {
            product[i] = new RoundPanel(20);
            product[i].setRadius(20);
//            product[i].setPreferredSize(new Dimension(200, 45));
            listProduct.add(product[i]);
        }
//        RoundPanel roundPanel = new RoundPanel(20);
//        roundPanel.setLayout(new BorderLayout());
//        roundPanel.add(scrollListProduct, BorderLayout.CENTER);
//
        panelProduct.add(scrollListProduct, gbc);
    }
}
