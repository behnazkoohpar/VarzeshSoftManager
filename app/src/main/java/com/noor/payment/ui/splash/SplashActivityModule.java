package com.noor.payment.ui.splash;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behnaz on 11/1/17.
 */
@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        return new SplashViewModel(dataManager,mRestManager,mSchedulersFacade,mToastLiveData,mCompositeDisposable);
    }
}
