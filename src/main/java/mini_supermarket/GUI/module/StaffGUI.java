package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class StaffGUI extends JPanel {
    private ControlLayout mainStaff;

    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;

    public StaffGUI() {
        this.setLayout(new BorderLayout());
        mainStaff = new ControlLayout();
        this.add(mainStaff, BorderLayout.CENTER);

        panelFunction = mainStaff.getTopPanel();
        panelData = mainStaff.getBottomPanel();

    }
}
