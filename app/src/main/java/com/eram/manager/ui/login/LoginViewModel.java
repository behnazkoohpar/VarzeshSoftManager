package com.eram.manager.ui.login;

import androidx.lifecycle.MutableLiveData;

import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.base.BaseResponse;
import com.eram.manager.data.model.api.ServerGym;
import com.eram.manager.data.model.api.TokenResponse;
import com.eram.manager.data.model.api.base.Data;
import com.eram.manager.di.module.RxRetrofitErrorConsumer;
import com.eram.manager.ui.base.BaseViewModel;
import com.eram.manager.utils.AppBaseUrl;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by behnaz on 11/3/17.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> implements AppConstants {

    private final MutableLiveData<TokenResponse> tokenResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<Data<ServerGym>> serverLoginResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<TokenResponse> getTokenResponseModelMutableLiveData() {
        return tokenResponseModelMutableLiveData;
    }
    public MutableLiveData<Data<ServerGym>> getServerLoginResponseModelMutableLiveData() {
        return serverLoginResponseModelMutableLiveData;
    }

    public LoginViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    public void onCallSendTelNumber() {
        getNavigator().login();
    }

    void login(String password, String userName) {
        Disposable disposable = mRestManager.loginUser(AppBaseUrl.BASE_URL+AppBaseUrl.LOGIN,new LoginRequestBody(userName, password))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    tokenResponseModelMutableLiveData.setValue(r);
                    Timber.i("data login : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in login view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    public void serverLogin() {
        Disposable disposable = mRestManager.serverLogin(AppBaseUrl.SERVER_URL)
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    serverLoginResponseModelMutableLiveData.setValue(r);
                    Timber.i("data serverLogin : " + r.toString());
                    Timber.d("result response : " + r.getSettings().getSuccess());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in serverLogin view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }
}
