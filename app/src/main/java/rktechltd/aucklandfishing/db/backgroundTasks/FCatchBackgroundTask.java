package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.FCatchAdapter;
import rktechltd.aucklandfishing.db.daos.implementations.FishCatchDAO;
import rktechltd.aucklandfishing.db.daos.implementations.FishingExperienceDAO;
import rktechltd.aucklandfishing.models.FishCatch;
import rktechltd.aucklandfishing.models.FishingExperience;

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

        if (method.equals("I")) {
            fcdao = new FishCatchDAO(ctc);
            int id = fcdao.getLatestFishCatchId()+1;
            int fx=Integer.parseInt(params[1]);
            double length = Double.parseDouble(params[2]);
            double weight = Double.parseDouble(params[3]);
            String name = params[4];
            String remarks = params[5];

            Log.d("XP",""+id);
            FishCatch fc = new FishCatch(id,fx,length,weight, name, remarks);
            fcdao.addFishCatch(fc);
            return "Fishing Experience Added";
        } else if (method.equals("R")) {

            lv = (ListView) activity.findViewById(R.id.listViewCatch);
            fcdao = new FishCatchDAO(ctc);
            fcatchAdapter = new FCatchAdapter(ctc);
            Log.d("BG", fcatchAdapter.toString());
            int id;
            String catchName;
            String remark;
            int xp;
            Double weight, length;
            byte[] img;

            Cursor cursor = fcdao.getAllFishCatch();
            if (cursor == null) {
                cursor.moveToFirst();
                Log.d("BG", "" + cursor.getCount());
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    xp = cursor.getInt(1);
                    length = cursor.getDouble(2);
                    weight = cursor.getDouble(3);
                    img = cursor.getBlob(4);
                    catchName = cursor.getString(5);
                    remark = cursor.getString(6);

                    FishCatch exp = new FishCatch(id, xp, length, weight, img, catchName, remark);
                    publishProgress(exp);
                    fcatchAdapter.add(exp);

                    Log.d("BG", "" + fcatchAdapter.getCount());
                }
            }
            return "Reading DB";
        } else {
            return ("No Catch to load");
        }
    }
}