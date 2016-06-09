package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Fish;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface FishDAOInterface {
    Fish getFish(int fishId);
    Cursor getAllFish();
    boolean addFish(Fish fish);
    int getFishId(String fishName);
}
