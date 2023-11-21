package mini_supermarket.GUI.dialog;

import mini_supermarket.BLL.StaffBLL;
import mini_supermarket.DTO.Staff;
import mini_supermarket.GUI.component.DateChooser;
import mini_supermarket.GUI.component.RadioButtons;
import mini_supermarket.utils.*;
import javax.swing.*;
import java.util.function.Function;

public class StaffDialog extends CustomDialog {
    private final JTextField txtName;
    private final RadioButtons radioGender;
    private final DateChooser txtBirthdate;
    private final JTextField txtPhone;
    private final JTextField txtAddress;
    private final JTextField txtEmail;
    private final DateChooser txtEntryDate;
    private Staff staff;

    public StaffDialog(String title, boolean readOnly, Function<Pair<Staff, Staff>, Pair<Boolean, String>> runWhenConfirm) {
        super(title, 9, false);
        txtName = new JTextField();
        radioGender = new RadioButtons("Nam", "Nữ");
        txtBirthdate = new DateChooser();
        txtPhone = new JTextField();
        txtAddress = new JTextField();
        txtEmail = new JTextField();
        txtEntryDate = new DateChooser();

        layoutForm.addTextAbove("Name:", txtName);
        layoutForm.addTextAbove("Gender:", radioGender);
        layoutForm.addTextAbove("Birthday:", txtBirthdate);
        layoutForm.addTextAbove("Phone:", txtPhone);
        layoutForm.addTextAbove("Address:", txtAddress);
        layoutForm.addTextAbove("Email:", txtEmail);
        layoutForm.addTextAbove("Entry date:", txtEntryDate);

        btnRefresh.addActionListener(e -> refresh());
        btnCancel.addActionListener(e -> {
            cancel = true;
            refresh();
            dispose();
        });
        btnConfirm.addActionListener(e -> {
            cancel = false;
            Staff staff = getData();
            if (staff == null)
                return;

            Pair<Boolean, String> result = runWhenConfirm.apply(new Pair<>(this.staff, staff));
            SmallDialog.showResult(this, result, () -> {
                refresh();
                dispose();
            }, null);
        });

        if (readOnly) {
            txtName.setEditable(false);
            radioGender.setEditable(false);
            txtBirthdate.setEditable(false);
            txtPhone.setEditable(false);
            txtAddress.setEditable(false);
            txtEmail.setEditable(false);
            txtEntryDate.setEditable(false);
            btnCancel.setText(I18n.get("dialog", "ok"));
            pnlButton.remove(btnConfirm);
            pnlButton.remove(btnRefresh);
        }
    }

    public void refresh() {
        if (staff != null) {
            setData(staff);
        } else {
            txtName.setText("");
            radioGender.setSelectedButton("Nam");
            txtBirthdate.setText(null);
            txtPhone.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
            txtEntryDate.setText(null);
        }
    }

    public Staff getData() {
        if (cancel)
            return null;
        String name = txtName.getText().trim().toUpperCase();
        Boolean gender = radioGender.isButtonSelected("Nam");
        Date birthdate = txtBirthdate.getText();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        Date entryDate = txtEntryDate.getText();
        return new Staff(null, name, gender, birthdate, phone, address, email, entryDate);
    }

    public void setData(Staff staff) {
        this.staff = staff;
        txtName.setText(staff.getName());
        radioGender.setSelectedButton(staff.getGender() ? "Nam" : "Nữ");
        txtBirthdate.setText(staff.getBirthdate());
        txtPhone.setText(staff.getPhone());
        txtAddress.setText(staff.getAddress());
        txtEmail.setText(staff.getEmail());
        txtEntryDate.setText(staff.getEntryDate());
    }
}
