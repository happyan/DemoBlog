package com.happy.customview;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by showhome002 on 2017/11/30.
 */

public class App extends Application {
   private final String TAG = "JiaLe";
    @Override
    public void onCreate() {
        super.onCreate();
        int pid =android.os.Process.myPid();
        Log.i(TAG,"myApp pid ===  " + pid);

        String processName = "";
        ActivityManager activityManager = (ActivityManager) this.getSystemService(getApplicationContext().ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcessInfo :activityManager.getRunningAppProcesses()){
            if (appProcessInfo.pid == pid) {
                processName = appProcessInfo.processName;
            }
        }
        if("com.happy.myprogress".equals(processName)){
            Log.i(TAG, "processName="+processName+"-----"+1);
        }else{
            Log.i(TAG, "processName="+processName+"-----"+2);
        }
    }
}
