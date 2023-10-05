package mini_supermarket.DAL;

import mini_supermarket.DTO.Category;

import java.util.List;

public class CategoryDAL extends EntityDAL<Category> {
    public CategoryDAL() {
        super(Category.class, List.of());
    }
}
