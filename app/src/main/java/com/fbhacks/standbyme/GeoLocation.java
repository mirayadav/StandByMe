package com.fbhacks.standbyme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.facebook.FacebookCallback;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.messenger.MessengerUtils;
import com.facebook.messenger.MessengerThreadParams;
import com.facebook.messenger.ShareToMessengerParams;

public class GeoLocation extends Activity {

    private LocationManager locationManager = null;
    private LocationListener locationListener = null;
    String locationProvider = LocationManager.GPS_PROVIDER;
    public static final int MY_PERMISSIONS_REQUEST_COARSE_LOC_PERM = 2;
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOC_PERM = 3;

    private Button btnGetLocation = null;
    private EditText editLocation = null;
    private ProgressBar pb = null;

    private static final String TAG = "---------------- Debug";
    private Boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_location);

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Log.d(TAG, "Starting Location");
        // Or use LocationManager.GPS_PROVIDER

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_COARSE_LOC_PERM);
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOC_PERM);
            Log.d(TAG, "No Permission");
            return;
        }


        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        sendLocation();
    }

    public Location sendLocation() throws SecurityException{
        // android.location.Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        android.location.Location lastKnownLocation = getLocation();
        String location = "";
        if (lastKnownLocation != null) {
            location = "http://www.google.com/maps/place/" + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude();
            Log.d(TAG, location);

            // Pull up messenger
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(location))
                    .build();
            /*SendButton sendButton = (SendButton)findViewById(R.id.fb_send_button);
            sendButton.setShareContent(content);
            sendButton.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() { ... });
*
            /*SendButton sendButton = (SendButton)findViewById(R.id.fb_send_button);
            sendButton.setShareContent(content);
            sendButton.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() { ... });*/

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent
                    .putExtra(Intent.EXTRA_TEXT, location);
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.facebook.orca");

            // -----------------
            try {
                startActivity(sendIntent);
            }
            catch (android.content.ActivityNotFoundException ex) {
                Log.d(TAG, "Couldn't send message!");
            }
        } else {
            Log.d(TAG, "No location found");
        }
        return lastKnownLocation;
    }

    public Location getLocation() throws SecurityException {
        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        long GPSLocationTime = 0;
        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {
            return locationGPS;
        }
        else {
            return locationNet;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_COARSE_LOC_PERM: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        sendLocation();
                    }

                } else {
                    Log.d(TAG, "Permission denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_FINE_LOC_PERM: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        sendLocation();
                    }

                } else {
                    Log.d(TAG, "Permission denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



}
