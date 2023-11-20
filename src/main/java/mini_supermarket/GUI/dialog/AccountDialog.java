package mini_supermarket.GUI.dialog;

import mini_supermarket.BLL.RoleBLL;
import mini_supermarket.BLL.StaffBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Role;
import mini_supermarket.DTO.Staff;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AccountDialog extends CustomDialog {
    private RoleBLL roleBLL;
    private StaffBLL staffBLL;
    private Long[] idsOfRoles;
    private Long[] idsOfStaffs;
    private Role role;
    private Staff staff;
    private JTextField txtUsername;
    private JTextField txtRole;
    private JTextField txtStaff;
    private CustomTable tblRole;
    private CustomTable tblStaff;
    private boolean readOnly;
    private Account account;

    public AccountDialog(String title, boolean readOnly) {
        super(title, 4, true);
        this.readOnly = readOnly;
        roleBLL = new RoleBLL();
        staffBLL = new StaffBLL();
        txtUsername = new JTextField();
        txtRole = new JTextField();
        txtStaff = new JTextField();

        layoutForm.addTextAbove("Username:", txtUsername);
        layoutForm.addTextAbove("Role:", txtRole);
        layoutForm.addTextAbove("Staff:", txtStaff);

        tblRole = getRoleTable(roleBLL.findAll());
        tblStaff = getStaffTable(staffBLL.findAll());
        setUpForeignTextField(txtRole, tblRole);
        setUpForeignTextField(txtStaff, tblStaff);
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
            txtUsername.setEditable(false);
            txtRole.setEditable(false);
            txtStaff.setEditable(false);
            btnConfirm.setText(I18n.get("dialog", "ok"));
            pnlButton.remove(btnCancel);
        }
    }

    public CustomTable getRoleTable(List<Role> roles) {
        Pair<Long[], Object[][]> pair = RoleBLL.getDataFrom(roles);
        idsOfRoles = pair.getFirst();
        String[] attributes = I18n.get("components", "table_headers.role").split(", ");
        Runnable action = this::fillRole;
        if (readOnly)
            action = null;
        return new CustomTable(pair.getSecond(),
            attributes,
            new Integer[]{1, 50},
            action, false, readOnly
        );
    }

    public void fillRole() {
        int row = tblRole.getTable().getSelectedRow();
        role = roleBLL.findBy(__.ROLE.ID, idsOfRoles[row]).get(0);
        txtRole.setText(role.getName());
    }

    public CustomTable getStaffTable(List<Staff> staffs) {
        Object[][] ids = staffBLL.getData(staffs, false, List.of(
            new Pair<>(__.STAFF.COLUMN.ID, Long::parseLong)
        ));
        idsOfStaffs = Arrays.stream(ids)
            .map(row -> (Long) row[0])
            .toArray(Long[]::new);
        Object[][] data = staffBLL.getData(staffs, true, List.of(
            new Pair<>(__.STAFF.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
            new Pair<>(__.STAFF.COLUMN.BIRTHDATE, Date::parse),
            new Pair<>(__.STAFF.COLUMN.EMAIL, String::toString)
        ));
        Runnable action = this::fillStaff;
        if (readOnly)
            action = null;
        return new CustomTable(data,
            new Object[]{"STT", "Tên nhân viên", "Giới tính", "Ngày sinh", "Email"},
            new Integer[]{1, 100, 1, 1, 0},
            action, false, readOnly
        );
    }

    public void fillStaff() {
        int row = tblStaff.getTable().getSelectedRow();
        staff = staffBLL.findBy(__.STAFF.ID, idsOfStaffs[row]).get(0);
        txtStaff.setText(staff.getName());
    }

    public void refresh() {
        txtSearch.setText("");
//        cbbSearch.setSelectedIndex(0);
        pnlTable.removeAll();
        pnlTable.revalidate();
        pnlTable.repaint();
        tblRole.getTable().getSelectionModel().clearSelection();
        tblStaff.getTable().getSelectionModel().clearSelection();
        if (account != null) {
            setData(account);
        } else {
            txtUsername.setText("");
            txtRole.setText("");
            txtStaff.setText("");
        }
    }

    public Account getData() {
        if (cancel)
            return null;
        String username = txtUsername.getText().trim();
        return new Account(null, username, null, role, staff, null);
    }

    public void setData(Account account) {
        this.account = account;
        txtUsername.setText(account.getUsername());

        role = account.getRole();
        txtRole.setText(account.getRole().getName());
        int roleIndex = IntStream.range(0, idsOfRoles.length)
            .filter(i -> role.getId().equals(idsOfRoles[i]))
            .findFirst()
            .orElse(-1);
        tblRole.getTable().setRowSelectionInterval(roleIndex, roleIndex);

        staff = account.getStaff();
        txtStaff.setText(account.getStaff().getName());
        int staffIndex = IntStream.range(0, idsOfStaffs.length)
            .filter(i -> staff.getId().equals(idsOfStaffs[i]))
            .findFirst()
            .orElse(-1);
        tblStaff.getTable().setRowSelectionInterval(staffIndex, staffIndex);
    }
}
