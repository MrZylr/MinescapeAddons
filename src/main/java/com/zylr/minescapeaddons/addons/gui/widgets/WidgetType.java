package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;

import java.io.File;

public enum WidgetType {
    XPTRACKER(new File("minescape/properties/widgets/xpTracker.properties"), "configs/xptrackerconfig.properties"),
    SCOREBOARD(new File("minescape/properties/widgets/scoreboard.properties"), "configs/scoreboardconfig.properties"),
    CHAT(new File("minescape/properties/widgets/chat.properties"), "configs/chatconfig.properties");

    private File file;
    private String defaultFile;

    WidgetType(File file, String defaultFile){
        this.file = file;
        this.defaultFile = defaultFile;
    }

    public File getFile() { return this.file; }
    public String getDefaultFile() { return this.defaultFile; }
}
