package com.eram.manager.ui.login;

public class LoginRequestBody {
     String user_name;
     String password;

    public LoginRequestBody(String userName, String passWord) {
        user_name = userName;
        password = passWord;
    }
}
