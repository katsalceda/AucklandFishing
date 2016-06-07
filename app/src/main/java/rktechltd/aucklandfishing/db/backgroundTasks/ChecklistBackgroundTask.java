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
 */
public class ChecklistBackgroundTask extends AsyncTask<String,Checklist,String> {
    Context ctc;
    CheckListAdapter checkListAdapter;
    Activity activity;
    ListView lv;

    public ChecklistBackgroundTask(Context ctc){
        Log.d("BG TASK", "inside constructor");
        this.ctc=ctc;
        checkListAdapter = new CheckListAdapter(ctc);
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
            lv.setAdapter(checkListAdapter);
        }else{
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Checklist... values) {
        Log.d("BG","On progress update");
        checkListAdapter.add(values[0]);
        Log.d("BG","populating");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        ChecklistDAO cdao;

        if(method.equals("C")){
            return "C";
        }else if(method.equals("R")){
            lv = (ListView)activity.findViewById(R.id.listViewSafetyChecklist);
            cdao= new ChecklistDAO(ctc);
            checkListAdapter = new CheckListAdapter(ctc);
            Log.d("BG",checkListAdapter.toString());
            int id;
            String title, description;
            byte[] image;
            Cursor cursor = cdao.getAllCheckList();
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            while(cursor.moveToNext()){
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID));
                title = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE));
                description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE));
                Checklist cl= new Checklist(id, title, description,image);
                publishProgress(cl);
                checkListAdapter.add(cl);
                Log.d("BG",""+checkListAdapter.getCount());
            }
            return "Reading DB";
        }
        return null;
    }
}
