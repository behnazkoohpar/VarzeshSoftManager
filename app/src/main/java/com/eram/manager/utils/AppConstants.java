package com.eram.manager.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by behnaz on 10/29/2017.
 */
public interface AppConstants {
    //10.1.7.31:8280
//    String BASE_URL = "https://service.sepehrnet.com:8243";
//    String BASE_URL ="https://service.sepehrnet.com:8243";
    //188.75.127.132:8243

    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String PREF_NAME = "noor_pref";
    String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    boolean LOGTRUE = true;
    String REQUEST_KEY_PASSWORD = "password";
    String REQUEST_KEY_PHONE_NUMBER = "mobileNumber";
    String REQUEST_KEY_NATIONAL_CODE = "nationalCode";
    String REQUEST_KEY_APPLICATION_CODE = "applicationCode";
    String REQUEST_KEY_APPLICATION_CODE_LOGIN = "ApplicationCode";
    String REQUEST_KEY_PASSWORD_LOGIN = "Password";
    String REQUEST_KEY_USERNAME_LOGIN = "UserName";
    String REQUEST_KEY_ROLE_CODE = "roleCode";
    String REQUEST_KEY_USER_ID = "userId";
    String REQUEST_KEY_CODE = "code";
    String REQUEST_KEY_USERNAME = "userName";
    String REQUEST_KEY_NEW_PASSWORD = "newPassword";
    String REQUEST_NOOR_PAYMENT = "NoorPayment";


    ////
    int MY_PERMISSIONS_REQUEST_CAMERA = 101;
    int MY_PERMISSIONS_REQUEST_LOCATION = 102;
    int MY_PERMISSIONS_REQUEST_FINGERPRINT = 104;
    // Headers
    String HEADER_SECURITY_KEY = "SignData";
    String HEADER_TOKEN = "Token";
    String HEADER_INVOICE_NUMBER = "InvoiceNumber";

}

