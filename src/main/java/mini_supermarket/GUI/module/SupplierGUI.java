package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class SupplierGUI extends JPanel {
    private ControlLayout mainSupplier;

    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;

    public SupplierGUI() {
        this.setLayout(new BorderLayout());
        mainSupplier = new ControlLayout();
        this.add(mainSupplier, BorderLayout.CENTER);

        panelFunction = mainSupplier.getTopPanel();
        panelData = mainSupplier.getBottomPanel();

    }
}
