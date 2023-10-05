package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionItemDAL;
import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionItem;

public class PromotionItemBLL extends RelationshipBLL<PromotionItem, PromotionDetailId> {
    public PromotionItemBLL() {
        super(new PromotionItemDAL());
    }
}
