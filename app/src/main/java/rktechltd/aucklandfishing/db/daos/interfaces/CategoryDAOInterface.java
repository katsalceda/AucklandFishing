package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBTables;
import rktechltd.aucklandfishing.models.Category;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface CategoryDAOInterface {
        boolean addCategory(Category category);
        Category getCategory(int id);
        int getCategoryId(String catName);
        Cursor getAllCategories();
}
