package mini_supermarket.GUI.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class RoundPanel extends JPanel {

    private int topRadius;
    private int bottomRadius;

    public int getTopRadius() {
        return topRadius;
    }

    public void setTopRadius(int topRadius) {
        this.topRadius = topRadius;
    }

    public int getBottomRadius() {
        return bottomRadius;
    }

    public void setBottomRadius(int bottomRadius) {
        this.bottomRadius = bottomRadius;
    }

    public RoundPanel() {
        setOpaque(false);
    }

    public RoundPanel(int radius) {
        this(radius,radius);
    }
    public RoundPanel(int topRadius, int bottomRadius) {
        this.topRadius = topRadius;
        this.bottomRadius = bottomRadius;
        repaint();
        setOpaque(false);
    }

    public int getRadius() {
        return topRadius;
    }

    public void setRadius(int topRadius) {
        this.topRadius = topRadius;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), topRadius, topRadius);
//        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), topRadius, bottomRadius);
        g2d.dispose();
    }
}
