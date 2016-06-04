package rktechltd.aucklandfishing.db.daos.interfaces;

import java.util.List;

import rktechltd.aucklandfishing.models.FishCatch;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface FishCatchDAOInterface {
    FishCatch getFishCatch(int fishCatchId);
    List<FishCatch> getAllFishCatch();
    boolean addFishCatch(FishCatch fishCatch);
    List<FishCatch> getAllFishCatchOfExperience(int fishingExperienceId);
}
