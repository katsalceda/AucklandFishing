package rktechltd.aucklandfishing.db.daos.interfaces;

import java.util.List;

import rktechltd.aucklandfishing.models.Fish;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface FishDAOInterface {
    Fish getFish(int fishId);
    List<Fish> getAllFish();
    boolean addFish(Fish fish);
    int getFishId(String fishName);
}
