package com.zylr.client.screen;

import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudWidget;
import com.zylr.client.screen.widget.ContextMenuWidget;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public final class HudEditScreen extends Screen {
	private static final int LAYOUT_BUTTON_BOTTOM_OFFSET = 52;
	private final Screen parent;
	private final ContextMenuWidget contextMenu = new ContextMenuWidget();
	private HudWidget selectedWidget;
	private boolean dragging;
	private boolean resizing;
	private String layoutStatus;

	public HudEditScreen(Screen parent) {
		super(Component.literal("Edit HUD"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		int actionButtonWidth = 86;
		int buttonGap = 8;
		int actionButtonY = this.height - 28;
		int centerX = this.width / 2;
		int actionTotalWidth = actionButtonWidth * 2 + buttonGap;
		int actionStartX = centerX - actionTotalWidth / 2;

		this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> this.onClose())
				.bounds(actionStartX, actionButtonY, actionButtonWidth, 20)
				.build());
		this.addRenderableWidget(Button.builder(Component.literal("Reset"), button -> {
			HudManager.getInstance().resetLayout();
			this.contextMenu.close();
			this.selectedWidget = null;
			this.layoutStatus = "Reset current layout";
		})
				.bounds(actionStartX + actionButtonWidth + buttonGap, actionButtonY, actionButtonWidth, 20)
				.build());

		int layoutButtonWidth = 48;
		int layoutButtonGap = 4;
		int layoutButtonY = this.height - LAYOUT_BUTTON_BOTTOM_OFFSET;
		int layoutTotalWidth = layoutButtonWidth * 6 + layoutButtonGap * 5;
		int layoutStartX = centerX - layoutTotalWidth / 2;
		for (int slot = 0; slot < 3; slot++) {
			int layoutSlot = slot;
			int saveX = layoutStartX + slot * (layoutButtonWidth * 2 + layoutButtonGap * 2);
			int loadX = saveX + layoutButtonWidth + layoutButtonGap;
			this.addRenderableWidget(Button.builder(Component.literal("Save " + (layoutSlot + 1)), button -> {
				HudManager.getInstance().saveLayoutSlot(layoutSlot);
				this.layoutStatus = "Saved layout " + (layoutSlot + 1);
			})
					.bounds(saveX, layoutButtonY, layoutButtonWidth, 20)
					.build());
			this.addRenderableWidget(Button.builder(Component.literal("Load " + (layoutSlot + 1)), button -> {
				if (HudManager.getInstance().loadLayoutSlot(layoutSlot)) {
					this.contextMenu.close();
					this.layoutStatus = "Loaded layout " + (layoutSlot + 1);
				} else {
					this.layoutStatus = "Layout " + (layoutSlot + 1) + " is empty";
				}
			})
					.bounds(loadX, layoutButtonY, layoutButtonWidth, 20)
					.build());
		}
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
		graphics.fill(0, 0, this.width, this.height, 0xAA0C0A08);
		HudManager.getInstance().render(graphics, this.minecraft, mouseX, mouseY, delta, true, this.selectedWidget);

		graphics.text(this.font, Component.literal("Drag widgets. Use corner handle or mouse wheel to rescale."), 12, 10, 0xFFF4DEB5, true);
		graphics.text(this.font, Component.literal("Right-click widgets for options."), 12, 22, 0xFFF4DEB5, true);
		graphics.text(this.font, Component.literal("Edit HUD"), 12, 34, 0xFFDBC48A, true);
		if (this.selectedWidget != null) {
			graphics.text(this.font, Component.literal("Selected: " + this.selectedWidget.id()), 12, 46, 0xFFEFD9A8, true);
			if ("chatWidget".equals(this.selectedWidget.id())) {
				graphics.text(this.font, Component.literal("Edit chat size in Options > Chat Settings."), 12, 58, 0xFFFFD54A, true);
			}
		}
		if (this.layoutStatus != null) {
			int statusWidth = this.font.width(this.layoutStatus);
			int statusX = Math.max(12, this.width / 2 - statusWidth / 2);
			graphics.text(this.font, Component.literal(this.layoutStatus), statusX, this.height - LAYOUT_BUTTON_BOTTOM_OFFSET - 12, 0xFFEFD9A8, true);
		}

		super.extractRenderState(graphics, mouseX, mouseY, delta);
		this.contextMenu.render(graphics, this.font, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (this.contextMenu.isOpen()) {
			return this.contextMenu.handleClick(event.x(), event.y(), event.button());
		}

		if (super.mouseClicked(event, doubleClick)) {
			return true;
		}

		double mouseX = event.x();
		double mouseY = event.y();
		int button = event.button();

		HudWidget widget = HudManager.getInstance().widgetAt(mouseX, mouseY, this.width, this.height);
		if (widget == null) {
			this.selectedWidget = null;
			this.dragging = false;
			this.resizing = false;
			return false;
		}

		this.selectedWidget = widget;
		if (button == 1) {
			this.dragging = false;
			this.resizing = false;
			if (this.openWidgetMenu(widget, (int) mouseX, (int) mouseY)) {
				return true;
			}
			return false;
		}

		if (button == 0) {
			this.resizing = widget.isOnScaleHandle(mouseX, mouseY, this.width, this.height);
			this.dragging = !this.resizing;
			return true;
		}

		return false;
	}

	@Override
	public boolean mouseDragged(MouseButtonEvent event, double deltaX, double deltaY) {
		double mouseX = event.x();
		double mouseY = event.y();
		int button = event.button();
		if (this.contextMenu.isOpen() || this.selectedWidget == null || button != 0) {
			return super.mouseDragged(event, deltaX, deltaY);
		}

		if (this.resizing) {
			if (HudManager.getInstance().tryResizeWidget(this.selectedWidget, deltaY / 120.0D, this.width, this.height)) {
				HudManager.getInstance().save();
			}
			return true;
		}

		if (this.dragging) {
			if (HudManager.getInstance().tryMoveWidget(this.selectedWidget, deltaX, deltaY, this.width, this.height)) {
				HudManager.getInstance().save();
			}
			return true;
		}

		return super.mouseDragged(event, deltaX, deltaY);
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		this.dragging = false;
		this.resizing = false;
		HudManager.getInstance().save();
		return super.mouseReleased(event);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		if (this.contextMenu.isOpen()) {
			return false;
		}

		if (this.selectedWidget != null && this.selectedWidget.contains(mouseX, mouseY, this.width, this.height)) {
			if (HudManager.getInstance().tryResizeWidget(this.selectedWidget, verticalAmount * 0.05D, this.width, this.height)) {
				HudManager.getInstance().save();
			}
			return true;
		}

		return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
	}

	private boolean openWidgetMenu(HudWidget widget, int mouseX, int mouseY) {
		List<ContextMenuWidget.MenuItem> items;
		HudManager manager = HudManager.getInstance();
		switch (widget.id()) {
			case "contentPanel" -> {
				boolean barsVisible = manager.isSideStatBarsEnabled();
				Component label = Component.literal("Side bars: " + (barsVisible ? "Visible" : "Hidden"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setSideStatBarsEnabled(!barsVisible)));
			}
			case "xpTrackerWidget" -> {
				boolean enabled = manager.isXpTrackerEnabled();
				Component label = Component.literal("XP tracker: " + (enabled ? "Enabled" : "Disabled"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setXpTrackerEnabled(!enabled)));
			}
			case "xpDropOrbWidget" -> {
				boolean enabled = manager.isXpDropOrbsEnabled();
				Component label = Component.literal("XP drop orbs: " + (enabled ? "Enabled" : "Disabled"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setXpDropOrbsEnabled(!enabled)));
			}
			case "scoreboardWidget" -> {
				boolean customEnabled = manager.isCustomScoreboardEnabled();
				boolean removeBranding = manager.isScoreboardBrandingRemovalEnabled();
				Component scoreboardLabel = Component.literal("Scoreboard: " + (customEnabled ? "Widget" : "Default"));
				Component brandingLabel = Component.literal("Remove branding: " + (removeBranding ? "On" : "Off"));
				items = List.of(
					ContextMenuWidget.MenuItem.of(scoreboardLabel, () -> manager.setCustomScoreboardEnabled(!customEnabled)),
					ContextMenuWidget.MenuItem.of(brandingLabel, () -> manager.setScoreboardBrandingRemovalEnabled(!removeBranding))
				);
			}
			case "chatWidget" -> {
				boolean enabled = manager.isCustomChatEnabled();
				Component label = Component.literal("Chat: " + (enabled ? "Widget" : "Vanilla"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setCustomChatEnabled(!enabled)));
			}
			case "minimapWidget" -> {
				boolean enabled = manager.isMinimapEnabled();
				Component label = Component.literal("Minimap: " + (enabled ? "Enabled" : "Disabled"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setMinimapEnabled(!enabled)));
			}
			case "browserWidget" -> {
				boolean enabled = manager.isBrowserEnabled();
				Component label = Component.literal("Browser: " + (enabled ? "Enabled" : "Disabled"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setBrowserEnabled(!enabled)));
			}
			case "targetInfoWidget" -> {
				boolean enabled = manager.isTargetInfoEnabled();
				Component label = Component.literal("Target info: " + (enabled ? "Enabled" : "Disabled"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setTargetInfoEnabled(!enabled)));
			}
			case "farmingAlertWidget" -> {
				boolean enabled = manager.isFarmingAlertEnabled();
				Component label = Component.literal("Farming alert: " + (enabled ? "Enabled" : "Disabled"));
				items = List.of(ContextMenuWidget.MenuItem.of(label, () -> manager.setFarmingAlertEnabled(!enabled)));
			}
			default -> {
				return false;
			}
		}

		this.contextMenu.open(mouseX, mouseY, this.width, this.height, this.font, items);
		return true;
	}
}





