package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionDAL;
import mini_supermarket.DTO.Promotion;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
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
            String message = I18n.get("messages", "promotion.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "promotion.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.PROMOTION.START_DATE))
            return validateStartDate((String) value);
        if (attribute.equals(__.PROMOTION.END_DATE))
            return validateEndDate((String) value);
        return new Pair<>(false, I18n.get("messages", "promotion.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateStartDate(String startDate) {
        if (startDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "promotion.validate.startDate.no_empty"));
        if (!VNString.checkFormatDate(startDate))
            return new Pair<>(false, I18n.get("messages", "promotion.validate.startDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "promotion.validate.startDate"));
    }

    private static Pair<Boolean, String> validateEndDate(String endDate) {
        if (endDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "promotion.validate.endDate.no_empty"));
        if (!VNString.checkFormatDate(endDate))
            return new Pair<>(false, I18n.get("messages", "promotion.validate.endDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "promotion.validate.endDate"));
    }
}
