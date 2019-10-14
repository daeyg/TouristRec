package com.r4sh33d.tourister;

import android.app.Application;

import timber.log.Timber;

public class TouristerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
