package com.zylr.client.farming;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public enum PersistenceFile {
    FARMINGTIMERSFILE("farming_timers.txt");

    private final Path path;

    PersistenceFile(String fileName) {
        this.path = FabricLoader.getInstance().getConfigDir().resolve("minescapeaddon").resolve(fileName);
    }

    public String getPath() {
        return this.path.toString();
    }

    public static Scanner readFile(String path) {
        Path file = Path.of(path);
        if (!Files.exists(file)) {
            return new Scanner("");
        }

        try {
            return new Scanner(Files.newBufferedReader(file, StandardCharsets.UTF_8));
        } catch (IOException exception) {
            exception.printStackTrace();
            return new Scanner("");
        }
    }

    public static void writeFile(String path, List<String> lines) {
        Path file = Path.of(path);
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, lines == null ? Collections.emptyList() : lines, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
