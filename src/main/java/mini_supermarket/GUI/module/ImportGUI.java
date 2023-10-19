package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class ImportGUI extends JPanel {
    private final ControlLayout mainImport;

    private final LeftRightLayout layoutFormAndData;


    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public ImportGUI() {
        this.setLayout(new BorderLayout());
        mainImport = new ControlLayout();
        this.add(mainImport, BorderLayout.CENTER);

        panelFunction = mainImport.getTopPanel();
        panelData = mainImport.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(1, 3, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}
