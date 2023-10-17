package mini_supermarket.DAL;

import mini_supermarket.DTO.Discount;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountDAL extends EntityDAL<Discount> {
    public DiscountDAL() {
        super(Discount.class, List.of(
            __.DISCOUNT.COLUMN.ID,
            __.DISCOUNT.COLUMN.PERCENT,
            __.DISCOUNT.COLUMN.START_DATE,
            __.DISCOUNT.COLUMN.END_DATE,
            __.DISCOUNT.COLUMN.STATUS
        ));
    }
}
