package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.FCatchAdapter;
import rktechltd.aucklandfishing.db.daos.implementations.FishCatchDAO;
import rktechltd.aucklandfishing.models.FishCatch;

/**
 * A class that would take care of communicating the UI thread and the thread
 * that would insert and read data about Fishing Catch
 * Created by KatSalceda on 8/06/16.
 */
public class FCatchBackgroundTask extends AsyncTask<String, FishCatch,String> {
   private Context ctc;
    private FCatchAdapter fcatchAdapter;
    private Activity activity;
    private ListView lv;

    /**
     * Constructor that accepts the context being passed
     * @param ctc
     */
    public FCatchBackgroundTask(Context ctc) {
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc;//initialized the context
        fcatchAdapter = new FCatchAdapter(ctc);//initializes the custom list adapter for checklist
        activity = (Activity) ctc;//initializes the activity
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
        Log.d("BG", "setting adapter");
        if (result.equals("Reading DB")) {
            lv.setAdapter(fcatchAdapter);// sets the adapter for the listview
        } else {
            Toast.makeText(ctc, result, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Adds the FishCatch in the custom list adapter for the FishCatch listview
     * @param values
     */
    @Override
    protected void onProgressUpdate(FishCatch... values) {
        Log.d("BG", "On progress update");
        fcatchAdapter.add(values[0]);//adds the checklist being passed as parameter
        Log.d("BG", "populating");
    }

    /**
     *Executes before the thread starts
     * @param params Accepts parameter of Strings
     * @return String
     */
    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FishCatchDAO fcdao;

        if (method.equals("I")) {    //if the task is inserting new fish caught in the database
          //  saveButton=(Button)this.activity.findViewById(R.id.buttonSaveXP);
            fcdao = new FishCatchDAO(ctc);//instantiates the Checklist Data Access Object
            int id = fcdao.getLatestFishCatchId()+1;//gets the next fish catch id
            int fx=Integer.parseInt(params[1]);
            double length = Double.parseDouble(params[2]);
            double weight = Double.parseDouble(params[3]);
            String name = params[4];
            String remarks = params[5];

            Log.d("XP",""+id);
            FishCatch fc = new FishCatch(id,fx,length,weight, name, remarks);
            fcdao.addFishCatch(fc);
            return "Fishing Experience Added";

        } else if (method.equals("R")) {   //if the task is reading the database

            lv = (ListView) activity.findViewById(R.id.listViewCatch);//casting of lv to the custom widget
            fcdao = new FishCatchDAO(ctc);//instantiates the Checklist Data Access Object
            fcatchAdapter = new FCatchAdapter(ctc);//instantiates the checklist custom adapter
            Log.d("BG", fcatchAdapter.toString());
            int id;
            String catchName;
            String remark;
            int xp;
            Double weight, length;
            byte[] img;

            Cursor cursor = fcdao.getAllFishCatch();//assigns a cursor with the list of all checklist from the db
            if (cursor == null) {
                cursor.moveToFirst();
                Log.d("BG", "" + cursor.getCount());
               do { //while the cursor has items
                    id = cursor.getInt(0);
                    xp = cursor.getInt(1);
                    length = cursor.getDouble(2);
                    weight = cursor.getDouble(3);
                    img = cursor.getBlob(4);
                    catchName = cursor.getString(5);
                    remark = cursor.getString(6);
                   //instantiates the FishCatch with the row in the cursor
                    FishCatch exp = new FishCatch(id, xp, length, weight, img, catchName, remark);
                    publishProgress(exp);//calls the onProgressUpdate method
                    Log.d("BG", "" + fcatchAdapter.getCount());
               }while(cursor.moveToNext());
            }
            return "Reading DB";
        } else {
            return ("No Catch to load");
        }
    }
}