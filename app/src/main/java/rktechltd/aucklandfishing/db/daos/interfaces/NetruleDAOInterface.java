package rktechltd.aucklandfishing.db.daos.interfaces;

import java.util.List;

import rktechltd.aucklandfishing.models.NetRule;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface NetruleDAOInterface {
    NetRule getNetRule(int netRuleId);
    List<NetRule> getAllNetRules();
    boolean addNetRule(NetRule netRule);
    int getNetRuleId(String title);
}
