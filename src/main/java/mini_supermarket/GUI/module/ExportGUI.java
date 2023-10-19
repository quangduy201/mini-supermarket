package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class ExportGUI extends JPanel {
    private final ControlLayout mainExport;

    private final LeftRightLayout layoutFormAndData;


    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public ExportGUI() {
        this.setLayout(new BorderLayout());
        mainExport = new ControlLayout();
        this.add(mainExport, BorderLayout.CENTER);

        panelFunction = mainExport.getTopPanel();
        panelData = mainExport.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(1, 3, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}
