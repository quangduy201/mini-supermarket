package mini_supermarket.BLL;

import mini_supermarket.DAL.DiscountDetailDAL;
import mini_supermarket.DTO.DiscountDetail;
import mini_supermarket.DTO.DiscountDetailId;

public class DiscountDetailBLL extends RelationshipBLL<DiscountDetail, DiscountDetailId> {
    public DiscountDetailBLL() {
        super(new DiscountDetailDAL());
    }
}
