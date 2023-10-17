package mini_supermarket.DAL;

import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionGift;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionGiftDAL extends RelationshipDAL<PromotionGift, PromotionDetailId> {
    public PromotionGiftDAL() {
        super(PromotionGift.class, List.of(
            __.PROMOTION.COLUMN.ID,
            __.PROMOTION.COLUMN.START_DATE,
            __.PROMOTION.COLUMN.END_DATE,
            __.PROMOTION.COLUMN.STATUS,
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
            __.PROMOTION_GIFT.COLUMN.QUANTITY
        ));
    }
}
