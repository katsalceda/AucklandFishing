package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.models.FishCatch;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface FishCatchDAOInterface {
    FishCatch getFishCatch(int fishCatchId);
    Cursor getAllFishCatch();
    boolean addFishCatch(FishCatch fishCatch);
    Cursor getAllFishCatchOfExperience(int fishingExperienceId);
    int getLatestFishCatchId();
}
