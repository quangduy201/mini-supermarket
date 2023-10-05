package mini_supermarket.DAL;

import mini_supermarket.DTO.DiscountDetail;
import mini_supermarket.DTO.DiscountDetailId;

import java.util.List;

public class DiscountDetailDAL extends RelationshipDAL<DiscountDetail, DiscountDetailId> {
    public DiscountDetailDAL() {
        super(DiscountDetail.class, List.of());
    }
}
