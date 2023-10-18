package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class CategoryGUI extends JPanel {
    private ControlLayout mainCategory;

    private LeftRightLayout layoutFormAndData;


    private RoundPanel panelFunction;
    private RoundPanel panelData;

    public CategoryGUI() {
        this.setLayout(new BorderLayout());
        mainCategory = new ControlLayout();
        this.add(mainCategory, BorderLayout.CENTER);

        panelFunction = mainCategory.getTopPanel();
        panelData = mainCategory.getBottomPanel();

    }
}
