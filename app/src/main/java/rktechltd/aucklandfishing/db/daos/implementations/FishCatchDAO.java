package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishCatchDAOInterface;
import rktechltd.aucklandfishing.models.FishCatch;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FishCatchDAO implements FishCatchDAOInterface {
    private AucklandFishingDBHelper db ;
    public FishCatchDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    @Override
    public FishCatch getFishCatch(int fishCatchId) {
        return db.findFishCatch(""+fishCatchId);
    }

    @Override
    public Cursor getAllFishCatch() {
      return db.getAllFishCatch();
    }

    @Override
    public boolean addFishCatch(FishCatch fishCatch) {
        return db.saveFishingCatch(fishCatch);
    }

    @Override
    public Cursor getAllFishCatchOfExperience(int fishingExperienceId) {
        return db.findCatches(""+fishingExperienceId);
    }

    @Override
    public int getLatestFishCatchId() {
        return db.findLatestFCId();
    }
}
