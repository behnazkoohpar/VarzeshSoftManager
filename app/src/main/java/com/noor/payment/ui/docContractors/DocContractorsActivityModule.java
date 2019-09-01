package com.noor.payment.ui.docContractors;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class DocContractorsActivityModule {

    @Provides
    DocContractorsViewModel provideForgotPasswordViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        return new DocContractorsViewModel(dataManager,mRestManager,mSchedulersFacade,mToastLiveData,mCompositeDisposable);
    }

}

