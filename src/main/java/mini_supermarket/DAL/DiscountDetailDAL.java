package mini_supermarket.DAL;

import mini_supermarket.DTO.DiscountDetail;
import mini_supermarket.DTO.DiscountDetailId;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountDetailDAL extends RelationshipDAL<DiscountDetail, DiscountDetailId> {
    public DiscountDetailDAL() {
        super(DiscountDetail.class, List.of(
            __.DISCOUNT.COLUMN.ID,
            __.DISCOUNT.COLUMN.PERCENT,
            __.DISCOUNT.COLUMN.START_DATE,
            __.DISCOUNT.COLUMN.END_DATE,
            __.DISCOUNT.COLUMN.STATUS,
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
            __.DISCOUNT_DETAIL.COLUMN.STATUS
        ));
    }
}
