package second.task.dod.mapsonroids.model;

import android.content.Context;

/**
 * Created by noiser on 19.06.15.
 */
public interface FindLocationInteractor {

    void getCurrentLocation(Context context, OnLocationListener locationListener);
}
