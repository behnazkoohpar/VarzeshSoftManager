package com.eram.manager.api;

import com.eram.manager.data.model.api.AllReport;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.base.BaseResponse;
import com.eram.manager.data.model.api.ServerGym;
import com.eram.manager.data.model.api.TokenResponse;
import com.eram.manager.data.model.api.base.Data;
import com.eram.manager.ui.login.LoginRequestBody;

import java.util.List;

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
    public Observable<TokenResponse> loginUser(String url,LoginRequestBody loginRequestBody) {
        return iCallApi.loginUser(url,loginRequestBody);
    }

    @Override
    public Observable<Data<ServerGym>> serverLogin(String url) {
        return iCallApi.serverLogin(url);
    }

    @Override
    public Observable<AllReport> allReport(String baseUrl) {
        return iCallApi.allReport(baseUrl);
    }

    @Override
    public Observable<OrganizationUnit> getOrganizatonUnit(String baseUrl) {
        return iCallApi.getOrganizatonUnit(baseUrl);
    }
}
