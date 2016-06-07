package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.NetruleDAOInterface;
import rktechltd.aucklandfishing.models.NetRule;

/**
 * Created by romelyn on 30/05/2016.
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
    public List<NetRule> getAllNetRules(){
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
