package com.eram.manager.api;

import com.eram.manager.data.model.api.AllReport;
import com.eram.manager.data.model.api.CreditorAmount;
import com.eram.manager.data.model.api.DebtorAmount;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.PoolReception;
import com.eram.manager.data.model.api.PoolReceptionLimit;
import com.eram.manager.data.model.api.PoolReceptionStatus;
import com.eram.manager.data.model.api.ServerGym;
import com.eram.manager.data.model.api.SumPrice;
import com.eram.manager.data.model.api.TokenResponse;
import com.eram.manager.data.model.api.base.Data;
import com.eram.manager.ui.login.LoginRequestBody;
import com.eram.manager.ui.stateReception.PoolReceptionDayRequestBody;
import com.eram.manager.ui.stateReception.PoolReceptionLimitRequestBody;
import com.eram.manager.ui.stateReception.PoolReceptionStatusRequestBody;

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

    @Override
    public Observable<PoolReception> getPoolReceptionDay(String baseUrl, PoolReceptionDayRequestBody poolReceptionDayRequestBody) {
        return iCallApi.getPoolReceptionDay(baseUrl,poolReceptionDayRequestBody);
    }

    @Override
    public Observable<PoolReceptionLimit> getPoolReceptionLimit(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody) {
        return iCallApi.getPoolReceptionLimit(baseUrl,poolReceptionLimitRequestBody);
    }

    @Override
    public Observable<PoolReceptionStatus> getPoolReceptionStatus(String baseUrl, PoolReceptionStatusRequestBody poolReceptionStatusRequestBody) {
        return iCallApi.getPoolReceptionStatus(baseUrl,poolReceptionStatusRequestBody);
    }

    @Override
    public Observable<DebtorAmount> getDebtorAmountToday(String baseUrl, PoolReceptionStatusRequestBody poolReceptionStatusRequestBody) {
        return iCallApi.getDebtorAmountToday(baseUrl,poolReceptionStatusRequestBody);
    }

    @Override
    public Observable<DebtorAmount> getDebtorAmountLimit(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody) {
        return iCallApi.getDebtorAmountLimit(baseUrl,poolReceptionLimitRequestBody);
    }

    @Override
    public Observable<SumPrice> getSumPriceReciept(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody) {
        return iCallApi.getSumPriceReciept(baseUrl,poolReceptionLimitRequestBody);
    }

    @Override
    public Observable<CreditorAmount> getCreditorAmountToday(String baseUrl, PoolReceptionStatusRequestBody poolReceptionStatusRequestBody) {
        return iCallApi.getCreditorAmountToday(baseUrl,poolReceptionStatusRequestBody);
    }

    @Override
    public Observable<CreditorAmount> getCreditorAmountLimit(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody) {
        return iCallApi.getCreditorAmountLimit(baseUrl,poolReceptionLimitRequestBody);
    }

    @Override
    public Observable<SumPrice> getSumPriceCreditor(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody) {
        return iCallApi.getSumPriceCreditor(baseUrl,poolReceptionLimitRequestBody);
    }


    @Override
    public Observable<SumPrice> getSumPriceCreditorToday(String baseUrl, PoolReceptionStatusRequestBody poolReceptionStatusRequestBody) {
        return iCallApi.getSumPriceCreditorToday(baseUrl,poolReceptionStatusRequestBody);
    }
}
