package com.zylr.client.hud;

import com.zylr.MinescapeAddon;
import com.zylr.client.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public final class FarmingAlertWidget extends HudWidget {
	private static final int BASE_SIZE = 28;
	private static final Identifier FARMING_ICON = Identifier.fromNamespaceAndPath(
		MinescapeAddon.MOD_ID,
		"textures/gui/runescape/skill/farming.png"
	);

	FarmingAlertWidget(double defaultX, double defaultY, double defaultScale) {
		super("farmingAlertWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return BASE_SIZE; }

	@Override
	protected int baseHeight() { return BASE_SIZE; }

	@Override
	protected boolean shouldRenderWidget(Minecraft minecraft, boolean editMode) {
		HudManager manager = HudManager.getInstance();
		return editMode || (manager.isFarmingAlertEnabled() && FarmingUtil.checkForCompletedTimers());
	}

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isFarmingAlertEnabled();
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		HudManager manager = HudManager.getInstance();
		if (!manager.isFarmingAlertEnabled()) return;
		if (!FarmingUtil.checkForCompletedTimers() && !(minecraft.screen instanceof com.zylr.client.screen.HudEditScreen)) return;

		int x = this.pixelX(minecraft.getWindow().getGuiScaledWidth());
		int y = this.pixelY(minecraft.getWindow().getGuiScaledHeight());
		int size = Math.min(this.pixelWidth(), this.pixelHeight());
		int border = 0xFFBFA882;
		int bg = 0xCC2B1E14;

		graphics.fill(x, y, x + size, y + size, bg);
		graphics.fill(x, y, x + size, y + 1, border);
		graphics.fill(x, y + size - 1, x + size, y + size, border);
		graphics.fill(x, y, x + 1, y + size, border);
		graphics.fill(x + size - 1, y, x + size, y + size, border);

		int padding = Math.max(2, Math.round(size * 0.12F));
		int iconSize = Math.max(1, size - padding * 2);
		graphics.blit(RenderPipelines.GUI_TEXTURED, FARMING_ICON, x + padding, y + padding, 0.0F, 0.0F, iconSize, iconSize, iconSize, iconSize);
	}
}
