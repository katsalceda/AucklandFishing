package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.CategoryDAOInterface;
import rktechltd.aucklandfishing.models.Category;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * A Category Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public class CategoryDAO implements CategoryDAOInterface {
    private AucklandFishingDBHelper db ;

    /**
     * Constructor
     * @param context
     */
    public CategoryDAO(Context context){
        db = new AucklandFishingDBHelper(context); //initialize database with context
    }

    /**
     * adds new category
     * @param category
     * @return
     */
    @Override
    public boolean addCategory(Category category) {
         return db.saveCategory(category);
    }

    /**
     * gets the category given the id
     * @param id
     * @return
     */
    @Override
    public Category getCategory(int id) {
      return db.findCategory(""+id);
    }

    /**
     * Gets the categoryid of a given name
     * @param catName
     * @return
     */
    @Override
    public int getCategoryId(String catName) {
       return db.findCategoryId(catName);
    }
    /**
     * Gets all Category
     * @return Cursor
     */
    @Override
    public Cursor getAllCategories() {
       return db.getAllCategories();
    }
}
