package com.zylr.client.screen;

import com.zylr.client.hud.HudManager;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public final class HudSettingsScreen extends Screen {
	private static final int CONTENT_WIDTH = 520;
	private static final int ROW_HEIGHT = 24;
	private static final int ROW_GAP = 4;
	private static final int SECTION_GAP = 12;
	private static final int PADDING = 14;
	private static final int HEADER_HEIGHT = 46;
	private static final int FOOTER_HEIGHT = 38;
	private static final int SCROLL_SPEED = 20;

	private static final int SCREEN_BG = 0xCC0C0A08;
	private static final int PANEL_BG = 0xDD2B1E14;
	private static final int PANEL_INNER = 0xCC3A2818;
	private static final int ROW_BG = 0xAA1B120C;
	private static final int ROW_HOVER = 0xCC4A3320;
	private static final int BORDER = 0xFFBFA882;
	private static final int ACCENT = 0xFFFFD54A;
	private static final int TEXT = 0xFFF0D7B0;
	private static final int MUTED_TEXT = 0xFFBFA882;
	private static final int ON = 0xFF7DFF8A;
	private static final int OFF = 0xFFFF8A80;

	private final Screen parent;
	private final List<SettingRow> settings = new ArrayList<>();
	private final List<RowBounds> rowBounds = new ArrayList<>();
	private double scrollY;
	private int contentStartX;
	private int contentWidth;
	private int viewportTop;
	private int viewportBottom;
	private int totalContentHeight;

	public HudSettingsScreen(Screen parent) {
		super(Component.literal("MineScape Addon Settings"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		this.rebuildSettings();
		this.updateLayoutValues();
		int buttonWidth = 86;
		this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> this.onClose())
			.bounds(this.width / 2 - buttonWidth / 2, this.height - 28, buttonWidth, 20)
			.build());
	}

	@Override
	public void onClose() {
		HudManager.getInstance().save();
		if (this.minecraft != null) {
			this.minecraft.setScreen(this.parent);
		}
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		this.updateLayoutValues();
		this.rowBounds.clear();

		graphics.fill(0, 0, this.width, this.height, SCREEN_BG);
		this.renderPanel(graphics);
		this.renderSettings(graphics, mouseX, mouseY);
		this.renderScrollbar(graphics);
		super.extractRenderState(graphics, mouseX, mouseY, delta);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (super.mouseClicked(event, doubleClick)) {
			return true;
		}
		if (event.button() != 0) {
			return false;
		}

		int mouseX = (int) event.x();
		int mouseY = (int) event.y();
		for (RowBounds bounds : this.rowBounds) {
			if (bounds.contains(mouseX, mouseY)) {
				bounds.setting.toggle();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		this.scrollY -= verticalAmount * SCROLL_SPEED;
		this.clampScroll();
		return true;
	}

	private void rebuildSettings() {
		HudManager manager = HudManager.getInstance();
		this.settings.clear();
		this.settings.add(SettingRow.section("HUD Widgets"));
		this.settings.add(SettingRow.toggle("Side Stat Bars", "Show the Runelite-style side stat bars.", manager::isSideStatBarsEnabled, manager::setSideStatBarsEnabled));
		this.settings.add(SettingRow.toggle("XP Tracker", "Track XP gained during the current session.", manager::isXpTrackerEnabled, manager::setXpTrackerEnabled));
		this.settings.add(SettingRow.toggle("Custom Scoreboard", "Replace the vanilla scoreboard with the addon widget.", manager::isCustomScoreboardEnabled, manager::setCustomScoreboardEnabled));
		this.settings.add(SettingRow.toggle("Custom Chat", "Use the addon chat panel instead of vanilla chat.", manager::isCustomChatEnabled, manager::setCustomChatEnabled));
		this.settings.add(SettingRow.toggle("Minimap", "Show the minimap and click-able orbs.", manager::isMinimapEnabled, manager::setMinimapEnabled));
		this.settings.add(SettingRow.toggle("Browser", "Show the in-game browser widget.", manager::isBrowserEnabled, manager::setBrowserEnabled));
		this.settings.add(SettingRow.toggle("Target Info", "Show the active target HP panel.", manager::isTargetInfoEnabled, manager::setTargetInfoEnabled));

		this.settings.add(SettingRow.section("XP And Levels"));
		this.settings.add(SettingRow.toggle("Virtual Levels", "Show virtual skill levels above 99.", manager::isVirtualLevelsEnabled, enabled -> {
			if (manager.isVirtualLevelsEnabled() != enabled) manager.toggleVirtualLevelsEnabled();
		}));
		this.settings.add(SettingRow.toggle("XP Drop Orbs", "Show floating XP drops when XP is gained.", manager::isXpDropOrbsEnabled, manager::setXpDropOrbsEnabled));

		this.settings.add(SettingRow.section("Gameplay Helpers"));
		this.settings.add(SettingRow.toggle("Farming Alert", "Show ready farming patch alerts.", manager::isFarmingAlertEnabled, manager::setFarmingAlertEnabled));
		this.settings.add(SettingRow.toggle("Low Health Vignette", "Show the red screen edge warning at low HP.", manager::isLowHealthVignetteEnabled, manager::setLowHealthVignetteEnabled));
		this.settings.add(SettingRow.toggle("Bigger Text", "Scale skill tab numbers and stack size overlays by 1.15x. Only use on small HUD's", manager::isBiggerTextEnabled, manager::setBiggerTextEnabled));
		this.settings.add(SettingRow.toggle("Agility Shortcut Outlines", "Highlight configured agility shortcuts.", manager::isAgilityShortcutOutlinesEnabled, manager::setAgilityShortcutOutlinesEnabled));
		this.settings.add(SettingRow.toggle("Custom Mob Outlines", "Highlight configured mobs in-world.", manager::isCustomMobOutlinesEnabled, manager::setCustomMobOutlinesEnabled));
		this.settings.add(SettingRow.toggle("Entity Occlusion Culling", "Hide entities when solid blocks fully block the camera view.", manager::isEntityOcclusionCullingEnabled, manager::setEntityOcclusionCullingEnabled));
		this.settings.add(SettingRow.toggle("Remove Scoreboard Branding", "Hide MineScape branding lines from scoreboard and boss bars.", manager::isScoreboardBrandingRemovalEnabled, manager::setScoreboardBrandingRemovalEnabled));
	}

	private void updateLayoutValues() {
		int margin = 20;
		this.contentWidth = Math.min(CONTENT_WIDTH, Math.max(240, this.width - margin * 2));
		this.contentStartX = (this.width - this.contentWidth) / 2;
		this.viewportTop = HEADER_HEIGHT + 8;
		this.viewportBottom = Math.max(this.viewportTop + 24, this.height - FOOTER_HEIGHT);
		this.totalContentHeight = this.computeTotalContentHeight();
		this.clampScroll();
	}

	private int computeTotalContentHeight() {
		int height = PADDING;
		for (SettingRow setting : this.settings) {
			height += setting.section ? SECTION_GAP + this.font.lineHeight : ROW_HEIGHT + ROW_GAP;
		}
		return height + PADDING;
	}

	private void renderPanel(GuiGraphicsExtractor graphics) {
		int panelBottom = this.height - FOOTER_HEIGHT + 8;
		graphics.fill(this.contentStartX, 18, this.contentStartX + this.contentWidth, panelBottom, PANEL_BG);
		graphics.fill(this.contentStartX + 1, 19, this.contentStartX + this.contentWidth - 1, panelBottom - 1, PANEL_INNER);
		graphics.fill(this.contentStartX, 18, this.contentStartX + this.contentWidth, 19, BORDER);
		graphics.fill(this.contentStartX, panelBottom - 1, this.contentStartX + this.contentWidth, panelBottom, BORDER);
		graphics.fill(this.contentStartX, 18, this.contentStartX + 1, panelBottom, BORDER);
		graphics.fill(this.contentStartX + this.contentWidth - 1, 18, this.contentStartX + this.contentWidth, panelBottom, BORDER);

		graphics.text(this.font, Component.literal("MineScape Addon Settings"), this.contentStartX + PADDING, 28, ACCENT, true);
	}

	private void renderSettings(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int x = this.contentStartX + PADDING;
		int y = this.viewportTop + PADDING - (int) this.scrollY;
		int width = this.contentWidth - PADDING * 2 - 10;

		for (SettingRow setting : this.settings) {
			if (setting.section) {
				y += SECTION_GAP;
				if (y >= this.viewportTop && y + this.font.lineHeight <= this.viewportBottom) {
					graphics.text(this.font, Component.literal(setting.label), x, y, ACCENT, true);
				}
				y += this.font.lineHeight + ROW_GAP;
				continue;
			}

			if (y >= this.viewportTop && y + ROW_HEIGHT <= this.viewportBottom) {
				boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + ROW_HEIGHT;
				this.rowBounds.add(new RowBounds(x, y, width, ROW_HEIGHT, setting));
				this.renderSettingRow(graphics, setting, x, y, width, hovered);
			}
			y += ROW_HEIGHT + ROW_GAP;
		}
	}

	private void renderSettingRow(GuiGraphicsExtractor graphics, SettingRow setting, int x, int y, int width, boolean hovered) {
		graphics.fill(x, y, x + width, y + ROW_HEIGHT, hovered ? ROW_HOVER : ROW_BG);
		graphics.fill(x, y, x + width, y + 1, BORDER);

		boolean enabled = setting.enabled.getAsBoolean();
		String state = enabled ? "ON" : "OFF";
		int switchWidth = 42;
		int switchX = x + width - switchWidth - 8;
		int switchY = y + 5;
		int textWidth = Math.max(40, switchX - x - 16);
		graphics.fill(switchX, switchY, switchX + switchWidth, switchY + 14, 0xCC0C0A08);
		graphics.fill(switchX + 1, switchY + 1, switchX + switchWidth - 1, switchY + 13, enabled ? 0xAA17391C : 0xAA3A1515);
		graphics.text(this.font, Component.literal(this.fitText(setting.label, textWidth)), x + 8, y + 4, TEXT, false);
		graphics.text(this.font, Component.literal(this.fitText(setting.description, textWidth)), x + 8, y + 14, MUTED_TEXT, false);
		graphics.text(this.font, Component.literal(state), switchX + (switchWidth - this.font.width(state)) / 2, switchY + 3, enabled ? ON : OFF, true);
	}

	private String fitText(String text, int maxWidth) {
		if (this.font.width(text) <= maxWidth) return text;
		String ellipsis = "...";
		int ellipsisWidth = this.font.width(ellipsis);
		if (maxWidth <= ellipsisWidth) return ellipsis;
		String trimmed = text;
		while (!trimmed.isEmpty() && this.font.width(trimmed) + ellipsisWidth > maxWidth) {
			trimmed = trimmed.substring(0, trimmed.length() - 1);
		}
		return trimmed + ellipsis;
	}

	private void renderScrollbar(GuiGraphicsExtractor graphics) {
		int maxScroll = this.maxScroll();
		if (maxScroll <= 0) return;

		int trackX = this.contentStartX + this.contentWidth - PADDING - 5;
		int trackHeight = this.viewportBottom - this.viewportTop;
		int thumbHeight = Math.max(20, trackHeight * trackHeight / Math.max(trackHeight, this.totalContentHeight));
		int thumbY = this.viewportTop + (int) ((trackHeight - thumbHeight) * (this.scrollY / maxScroll));
		graphics.fill(trackX, this.viewportTop, trackX + 4, this.viewportBottom, 0x66000000);
		graphics.fill(trackX, thumbY, trackX + 4, thumbY + thumbHeight, BORDER);
	}

	private void clampScroll() {
		this.scrollY = Math.max(0, Math.min(this.scrollY, this.maxScroll()));
	}

	private int maxScroll() {
		return Math.max(0, this.totalContentHeight - (this.viewportBottom - this.viewportTop));
	}

	private record RowBounds(int x, int y, int width, int height, SettingRow setting) {
		boolean contains(int mouseX, int mouseY) {
			return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
		}
	}

	private static final class SettingRow {
		private final String label;
		private final String description;
		private final boolean section;
		private final BooleanSupplier enabled;
		private final Consumer<Boolean> setter;

		private SettingRow(String label, String description, boolean section, BooleanSupplier enabled, Consumer<Boolean> setter) {
			this.label = label;
			this.description = description;
			this.section = section;
			this.enabled = enabled;
			this.setter = setter;
		}

		static SettingRow section(String label) {
			return new SettingRow(label, "", true, () -> false, enabled -> {});
		}

		static SettingRow toggle(String label, String description, BooleanSupplier enabled, Consumer<Boolean> setter) {
			return new SettingRow(label, description, false, enabled, setter);
		}

		void toggle() {
			this.setter.accept(!this.enabled.getAsBoolean());
		}
	}
}
