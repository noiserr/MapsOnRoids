package second.task.dod.mapsonroids;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import second.task.dod.mapsonroids.adapter.PopupAdapter;
import second.task.dod.mapsonroids.presenter.IMainPresenter;
import second.task.dod.mapsonroids.presenter.MainPresenter;
import second.task.dod.mapsonroids.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.location_fab)
    FloatingActionButton gpsButton;
    @InjectView(R.id.progressBar_gps)
    ProgressBar progressBar;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    GoogleMap map;
    Marker marker = null;
    private IMainPresenter IMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        setupMap();
        IMainPresenter = new MainPresenter(getApplicationContext(), this);

    }

    public void setupMap() {
        MapFragment fragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
        fragment.setRetainInstance(true);
        map = fragment.getMap();
        map.getUiSettings().setMapToolbarEnabled(false);
        map.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
    }


    public void findLocation(View view) {
        map.clear();
        showProgressBar();
        gpsButton.setEnabled(false);
        IMainPresenter.getLocation();
    }

    @Override
    public void showMarker(MarkerOptions marker) {
        gpsButton.setEnabled(true);
        map.addMarker(marker);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(marker.getPosition().latitude, marker.getPosition().longitude), 15));
    }

    @Override
    public void showInformation(String information) {
        gpsButton.setEnabled(true);
        Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        gpsButton.setEnabled(false);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        gpsButton.setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
