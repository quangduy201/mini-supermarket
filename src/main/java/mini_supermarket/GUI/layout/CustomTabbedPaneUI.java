package mini_supermarket.GUI.layout;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

class CustomTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        // Vẽ nền của tab tùy chỉnh
        g.setColor(new Color(0xF0F0F0));
//        g.fillRoundRect(x, y, w, h, 10, 10);
    }
}
