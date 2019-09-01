package com.noor.payment.ui.docMember;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.ui.base.BaseViewModel;
import com.noor.payment.utils.AppConstants;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;

public class DocMemberViewModel extends BaseViewModel implements AppConstants {

    public DocMemberViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }


}
