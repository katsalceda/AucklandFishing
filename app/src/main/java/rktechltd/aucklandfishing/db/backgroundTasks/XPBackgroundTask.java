package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.XPListAdapter;
import rktechltd.aucklandfishing.db.daos.implementations.FishingExperienceDAO;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * Created by KatSalceda on 8/06/16.
 */
public class XPBackgroundTask extends AsyncTask<String,FishingExperience,String> {
    Context ctc;
    XPListAdapter xpAdapter;
    Activity activity;
    ListView lv;

    public XPBackgroundTask(Context ctc) {
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc;
        xpAdapter = new XPListAdapter(ctc);
        activity = (Activity) ctc;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("BG", "setting adapter");
        if (result.equals("Reading DB")) {
            lv.setAdapter(xpAdapter);
        } else {
            Toast.makeText(ctc, result, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(FishingExperience... values) {
        Log.d("BG", "On progress update");
        xpAdapter.add(values[0]);
        Log.d("BG", "populating");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FishingExperienceDAO xpdao;

        if (method.equals("C")) {
            return "C";
        } else if (method.equals("R")) {
            lv = (ListView) activity.findViewById(R.id.listViewXP);
            xpdao = new FishingExperienceDAO(ctc);
            xpAdapter = new XPListAdapter(ctc);
            Log.d("BG", xpAdapter.toString());
            int id;
            String locationName;
            String remark;
            Double latitude, longitude;
            java.sql.Date convertedDate = null;

            Cursor cursor = xpdao.getAllFishingExperience();
            if (cursor != null) {
                cursor.moveToFirst();
                Log.d("BG", "" + cursor.getCount());
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    locationName = cursor.getString(1);
                    latitude = cursor.getDouble(2);
                    longitude = cursor.getDouble(3);
                    String d = cursor.getString(4);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                    try {
                        Date parsed = dateFormat.parse(d);
                        convertedDate = new java.sql.Date(parsed.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String t = cursor.getString(5);
                    Time convertedTime;
                    convertedTime = Time.valueOf(t);

                    remark = cursor.getString(6);

                    FishingExperience exp = new FishingExperience(id, locationName, latitude, longitude, convertedDate, convertedTime, remark);
                    publishProgress(exp);
                    xpAdapter.add(exp);
                    Log.d("BG", "" + xpAdapter.getCount());
                }
            }
            return "Reading DB";
        } else {
            return ("No fishing experience to load");
        }
    }
}