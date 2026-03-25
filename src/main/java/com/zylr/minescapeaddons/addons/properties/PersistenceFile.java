package com.zylr.minescapeaddons.addons.properties;

import com.zylr.minescapeaddons.addons.skills.SkillType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenceFile {
    // Spliterator character
    public static final String SPLIT = ":";
    // Directories
    public static final File MODDIR = new File("minescape");
    public static final File FARMINGDIR = new File("minescape/farming");
    public static final File SKILLDIR = new File("minescape/skills");
    public static final File WIDGETDIR = new File("minescape/widgets");
    // Files
    public static final File FARMINGTIMERSFILE = new File("minescape/farming/timers.txt");
    public static final File PATCHTYPESETTINGS = new File("minescape/farming/patch_type_settings.txt");
    public static final File SKILLXPFILE = new File("minescape/skills/skillXp.txt");
    public static final File XPTRACKERFILE = new File("minescape/widgets/xptracker.txt");
    public static final File SCOREBOARDFILE = new File("minescape/widgets/scoreboard.txt");


    public static void createFiles() {
        try {
            // Directories
            if (MODDIR.mkdirs())
                System.out.println("Creating Minescape Directory");
            if (FARMINGDIR.mkdirs())
                System.out.println("Creating Farming Directory");
            if (SKILLDIR.mkdirs())
                System.out.println("Creating Skills Directory");
            if (WIDGETDIR.mkdirs())
                System.out.println("Creating Widget Directory");
            // Files
            if (FARMINGTIMERSFILE.createNewFile())
                System.out.println("Creating Farming timers file");
            if (SKILLXPFILE.createNewFile())
                System.out.println("Creating xp file");
            if (PATCHTYPESETTINGS.createNewFile())
                System.out.println("Creating patch type file");
            if (XPTRACKERFILE.createNewFile())
                defaultXpTrackerFile();
            if (SCOREBOARDFILE.createNewFile())
                System.out.println("Creating scoreboard file");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void defaultXpTrackerFile() {
        System.out.println("Creating default xp tracker file.");
        List<String> data = new ArrayList<>();
        // AnchorX
        data.add("AnchorX:0");
        // AnchorY
        data.add("AnchorY:0");
        // CurrentSkill
        data.add("Skill:" + SkillType.TOTAL.toString());
        writeFile(XPTRACKERFILE.getPath(), data);
    }

    public static Scanner readFile(String path) {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            return reader;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String path, List<String> data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            for (String s : data) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void resetFile(String path) {
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
