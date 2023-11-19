package mini_supermarket.GUI.dialog;


import mini_supermarket.BLL.StaffBLL;
import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.*;
import javax.swing.*;





public class StaffDialog extends CustomDialog {
    private final StaffBLL staffBLL;

    private final JTextField txtUsername;
    private final JTextField txtBirthdate;
    private final JTextField txtPhone;
    private final JTextField txtAddress;
    private final JTextField txtEmail;
    private final JTextField txtEntryDate;
    private final JCheckBox chkGenderMale;
    private final JCheckBox chkGenderFemale;


    private Staff staff;

    public StaffDialog(String title, boolean readOnly) {
        super(title, 7, false);
        staffBLL = new StaffBLL();

        txtUsername = new JTextField();
        txtBirthdate = new JTextField();
        txtPhone = new JTextField();
        txtAddress = new JTextField();
        txtEmail = new JTextField();
        txtEntryDate = new JTextField();
        chkGenderMale = new JCheckBox("Male");
        chkGenderFemale = new JCheckBox("Famale");

        layoutForm.addTextAbove("Username:", txtUsername);
        layoutForm.addTextAbove("Birthday:", txtBirthdate);
        layoutForm.addTextAbove("Phone:", txtPhone);
        layoutForm.addTextAbove("Address:", txtAddress);
        layoutForm.addTextAbove("Email:", txtEmail);
        layoutForm.addTextAbove("Entrydate:", txtEntryDate);
        layoutForm.addTextAbove("Gender:", chkGenderMale);
        layoutForm.addTextAbove("", chkGenderFemale);


        chkGenderMale.addItemListener(e -> {
            if (chkGenderMale.isSelected()) {
                chkGenderFemale.setSelected(false);
            }
        });

        chkGenderFemale.addItemListener(e -> {
            if (chkGenderFemale.isSelected()) {
                chkGenderMale.setSelected(false);
            }
        });

        btnConfirm.addActionListener(e -> saveStaff());
    }


    public void saveStaff(){
        /*String username = txtUsername.getText();
        String birthdateString = txtBirthdate.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String entrydateString = txtEntryDate.getText();


        // Chuyển đổi chuỗi ngày sinh thành đối tượng Date
        mini_supermarket.utils.Date birthdate;
        birthdate = Date.parse(birthdateString, "dd/MM/yyyy");


        mini_supermarket.utils.Date entrydate;
        entrydate = Date.parse(entrydateString, "dd/MM/yyyy");
        staff.setName(username);
        staff.setGender(gender);
        staff.setBirthdate(birthdate);
        staff.setPhone(phone);
        staff.setAddress(address);
        staff.setEmail(email);
        staff.setEntryDate(entrydate);
         Boolean gender = null;
        if (chkGenderMale.isSelected()) {
            gender = true; // Male
        } else if (chkGenderFemale.isSelected()) {
            gender = false; // Female
        }

        if (staff == null) {
            staff = new Staff();
        }

        */


        staffBLL.addStaff(staff);


        cancel = false;
        dispose();


    }

}
