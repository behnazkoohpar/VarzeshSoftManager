package com.eram.manager.data.model.api;

import com.google.gson.annotations.SerializedName;

public class TokenResponse{

    @SerializedName("status")
    private boolean status;

    @SerializedName("errmessage")
    private String errmessage;

    @SerializedName("SystemUserID")
    private String SystemUserID;

    @SerializedName("Name")
    private String Name;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrmessage() {
        return errmessage;
    }

    public void setErrmessage(String errmessage) {
        this.errmessage = errmessage;
    }

    public String getSystemUserID() {
        return SystemUserID;
    }

    public void setSystemUserID(String systemUserID) {
        SystemUserID = systemUserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
