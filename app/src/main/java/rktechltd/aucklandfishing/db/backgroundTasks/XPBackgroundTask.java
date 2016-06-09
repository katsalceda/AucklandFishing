package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import rktechltd.aucklandfishing.R;

import rktechltd.aucklandfishing.adapters.FAQListAdapter;
import rktechltd.aucklandfishing.adapters.XPListAdapter;
import rktechltd.aucklandfishing.db.daos.implementations.FishingExperienceDAO;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * A class that would take care of communicating the UI thread and the thread
 * that would read data about Fishing Experience
 * Created by romelyn on 2/06/2016.
 */
public class XPBackgroundTask extends AsyncTask<String,FishingExperience,String> {
    private Context ctc;
    private XPListAdapter xpListAdapter;
    private Activity activity;
    private ListView lv;
    private Button saveButton;

    /**
     * Constructor that accepts the context being passed
     * @param ctc
     */
    public XPBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc;//initialized the context
        xpListAdapter = new XPListAdapter(ctc);//initializes the custom list adapter for checklist
        activity = (Activity) this.ctc;//initializes the activity
    }

    /**
     *Executes before the thread starts
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    /**
     * Adds the FishingExperience in the custom list adapter for the FishingExperience listview
     * @param values
     */
    @Override
    protected void onProgressUpdate(FishingExperience... values) {
        Log.d("BG","On progress update");
        xpListAdapter.add(values[0]);//adds the checklist being passed as parameter
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
        FishingExperienceDAO fxdao;
        if (method.equals("I")) {   //if the task is inserting new fishing experience in the database
            saveButton=(Button)this.activity.findViewById(R.id.buttonSaveXP);
            fxdao = new FishingExperienceDAO(ctc); //instantiates the Checklist Data Access Object
            int id = fxdao.getLatestXPId()+1;//gets the next fishing experience id
            Log.d("XP",""+id);
            String location = params[1];
            double latitude = Double.parseDouble(params[2]);
            double longitude = Double.parseDouble(params[3]);
            //gets the current date
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            //gets the current time
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            //new fishing experience created
            FishingExperience fx = new FishingExperience(id,location,latitude,longitude,date, time, "");
            fxdao.addFishingExperience(fx);
            return "Fishing Experience Added";
        }else if(method.equals("R")){   //if the task is reading the database
            lv = (ListView)this.activity.findViewById(R.id.lvXP);//casting of lv to the custom widget
            fxdao= new FishingExperienceDAO(ctc);//instantiates the Checklist Data Access Object
            xpListAdapter = new XPListAdapter(ctc);
            Log.d("BG",xpListAdapter.toString());
            int id;
            String locationName;
            String remark;
            Double latitude, longitude;
            java.sql.Date convertedDate = null;

            Cursor cursor = fxdao.getAllFishingExperience();//assigns a cursor with the list of all checklist from the db
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            do { //while the cursor has items
                Log.d("INSIDE DO","WHILE");
                id = cursor.getInt(0);
                locationName = cursor.getString(1);
                latitude = cursor.getDouble(2);
                longitude = cursor.getDouble(3);
                String d = cursor.getString(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                Date parsed=null;
                try {
                     parsed = dateFormat.parse(d);//converts the date from the database
                    } catch (ParseException e) {
                    e.printStackTrace();
                }
                String t = cursor.getString(5);
                Time convertedTime;
                convertedTime = Time.valueOf(t); //converts the time from the database
                remark = cursor.getString(6);
                //instantiates the FishingExperience with the row in the cursor
                FishingExperience exp = new FishingExperience(id, locationName, latitude, longitude, parsed, convertedTime, remark);
                publishProgress(exp);//calls the onProgressUpdate method
             }while (cursor.moveToNext());
            return "Reading DB";
        }
        return null;
    }

    /**
     * Executes at the end of the thread
     * @param s of type string
     */
    @Override
    protected void onPostExecute(String s) {
        Log.d("POST", "setting adapter");
        if (s.equals("Reading DB")) {
            this.lv.setAdapter(xpListAdapter);// sets the adapter for the listview
        } else if(s.equals("Fishing Experience Added")){

            Toast.makeText(ctc,s, Toast.LENGTH_SHORT).show();
        }
    }
}
