package mini_supermarket.BLL;

import mini_supermarket.DAL.BrandDAL;
import mini_supermarket.DTO.Brand;

public class BrandBLL extends EntityBLL<Brand> {
    public BrandBLL() {
        super(new BrandDAL());
    }
}
