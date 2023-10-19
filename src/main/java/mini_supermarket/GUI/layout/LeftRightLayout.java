package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.component.RoundPanel;

import java.awt.*;

public class LeftRightLayout extends RoundPanel {
    private RoundPanel leftPanel;
    private RoundPanel rightPanel;
    private RoundPanel leftContainer;
    private RoundPanel rightContainer;

    public LeftRightLayout(double leftColumns, double rightColumns, int radius, int vgap, int size) {
        super(radius);
        this.setLayout(new GridLayout(1, 1, 0, 0));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(size, size, size, vgap);
        gbc.ipady = 10;
        leftPanel = new RoundPanel(radius); // TODO: set radius later
        rightPanel = new RoundPanel(radius);
        leftPanel.setBackground(new Color(215, 215, 215));
        gbc.weightx = leftColumns;
        this.add(leftPanel, gbc);
        gbc.insets = new Insets(size, 0, size, size);
        rightPanel.setBackground(new Color(215, 215, 215));
        gbc.weightx = rightColumns;
        this.add(rightPanel, gbc);
    }

    public LeftRightLayout(double leftColumns, double rightColumns, int radius, int size) {
        this(leftColumns, rightColumns, radius, size, size);
    }

    public LeftRightLayout(double leftColumns, double rightColumns) {
        this(leftColumns, rightColumns, 20, 10);
    }

    public LeftRightLayout(int panelSize, boolean standStillPanel, int radius, int size) {
        super(radius);
        this.setBackground(new Color(215, 215, 215));
        leftContainer = new RoundPanel(radius);
        rightContainer = new RoundPanel(radius);
        if (standStillPanel) {
            init(leftContainer, rightContainer, radius, size);
            leftContainer.setPreferredSize(new Dimension(panelSize, this.getHeight()));
            this.add(leftContainer, BorderLayout.WEST);
            this.add(rightContainer, BorderLayout.CENTER);
        } else {
            init(leftContainer, rightContainer, radius, size);
            rightContainer.setPreferredSize(new Dimension(panelSize, this.getHeight()));
            this.add(leftContainer, BorderLayout.CENTER);
            this.add(rightContainer, BorderLayout.EAST);
        }
    }

    public LeftRightLayout(int panelSize, boolean isLeftPanel) {
        this(panelSize, isLeftPanel, 15, 10);
    }

    public LeftRightLayout(int sizedPanel, int radius, int hgap, int vgap, boolean standStillPanel) {
        super(20);
    }


    public void init(RoundPanel leftContainer, RoundPanel rightContainer, int radius, int size) {
        this.setLayout(new BorderLayout());
        leftPanel = new RoundPanel(radius);
        rightPanel = new RoundPanel(radius);
        leftPanel.setBackground(new Color(215, 215, 215));
        rightPanel.setBackground(new Color(215, 215, 215));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(size, size, size, size);
        leftContainer.setLayout(new GridBagLayout());
        rightContainer.setLayout(new GridBagLayout());
        leftContainer.add(leftPanel, gbc);
        gbc.insets = new Insets(size, 0, size, size);
        rightContainer.add(rightPanel, gbc);
    }

    public void layoutBackground(Color panelColor, Color borderColor) {
        this.setBackground(borderColor);
        leftPanel.setBackground(panelColor);
        rightPanel.setBackground(panelColor);
        leftContainer.setBackground(panelColor);
        rightContainer.setBackground(panelColor);
    }

    public void layoutBackground(Color panelColor) {
        layoutBackground(panelColor, panelColor);
    }

    public RoundPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(RoundPanel leftRoundPanel) {
        this.leftPanel = leftRoundPanel;
    }

    public RoundPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(RoundPanel rightRoundPanel) {
        this.rightPanel = rightRoundPanel;
    }
}
