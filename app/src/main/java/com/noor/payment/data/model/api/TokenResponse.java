package com.noor.payment.data.model.api;

import com.google.gson.annotations.SerializedName;

public class TokenResponse{

    @SerializedName("Token")
    private String Token;

    @SerializedName("UserId")
    private String UserId;

    @SerializedName("DateTime")
    private String DateTime;

    @SerializedName("key")
    private String key;


    public String getToken() {
        return Token;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
