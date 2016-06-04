package rktechltd.aucklandfishing.db.daos.interfaces;

import java.util.List;

import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface FishingExperienceDAOInterface {
    FishingExperience getFishingExperience(int fishingExperienceId);
    FishingExperience getFishingExperience(String date, int locationId);
    FishingExperience getFishingExperience(String date);
    List<FishingExperience> getAllFishingExperience();
    boolean addFishingExperience(FishingExperience fishingExperience);
    int getFishingExperienceId(String date, int locationId);
}
