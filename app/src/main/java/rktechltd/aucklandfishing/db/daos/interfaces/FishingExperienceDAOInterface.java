package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * An interface for FishingExperience Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface FishingExperienceDAOInterface {
    FishingExperience getFishingExperience(int fishingExperienceId);
    FishingExperience getFishingExperience(String date, int locationId);
    FishingExperience getFishingExperience(String date);
    Cursor getAllFishingExperience();
    boolean addFishingExperience(FishingExperience fishingExperience);
    int getFishingExperienceId(String date, String locationName);
    int getLatestXPId();
}
