package mini_supermarket.DAL;

import mini_supermarket.DTO.Account;
import mini_supermarket.utils.__;

import java.util.List;

public class AccountDAL extends SafeEntityDAL<Account> {
    public AccountDAL() {
        super(Account.class, List.of(
            __.ACCOUNT.COLUMN.ID,
            __.ACCOUNT.COLUMN.USERNAME,
            __.ACCOUNT.COLUMN.PASSWORD,
            __.ROLE.COLUMN.ID,
            __.ROLE.COLUMN.NAME,
            __.STAFF.COLUMN.ID,
            __.STAFF.COLUMN.NAME,
            __.STAFF.COLUMN.GENDER,
            __.STAFF.COLUMN.BIRTHDATE,
            __.STAFF.COLUMN.PHONE,
            __.STAFF.COLUMN.ADDRESS,
            __.STAFF.COLUMN.EMAIL,
            __.STAFF.COLUMN.ENTRY_DATE,
            __.ACCOUNT.COLUMN.LAST_SIGNED_IN
        ));
    }
}
