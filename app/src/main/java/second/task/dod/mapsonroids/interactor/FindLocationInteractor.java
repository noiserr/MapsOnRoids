package second.task.dod.mapsonroids.interactor;

import android.content.Context;


import second.task.dod.mapsonroids.service.LocationService;
import second.task.dod.mapsonroids.service.LocationAsyncTask;

/**
 * Created by noiser on 19.06.15.
 */
public class FindLocationInteractor implements IFindLocationInteractor {


    @Override
    public void getCurrentLocation(final Context context, final OnLocationListener locationListener) {
                new LocationAsyncTask(new LocationService(context), locationListener).execute();
    }


}
