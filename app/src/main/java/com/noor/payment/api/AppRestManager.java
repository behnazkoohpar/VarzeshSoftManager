package com.noor.payment.api;

import com.noor.payment.data.model.api.BaseResponse;
import com.noor.payment.data.model.api.TokenResponse;
import com.noor.payment.ui.login.LoginRequestBody;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppRestManager implements RestManager {

    @SuppressWarnings("WeakerAccess")
    @Inject
    public AppRestManager(ICallApi iCallApi) {
        this.iCallApi = iCallApi;
    }

//    @SuppressWarnings("WeakerAccess")
//    @Inject
    ICallApi iCallApi;

    @Override
    public Observable<BaseResponse<TokenResponse>> loginUser(LoginRequestBody loginRequestBody) {
        return iCallApi.loginUser(loginRequestBody);
    }
}
