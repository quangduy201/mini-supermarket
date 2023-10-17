package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionDAL;
import mini_supermarket.DTO.Promotion;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class PromotionBLL extends EntityBLL<Promotion> {
    public PromotionBLL() {
        super(new PromotionDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Promotion promotion) {
        List<Promotion> promotions;
        promotions = findBy(__.PROMOTION.ID, promotion.getId());
        if (!promotions.isEmpty()) {
            String message = I18n.getString("promotion.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("promotion.exists.not");
        return new Pair<>(false, message);
    }
}
