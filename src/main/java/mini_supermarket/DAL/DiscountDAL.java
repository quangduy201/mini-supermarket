package mini_supermarket.DAL;

import mini_supermarket.DTO.Discount;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountDAL extends EntityDAL<Discount> {
    public DiscountDAL() {
        super(Discount.class, List.of(
            __.DISCOUNT.ID,
            __.DISCOUNT.PERCENT,
            __.DISCOUNT.START_DATE,
            __.DISCOUNT.END_DATE,
            __.DISCOUNT.STATUS
        ));
    }
}
