package second.task.dod.mapsonroids.service;

import android.location.Location;
import android.os.AsyncTask;

import second.task.dod.mapsonroids.model.OnLocationListener;

/**
 * Created by noiser on 20.06.15.
 */
public class LocationAsyncTask extends AsyncTask<String, Void, Location> {


    LocationService locationService;
    OnLocationListener onLocationListener;

    public LocationAsyncTask(LocationService locationService, OnLocationListener onLocationListener) {
        this.locationService = locationService;
        this.onLocationListener = onLocationListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        locationService.requestUpdates();
    }

    @Override
    protected Location doInBackground(String... params) {
        Location location = locationService.getLocation();
        return location;
    }

    @Override
    protected void onPostExecute(Location location) {
        super.onPostExecute(location);
        locationService.removeUpdates();
        if (location != null) {
            onLocationListener.onLocationSuccess(location);
        } else {
            onLocationListener.onLocationFailed();
        }
    }
}
