package mini_supermarket.GUI.dialog;

import mini_supermarket.DTO.Role;
import mini_supermarket.utils.I18n;

import javax.swing.*;

public class RoleDialog extends CustomDialog {
    private JTextField txtName;
    private Role role;

    public RoleDialog(String title, boolean readOnly) {
        super(title, 1, false);
        txtName = new JTextField();

        layoutForm.addTextAbove("Role:", txtName);

        btnRefresh.addActionListener(e -> refresh());
        btnCancel.addActionListener(e -> {
            cancel = true;
            refresh();
            dispose();
        });
        btnConfirm.addActionListener(e -> {
            cancel = false;
            dispose();
        });

        if (readOnly) {
            txtName.setEditable(false);
            btnConfirm.setText(I18n.get("dialog", "ok"));
            pnlButton.remove(btnCancel);
        }
    }

    public void refresh() {
        txtSearch.setText("");
//        cbbSearch.setSelectedIndex(0);
        if (role != null) {
            setData(role);
        } else {
            txtName.setText("");
        }
    }

    public Role getData() {
        if (cancel)
            return null;
        String name = txtName.getText().trim();
        return new Role(null, name);
    }

    public void setData(Role role) {
        this.role = role;
        txtName.setText(role.getName());
    }
}
