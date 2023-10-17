package mini_supermarket.DAL;

import mini_supermarket.DTO.DiscountDetail;
import mini_supermarket.DTO.DiscountDetailId;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountDetailDAL extends RelationshipDAL<DiscountDetail, DiscountDetailId> {
    public DiscountDetailDAL() {
        super(DiscountDetail.class, List.of(
            __.DISCOUNT.ID,
            __.DISCOUNT.PERCENT,
            __.DISCOUNT.START_DATE,
            __.DISCOUNT.END_DATE,
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
            __.DISCOUNT_DETAIL.STATUS
        ));
    }
}
