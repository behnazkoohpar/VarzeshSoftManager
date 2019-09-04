package com.eram.manager.data.model.api;

import java.util.List;

public class PoolReception {
    private boolean status;
    private String errmessage;
    private String PresentMemberCount;
    private String ExitedMemberCount;
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

    public String getPresentMemberCount() {
        return PresentMemberCount;
    }

    public void setPresentMemberCount(String presentMemberCount) {
        PresentMemberCount = presentMemberCount;
    }

    public String getExitedMemberCount() {
        return ExitedMemberCount;
    }

    public void setExitedMemberCount(String exitedMemberCount) {
        ExitedMemberCount = exitedMemberCount;
    }

    public String getAllReceptionedMemberCount() {
        return AllReceptionedMemberCount;
    }

    public void setAllReceptionedMemberCount(String allReceptionedMemberCount) {
        AllReceptionedMemberCount = allReceptionedMemberCount;
    }


}
