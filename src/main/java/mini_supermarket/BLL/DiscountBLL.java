package mini_supermarket.BLL;

import mini_supermarket.DAL.DiscountDAL;
import mini_supermarket.DTO.Discount;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountBLL extends EntityBLL<Discount> {
    public DiscountBLL() {
        super(new DiscountDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Discount oldDiscount, Discount newDiscount) {
        List<Discount> discounts;
        discounts = findBy(__.DISCOUNT.ID, newDiscount.getId());
        if (!discounts.isEmpty()) {
            String message = I18n.get("messages", "discount.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "discount.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.DISCOUNT.PERCENT))
            return validatePercent((String) value);
        if (attribute.equals(__.DISCOUNT.START_DATE))
            return validateStartDate((String) value);
        if (attribute.equals(__.DISCOUNT.END_DATE))
            return validateEndDate((String) value);
        return new Pair<>(false, I18n.get("messages", "discount.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validatePercent(String percent){
        if (percent.isBlank())
            return new Pair<>(false, I18n.get("messages", "discount.validate.percent.no_empty"));
        if (!VNString.checkRangeOfPercent(percent))
            return new Pair<>(false, I18n.get("messages", "discount.validate.percent.out_of_range"));
        return new Pair<>(true, I18n.get("messages", "discount.validate.percent"));
    }

    private static Pair<Boolean, String> validateStartDate(String startDate) {
        if (startDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "discount.validate.startDate.no_empty"));
        if (!VNString.checkFormatDate(startDate))
            return new Pair<>(false, I18n.get("messages", "discount.validate.startDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "discount.validate.startDate"));
    }

    private static Pair<Boolean, String> validateEndDate(String endDate) {
        if (endDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "discount.validate.endDate.no_empty"));
        if (!VNString.checkFormatDate(endDate))
            return new Pair<>(false, I18n.get("messages", "discount.validate.endDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "discount.validate.endDate"));
    }
}
