package com.eram.manager.data.model.api;

import com.google.gson.annotations.SerializedName;

public class AllReport {

    @SerializedName("status")
    private boolean status;

    @SerializedName("errmessage")
    private String errmessage;

    @SerializedName("Result")
    private Result Result;

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

    public AllReport.Result getResult() {
        return Result;
    }

    public void setResult(AllReport.Result result) {
        Result = result;
    }

    public class Result{
        @SerializedName("PresentMemberCount")
        private String PresentMemberCount;

        @SerializedName("ExitedMemberCount")
        private String ExitedMemberCount;

        @SerializedName("SumTotalAmount_Creditor")
        private String SumTotalAmount_Creditor;

        @SerializedName("SumTotalAmount_Receipt")
        private String SumTotalAmount_Receipt;

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

        public String getSumTotalAmount_Creditor() {
            return SumTotalAmount_Creditor;
        }

        public void setSumTotalAmount_Creditor(String sumTotalAmount_Creditor) {
            SumTotalAmount_Creditor = sumTotalAmount_Creditor;
        }

        public String getSumTotalAmount_Receipt() {
            return SumTotalAmount_Receipt;
        }

        public void setSumTotalAmount_Receipt(String sumTotalAmount_Receipt) {
            SumTotalAmount_Receipt = sumTotalAmount_Receipt;
        }
    }

}
