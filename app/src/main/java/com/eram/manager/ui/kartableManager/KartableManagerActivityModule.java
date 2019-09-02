package com.eram.manager.ui.kartableManager;

import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class KartableManagerActivityModule {

    @Provides
    KartableManagerViewModel provideKartableManagerViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        return new KartableManagerViewModel(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }
}
