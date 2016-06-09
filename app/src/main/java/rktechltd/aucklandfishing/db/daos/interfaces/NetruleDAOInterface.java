package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.models.NetRule;

/**
 * An interface for NetRule Data Access Object
 * Created by romelyn on 30/05/2016.
 */
public interface NetruleDAOInterface {
    NetRule getNetRule(int netRuleId);
    Cursor getAllNetRules();
    boolean addNetRule(NetRule netRule);
    int getNetRuleId(String title);
}
