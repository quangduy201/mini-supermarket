package mini_supermarket.GUI.layout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

class CustomSplitPaneDivider extends BasicSplitPaneDivider {
    private Color dividerColor;
    private int customDividerSize;

    public CustomSplitPaneDivider(BasicSplitPaneUI ui) {
        super(ui);
        dividerColor = Color.RED;
        customDividerSize = 200;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(dividerColor);
        if (orientation == JSplitPane.HORIZONTAL_SPLIT) {
            g.fillRect(0, 0, customDividerSize, getHeight());
        } else {
            g.fillRect(0, 0, customDividerSize, getHeight());
        }
        super.paint(g);
    }
}



