package mini_supermarket.DAL;

import mini_supermarket.DTO.PromotionDetailId;
import mini_supermarket.DTO.PromotionItem;

import java.util.List;

public class PromotionItemDAL extends RelationshipDAL<PromotionItem, PromotionDetailId> {
    public PromotionItemDAL() {
        super(PromotionItem.class, List.of());
    }
}
