package second.task.dod.mapsonroids.view;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by noiser on 19.06.15.
 */
public interface MainView {

    void showMarker(MarkerOptions marker);

    void showInformation(String information);

    void showProgressBar();

    void hideProgressBar();
}
