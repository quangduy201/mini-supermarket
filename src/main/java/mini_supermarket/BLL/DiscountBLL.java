package mini_supermarket.BLL;

import mini_supermarket.DAL.DiscountDAL;
import mini_supermarket.DTO.Discount;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountBLL extends EntityBLL<Discount> {
    public DiscountBLL() {
        super(new DiscountDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Discount discount) {
        List<Discount> discounts;
        discounts = findBy(__.DISCOUNT.ID, discount.getId());
        if (!discounts.isEmpty()) {
            String message = I18n.getString("discount.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("discount.exists.not");
        return new Pair<>(false, message);
    }
}
