package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class SaleGUI extends JPanel {
    private RoundPanel mainSale;
    private LeftRightLayout layoutMainSale;
    private BottomTopLayout layoutContent;
    private LeftRightLayout layoutInformation;

    private RoundPanel panelLeftSale;
    private RoundPanel panelRightSale;

    private RoundPanel panelListProduct;
    private RoundPanel panelDetailProduct;


    private RoundPanel panelSearch;
    private RoundPanel panelDetail;

    public SaleGUI() {
        this.setLayout(new BorderLayout());
        layoutMainSale = new LeftRightLayout(3, 1, 20,5,5);
        this.add(layoutMainSale, BorderLayout.CENTER);

        panelLeftSale = layoutMainSale.getLeftPanel();
        panelRightSale = layoutMainSale.getRightPanel();

        panelLeftSale.setLayout(new BorderLayout());
        layoutContent = new BottomTopLayout(4,1.5 ,20,5,0);
        panelLeftSale.add(layoutContent, BorderLayout.CENTER);

        panelDetailProduct = layoutContent.getTopPanel();
        panelListProduct = layoutContent.getBottomPanel();

        panelDetailProduct.setLayout(new BorderLayout());
        layoutInformation = new LeftRightLayout(1,1 ,20,5,0);
        panelDetailProduct.add(layoutInformation, BorderLayout.CENTER);


    }
}
