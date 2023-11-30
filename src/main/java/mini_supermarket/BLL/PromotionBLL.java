package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionDAL;
import mini_supermarket.DTO.Promotion;
import mini_supermarket.utils.*;

import java.util.List;

public class PromotionBLL extends EntityBLL<Promotion> {
    public PromotionBLL() {
        super(new PromotionDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Promotion oldPromotion, Promotion newPromotion) {
        List<Promotion> promotions;
        promotions = findBy(__.PROMOTION.ID, newPromotion.getId());
        if (!promotions.isEmpty()) {
            String message = I18n.get("messages", "promotion.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "promotion.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if(attribute.equals(__.PROMOTION.NAME))
            return validateName((String) value);
        if (attribute.equals(__.PROMOTION.START_DATE))
            return validateStartDate((Date) value);
        if (attribute.equals(__.PROMOTION.END_DATE))
            return validateEndDate((Date) value);
        return new Pair<>(false, I18n.get("messages", "promotion.attribute.not_found", attribute));
    }

     private static  Pair<Boolean, String> validateName(String name){
        if(name.isBlank())
            return new Pair<>(false, I18n.get("messages", "promotion.validate.start_date.no_empty"));
         return new Pair<>(true, I18n.get("messages", "promotion.validate.name"));
     }

    private static Pair<Boolean, String> validateStartDate(Date startDate) {
       if (startDate == null || !Date.isValidDate(startDate.date))
            return new Pair<>(false, I18n.get("messages", "promotion.validate.start_date.format.not"));
        return new Pair<>(true, I18n.get("messages", "promotion.validate.start_date"));
    }

    private static Pair<Boolean, String> validateEndDate(Date endDate) {
     if(endDate == null || !Date.isValidDate(endDate.date))
            return new Pair<>(false, I18n.get("messages", "promotion.validate.end_date.format.not"));
        return new Pair<>(true, I18n.get("messages", "promotion.validate.end_date"));
    }
}
