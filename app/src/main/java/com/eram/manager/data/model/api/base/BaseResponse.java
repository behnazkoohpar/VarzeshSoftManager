package com.eram.manager.data.model.api.base;

import com.eram.manager.data.model.api.Settings;

/**
 * Created by sandeeppatel on 9/25/15.
 */


public class BaseResponse  {

    protected Settings settings;

    public Settings getSettings() {
        return settings;
    }
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}
