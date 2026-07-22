package com.zylr.client;

import com.zylr.MinescapeAddon;
import com.zylr.client.hud.HudManager;
import com.zylr.player.PlayerStats;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

import java.util.List;

public class ChatListener {
	private static final String RESOURCE_PACK_OVERLAY_MESSAGE = "Make sure to accept the resourcepack or do: /urp";
	public static final List<String> NUMBERS = List.of("\uF01B", "\uF01C", "\uF01D", "\uF01E", "\uF01F", "\uF020",
			"\uF021", "\uF022", "\uF023", "\uF024");

	private static final Logger LOGGER = MinescapeAddon.LOGGER;

	public static void register() {
		ClientReceiveMessageEvents.GAME.register(ChatListener::onChatReceived);
	}

	private static void onChatReceived(Component messageComp, boolean overlay) {
		if (overlay) {
			String message = messageComp.getString();
			if (stripColorCodes(message).equalsIgnoreCase(RESOURCE_PACK_OVERLAY_MESSAGE)) {
				HudManager.getInstance().pauseXpTrackerForResourcePackOverlay();
				return;
			}

			String hpMessage = message.substring(0, message.length() / 3);
			String prayerMessage = message.substring(message.length() / 3);

			String hpDigits = "";
			String prayerDigits = "";

			for (String number : NUMBERS) {
				if (message.contains(number)) {
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
						PlayerStats.setHealth(hp);
						PlayerStats.setPrayer(prayer);
					} catch (Exception ex) {
						LOGGER.error("Error parsing health or prayer points: " + ex.getMessage());
						return;
					}
					return;
				}
			}
			return;
		}
	}

	// Hub Coords
	private static boolean isHubLocation(BlockPos pos) {
		return pos.getX() == -2038 && pos.getY() == 65 && pos.getZ() == -2802;
	}

	private static String stripColorCodes(String text) {
		if (text == null) return "";
		return text.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
	}
}



