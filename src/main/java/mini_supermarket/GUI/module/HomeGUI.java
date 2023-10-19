package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;

import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JPanel {
    private final RoundPanel home;

    public HomeGUI() {
        this.setLayout(new GridBagLayout());
        home = new RoundPanel(20);
        home.setBackground(new Color(215, 215, 215));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(home, gbc);
    }
}
