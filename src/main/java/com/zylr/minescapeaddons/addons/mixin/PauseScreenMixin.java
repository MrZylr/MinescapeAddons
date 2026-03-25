// java
package com.zylr.minescapeaddons.addons.mixin;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.screens.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.screens.HudEditScreen;
import com.zylr.minescapeaddons.addons.gui.screens.SettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PauseScreen.class)
public class PauseScreenMixin {


    @Inject(
            method = "createPauseMenu",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/layouts/GridLayout;arrangeElements()V",
                    shift = At.Shift.BEFORE
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void onCreatePauseMenu(CallbackInfo ci, GridLayout gridlayout, GridLayout.RowHelper gridlayout$rowhelper) {
        // Example: add a custom button using the captured RowHelper before visitWidgets is called
        gridlayout$rowhelper.addChild(
                Button.builder(Component.literal("Hud Edit"), btn -> {
                    this.onEditHudButtonPress();
                }).width(98).build()
        );
        gridlayout$rowhelper.addChild(
                Button.builder(Component.literal("Mod Settings"), btn -> {
                    this.onSettingsButtonPress();
                }).width(98).build()
        );
        gridlayout$rowhelper.addChild(
                Button.builder(Component.literal("Farming Timers"), btn -> {
                    this.onFarmingTimersButtonPress();
                }).width(98).build()
        );
        gridlayout$rowhelper.addChild(
                Button.builder(Component.literal("Reset Barrows"), btn -> {
                    this.onBarrowsButtonPress();
                }).width(98).build()
        );
        // No need to call visitWidgets here — the original method will continue and call it.
    }

    private void onEditHudButtonPress() {Minecraft.getInstance().setScreen(new HudEditScreen());}
    private void onSettingsButtonPress() { Minecraft.getInstance().setScreen(new SettingsScreen()); }
    private void onFarmingTimersButtonPress() { Minecraft.getInstance().setScreen(new FarmingTimersScreen()); }
    private void onBarrowsButtonPress() { MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().resetValues(); }
}