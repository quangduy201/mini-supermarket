package mini_supermarket.GUI.dialog;

import mini_supermarket.GUI.component.RoundPanel;
import org.hibernate.engine.jdbc.Size;

import javax.swing.*;
import java.awt.*;

public class LayoutForm extends RoundPanel {
    private int numberAttribute;
    private int heightFuncAndText = 35;

    private Insets insets;

    public int getHeightFuncAndText() {
        return heightFuncAndText + 5;
    }

    public void setHeightFuncAndText(int heightFuncAndText) {
        this.heightFuncAndText = heightFuncAndText;
    }

    private RoundPanel PanelDataEntry;
    private GridBagConstraints gbc;
    private GridBagConstraints gbcPanel;
    private GridBagConstraints gbcInPanel;
    private JLabel textView;
    private JLabel textOne;
    private JLabel textTwo;
    private RoundPanel mainForm;
    private JScrollPane jScrollPane;

    private int textOccupy = 1;
    private int functionOccupy = 2;

    public int getTextOccupy() {
        return textOccupy;
    }

    public void setTextOccupy(int textOccupy) {
        this.textOccupy = textOccupy;
    }

    public int getFunctionOccupy() {
        return functionOccupy;
    }

    public void setFunctionOccupy(int functionOccupy) {
        this.functionOccupy = functionOccupy;
    }

    public int getNumberAttribute() {
        return numberAttribute;
    }

    public void setNumberAttribute(int numberAttribute) {
        this.numberAttribute = numberAttribute;
    }


    public LayoutForm(int numberAttribute) {
        this(numberAttribute, new Insets(0,0,0,0));
    }

    public LayoutForm(int numberAttribute, Insets insets) {
        super(20);
        this.insets = insets;
        this.numberAttribute = numberAttribute;
        mainForm = new RoundPanel();
        jScrollPane = new JScrollPane();
        jScrollPane = new JScrollPane(mainForm);
        jScrollPane.setBorder(null);
        jScrollPane.getViewport().setBackground(null);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new GridBagLayout());
        setBackground(new Color(215,215, 215));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(jScrollPane, gbc);
        gbc.insets = new Insets(insets.top, 0, 0, 0);
        mainForm.setLayout(new GridBagLayout());
        mainForm.setBackground(new Color(215,215, 215));

        gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.weighty = 1.0;
        gbcPanel.gridy = 0;
    }

    public void addTextLeft(String text, JComponent dataEntry) {
        initializationPanel();
        textView = new JLabel();
        textView.setHorizontalAlignment(SwingConstants.CENTER);
        textView.setFont(new Font("Roboto", Font.PLAIN, 14));
        textView.setPreferredSize(new Dimension(0,heightFuncAndText));
        textView.setText(text);
        gbcPanel.weightx = textOccupy;
        gbcPanel.gridx = 0;
        PanelDataEntry.add(textView, gbcPanel);
        dataEntry.setPreferredSize(new Dimension(0,heightFuncAndText));
        gbcPanel.weightx = functionOccupy;
        gbcPanel.gridx = 1;
        PanelDataEntry.add(dataEntry, gbcPanel);
        gbc.gridy++;
    }

    public void addTwoComponent(JComponent firstDataEntry, JComponent secondDataEntry, int firstDataWidth, int secondDataWidth, int hgap) {
        initializationPanel();
        firstDataEntry.setPreferredSize(new Dimension(0,heightFuncAndText));
        gbcPanel.weightx = firstDataWidth;
        gbcPanel.gridx = 0;
        gbcPanel.insets = new Insets(0, 0, 0, hgap);
        PanelDataEntry.add(firstDataEntry, gbcPanel);
        secondDataEntry.setPreferredSize(new Dimension(0,heightFuncAndText));
        gbcPanel.weightx = secondDataWidth;
        gbcPanel.gridx = 1;
        gbcPanel.insets = new Insets(0, 0, 0, 0);
        PanelDataEntry.add(secondDataEntry, gbcPanel);
        gbc.gridy++;
    }

    public  void addPanel(RoundPanel panel, int height) {
        gbcPanel.weightx = 1.0;
        gbcPanel.gridx = 0;
        panel.setPreferredSize(new Dimension(0, height));
        mainForm.add(panel, gbc);
        gbc.gridy++;
    }

    public void addTwoText(String strOne, String strTwo, int firstTextWidth, int secondTextWidth, int hgap) {
        initializationPanel();
        textOne = new JLabel();
        gbc.weighty = 1.0;
        textOne.setHorizontalAlignment(SwingConstants.LEFT);
        textOne.setFont(new Font("Roboto", Font.PLAIN, 14));
        textOne.setPreferredSize(new Dimension(0,heightFuncAndText));
        textOne.setText(strOne);
        gbcPanel.weightx = firstTextWidth;
        gbcPanel.gridx = 0;
        gbcPanel.insets = new Insets(0, 5, 0, hgap);
        PanelDataEntry.add(textOne, gbcPanel);
        textTwo = new JLabel();
        textTwo.setHorizontalAlignment(SwingConstants.LEFT);
        textTwo.setFont(new Font("Roboto", Font.PLAIN, 14));
        textTwo.setPreferredSize(new Dimension(0,heightFuncAndText));
        textTwo.setText(strTwo);
        gbcPanel.weightx = secondTextWidth;
        gbcPanel.gridx = 1;
        gbc.gridy++;
        gbcPanel.insets = new Insets(0, 0, 0, 0);
        PanelDataEntry.add(textTwo, gbcPanel);
        gbc.gridy++;
    }

    public void addTextAbove(String text, JComponent dataEntry) {
        textView = new JLabel();
        textView.setHorizontalAlignment(SwingConstants.LEFT);
        textView.setFont(new Font("Roboto", Font.PLAIN, 14));
        textView.setPreferredSize(new Dimension(0,20));
        textView.setText(text);
        gbc.weightx = 1.0;
        gbc.insets = new Insets(insets.top, 5, 0, 0);
        mainForm.add(textView, gbc);
        dataEntry.setPreferredSize(new Dimension(0,heightFuncAndText));
        gbc.gridy++;
        gbc.insets = new Insets(insets.top, 0, 0, 0);
        mainForm.add(dataEntry, gbc);
        gbc.gridy++;
    }

    public void add(String text, JComponent dataEntry, int insetLeft, int insetRight) {
        initializationPanel();
        textView = new JLabel();
        textView.setHorizontalAlignment(SwingConstants.CENTER);
        textView.setFont(new Font("Roboto", Font.PLAIN, 14));
        textView.setText(text);
        textView.setPreferredSize(new Dimension(0,heightFuncAndText));
        gbcPanel.weightx = textOccupy;
        gbcPanel.gridx = 0;
        PanelDataEntry.add(textView, gbcPanel);
        dataEntry.setPreferredSize(new Dimension(0,heightFuncAndText));
        gbcPanel.weightx = functionOccupy;
        gbcPanel.gridx = 1;
        RoundPanel panelComBox = new RoundPanel(20);
        panelComBox.setLayout(new GridBagLayout());
        panelComBox.setPreferredSize(new Dimension(0,heightFuncAndText + 2));
        gbcInPanel = new GridBagConstraints();
        panelComBox.setBackground(new Color(215,215, 215));
        gbcInPanel.fill = GridBagConstraints.BOTH;
        gbcInPanel.weighty = 1.0;
        gbcInPanel.weightx = 1.0;
        gbcInPanel.gridx = 0;
        gbcInPanel.gridy = 0;
        gbcInPanel.insets = new Insets(insetRight,insetLeft,insetRight,insetLeft);
        panelComBox.add(dataEntry, gbcInPanel);
        gbcPanel.insets = new Insets(0,0,0,0);
        PanelDataEntry.add(panelComBox, gbcPanel);
        gbc.gridy++;
    }

    public void initializationPanel() {
        PanelDataEntry = new RoundPanel(20);
        PanelDataEntry.setBackground(new Color(215,215, 215));
//        PanelDataEntry.setLayout(new BorderLayout(0,0));
        PanelDataEntry.setLayout(new GridBagLayout());
        mainForm.add(PanelDataEntry, gbc);
    }
}
