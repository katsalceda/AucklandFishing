package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FishDAOInterface;
import rktechltd.aucklandfishing.models.Fish;

/**
 * Created by romelyn on 30/05/2016.
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
    public List<Fish> getAllFish() {

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
