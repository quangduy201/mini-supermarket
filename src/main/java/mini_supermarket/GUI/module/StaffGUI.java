package mini_supermarket.GUI.module;

import mini_supermarket.BLL.Criteria;
import mini_supermarket.BLL.StaffBLL;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Staff;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.ExcelDialog;
import mini_supermarket.GUI.dialog.SmallDialog;
import mini_supermarket.GUI.dialog.StaffDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaffGUI extends ControlLayout {
    private final StaffBLL staffBLL;
    private final RoundPanel panelFunction;
    private final StaffDialog dialogAdd;
    private final StaffDialog dialogEdit;
    private final StaffDialog dialogDetail;
    private final ExcelDialog dialogExcel;
    private final RoundPanel panelData;
    private final CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public StaffGUI(List<Function> functions) {
        super(functions);
        staffBLL = new StaffBLL();
        panelFunction = getTopPanel();

        dialogAdd = new StaffDialog(I18n.get("dialog", "staff.add"),
            false, staff -> staffBLL.addStaff(staff.getSecond()));
        dialogEdit = new StaffDialog(I18n.get("dialog", "staff.edit"),
            false, staff -> staffBLL.editStaff(staff.getFirst(), staff.getSecond()));
        dialogDetail = new StaffDialog(I18n.get("dialog", "staff.detail"),
            true, null);
        String[] columns = I18n.get("components", "excel_headers.staff").split(", ");
        dialogExcel = new ExcelDialog(List.of(
            new Pair<>(columns[0], Excel.Type.STRING), // name
            new Pair<>(columns[1], Excel.Type.STRING), // gender
            new Pair<>(columns[2], Excel.Type.STRING), // birthdate
            new Pair<>(columns[3], Excel.Type.STRING), // phone
            new Pair<>(columns[4], Excel.Type.STRING), // address
            new Pair<>(columns[5], Excel.Type.STRING), // email
            new Pair<>(columns[6], Excel.Type.STRING) // entry date
        ), row -> {
            String name = row.get(0).trim().toUpperCase();
            String stringGender = VNString.removeAccent(row.get(1).trim().toLowerCase());
            Boolean gender = stringGender.equals("nam") || stringGender.equals("male");
            Date birthdate = Date.parse(row.get(2).trim());
            String phone = row.get(3).trim();
            String address = row.get(4).trim();
            String email = row.get(5).trim();
            Date entryDate = Date.parse(row.get(6).trim());
            Staff staff = new Staff(null, name, gender, birthdate, phone, address, email, entryDate);
            return staffBLL.addStaff(staff);
        });

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        String[] attributes = I18n.get("components", "table_headers.staff").split(", ");

        Pair<Long[], Object[][]> pair = StaffBLL.getDataFrom(staffBLL.findAll());
        idsOfCurrentData = pair.getFirst();
        dataTable = new CustomTable(
            pair.getSecond(),
            attributes,
            new Integer[]{1, 100, 1, 2, 0, 0, 2},
            this::detail, true,
            new Pair<>(Date.class, 2),
            new Pair<>(Date.class, 5)
        );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10 , 10 ,10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable, gbc);

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBoxFilter.getModel();
        model.addElement(attributes[1]); // name
        model.addElement(attributes[4]); // phone
        model.addElement(attributes[5]); // email
    }

    public List<Staff> getStaffsFromSelectedRows() {
        List<Staff> staff = new ArrayList<>();
        for (int row : dataTable.getTable().getSelectedRows()) {
            Staff staffItem = staffBLL.findBy(__.STAFF.ID, idsOfCurrentData[row]).get(0);
            staff.add(staffItem);
        }
        return staff;
    }

    public Staff getStaffFromSelectedRow() {
        List<Staff> staffs = getStaffsFromSelectedRows();
        if (staffs.isEmpty())
            return null;
        return staffs.get(0);
    }

    @Override
    public void add() {
        dialogAdd.setVisible(true);
        if (!dialogAdd.isCancel())
            refresh();
    }

    @Override
    public void edit() {
        Staff currentStaff = getStaffFromSelectedRow();
        if (currentStaff == null) {
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        dialogEdit.setData(currentStaff);
        dialogEdit.setVisible(true);
        if (!dialogEdit.isCancel())
            refresh();
    }

    @Override
    public void remove() {
        Staff staff = getStaffFromSelectedRow();
        if (staff == null) {
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        int choice = SmallDialog.showOptionDialogWhenDeleting(MiniSupermarket.main,
            I18n.get("messages", "staff.remove.confirm"));
        if (choice != 0)
            return;

        Pair<Boolean, String> result = staffBLL.removeStaff(staff);
        SmallDialog.showResult(MiniSupermarket.main, result, this::refresh, null);
    }

    @Override
    public void detail() {
        Staff staff = getStaffFromSelectedRow();
        if (staff == null) {
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        dialogDetail.setData(staff);
        dialogDetail.setVisible(true);
    }

    @Override
    public void excel() {
        dialogExcel.setVisible(true);
        if (!dialogExcel.isCancel()) {
            Pair<Boolean, String> result = new Pair<>(true, I18n.get("messages", "excel.import.success"));
            SmallDialog.showResult(MiniSupermarket.main, result, this::refresh, null);
            refresh();
        }
    }

    @Override
    public void pdf() {
        File file = Excel.saveFile();
        if (file == null)
            return;
        Pair<Boolean, String> result = Excel.exportExcel(file, dataTable.getTable().getModel());
        SmallDialog.showResult(MiniSupermarket.main, result, null, null);
    }

    @Override
    public void find() {
        int attributeIndex = comboBoxFilter.getSelectedIndex();
        String criteria = jTextFieldSearch.getText().trim();

        List<Staff> staffs;
        if (criteria.isBlank())
            staffs = staffBLL.findAll();
        else {
            Criteria<Staff> c = new Criteria<>(staffBLL);
            staffs = switch (attributeIndex) {
                case 0 -> staffBLL.findByCriteria(
                    c.like(__.STAFF.NAME, "%" + criteria + "%"));
                case 1 -> staffBLL.findByCriteria(
                    c.like(__.STAFF.PHONE, "%" + criteria + "%"));
                case 2 -> staffBLL.findByCriteria(
                    c.like(__.STAFF.EMAIL, "%" + criteria + "%"));
                default -> staffBLL.findAll();
            };
        }

        Pair<Long[], Object[][]> pair = StaffBLL.getDataFrom(staffs);
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
        dialogDetail.refresh();
        dialogExcel.refresh();
        System.gc();
    }
}
