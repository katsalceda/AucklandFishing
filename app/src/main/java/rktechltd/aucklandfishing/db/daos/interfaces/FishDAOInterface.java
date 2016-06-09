package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Fish;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * An interface for Fish Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public interface FishDAOInterface {
    Fish getFish(int fishId);
    Cursor getAllFish();
    boolean addFish(Fish fish);
    int getFishId(String fishName);
}
