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
 * A class that would take care of communicating the UI thread and the thread
 * that would read data about fishes
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */

public class FishBackgroundTask extends AsyncTask<String,Fish,String> {
    private Context ctc;
    private FishListAdapter fishListAdapter;
    private Activity activity;
    private ListView lv;

    /**
     * Constructor that accepts the context being passed
     * @param ctc
     */
    public FishBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc=ctc;//initialized the context
        fishListAdapter = new FishListAdapter(ctc);//initializes the custom list adapter for checklist
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
            lv.setAdapter(fishListAdapter);// sets the adapter for the listview
        }else{
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Adds the Fish in the custom list adapter for the Fish listview
     * @param values
     */
    @Override
    protected void onProgressUpdate(Fish... values) {
        Log.d("BG","On progress update");
        fishListAdapter.add(values[0]);//adds the checklist being passed as parameter
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
        FishDAO fishDao;

        //if the task is reading the database
        if(method.equals("R")){
            lv = (ListView)activity.findViewById(R.id.listViewFish);//casting of lv to the custom widget
            fishDao= new FishDAO(ctc);//instantiates the Checklist Data Access Object
            fishListAdapter = new FishListAdapter(ctc);//instantiates the checklist custom adapter
            Log.d("BG",fishListAdapter.toString());
            int id;
            String f_name;
            String f_description;
            byte[] image;
            int cat;
            double length;
            int maxLimit;
            int isCombined;
            Cursor cursor = fishDao.getAllFish();//assigns a cursor with the list of all checklist from the db
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            do{ //while the cursor has items
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_ID));
                f_name = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_NAME));
                f_description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_DESC));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE));
                cat = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_FISH_CAT));
                length = cursor.getDouble(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM));
                maxLimit = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT));
                isCombined = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG));
                //instantiates the Fish with the row in the cursor
                Fish cl= new Fish(id, f_name, f_description,image,cat,length,maxLimit,isCombined);
                publishProgress(cl);//calls the onProgressUpdate method
                Log.d("BG",""+fishListAdapter.getCount());
            }while(cursor.moveToNext());
            return "Reading DB";
        }
        return null;
    }
}
