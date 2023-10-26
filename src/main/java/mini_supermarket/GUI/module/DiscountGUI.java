package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.Main;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LayoutForm;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DiscountGUI extends ControlLayout {
    private final BottomTopLayout layoutDataDiscount;
    private final LeftRightLayout layoutFormAndData;


    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private final RoundPanel panelForm;
    private final RoundPanel panelDetailData;
    private int totalHeightPanel = 0;

    public DiscountGUI(List<Function> functions) {
        super(functions);
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
        panelData.setBackground(null);
        panelData.setLayout(new BorderLayout());

        layoutFormAndData = new LeftRightLayout(3, 1.2, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);

        panelDetailData = layoutFormAndData.getLeftPanel();
        panelDetailData.setLayout(new BorderLayout());

        layoutDataDiscount = new BottomTopLayout(1, 1, 20, 5, 0);
        panelDetailData.add(layoutDataDiscount, BorderLayout.CENTER);

        LayoutForm layoutForm = new LayoutForm(11);

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
        layoutForm.add("Con meo:", jTextField5);
        layoutForm.add("Con meo:", jTextField6);
        layoutForm.add("Combox:", jComboBox,20,0);
        layoutForm.add("Con meo:", jTextField7);
        layoutForm.add("Con meo:", jTextField8);
        layoutForm.add("Con meo:", jTextField9);
        totalHeightPanel = (layoutForm.getNumberAttribute() * layoutForm.getHeightFuncAndText());
        int sizeHeight = Main.getFrameHeight() - 165;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, sizeHeight -  totalHeightPanel, 5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        panelForm = layoutFormAndData.getRightPanel();
        panelForm.setLayout(new GridBagLayout());
        panelForm.add(layoutForm, gbc);

        panelForm.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int sizeHeight = Main.getFrameHeight() - 165;
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
