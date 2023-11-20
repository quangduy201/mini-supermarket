package mini_supermarket.BLL;

import mini_supermarket.DAL.AccountDAL;
import mini_supermarket.DTO.Account;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;

import java.util.Arrays;
import java.util.List;

public class AccountBLL extends EntityBLL<Account> {
    public AccountBLL() {
        super(new AccountDAL());
    }

    public Pair<Boolean, String> addAccount(Account account) {
        Pair<Boolean, String> result;
        result = validate(__.ACCOUNT.USERNAME, account.getUsername());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = exists(null, account);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());

        String password = Password.generateRandomPassword(8);
        String hashedPassword = Password.hashPassword(password);
        account.setPassword("first" + hashedPassword);
        account.setLastSignedIn(DateTime.MIN);
        if (!add(account))
            return new Pair<>(false, I18n.get("messages", "account.add.failed"));

        new Thread(() -> OTP.sendPassword(account.getStaff().getEmail(), password)).start();
        return new Pair<>(true, I18n.get("messages", "account.add.success"));
    }

    public Pair<Boolean, String> editAccount(Account currentAccount, Account account) {
        Pair<Boolean, String> result;
        result = validate(__.ACCOUNT.USERNAME, account.getUsername());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        account.setId(currentAccount.getId());
        result = exists(currentAccount, account);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());

        account.setPassword(currentAccount.getPassword());
        account.setLastSignedIn(currentAccount.getLastSignedIn());
        if (!update(account))
            return new Pair<>(false, I18n.get("messages", "account.edit.failed"));

        if (MiniSupermarket.main.getAccount().getId().equals(account.getId()))
            MiniSupermarket.main.setAccount(account);
        return new Pair<>(true, I18n.get("messages", "account.edit.success"));
    }

    public Pair<Boolean, String> removeAccount(Account account) {
        if (account.getId() == 1 || !delete(account))
            return new Pair<>(false, I18n.get("messages", "account.remove.failed"));
        return new Pair<>(true, I18n.get("messages", "account.remove.success"));
    }

    @Override
    public Pair<Boolean, String> exists(Account oldAccount, Account newAccount) {
        boolean hasChanges = false;
        List<Account> accounts;
        if (oldAccount == null || newAccount.getId() == null || !newAccount.getId().equals(oldAccount.getId())) {
            hasChanges = true;
            accounts = findBy(__.ACCOUNT.ID, newAccount.getId());
            if (!accounts.isEmpty())
                return new Pair<>(true, I18n.get("messages", "account.exists"));
        }

        if (oldAccount == null || !newAccount.getUsername().equals(oldAccount.getUsername())) {
            hasChanges = true;
            accounts = findBy(__.ACCOUNT.USERNAME, newAccount.getUsername());
            Account foundAccount = null;
            for (Account acc : accounts) {
                if (acc.getUsername().equals(newAccount.getUsername())) {
                    foundAccount = acc;
                    break;
                }
            }
            if (foundAccount != null)
                return new Pair<>(true, I18n.get("messages", "account.exists.username", newAccount.getUsername()));
        }

        if (oldAccount == null || !newAccount.getStaff().equals(oldAccount.getStaff())) {
            accounts = findBy(__.ACCOUNT.STAFF, newAccount.getStaff());
            if (!accounts.isEmpty())
                return new Pair<>(true, I18n.get("messages", "account.exists.staff", newAccount.getStaff().getName()));
        }

        if (!hasChanges)
            return new Pair<>(true, I18n.get("messages", "account.edit.unchanged"));
        return new Pair<>(false, I18n.get("messages", "account.exists.not"));
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.ACCOUNT.USERNAME))
            return validateUsername((String) value);
        if (attribute.equals(__.ACCOUNT.PASSWORD))
            return validatePassword((String) value);
        return new Pair<>(false, I18n.get("messages", "account.attribute.not_found", attribute));
    }

    public static Pair<Boolean, String> validateUsername(String username) {
        if (username.isBlank())
            return new Pair<>(false, I18n.get("messages", "account.validate.username.no_empty"));
        if (VNString.containsUnicode(username))
            return new Pair<>(false, I18n.get("messages", "account.validate.username.no_unicode"));
        if (VNString.containsSpecial(username))
            return new Pair<>(false, I18n.get("messages", "account.validate.username.no_special"));
        return new Pair<>(true, I18n.get("messages", "account.validate.username"));
    }

    public static Pair<Boolean, String> validatePassword(String password) {
        if (password.isBlank())
            return new Pair<>(false, I18n.get("messages", "account.validate.password.no_empty"));
        if (VNString.containsUnicode(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.no_unicode"));
        if (!VNString.containsUpperCase(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.upper"));
        if (!VNString.containsLowerCase(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.lower"));
        if (!VNString.containsNumber(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.number"));
        if (!VNString.containsSpecial(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.special"));
        return new Pair<>(true, I18n.get("messages", "account.validate.password"));
    }

    public static Pair<Account, String> validateLogin(String username, String password) {
        Pair<Boolean, String> result;
        result = AccountBLL.validate(__.ACCOUNT.USERNAME, username);
        if (!result.getFirst())
            return new Pair<>(null, I18n.get("frame", "login.failed.username"));

        result = AccountBLL.validate(__.ACCOUNT.PASSWORD, password);
        if (!result.getFirst())
            return new Pair<>(null, I18n.get("frame", "login.failed.password"));

        AccountBLL accountBLL = new AccountBLL();
        List<Account> accountList = accountBLL.findBy(__.ACCOUNT.USERNAME, username);
        Account account = null;
        for (Account acc : accountList) {
            if (acc.getUsername().equals(username)) {
                account = acc;
                break;
            }
        }
        if (account == null)
            return new Pair<>(null, I18n.get("frame", "login.failed.username"));

        String hashedPassword = account.getPassword();
        if (hashedPassword.startsWith("first"))
            hashedPassword = hashedPassword.substring("first".length());
        if (!Password.verifyPassword(password, hashedPassword))
            return new Pair<>(null, I18n.get("frame", "login.failed.password"));

        return new Pair<>(account, I18n.get("frame", "login.success"));
    }

    public static Pair<Long[], Object[][]> getDataFrom(List<Account> accounts) {
        AccountBLL accountBLL = new AccountBLL();
        Object[][] ids = accountBLL.getData(accounts, false, List.of(
            new Pair<>(__.ACCOUNT.COLUMN.ID, Long::parseLong)
        ));
        Long[] idsOfData = Arrays.stream(ids)
            .map(row -> (Long) row[0])
            .toArray(Long[]::new);
        Object[][] data = accountBLL.getData(accounts, true, List.of(
            new Pair<>(__.ACCOUNT.COLUMN.USERNAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
            new Pair<>(__.STAFF.COLUMN.BIRTHDATE, Date::parse),
            new Pair<>(__.ROLE.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.EMAIL, String::toString)
        ));
        return new Pair<>(idsOfData, data);
    }
}
