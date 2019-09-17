package com.eram.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.multidex.MultiDex;

import com.eram.manager.di.component.DaggerAppComponent;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by behnaz on 10/29/2017.
 */

public class App extends Application implements HasActivityInjector {

    public static Context context;
    public static SharedPreferences preferences;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences(AppConstants.PREF_NAME, MODE_PRIVATE);
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        AppLogger.init();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
