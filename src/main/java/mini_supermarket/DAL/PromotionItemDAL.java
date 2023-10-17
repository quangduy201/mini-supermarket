package mini_supermarket.DAL;

import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionItem;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionItemDAL extends RelationshipDAL<PromotionItem, PromotionDetailId> {
    public PromotionItemDAL() {
        super(PromotionItem.class, List.of(
            __.PROMOTION.ID,
            __.PROMOTION.START_DATE,
            __.PROMOTION.END_DATE,
            __.PROMOTION.STATUS,
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
            __.PROMOTION_ITEM.QUANTITY
        ));
    }
}
