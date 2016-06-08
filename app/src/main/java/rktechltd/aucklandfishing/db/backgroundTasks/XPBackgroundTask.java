package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
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
 * Created by romelyn on 2/06/2016.
 */
public class XPBackgroundTask extends AsyncTask<String,FishingExperience,String> {
   private Context ctc;
    private XPListAdapter xpListAdapter;
    private Activity activity;
    private ListView lv;

    public XPBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc;
        xpListAdapter = new XPListAdapter(ctc);
        activity = (Activity) this.ctc;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onProgressUpdate(FishingExperience... values) {
        Log.d("BG","On progress update");
        xpListAdapter.add(values[0]);
        Log.d("BG","populating");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FishingExperienceDAO fxdao;
        if (method.equals("I")) {
            fxdao = new FishingExperienceDAO(ctc);
            int id = fxdao.getLatestXPId()+1;
            Log.d("XP",""+id);
            String location = params[1];
            double latitude = Double.parseDouble(params[2]);
            double longitude = Double.parseDouble(params[3]);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            FishingExperience fx = new FishingExperience(id,location,latitude,longitude,date, time, "");
            fxdao.addFishingExperience(fx);
            return "Fishing Experience Added";
        }else if(method.equals("R")){
            lv = (ListView)this.activity.findViewById(R.id.lvXP);
            fxdao= new FishingExperienceDAO(ctc);
            xpListAdapter = new XPListAdapter(ctc);
            Log.d("BG",xpListAdapter.toString());
            int id;
            String locationName;
            String remark;
            Double latitude, longitude;
            java.sql.Date convertedDate = null;

            Cursor cursor = fxdao.getAllFishingExperience();
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            do {
                Log.d("INSIDE DO","WHILE");
                id = cursor.getInt(0);
                locationName = cursor.getString(1);
                latitude = cursor.getDouble(2);
                longitude = cursor.getDouble(3);
                String d = cursor.getString(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                Date parsed=null;
                try {
                     parsed = dateFormat.parse(d);
                  //  convertedDate = new java.sql.Date(parsed.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String t = cursor.getString(5);
                Time convertedTime;
                convertedTime = Time.valueOf(t);

                remark = cursor.getString(6);

                FishingExperience exp = new FishingExperience(id, locationName, latitude, longitude, parsed, convertedTime, remark);
                publishProgress(exp);
                xpListAdapter.add(exp);
             }while (cursor.moveToNext());
            return "Reading DB";
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("POST", "setting adapter");
        if (s.equals("Reading DB")) {
            this.lv.setAdapter(xpListAdapter);
        } else {
            Toast.makeText(ctc,s, Toast.LENGTH_SHORT).show();
        }
    }
}
