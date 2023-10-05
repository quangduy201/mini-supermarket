package mini_supermarket.DAL;

import mini_supermarket.DTO.Promotion;

import java.util.List;

public class PromotionDAL extends EntityDAL<Promotion> {
    public PromotionDAL() {
        super(Promotion.class, List.of());
    }
}
