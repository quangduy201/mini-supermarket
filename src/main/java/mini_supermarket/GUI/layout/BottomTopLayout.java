package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.component.RoundPanel;

import java.awt.*;

public class BottomTopLayout extends RoundPanel {
    private RoundPanel topPanel;
    private RoundPanel bottomPanel;
    private RoundPanel topContainer;
    private RoundPanel bottomContainer;

    public BottomTopLayout(double topRows, double bottomRows, int radius, int vgap, Insets insets) {
        super(radius);
        this.setBackground(null);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(insets.top, insets.left, vgap, insets.right);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel = new RoundPanel(radius); // TODO: set radius later
        bottomPanel = new RoundPanel(radius);
//        topPanel.setBackground(new Color(215, 215, 215));
        gbc.weighty = topRows;
        this.add(topPanel, gbc);
        gbc.insets = new Insets(vgap, insets.left, insets.bottom, insets.right);
//        bottomPanel.setBackground(new Color(215, 215, 215));
        gbc.weighty = bottomRows;
        gbc.gridy = 1;
        this.add(bottomPanel, gbc);
    }

    public BottomTopLayout(double topRows, double bottomRows, int radius, int vgap, int size) {
        this(topRows, bottomRows, radius, vgap, new Insets(size, size, size, size));
    }

    public BottomTopLayout(double topRows, double bottomRows, int radius, int size) {
        this(topRows, bottomRows, radius, size, new Insets(size, size, size, size));
    }

    public BottomTopLayout(double topRows, double bottomRows) {
        this(topRows, bottomRows, 15, 10);
    }

    public BottomTopLayout(int panelHeight, boolean isTopPanel, int radius, Insets insets) {
        super(radius);
        this.setBackground(null);
        this.setLayout(new BorderLayout());
        init(radius, insets);
        if (isTopPanel) {
            topContainer.setPreferredSize(new Dimension(this.getWidth(), panelHeight));
            this.add(topContainer, BorderLayout.NORTH);
            this.add(bottomContainer, BorderLayout.CENTER);
        } else {
            bottomContainer.setPreferredSize(new Dimension(this.getWidth(), panelHeight));
            this.add(topContainer, BorderLayout.CENTER);
            this.add(bottomContainer, BorderLayout.SOUTH);
        }
    }

    public BottomTopLayout(int panelHeight, boolean isTopPanel, int radius, int size) {
        this(panelHeight, isTopPanel, radius, new Insets(size, size, size, size));
    }

    public BottomTopLayout(int panelHeight, boolean isTopPanel) {
        this(panelHeight, isTopPanel, 20, new Insets(10, 10, 10, 10));
    }

    public void init(int radius, Insets insets) {
        topContainer = new RoundPanel(radius);
        bottomContainer = new RoundPanel(radius);
        topContainer.setBackground(null);
        bottomContainer.setBackground(null);

        topPanel = new RoundPanel(radius); // TODO: set radius later
        bottomPanel = new RoundPanel(radius);
//        topPanel.setBackground(new Color(215, 215, 215));
//        bottomPanel.setBackground(new Color(215, 215, 215));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = insets;
        topContainer.setLayout(new GridBagLayout());
        bottomContainer.setLayout(new GridBagLayout());
        topContainer.add(topPanel, gbc);
        gbc.insets = new Insets(0, insets.left, insets.bottom, insets.right);
        bottomContainer.add(bottomPanel, gbc);
    }

    public void layoutBackground(Color panelColor, Color containerColor, Color borderColor) {
        this.setBackground(borderColor);
        topPanel.setBackground(panelColor);
        bottomPanel.setBackground(panelColor);
        if (topContainer != null) {
            topContainer.setBackground(containerColor);
            bottomContainer.setBackground(containerColor);
        }
    }

    public void layoutBackground(Color panelColor, Color borderColor) {
        layoutBackground(panelColor, panelColor, borderColor);
    }

    public void layoutBackground(Color panelColor) {
        layoutBackground(panelColor, panelColor);
    }

    public RoundPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(RoundPanel topPanel) {
        this.topPanel = topPanel;
    }

    public RoundPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(RoundPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public RoundPanel getTopContainer() {
        return topContainer;
    }

    public void setTopContainer(RoundPanel topContainer) {
        this.topContainer = topContainer;
    }

    public RoundPanel getBottomContainer() {
        return bottomContainer;
    }

    public void setBottomContainer(RoundPanel bottomContainer) {
        this.bottomContainer = bottomContainer;
    }
}
