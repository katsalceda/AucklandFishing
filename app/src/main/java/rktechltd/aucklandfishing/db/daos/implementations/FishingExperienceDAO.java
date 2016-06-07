package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishingExperienceDAOInterface;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * Created by romelyn on 30/05/2016.
 */
public class FishingExperienceDAO implements FishingExperienceDAOInterface {
    private AucklandFishingDBHelper db ;
    public FishingExperienceDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    @Override
    public FishingExperience getFishingExperience(int fishingExperienceId) {
         return db.findFishingExperience(""+fishingExperienceId);
    }

    @Override
    public FishingExperience getFishingExperience(String date, int locationId) {
       return db.findFishingExperience(date, ""+locationId);
    }

    @Override
    public FishingExperience getFishingExperience(String date) {
        return db.findFishingExperience(date);
    }

    @Override
    public Cursor getAllFishingExperience() {
        return db.getAllFishingExperience();
    }

    @Override
    public boolean addFishingExperience(FishingExperience fishingExperience) {
        return db.saveFishingExperience(fishingExperience);
    }

    @Override
    public int getFishingExperienceId(String date, int locationId) {

        return db.findFishingExperienceId(date, ""+locationId);
    }
}
