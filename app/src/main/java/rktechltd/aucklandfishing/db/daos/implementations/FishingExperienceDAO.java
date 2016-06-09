package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishingExperienceDAOInterface;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FishingExperienceDAO implements FishingExperienceDAOInterface {
    private AucklandFishingDBHelper db ;
    public FishingExperienceDAO(Context context){
        db = new AucklandFishingDBHelper(context); //initialize database with context
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
        Cursor cursor = null;
        cursor = db.getAllFishingExperience();
        return cursor;
    }

    @Override
    public boolean addFishingExperience(FishingExperience fishingExperience) {
        return db.saveFishingExperience(fishingExperience);
    }

    @Override
    public int getFishingExperienceId(String date, int locationId) {

        return db.findFishingExperienceId(date, ""+locationId);
    }

    @Override
    public int getLatestXPId() {
        return db.findLatestXPId();
    }
}
