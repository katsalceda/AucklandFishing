package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Category;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * An interface for Category Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public interface CategoryDAOInterface {
        boolean addCategory(Category category);
        Category getCategory(int id);
        int getCategoryId(String catName);
        Cursor getAllCategories();
}
