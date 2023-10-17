package mini_supermarket.BLL;

import mini_supermarket.DAL.DiscountDetailDAL;
import mini_supermarket.DTO.DiscountDetail;
import mini_supermarket.DTO.DiscountDetailId;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class DiscountDetailBLL extends RelationshipBLL<DiscountDetail, DiscountDetailId> {
    public DiscountDetailBLL() {
        super(new DiscountDetailDAL());
    }

    @Override
    public Pair<Boolean, String> exists(DiscountDetail discountDetail) {
        List<DiscountDetail> discountDetails;
        discountDetails = findBy(
            __.DISCOUNT_DETAIL.DISCOUNT, discountDetail.getId().getDiscount(),
            __.DISCOUNT_DETAIL.PRODUCT, discountDetail.getId().getProduct());
        if (!discountDetails.isEmpty()) {
            String message = I18n.getString("discount_detail.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("discount_detail.exists.not");
        return new Pair<>(false, message);
    }
}
