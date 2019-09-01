package com.noor.payment.ui.main;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        return new MainViewModel(dataManager,mRestManager,mSchedulersFacade,mToastLiveData,mCompositeDisposable);
    }
}
