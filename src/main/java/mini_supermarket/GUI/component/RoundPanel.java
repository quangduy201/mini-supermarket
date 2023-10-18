package mini_supermarket.GUI.component;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {

    private int radius;

    public RoundPanel() {
        setOpaque(false);
    }

    public RoundPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.dispose();
        super.paint(g);
    }
}
