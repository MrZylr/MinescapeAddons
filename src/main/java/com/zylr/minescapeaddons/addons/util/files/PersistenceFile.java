package com.zylr.minescapeaddons.addons.util.files;

import org.apache.logging.log4j.core.config.yaml.YamlConfigurationFactory;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class PersistenceFile {
    public static final File FARMINGDIR = new File("minescape/farming");
    public static final File MODDIR = new File("minescape");
    public static final File FARMINGTIMERSFILE = new File("minescape/farming/timers.txt");
    public static final File PATCHTYPESETTINGS = new File("minescape/farming/patch_type_settings.txt");
    public static final File SKILLXPFILE = new File("minescape/skillXp.txt");


    public static void createFiles() {
        try {
            if (MODDIR.mkdirs())
                System.out.println("Creating Minescape Directory");
            if (FARMINGDIR.mkdirs())
                System.out.println("Creating Farming Directory");
            if (FARMINGTIMERSFILE.createNewFile())
                System.out.println("Creating Farming timers file");
            if (SKILLXPFILE.createNewFile())
                System.out.println("Creating xp file");
            if (PATCHTYPESETTINGS.createNewFile())
                System.out.println("Creating patch type file");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
