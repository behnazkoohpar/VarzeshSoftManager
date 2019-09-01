package com.noor.payment.api;

import com.noor.payment.data.model.api.BaseResponse;
import com.noor.payment.data.model.api.TokenResponse;
import com.noor.payment.ui.login.LoginRequestBody;

import io.reactivex.Observable;

public interface RestManager {

    Observable<BaseResponse<TokenResponse>> loginUser(LoginRequestBody loginRequestBody);

}
