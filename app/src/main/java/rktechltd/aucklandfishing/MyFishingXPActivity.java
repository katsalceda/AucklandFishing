package rktechltd.aucklandfishing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyFishingXPActivity extends AppCompatActivity {

    private Button buttonGetLocation;
    private EditText spaceLatitude, spaceLongitude;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fishing_xp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

        /** buttonGetLocation = (Button) findViewById(R.id.GetLocationButton);
        spaceLatitude = (EditText) findViewById(R.id.latitudeSpace);
        spaceLongitude = (EditText) findViewById(R.id.longitudeSpace);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                spaceLatitude.append("\n " + location.getLatitude() + " ");
                spaceLongitude.append("\n " + location.getLongitude() + " ");
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET
            }, 10);
            return;
        }
        else {
            configureButton();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;

        }
    }

    private void configureButton() {
        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
            }
        });
    } */


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

    public void GetLocationButton(View v) {
         spaceLatitude = (EditText) findViewById(R.id.tfLatitude);
         spaceLongitude = (EditText) findViewById(R.id.tfLongitude);

         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
         locationListener = new LocationListener() {

             @Override
            public void onLocationChanged(Location location) {
            spaceLatitude.append("\n " + location.getLatitude() + " ");
                 spaceLongitude.append("\n " + location.getLongitude() + " ");
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
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         requestPermissions(new String[]{
         Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
         Manifest.permission.INTERNET
         }, 10);
         return;
         }
         else {
         configureButton();
         }

         }

         @Override
         public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
         switch (requestCode) {
         case 10:
         if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
         configureButton();
         return;

         }
         }

         private void configureButton() {
         buttonGetLocation.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
         locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
         }
         });
         }
}
