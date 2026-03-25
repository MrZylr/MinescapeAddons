package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.Player;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.BrowserWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@Listener
public class RenderHudElements {
    @SubscribeEvent
    public void onRender(RenderGuiEvent.Post e) {
        Minecraft mc = Minecraft.getInstance();

        if (Minecraft.getInstance().screen instanceof ChatScreen chatScreen) {

        }

        if (mc.level != null) {
            // Render white box on the right side (250 pixels wide)
            int screenWidth = mc.getWindow().getGuiScaledWidth();
            int screenHeight = mc.getWindow().getGuiScaledHeight();
            int whiteBoxWidth = 250;
            int whiteBoxStartX = screenWidth - whiteBoxWidth;

            // Draw white background for the panel
            //e.getGuiGraphics().fill(whiteBoxStartX, 0, screenWidth, screenHeight, new Color(0, 0, 0, 255).getRGB());
        }


        if (MinescapeAddons.getInstance().getThurgo() != null) {
            if (MinescapeAddons.getInstance().getThurgo().shouldThurgoDie())
                MinescapeAddons.getInstance().setThurgo(null);
            else
                MinescapeAddons.getInstance().getThurgo().render(e.getGuiGraphics());

        }

        if (MinescapeAddons.getInstance().scoreboard == null)
            MinescapeAddons.getInstance().scoreboard = Minecraft.getInstance().level.getScoreboard();

        if (!(Minecraft.getInstance().screen instanceof AbstractContainerScreen)) {
            for (IWidget widget : MinescapeAddons.getInstance().activeHud.widgets) {
                if (widget != null) {
                    if (widget instanceof BrowserWidget browser) {
                        browser.render(e.getGuiGraphics());
                    } else {
                        if (mc.screen instanceof ZylrInventoryScreen) return;
                        widget.render(e.getGuiGraphics());//
                    }
                }
            }
        }

        // Render red vignette if health is below threshold
        e.getGuiGraphics().pose().pushPose();
        e.getGuiGraphics().pose().translate(0, 0, -1000);
        if (Config.getVignette() && mc.player != null) {
            float health = MinescapeAddons.getInstance().player.getHealthPoints();
            float maxHealth = MinescapeAddons.skills.get(SkillType.HITPOINTS).getLevel();
            float healthThreshold = 0.2f;// Health threshold from config
            if(health / maxHealth < healthThreshold && maxHealth >= 10 && health != 0) {
                int screenWidth = mc.getWindow().getGuiScaledWidth();
                int screenHeight = mc.getWindow().getGuiScaledHeight();
                Player.renderRedVignette(e.getGuiGraphics(), screenWidth, screenHeight);
            }
        }
        e.getGuiGraphics().pose().popPose();
    }
}
