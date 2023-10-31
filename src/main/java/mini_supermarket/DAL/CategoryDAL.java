package mini_supermarket.DAL;

import mini_supermarket.DTO.Category;
import mini_supermarket.utils.__;

import java.util.List;

public class CategoryDAL extends SafeEntityDAL<Category> {
    public CategoryDAL() {
        super(Category.class, List.of(
            __.CATEGORY.COLUMN.ID,
            __.CATEGORY.COLUMN.NAME,
            __.CATEGORY.COLUMN.QUANTITY
        ));
    }
}
