package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class ProductGUI extends JPanel {
    private ControlLayout mainProduct;

    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public ProductGUI() {
        this.setLayout(new BorderLayout());
        mainProduct = new ControlLayout();
        this.add(mainProduct, BorderLayout.CENTER);

        panelFunction = mainProduct.getTopPanel();
        panelData = mainProduct.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(3,1 ,20,5,0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}
