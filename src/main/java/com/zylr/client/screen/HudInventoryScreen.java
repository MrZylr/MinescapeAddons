package com.zylr.client.screen;

import com.zylr.client.hud.HudManager;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public final class HudInventoryScreen extends Screen {
	public HudInventoryScreen() {
		super(Component.literal("Inventory"));
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		if (this.minecraft == null) {
			return;
		}

		HudManager.getInstance().render(graphics, this.minecraft, mouseX, mouseY, delta, false, null);
		HudManager.getInstance().renderRuntimeContextMenu(graphics, this.minecraft, mouseX, mouseY);
		if (HudManager.getInstance().isHighAlchContainerMode()) {
			this.drawHighAlchPrompt(graphics);
		}
	}

	private void drawHighAlchPrompt(GuiGraphicsExtractor graphics) {
		String message = "Click the item in your inventory, then left click with your staff to begin alching";
		float scale = 2.0F;
		int textWidth = Math.max(1, Math.round(this.minecraft.font.width(message) * scale));
		int textHeight = Math.max(1, Math.round(this.minecraft.font.lineHeight * scale));
		int x = (this.width - textWidth) / 2;
		int y = (this.height - textHeight) / 2;
		this.drawScaledText(graphics, message, x + 2, y + 2, 0xAA000000, scale);
		this.drawScaledText(graphics, message, x, y, 0xFFFFFFFF, scale);
	}

	private void drawScaledText(GuiGraphicsExtractor graphics, String text, int x, int y, int color, float scale) {
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(scale, scale);
		graphics.text(this.minecraft.font, text, 0, 0, color, false);
		graphics.pose().popMatrix();
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (super.mouseClicked(event, doubleClick)) {
			return true;
		}

		if (this.minecraft == null) {
			return false;
		}

		int button = event.button();
		double mouseX = event.x();
		double mouseY = event.y();

		// Only left-click can start equipment model drag
		if (button == 0 && HudManager.getInstance().beginEquipmentModelDrag(mouseX, mouseY, this.width, this.height)) {
			return true;
		}

		long window = this.minecraft.getWindow().handle();
		boolean shift = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
			|| GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
		return HudManager.getInstance().clickHud(mouseX, mouseY, this.width, this.height, button, shift);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (super.keyPressed(event)) {
			return true;
		}

		if (this.minecraft == null) {
			return false;
		}

		if (this.minecraft.options.keyInventory.matches(event)) {
			this.onClose();
			return true;
		}

		// Hotbar keys 1-9 (GLFW_KEY_1 = 49 through GLFW_KEY_9 = 57)
		int keyCode = event.key();
		if (this.minecraft.options.keyDrop.matches(event)) {
			long window = this.minecraft.getWindow().handle();
			boolean dropStack = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS
				|| GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
			double mouseX = HudManager.getInstance().cursorX();
			double mouseY = HudManager.getInstance().cursorY();
			return HudManager.getInstance().dropHoveredSlot(mouseX, mouseY, this.width, this.height, dropStack);
		}

		if (keyCode >= GLFW.GLFW_KEY_1 && keyCode <= GLFW.GLFW_KEY_9) {
			int hotbarSlot = keyCode - GLFW.GLFW_KEY_1; // 0-8
			double mouseX = HudManager.getInstance().cursorX();
			double mouseY = HudManager.getInstance().cursorY();
			return HudManager.getInstance().swapHotbarSlot(mouseX, mouseY, this.width, this.height, hotbarSlot);
		}

		return false;
	}

	@Override
	public boolean mouseDragged(MouseButtonEvent event, double deltaX, double deltaY) {
		if (event.button() == 0 && HudManager.getInstance().dragEquipmentModel(deltaX)) {
			return true;
		}

		return super.mouseDragged(event, deltaX, deltaY);
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		if (event.button() == 0) {
			HudManager.getInstance().endEquipmentModelDrag();
		}
		return super.mouseReleased(event);
	}

}



