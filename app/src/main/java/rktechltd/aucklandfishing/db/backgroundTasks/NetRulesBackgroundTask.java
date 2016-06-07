package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.NetRulesListAdapter;
import rktechltd.aucklandfishing.db.AucklandFishingDBTables;
import rktechltd.aucklandfishing.db.daos.implementations.NetRuleDAO;
import rktechltd.aucklandfishing.models.NetRule;

/**
 * Created by romelyn on 7/06/2016.
 */
public class NetRulesBackgroundTask extends AsyncTask<String,NetRule,String> {
    Context ctc;
    NetRulesListAdapter netRuleAdapter;
    Activity activity;
    ListView lv;

    public NetRulesBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc=ctc;
        netRuleAdapter = new NetRulesListAdapter(ctc);
        activity =(Activity)ctc;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("BG","setting adapter");
        if(result.equals("Reading DB")){
            lv.setAdapter(netRuleAdapter);
        }else{
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(NetRule... values) {
        Log.d("BG","On progress update");
        netRuleAdapter.add(values[0]);
        Log.d("BG","populating");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        NetRuleDAO ndao;

        if(method.equals("C")){
            return "C";
        }else if(method.equals("R")){
            lv = (ListView)activity.findViewById(R.id.listViewNetRules);
            ndao= new NetRuleDAO(ctc);
            netRuleAdapter = new NetRulesListAdapter(ctc);
            Log.d("BG",netRuleAdapter.toString());
            int id;
            String n_title;
            String n_description;
            Double n_penalty;
            byte[] image;
            Cursor cursor = ndao.getAllNetRules();
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            while(cursor.moveToNext()){
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID));
                n_title = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE));
                n_description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION));
                n_penalty = cursor.getDouble(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE));
                NetRule cl= new NetRule(id, n_title, n_description,n_penalty,image);
                publishProgress(cl);
                netRuleAdapter.add(cl);
                Log.d("BG",""+netRuleAdapter.getCount());
            }
            return "Reading DB";
        }
        return null;
    }
}