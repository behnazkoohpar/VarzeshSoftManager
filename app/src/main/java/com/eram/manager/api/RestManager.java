package com.eram.manager.api;

import com.eram.manager.data.model.api.AllReport;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.base.BaseResponse;
import com.eram.manager.data.model.api.ServerGym;
import com.eram.manager.data.model.api.TokenResponse;
import com.eram.manager.data.model.api.base.Data;
import com.eram.manager.ui.login.LoginRequestBody;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface RestManager {

    Observable<TokenResponse> loginUser(String url,LoginRequestBody loginRequestBody);

    Observable<Data<ServerGym>>  serverLogin(String url);

    Observable<AllReport> allReport(String baseUrl);

    Observable<OrganizationUnit> getOrganizatonUnit(String baseUrl);
}
