package mini_supermarket.DAL;

import mini_supermarket.DTO.Promotion;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionDAL extends EntityDAL<Promotion> {
    public PromotionDAL() {
        super(Promotion.class, List.of(
            __.PROMOTION.COLUMN.ID,
            __.PROMOTION.COLUMN.NAME,
            __.PROMOTION.COLUMN.START_DATE,
            __.PROMOTION.COLUMN.END_DATE,
            __.PROMOTION.COLUMN.STATUS
        ));
    }
}
