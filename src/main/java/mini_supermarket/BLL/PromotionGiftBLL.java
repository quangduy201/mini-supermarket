package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionGiftDAL;
import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionGift;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionGiftBLL extends RelationshipBLL<PromotionGift, PromotionDetailId> {
    public PromotionGiftBLL() {
        super(new PromotionGiftDAL());
    }

    @Override
    public Pair<Boolean, String> exists(PromotionGift promotionGift) {
        List<PromotionGift> promotionGifts;
        promotionGifts = findBy(
            __.PROMOTION_GIFT.PROMOTION, promotionGift.getId().getPromotion(),
            __.PROMOTION_GIFT.PRODUCT, promotionGift.getId().getProduct());
        if (!promotionGifts.isEmpty()) {
            String message = I18n.get("messages", "promotion_gift.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "promotion_gift.exists.not");
        return new Pair<>(false, message);
    }
}
