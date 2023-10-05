package mini_supermarket.BLL;

import mini_supermarket.DAL.DiscountDAL;
import mini_supermarket.DTO.Discount;

public class DiscountBLL extends EntityBLL<Discount> {
    public DiscountBLL() {
        super(new DiscountDAL());
    }
}
