package com.zylr.minescapeaddons.addons.gui.widgets.scrollingwidgets;

import com.zylr.minescapeaddons.addons.config.Config;
import com.zylr.minescapeaddons.addons.config.Setting;
import com.zylr.minescapeaddons.addons.gui.widgets.screens.MainSettings;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class SettingsList extends Widget implements ScrollingWidget{

    private Minecraft mc = Minecraft.getInstance();
    private FontRenderer font = mc.fontRenderer;

    private MainSettings parentScreen;
    private int scrollIndex;
    private int scrollAmount;
    private int linesVisible;
    private int totalLines;
    private int lineHeight = font.FONT_HEIGHT + 4;
    private int padding = 10;
    private String title;

    private List<Setting> settings = new ArrayList<>();

    public SettingsList(MainSettings parentScreen, int anchorX, int anchorY, int width) {
        super();
        this.parentScreen = parentScreen;
        //TODO:: fix this to work with relative positions
        this.anchorX = anchorX;
        this.anchorY = anchorY;

        for (Config value : Config.values()) {
            settings.add(new Setting(value));
        }

        totalLines = settings.size();
        linesVisible = parentScreen.getSettingsHeight()/lineHeight;

        this.scrollAmount = 1;

        this.widgetHeight = parentScreen.getScreenHeight();
        this.widgetWidth = width;
    }

    @Override
    public void render() {
        // Calculate information on how many lines are being rendered
        int renderY = lineHeight;
        totalLines = settings.size();
        linesVisible = (mc.getMainWindow().getScaledHeight()/lineHeight)-2;
//        System.out.println(scrollIndex);

        // Verify scrollIndex
        if (linesVisible >= settings.size())
            scrollIndex = 0;
        else if (scrollIndex > settings.size()-linesVisible)
            scrollIndex = settings.size()-linesVisible;

        // Using the scroll index it shows where in the list is the top of the screen
        // increment from that point until no more lines can fit on screen or no more items are in the list

        if (parentScreen.getSettingsWidth() < parentScreen.getSettingsMaxWidth())
            return;
        for (int i = scrollIndex; i < linesVisible+scrollIndex && i < settings.size(); i++) {
            renderY += lineHeight;
            settings.get(i).render(this.getLeftSide() + padding,
                    this.getRightSide() - settings.get(i).getButtonWidth() - padding, renderY);
        }
    }

    @Override
    public void scrollUp() {
        boolean canScroll = true;
        System.out.println("scrolling");
        // Check if chat is full enough to scroll
        if (linesVisible >= settings.size()) {
            System.out.println(1);
            scrollIndex = 0;
            canScroll = false;
        }
        // Check if scrollIndex would be past the top of the list
        if (scrollIndex > settings.size() - linesVisible) {
            System.out.println(2);
            scrollIndex = settings.size() - linesVisible;
            canScroll = false;
        }

        if (canScroll)
            scrollIndex += scrollAmount;

    }

    @Override
    public void scrollDown() {
        if (scrollIndex < 3)
            scrollIndex = 0;
        else
            scrollIndex -= scrollAmount;
    }

    public List<Setting> getSettings() { return this.settings; }
}
