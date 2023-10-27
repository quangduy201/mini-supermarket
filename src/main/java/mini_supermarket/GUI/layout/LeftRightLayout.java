package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.component.RoundPanel;

import java.awt.*;

public class LeftRightLayout extends RoundPanel {
    private RoundPanel leftPanel;
    private RoundPanel rightPanel;
    private RoundPanel leftContainer;
    private RoundPanel rightContainer;

    public LeftRightLayout(double leftColumns, double rightColumns, int radius, int hgap, Insets insets) {
        super(radius);
        this.setBackground(null);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(insets.top, insets.left, insets.bottom, hgap);
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel = new RoundPanel(radius); // TODO: set radius later
        rightPanel = new RoundPanel(radius);
//        leftPanel.setBackground(new Color(215, 215, 215));
        gbc.weightx = leftColumns;
        this.add(leftPanel, gbc);
        gbc.insets = new Insets(insets.top, hgap, insets.bottom, insets.right);
//        rightPanel.setBackground(new Color(215, 215, 215));
        gbc.weightx = rightColumns;
        gbc.gridx = 1;
        this.add(rightPanel, gbc);
    }

    public LeftRightLayout(double leftColumns, double rightColumns, int radius, int vgap, int size) {
        this(leftColumns, rightColumns, radius, vgap, new Insets(size, size, size, size));
    }

    public LeftRightLayout(double leftColumns, double rightColumns, int radius, int size) {
        this(leftColumns, rightColumns, radius, size, new Insets(size, size, size, size));
    }

    public LeftRightLayout(double leftColumns, double rightColumns) {
        this(leftColumns, rightColumns, 20, 10);
    }

    public LeftRightLayout(int panelWidth, boolean isLeftPanel, int radius, Insets insets) {
        super(radius);
        this.setBackground(null);
        this.setLayout(new BorderLayout());
        init(radius, insets);
        if (isLeftPanel) {
            leftContainer.setPreferredSize(new Dimension(panelWidth, this.getHeight()));
            this.add(leftContainer, BorderLayout.WEST);
            this.add(rightContainer, BorderLayout.CENTER);
        } else {
            rightContainer.setPreferredSize(new Dimension(panelWidth, this.getHeight()));
            this.add(leftContainer, BorderLayout.CENTER);
            this.add(rightContainer, BorderLayout.EAST);
        }
    }

    public LeftRightLayout(int panelSize, boolean isLeftPanel, int radius, int size) {
        this(panelSize, isLeftPanel, radius, new Insets(size, size, size, size));
    }

    public LeftRightLayout(int panelSize, boolean isLeftPanel) {
        this(panelSize, isLeftPanel, 20, new Insets(10, 10, 10, 10));
    }

    public void init(int radius, Insets insets) {
        leftContainer = new RoundPanel(radius);
        rightContainer = new RoundPanel(radius);
        leftContainer.setBackground(null);
        rightContainer.setBackground(null);

        leftPanel = new RoundPanel(radius); // TODO: set radius later
        rightPanel = new RoundPanel(radius);
//        leftPanel.setBackground(new Color(215, 215, 215));
//        rightPanel.setBackground(new Color(215, 215, 215));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = insets;
        leftContainer.setLayout(new GridBagLayout());
        rightContainer.setLayout(new GridBagLayout());
        leftContainer.add(leftPanel, gbc);
        gbc.insets = new Insets(insets.top, 0, insets.bottom, insets.right);
        rightContainer.add(rightPanel, gbc);
    }

    public void layoutBackground(Color panelColor, Color containerBorder, Color borderColor) {
        this.setBackground(borderColor);
        leftPanel.setBackground(panelColor);
        rightPanel.setBackground(panelColor);
        if (leftContainer != null) {
            leftContainer.setBackground(containerBorder);
            rightContainer.setBackground(containerBorder);
        }
    }

    public void layoutBackground(Color panelColor, Color borderColor) {
        layoutBackground(panelColor, panelColor, borderColor);
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

    public RoundPanel getLeftContainer() {
        return leftContainer;
    }

    public void setLeftContainer(RoundPanel leftContainer) {
        this.leftContainer = leftContainer;
    }

    public RoundPanel getRightContainer() {
        return rightContainer;
    }

    public void setRightContainer(RoundPanel rightContainer) {
        this.rightContainer = rightContainer;
    }
}
