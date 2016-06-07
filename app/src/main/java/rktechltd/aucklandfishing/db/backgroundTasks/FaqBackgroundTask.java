package rktechltd.aucklandfishing.db.backgroundTasks;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.adapters.FAQListAdapter;
import rktechltd.aucklandfishing.db.AucklandFishingDBTables;
import rktechltd.aucklandfishing.db.daos.implementations.FaqDAO;
import rktechltd.aucklandfishing.models.Faq;

/**
 * Created by romelyn on 7/06/2016.
 */
public class FaqBackgroundTask extends AsyncTask<String,Faq,String> {
    Context ctc;
    FAQListAdapter faqListAdapter;
    Activity activity;
    ListView lv;

    public FaqBackgroundTask(Context ctc) {
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc;
        faqListAdapter = new FAQListAdapter(ctc);
        activity = (Activity) ctc;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FaqDAO fdao;
        if(method.equals("C")){
            return "C";
        }else if(method.equals("R")) {
            lv = (ListView)activity.findViewById(R.id.listViewFaqs);
            fdao= new FaqDAO(ctc);
            faqListAdapter = new FAQListAdapter(ctc);
            Log.d("BG",faqListAdapter.toString());
            int id;
            String question;
            String answer;
            Cursor cursor = fdao.getAllFaqs();
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
            while(cursor.moveToNext()){
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Faq.COLUMN_FAQ_ID));
                question = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION));
                answer = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER));

                Faq f= new Faq(id, question, answer);
                publishProgress(f);
                faqListAdapter.add(f);
                Log.d("BG",""+faqListAdapter.getCount());
            }
            return "Reading DB";
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("BG", "setting adapter");
        if (result.equals("Reading DB")) {
            lv.setAdapter(faqListAdapter);
        } else {
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Faq... values) {
        Log.d("BG", "On progress update");
        faqListAdapter.add(values[0]);
        Log.d("BG", "populating");
    }

}