package com.zylr.client.screen.overridescreens;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.inventory.ChestMenu;

public final class GrandExchangePollingState {
	private static ChestMenu pendingMenu;
	private static Screen fallbackScreen;
	private static int fallbackRenderTicksRemaining;

	private GrandExchangePollingState() {
	}

	public static void setPendingMenu(ChestMenu menu, Screen previousScreen, int previousScreenRenderTicks) {
		pendingMenu = menu;
		fallbackScreen = previousScreenRenderTicks > 0 ? previousScreen : null;
		fallbackRenderTicksRemaining = Math.max(0, previousScreenRenderTicks);
	}

	public static void clear() {
		pendingMenu = null;
		fallbackScreen = null;
		fallbackRenderTicksRemaining = 0;
	}

	public static boolean isPending(ChestMenu menu) {
		return pendingMenu != null && pendingMenu == menu;
	}

	public static Screen fallbackScreen(ChestMenu menu) {
		if (!isPending(menu) || fallbackRenderTicksRemaining <= 0) {
			return null;
		}
		return fallbackScreen;
	}

	public static void tick() {
		if (fallbackRenderTicksRemaining > 0) {
			fallbackRenderTicksRemaining--;
			if (fallbackRenderTicksRemaining <= 0) {
				fallbackScreen = null;
			}
		}
	}
}
