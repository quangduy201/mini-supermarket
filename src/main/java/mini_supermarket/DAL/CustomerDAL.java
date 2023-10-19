package mini_supermarket.DAL;

import mini_supermarket.DTO.Customer;
import mini_supermarket.utils.__;

import java.util.List;

public class CustomerDAL extends EntityDAL<Customer> {
    public CustomerDAL() {
        super(Customer.class, List.of(
            __.CUSTOMER.COLUMN.ID,
            __.CUSTOMER.COLUMN.NAME,
            __.CUSTOMER.COLUMN.GENDER,
            __.CUSTOMER.COLUMN.BIRTHDATE,
            __.CUSTOMER.COLUMN.PHONE,
            __.CUSTOMER.COLUMN.MEMBERSHIP,
            __.CUSTOMER.COLUMN.SIGNED_UP_DATE,
            __.CUSTOMER.COLUMN.POINT
        ));
    }
}
