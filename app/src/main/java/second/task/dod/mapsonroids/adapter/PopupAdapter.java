package second.task.dod.mapsonroids.adapter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.List;

import second.task.dod.mapsonroids.R;

/**
 * Created by noiser on 20.06.15.
 */
public class PopupAdapter implements GoogleMap.InfoWindowAdapter {


    LayoutInflater inflater;
    Geocoder geocoder;

    public PopupAdapter(LayoutInflater inflater) {
        this.inflater = inflater;

    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {

        View v = inflater.inflate(R.layout.info_window, null);
        geocoder = new Geocoder(inflater.getContext());
        LatLng latLng = arg0.getPosition();
        List<Address> addressList = null;
        try {
            addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = addressList.get(0);

        TextView country = (TextView) v.findViewById(R.id.tv_country);

        TextView city = (TextView) v.findViewById(R.id.tv_city);

        TextView street = (TextView) v.findViewById(R.id.tv_address);

        country.setText(address.getCountryName());

        city.setText(address.getAddressLine(1));

        street.setText(address.getAddressLine(0));


        return v;

    }
}
