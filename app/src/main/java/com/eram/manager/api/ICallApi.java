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

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by behnaz on 10/31/2017.
 */

public interface ICallApi {

    @GET
    Observable<Data<ServerGym>> serverLogin(@Url String url);

    @POST
    Observable<TokenResponse> loginUser(@Url String url,@Body LoginRequestBody loginRequestBody);

    @GET
    Observable<AllReport> allReport(@Url String baseUrl);

    @POST
    Observable<OrganizationUnit> getOrganizatonUnit(@Url String baseUrl);

    @POST
    Observable<PoolReception> getPoolReceptionDay(@Url String baseUrl,@Body PoolReceptionDayRequestBody poolReceptionDayRequestBody);

    @POST
    Observable<PoolReceptionLimit> getPoolReceptionLimit(@Url String baseUrl, @Body PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    @POST
    Observable<PoolReceptionStatus> getPoolReceptionStatus(@Url String baseUrl, @Body PoolReceptionStatusRequestBody poolReceptionStatusRequestBody);

    @POST
    Observable<DebtorAmount> getDebtorAmountToday(@Url String baseUrl,@Body PoolReceptionStatusRequestBody poolReceptionStatusRequestBody);

    @POST
    Observable<SumPrice> getSumPriceReciept(@Url String baseUrl,@Body PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    @POST
    Observable<DebtorAmount> getDebtorAmountLimit(@Url String baseUrl,@Body PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    @POST
    Observable<CreditorAmount> getCreditorAmountToday(@Url String baseUrl,@Body PoolReceptionStatusRequestBody poolReceptionStatusRequestBody);

    @POST
    Observable<CreditorAmount> getCreditorAmountLimit(@Url String baseUrl,@Body PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    @POST
    Observable<SumPrice> getSumPriceCreditor(@Url String baseUrl,@Body PoolReceptionLimitRequestBody poolReceptionLimitRequestBody);

    @POST
    Observable<SumPrice> getSumPriceCreditorToday(@Url String baseUrl,@Body PoolReceptionStatusRequestBody poolReceptionStatusRequestBody);

//    @GET
//    Call<ResponseBody> callMapAddress(@Url String url, @Body RequestBody body);

//    @GET("/xml")
//    Call<Responce> getProducts();

}
