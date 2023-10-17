package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionItemDAL;
import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionItem;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionItemBLL extends RelationshipBLL<PromotionItem, PromotionDetailId> {
    public PromotionItemBLL() {
        super(new PromotionItemDAL());
    }

    @Override
    public Pair<Boolean, String> exists(PromotionItem promotionItem) {
        List<PromotionItem> promotionItems;
        promotionItems = findBy(
            __.PROMOTION_ITEM.PROMOTION, promotionItem.getId().getPromotion(),
            __.PROMOTION_ITEM.PRODUCT, promotionItem.getId().getProduct());
        if (!promotionItems.isEmpty()) {
            String message = I18n.getString("promotion_item.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("promotion_item.exists.not");
        return new Pair<>(false, message);
    }
}
