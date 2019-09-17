package com.eram.manager.data.model.api;

import java.util.List;

public class CreditorAmount {
    private boolean status;
    private String errmessage;
    private List<Result> Result;

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

    public List<Result> getResult() {
        return Result;
    }

    public void setResult(List<Result> result) {
        Result = result;
    }

    public class Result {
        private String OperationCount;
        private String OperationTitle;
        private String TotalAmount;

        public String getOperationCount() {
            return OperationCount;
        }

        public void setOperationCount(String operationCount) {
            OperationCount = operationCount;
        }

        public String getOperationTitle() {
            return OperationTitle;
        }

        public void setOperationTitle(String operationTitle) {
            OperationTitle = operationTitle;
        }

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            TotalAmount = totalAmount;
        }
    }
}
