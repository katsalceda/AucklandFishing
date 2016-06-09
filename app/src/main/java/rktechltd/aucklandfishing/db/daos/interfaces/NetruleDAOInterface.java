package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.NetRule;

/**
 * An interface for NetRule Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface NetruleDAOInterface {
    NetRule getNetRule(int netRuleId);
    Cursor getAllNetRules();
    boolean addNetRule(NetRule netRule);
    int getNetRuleId(String title);
}
