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
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountGUI extends ControlLayout {
    private final AccountBLL accountBLL;
    private final RoundPanel panelFunction;
    private AccountDialog dialogAdd;
    private AccountDialog dialogEdit;
    private AccountDialog dialogDetail;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;
    private CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public AccountGUI(List<Function> functions) {
        super(functions);
        accountBLL = new AccountBLL();
        panelFunction = getTopPanel();

        dialogAdd = new AccountDialog(I18n.get("dialog", "account.add"), false);
        dialogEdit = new AccountDialog(I18n.get("dialog", "account.edit"), false);
        dialogDetail = new AccountDialog(I18n.get("dialog", "account.detail"), true);

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
        Account account = dialogAdd.getData();
        if (account == null)
            return;

        Pair<Boolean, String> result = accountBLL.addAccount(account);
        SmallDialog.showResult(result, this::refresh, this::add);
    }

    @Override
    public void edit() {
        Account currentAccount = getAccountFromSelectedRow();
        if (currentAccount == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        dialogEdit.setData(currentAccount);
        dialogEdit.setVisible(true);
        Account account = dialogEdit.getData();
        if (account == null)
            return;

        Pair<Boolean, String> result = accountBLL.editAccount(currentAccount, account);
        SmallDialog.showResult(result, this::refresh, this::edit);
    }

    @Override
    public void remove() {
        Account account = getAccountFromSelectedRow();
        if (account == null) {
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
            I18n.get("messages", "account.remove.confirm"),
            I18n.get("dialog", "title.question"),
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice != 0)
            return;

        Pair<Boolean, String> result = accountBLL.removeAccount(account);
        SmallDialog.showResult(result, this::refresh, null);
    }

    @Override
    public void detail() {
        Account account = getAccountFromSelectedRow();
        if (account == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Log.info(dataTable.getDataFromSelectedRow(), account);
        dialogDetail.setData(account);
        dialogDetail.setVisible(true);
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
