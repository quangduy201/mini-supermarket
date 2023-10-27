package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.component.RoundPanel;

import java.awt.*;

public class EmptyLayout extends RoundPanel {
    private RoundPanel panel;

    public EmptyLayout() {
        this(20, new Insets(5, 5, 5, 5));
    }

    public EmptyLayout(int radius, Insets insets) {
        super(radius);
        this.setBackground(null);
        setLayout(new GridBagLayout());
        panel = new RoundPanel(radius);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = insets;
        this.add(panel, gbc);
    }

    public RoundPanel getPanel() {
        return panel;
    }

    public void setPanel(RoundPanel panel) {
        this.panel = panel;
    }
}
