package mini_supermarket.DAL;

import mini_supermarket.DTO.Brand;

import java.util.List;

public class BrandDAL extends EntityDAL<Brand> {
    public BrandDAL() {
        super(Brand.class, List.of());
    }
}
