package com.eram.manager.data.model.api;

public class PoolReceptionLimit {
    private boolean status;
    private String errmessage;
    private String AllReceptionedMemberCount;

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

    public String getAllReceptionedMemberCount() {
        return AllReceptionedMemberCount;
    }

    public void setAllReceptionedMemberCount(String allReceptionedMemberCount) {
        AllReceptionedMemberCount = allReceptionedMemberCount;
    }
}
