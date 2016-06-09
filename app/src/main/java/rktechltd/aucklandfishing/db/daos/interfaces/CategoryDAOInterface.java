package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import rktechltd.aucklandfishing.models.Category;

/**
 * An interface for Category Data Access Object
 * Created by romelyn on 30/05/2016.
 */
public interface CategoryDAOInterface {
        boolean addCategory(Category category);
        Category getCategory(int id);
        int getCategoryId(String catName);
        Cursor getAllCategories();
}
