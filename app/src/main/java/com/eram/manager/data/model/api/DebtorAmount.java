package com.eram.manager.data.model.api;

import java.util.List;

public class DebtorAmount {
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

    public List<DebtorAmount.Result> getResult() {
        return Result;
    }

    public void setResult(List<DebtorAmount.Result> result) {
        Result = result;
    }

    public class Result {
        private int OperationCount;
        private String OperationTitle;
        private String TotalAmount;

        public int getOperationCount() {
            return OperationCount;
        }

        public void setOperationCount(int operationCount) {
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
