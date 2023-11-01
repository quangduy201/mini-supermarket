package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.Main;
import mini_supermarket.GUI.component.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LayoutWarningTable extends JDialog {

    private RoundPanel panelSearch;
    private RoundPanel panelForm;
    private RoundPanel form;
    private RoundPanel panelButton;
    private RoundPanel panelDataTable;
    private JTextField search;
    private JComboBox categorySearch;
    private Button btnAssert;
    private Button btnCansel;
    private int totalHeightPanel;
    private JLabel labelText;

    private LayoutForm layoutForm;
    public LayoutWarningTable() {
        super();
        this.setSize(new Dimension(1300,700));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(240,240,240));
        init();
    }

    public Dimension getSized() {
        return this.getSize();
    }
    public void init() {
        form = new RoundPanel();
        btnAssert = new Button();
        btnCansel = new Button();
        labelText = new JLabel();
        panelButton = new RoundPanel();
        layoutForm = new LayoutForm(5);
        panelSearch = new RoundPanel(20);
        panelForm = new RoundPanel(20);
        panelDataTable = new RoundPanel();
        search = new JTextField();
        categorySearch = new JComboBox();
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;


        gbcPanel.gridwidth = 2;
        gbcPanel.weighty = 0.3;
        labelText.setPreferredSize(new Dimension(0,50));
        labelText.setFont(new Font("Time New Romans", Font.PLAIN, 20));
        labelText.setForeground(new Color(0x2CC9E5));
        labelText.setHorizontalAlignment(SwingConstants.CENTER);
        labelText.setText("Con Meo Con");
        this.add(labelText, gbcPanel);
        gbcPanel.insets = new Insets(5, 5, 5, 5);
        gbcPanel.gridwidth = 2;
        gbcPanel.weighty = 0.1;
        gbcPanel.gridy = 1;
        panelSearch.setPreferredSize(new Dimension(0,30));
        this.add(panelSearch, gbcPanel);
        gbcPanel.weighty = 5;
        gbcPanel.gridwidth = 1;
        gbcPanel.gridy = 2;
        gbcPanel.weightx = 3;
        this.add(panelDataTable, gbcPanel);
        gbcPanel.gridx = 1;
        gbcPanel.weightx = 1;
        this.add(panelForm, gbcPanel);
        gbcPanel.weighty = 0.1;
        gbcPanel.gridwidth = 2;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 3;
        gbcPanel.weightx = 1;
        panelButton.setPreferredSize(new Dimension(0,30));
        this.add(panelButton, gbcPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1.0;
//        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        panelSearch.setLayout(new GridBagLayout());
        search.setPreferredSize(new Dimension(200,0));
        panelSearch.add(search, gbc);
        gbc.gridx = 1;
        categorySearch.setPreferredSize(new Dimension(100,0));
        panelSearch.add(categorySearch, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelButton.setLayout(new GridBagLayout());
        btnAssert.setPreferredSize(new Dimension(150,0));
        panelButton.add(btnAssert, gbc);
        gbc.gridx = 1;
        btnCansel.setPreferredSize(new Dimension(150,0));
        panelButton.add(btnCansel, gbc);

//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = 1.0;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0, 0, 0, 0);
//        panelForm.setLayout(new GridBagLayout());
//        gbc.weighty = 9;
//        panelForm.add(form, gbc);
//        gbc.weighty = 1;
//        gbc.gridy = 1;
//        panelForm.add(panelButton, gbc);

        JTextField jTextField = new JTextField();
        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        JTextField jTextField3 = new JTextField();
        JTextField jTextField4 = new JTextField();
        JTextField jTextField5 = new JTextField();
        JTextField jTextField6 = new JTextField();
        JTextField jTextField7 = new JTextField();
        JTextField jTextField8 = new JTextField();
        JTextField jTextField9 = new JTextField();
        JComboBox jComboBox = new JComboBox();
        layoutForm.add("Con mèo:", jTextField);
        layoutForm.add("Con mèo:", jTextField1);
        layoutForm.add("Con meo :", jTextField2);
        layoutForm.add("Con meo:", jTextField3);
        layoutForm.add("Con meo:", jTextField4);
//        layoutForm.add("Con meo:", jTextField5);
//        layoutForm.add("Con meo:", jTextField6);
//        layoutForm.add("Combox:", jComboBox,20,0);
//        layoutForm.add("Con meo:", jTextField7);
//        layoutForm.add("Con meo:", jTextField8);
//        layoutForm.add("Con meo:", jTextField9);
        totalHeightPanel = (layoutForm.getNumberAttribute() * layoutForm.getHeightFuncAndText());
        int sizeHeight = Main.getFrameHeight() - 165;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, sizeHeight -  totalHeightPanel, 5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        panelForm.setLayout(new GridBagLayout());
        panelForm.add(layoutForm, gbc);

        panelForm.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int sizeHeight =  getSized().height - 165;
                int distancePanel = sizeHeight - totalHeightPanel;
                if (distancePanel >= 0 && totalHeightPanel != 0) {
                    gbc.insets = new Insets(5, 5, distancePanel, 5);
                } else {
                    gbc.insets = new Insets(5, 5, 5, 5);
                }
                panelForm.add(layoutForm, gbc);
                panelForm.revalidate();
                panelForm.repaint();
            }
        });

    }
}
