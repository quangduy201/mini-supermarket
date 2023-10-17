package mini_supermarket.DAL;

import mini_supermarket.DTO.Receipt;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptDAL extends EntityDAL<Receipt> {
    public ReceiptDAL() {
        super(Receipt.class, List.of(
            __.RECEIPT.ID,
            __.STAFF.ID,
            __.STAFF.NAME,
            __.STAFF.GENDER,
            __.STAFF.BIRTHDATE,
            __.STAFF.PHONE,
            __.STAFF.ADDRESS,
            __.STAFF.EMAIL,
            __.STAFF.ENTRY_DATE,
            __.CUSTOMER.ID,
            __.CUSTOMER.NAME,
            __.CUSTOMER.GENDER,
            __.CUSTOMER.BIRTHDATE,
            __.CUSTOMER.PHONE,
            __.CUSTOMER.MEMBERSHIP,
            __.CUSTOMER.SIGNED_UP_DATE,
            __.CUSTOMER.POINT,
            __.RECEIPT.INVOICE_DATE,
            __.RECEIPT.TOTAL,
            __.RECEIPT.RECEIVED,
            __.RECEIPT.EXCESS
        ));
    }
}
