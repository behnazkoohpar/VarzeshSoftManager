package com.eram.manager.di.component;

import android.app.Application;

import com.eram.manager.App;
import com.eram.manager.di.builder.ActivityBuilder;
import com.eram.manager.di.module.AppModule;
import com.eram.manager.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by behnaz on 10/29/2017.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class , AppModule.class, ActivityBuilder.class ,  NetworkModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(App app);

}
