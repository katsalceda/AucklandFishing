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
import rktechltd.aucklandfishing.db.daos.implementations.FishingExperienceDAO;
import rktechltd.aucklandfishing.models.FishCatch;

/**
 * A class that would take care of communicating the UI thread and the thread
 * that would insert and read data about Fishing Catch
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
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
        Log.d("ACTIVITY",activity.toString());
    }

    /**
     *Executes before the thread starts
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
             FishingExperienceDAO fdao = new FishingExperienceDAO(ctc);
            fcdao = new FishCatchDAO(ctc);//instantiates the Checklist Data Access Object
            int id = fcdao.getLatestFishCatchId()+1;//gets the next fish catch id
            int fx=fdao.getLatestXPId();//gets the latest fishingexperience id that was saved
            double length = Double.parseDouble(params[1]);
            double weight = Double.parseDouble(params[2]);
            String name = params[3];
            String remarks = params[4];

            Log.d("XP",""+id);
            FishCatch fc = new FishCatch(id,fx,length,weight, name, remarks);
            fcdao.addFishCatch(fc);
            return "Catch Added";

        } else if (method.equals("R")) {   //if the task is reading the database

            lv = (ListView)this.activity.findViewById(R.id.lvFC);//casting of lv to the custom widget
            fcdao = new FishCatchDAO(ctc);//instantiates the Checklist Data Access Object
            fcatchAdapter = new FCatchAdapter(ctc);//instantiates the checklist custom adapter
            Log.d("BG", fcatchAdapter.toString());
            int id;
            String catchName;
            String remark;
            int xp;
            Double weight, length;
            byte[] img;
            //assigns a cursor with the list of all checklist from the db
            Cursor cursor = fcdao.getAllFishCatchOfExperience(Integer.parseInt(params[1]));
            if (cursor.getCount()>0) {
                cursor.moveToFirst();
                Log.d("BG", "" + cursor.getCount());
               do { //while the cursor has items
                    id = cursor.getInt(0);
                    xp = cursor.getInt(1);
                    length = cursor.getDouble(2);
                    weight = cursor.getDouble(3);
                    //img = cursor.getBlob(6);
                    catchName = cursor.getString(4);
                    remark = cursor.getString(5);
                   //instantiates the FishCatch with the row in the cursor

                    FishCatch exp = new FishCatch(id, xp, length, weight, catchName, remark);
                    publishProgress(exp);//calls the onProgressUpdate method
                    Log.d("BG", "" + fcatchAdapter.getCount());
               }while(cursor.moveToNext());
            }
            return "Reading DB";
        } else {
            return ("No Catch to load");
        }
    }
    @Override
    protected void onPostExecute(String s) {
        Log.d("POST", "setting adapter");
        if (s.equals("Reading DB")) {
            this.lv.setAdapter(fcatchAdapter);// sets the adapter for the listview
        } else if(s.equals("Catch Added")){
            Toast.makeText(ctc,s, Toast.LENGTH_SHORT).show();
        }
    }
}