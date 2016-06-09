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
 *
 * Created by romelyn on 7/06/2016.
 * A class that would take care of communicating the UI thread and the thread
 * that would read data about NetRules
 */
public class NetRulesBackgroundTask extends AsyncTask<String,NetRule,String> {
    private Context ctc;
    private NetRulesListAdapter netRuleAdapter;
    private Activity activity;
    private ListView lv;

    /**
     * Constructor that accepts the context being passed
     * @param ctc
     */
    public NetRulesBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc=ctc;//initialized the context
        netRuleAdapter = new NetRulesListAdapter(ctc);//initializes the custom list adapter for checklist
        activity =(Activity)ctc;//initializes the activity
    }

    /**
     *Executes before the thread starts
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * Executes at the end of the thread
     * @param result of type string
     */
    @Override
    protected void onPostExecute(String result) {
        Log.d("BG","setting adapter");
        if(result.equals("Reading DB")){
            lv.setAdapter(netRuleAdapter);// sets the adapter for the listview
        }else{
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * Adds the Netrules in the custom list adapter for the Netrules listview
     * @param values
     */
    @Override
    protected void onProgressUpdate(NetRule... values) {
        Log.d("BG","On progress update");
        netRuleAdapter.add(values[0]);//adds the checklist being passed as parameter
        Log.d("BG","populating");
    }

    /**
     *Executes before the thread starts
     * @param params Accepts parameter of Strings
     * @return String
     */
    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        NetRuleDAO ndao;

        //if the task is reading the database
        if(method.equals("R")){
            lv = (ListView)activity.findViewById(R.id.listViewNetRules);//casting of lv to the custom widget
            ndao= new NetRuleDAO(ctc);//instantiates the Checklist Data Access Object
            netRuleAdapter = new NetRulesListAdapter(ctc);//instantiates the checklist custom adapter
            Log.d("BG",netRuleAdapter.toString());
            int id;
            String n_title;
            String n_description;
            Double n_penalty;
            byte[] image;
            Cursor cursor = ndao.getAllNetRules();//assigns a cursor with the list of all checklist from the db
            cursor.moveToFirst();
            Log.d("BG Hello",""+cursor.getCount());
            do{ //while the cursor has items
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID));
                n_title = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE));
                n_description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION));
                n_penalty = cursor.getDouble(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE));
                //instantiates the NetRule with the row in the cursor
                NetRule cl= new NetRule(id, n_title, n_description,n_penalty,image);
                publishProgress(cl);//calls the onProgressUpdate method
                Log.d("BG",""+netRuleAdapter.getCount());
            }while(cursor.moveToNext());
            return "Reading DB";
        }
        return null;
    }
}