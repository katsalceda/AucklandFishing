package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.CheckListAdapter;
import rktechltd.aucklandfishing.db.AucklandFishingDBTables;
import rktechltd.aucklandfishing.db.daos.implementations.ChecklistDAO;
import rktechltd.aucklandfishing.models.Checklist;

/**
 * Created by romelyn on 2/06/2016.
 *
 * A class that would take care of communicating the UI thread and the thread
 * that would read data about checklist
 */
public class ChecklistBackgroundTask extends AsyncTask<String,Checklist,String> {
    private Context ctc;
    private CheckListAdapter checkListAdapter;
    private Activity activity;
    private ListView lv;

    /**
     * Constructor that accepts the context being passed
     *
     * @param ctc
     */
    public ChecklistBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc=ctc; //initialized the context
        checkListAdapter = new CheckListAdapter(ctc); //initializes the custom list adapter for checklist
        activity =(Activity)ctc; //initializes the activity
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
            lv.setAdapter(checkListAdapter); // sets the adapter for the listview
        }else{
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Adds the checklist in the custom list adapter for the checklist listview
     * @param values
     */
    @Override
    protected void onProgressUpdate(Checklist... values) {
        Log.d("BG","On progress update");
        checkListAdapter.add(values[0]); //adds the checklist being passed as parameter
        Log.d("BG","populating");
    }

    /**
     * Thread that would run on the background
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        ChecklistDAO cdao;
        //if the task is reading the database
       if(method.equals("R")){
            lv = (ListView)activity.findViewById(R.id.listViewSafetyChecklist); //casting of lv to the custom widget
            cdao= new ChecklistDAO(ctc);//instantiates the Checklist Data Access Object
            checkListAdapter = new CheckListAdapter(ctc); //instantiates the checklist custom adapter
            Log.d("BG",checkListAdapter.toString());
            int id;
            String title, description;
            byte[] image;
            Cursor cursor = cdao.getAllCheckList(); //assigns a cursor with the list of all checklist from the db
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            do{ //while the cursor has items
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID));
                title = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE));
                description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE));
                Checklist cl= new Checklist(id, title, description,image); //instantiates the Checklist with the row in the cursor
                publishProgress(cl); //calls the onProgressUpdate method
                Log.d("BG",""+checkListAdapter.getCount());
            } while(cursor.moveToNext());
            return "Reading DB";
        }
        return null;
    }
}
