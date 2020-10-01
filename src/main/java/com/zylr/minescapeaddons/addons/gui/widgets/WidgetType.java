package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;

import java.io.File;

public enum WidgetType {
    XPTRACKER(PersistenceFile.XPTRACKERFILE),
    SCOREBOARD(PersistenceFile.SCOREBOARDFILE);

    private File file;

    WidgetType(File file){
        this.file = file;
    }

    public File getFile() { return this.file; }
}
