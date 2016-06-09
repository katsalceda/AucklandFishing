package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * An interface for FishingExperience Data Access Object
 * Created by romelyn on 30/05/2016.
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
