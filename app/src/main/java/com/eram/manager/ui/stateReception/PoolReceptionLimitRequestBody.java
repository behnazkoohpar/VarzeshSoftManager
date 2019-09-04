package com.eram.manager.ui.stateReception;

public class PoolReceptionLimitRequestBody {
    String from_date;
    String to_date;
    String organization_unit;

    public PoolReceptionLimitRequestBody(String from_date, String to_date, String organization_unit) {
        this.from_date = from_date;
        this.to_date = to_date;
        this.organization_unit = organization_unit;
    }
}
