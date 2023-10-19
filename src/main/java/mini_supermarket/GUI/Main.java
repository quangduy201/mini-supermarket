package mini_supermarket.GUI;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.LayoutHome;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {
    private JMenuBar menuBar;
    private JLabel lbTime;
    private LeftRightLayout home;
    private RoundPanel leftHome;
    private RoundPanel rightHome;

    public Main() {
        initComponents();
    }

    public RoundPanel getRightHome() {
        return rightHome;
    }

    public void setRightHome(RoundPanel rightHome) {
        this.rightHome = rightHome;
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

        menuBar = new JMenuBar();
        lbTime = new JLabel("Hello");

        lbTime.setFont(new Font("Roboto", Font.PLAIN, 16));
        lbTime.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(lbTime);
        setJMenuBar(menuBar);

        home = new LeftRightLayout(280, true, 20, 0);
        home.setBackground(new Color(240, 240, 240));
        this.add(home);
        leftHome = home.getLeftPanel();
        leftHome.setLayout(new BorderLayout());
        rightHome = home.getRightPanel();
        rightHome.setLayout(new BorderLayout());

//        ControlLayout splitPane = new ControlLayout(rightHome);
        LayoutHome home = new LayoutHome(rightHome);
//        rightHome.add(splitPane,BorderLayout.CENTER);
        leftHome.add(home, BorderLayout.CENTER);

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

    private void exit() {
        String message = I18n.get("frame", "exit");
        String title = I18n.get("dialog", "title.exit");
        String[] options = new String[]{
            I18n.get("dialog", "cancel"),
            I18n.get("dialog", "exit")
        };
        int choice = JOptionPane.showOptionDialog(this, message, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 1)
            MiniSupermarket.exit(1);
    }
}
