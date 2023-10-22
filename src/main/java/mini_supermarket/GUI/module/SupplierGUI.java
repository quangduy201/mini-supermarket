package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SupplierGUI extends JPanel {
    private final ControlLayout mainSupplier;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public SupplierGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainSupplier = new ControlLayout(functions);
        this.add(mainSupplier, BorderLayout.CENTER);

        panelFunction = mainSupplier.getTopPanel();
        panelData = mainSupplier.getBottomPanel();

    }
}
