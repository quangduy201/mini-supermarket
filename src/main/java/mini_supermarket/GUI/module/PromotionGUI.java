package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PromotionGUI extends JPanel {
    private final ControlLayout mainPromotion;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public PromotionGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainPromotion = new ControlLayout(functions);
        this.add(mainPromotion, BorderLayout.CENTER);

        panelFunction = mainPromotion.getTopPanel();
        panelData = mainPromotion.getBottomPanel();

    }
}
