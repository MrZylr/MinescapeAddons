package com.zylr.minescapeaddons.addons.gui.widgets.scrollingwidgets;

import com.zylr.minescapeaddons.addons.gui.widgets.Widget;

import java.util.ArrayList;

public interface ScrollingWidget {
    int scrollIndex = 0;
    int scrollAmount = 0;
    int linesVisible = 0;
    String title = "";

    void render();
    void scrollUp();
    void scrollDown();

}
