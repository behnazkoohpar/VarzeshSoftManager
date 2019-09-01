package com.noor.payment.ui.docContractors;

import com.noor.payment.api.ICallApi;
import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.ui.base.BaseViewModel;
import com.noor.payment.utils.AppConstants;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.RequestBody;

public class DocContractorsViewModel extends BaseViewModel implements AppConstants {

    public DocContractorsViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

}

