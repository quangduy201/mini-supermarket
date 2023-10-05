package mini_supermarket.DAL;

import mini_supermarket.DTO.Account;
import mini_supermarket.utils.__;

import java.util.List;

public class AccountDAL extends EntityDAL<Account> {
    public AccountDAL() {
        super(Account.class, List.of(
            __.ACCOUNT.ID,
            __.ACCOUNT.USERNAME,
            __.ACCOUNT.PASSWORD,
            __.ROLE.ID,
            __.ROLE.NAME,
            __.STAFF.ID,
            __.STAFF.NAME,
            __.STAFF.GENDER,
            __.STAFF.BIRTHDATE,
            __.STAFF.PHONE,
            __.STAFF.ADDRESS,
            __.STAFF.EMAIL,
            __.STAFF.ENTRY_DATE,
            __.ACCOUNT.LAST_SIGNED_IN
        ));
    }
}
