package com.eram.manager.ui.stateReception;

public class PoolReceptionDayRequestBody {
    String date_eng;
    String organization_unit;

    public PoolReceptionDayRequestBody(String date_eng, String organization_unit) {
        this.date_eng = date_eng;
        this.organization_unit = organization_unit;
    }
}
