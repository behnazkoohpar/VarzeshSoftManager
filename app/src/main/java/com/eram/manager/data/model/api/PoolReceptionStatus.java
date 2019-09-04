package com.eram.manager.data.model.api;

public class PoolReceptionStatus {
    private boolean status;
    private String errmessage;
    private String PresentMember_Max_Count;
    private String PresentMember_Max_DateTime;
    private String AllReceptionedMember_Min_Count;
    private String AllReceptionedMember_Min_DateTime;
    private String AllReceptionedMember_Max_Count;
    private String AllReceptionedMember_Max_DateTime;

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

    public String getPresentMember_Max_Count() {
        return PresentMember_Max_Count;
    }

    public void setPresentMember_Max_Count(String presentMember_Max_Count) {
        PresentMember_Max_Count = presentMember_Max_Count;
    }

    public String getPresentMember_Max_DateTime() {
        return PresentMember_Max_DateTime;
    }

    public void setPresentMember_Max_DateTime(String presentMember_Max_DateTime) {
        PresentMember_Max_DateTime = presentMember_Max_DateTime;
    }

    public String getAllReceptionedMember_Min_Count() {
        return AllReceptionedMember_Min_Count;
    }

    public void setAllReceptionedMember_Min_Count(String allReceptionedMember_Min_Count) {
        AllReceptionedMember_Min_Count = allReceptionedMember_Min_Count;
    }

    public String getAllReceptionedMember_Min_DateTime() {
        return AllReceptionedMember_Min_DateTime;
    }

    public void setAllReceptionedMember_Min_DateTime(String allReceptionedMember_Min_DateTime) {
        AllReceptionedMember_Min_DateTime = allReceptionedMember_Min_DateTime;
    }

    public String getAllReceptionedMember_Max_Count() {
        return AllReceptionedMember_Max_Count;
    }

    public void setAllReceptionedMember_Max_Count(String allReceptionedMember_Max_Count) {
        AllReceptionedMember_Max_Count = allReceptionedMember_Max_Count;
    }

    public String getAllReceptionedMember_Max_DateTime() {
        return AllReceptionedMember_Max_DateTime;
    }

    public void setAllReceptionedMember_Max_DateTime(String allReceptionedMember_Max_DateTime) {
        AllReceptionedMember_Max_DateTime = allReceptionedMember_Max_DateTime;
    }
}
