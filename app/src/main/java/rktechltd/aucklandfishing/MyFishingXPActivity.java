package rktechltd.aucklandfishing;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;

import rktechltd.aucklandfishing.db.backgroundTasks.XPBackgroundTask;

/**
 * A class that handles the fishing experience activity
 * Back code for the fishing experience activity
 * @author ROmelyn Ungab
 * @Author Katrina Salceda
 */
public class MyFishingXPActivity extends AppCompatActivity {
    private XPBackgroundTask xpBackgroundTask;
    private EditText locationName;

    double nlat;
    double nlng;
    double glat;
    double glng;

    LocationManager glocManager;
    MyLocationListenerGPS glocListener;
    LocationManager nlocManager;
    MyLocationListenerNetWork nlocListener;

    TextView textViewNetLat;
    TextView textViewNetLng;
    TextView textViewGpsLat;
    TextView textViewGpsLng;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button saveXP;
    /**
     * On creatting the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fishing_xp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //All textView
        textViewNetLat = (TextView) findViewById(R.id.textViewNetLat);
        textViewNetLng = (TextView) findViewById(R.id.textViewNetLng);
        textViewGpsLat = (TextView) findViewById(R.id.textViewGpsLat);
        textViewGpsLng = (TextView) findViewById(R.id.textViewGpsLng);
        locationName = (EditText) findViewById(R.id.tfLocationName);
    }

    /**
     * Removes the location update
     */
    @Override
    public void onDestroy() {

        //Remove GPS location update
        if (glocManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                 return;
            }
            glocManager.removeUpdates((android.location.LocationListener) glocListener);
            Log.d("ServiceForLatLng", "GPS Update Released");
        }

        //Remove Network location update
        if (nlocManager != null) {
            nlocManager.removeUpdates((android.location.LocationListener) nlocListener);
            Log.d("ServiceForLatLng", "Network Update Released");
        }
        super.onDestroy();
    }

    /**
     * Saving new Fishing Experience
     * @param v
     */
    public void buttonSaveXP(View v) {
        String location = locationName.getText().toString();
        String latitude = textViewGpsLat.getText().toString();
        String longitude = textViewGpsLng.getText().toString();
        Log.d("SAVING", "FISHING EXP");
        xpBackgroundTask = new XPBackgroundTask(this);
        xpBackgroundTask.execute("I", location, latitude, longitude);
    }

    /**
     * This is for Lat lng which is determine by your wireless or mobile network
     * @author  ROMELYN UNGAB
     * @author KATRINA SALCEDA
     */
     public class MyLocationListenerNetWork implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            nlat = loc.getLatitude();
            nlng = loc.getLongitude();

            //Setting the Network Lat, Lng into the textView
            textViewNetLat.setText("Network Latitude:  " + nlat);
            textViewNetLng.setText("Network Longitude:  " + nlng);

            Log.d("LAT & LNG Network:", nlat + " " + nlng);
        }


    }

    /**
     *This is for Lat long which is determine by your device GPS
     * @author ROMELYN UNGA
     * @author KATRINA SALCEDAs
     */
       public class MyLocationListenerGPS implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            glat = loc.getLatitude();
            glng = loc.getLongitude();

            //Setting the GPS Lat, Lng into the textView
            textViewGpsLat.setText("GPS Latitude:  " + glat);
            textViewGpsLng.setText("GPS Longitude:  " + glng);

            Log.d("LAT & LNG GPS:", glat + " " + glng);
        }


    }

    /**
     * Shows the current location
     * @param v
     */
    public void showLoc(View v) {

        //Location access ON or OFF checking
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
        boolean networkWifiStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.NETWORK_PROVIDER);

        //If GPS and Network location is not accessible show an alert and ask user to enable both
        if (!gpsStatus || !networkWifiStatus) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyFishingXPActivity.this);

            alertDialog.setTitle("Make your location accessible ...");
            alertDialog.setMessage("Your Location is not accessible to us.To show location you have to enable it.");
            alertDialog.setIcon(R.drawable.warning);

            alertDialog.setNegativeButton("Enable", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                }
            });

            alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Remember to show location you have to enable it !", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            alertDialog.show();
        }
        //IF GPS and Network location is accessible
        else {
            nlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            nlocListener = new MyLocationListenerNetWork();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
            }
            nlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 1, 0, (android.location.LocationListener) nlocListener);


            glocManager  = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            glocListener = new MyLocationListenerGPS();
            glocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 1, 0, (android.location.LocationListener) glocListener);
        }
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
     * Loading the dialog to add fish catch
     * @param v
     */
    public void tvAddCatch(View v) {
        DialogActivity dialog = new DialogActivity();
        //dialog.getActivity().
        dialog.show(getFragmentManager(), "my_dialog");
    }
}
