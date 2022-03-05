package com.zylr.minescapeaddons.addons.gui.screens.settings;

import com.zylr.minescapeaddons.addons.config.Config;
import com.zylr.minescapeaddons.addons.gui.screens.ModScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.SettingsList;
import com.zylr.minescapeaddons.addons.gui.widgets.SettingsListWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.list.AbstractList;
import net.minecraft.client.gui.widget.list.ExtendedList;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainSettings extends Screen {
    private Minecraft mc = Minecraft.getInstance();

    // Full screen
    private int screenMaxWidth;
    private int screenWidth;
    private int screenHeight;
    private int backgroundColor;
    // Settings
    private int settingsMaxWidth;
    private int settingsWidth;
    private int settingsHeight;
    private int settingsBackgroundColor;
    private int settingsFontColor;

    private List<Config> settings;
    private SettingsListWidget settingsList;
    private SettingsList list;

    public MainSettings() {
        super(new StringTextComponent("Main Settings"));

        this.screenMaxWidth = mc.getMainWindow().getScaledWidth();
        this.screenWidth = 0;
        this.screenHeight = mc.getMainWindow().getScaledHeight();
        this.backgroundColor = new Color(0, 0, 0, 100).getRGB();
        this.settingsMaxWidth = 150;
        this.settingsWidth = 0;
        this.settingsHeight = this.screenHeight;
        this.settingsBackgroundColor = new Color(51, 104, 1, 100).getRGB();
        this.settingsFontColor = new Color(255, 255, 255, 255).getRGB();

        this.settings = Collections.unmodifiableList(Arrays.asList(Config.values()));

        this.list = new SettingsList(mc, settingsMaxWidth, settingsHeight, 20, settingsHeight - 20);
    }

    @Override
    public void init() {
        settingsList = new SettingsListWidget(this, settingsWidth, settingsHeight, 10, 20);

        children.clear();
        children.add(settingsList);
        children.add(list);
    }

    @Override
    public void tick() {
        super.tick();
        // TODO:: TEMP, REMOVE
        this.backgroundColor = new Color(0, 0, 0, 100).getRGB();
        this.settingsBackgroundColor = new Color(85, 0, 67, 100).getRGB();
        this.settingsFontColor = new Color(252, 242, 14, 255).getRGB();
        this.settingsMaxWidth = 225;


        // update height
        this.screenHeight = mc.getMainWindow().getScaledHeight();
        this.settingsHeight = screenHeight;

        //settingsList.refreshList();

        animateScreenOpen();
        drawBackground();
        drawSettingsBackground();
//        drawSettings();

//        list.drawPanel(settingsWidth, settingsMaxWidth, Tessellator.getInstance(), (int)mc.mouseHelper.getMouseX(), (int)mc.mouseHelper.getMouseY());
//        new SettingsList(Minecraft.getInstance(), screenWidth, screenHeight, 10, screenHeight).
//                drawPanel(settingsWidth - settingsMaxWidth + 10, 0, Tessellator.getInstance(), 0, 0);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) {
        super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);

        System.out.println("clicked");
        for (Config setting : settings) {
            System.out.println("Setting: " + setting.getName());
        }
        for (SettingsListWidget.SettingsEntry child : settingsList.children()) {
            System.out.println("SettingsEntry: " + child.getSetting().getName());
        }

        return true;
    }

    public <T extends ExtendedList.AbstractListEntry<T>> void buildModList(Consumer<T> modListViewConsumer, Function<Config, T> newEntry)
    {
        settings.forEach(setting->modListViewConsumer.accept(newEntry.apply(setting)));
    }

//    public void buildModList ()

    private void animateScreenOpen() {
        // Increase background growing width every 60 ticks until full screen width
        if (screenWidth < screenMaxWidth)
            screenWidth += 30;
        if (settingsWidth < settingsMaxWidth)
            settingsWidth += 5;
    }

    private void drawBackground() {
        GuiUtils.drawGradientRect(0, 0, 0, screenWidth, screenHeight, backgroundColor, backgroundColor);
    }

    private void drawSettingsBackground() {
        GuiUtils.drawGradientRect(1, 0, 0, settingsWidth,settingsHeight, settingsBackgroundColor, settingsBackgroundColor);
    }

    private void drawSettings() {
        FontRenderer font = mc.fontRenderer;
        // Spaces from side of screen and between lines
        int padding = 15;
        int gap = 10;

        // X font postion
        int xPos = settingsWidth - settingsMaxWidth + padding;
        // Y position to start at and then increment
        int yPos = gap;

        for (Config value : Config.values()) {
            font.drawString(value.getName(), xPos, yPos, settingsFontColor);
            yPos += gap+font.FONT_HEIGHT;
        }
    }

    public int getScreenMaxWidth() {
        return screenMaxWidth;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getSettingsMaxWidth() {
        return settingsMaxWidth;
    }

    public int getSettingsWidth() {
        return settingsWidth;
    }

    public int getSettingsHeight() {
        return settingsHeight;
    }

    public int getSettingsBackgroundColor() {
        return settingsBackgroundColor;
    }

    public int getSettingsFontColor() {
        return settingsFontColor;
    }

    public SettingsList getList() {
        return list;
    }
}
