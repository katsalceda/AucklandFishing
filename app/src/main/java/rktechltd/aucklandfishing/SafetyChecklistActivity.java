package rktechltd.aucklandfishing;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import rktechltd.aucklandfishing.db.backgroundTasks.ChecklistBackgroundTask;

/**
 * A class that handles the checklist activity
 * Back code for the checklist activity
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */

public class SafetyChecklistActivity extends AppCompatActivity {
    private ListView listView ;
    private ChecklistBackgroundTask checklistBgt;
    /**
     * On creatting the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_checklist);

        checklistBgt = new ChecklistBackgroundTask(this);
        Log.d("CL BACK",checklistBgt.toString());
        checklistBgt.execute("R");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.listViewSafetyChecklist);
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
}
