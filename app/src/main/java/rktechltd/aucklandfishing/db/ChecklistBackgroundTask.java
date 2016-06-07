package rktechltd.aucklandfishing.db;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.CheckListAdapter;
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
        this.ctc=ctc;
        activity =(Activity)ctc;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("R")){
            lv.setAdapter(checkListAdapter);
        }else{

        }
        Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Checklist... values) {

        checkListAdapter.add(values[0]);

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
            int id;
            String title, description;
            byte[] image;
            Cursor cursor = cdao.getAllCheckList();
            while(cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID));
                title = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE));
                description = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION));
                image = cursor.getBlob(cursor.getColumnIndex(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE));
                Checklist cl= new Checklist(id, title, description,image);
                publishProgress(cl);
            }
            return "R";
        }
        return null;
    }
}
