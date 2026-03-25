package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;

public class SettingsScreenButton extends PauseScreen {

    public SettingsScreenButton(boolean showPauseMenu) {
        super(showPauseMenu);
    }

    private void onEditHudButtonPress() {
        Minecraft.getInstance().setScreen(new HudEditScreen());
    }
    private void onSettingsButtonPress() { Minecraft.getInstance().setScreen(new SettingsScreen()); }
    private void onFarmingTimersButtonPress() { Minecraft.getInstance().setScreen(new FarmingTimersScreen()); }
    private void onBarrowsButtonPress() { MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().resetValues(); }
}
