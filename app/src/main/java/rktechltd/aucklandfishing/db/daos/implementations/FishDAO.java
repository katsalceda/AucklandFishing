package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishDAOInterface;
import rktechltd.aucklandfishing.models.Fish;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FishDAO implements FishDAOInterface {
    private AucklandFishingDBHelper db ;
    public FishDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    @Override
    public Fish getFish(int fishId) {
      return db.findFish(""+fishId);
    }

    @Override
    public Cursor getAllFish() {
       return db.getAllFishes();
    }
    @Override
    public boolean addFish(Fish fish) {
        return db.saveFish(fish);
    }

    @Override
    public int getFishId(String fishName) {
        return db.findFishId(fishName);
    }
}
