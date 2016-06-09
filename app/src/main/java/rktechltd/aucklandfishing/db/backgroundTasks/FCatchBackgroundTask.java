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
 * Created by KatSalceda on 8/06/16.
 */
public class FCatchBackgroundTask extends AsyncTask<String, FishCatch,String> {
    Context ctc;
    FCatchAdapter fcatchAdapter;
    Activity activity;
    ListView lv;

    public FCatchBackgroundTask(Context ctc) {
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc;
        fcatchAdapter = new FCatchAdapter(ctc);
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
            lv.setAdapter(fcatchAdapter);
        } else {
            Toast.makeText(ctc, result, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(FishCatch... values) {
        Log.d("BG", "On progress update");
        fcatchAdapter.add(values[0]);
        Log.d("BG", "populating");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FishCatchDAO fcdao;

        if (method.equals("C")) {
            return "C";
        } else if (method.equals("R")) {
            lv = (ListView) activity.findViewById(R.id.listViewCatch);
            fcdao = new FishCatchDAO(ctc);
            fcatchAdapter = new FCatchAdapter(ctc);
            Log.d("BG", fcatchAdapter.toString());
            int id;
            String catchName;
            String remark;
            Double weight, length;

            Cursor cursor = fcdao.getAllFishCatch();
            if (cursor == null) {
                cursor.moveToFirst();
                Log.d("BG", "" + cursor.getCount());
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    catchName = cursor.getString(1);
                    weight = cursor.getDouble(2);
                    length = cursor.getDouble(3);
                    remark = cursor.getString(6);

                    //FishCatch exp = new FishCatch(id, fx, length, weight, picture, name, remark);
                    //publishProgress(exp);
                    //fcatchAdapter.add(exp);
                    Log.d("BG", "" + fcatchAdapter.getCount());
                }
            }
            return "Reading DB";
        } else {
            return ("No fishing experience to load");
        }
    }
}