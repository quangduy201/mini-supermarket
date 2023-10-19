package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class BrandGUI extends JPanel {
    private final ControlLayout mainBrand;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public BrandGUI() {
        this.setLayout(new BorderLayout());
        mainBrand = new ControlLayout();
        this.add(mainBrand, BorderLayout.CENTER);

        panelFunction = mainBrand.getTopPanel();
        panelData = mainBrand.getBottomPanel();

    }
}
