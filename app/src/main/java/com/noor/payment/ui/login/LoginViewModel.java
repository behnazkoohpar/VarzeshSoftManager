package com.noor.payment.ui.login;

import android.arch.lifecycle.MutableLiveData;

import com.noor.payment.api.RestManager;
import com.noor.payment.data.DataManager;
import com.noor.payment.data.model.api.BaseResponse;
import com.noor.payment.data.model.api.TokenResponse;
import com.noor.payment.di.module.RxRetrofitErrorConsumer;
import com.noor.payment.ui.base.BaseViewModel;
import com.noor.payment.utils.AppConstants;
import com.noor.payment.utils.rx.SchedulersFacade;
import com.noor.payment.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by behnaz on 11/3/17.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> implements AppConstants {

    private final MutableLiveData<BaseResponse<TokenResponse>> tokenResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<BaseResponse<TokenResponse>> getTokenResponseModelMutableLiveData() {
        return tokenResponseModelMutableLiveData;
    }

    public LoginViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    public void onCallSendTelNumber() {
        getNavigator().login();
    }

    void login(String password, String userName, String applicationCode) {
        Disposable disposable = mRestManager.loginUser(new LoginRequestBody(userName, password, applicationCode))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    tokenResponseModelMutableLiveData.setValue(r);
                    Timber.i("data login : " + r.toString());
                    Timber.d("result response : " + r.getErrorDetails().getErrorCode());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in login view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

}
