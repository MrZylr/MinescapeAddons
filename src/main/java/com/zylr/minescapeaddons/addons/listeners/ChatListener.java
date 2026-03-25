package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.widgets.chat.ChatWidget;
import com.zylr.minescapeaddons.addons.skills.tracker.XpTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientChatEvent;
import net.neoforged.neoforge.client.event.ClientChatReceivedEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Listener
public class ChatListener {
    public static final List<String> NUMBERS =  List.of("\uF01B", "\uF01C", "\uF01D", "\uF01E", "\uF01F", "\uF020",
            "\uF021", "\uF022", "\uF023", "\uF024");


    @SubscribeEvent
    public void onChatSent(ClientChatEvent e) {
        //if (Config.getCustomChat())
            //e.setCanceled(true);
    }


    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent.System e) {
        Logger LOGGER = MinescapeAddons.LOGGER;

        Component messageComp = e.getMessage();
        String[] messages = messageComp.getString().split("\n");

        if (!e.isOverlay() && Config.getCustomChat()) {
            ChatWidget chatWidget = MinescapeAddons.getInstance().resizableClassic.getChatWidget();
            List<Component> messageList = new ArrayList<>();
            for (String line : messages) {
                // Preserve the original message style (color/formatting) for each created line.
                // Apply the original component's style to a literal containing the visual line.
                if (line.equalsIgnoreCase(messages[messages.length-1])) continue;
                Component lineComp = Component.literal(line).withStyle(messageComp.getStyle());
                if (lineComp.getString().equalsIgnoreCase("You can claim your daily reward!")) {
                    lineComp.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Click Here.")));
                    lineComp = Component.literal(line).withStyle(lineComp.getStyle());
                }
                messageList.add(lineComp);
            }
            //chatWidget.addChatMessage(messageList, messageComp);
            //e.setCanceled(true);
        }

        if (messages.length-2 >= 0) {
            if (messages[messages.length - 2].contains("clue casket")) {
                MinescapeAddons.getInstance().resizableClassic.getClueWidget().checkForCasket();
            }
        }

        if (e.isOverlay()) {
            String message = messageComp.getString();
            String hpMessage = message.substring(0, message.length()/3);
            String prayerMessage = message.substring(message.length()/3);

            String hpDigits = "";
            String prayerDigits = "";

            // Check if the message contains health points and prayer points
            for (String number : NUMBERS) {
                if (message.contains(number)) {
                    // Extract digits from the health points message
                    for (char c : hpMessage.toCharArray()) {
                        if (NUMBERS.indexOf(c + "") != -1) {
                            hpDigits += NUMBERS.indexOf(c + "");
                        }
                    }
                    for (char c : prayerMessage.toCharArray()) {
                        if (NUMBERS.indexOf(c + "") != -1) {
                            prayerDigits += NUMBERS.indexOf(c + "");
                        }
                    }
                    try {
                        int hp = Integer.parseInt(hpDigits);
                        int prayer = Integer.parseInt(prayerDigits);
                        MinescapeAddons.getInstance().player.setHealthPoints(hp);
                        MinescapeAddons.getInstance().player.setPrayerPoints(prayer);
                    }catch(Exception ex) {
                        LOGGER.error("Error parsing health or prayer points: " + ex.getMessage());
                        return;
                    }

                    return;
                }
            }
        }

        // Check for player joined hub message
        if (messageComp.getString().equalsIgnoreCase("Welcome to MineScape pre-beta")) {
            if (messageComp.getSiblings().size() == 3) {
                LOGGER.info("Joined Hub - Pausing all XP trackers");
                XpTracker.pauseAllTrackers();
            }
        }
    }

    // Hub Coords
    private boolean isHubLocation(net.minecraft.core.BlockPos pos) {
        System.out.println("Player Position: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
        return pos.getX() == -2038 && pos.getY() == 65 && pos.getZ() == -2802; // Example coordinates
    }
}