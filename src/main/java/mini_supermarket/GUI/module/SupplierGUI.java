package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class SupplierGUI extends JPanel {
    private final ControlLayout mainSupplier;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public SupplierGUI() {
        this.setLayout(new BorderLayout());
        mainSupplier = new ControlLayout();
        this.add(mainSupplier, BorderLayout.CENTER);

        panelFunction = mainSupplier.getTopPanel();
        panelData = mainSupplier.getBottomPanel();

    }
}
