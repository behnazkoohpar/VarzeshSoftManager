package com.eram.manager.api;

import com.eram.manager.data.model.api.AllReport;
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

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface RestManager {

    Observable<TokenResponse> loginUser(String url,LoginRequestBody loginRequestBody);

    Observable<Data<ServerGym>>  serverLogin(String url);

    Observable<AllReport> allReport(String baseUrl);

    Observable<OrganizationUnit> getOrganizatonUnit(String baseUrl);

    Observable<PoolReception> getPoolReceptionDay(String baseUrl, PoolReceptionDayRequestBody poolReceptionDayRequestBody);

    Observable<PoolReceptionLimit> getPoolReceptionLimit(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    Observable<PoolReceptionStatus> getPoolReceptionStatus(String baseUrl, PoolReceptionStatusRequestBody poolReceptionStatusRequestBody);

    Observable<DebtorAmount> getDebtorAmountToday(String baseUrl, PoolReceptionStatusRequestBody poolReceptionStatusRequestBody);

    Observable<DebtorAmount> getDebtorAmountLimit(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    Observable<SumPrice> getSumPriceReciept(String baseUrl, PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

}
