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

public class HomeGUI extends EmptyLayout {
    private final RoundPanel home;
    private RoundPanel content;
    public static List<JLabel> banners;
    private List<String> nameBanners;
    private Thread autoRenderBanner;
    private int currentBanner = 0;
    public HomeGUI(List<Function> functions) {
        home = getPanel();
        content = new RoundPanel();
        banners = new ArrayList<>();
        try {
            Settings.initialize();
            nameBanners = Settings.getBanners();
        } catch (Exception e) {
            Log.info(e);
        }
        for (String nameBanner : nameBanners) {
            Icon icon = Resource.loadSVGIcon("img/" + nameBanner);
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


        renderBanners();
    }

    public void renderBanners(){
        if (autoRenderBanner != null)
            autoRenderBanner.interrupt();
        content.removeAll();
        if (!banners.isEmpty()) {
            currentBanner = 0;
            content.add(banners.get(currentBanner));
        } else {
            currentBanner = -1;
        }
        content.repaint();
        content.revalidate();
        home.add(content);
        autoRenderBanner = new Thread(() -> {
            DateTime start = new DateTime();
            while (!Thread.currentThread().isInterrupted()) {
                if (!banners.isEmpty()) {
                    if (DateTime.calculateTime(start, new DateTime()) == 3) {
                        if (currentBanner == banners.size()-1)
                            currentBanner = 0;
                        else
                            currentBanner += 1;
                        content.removeAll();
                        content.add(banners.get(currentBanner));
                        content.repaint();
                        content.revalidate();
                        start = new DateTime();
                    }
                } else {
                    currentBanner = -1;
                    content.removeAll();
                    content.repaint();
                    content.revalidate();
                    start = new DateTime();
                }
            }
        });
        autoRenderBanner.start();
    }
}
