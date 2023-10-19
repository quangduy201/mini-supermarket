package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.component.RoundPanel;

import java.awt.*;

public class ForCellsLayout extends RoundPanel {
    private RoundPanel panel1;
    private RoundPanel panel2;
    private RoundPanel panel3;
    private RoundPanel panel4;
    private int sized = 10;

    public ForCellsLayout(int radius) {
        super();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel1 = new RoundPanel(radius);
        panel2 = new RoundPanel(radius);
        panel3 = new RoundPanel(radius);
        panel4 = new RoundPanel(radius);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(sized, sized, sized, sized);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        panel1.setBackground(new Color(215, 215, 215));
        this.add(panel1, gbc);

        gbc.insets = new Insets(sized, 0, sized, sized);
        gbc.gridx = 1;
        panel2.setBackground(new Color(215, 215, 215));
        this.add(panel2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, sized, sized, sized);
        panel3.setBackground(new Color(215, 215, 215));
        this.add(panel3, gbc);

        gbc.insets = new Insets(0, 0, sized, sized);
        gbc.gridx = 1;
        panel4.setBackground(new Color(215, 215, 215));
        this.add(panel4, gbc);
    }

    public ForCellsLayout() {
        this(15);
    }

    public RoundPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(RoundPanel panel1) {
        this.panel1 = panel1;
    }

    public RoundPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(RoundPanel panel2) {
        this.panel2 = panel2;
    }

    public RoundPanel getPanel3() {
        return panel3;
    }

    public void setPanel3(RoundPanel panel3) {
        this.panel3 = panel3;
    }

    public RoundPanel getPanel4() {
        return panel4;
    }

    public void setPanel4(RoundPanel panel4) {
        this.panel4 = panel4;
    }

    public int getSized() {
        return sized;
    }

    public void setSized(int sized) {
        this.sized = sized;
    }

}
