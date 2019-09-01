package com.noor.payment.ui.kartableManager;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

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
