package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class CustomerGUI extends JPanel {
    private final ControlLayout mainCustomer;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public CustomerGUI() {
        this.setLayout(new BorderLayout());
        mainCustomer = new ControlLayout();
        this.add(mainCustomer, BorderLayout.CENTER);

        panelFunction = mainCustomer.getTopPanel();
        panelData = mainCustomer.getBottomPanel();

    }
}
