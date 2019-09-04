package com.eram.manager.data.model.api;

import java.util.List;

public class OrganizationUnit {
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

    public List<OrganizationUnit.Result> getResult() {
        return Result;
    }

    public void setResult(List<OrganizationUnit.Result> result) {
        Result = result;
    }

    public static class Result{
    private String ID;
    private String Name;

        public Result(String ID, String name) {
            this.ID = ID;
            Name = name;
        }

        public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }}
}
