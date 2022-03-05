package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.hud.RSHud;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.*;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class ConfigBuilder extends Screen {

    ModConfiguration.Client client = ModConfiguration.CLIENT;
    Map<SkillType, Skill> skills = Main.getInstance().skills;
    private int orientation;
    String patreonLink = "https://www.patreon.com/Mr_Zylr";

    static {

    }
    public ConfigBuilder() {

        super(new StringTextComponent("Config"));
        this.orientation = RunescapeInventoryScreen.setupOrientation();

    }

    public void init() {
        drawButtons();
    }

    public void build() {
        Minecraft.getInstance().displayGuiScreen(this);
    }

    public void drawButtons() {
        // TODO:: Setup new buttons

        this.buttons.clear();
        // Left column
        this.addButton(new Button(width/2 -135, height/2 -175, 105, 20,
                "Show OSRS Inventory: " + client.hotbar.get(), button -> { client.hotbar.set(changeBoolean(client.hotbar.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -150, 105, 20,
                "Small Hotbar: " + client.small.get(), button -> { client.small.set(changeBoolean(client.small.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -125, 105, 20,
                "Vertical Hotbar: " + client.verticalBar.get(), button -> { client.verticalBar.set(changeBoolean(client.verticalBar.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -100, 105, 20,
                "Thicc Boi: " + client.thurgo.get(), button -> { client.thurgo.set(changeBoolean(client.thurgo.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -75, 105, 20,
                "Idle Alert: " + client.idleAlert.get(), button -> { client.idleAlert.set(changeBoolean(client.idleAlert.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -50, 105, 20,
                "Render Exp Bar: " + client.renderXpBar.get(), button -> { client.renderXpBar.set(changeBoolean(client.renderXpBar.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -25, 105, 20,
                "Render Health: " + client.renderHealth.get(), button -> { client.renderHealth.set(changeBoolean(client.renderHealth.get()));}));
        this.addButton(new Button(width/2 -135, height/2 -0, 105, 20,
                "Persistent Mouse: " + client.persistentMouse.get(), button -> { client.persistentMouse.set(changeBoolean(client.persistentMouse.get()));}));

        // Right column
        this.addButton(new Button(width/2 +30, height/2 -175, 105, 20,
                "Show XP: " + client.exp.get(), button -> { client.exp.set(changeBoolean(client.exp.get()));}));
        this.addButton(new Button(width/2 +30, height/2 -150, 105, 20,
                "Reset Current Tracker", button -> { Main.getInstance().getXpTrackerBuilder().getSkill().getTracker().resetTracker();}));
        this.addButton(new Button(width/2 +30, height/2 -125, 105, 20,
                "Reset All Trackers", button -> { Main.getInstance().getXpTrackerBuilder().resetAllTrackers();}));
        this.addButton(new Button(width/2 +30, height/2 -100, 105, 20,
                "Virtual Lvls: " + client.virtualLevel.get(), button -> { client.virtualLevel.set(changeBoolean(client.virtualLevel.get()));}));
        this.addButton(new Button(width/2 +30, height/2 -75, 105, 20,
                "Render Hotbar: " + client.renderHotbar.get(), button -> { client.renderHotbar.set(changeBoolean(client.renderHotbar.get()));}));
        this.addButton(new Button(width/2 +30, height/2 -50, 105, 20,
                "Dark Inventory: " + client.darkInventory.get(), button -> { client.darkInventory.set(changeBoolean(client.darkInventory.get()));}));
        this.addButton(new Button(width/2 +30, height/2 -25, 105, 20,
                "Render Map Overlay: " + client.renderMapOverlay.get(), button -> { client.renderMapOverlay.set(changeBoolean(client.renderMapOverlay.get()));}));
        this.addButton(new Button(width/2 +30, height/2 -0, 105, 20,
                "Tracker Paused: " + Main.getInstance().getXpTrackerBuilder().getSkill().getTracker().getTimer().isPaused(), button -> {
            if (Main.getInstance().getXpTrackerBuilder().getSkill().getTracker().getTimer().isPaused())
                Main.getInstance().getXpTrackerBuilder().getSkill().getTracker().getTimer().unpause();
            else
                Main.getInstance().getXpTrackerBuilder().getSkill().getTracker().getTimer().pause();
        }));

        this.addButton(new Button(width/2 +150, height/2 -0, 105, 20,
                "OSRS Chat: " + client.osrsChat.get(), button -> {
            client.osrsChat.set(changeBoolean(client.osrsChat.get()));
            // Set visible
            for (IWidget widget : Main.getInstance().getRsHud().getWidgets()) {
                if (widget instanceof ChatWidget)
                    ((ChatWidget) widget).setVisible(client.osrsChat.get());
            }
            if (!client.osrsChat.get()) {
                Minecraft.getInstance().gameSettings.keyBindChat.setToDefault();
                Minecraft.getInstance().gameSettings.keyBindCommand.setToDefault();
                Main.altChatKey.bind(InputMappings.getInputByCode(-1, -1));
                Main.commandKey.bind(InputMappings.getInputByCode(-1, -1));
            }
            if (client.osrsChat.get()) {
                Main.altChatKey.setToDefault();
                Main.commandKey.setToDefault();
            }
        }));


        this.addButton(new Button(width/2 -20, height/2+0, 40, 20,
                "Edit Hud", button -> {
            Minecraft.getInstance().player.closeScreen();
            Minecraft.getInstance().displayGuiScreen(new HudEditScreen(Main.getInstance().getRsHud()));
        }));

        this.addButton(new Button(width/2 -135, height/2+25, 270, 20,
                "OSRS Style Inventory: " + client.rsInventory.get(), button -> { ModConfiguration.CLIENT.rsInventory.set(changeBoolean(client.rsInventory.get())); }));


        this.addButton(new Button(width/2 -135, height/2+75, 270, 20,
                "Patreon Link", button -> {

            Minecraft.getInstance().displayGuiScreen(new ConfirmOpenLinkScreen(this::confirmLink, this.patreonLink, true));
        }));

        this.addButton(new Button(width/2 -135, height/2+50, 270, 20,
                "Use Custom Armor Overrides: " + client.customArmor.get(), button -> { ModConfiguration.CLIENT.customArmor.set(changeBoolean(client.customArmor.get())); }));


        if (ModConfiguration.CLIENT.hotbar.get()) {
            if (Main.getInstance().getStatsPanel().getCurrentScreen() == 0) {
                for (ImageButton skillButton:Main.getInstance().getImageButtons().getSkillButtons()) {
                    this.addButton(skillButton);
                }
            }
        }

//        this.addButton(new CheckboxButton(width/2 -80, height/2+150, 20, 20, "Some Text", false));
//        this.addButton(new ChangePageButton(width/2 -80, height/2 +175, false, button ->{}, false));

        this.addButton(new Button(width/2 -80, height/2+125, 165, 20,
                "Close", button -> Minecraft.getInstance().player.closeScreen()));
        this.addButton(new Button(width/2 -135, height/2+100, 270, 20,
                "Zylr is dumb and forgot to remove debug keys and i cant type now", button -> ModConfiguration.CLIENT.debugOff.set(true)));
    }

    private void confirmLink(boolean confirmed) {
        if (confirmed) {
            try {
                Util.getOSType().openURI(new URI(this.patreonLink));
            } catch (URISyntaxException var3) {
                var3.printStackTrace();
            }
        }

        Minecraft.getInstance().displayGuiScreen(this);
    }

    public boolean changeBoolean(boolean boo) {
        if (boo)
            return false;
        return true;
    }

    public int getOrientation() { return orientation; }
}
