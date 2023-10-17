package mini_supermarket.DAL;

import mini_supermarket.DTO.Category;
import mini_supermarket.utils.__;

import java.util.List;

public class CategoryDAL extends EntityDAL<Category> {
    public CategoryDAL() {
        super(Category.class, List.of(
            __.CATEGORY.ID,
            __.CATEGORY.NAME,
            __.CATEGORY.QUANTITY
        ));
    }
}
