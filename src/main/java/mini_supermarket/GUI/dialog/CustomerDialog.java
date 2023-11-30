package mini_supermarket.GUI.dialog;

import mini_supermarket.DTO.Customer;

import mini_supermarket.GUI.component.DateChooser;
import mini_supermarket.GUI.component.RadioButtons;
import mini_supermarket.utils.*;
import javax.swing.*;
import java.util.function.Function;

public class CustomerDialog extends CustomDialog {

    private final JTextField txtName;
    private final RadioButtons radioGender;

    private final JTextField txtPhone;
    private final RadioButtons radioMembership;
    private final DateChooser txtSignUpDate;
    private final JTextField txtPoint;
    private Customer customer;

    public CustomerDialog(String title, boolean readOnly, Function<Pair<Customer, Customer>, Pair<Boolean, String>> runWhenConfirm) {
        super(title, 7, false);
        txtName = new JTextField();
        radioGender = new RadioButtons("Nam", "Nữ");
        txtPhone = new JTextField();
        radioMembership = new RadioButtons("yes", "no");
        txtSignUpDate = new DateChooser();
        txtPoint = new JTextField();

        layoutForm.addTextAbove("Name" , txtName);
        layoutForm.addTextAbove("Gender", radioGender);
        layoutForm.addTextAbove("Phone", txtPhone);
        layoutForm.addTextAbove("Membership" , radioMembership);
        layoutForm.addTextAbove("Sign up date", txtSignUpDate);
        layoutForm.addTextAbove("Point", txtPoint);

        btnRefresh.addActionListener(e -> refresh());
        btnCancel.addActionListener(e -> {
            cancel = true;
            refresh();
            dispose();
        });
        btnConfirm.addActionListener(e -> {
            cancel = false;
            Customer customer = getData();
            if(customer == null)
                return;

            Pair<Boolean, String> result = runWhenConfirm.apply(new Pair<>(this.customer, customer));
            SmallDialog.showResult(this, result, () ->{
                refresh();
                dispose();
            }, null);
        });

        if(readOnly){
            txtName.setEditable(false);
            radioGender.setEditable(false);
            txtPhone.setEditable(false);
            radioMembership.setEditable(false);
            txtSignUpDate.setEditable(false);
            txtPoint.setEditable(false);
            btnCancel.setText(I18n.get("dialog" , "ok"));
            pnlButton.remove(btnConfirm);
            pnlButton.remove(btnRefresh);
        }

    }

    public void refresh() {
        if (customer != null) {
            setData(customer);
        } else {
            txtName.setText("");
            radioGender.setSelectedButton("Nam");
            txtPhone.setText("");
            radioMembership.setSelectedButton(" ");
            txtSignUpDate.setText(null);
            txtPoint.setText("");
        }
    }

    public Customer getData() {
        if (cancel)
            return null;
        String name = txtName.getText().trim().toUpperCase();
        Boolean gender = radioGender.isButtonSelected("Nam");
        String phone = txtPhone.getText().trim();
        Boolean membership = radioMembership.isButtonSelected("yes");
        Date signupdate = txtSignUpDate.getText();
        Integer point = Integer.parseInt(txtPoint.getText());
        return new Customer(null, name, gender, phone, membership,signupdate,point);
    }



    public void setData(Customer customer){
        this.customer = customer;
        txtName.setText(customer.getName());
        radioGender.setSelectedButton(customer.getGender()? "Nam" : "Nữ" );
        txtPhone.setText(customer.getPhone());
        radioMembership.setSelectedButton(customer.getMembership() ? "yes": "no");
        txtSignUpDate.setText(customer.getSignedUpDate());
        txtPoint.setText(String.valueOf(customer.getPoint()));
    }
}
