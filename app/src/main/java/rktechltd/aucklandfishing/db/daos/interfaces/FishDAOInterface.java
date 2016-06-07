package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.models.Fish;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface FishDAOInterface {
    Fish getFish(int fishId);
    Cursor getAllFish();
    boolean addFish(Fish fish);
    int getFishId(String fishName);
}
