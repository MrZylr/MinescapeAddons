package com.zylr.client.screen.overridescreens;

import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerInput;
import org.lwjgl.glfw.GLFW;

import java.util.function.IntUnaryOperator;

final class CustomScreenInventoryKeyHandler {
	private CustomScreenInventoryKeyHandler() {
	}

	static boolean handleDropHoveredInventorySlot(KeyEvent event, Minecraft minecraft, ChestMenu menu, IntUnaryOperator inventorySlotToMenuSlot) {
		if (minecraft == null || minecraft.player == null || minecraft.gameMode == null) {
			return false;
		}
		if (!minecraft.options.keyDrop.matches(event)) {
			return false;
		}
		if (HudManager.getInstance().getSelectedTab() != HudTab.INVENTORY) {
			return false;
		}

		double mouseX = HudManager.getInstance().cursorX();
		double mouseY = HudManager.getInstance().cursorY();
		int inventorySlot = HudManager.getInstance().inventoryPanelSlotIndexForBank(mouseX, mouseY);
		if (inventorySlot < 0) {
			return false;
		}

		int menuSlotId = inventorySlotToMenuSlot.applyAsInt(inventorySlot);
		if (menuSlotId < 0 || menuSlotId >= menu.slots.size()) {
			return false;
		}

		long window = minecraft.getWindow().handle();
		boolean dropStack = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS
			|| GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
		if (!MenuInteractionGate.allowMenuClick(menu.containerId, menuSlotId, dropStack ? 1 : 0, ContainerInput.THROW)) {
			return false;
		}

		minecraft.gameMode.handleContainerInput(
			menu.containerId,
			menuSlotId,
			dropStack ? 1 : 0,
			ContainerInput.THROW,
			minecraft.player
		);
		return true;
	}
}
