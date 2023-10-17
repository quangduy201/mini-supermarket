package mini_supermarket.DAL;

import mini_supermarket.DTO.Promotion;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionDAL extends EntityDAL<Promotion> {
    public PromotionDAL() {
        super(Promotion.class, List.of(
            __.PROMOTION.ID,
            __.PROMOTION.START_DATE,
            __.PROMOTION.END_DATE,
            __.PROMOTION.STATUS
        ));
    }
}
