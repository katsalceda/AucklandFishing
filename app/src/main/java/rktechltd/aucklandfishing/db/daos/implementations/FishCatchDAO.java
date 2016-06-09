package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishCatchDAOInterface;
import rktechltd.aucklandfishing.models.FishCatch;

/**
 * A FishCatch Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FishCatchDAO implements FishCatchDAOInterface {
    private AucklandFishingDBHelper db ;
    /**
     * Constructor
     * @param context
     */
    public FishCatchDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    /**
     * gets the FishCatch given the id
     * @param fishCatchId
     * @return
     */
    @Override
    public FishCatch getFishCatch(int fishCatchId) {
        return db.findFishCatch(""+fishCatchId);
    }

    /**
     * Gets all fish catch
     * @return Cursor
     */
    @Override
    public Cursor getAllFishCatch() {
      return db.getAllFishCatch();
    }
    /**
     * adds new FishCatch
     * @param fishCatch
     * @return
     */
    @Override
    public boolean addFishCatch(FishCatch fishCatch) {
        return db.saveFishingCatch(fishCatch);
    }
    /**
     * Gets all FishCatch
     * @return Cursor
     */
    @Override
    public Cursor getAllFishCatchOfExperience(int fishingExperienceId) {
        return db.findCatches(""+fishingExperienceId);
    }

    /**
     * Gets the next fish catch id
     * @return
     */
    @Override
    public int getLatestFishCatchId() {
        return db.findLatestFCId();
    }
}
