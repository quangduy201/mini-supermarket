package mini_supermarket.GUI.module;

import mini_supermarket.BLL.Criteria;
import mini_supermarket.BLL.StaffBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Staff;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.AccountDialog;
import mini_supermarket.GUI.dialog.ExcelDialog;
import mini_supermarket.GUI.dialog.SmallDialog;
import mini_supermarket.GUI.dialog.StaffDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
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
    private StaffDialog dialogAdd;
    private StaffDialog dialogEdit;
    private StaffDialog dialogDetail;
    private ExcelDialog dialogExcel;
    private final RoundPanel panelData;
    private CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public StaffGUI(List<Function> functions) {
        super(functions);
        staffBLL = new StaffBLL();
        panelFunction = getTopPanel();

        dialogAdd = new StaffDialog(I18n.get("dialog", "staff.add"), false);
        dialogEdit = new StaffDialog(I18n.get("dialog", "staff.edit"), false);
        dialogDetail = new StaffDialog(I18n.get("dialog", "staff.detail"), true);

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        String[] columns = I18n.get("components", "excel_headers.staff").split(", ");
        dialogExcel = new ExcelDialog(List.of(
            new Pair<>(columns[0], Excel.Type.STRING), // name
            new Pair<>(columns[1], Excel.Type.STRING), // gender
            new Pair<>(columns[2], Excel.Type.STRING), // birthdate
            new Pair<>(columns[3], Excel.Type.STRING), // phone
            new Pair<>(columns[4], Excel.Type.STRING), // address
            new Pair<>(columns[5], Excel.Type.STRING), // email
            new Pair<>(columns[6], Excel.Type.STRING) // entry date
        ));

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

    }

    @Override
    public void edit() {
        // TODO
    }

    @Override
    public void remove() {
        // TODO
    }

    @Override
    public void detail() {
        // TODO
    }

    @Override
    public void excel() {
        dialogExcel.setVisible(true);
        List<List<String>> data = dialogExcel.getData();
        if (data == null)
            return;

        boolean hasError = false;
        for (int i = 0; i < data.size(); i++) {
            String messageArgument = data.get(i).toString();
            try {
                List<String> row = data.get(i);
                String name = row.get(0);
                String stringGender = VNString.removeAccent(row.get(1).toLowerCase());
                Boolean gender = stringGender.equals("nam") || stringGender.equals("male");
                Date birthdate = Date.parse(row.get(2));
                String phone = row.get(3);
                String address = row.get(4);
                String email = row.get(5);
                Date entryDate = Date.parse(row.get(6));
                Staff staff = new Staff(null, name, gender, birthdate, phone, address, email, entryDate);
                Log.info(staff);
//                Pair<Boolean, String> result = staffBLL.addStaff(staff);
                Pair<Boolean, String> result = new Pair<>(true, "Hello");
                if (!result.getFirst()) {
                    messageArgument = result.getSecond();
                    throw new RuntimeException();
                }
                data.remove(i);
                dialogExcel.getModel().removeRow(i);
                i--;
            } catch (Exception e) {
                hasError = true;
                String message = I18n.get("messages", "excel.import.error.row", i + 1, messageArgument);
                int choice = SmallDialog.showErrorWhileImporting(message);
                if (choice == 0) continue;
                if (choice == 1) {
                    excel();
                    break;
                }
            }
        }
        if (hasError) {
            excel();
            return;
        }
        SmallDialog.showResult(new Pair<>(true, I18n.get("messages", "excel.import.success")), this::refresh, null);
    }

    @Override
    public void pdf() {
        File file = Excel.saveFile();
        if (file == null)
            return;
        Pair<Boolean, String> result = Excel.exportExcel(file, I18n.get("components", "excel_title.staff"), dataTable.getTable().getModel());
        SmallDialog.showResult(result, null, null);
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
//        dialogAdd.refresh();
//        dialogEdit.refresh();
//        dialogDetail.refresh();
        System.gc();
    }
}
