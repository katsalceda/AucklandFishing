package rktechltd.aucklandfishing;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */


public class SplashActivity extends AppCompatActivity {
     private InitializeDatabase initdb;
    private static Context context;
    /** Waiting time **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    /**
     * On creating the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashActivity.context = getApplicationContext();
        initdb = new InitializeDatabase(context);
      initdb.execute();
    }

    /**
     *gets the current context of the splash activity
     * @return
     */
    public static Context getAppContext() {
        return SplashActivity.context;
    }

    /**
     * A class that initializes the database using AsyncTask
     */
    public class InitializeDatabase extends AsyncTask<Void,Void,Void> {
        private Context context;
        private AucklandFishingDBHelper db;
        public InitializeDatabase(Context context){
            this.context = context;
        }

        /**
         * Displays the splash while initializing the database
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            db = new AucklandFishingDBHelper(SplashActivity.getAppContext());
            try {
                Thread.sleep(SPLASH_DISPLAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Executes before the thread starts
         */
        @Override
        protected void onPreExecute() {
          super.onPreExecute();
        }

        /**
         * Executes when the thread ends
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //starts the main activity when the thread ends and finishes the splash activity
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}