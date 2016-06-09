package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishingExperienceDAOInterface;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * A FishingExperience Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FishingExperienceDAO implements FishingExperienceDAOInterface {
    private AucklandFishingDBHelper db ;
    /**
     * Constructor
     * @param context
     */
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
    /**
     * gets the FishingExperience given the id
     * @param date
     * @return
     */
    @Override
    public FishingExperience getFishingExperience(String date) {
        return db.findFishingExperience(date);
    }
    /**
     * Gets all FishingExperience
     * @return Cursor
     */
    @Override
    public Cursor getAllFishingExperience() {
        Cursor cursor = null;
        cursor = db.getAllFishingExperience();
        return cursor;
    }
    /**
     * adds new FishingExperience
     * @param fishingExperience
     * @return
     */
    @Override
    public boolean addFishingExperience(FishingExperience fishingExperience) {
        return db.saveFishingExperience(fishingExperience);
    }

    /**
     * Gets the id given the date and location name
     * @param date
     * @param locationname
     * @return
     */
    @Override
    public int getFishingExperienceId(String date, String locationname) {

        return db.findFishingExperienceId(date, ""+locationname);
    }

    /**
     * Gets the next fishing experience id
     * @return
     */
    @Override
    public int getLatestXPId() {
        return db.findLatestXPId();
    }
}
