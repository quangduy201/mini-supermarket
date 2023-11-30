package mini_supermarket.GUI.dialog;

import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.Pair;

import javax.swing.*;
import java.util.function.Function;

public class SupplierDialog extends CustomDialog{
    private final JTextField txtName;
    private final JTextField txtPhone;
    private final JTextField txtAddress;

    private final JTextField txtEmail;

    private Supplier supplier;


    public SupplierDialog(String title, boolean readOnly, Function<Pair<Supplier, Supplier>, Pair<Boolean, String>> runWhenConfirm ) {
        super(title, 4, false);
        txtName = new JTextField();
        txtPhone = new JTextField();
        txtAddress = new JTextField();
        txtEmail = new JTextField();


        layoutForm.addTextAbove("Name", txtName);
        layoutForm.addTextAbove("Phone", txtPhone);
        layoutForm.addTextAbove("Address", txtAddress);
        layoutForm.addTextAbove("Email", txtEmail);

        btnRefresh.addActionListener(e -> refresh());
        btnCancel.addActionListener(e -> {
            cancel = true;
            refresh();;
            dispose();;
        });
        btnConfirm.addActionListener(e -> {
            cancel = false;
            Supplier supplier = getData();
            if (supplier == null)
                return;

            Pair<Boolean, String> result = runWhenConfirm.apply(new Pair<>(this.supplier, supplier));
            SmallDialog.showResult(this, result, () ->{
                refresh();;
                dispose();;
            }, null);

        });


        if(readOnly){
            txtName.setEditable(false);
            txtAddress.setEditable(false);
            txtEmail.setEditable(false);
            txtPhone.setEditable(false);
            pnlButton.remove(btnConfirm);
            pnlButton.remove(btnRefresh);
        }

    }
    public void refresh() {
        if (supplier != null) {
            setData(supplier);
        } else {
            txtName.setText("");
            txtPhone.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
        }
    }
    public Supplier getData() {
        if (cancel)
            return null;
        String name = txtName.getText().trim().toUpperCase();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        return new Supplier(null, name, phone, address,email);
    }
    public void setData(Supplier supplier){
        this.supplier = supplier;
        txtName.setText(supplier.getName());
        txtPhone.setText(supplier.getPhone());
        txtAddress.setText(supplier.getAddress());
        txtEmail.setText(supplier.getEmail());
    }
}
