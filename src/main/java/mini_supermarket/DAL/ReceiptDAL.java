package mini_supermarket.DAL;

import mini_supermarket.DTO.Receipt;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptDAL extends EntityDAL<Receipt> {
    public ReceiptDAL() {
        super(Receipt.class, List.of(
            __.RECEIPT.COLUMN.ID,
            __.STAFF.COLUMN.ID,
            __.STAFF.COLUMN.NAME,
            __.STAFF.COLUMN.GENDER,
            __.STAFF.COLUMN.BIRTHDATE,
            __.STAFF.COLUMN.PHONE,
            __.STAFF.COLUMN.ADDRESS,
            __.STAFF.COLUMN.EMAIL,
            __.STAFF.COLUMN.ENTRY_DATE,
            __.CUSTOMER.COLUMN.ID,
            __.CUSTOMER.COLUMN.NAME,
            __.CUSTOMER.COLUMN.GENDER,
            __.CUSTOMER.COLUMN.BIRTHDATE,
            __.CUSTOMER.COLUMN.PHONE,
            __.CUSTOMER.COLUMN.MEMBERSHIP,
            __.CUSTOMER.COLUMN.SIGNED_UP_DATE,
            __.CUSTOMER.COLUMN.POINT,
            __.RECEIPT.COLUMN.INVOICE_DATE,
            __.RECEIPT.COLUMN.TOTAL,
            __.RECEIPT.COLUMN.RECEIVED,
            __.RECEIPT.COLUMN.EXCESS
        ));
    }
}
