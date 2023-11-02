package mini_supermarket.GUI.module;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Resource;
import mini_supermarket.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ManageBannersGUI extends JDialog {
    private JScrollPane scrollPane;
    private JPanel contentPanel;
    private JLabel addBanner;
    private List<JLabel> banners = new ArrayList<>();

    public ManageBannersGUI() {
        super((Frame) null, I18n.get("frame", "manage_advert"), true);
        setSize(1050, 500);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        contentPanel = new JPanel(new GridLayout(10, 2, 20, 20));
        scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addBanner = new JLabel();

        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        setContentPane(scrollPane);

        banners = new ArrayList<>();

        for (int i = 0; i < HomeGUI.banners.size(); i++) {
            banners.add(new JLabel());
        }
        for (int i = 0; i < HomeGUI.banners.size(); i++) {
            banners.get(i).setIcon(HomeGUI.banners.get(i).getIcon());
        }

        for (JLabel label : banners) {
            label.setPreferredSize(new Dimension(500, 200));
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    removeBanner(label);
                }
            });
            contentPanel.add(label);
        }

        addBanner.setIcon(Resource.loadSVGIcon("img/icon/add_banner.svg"));
        addBanner.setHorizontalAlignment(SwingConstants.CENTER);
        addBanner.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBanner.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser(Resource.getResourcePath("img/banner", true));

                int value = jFileChooser.showSaveDialog(null);
                if (value == JFileChooser.APPROVE_OPTION) {
                    JLabel newBanner = new JLabel();
                    newBanner.setPreferredSize(new Dimension(1050, 200));
                    newBanner.setIcon(new FlatSVGIcon(jFileChooser.getSelectedFile()));
                    newBanner.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            removeBanner(newBanner);
                        }
                    });
                    banners.add(newBanner);

                    JLabel label = new JLabel();
                    label.setIcon(new FlatSVGIcon(jFileChooser.getSelectedFile()));
                    label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            new ManageBannersGUI();
                        }
                    });
                    reloadBanners();
                    HomeGUI.banners.add(label);

                    List<String> listBanners = new ArrayList<>(Settings.getBanners());
                    listBanners.add(jFileChooser.getSelectedFile().getName());
                    Settings.setBanners(listBanners);
                }
            }
        });
        contentPanel.add(addBanner);
    }

    public void reloadBanners() {
        contentPanel.removeAll();
        for (JLabel label : banners) {
            contentPanel.add(label);
        }
        contentPanel.add(addBanner);
        contentPanel.repaint();
        contentPanel.revalidate();
        setContentPane(scrollPane);
        HomeGUI homeGUI = (HomeGUI) MiniSupermarket.main.getMainMenu().getAllPanelModules()[0];
        homeGUI.renderBanner();
    }

    public void removeBanner(JLabel banner) {
        String[] options = new String[]{ I18n.get("dialog", "cancel"),  I18n.get("dialog", "delete")};
        int choice = JOptionPane.showOptionDialog(null, I18n.get("frame", "manage_advert.delete.banner"),
            I18n.get("dialog", "title.info"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            List<String> listBanners = new ArrayList<>(Settings.getBanners());
            for (int i = 0; i < banners.size(); i++) {
                if (banners.get(i) == banner) {
                    banners.remove(banner);
                    HomeGUI.banners.remove(i);
                    listBanners.remove(i);
                    break;
                }
            }
            reloadBanners();
            Settings.setBanners(listBanners);
        }
    }

    public void exit() {
        String[] options = new String[]{I18n.get("dialog", "cancel"),  I18n.get("dialog", "exit")};
        int choice = JOptionPane.showOptionDialog(null, I18n.get("frame", "manage_advert.exit"),
            I18n.get("dialog", "title.info"), JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            dispose();
        }
    }
}
