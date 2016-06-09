package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.FishCatch;

/**
 * An interface for FishCatch Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface FishCatchDAOInterface {
    FishCatch getFishCatch(int fishCatchId);
    Cursor getAllFishCatch();
    boolean addFishCatch(FishCatch fishCatch);
    Cursor getAllFishCatchOfExperience(int fishingExperienceId);
    int getLatestFishCatchId();
}
