package mini_supermarket.DAL;

import mini_supermarket.DTO.Shipment;
import mini_supermarket.utils.__;

import java.util.List;

public class ShipmentDAL extends EntityDAL<Shipment> {
    public ShipmentDAL() {
        super(Shipment.class, List.of(
            __.SHIPMENT.COLUMN.ID,
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
            __.SHIPMENT.COLUMN.UNIT_PRICE,
            __.SHIPMENT.COLUMN.QUANTITY,
            __.SHIPMENT.COLUMN.REMAIN,
            __.SHIPMENT.COLUMN.MFG,
            __.SHIPMENT.COLUMN.EXP,
            __.SHIPMENT.COLUMN.SKU,
            __.IMPORT_NOTE.COLUMN.ID,
            __.STAFF.COLUMN.ID,
            __.STAFF.COLUMN.NAME,
            __.STAFF.COLUMN.GENDER,
            __.STAFF.COLUMN.BIRTHDATE,
            __.STAFF.COLUMN.PHONE,
            __.STAFF.COLUMN.ADDRESS,
            __.STAFF.COLUMN.EMAIL,
            __.STAFF.COLUMN.ENTRY_DATE,
            __.IMPORT_NOTE.COLUMN.RECEIVED_DATE,
            __.IMPORT_NOTE.COLUMN.TOTAL,
            __.SUPPLIER.COLUMN.ID,
            __.SUPPLIER.COLUMN.NAME,
            __.SUPPLIER.COLUMN.PHONE,
            __.SUPPLIER.COLUMN.ADDRESS,
            __.SUPPLIER.COLUMN.EMAIL
        ));
    }
}
