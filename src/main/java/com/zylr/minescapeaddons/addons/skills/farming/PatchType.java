package com.zylr.minescapeaddons.addons.skills.farming;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;

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

        PersistenceFile.writeFile(PersistenceFile.PATCHTYPESETTINGS.getPath(), list);
    }

    // Load the boolean settings for the buttons to configure the timer alerts(Loaded in Main)
    public static void loadPatchTypeSettings() {
        Scanner data = PersistenceFile.readFile(PersistenceFile.PATCHTYPESETTINGS.getPath());

        while (data.hasNext()) {
            String typeData = data.nextLine();

            String[] typeParts = typeData.split("/");
            PatchType patchType;
            try {
                patchType = PatchType.valueOf(typeParts[0]);
            }catch (IllegalArgumentException ex) {
                System.out.println("INCORRECT PATCH TYPE");
                patchType = HOPS;
                ex.printStackTrace();
            }
            try {
                patchType.alertOnComplete = Boolean.parseBoolean(typeParts[1]);
            }catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            try {
                patchType.alertOnStages = Boolean.parseBoolean(typeParts[2]);
            }catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            try {
                patchType.onlyAlertForAllSameType = Boolean.parseBoolean(typeParts[3]);
            }catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
    }
}
