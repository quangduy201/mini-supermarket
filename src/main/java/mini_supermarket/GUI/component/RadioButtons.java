package mini_supermarket.GUI.component;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class RadioButtons extends JPanel {
    protected final Map<String, JRadioButton> buttons;
    protected boolean editable;
    protected JRadioButton selectedButton;

    public RadioButtons(String... names) {
        this(true, names);
    }

    public RadioButtons(boolean editable, String... names) {
        buttons = new HashMap<>();
        this.editable = editable;
        for (String name : names)
            addButton(name);
    }

    public Map<String, JRadioButton> getButtons() {
        return buttons;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public JRadioButton getButton(String text) {
        return buttons.get(text);
    }

    public JRadioButton getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(String text) {
        JRadioButton button = getButton(text);
        if (button == null)
            return;
        setSelectedButton(button);
    }

    public void setSelectedButton(JRadioButton button) {
        if (button.isSelected())
            return;
        selectedButton = button;
        for (JRadioButton btn : buttons.values())
            btn.setSelected(false);
        button.setSelected(true);
    }

    public int getButtonCount() {
        return buttons.size();
    }

    public JRadioButton addButton(String text) {
        JRadioButton button = new JRadioButton(text);
        if (buttons.isEmpty()) {
            button.setSelected(true);
            selectedButton = button;
        }
        button.addActionListener(e -> {
            button.setSelected(!button.isSelected());
            if (editable && !button.equals(selectedButton))
                setSelectedButton(button);
        });
        buttons.put(button.getText(), button);
        this.add(button);
        return button;
    }

    public JRadioButton removeButton(String text) {
        JRadioButton button = buttons.remove(text);
        if (button != null)
            this.remove(button);
        return button;
    }

    public boolean isButtonSelected(String text) {
        JRadioButton button = buttons.get(text);
        if (button == null)
            return false;
        return button.isSelected();
    }
}
