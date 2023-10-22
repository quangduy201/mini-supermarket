package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StaffGUI extends JPanel {
    private final ControlLayout mainStaff;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public StaffGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainStaff = new ControlLayout(functions);
        this.add(mainStaff, BorderLayout.CENTER);

        panelFunction = mainStaff.getTopPanel();
        panelData = mainStaff.getBottomPanel();

    }
}
