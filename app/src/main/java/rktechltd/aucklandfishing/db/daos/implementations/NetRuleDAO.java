package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.NetruleDAOInterface;
import rktechltd.aucklandfishing.models.NetRule;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class NetRuleDAO implements NetruleDAOInterface {
    private AucklandFishingDBHelper db ;
    public NetRuleDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    @Override
    public NetRule getNetRule(int netRuleId) {

        return db.findNetRule(""+netRuleId);
    }

    @Override
    public Cursor getAllNetRules(){
        return db.getAllNetRules();
    }

    @Override
    public boolean addNetRule(NetRule netRule) {
      return db.saveNetRule(netRule);
    }

    @Override
    public int getNetRuleId(String title) {

        return db.findNetRuleId(title);
    }
}
