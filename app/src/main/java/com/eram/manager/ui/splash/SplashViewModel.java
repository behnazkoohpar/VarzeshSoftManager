package com.eram.manager.ui.splash;

import android.app.Activity;
import android.content.pm.PackageInfo;

import com.eram.manager.R;
import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.ui.base.BaseViewModel;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behnaz on 10/29/2017.
 */

public class SplashViewModel extends BaseViewModel implements AppConstants {


    public SplashViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }
}
