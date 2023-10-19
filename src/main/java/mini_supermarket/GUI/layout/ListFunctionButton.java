package mini_supermarket.GUI.layout;

import mini_supermarket.GUI.component.Button;
import mini_supermarket.GUI.component.FunctionButton;
import mini_supermarket.GUI.component.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class ListFunctionButton extends RoundPanel {
    private final Button btnLeftArrow;
    private final Button btnRightArrow;
    private final RoundPanel mainFunction;
    private final RoundPanel ListFunction;
    private final RoundPanel panelFunction;
    private final JScrollPane scrollListFunc;

    public ListFunctionButton(List<String> nameFunction) {
        super(20);
        ListFunction = new RoundPanel();
        setLayout(new BorderLayout());
        mainFunction = new RoundPanel(20);
        this.add(mainFunction);
        btnLeftArrow = new Button();
        btnLeftArrow.setRadius(20);
        btnLeftArrow.setColor(new Color(0x737070));
        btnLeftArrow.setColorOver(new Color(0x737070));
        btnLeftArrow.setColorClick(new Color(0xB0ADAD));
        btnRightArrow = new Button();
        btnRightArrow.setColor(new Color(0x737070));
        btnRightArrow.setColorOver(new Color(0x737070));
        btnRightArrow.setColorClick(new Color(0xB0ADAD));
        btnRightArrow.setRadius(20);

        panelFunction = new RoundPanel(20);
        panelFunction.setBackground(new Color(215, 215, 215));

        mainFunction.setBackground(new Color(215, 215, 215));
        mainFunction.setLayout(new BorderLayout());
        btnLeftArrow.setPreferredSize(new Dimension(30, 100));
        btnRightArrow.setPreferredSize(new Dimension(30, 100));
        mainFunction.add(panelFunction, BorderLayout.CENTER);
        mainFunction.add(btnLeftArrow, BorderLayout.WEST);
        mainFunction.add(btnRightArrow, BorderLayout.EAST);
        mainFunction.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int width = getSized();
                if (width < 550) {
                    btnLeftArrow.setVisible(true);
                    btnRightArrow.setVisible(true);
                } else {
                    btnLeftArrow.setVisible(false);
                    btnRightArrow.setVisible(false);
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(3, 3, 2, 2);

        ListFunction.setLayout(new FlowLayout(FlowLayout.LEFT));
        ListFunction.setBackground(new Color(215, 215, 215));
        FunctionButton add = new FunctionButton("Thêm");
        FunctionButton edit = new FunctionButton("Sua");
        FunctionButton remove = new FunctionButton("Xoa");
        FunctionButton detail = new FunctionButton("Chitiet");
        FunctionButton excel = new FunctionButton("Excel");
        FunctionButton pdf = new FunctionButton("File PDF");

        ListFunction.add(add);
        ListFunction.add(edit);
        ListFunction.add(remove);
        ListFunction.add(detail);
        ListFunction.add(excel);
        ListFunction.add(pdf);

        scrollListFunc = new JScrollPane(ListFunction);
        scrollListFunc.setBorder(null);
        scrollListFunc.getViewport().setBackground(new Color(215, 215, 215));
        scrollListFunc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollListFunc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelFunction.setLayout(new GridBagLayout());
        panelFunction.add(scrollListFunc, gbc);

        btnLeftArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollBar horizontalScrollBar = scrollListFunc.getHorizontalScrollBar();
                int currentValue = horizontalScrollBar.getValue();
                horizontalScrollBar.setValue(currentValue - 50);
            }
        });

        btnRightArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollBar horizontalScrollBar = scrollListFunc.getHorizontalScrollBar();
                int currentValue = horizontalScrollBar.getValue();
                horizontalScrollBar.setValue(currentValue + 50);
            }
        });
    }

    public int getSized() {
        return this.getWidth();
    }
}
