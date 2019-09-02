package com.eram.manager.api;

import com.eram.manager.data.model.api.AllReport;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.base.BaseResponse;
import com.eram.manager.data.model.api.ServerGym;
import com.eram.manager.data.model.api.TokenResponse;
import com.eram.manager.data.model.api.base.Data;
import com.eram.manager.ui.login.LoginRequestBody;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
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

//    @GET
//    Call<ResponseBody> callMapAddress(@Url String url, @Body RequestBody body);

//    @GET("/xml")
//    Call<Responce> getProducts();

}
