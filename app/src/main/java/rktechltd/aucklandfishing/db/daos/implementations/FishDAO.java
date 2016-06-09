package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishDAOInterface;
import rktechltd.aucklandfishing.models.Fish;

/**
 *  A Fish Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FishDAO implements FishDAOInterface {
    private AucklandFishingDBHelper db ;
    /**
     * Constructor
     * @param context
     */
    public FishDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    /**
     * gets the Fish given the id
     * @param fishId
     * @return
     */
    @Override
    public Fish getFish(int fishId) {
      return db.findFish(""+fishId);
    }
    /**
     * Gets all Fish
     * @return Cursor
     */
    @Override
    public Cursor getAllFish() {
       return db.getAllFishes();
    }
    /**
     * adds new Fish
     * @param fish
     * @return
     */
    @Override
    public boolean addFish(Fish fish) {
        return db.saveFish(fish);
    }

    /**
     * Get the id given a fishname
     * @param fishName
     * @return
     */
    @Override
    public int getFishId(String fishName) {
        return db.findFishId(fishName);
    }
}
