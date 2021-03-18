package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.widgets.screens.HudEditScreen;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimerCompletedOnLogin;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimerOptions;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.ModContainerScreen;
import com.zylr.minescapeaddons.addons.gui.screens.ConfigBuilder;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.screens.MainSettings;
import com.zylr.minescapeaddons.addons.gui.screens.vanilla.ModVanillaInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.XpDropWidget;
import com.zylr.minescapeaddons.addons.skills.SkillHover;
import com.zylr.minescapeaddons.addons.skills.tracker.XpOrb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderGuiHandler {
    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Pre e) {
        Minecraft mc = Minecraft.getInstance();

        // Grab exp
        if (e.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO) {
            RenderGameOverlayEvent.BossInfo bossInfo = ((RenderGameOverlayEvent.BossInfo)e);
            bossInfo.getBossInfo().getName().getFormattedText();
            String bossInfoText = bossInfo.getBossInfo().getName().getFormattedText();
            boolean isXp = false;

            for (String skill : Main.skillsSymbolList) {
                if (bossInfoText.contains(skill)) {
                    isXp = true;
                    break;
                }
            }

            if (isXp) {
                System.out.println(bossInfoText);
                XpOrb.handleXpGained(bossInfoText);
                for (IWidget widget : Main.getInstance().getRsHud().getWidgets()) {
                    if (widget instanceof XpDropWidget) {
                        ((XpDropWidget) widget).setXpDropString(bossInfoText);
                    }
                }
                bossInfo.getBossInfo().setName(new StringTextComponent(""));
            }


            /*float PI = 3.14152f;
            int RADIUS = 100;
            int SECTORS = 10;
            int items = 1;
            // Place circle slightly above centre of screen
            int x = mc.getMainWindow().getScaledWidth() / 2;
            int y = mc.getMainWindow().getScaledHeight() / 2 - 15;

            float multiplier = SECTORS / items;
            // System.out.println(multiplier);

            //GL11.glPushMatrix();
            float red = 0.0f;
            GL11.glColor4f(red, 0.6F, 0.6F, 0.3F);
            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            GL11.glVertex2f(x, y);
            for(int i = 0; i < items; i++)
            {
                for(float n = 0 + (i * multiplier); n <= (SECTORS / items) + (i * multiplier); n += 1)
                {
                    float t = 2 * PI * (float) n / (float) SECTORS;
                    if (red >= 1)
                        red = 0.0f;
                    red += 0.1f;
                    GL11.glColor4f(red, 0.6F, 0.6F, 0.3F);
                    GL11.glVertex2d(x + Math.sin(t) * RADIUS, y + Math.cos(t) * RADIUS);
                }
            }
            GL11.glEnd();*/
//            System.out.println(exp);
        }

        // Cancel Elements
//        if (e.getType() == RenderGameOverlayEvent.ElementType.CHAT && ModConfiguration.CLIENT.osrsChat.get())
//            e.setCanceled(true);
        if (e.getType() == RenderGameOverlayEvent.ElementType.HEALTH && !ModConfiguration.CLIENT.renderHealth.get()) {
            e.setCanceled(true);
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR && !ModConfiguration.CLIENT.renderHotbar.get())
            e.setCanceled(true);
        if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR && mc.currentScreen instanceof FarmingTimersScreen)
            e.setCanceled(true);
        if (e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            if (!ModConfiguration.CLIENT.renderXpBar.get())
                e.setCanceled(true);
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            //e.setCanceled(true);
        }
        // Render in the bottom layer
        if (e.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            FarmingTimersScreen.buildOnScreenButton();
            // Render in darkened background
            if (ModConfiguration.CLIENT.darkInventory.get()) {
                if (mc.currentScreen instanceof ModVanillaInventoryScreen) {
                    mc.currentScreen.renderBackground();
                }
            }
        }

        // Build custom HUD
        // Render on TOP LAYER
        if (e.getType() == RenderGameOverlayEvent.ElementType.CHAT) {
//            ((RenderGameOverlayEvent.Chat)e)
            ForgeIngameGui.renderObjective = false;
//            if (ModConfiguration.CLIENT.osrsChat.get()) {
//                mc.gameSettings.keyBindChat.bind(InputMappings.getInputByCode(-1, -1));
//                mc.gameSettings.keyBindCommand.bind(InputMappings.getInputByCode(-1, -1));
//            }


            if (mc.currentScreen instanceof FarmingTimersScreen) {
                mc.currentScreen.renderDirtBackground(0);
                ((FarmingTimersScreen) mc.currentScreen).drawScreen();
                e.setCanceled(true);
            }else if (mc.currentScreen instanceof FarmingTimerOptions) {
                mc.currentScreen.renderDirtBackground(0);
                ((FarmingTimerOptions) mc.currentScreen).build();
                e.setCanceled(true);
            }
            else {
//                mc.fontRenderer.drawString(CurrentHealth.calculateCurrentHealth()+"", mc.getMainWindow().getScaledWidth()/2, mc.getMainWindow().getScaledHeight()/2, 16777215);


                if (mc.currentScreen instanceof FarmingTimerCompletedOnLogin)
                    ((FarmingTimerCompletedOnLogin) mc.currentScreen).buildLoginCheck();

//                Main.getInstance().getScoreboard().testScoreboard();
                if (Main.getInstance().XAERO)
//                    Main.getInstance().getMinimap().build();
//                else
//                    Main.getInstance().getXpTrackerBuilder().buildXp(10, mc.getMainWindow().getScaledHeight() - 10, true);

/*
                if (mc.player.isSwingInProgress) {
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.disableAlphaTest();
                    RenderSystem.pushMatrix();
                    RenderSystem.translatef(0.0F, (float) (mc.getMainWindow().getScaledHeight() - 40), 0.0F);
                    mc.getProfiler().startSection("chat");
                    mc.ingameGUI.getChatGUI().render(1);
                    mc.getProfiler().endSection();
                    RenderSystem.popMatrix();
                }*/


                    Main.getInstance().getStatsPanel().build();
                    Main.getInstance().getIdleChecker().build();
                    Main.getInstance().getInv().build();
                    FarmingTimersScreen.buildOnSreenFarmIcon();
                    FarmingTimersScreen.buildStageNotification();

                    // Render new Hud
                    Main.getInstance().getRsHud().renderWidgets();

                    // temp
                    if (mc.currentScreen instanceof HudEditScreen) {
                        mc.currentScreen.tick();
                    }
                    if (mc.currentScreen instanceof MainSettings) {
                        mc.currentScreen.tick();
                    }
                    if (mc.currentScreen instanceof ChatScreen) {
                        mc.currentScreen.tick();
                    }

                // Update inventory size
                if (mc.currentScreen instanceof ModContainerScreen)
                    ((ModContainerScreen) mc.currentScreen).updateSize();

                // Check camera location for idle
                if (Main.getInstance().getIdleChecker().getMouseIdle().hasMouseMoved())
                    Main.getInstance().getIdleChecker().getMouseTimer().startTimer();

                if (mc.player.isSwingInProgress)
                    Main.getInstance().getIdleChecker().getInteractTimer().startTimer();

                // Check if screen changes and rebuild
                if (mc.currentScreen instanceof ModVanillaInventoryScreen) {
                    if (((ModVanillaInventoryScreen) mc.currentScreen).getCurrentInventory() != Main.getInstance().getStatsPanel().getCurrentScreen()) {
                        ((ModVanillaInventoryScreen) mc.currentScreen).drawButtons();
                    }
                }
                if (mc.currentScreen instanceof RunescapeInventoryScreen) {
                    if (((RunescapeInventoryScreen) mc.currentScreen).getCurrentInventory() != Main.getInstance().getStatsPanel().getCurrentScreen()) {
                        mc.displayGuiScreen(new RunescapeInventoryScreen(mc.player));
                    }
                    if (((RunescapeInventoryScreen) mc.currentScreen).setupOrientation() != ((RunescapeInventoryScreen) mc.currentScreen).getOrientation())
                        mc.displayGuiScreen(new RunescapeInventoryScreen(mc.player));
                }

                if (mc.currentScreen instanceof ConfigBuilder) {
                    if (RunescapeInventoryScreen.setupOrientation() != ((ConfigBuilder) mc.currentScreen).getOrientation())
                        new ConfigBuilder().build();
                    ((ConfigBuilder) mc.currentScreen).drawButtons();
                }

                if (mc.currentScreen instanceof ModVanillaInventoryScreen) {
                    if (ModConfiguration.CLIENT.persistentMouse.get()) {
                        Main.getInstance().getInv().setMouseX((int) mc.mouseHelper.getMouseX());
                        Main.getInstance().getInv().setMouseY((int) mc.mouseHelper.getMouseY());
                    } else
                        Main.getInstance().getInv().resetMouse();
                    if (((ModVanillaInventoryScreen) mc.currentScreen).getCurrentInventory() == 0) {
                        double mouseX = mc.mouseHelper.getMouseX() / mc.getMainWindow().getGuiScaleFactor();
                        double mouseY = mc.mouseHelper.getMouseY() / mc.getMainWindow().getGuiScaleFactor();

                        SkillHover hover = new SkillHover((int) mouseX, (int) mouseY);
                        hover.build();
                    }
                }
            }



            /*
            mc.getConnection().sendPacket(new CClientStatusPacket(CClientStatusPacket.State.REQUEST_STATS));
            System.out.println("Zombie killed: " +
                    mc.player.getStats().getValue(Stats.CUSTOM.get(Stats.MOB_KILLS)));
            */
        }
    }
}
