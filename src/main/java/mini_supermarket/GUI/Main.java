package mini_supermarket.GUI;

import mini_supermarket.DTO.Account;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.component.main.MainMenu;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Resource;
import mini_supermarket.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private JMenuBar menuBar;
    private JLabel lbTime;
    private boolean running;
    private Thread time;
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

        running = true;
        time = new Thread(() -> {
            while (running)
                updateTimeLabel();
        });
        time.start();

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

    private void updateTimeLabel() {
        try {
            DateTime nextSecond = DateTime.now().plusSeconds(1);
            nextSecond.setNano(0);
            long sleepTime = DateTime.calculateTime(DateTime.now(), nextSecond, TimeUnit.MILLISECONDS);
            if (sleepTime > 0)
                Thread.sleep(sleepTime);
            String time = nextSecond.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss a"));
            lbTime.setText(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout() {
        running = false;
        setAccount(null);
        MiniSupermarket.login = new Login();
        dispose();
        MiniSupermarket.login.setVisible(true);
        MiniSupermarket.main = null;
        System.gc();
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
            running = false;
            if (checkBox.isSelected())
                setAccount(null);
            MiniSupermarket.exit(1);
        }
    }
}
