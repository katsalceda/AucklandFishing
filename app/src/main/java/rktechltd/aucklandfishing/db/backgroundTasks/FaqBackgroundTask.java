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
 * A class that would take care of communicating the UI thread and the thread
 * that would read data about faq
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */
public class FaqBackgroundTask extends AsyncTask<String,Faq,String> {
    private Context ctc;
    private FAQListAdapter faqListAdapter;
    private Activity activity;
    private ListView lv;

    /**
     * Constructor that accepts the context being passed
     * @param ctc
     */
    public FaqBackgroundTask(Context ctc) {
        Log.d("BG TASK", "inside constructor");
        this.ctc = ctc; //initialized the context
        faqListAdapter = new FAQListAdapter(ctc); //initializes the custom list adapter for faq
        activity = (Activity) ctc; //initializes the activity
    }

    /**
     *Executes before the thread starts
     * @param params Accepts parameter of Strings
     * @return String
     */
    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        FaqDAO fdao;
        //if the task is reading the database
       if(method.equals("R")) {
            lv = (ListView)activity.findViewById(R.id.listViewFaqs);//casting of lv to the custom widget
            fdao= new FaqDAO(ctc);//instantiates the Checklist Data Access Object
            faqListAdapter = new FAQListAdapter(ctc); //instantiates the checklist custom adapter
            Log.d("BG",faqListAdapter.toString());
            int id;
            String question;
            String answer;
            Cursor cursor = fdao.getAllFaqs(); //assigns a cursor with the list of all checklist from the db
            cursor.moveToFirst();
            Log.d("BG",""+cursor.getCount());
        do{ //while the cursor has items
                Log.d("BG","inside while");
                id = cursor.getInt(cursor.getColumnIndex(AucklandFishingDBTables.Faq.COLUMN_FAQ_ID));
                question = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION));
                answer = cursor.getString(cursor.getColumnIndex(AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER));

                Faq f= new Faq(id, question, answer);//instantiates the Faq with the row in the cursor
                publishProgress(f);//calls the onProgressUpdate method
                Log.d("BG",""+faqListAdapter.getCount());
            }while(cursor.moveToNext());
            return "Reading DB";
        }
        return null;
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
        Log.d("BG", "setting adapter");
        if (result.equals("Reading DB")) {
            lv.setAdapter(faqListAdapter);// sets the adapter for the listview
        } else {
            Toast.makeText(ctc, result, Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * Adds the Faq in the custom list adapter for the faq listview
     * @param values
     */
    @Override
    protected void onProgressUpdate(Faq... values) {
        Log.d("BG", "On progress update");
        faqListAdapter.add(values[0]);//adds the checklist being passed as parameter
        Log.d("BG", "populating");
    }

}