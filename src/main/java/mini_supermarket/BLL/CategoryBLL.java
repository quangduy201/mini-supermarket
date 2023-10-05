package mini_supermarket.BLL;

import mini_supermarket.DAL.CategoryDAL;
import mini_supermarket.DTO.Category;

public class CategoryBLL extends EntityBLL<Category> {

    public CategoryBLL() {
        super(new CategoryDAL());
    }
}
