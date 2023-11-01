package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.EmptyLayout;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.Log;
import mini_supermarket.utils.Resource;
import mini_supermarket.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeGUI extends EmptyLayout {
    public static List<JLabel> banners;
    private final RoundPanel home;
    private RoundPanel content;
    private List<String> bannerNames;
    private Thread autoRenderBanner;
    private int currentBanner = 0;
    private boolean bannersAreRunning;

    public HomeGUI(List<Function> functions) {
        home = getPanel();
        content = new RoundPanel();
        banners = new ArrayList<>();
        try {
            Settings.initialize();
            bannerNames = Settings.getBanners();
        } catch (Exception e) {
            Log.error(e.toString());
        }
        for (String bannerName : bannerNames) {
            Icon icon = Resource.loadSVGIcon("img/banner/" + bannerName);
            banners.add(new JLabel(icon));
        }

//        this.setLayout(new GridBagLayout());
//        home = new RoundPanel(20);
//        home.setBackground(new Color(215, 215, 215));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5, 5, 5, 5);
//        this.add(home, gbc);

        home.setLayout(new BorderLayout());

        content.setLayout(new GridBagLayout());
        home.add(content, BorderLayout.CENTER);

        for (JLabel banner : banners) {
            banner.setCursor(new Cursor(Cursor.HAND_CURSOR));
            banner.setHorizontalAlignment(SwingConstants.CENTER);
            banner.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    new ManageBannersGUI();
                }
            });
        }

        bannersAreRunning = true;
        autoRenderBanner = new Thread(() -> {
            renderBanner();
            while (bannersAreRunning)
                runBanners();
        });
        autoRenderBanner.start();
    }

    public void runBanners() {
        try {
            DateTime nextSecond = DateTime.now().plusSeconds(3);
            nextSecond.setNano(0);
            long sleepTime = DateTime.calculateTime(DateTime.now(), nextSecond, TimeUnit.MILLISECONDS);
            if (sleepTime > 0)
                Thread.sleep(sleepTime);
            renderBanner();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderBanner() {
        content.removeAll();
        if (banners.isEmpty()) {
            currentBanner = -1;
            content.repaint();
            content.revalidate();
            return;
        }
        if (currentBanner >= banners.size() - 1)
            currentBanner = 0;
        else
            currentBanner += 1;
        content.add(banners.get(currentBanner));
        content.repaint();
        content.revalidate();
    }
}
