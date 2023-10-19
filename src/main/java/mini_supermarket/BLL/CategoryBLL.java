package mini_supermarket.BLL;

import mini_supermarket.DAL.CategoryDAL;
import mini_supermarket.DTO.Category;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class CategoryBLL extends EntityBLL<Category> {
    public CategoryBLL() {
        super(new CategoryDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Category category) {
        List<Category> categories;
        categories = findBy(__.CATEGORY.ID, category.getId());
        if (!categories.isEmpty()) {
            String message = I18n.get("messages", "category.exists");
            return new Pair<>(true, message);
        }

        categories = findBy(
            __.CATEGORY.NAME, category.getName(),
            __.CATEGORY.DELETED, false);
        if (!categories.isEmpty()) {
            String message = I18n.get("messages", "category.exists.name", category.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "category.exists.not");
        return new Pair<>(false, message);
    }
}
