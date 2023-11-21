package mini_supermarket.GUI.module;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.BLL.Criteria;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.AccountDialog;
import mini_supermarket.GUI.dialog.SmallDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AccountGUI extends ControlLayout {
    private final AccountBLL accountBLL;
    private final RoundPanel panelFunction;
    private final AccountDialog dialogAdd;
    private final AccountDialog dialogEdit;
    private final AccountDialog dialogDetail;
    private final RoundPanel panelData;
    private final CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public AccountGUI(List<Function> functions) {
        super(functions);
        accountBLL = new AccountBLL();
        panelFunction = getTopPanel();

        dialogAdd = new AccountDialog(I18n.get("dialog", "account.add"),
            false, account -> accountBLL.addAccount(account.getSecond()));
        dialogEdit = new AccountDialog(I18n.get("dialog", "account.edit"),
            false, account -> accountBLL.editAccount(account.getFirst(), account.getSecond()));
        dialogDetail = new AccountDialog(I18n.get("dialog", "account.detail"),
            true, null);

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        String[] attributes = I18n.get("components", "table_headers.account").split(", ");

        Pair<Long[], Object[][]> pair = AccountBLL.getDataFrom(accountBLL.findAll());
        idsOfCurrentData = pair.getFirst();
        dataTable = new CustomTable(
            pair.getSecond(),
            attributes,
            new Integer[]{1, 1, -100, 1, 1, 0, null},
            this::detail, true,
            new Pair<>(Date.class, 3)
        );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable, gbc);

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBoxFilter.getModel();
        model.addElement(attributes[1]); // username
        model.addElement(attributes[2]); // staff
        model.addElement(attributes[5]); // role
        model.addElement(attributes[6]); // email
    }

    public List<Account> getAccountsFromSelectedRows() {
        List<Account> accounts = new ArrayList<>();
        for (int row : dataTable.getTable().getSelectedRows()) {
            Account account = accountBLL.findBy(__.ACCOUNT.ID, idsOfCurrentData[row]).get(0);
            accounts.add(account);
        }
        return accounts;
    }

    public Account getAccountFromSelectedRow() {
        List<Account> accounts = getAccountsFromSelectedRows();
        if (accounts.isEmpty())
            return null;
        return accounts.get(0);
    }

    @Override
    public void add() {
        dialogAdd.setVisible(true);
        if (!dialogAdd.isCancel())
            refresh();
    }

    @Override
    public void edit() {
        Account currentAccount = getAccountFromSelectedRow();
        if (currentAccount == null) {
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        dialogEdit.setData(currentAccount);
        dialogEdit.setVisible(true);
        if (!dialogEdit.isCancel())
            refresh();
    }

    @Override
    public void remove() {
        Account account = getAccountFromSelectedRow();
        if (account == null) {
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        int choice = SmallDialog.showOptionDialogWhenDeleting(MiniSupermarket.main,
            I18n.get("messages", "account.remove.confirm"));
        if (choice != 0)
            return;

        Pair<Boolean, String> result = accountBLL.removeAccount(account);
        SmallDialog.showResult(MiniSupermarket.main, result, this::refresh, null);
    }

    @Override
    public void detail() {
        Account account = getAccountFromSelectedRow();
        if (account == null) {
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        dialogDetail.setData(account);
        dialogDetail.setVisible(true);
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

        List<Account> accounts;
        if (criteria.isBlank())
            accounts = accountBLL.findAll();
        else {
            Criteria<Account> c = new Criteria<>(accountBLL);
            accounts = switch (attributeIndex) {
                case 0 -> accountBLL.findByCriteria(
                    c.like(__.ACCOUNT.USERNAME, "%" + criteria + "%"));
                case 1 -> accountBLL.findByCriteria(
                    c.like(
                        c.join(__.ACCOUNT.STAFF).get(__.STAFF.NAME), "%" + criteria + "%"
                    ));
                case 2 -> accountBLL.findByCriteria(
                    c.like(
                        c.join(__.ACCOUNT.ROLE).get(__.ROLE.NAME), "%" + criteria + "%"
                    ));
                case 3 -> accountBLL.findByCriteria(
                    c.like(
                        c.join(__.ACCOUNT.STAFF).get(__.STAFF.EMAIL), "%" + criteria + "%"
                    ));
                default -> accountBLL.findAll();
            };
        }

        Pair<Long[], Object[][]> pair = AccountBLL.getDataFrom(accounts);
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
        System.gc();
    }
}
