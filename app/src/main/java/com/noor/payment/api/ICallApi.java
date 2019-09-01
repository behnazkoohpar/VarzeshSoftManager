package com.noor.payment.api;

import com.noor.payment.data.model.api.BaseResponse;
import com.noor.payment.data.model.api.TokenResponse;
import com.noor.payment.ui.login.LoginRequestBody;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by behnaz on 10/31/2017.
 */

public interface ICallApi {

    @POST("/AppLogin")
//    Observable<BaseResponse<TokenResponse>> login(@Body RequestBody map);
    Call<ResponseBody> login(@Body RequestBody map);

    @POST("/WS/login")
    Observable<BaseResponse<TokenResponse>> loginUser(@Body LoginRequestBody loginRequestBody);

    @POST("/Register")
    Call<ResponseBody> signUp(@Body RequestBody map);

    @POST("/SmsValidation")
    Call<ResponseBody> smsValidation(@Body RequestBody map);

    @POST("/KartableManager")
    Call<ResponseBody> KartableManager(@Body RequestBody requestBody);

    @POST("/CreatePassword")
    Call<ResponseBody> createPassword(@Body RequestBody requestBody);

    @POST("/ForgotPassword")
    Call<ResponseBody> forgotPassword(@Body RequestBody requestBody);

    @POST("/RetryGetSms")
    Call<ResponseBody> callRetryGetSms(@Body RequestBody requestBody);

    //    @GET
//    Call<ResponseBody> callMapAddress(@Url String url, @Body RequestBody body);

//    @GET("/xml")
//    Call<Responce> getProducts();

}
