package mini_supermarket.DAL;

import mini_supermarket.DTO.Customer;
import mini_supermarket.utils.__;

import java.util.List;

public class CustomerDAL extends EntityDAL<Customer> {
    public CustomerDAL() {
        super(Customer.class, List.of(
            __.CUSTOMER.ID,
            __.CUSTOMER.NAME,
            __.CUSTOMER.GENDER,
            __.CUSTOMER.BIRTHDATE,
            __.CUSTOMER.PHONE,
            __.CUSTOMER.MEMBERSHIP,
            __.CUSTOMER.SIGNED_UP_DATE,
            __.CUSTOMER.POINT
        ));
    }
}
