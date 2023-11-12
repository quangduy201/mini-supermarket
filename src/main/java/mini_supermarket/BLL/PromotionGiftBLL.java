package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionGiftDAL;
import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionGift;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionGiftBLL extends RelationshipBLL<PromotionGift, PromotionDetailId> {
    public PromotionGiftBLL() {
        super(new PromotionGiftDAL());
    }

    @Override
    public Pair<Boolean, String> exists(PromotionGift oldPromotionGift, PromotionGift newPromotionGift) {
        List<PromotionGift> promotionGifts;
        promotionGifts = findBy(
            __.PROMOTION_GIFT.PROMOTION, newPromotionGift.getId().getPromotion(),
            __.PROMOTION_GIFT.PRODUCT, newPromotionGift.getId().getProduct());
        if (!promotionGifts.isEmpty()) {
            String message = I18n.get("messages", "promotion_gift.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "promotion_gift.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.PROMOTION_GIFT.QUANTITY))
            return validateQuantity((String) value);
        return new Pair<>(false, I18n.get("messages", "promotion_gift.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateQuantity(String quantity) {
        if (quantity.isBlank())
            return new Pair<>(false, I18n.get("messages", "promotion_gift.validate.quantity.no_empty"));
        if (!VNString.checkUnsignedNumber(quantity))
            return new Pair<>(false, I18n.get("messages", "promotion_gift.validate.quantity.unsignedNumber.not"));
        return new Pair<>(true, I18n.get("messages", "promotion_gift.validate.quantity"));
    }
}
