package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductGUI extends JPanel {
    private final ControlLayout mainProduct;

    private final LeftRightLayout layoutFormAndData;


    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public ProductGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainProduct = new ControlLayout(functions);
        this.add(mainProduct, BorderLayout.CENTER);

        panelFunction = mainProduct.getTopPanel();
        panelData = mainProduct.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(3, 1, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}
