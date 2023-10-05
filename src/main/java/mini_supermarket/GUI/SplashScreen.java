package mini_supermarket.GUI;

import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.Resource;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    private JLabel image;
    private JProgressBar progressBar;

    public SplashScreen() {
        setUndecorated(true);
        image = new JLabel(Resource.loadImageIcon("img/logo_full.png"));
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // You can use an indeterminate progress bar

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0x008848));
        panel.add(image, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Thread backgroundThread = new Thread(MiniSupermarket::init);
        backgroundThread.start();
    }
}
