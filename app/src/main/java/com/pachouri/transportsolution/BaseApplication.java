package com.pachouri.transportsolution;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.accountkit.AccountKit;

/**
 * Created by ankit on 12/24/16.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize(getApplicationContext());
        ActiveAndroid.initialize(this);
    }
}
