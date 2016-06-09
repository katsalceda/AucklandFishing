package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishCatchDAOInterface;
import rktechltd.aucklandfishing.models.FishCatch;

/**
 * Created by romelyn on 30/05/2016.
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
