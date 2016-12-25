package com.pachouri.transportsolution;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.accountkit.AccountKit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karumi.dexter.Dexter;

import java.lang.reflect.Modifier;

/**
 * Created by ankit on 12/24/16.
 */
public class BaseApplication extends Application {
    private Gson gson;
    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize(getApplicationContext());
        ActiveAndroid.initialize(this);
        Dexter.initialize(this);
    }
    public Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier
                    .STATIC);
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            gsonBuilder.serializeNulls();
            gson = gsonBuilder.create();
        }

        return gson;
    }

}
