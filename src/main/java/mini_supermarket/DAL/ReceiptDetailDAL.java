package mini_supermarket.DAL;

import mini_supermarket.DTO.ReceiptDetail;
import mini_supermarket.DTO.ReceiptDetailId;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptDetailDAL extends RelationshipDAL<ReceiptDetail, ReceiptDetailId> {
    public ReceiptDetailDAL() {
        super(ReceiptDetail.class, List.of(
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
            __.RECEIPT.EXCESS,
            __.PRODUCT.ID,
            __.PRODUCT.NAME,
            __.BRAND.ID,
            __.BRAND.NAME,
            __.SUPPLIER.ID,
            __.SUPPLIER.NAME,
            __.SUPPLIER.ADDRESS,
            __.SUPPLIER.PHONE,
            __.SUPPLIER.EMAIL,
            __.CATEGORY.ID,
            __.CATEGORY.NAME,
            __.CATEGORY.QUANTITY,
            __.PRODUCT.UNIT,
            __.PRODUCT.COST,
            __.PRODUCT.QUANTITY,
            __.PRODUCT.IMAGE,
            __.PRODUCT.BARCODE,
            __.RECEIPT_DETAIL.QUANTITY,
            __.RECEIPT_DETAIL.TOTAL,
            __.RECEIPT_DETAIL.PERCENT
        ));
    }
}
