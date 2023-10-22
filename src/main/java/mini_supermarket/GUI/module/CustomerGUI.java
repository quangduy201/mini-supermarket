package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerGUI extends JPanel {
    private final ControlLayout mainCustomer;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public CustomerGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainCustomer = new ControlLayout(functions);
        this.add(mainCustomer, BorderLayout.CENTER);

        panelFunction = mainCustomer.getTopPanel();
        panelData = mainCustomer.getBottomPanel();

    }
}
