package mini_supermarket.GUI.dialog;

import mini_supermarket.GUI.Main;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.utils.I18n;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomDialog extends JDialog {
    protected RoundPanel pnlTitle;
    protected JLabel lbTitle;
    protected RoundPanel pnlSearch;
    protected JTextField txtSearch;
    protected JComboBox cbbSearch;
    protected JButton btnRefresh;
    protected RoundPanel pnlTable;
    protected GridBagConstraints gbcTable;
    protected RoundPanel pnlForm;
    protected LayoutForm layoutForm;
    protected RoundPanel pnlButton;
    protected JButton btnConfirm;
    protected JButton btnCancel;
    protected int totalHeightPanel;
    protected boolean cancel;

    public CustomDialog(String title, int numberAttribute, boolean hasTable) {
        super((Frame) null, true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel = true;
                dispose();
            }
        });
        this.getRootPane().registerKeyboardAction(e -> {
            cancel = true;
            dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(new Color(240, 240, 240));
        if (hasTable)
            this.setSize(new Dimension(1300, 700));
        else
            this.setSize(new Dimension(500, 600));
        this.setLocationRelativeTo(null);

        init(title, numberAttribute, hasTable);
    }

    public Dimension getDimension() {
        return this.getSize();
    }

    public void init(String title, int numberAttribute, boolean hasTable) {
        pnlTitle = new RoundPanel();
        lbTitle = new JLabel();
        pnlSearch = new RoundPanel();
        txtSearch = new JTextField();
        cbbSearch = new JComboBox();
        btnRefresh = new JButton();
        pnlTable = new RoundPanel();
        gbcTable = new GridBagConstraints();
        pnlForm = new RoundPanel();
        layoutForm = new LayoutForm(numberAttribute);
        pnlButton = new RoundPanel();
        btnConfirm = new JButton();
        btnCancel = new JButton();
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;

        gbcPanel.gridwidth = 2;
        gbcPanel.weighty = 0.3;
        lbTitle.setPreferredSize(new Dimension(0, 50));
        lbTitle.setFont(new Font("Roboto", Font.PLAIN, 20));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setText(title);
        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setBackground(new Color(0x90bae0));
        pnlTitle.add(lbTitle);
        this.add(pnlTitle, gbcPanel);

        if (hasTable) {
            gbcPanel.insets = new Insets(2, 2, 2, 2);
            gbcPanel.gridwidth = 2;
            gbcPanel.weighty = 0.1;
            gbcPanel.gridy = 1;
            pnlSearch.setPreferredSize(new Dimension(0, 30));
            this.add(pnlSearch, gbcPanel);
            gbcPanel.weighty = 5;
            gbcPanel.gridwidth = 1;
            gbcPanel.gridy = 2;
            gbcPanel.weightx = 3;
            pnlTable.setLayout(new GridBagLayout());
            this.add(pnlTable, gbcPanel);

            gbcTable.fill = GridBagConstraints.BOTH;
            gbcTable.insets = new Insets(10, 10, 10, 10);
            gbcTable.weightx = 1.0;
            gbcTable.weighty = 1.0;
            gbcTable.gridx = 0;
            gbcTable.gridy = 0;

            gbcPanel.gridx = 1;
            gbcPanel.weightx = 1;
            this.add(pnlForm, gbcPanel);
            gbcPanel.weighty = 0.1;
            gbcPanel.gridwidth = 2;
            gbcPanel.gridx = 0;
            gbcPanel.gridy = 3;
        } else {
            gbcPanel.weighty = 5;
            gbcPanel.gridy = 1;
            gbcPanel.weightx = 1;
            this.add(pnlForm, gbcPanel);
            gbcPanel.weighty = 0.3;
            gbcPanel.gridx = 0;
            gbcPanel.gridy = 2;
        }
        gbcPanel.weightx = 1;
        pnlButton.setPreferredSize(new Dimension(0, 30));
        this.add(pnlButton, gbcPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1.0;
//        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        pnlSearch.setLayout(new GridBagLayout());
        txtSearch.setPreferredSize(new Dimension(200, 0));
        pnlSearch.add(txtSearch, gbc);
        gbc.gridx = 1;
        cbbSearch.setPreferredSize(new Dimension(100, 0));
        pnlSearch.add(cbbSearch, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlButton.setLayout(new GridBagLayout());
        btnConfirm.setPreferredSize(new Dimension(120, 0));
        btnConfirm.setText(I18n.get("dialog", "confirm"));
        pnlButton.add(btnConfirm, gbc);
        gbc.gridx = 1;
        btnRefresh.setPreferredSize(new Dimension(120, 0));
        btnRefresh.setText(I18n.get("dialog", "refresh"));
        pnlButton.add(btnRefresh, gbc);
        gbc.gridx = 2;
        btnCancel.setPreferredSize(new Dimension(120, 0));
        btnCancel.setText(I18n.get("dialog", "cancel"));
        pnlButton.add(btnCancel, gbc);

        totalHeightPanel = (layoutForm.getNumberAttribute() * layoutForm.getHeightFuncAndText());
        int sizeHeight = Main.getFrameHeight() - 165;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, sizeHeight -  totalHeightPanel, 5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        pnlForm.setLayout(new GridBagLayout());
        pnlForm.add(layoutForm, gbc);
        pnlForm.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int sizeHeight =  getDimension().height - 165;
                int distancePanel = sizeHeight - totalHeightPanel;
                if (distancePanel >= 0 && totalHeightPanel != 0) {
                    gbc.insets = new Insets(5, 5, distancePanel, 5);
                } else {
                    gbc.insets = new Insets(5, 5, 5, 5);
                }
                pnlForm.add(layoutForm, gbc);
                pnlForm.revalidate();
                pnlForm.repaint();
            }
        });
    }

    public void setUpForeignTextField(JTextField textField, CustomTable table) {
        textField.setEditable(false);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                pnlTable.removeAll();
                pnlTable.add(table, gbcTable);
                pnlTable.revalidate();
                pnlTable.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
