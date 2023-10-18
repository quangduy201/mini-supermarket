package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class CustomerGUI extends JPanel {
    private ControlLayout mainCustomer;

    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;

    public CustomerGUI() {
        this.setLayout(new BorderLayout());
        mainCustomer = new ControlLayout();
        this.add(mainCustomer, BorderLayout.CENTER);

        panelFunction = mainCustomer.getTopPanel();
        panelData = mainCustomer.getBottomPanel();

    }
}
