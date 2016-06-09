package rktechltd.aucklandfishing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import rktechltd.aucklandfishing.db.backgroundTasks.NetRulesBackgroundTask;
import rktechltd.aucklandfishing.db.backgroundTasks.XPBackgroundTask;
/**
 * A class that handles the View Fishing Expereince activity
 * Back code for the  View Fishing Expereince activity
 * @author ROmelyn Ungab
 * @Author Katrina Salceda
 */
public class xpViewActivity extends AppCompatActivity {
    private static Context context;
    private ListView listView;
    private XPBackgroundTask xpBackgroundTask;

    /**
     * On creatting the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_xp);
        xpViewActivity.context=getApplicationContext();
        xpBackgroundTask = new XPBackgroundTask(this);
        xpBackgroundTask.execute("R");
        Log.d("BG onCreate",xpBackgroundTask.toString());

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)this.findViewById(R.id.lvXP);
        listView.setOnItemClickListener(new XPClick());
    }
    /**
     * creating the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Selecting a menu item
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.action_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.action_credits:
                intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                break;

            case R.id.action_exit:
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     *gets the current context of the XPVIEW activity
     * @return
     */
    public static Context getAppContext() {
        return xpViewActivity.context;
    }
    /**
     * Loads the List of Fish Catch when clicking on the item in the listview
     */
   public class XPClick implements AdapterView.OnItemClickListener{
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ViewGroup vg =(ViewGroup)view;
            TextView txid =(TextView)vg.findViewById(R.id.tvXPId);
            Intent intent = new Intent(xpViewActivity.this,ViewFishCatchActivity.class);
           intent.putExtra("XPId",txid.getText().toString());
           startActivity(intent);
       }
   }



}
