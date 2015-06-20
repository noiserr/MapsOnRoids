package second.task.dod.mapsonroids.service;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by noiser on 19.06.15.
 */
public class LocationService implements LocationListener {

    String privider;
    private boolean canGetLocation;
    Criteria criteria;
    Location location;
    LocationManager locationManager;
    Context context;

    boolean isGPSEnabled;

    boolean isNetworkEnabled;

    public LocationService(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("LOCATION", "onLocationChanged");
        Log.d("LOCATION", location.getLatitude() + " " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("LOCATION", "onStatusChanged");


    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("LOCATION", "onProviderEnabled");


    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("LOCATION", "onProviderDisabled");


    }

    public void requestUpdates() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    100,
                    100f, this);
        }
        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    100,
                    100f, this);
        }
    }

    public Location getLocation() {
        try {

            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {

                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public void removeUpdates() {
        locationManager.removeUpdates(this);
    }
}
