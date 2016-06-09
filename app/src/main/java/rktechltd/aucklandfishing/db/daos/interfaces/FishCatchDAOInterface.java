package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.FishCatch;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * An interface for FishCatch Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public interface FishCatchDAOInterface {
    FishCatch getFishCatch(int fishCatchId);
    Cursor getAllFishCatch();
    boolean addFishCatch(FishCatch fishCatch);
    Cursor getAllFishCatchOfExperience(int fishingExperienceId);
    int getLatestFishCatchId();
}
