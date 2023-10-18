package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class DecentralizationGUI extends JPanel {
    private ControlLayout mainDecentralization;

    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public DecentralizationGUI() {
        this.setLayout(new BorderLayout());
        mainDecentralization = new ControlLayout();
        this.add(mainDecentralization, BorderLayout.CENTER);

        panelFunction = mainDecentralization.getTopPanel();
        panelData = mainDecentralization.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(3,1 ,20,5,0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}
