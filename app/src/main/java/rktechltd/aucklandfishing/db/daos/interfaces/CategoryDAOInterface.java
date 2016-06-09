package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Category;

/**
 * An interface for Category Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface CategoryDAOInterface {
        boolean addCategory(Category category);
        Category getCategory(int id);
        int getCategoryId(String catName);
        Cursor getAllCategories();
}
