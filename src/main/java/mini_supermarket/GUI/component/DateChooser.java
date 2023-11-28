package mini_supermarket.GUI.component;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import mini_supermarket.utils.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.Instant;
import java.time.ZoneId;

public class DateChooser extends JDateChooser {
    protected final JTextFieldDateEditor editor;
    protected final JButton button;

    public DateChooser() {
        this("dd/MM/yyyy");
    }

    public DateChooser(String format) {
        this.setDateFormatString(format);
        this.setMinSelectableDate(convertDateToJavaDate(Date.MIN.plusDays(6)));
        this.setMaxSelectableDate(convertDateToJavaDate(Date.MAX));
        editor = (JTextFieldDateEditor) this.dateEditor;
        editor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.setForeground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Date.parse(editor.getText());
                    editor.setForeground(null);
                } catch (Exception exception) {
                    editor.setForeground(Color.RED);
                }
            }
        });
        button = this.calendarButton;
        button.addActionListener(e -> editor.requestFocusInWindow());
    }

    public JTextFieldDateEditor getEditor() {
        return editor;
    }

    public Date getText() {
        try {
            return Date.parse(this.editor.getText().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public void setText(Date date) {
        if (date == null)
            this.editor.setText("");
        else
            this.editor.setText(date.toString());
        this.editor.setForeground(null);
    }

    public void setEditable(boolean editable) {
        editor.setEditable(editable);
        if (editable)
            this.add(button);
        else
            this.remove(button);
    }

    public java.util.Date convertDateToJavaDate(Date date) {
        Instant instant = date.date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return java.util.Date.from(instant);
    }
}
