package mini_supermarket.GUI.component;

import javax.swing.*;
import java.awt.*;

public class FunctionButton extends RoundPanel {
    private JLabel icon;
    private JLabel name;
    public FunctionButton(String stringFunction) {
        super(20);
        setBackground(new Color(215,215,215));
        this.setPreferredSize(new Dimension(80,80));
        icon = new JLabel();
        name = new JLabel();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        icon.setPreferredSize(new Dimension(50,50));
        this.add(icon);
        name.setPreferredSize(new Dimension(80,20));
        name.setText(stringFunction);
        name.setForeground(new Color(0x000000));
        name.setFont(new Font("Time new Romans", Font.BOLD, 18));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(name);
    }
}
