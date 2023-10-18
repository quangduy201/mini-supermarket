package mini_supermarket.GUI;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.*;
import mini_supermarket.main.MiniSupermarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main extends JFrame {
    public Main() {

        initComponents();
    }
    private LeftRightLayout home;
    private RoundPanel leftHome;
    private RoundPanel rightHome;

    public RoundPanel getRightHome() {
        return rightHome;
    }

    public void setRightHome(RoundPanel rightHome) {
        this.rightHome = rightHome;
    }

    private void initComponents() {
        setSize(1400, 810);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500,500));

        home = new LeftRightLayout(280,true,20,0);
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
                Dimension d= MiniSupermarket.main.getSize();
                Dimension minD= MiniSupermarket.main.getMinimumSize();
                if(d.width<minD.width)
                    d.width=minD.width;
                if(d.height<minD.height)
                    d.height=minD.height;
                MiniSupermarket.main.setSize(d);
            }
        });

    }
}
