package com.eram.manager.data.model.api;

public class SumPrice {
    private boolean status;
    private String errmessage;
    private String SumTotalAmount;

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

    public String getSumTotalAmount() {
        return SumTotalAmount;
    }

    public void setSumTotalAmount(String sumTotalAmount) {
        SumTotalAmount = sumTotalAmount;
    }
}
