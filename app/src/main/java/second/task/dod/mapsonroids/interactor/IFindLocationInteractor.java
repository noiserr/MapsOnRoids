package second.task.dod.mapsonroids.interactor;

import android.content.Context;

/**
 * Created by noiser on 19.06.15.
 */
public interface IFindLocationInteractor {

    void getCurrentLocation(Context context, OnLocationListener locationListener);
}
