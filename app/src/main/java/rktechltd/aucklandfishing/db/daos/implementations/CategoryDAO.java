package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;
import android.database.Cursor;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.CategoryDAOInterface;
import rktechltd.aucklandfishing.models.Category;

/**
 * Created by romelyn on 30/05/2016.
 */
public class CategoryDAO implements CategoryDAOInterface {
    private AucklandFishingDBHelper db ;
    public CategoryDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }

    @Override
    public boolean addCategory(Category category) {
       // SQLiteDatabase sqldb = getWritableDatabase
        return db.saveCategory(category);
    }

    @Override
    public Category getCategory(int id) {
      return db.findCategory(""+id);
    }

    @Override
    public int getCategoryId(String catName) {
       return db.findCategoryId(catName);
    }

    @Override
    public Cursor getAllCategories() {
       return db.getAllCategories();
    }


}
