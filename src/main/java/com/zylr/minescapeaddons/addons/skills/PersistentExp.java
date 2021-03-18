package com.zylr.minescapeaddons.addons.skills;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistentExp {

    public static void saveExp() {
        List<String> epxList = new ArrayList<>();

        // Add all exp to list
        for (SkillType skillType : SkillType.values()) {
            epxList.add(skillType.toString() + ":" +  Main.skills.get(skillType).getExp());
        }

        PersistenceFile.writeFile(PersistenceFile.SKILLXPFILE.getPath(), epxList);
    }

    public static void loadExp() {
        Scanner reader = PersistenceFile.readFile(PersistenceFile.SKILLXPFILE.getPath());

        // Set exp from file
        while (reader.hasNext()) {
            String[] skillInfo = reader.nextLine().split(":");

            SkillType skillType;
            int exp;

            try {
                skillType = SkillType.valueOf(skillInfo[0]);
            } catch (IllegalArgumentException ex) {
                PersistenceFile.resetFile(PersistenceFile.SKILLXPFILE.getPath());
                ex.printStackTrace();
                return;
            }
            try {
                exp = Integer.parseInt(skillInfo[1]);
            } catch (NumberFormatException ex) {
                PersistenceFile.resetFile(PersistenceFile.SKILLXPFILE.getPath());
                ex.printStackTrace();
                return;
            }

            Main.skills.get(skillType).setupLevel(exp);
        }
    }
}
