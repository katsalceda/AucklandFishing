package rktechltd.aucklandfishing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import rktechltd.aucklandfishing.db.backgroundTasks.FCatchBackgroundTask;

/**
 * Created by romelyn on 9/06/2016.
 */
public class ViewFishCatchActivity extends AppCompatActivity {

    private ListView listView;
    private FCatchBackgroundTask fcBackgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fishcatch);
        Intent intent = this.getIntent();
        int fxid = Integer.parseInt(intent.getExtras().getString("XPId"));
        fcBackgroundTask = new FCatchBackgroundTask(this);
        fcBackgroundTask.execute("R",""+fxid);
        Log.d("BG onCreate",fcBackgroundTask.toString());

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)this.findViewById(R.id.listViewCatch);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

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

}
