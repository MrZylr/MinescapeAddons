package com.zylr.minescapeaddons.addons.gui.widgets;

import java.io.File;

public enum WidgetType {
    XPTRACKER(new File("minescape/properties/widgets/xpTracker.properties"), "configs/xptrackerconfig.properties"),
    SCOREBOARD(new File("minescape/properties/widgets/scoreboard.properties"), "configs/scoreboardconfig.properties"),
    CHAT(new File("minescape/properties/widgets/chat.properties"), "configs/chatconfig.properties"),
    HUDMENU(new File("minescape/properties/widgets/inventory.properties"), "configs/inventory.properties"),
    CLUEHELPER(new File("minescape/properties/widgets/clue.properties"), "configs/clue.properties"),
    BROWSER(new File("minescape/properties/widgets/browser.properties"), "configs/browserconfig.properties"),
    BARROWS_BROTHERS_WIDGET(new File("minescape/properties/widgets/barrows_brothers.properties"), "configs/barrows_brothers.properties"),
    TARGET_INFO(new File("minescape/properties/widgets/target_info.properties"), "configs/target_info.properties"),
    MINIMAP(new File("minescape/properties/widgets/minimap.properties"), "configs/minimap.properties");


    private File file;
    private String defaultFile;

    WidgetType(File file, String defaultFile){
        this.file = file;
        this.defaultFile = defaultFile;
    }

    public File getFile() { return this.file; }
    public String getDefaultFile() { return this.defaultFile; }
}
