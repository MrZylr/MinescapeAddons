package com.zylr.minescapeaddons.addons.config;

import com.zylr.minescapeaddons.addons.util.files.properties.MainProperties;

import java.util.Properties;

public class BooleanOption {
    private Properties config;
    private String optionName;

    public BooleanOption(String optionName) {
        this.config = MainProperties.getConfig();
        this.optionName = optionName;
    }

    public boolean getValue() {
        return Boolean.parseBoolean(config.getProperty(optionName));
    }

    public void setValue(boolean value) {
        config.setProperty(optionName, value+"");
    }
}
