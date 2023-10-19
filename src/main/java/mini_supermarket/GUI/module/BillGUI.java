package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class BillGUI extends JPanel {
    private final ControlLayout mainBill;

    private final LeftRightLayout layoutFormAndData;


    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public BillGUI() {
        this.setLayout(new BorderLayout());
        mainBill = new ControlLayout();
        this.add(mainBill, BorderLayout.CENTER);

        panelFunction = mainBill.getTopPanel();
        panelData = mainBill.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(1, 3, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}