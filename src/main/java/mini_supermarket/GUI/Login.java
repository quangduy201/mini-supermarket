package mini_supermarket.GUI;

import mini_supermarket.utils.Resource;
import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JSVGCanvas add;
    private JSVGCanvas edit;
    private JSVGCanvas delete;
    private JSVGCanvas detail;
    private JSVGCanvas excel;
    private JSVGCanvas pdf;
    private JSVGCanvas cancel;
    private JSVGCanvas refresh;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add = Resource.loadSVGCanvas("img/icon/Add.svg", true, 200, 200);
        edit = Resource.loadSVGCanvas("img/icon/Edit.svg", true, 200, 200);
        delete = Resource.loadSVGCanvas("img/icon/Delete.svg", true, 200, 200);
        detail = Resource.loadSVGCanvas("img/icon/Detail.svg", true, 200, 200);
        excel = Resource.loadSVGCanvas("img/icon/Excel.svg", true, 200, 200);
        pdf = Resource.loadSVGCanvas("img/icon/PDF.svg", true, 200, 200);
        cancel = Resource.loadSVGCanvas("img/icon/cancel.svg", true, 200, 200);
        refresh = Resource.loadSVGCanvas("img/icon/refresh.svg", true, 200, 200);
        JPanel panel = new JPanel(new GridLayout(3, 3));
        panel.setBackground(new Color(0x008848));
        panel.add(add);
        panel.add(edit);
        panel.add(delete);
        panel.add(detail);
        panel.add(excel);
        panel.add(pdf);
        panel.add(cancel);
        panel.add(refresh);
        getContentPane().add(panel);
//        pack();
    }
}
