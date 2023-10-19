package mini_supermarket.GUI;

import mini_supermarket.utils.Resource;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    private final JPanel panel;
    private final JLabel image;
    private final JProgressBar progressBar;

    public SplashScreen() {
        panel = new JPanel();
        image = new JLabel();
        progressBar = new JProgressBar();

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x008848));

        image.setIcon(Resource.loadSVGIcon("img/logo.svg"));
        panel.add(image, BorderLayout.CENTER);

        progressBar.setIndeterminate(true);
        panel.add(progressBar, BorderLayout.SOUTH);

        getContentPane().add(panel);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
