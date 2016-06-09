package rktechltd.aucklandfishing;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import rktechltd.aucklandfishing.db.backgroundTasks.XPBackgroundTask;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */

public class MyFishingXPActivity extends AppCompatActivity {
    private XPBackgroundTask xpBackgroundTask;
    private EditText locationName;

    private EditText tflat;
    private EditText tflong;

    private LocationManager glocManager;
    private LocationListener glocListener;

    private Button saveXP;
    private ImageButton getLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fishing_xp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //All textView
        tflat = (EditText) findViewById(R.id.tflat);
        tflong = (EditText) findViewById(R.id.tflong);
        locationName = (EditText) findViewById(R.id.tfLocationName);
        glocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        glocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("PASOK",location.toString());
                if(location!=null) {
                    tflat.setText("" + location.getLatitude());
                    tflong.setText("" + location.getLongitude());
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };


        getLocation = (ImageButton) findViewById(R.id.buttonGetLoc);
        getLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("CONF",getLocation.toString());
                glocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, glocListener);
            }
        });
        Log.d("BUTTON",getLocation.toString());



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Log.d("Build",Build.VERSION.SDK_INT+"");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET}, 10);

                return;
            }
        }else{
            configureButton();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 10:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configureButton();
                         return;
        }
    }

    private void configureButton(){

        getLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("CONF",getLocation.toString());
                glocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, glocListener);
            }
        });

    }


    public void buttonSaveXP(View v){
        String location = locationName.getText().toString();
        String latitude =  tflat.getText().toString();
        String longitude = tflong.getText().toString();
        Log.d("SAVING","FISHING EXP");
        xpBackgroundTask = new XPBackgroundTask(this);
        xpBackgroundTask.execute("I",location,latitude,longitude);
        //saveXP.isEnabled(false);
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

    public void tvAddCatch(View v) {

        DialogActivity dialog = new DialogActivity();
         dialog.show(getFragmentManager(), "my_dialog");
    }
}
