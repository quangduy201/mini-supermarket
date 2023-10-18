package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class DiscountGUI extends JPanel {
    private ControlLayout mainDiscount;

    private BottomTopLayout layoutDataDiscount;
    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public DiscountGUI() {
        this.setLayout(new BorderLayout());
        mainDiscount = new ControlLayout();
        this.add(mainDiscount, BorderLayout.CENTER);

        panelFunction = mainDiscount.getTopPanel();
        panelData = mainDiscount.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(3,1 ,20,5,0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);

        panelDetailData = layoutFormAndData.getLeftPanel();
        panelForm = layoutFormAndData.getRightPanel();

        panelDetailData.setLayout(new BorderLayout());
        layoutDataDiscount = new BottomTopLayout(1,1,20,5,0);
        panelDetailData.add(layoutDataDiscount, BorderLayout.CENTER);

    }
}
