package com.happy.customview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by showhome002 on 2017/11/30.
 */

public class MyService extends Service {

    private static final String TAG = "Jiale";

    @Override
    public void onCreate() {
        Log.i(TAG,"MyService is oncreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MyProcessActivity is created: ");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG , "OnDestory");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
