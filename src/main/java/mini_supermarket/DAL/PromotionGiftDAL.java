package mini_supermarket.DAL;

import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionGift;

import java.util.List;

public class PromotionGiftDAL extends RelationshipDAL<PromotionGift, PromotionDetailId> {
    public PromotionGiftDAL() {
        super(PromotionGift.class, List.of());
    }
}
