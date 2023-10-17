package mini_supermarket.BLL;

import mini_supermarket.DAL.BrandDAL;
import mini_supermarket.DTO.Brand;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class BrandBLL extends EntityBLL<Brand> {
    public BrandBLL() {
        super(new BrandDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Brand brand) {
        List<Brand> brands;
        brands = findBy(__.BRAND.ID, brand.getId());
        if (!brands.isEmpty()) {
            String message = I18n.getString("brand.exists");
            return new Pair<>(true, message);
        }

        brands = findBy(
            __.BRAND.NAME, brand.getName(),
            __.BRAND.DELETED, false);
        if (!brands.isEmpty()) {
            String message = I18n.getString("brand.exists.name", brand.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.getString("brand.exists.not");
        return new Pair<>(false, message);
    }
}
