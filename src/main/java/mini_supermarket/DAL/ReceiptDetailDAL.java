package mini_supermarket.DAL;

import mini_supermarket.DTO.ReceiptDetail;
import mini_supermarket.DTO.ReceiptDetailId;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptDetailDAL extends RelationshipDAL<ReceiptDetail, ReceiptDetailId> {
    public ReceiptDetailDAL() {
        super(ReceiptDetail.class, List.of(
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
            __.RECEIPT.COLUMN.EXCESS,
            __.PRODUCT.COLUMN.ID,
            __.PRODUCT.COLUMN.NAME,
            __.BRAND.COLUMN.ID,
            __.BRAND.COLUMN.NAME,
            __.SUPPLIER.COLUMN.ID,
            __.SUPPLIER.COLUMN.NAME,
            __.SUPPLIER.COLUMN.PHONE,
            __.SUPPLIER.COLUMN.ADDRESS,
            __.SUPPLIER.COLUMN.EMAIL,
            __.CATEGORY.COLUMN.ID,
            __.CATEGORY.COLUMN.NAME,
            __.CATEGORY.COLUMN.QUANTITY,
            __.PRODUCT.COLUMN.UNIT,
            __.PRODUCT.COLUMN.COST,
            __.PRODUCT.COLUMN.QUANTITY,
            __.PRODUCT.COLUMN.IMAGE,
            __.PRODUCT.COLUMN.BARCODE,
            __.RECEIPT_DETAIL.COLUMN.QUANTITY,
            __.RECEIPT_DETAIL.COLUMN.TOTAL,
            __.RECEIPT_DETAIL.COLUMN.PERCENT
        ));
    }
}
