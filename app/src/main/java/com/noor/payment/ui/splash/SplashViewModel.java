package com.noor.payment.ui.splash;

import android.app.Activity;
import android.content.pm.PackageInfo;

import com.noor.payment.R;
import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.ui.base.BaseViewModel;
import com.noor.payment.utils.AppConstants;
import com.noor.payment.utils.CommonUtils;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behnaz on 10/29/2017.
 */

public class SplashViewModel extends BaseViewModel implements AppConstants {


    public SplashViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }
}
