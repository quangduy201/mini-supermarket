package mini_supermarket.GUI.layout;


import mini_supermarket.GUI.component.RoundPanel;

import java.awt.*;

public class BottomTopLayout extends RoundPanel {
    private RoundPanel topPanel;
    private RoundPanel bottomPanel;
    private RoundPanel topContainer;
    private RoundPanel bottomContainer;

    public BottomTopLayout(double topRows, double bottomRows, int radius, int hgap, int size) {
        super(radius);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(size, size, 0, size);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel = new RoundPanel(radius); // TODO: set radius later
        bottomPanel = new RoundPanel(radius);
        topPanel.setBackground(new Color(215, 215, 215));
        gbc.weighty = topRows;
        this.add(topPanel, gbc);
        gbc.insets = new Insets(hgap, size, size, size);
        bottomPanel.setBackground(new Color(215, 215, 215));
        gbc.weighty = bottomRows;
        gbc.gridy = 1;
        this.add(bottomPanel, gbc);
    }

    public BottomTopLayout(double topRows, double bottomRows, int radius, int size) {
        this(topRows, bottomRows, radius, size, size);
    }

    public BottomTopLayout(double topRows, double bottomRows) {
        this(topRows, bottomRows, 15, 10);
    }

    public BottomTopLayout(int panelHeight, boolean isTopPanel, int radius, int size) {
        super(radius);
        this.setBackground(new Color(215, 215, 215));
        topContainer = new RoundPanel(radius); // TODO: set radius later
        bottomContainer = new RoundPanel(radius);
        init(topContainer, bottomContainer, radius, size);
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

    public BottomTopLayout(int panelHeight, boolean isTopPanel) {
        this(panelHeight, isTopPanel, 20, 10);
    }

    public void init(RoundPanel topContainer, RoundPanel bottomContainer, int radius, int size) {
        this.setLayout(new BorderLayout());
        topPanel = new RoundPanel(radius);
        bottomPanel = new RoundPanel(radius);
        topPanel.setBackground(new Color(215, 215, 215));
        bottomPanel.setBackground(new Color(215, 215, 215));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(size, size, size, size);
        topContainer.setLayout(new GridBagLayout());
        bottomContainer.setLayout(new GridBagLayout());
        topContainer.add(topPanel, gbc);
        gbc.insets = new Insets(0, size, size, size);
        bottomContainer.add(bottomPanel, gbc);
    }

    public void layoutBackground(Color panelColor, Color borderColor) {
        this.setBackground(borderColor);
        topPanel.setBackground(panelColor);
        bottomPanel.setBackground(panelColor);
        topContainer.setBackground(panelColor);
        bottomContainer.setBackground(panelColor);
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
}
