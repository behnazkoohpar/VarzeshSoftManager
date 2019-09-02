package com.eram.manager.ui.docContractors;

import com.eram.manager.api.ICallApi;
import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.ui.base.BaseViewModel;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.RequestBody;

public class DocContractorsViewModel extends BaseViewModel implements AppConstants {

    public DocContractorsViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

}

