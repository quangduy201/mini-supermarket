package mini_supermarket.GUI.dialog;

import mini_supermarket.DTO.Role;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;

import javax.swing.*;
import java.util.function.Function;

public class RoleDialog extends CustomDialog {
    private JTextField txtName;
    private Role role;

    public RoleDialog(String title, boolean readOnly, Function<Pair<Role, Role>, Pair<Boolean, String>> runWhenConfirm) {
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
            Role role = getData();
            if (role == null)
                return;

            Pair<Boolean, String> result = runWhenConfirm.apply(new Pair<>(this.role, role));
            SmallDialog.showResult(this, result, () -> {
                refresh();
                dispose();
            }, null);
        });

        if (readOnly) {
            txtName.setEditable(false);
            btnCancel.setText(I18n.get("dialog", "ok"));
            pnlButton.remove(btnConfirm);
        }
    }

    public void refresh() {
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
