package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionGiftDAL;
import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionGift;

public class PromotionGiftBLL extends RelationshipBLL<PromotionGift, PromotionDetailId> {
    public PromotionGiftBLL() {
        super(new PromotionGiftDAL());
    }
}
