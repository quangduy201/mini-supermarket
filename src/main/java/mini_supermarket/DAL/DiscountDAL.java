package mini_supermarket.DAL;

import mini_supermarket.DTO.Discount;

import java.util.List;

public class DiscountDAL extends EntityDAL<Discount> {
    public DiscountDAL() {
        super(Discount.class, List.of());
    }
}
