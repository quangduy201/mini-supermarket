package mini_supermarket.BLL;

import mini_supermarket.DAL.PromotionDAL;
import mini_supermarket.DTO.Promotion;

public class PromotionBLL extends EntityBLL<Promotion> {
    public PromotionBLL() {
        super(new PromotionDAL());
    }
}
