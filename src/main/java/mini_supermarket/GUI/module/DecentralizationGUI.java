package mini_supermarket.GUI.module;

import mini_supermarket.BLL.Criteria;
import mini_supermarket.BLL.RoleBLL;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Role;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.DecentralizationTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.RoleDialog;
import mini_supermarket.GUI.dialog.SmallDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DecentralizationGUI extends ControlLayout {
    private final RoleBLL roleBLL;
    private final RoundPanel panelFunction;
    private RoleDialog dialogAdd;
    private RoleDialog dialogEdit;
    private final RoundPanel panelData;
    private final LeftRightLayout layoutFormAndData;
    private RoundPanel panelDetailData;
    private RoundPanel panelForm;
    private CustomTable dataTable;
    private Long[] idsOfCurrentData;
    private DecentralizationTable detailTable;

    public DecentralizationGUI(List<Function> functions) {
        super(functions);
        roleBLL = new RoleBLL();
        panelFunction = getTopPanel();

        dialogAdd = new RoleDialog(I18n.get("dialog", "role.add"), false);
        dialogEdit = new RoleDialog(I18n.get("dialog", "role.edit"), false);

        panelData = getBottomPanel();
        panelData.setBackground(null);
        panelData.setLayout(new BorderLayout());

        layoutFormAndData = new LeftRightLayout(350, false, 20, 5);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);

        panelDetailData = layoutFormAndData.getLeftPanel();
        panelDetailData.setLayout(new GridBagLayout());

        detailTable = new DecentralizationTable();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDetailData.add(detailTable, gbc);

        panelForm = layoutFormAndData.getRightPanel();
        panelForm.setLayout(new GridBagLayout());

        String[] attributes = I18n.get("components", "table_headers.role").split(", ");
        Pair<Long[], Object[][]> pair = RoleBLL.getDataFrom(roleBLL.findAll());
        idsOfCurrentData = pair.getFirst();
        dataTable = new CustomTable(
            pair.getSecond(),
            attributes,
            new Integer[]{1, 200},
            this::detail, false
        );
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(dataTable, gbc);

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBoxFilter.getModel();
        model.addElement(attributes[1]); // name
    }

    public List<Role> getRolesFromSelectedRows() {
        List<Role> roles = new ArrayList<>();
        for (int row : dataTable.getTable().getSelectedRows()) {
            Role role = roleBLL.findBy(__.ROLE.ID, idsOfCurrentData[row]).get(0);
            roles.add(role);
        }
        return roles;
    }

    public Role getRoleFromSelectedRow() {
        List<Role> roles = getRolesFromSelectedRows();
        if (roles.isEmpty())
            return null;
        return roles.get(0);
    }

    @Override
    public void add() {
        dialogAdd.setVisible(true);
        Role role = dialogAdd.getData();
        if (role == null)
            return;

        Pair<Boolean, String> result = roleBLL.addRole(role);
        SmallDialog.showResult(result, this::refresh, this::add);
    }

    @Override
    public void edit() {
        Role currentRole = getRoleFromSelectedRow();
        if (currentRole == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        dialogEdit.setData(currentRole);
        dialogEdit.setVisible(true);
        Role role = dialogEdit.getData();
        if (role == null)
            return;

        Pair<Boolean, String> result = roleBLL.editRole(currentRole, role);
        SmallDialog.showResult(result, this::refresh, this::edit);
    }

    @Override
    public void remove() {
        Role role = getRoleFromSelectedRow();
        if (role == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] options = new String[]{
            I18n.get("dialog", "yes"),
            I18n.get("dialog", "no")
        };
        int choice = JOptionPane.showOptionDialog(MiniSupermarket.main,
            I18n.get("messages", "role.remove.confirm"),
            I18n.get("dialog", "title.question"),
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice != 0)
            return;

        Pair<Boolean, String> result = roleBLL.removeRole(role);
        SmallDialog.showResult(result, this::refresh, null);
    }

    @Override
    public void detail() {
        Role role = getRoleFromSelectedRow();
        detailTable.refreshTable();
        detailTable.setRole(role);
    }

    @Override
    public void excel() {
        // TODO
    }

    @Override
    public void pdf() {
        // TODO
    }

    @Override
    public void find() {
        int attributeIndex = comboBoxFilter.getSelectedIndex();
        String criteria = jTextFieldSearch.getText().trim();

        List<Role> roles;
        if (criteria.isBlank())
            roles = roleBLL.findAll();
        else {
            Criteria<Role> c = new Criteria<>(roleBLL);
            roles = switch (attributeIndex) {
                case 0 -> roleBLL.findByCriteria(
                    c.like(__.ROLE.NAME, "%" + criteria + "%"));
                default -> roleBLL.findAll();
            };
        }

        Pair<Long[], Object[][]> pair = RoleBLL.getDataFrom(roles);
        idsOfCurrentData = pair.getFirst();
        Object[][] data = pair.getSecond();
        dataTable.setData(data);
    }

    @Override
    public void refresh() {
        jTextFieldSearch.setText("");
        if (comboBoxFilter.getSelectedIndex() == 0)
            find();
        else
            comboBoxFilter.setSelectedIndex(0);
        dialogAdd.refresh();
        dialogEdit.refresh();
        detailTable.refreshTable();
        System.gc();
    }
}
