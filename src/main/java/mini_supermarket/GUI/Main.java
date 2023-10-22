package mini_supermarket.GUI;

import mini_supermarket.DTO.*;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.component.main.MainMenu;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Main extends JFrame {
    private JMenuBar menuBar;
    private JLabel lbTime;
    private LeftRightLayout mainLayout;
    private MainMenu mainMenu;
    private Account account;

    public Main(Account account) {
        setAccount(account);
        initComponents();
    }

    public LeftRightLayout getMainLayout() {
        return mainLayout;
    }

    public void setAccount(Account account) {
        this.account = account;
        Settings.setLastAccount(account);
    }

    private void initComponents() {
        setSize(1400, 810);
        setTitle(I18n.get("frame", "main"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 500));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        setIconImage(Resource.loadSVGIcon("img/logo.svg").getImage());

        menuBar = new JMenuBar();
        getRootPane().setBackground(new Color(215, 215, 215));

        lbTime = new JLabel();
        lbTime.setFont(new Font("Roboto", Font.PLAIN, 16));
        lbTime.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(lbTime);
        setJMenuBar(menuBar);

        mainLayout = new LeftRightLayout(280, true, 20, 0);
        mainLayout.setBackground(new Color(240, 240, 240));
        this.add(mainLayout);

        RoundPanel leftPanel = mainLayout.getLeftPanel();
        leftPanel.setLayout(new BorderLayout());

        mainMenu = new MainMenu(account);
        leftPanel.add(mainMenu, BorderLayout.CENTER);

        RoundPanel rightPanel = mainLayout.getRightPanel();
        rightPanel.setLayout(new BorderLayout());

        JPanel panelModule = mainMenu.getPanelModule(1L, List.of());
        rightPanel.add(panelModule);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension d = MiniSupermarket.main.getSize();
                Dimension minD = MiniSupermarket.main.getMinimumSize();
                if (d.width < minD.width)
                    d.width = minD.width;
                if (d.height < minD.height)
                    d.height = minD.height;
                MiniSupermarket.main.setSize(d);
            }
        });
    }

    public void setTime(String time) {
        lbTime.setText(time);
    }

    public void logout() {
        setAccount(null);
        dispose();
        MiniSupermarket.login = new Login();
        MiniSupermarket.login.setVisible(true);
    }

    public void exit() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(I18n.get("frame", "exit"));
        JCheckBox checkBox = new JCheckBox(I18n.get("frame", "logout.checkbox"));
        panel.add(label, BorderLayout.CENTER);
        panel.add(checkBox, BorderLayout.SOUTH);
        String title = I18n.get("dialog", "title.exit");
        String[] options = new String[]{
            I18n.get("dialog", "cancel"),
            I18n.get("dialog", "exit")
        };
        int choice = JOptionPane.showOptionDialog(this, panel, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            if (checkBox.isSelected())
                setAccount(null);
            MiniSupermarket.exit(1);
        }
    }
}
