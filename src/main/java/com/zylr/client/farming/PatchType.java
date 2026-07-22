package com.zylr.client.farming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public enum PatchType {
    ALLOTMENT( true, true, false),
    CACTUS( true, true, false),
    FLOWER( true, true, false),
    HERB( true, true, false),
    HOPS( true, true, false),
    BUSH( true, true, false),
    TREE( true, true, false),
    FRUIT_TREE( true, true, false),
    SPECIAL_PATCH( true, true, false),
    SPIRIT_TREE( true, true, false),
    SPECIAL_TREE( true, true, false);

    public boolean alertOnComplete;
    public boolean alertOnStages;
    public boolean onlyAlertForAllSameType;

    PatchType(boolean alertOnComplete, boolean alertOnStages, boolean onlyAlertForAllSameType) {
        this.alertOnComplete = alertOnComplete;
        this.alertOnStages = alertOnStages;
        this.onlyAlertForAllSameType = onlyAlertForAllSameType;
    }

    public void toggleAlertOnComplete() {
        if (alertOnComplete)
            alertOnComplete = false;
        else
            alertOnComplete = true;
    }

    public void toggleAlertOnStages() {
        if (alertOnStages)
            alertOnStages = false;
        else
            alertOnStages = true;
    }

    public void toggleOnlyAlertForAllStages() {
        if (onlyAlertForAllSameType)
            onlyAlertForAllSameType = false;
        else
            onlyAlertForAllSameType = true;
    }

    // Save the boolean settings for the buttons to configure the timer alerts (Called every time a button is pressed in FarmingTimerOptions)
    public static void savePatchTypeSettings() {
        List<String> list = new ArrayList<>();

        for (PatchType type : PatchType.values()) {
            list.add(type + "/" + type.alertOnComplete + "/" + type.alertOnStages + "/" + type.onlyAlertForAllSameType);
        }
    }
}
