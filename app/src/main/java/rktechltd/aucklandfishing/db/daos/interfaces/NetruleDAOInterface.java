package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.NetRule;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * An interface for NetRule Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public interface NetruleDAOInterface {
    NetRule getNetRule(int netRuleId);
    Cursor getAllNetRules();
    boolean addNetRule(NetRule netRule);
    int getNetRuleId(String title);
}
