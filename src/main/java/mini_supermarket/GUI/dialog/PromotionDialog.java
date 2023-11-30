package mini_supermarket.GUI.dialog;

import mini_supermarket.GUI.component.DateChooser;
import mini_supermarket.GUI.component.RadioButtons;

import javax.swing.*;

public class PromotionDialog extends CustomDialog{
    private final JTextField txtName;

    private final DateChooser txtStartDate;

    private final DateChooser txtEnddate;

    private final RadioButtons radioStatus;


    public PromotionDialog(String title, int numberAttribute, boolean hasTable) {
        super(title, 4,false);
        txtName = new JTextField();
        txtStartDate = new DateChooser();
        txtEnddate = new DateChooser();
        radioStatus = new RadioButtons("ON" ," ");


    }
}
