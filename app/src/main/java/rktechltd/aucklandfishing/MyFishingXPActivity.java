package rktechltd.aucklandfishing;
<<<<<<< HEAD
=======

>>>>>>> origin/master
import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< HEAD
=======

>>>>>>> origin/master
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
<<<<<<< HEAD
=======
import android.support.annotation.NonNull;
>>>>>>> origin/master
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD
import com.google.android.gms.location.LocationListener;
=======


>>>>>>> origin/master
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
<<<<<<< HEAD
        textViewNetLat = (TextView) findViewById(R.id.textViewNetLat);
        textViewNetLng = (TextView) findViewById(R.id.textViewNetLng);
        textViewGpsLat = (TextView) findViewById(R.id.textViewGpsLat);
        textViewGpsLng = (TextView) findViewById(R.id.textViewGpsLng);
        locationName = (EditText) findViewById(R.id.tfLocationName);
    }

    @Override
    public void onDestroy() {

        //Remove GPS location update
        if (glocManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
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

    public void buttonSaveXP(View v) {
        String location = locationName.getText().toString();
        String latitude = textViewGpsLat.getText().toString();
        String longitude = textViewGpsLng.getText().toString();
        Log.d("SAVING", "FISHING EXP");
        xpBackgroundTask = new XPBackgroundTask(this);
        xpBackgroundTask.execute("I", location, latitude, latitude);
        //saveXP.isEnabled(false);
    }

    //This is for Lat lng which is determine by your wireless or mobile network
    public class MyLocationListenerNetWork implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            nlat = loc.getLatitude();
            nlng = loc.getLongitude();
=======
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
>>>>>>> origin/master

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

<<<<<<< HEAD

        public void onProviderDisabled(String provider) {
            Log.d("LOG", "Network is OFF!");
        }

        public void onProviderEnabled(String provider) {
            Log.d("LOG", "Thanks for enabling Network !");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    //This is for Lat lng which is determine by your device GPS
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


        public void onProviderDisabled(String provider) {
            Log.d("LOG", "GPS is OFF!");
        }


        public void onProviderEnabled(String provider) {
            Log.d("LOG", "Thanks for enabling GPS !");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
=======
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 10:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configureButton();
                         return;
>>>>>>> origin/master
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

<<<<<<< HEAD
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
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            nlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 1, 0, (android.location.LocationListener) nlocListener);


            glocManager  = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            glocListener = new MyLocationListenerGPS();
            glocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 1, 0, (android.location.LocationListener) glocListener);
        }
    }

=======
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



>>>>>>> origin/master
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
