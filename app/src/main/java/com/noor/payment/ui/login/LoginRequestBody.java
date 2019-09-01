package com.noor.payment.ui.login;

public class LoginRequestBody {
    final String vUserName;

    final String vPassword;

    final String ApplicationCode;

    public LoginRequestBody(String userName, String password, String applicationCode) {
        vUserName = userName;
        ApplicationCode = applicationCode;
        vPassword = password;
    }
}
