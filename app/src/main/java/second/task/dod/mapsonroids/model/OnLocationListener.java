package second.task.dod.mapsonroids.model;

import android.location.Location;

/**
 * Created by noiser on 19.06.15.
 */
public interface OnLocationListener {

    void onLocationSuccess(Location location);

    void onLocationFailed();
}
