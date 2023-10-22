package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReceiptGUI extends JPanel {
    private final ControlLayout mainBill;

    private final LeftRightLayout layoutFormAndData;


    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public ReceiptGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainBill = new ControlLayout(functions);
        this.add(mainBill, BorderLayout.CENTER);

        panelFunction = mainBill.getTopPanel();
        panelData = mainBill.getBottomPanel();

        panelData.setLayout(new BorderLayout());
        layoutFormAndData = new LeftRightLayout(1, 3, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }
}
