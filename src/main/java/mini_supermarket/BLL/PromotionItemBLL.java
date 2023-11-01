package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionItemDAL;
import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionItem;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
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
            String message = I18n.get("messages", "promotion_item.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "promotion_item.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.PROMOTION_ITEM.QUANTITY))
            return validateQuantity((String) value);
        return new Pair<>(false, I18n.get("messages", "promotion_item.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateQuantity(String quantity) {
        if (quantity.isBlank())
            return new Pair<>(false, I18n.get("messages", "promotion_item.validate.quantity.no_empty"));
        if (!VNString.checkUnsignedNumber(quantity))
            return new Pair<>(false, I18n.get("messages", "promotion_item.validate.quantity.unsignedNumber.not"));
        return new Pair<>(true, I18n.get("messages", "promotion_item.validate.quantity"));
    }
}
