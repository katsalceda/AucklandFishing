package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.FishListAdapter;
import rktechltd.aucklandfishing.db.AucklandFishingDBTables;
import rktechltd.aucklandfishing.db.daos.implementations.FishDAO;
import rktechltd.aucklandfishing.models.Fish;

/**
 * Created by romelyn on 7/06/2016.
 */
public class FishBackgroundTask extends AsyncTask<String,Fish,String> {
    Context ctc;
    FishListAdapter fishListAdapter;
    Activity activity;
    ListView lv;

    public FishBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc=ctc;
        fishListAdapter = new FishListAdapter(ctc);
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
            lv.setAdapter(fishListAdapter);
        }else{
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Fish... values) {
        Log.d("BG","On progress update");
        fishListAdapter.add(values[0]);
        Log.d("BG","populating");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FishDAO fishDao;

        if(method.equals("C")){
            return "C";
        }else if(method.equals("R")){
            lv = (ListView)activity.findViewById(R.id.listViewFish);
            fishDao= new FishDAO(ctc);
            fishListAdapter = new FishListAdapter(ctc);
            Log.d("BG",fishListAdapter.toString());
            int id;
            String f_name;
            String f_description;
            byte[] image;
            int cat;
            double length;
            int maxLimit;
            int isCombined;
            Cursor cursor = fishDao.getAllFish();
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            while(cursor.moveToNext()){
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_ID));
                f_name = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_NAME));
                f_description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_DESC));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE));
                cat = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_CAT));
                length = cursor.getDouble(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM));
                maxLimit = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT));
                isCombined = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG));
                Fish cl= new Fish(id, f_name, f_description,image,cat,length,maxLimit,isCombined);
                publishProgress(cl);
                fishListAdapter.add(cl);
                Log.d("BG",""+fishListAdapter.getCount());
            }
            return "Reading DB";
        }
        return null;
    }
}
