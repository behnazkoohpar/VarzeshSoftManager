package com.noor.payment.ui.login;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behnaz on 11/3/17.
 */
@Module
public class LoginActivityModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        return new LoginViewModel(dataManager,mRestManager,mSchedulersFacade,mToastLiveData,mCompositeDisposable);
    }

}
