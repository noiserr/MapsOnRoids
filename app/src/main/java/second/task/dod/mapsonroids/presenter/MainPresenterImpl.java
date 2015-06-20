package second.task.dod.mapsonroids.presenter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import second.task.dod.mapsonroids.R;
import second.task.dod.mapsonroids.model.FindLocationInteractor;
import second.task.dod.mapsonroids.model.FindLocationInteractroImpl;
import second.task.dod.mapsonroids.model.OnLocationListener;
import second.task.dod.mapsonroids.view.MainView;

/**
 * Created by noiser on 19.06.15.
 */
public class MainPresenterImpl implements MainPresenter, OnLocationListener {


    private MainView mainView;
    private Context context;
    private FindLocationInteractor interactor;

    public MainPresenterImpl(Context context, MainView mainView) {
        this.mainView = mainView;
        this.context = context;
        interactor = new FindLocationInteractroImpl();
    }



    @Override
    public void getLocation() {
        interactor.getCurrentLocation(context, this);
    }

    @Override
    public void onLocationSuccess(Location location) {
        double lon = location.getLongitude();
        double lat = location.getLatitude();
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lon))
                .title(addresses.get(0).getLocality());

        mainView.showMarker(marker);
        mainView.hideProgressBar();
    }

    @Override
    public void onLocationFailed() {
        String info = context.getResources().getString(R.string.no_gps_message);
        mainView.showInformation(info);
        mainView.hideProgressBar();

    }
}
