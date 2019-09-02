package com.eram.manager.di.module;

import android.app.Application;
import android.content.Context;

import com.eram.manager.api.AppRestManager;
import com.eram.manager.api.ICallApi;
import com.eram.manager.api.RestManager;
import com.eram.manager.data.AppDataManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.pref.AppPreferencesHelper;
import com.eram.manager.data.pref.PreferencesHelper;
import com.eram.manager.di.PreferenceInfo;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.rx.SingleLiveData;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by behnaz on 10/29/2017.
 */

@Module
public class AppModule implements AppConstants {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ICallApi provideICallApi(Retrofit retrofit) {
        return retrofit.create(ICallApi.class);
    }

    @Singleton
    @Provides
    static RestManager provideRestManager(ICallApi iCallApi ) {
     return new AppRestManager(iCallApi);
    }

    @Provides
    static CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    static SingleLiveData<Integer> provideIntegerSingleLiveData(){
        return new SingleLiveData<>();
    }


}
