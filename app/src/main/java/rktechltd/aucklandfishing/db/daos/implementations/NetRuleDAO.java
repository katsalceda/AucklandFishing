package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.NetruleDAOInterface;
import rktechltd.aucklandfishing.models.NetRule;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * A NetRule Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public class NetRuleDAO implements NetruleDAOInterface {
    private AucklandFishingDBHelper db ;
    /**
     * Constructor
     * @param context
     */
    public NetRuleDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    /**
     * gets the NetRule given the id
     * @param netRuleId
     * @return
     */
    @Override
    public NetRule getNetRule(int netRuleId) {

        return db.findNetRule(""+netRuleId);
    }
    /**
     * Gets all NetRule
     * @return Cursor
     */
    @Override
    public Cursor getAllNetRules(){
        return db.getAllNetRules();
    }
    /**
     * adds new NetRule
     * @param netRule
     * @return
     */
    @Override
    public boolean addNetRule(NetRule netRule) {
      return db.saveNetRule(netRule);
    }

    /**
     * Gets the id given a title
     * @param title
     * @return
     */
    @Override
    public int getNetRuleId(String title) {

        return db.findNetRuleId(title);
    }
}
