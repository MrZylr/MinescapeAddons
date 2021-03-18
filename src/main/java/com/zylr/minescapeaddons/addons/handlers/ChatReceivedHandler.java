package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimerCompletedOnLogin;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.scrollingwidgets.TextList;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.gui.builders.XpTrackerBuilder;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.util.text.ChatType;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChatReceivedHandler {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent e) {
        XpTrackerBuilder gui = Main.getInstance().getXpTrackerBuilder();
        Minecraft mc = Minecraft.getInstance();
        // Get messaged for chatWidget
        if (e.getType() == ChatType.SYSTEM){
            for (IWidget widget : Main.getInstance().getRsHud().getWidgets()) {
                if (widget instanceof ChatWidget) {
                    ((ChatWidget)widget).addTextComponent(e.getMessage());
                }
            }
        }

        // Check for player joined hub message
        if (e.getMessage().getFormattedText().equalsIgnoreCase("§aWelcome to §r§6MineScape §r§bpre-beta§r") && Main.getInstance().inServer == true) {
            System.out.println("Joined Hub. Pausing XpTracker.");
            Main.getInstance().getXpTrackerBuilder().pauseAllTimers();
            Main.getInstance().inServer = false;
        }

        // Check for player joined message
        if (e.getMessage().getFormattedText().length() > 80) {
            String message = e.getMessage().getFormattedText().substring(40, 80);
            if (message.equalsIgnoreCase("----------------------------------------") && Main.getInstance().inServer == false) {
                // Unpause trackers
                System.out.println("Joined world. Unpausing Xp Tracker.");
                Main.getInstance().getXpTrackerBuilder().unpauseAllTimers();
                Main.getInstance().inServer = true;

                // Check for completed patches
                if (FarmingUtil.checkForCompletedTimers()) {
                    mc.displayGuiScreen(new FarmingTimerCompletedOnLogin());
                }
            }
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent e) {

    }
}
