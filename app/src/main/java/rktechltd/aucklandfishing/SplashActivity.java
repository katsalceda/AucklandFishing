package rktechltd.aucklandfishing;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;

public class SplashActivity extends AppCompatActivity {
     private InitializeDatabase initdb;
    private static Context context;
    /** Waiting time **/
    private final int SPLASH_DISPLAY_LENGTH = 10000;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashActivity.context = getApplicationContext();
        initdb = new InitializeDatabase(context);
      initdb.execute();
    }

    public static Context getAppContext() {
        return SplashActivity.context;
    }
    public class InitializeDatabase extends AsyncTask<Void,Void,Void> {
        private Context context;
        private AucklandFishingDBHelper db;
        public InitializeDatabase(Context context){
            this.context = context;
        }

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

        @Override
        protected void onPreExecute() {
          super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }


}