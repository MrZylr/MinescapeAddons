package com.zylr.client.hud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zylr.MinescapeAddon;
import com.zylr.client.PerfDebug;
import com.zylr.client.hud.minimap.MinimapWidget;
import com.zylr.client.screen.overridescreens.categories.CustomContainerScreenRegistry;
import com.zylr.client.screen.HudInventoryScreen;
import com.zylr.client.screen.widget.ContextMenuWidget;
import com.zylr.client.skills.SkillType;
import com.zylr.utils.PrivateUseAsciiDecoder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.phys.AABB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Locale;
import java.util.List;
import java.util.UUID;

public final class HudManager {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final HudManager INSTANCE = new HudManager();
	private static final int SAVED_LAYOUT_COUNT = 3;
	private static final List<String> XP_DROP_BOSS_BAR_SYMBOLS = List.of(
		"退", "逈", "逐", "送", "选", "逑", "适", "逊", "递", "逃", "逋", "逓",
		"逄", "逌", "途", "逅", "逍", "逕", "逆", "逎", "逖", "逇", "透", "速"
	);
	private static final long RESOURCE_PACK_OVERLAY_PAUSE_MILLIS = 1_500L;
	private static final int[][] HIGH_ALCH_SLOT_TRANSLATIONS = {
		{2, 29}, {3, 30}, {4, 31}, {5, 32}, {6, 33}, {7, 34}, {8, 35},
		{11, 2}, {12, 3}, {13, 4}, {14, 5}, {15, 6}, {16, 7}, {17, 8},
		{20, 11}, {21, 12}, {22, 13}, {23, 14}, {24, 15}, {25, 16}, {26, 17},
		{29, 20}, {30, 21}, {31, 22}, {32, 23}, {33, 24}, {34, 25}, {35, 26}
	};
	private static final long AFK_THRESHOLD_MILLIS = 300_000L;
	static final double MIN_SCALE = 0.55D;
	static final double MAX_SCALE = 3.0D;
	static final Identifier TAB_STONE = texture("resizeable_mode/tab_stone_middle.png");
	static final Identifier TAB_STONE_SELECTED = texture("resizeable_mode/tab_stone_middle_selected.png");
	static final TabSlot[] TAB_ROW_TOP = {
		new TabSlot(texture("tab/combat.png"), HudTab.COMBAT),
		new TabSlot(texture("tab/stats.png"), HudTab.SKILLS),
		new TabSlot(texture("tab/quests.png"), null),
		new TabSlot(texture("tab/inventory.png"), HudTab.INVENTORY),
		new TabSlot(texture("tab/equipment.png"), HudTab.EQUIPMENT),
		new TabSlot(texture("tab/prayer.png"), null),
		new TabSlot(texture("tab/magic.png"), null)
	};
	static final TabSlot[] TAB_ROW_BOTTOM = {
		new TabSlot(texture("tab/clan_chat.png"), null),
		new TabSlot(texture("tab/friends.png"), null),
		new TabSlot(texture("tab/account_management.png"), null),
		new TabSlot(texture("tab/logout_small.png"), null),
		new TabSlot(texture("tab/options.png"), null),
		new TabSlot(texture("tab/emotes.png"), null),
		new TabSlot(texture("tab/music.png"), null),
	};

	public enum TabType {
		QUESTS(2, true, 1),
		PRAYER(5, true, 4),
		MAGIC(6, true, 3),
		CLAN_CHAT(0, false, -1),
		FRIENDS(1, false, 2),
		ACCOUNT_MANAGEMENT(2, false, -1),
		LOGOUT(3, false, -1),
		OPTIONS(4, false, 0),
		EMOTES(5, false, -1),
		MUSIC(6, false, -1);

		final int slotIndex;
		final boolean isTopRow;
		final int containerSlot;

		TabType(int slotIndex, boolean isTopRow, int containerSlot) {
			this.slotIndex = slotIndex;
			this.isTopRow = isTopRow;
			this.containerSlot = containerSlot;
		}

		static TabType fromPosition(int slotIndex, boolean isTopRow) {
			for (TabType t : values()) {
				if (t.slotIndex == slotIndex && t.isTopRow == isTopRow) return t;
			}
			return null;
		}
	}

	private final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("minescapeaddon-hud.json");
	private final TabStripWidget tabStrip = new TabStripWidget(0.63D, 0.81D, 1.77083D);
	private final ContentPanelWidget contentPanel = new ContentPanelWidget(0.60D, 0.47D, 1.95D);
	private final ScoreboardWidget scoreboardWidget = new ScoreboardWidget(0.82D, 0.16D, 1.05D);
	private final XpTrackerWidget xpTrackerWidget = new XpTrackerWidget(0.03D, 0.16D, 1.5D);
	private final XpDropOrbWidget xpDropOrbWidget = new XpDropOrbWidget(0.43D, 0.03D, 1.0D);
	private final ChatWidget chatWidget = new ChatWidget(0.02D, 0.76D, 1.0D);
	private final MinimapWidget minimapWidget = new MinimapWidget(0.73D, 0.03D, 1.0D);
	private final BrowserHudWidget browserWidget = new BrowserHudWidget(0.03D, 0.03D, 1.0D);
	private final BarrowsTrackerWidget barrowsTrackerWidget = new BarrowsTrackerWidget(0.03D, 0.34D, 1.0D);
	private final TargetInfoWidget targetInfoWidget = new TargetInfoWidget(0.43D, 0.08D, 1.0D);
	private final FarmingAlertWidget farmingAlertWidget = new FarmingAlertWidget(0.03D, 0.43D, 1.0D);
	private final ClueHelperWidget clueHelperWidget = new ClueHelperWidget(0.50D, 0.03D, 1.0D);
	private final List<HudWidget> widgets = List.of(this.contentPanel, this.scoreboardWidget, this.xpTrackerWidget, this.xpDropOrbWidget, this.chatWidget, this.minimapWidget, this.browserWidget, this.barrowsTrackerWidget, this.targetInfoWidget, this.farmingAlertWidget, this.clueHelperWidget);
	private final ContextMenuWidget runtimeContextMenu = new ContextMenuWidget();
	private final EnumMap<SkillType, Long> skillXpSessionStartMillis = new EnumMap<>(SkillType.class);
	private final EnumMap<SkillType, Double> skillXpSessionGained = new EnumMap<>(SkillType.class);
	private final List<XpPauseInterval> xpPauseIntervals = new ArrayList<>();
	private final HudLayout[] savedLayouts = new HudLayout[SAVED_LAYOUT_COUNT];
	long totalXpSessionStartMillis = 0L;
	double totalXpSessionGained = 0.0D;
	private long xpTrackerPauseStartedMillis = 0L;
	private long resourcePackOverlayPauseUntilMillis = 0L;
	private long lastActivityMillis = System.currentTimeMillis();
	private double lastPlayerX;
	private double lastPlayerY;
	private double lastPlayerZ;
	private float lastPlayerYRot;
	private float lastPlayerXRot;
	private boolean hasLastPlayerState;

	private HudTab selectedTab = HudTab.COMBAT;
	private boolean sideStatBarsEnabled = true;
	private boolean xpTrackerEnabled = true;
	private boolean customScoreboardEnabled = true;
	private boolean customChatEnabled = true;
	private boolean minimapEnabled = true;
	private boolean browserEnabled = true;
	private boolean runOrbSprintEnabled = true;
	private boolean scoreboardBrandingRemovalEnabled = false;
	private boolean virtualLevelsEnabled = false;
	private boolean armorOverridesEnabled = true;
	private boolean capeOverridesEnabled = true;
	private boolean targetInfoEnabled = true;
	private boolean farmingAlertEnabled = true;
	private boolean xpDropOrbsEnabled = true;
	private boolean agilityShortcutOutlinesEnabled = false;
	private boolean customMobOutlinesEnabled = false;
	private boolean entityOcclusionCullingEnabled = true;
	private boolean lowHealthVignetteEnabled = true;
	private boolean performanceDebugEnabled = false;
	private boolean barrowsLootResetPending = false;
	private SkillType trackedXpSkill = null;
	private UUID targetUuid = null;
	private int targetTotalHp = 0;
	private UUID cachedTargetUuid = null;
	private Entity cachedTargetEntity = null;
	private TargetInfo cachedTargetInfo = null;
	private long cachedTargetInfoMillis = 0L;
	private double cursorX;
	private double cursorY;
	private boolean highAlchContainerMode = false;
	private HudTab highAlchPreviousTab = null;

	private HudManager() {
		this.resetXpSessionTracking();
	}

	public static HudManager getInstance() { return INSTANCE; }

	public void load() {
		if (!Files.exists(this.configPath)) { this.save(); return; }
		try {
			HudConfig config = GSON.fromJson(Files.readString(this.configPath), HudConfig.class);
			if (config == null) return;
			boolean shouldSaveMissingConfigDefaults = config.agilityShortcutOutlinesEnabled == null
				|| config.customMobOutlinesEnabled == null
				|| config.entityOcclusionCullingEnabled == null
				|| config.lowHealthVignetteEnabled == null;
			if (config.selectedTab != null) this.selectedTab = config.selectedTab;
			this.sideStatBarsEnabled = config.sideStatBarsEnabled;
			this.xpTrackerEnabled = config.xpTrackerEnabled;
			this.customScoreboardEnabled = config.customScoreboardEnabled;
			this.customChatEnabled = config.customChatEnabled;
			this.minimapEnabled = config.minimapEnabled;
			this.browserEnabled = config.browserEnabled == null || config.browserEnabled;
			this.runOrbSprintEnabled = config.runOrbSprintEnabled;
			this.scoreboardBrandingRemovalEnabled = config.scoreboardBrandingRemovalEnabled;
			this.virtualLevelsEnabled = config.virtualLevelsEnabled;
			this.armorOverridesEnabled = config.armorOverridesEnabled == null || config.armorOverridesEnabled;
			this.capeOverridesEnabled = config.capeOverridesEnabled == null || config.capeOverridesEnabled;
			this.targetInfoEnabled = config.targetInfoEnabled == null || config.targetInfoEnabled;
			this.farmingAlertEnabled = config.farmingAlertEnabled == null || config.farmingAlertEnabled;
			this.xpDropOrbsEnabled = config.xpDropOrbsEnabled == null || config.xpDropOrbsEnabled;
			this.agilityShortcutOutlinesEnabled = config.agilityShortcutOutlinesEnabled != null && config.agilityShortcutOutlinesEnabled;
			this.customMobOutlinesEnabled = config.customMobOutlinesEnabled != null && config.customMobOutlinesEnabled;
			this.entityOcclusionCullingEnabled = config.entityOcclusionCullingEnabled == null || config.entityOcclusionCullingEnabled;
			this.lowHealthVignetteEnabled = config.lowHealthVignetteEnabled == null || config.lowHealthVignetteEnabled;
			this.performanceDebugEnabled = config.performanceDebugEnabled != null && config.performanceDebugEnabled;
			PerfDebug.setEnabled(this.performanceDebugEnabled);
			this.trackedXpSkill = parseTrackedXpSkill(config.trackedXpSkill);
			if (config.tabStrip != null) this.tabStrip.apply(config.tabStrip);
			if (config.contentPanel != null) this.contentPanel.apply(config.contentPanel);
			if (config.scoreboardWidget != null) this.scoreboardWidget.apply(config.scoreboardWidget);
			if (config.xpTrackerWidget != null) this.xpTrackerWidget.apply(config.xpTrackerWidget);
			if (config.xpDropOrbWidget != null) this.xpDropOrbWidget.apply(config.xpDropOrbWidget);
			if (config.chatWidget != null) this.chatWidget.apply(config.chatWidget);
			if (config.minimapWidget != null) this.minimapWidget.apply(config.minimapWidget);
			if (config.browserWidget != null) this.browserWidget.apply(config.browserWidget);
			if (config.barrowsTrackerWidget != null) this.barrowsTrackerWidget.apply(config.barrowsTrackerWidget);
			if (config.targetInfoWidget != null) this.targetInfoWidget.apply(config.targetInfoWidget);
			if (config.farmingAlertWidget != null) this.farmingAlertWidget.apply(config.farmingAlertWidget);
			if (config.clueHelperWidget != null) this.clueHelperWidget.apply(config.clueHelperWidget);
			this.loadSavedLayouts(config.savedLayouts);
			if (shouldSaveMissingConfigDefaults) this.save();
		} catch (IOException exception) {
			MinescapeAddon.LOGGER.warn("Failed to load HUD config", exception);
		}
	}

	public void save() {
		try {
			Files.createDirectories(this.configPath.getParent());
			Files.writeString(this.configPath, GSON.toJson(this.captureConfig()));
		} catch (IOException exception) {
			MinescapeAddon.LOGGER.warn("Failed to save HUD config", exception);
		}
	}

	public void resetLayout() {
		this.selectedTab = HudTab.COMBAT;
		this.sideStatBarsEnabled = true;
		this.xpTrackerEnabled = true;
		this.customScoreboardEnabled = true;
		this.customChatEnabled = true;
		this.minimapEnabled = true;
		this.browserEnabled = true;
		this.runOrbSprintEnabled = true;
		this.scoreboardBrandingRemovalEnabled = false;
		this.virtualLevelsEnabled = false;
		this.armorOverridesEnabled = true;
		this.capeOverridesEnabled = true;
		this.targetInfoEnabled = true;
		this.farmingAlertEnabled = true;
		this.xpDropOrbsEnabled = true;
		this.agilityShortcutOutlinesEnabled = false;
		this.customMobOutlinesEnabled = false;
		this.entityOcclusionCullingEnabled = true;
		this.lowHealthVignetteEnabled = true;
		this.performanceDebugEnabled = false;
		PerfDebug.setEnabled(false);
		this.trackedXpSkill = null;
		this.tabStrip.reset();
		this.contentPanel.reset();
		this.scoreboardWidget.reset();
		this.xpTrackerWidget.reset();
		this.xpDropOrbWidget.reset();
		this.chatWidget.reset();
		this.minimapWidget.reset();
		this.browserWidget.reset();
		this.barrowsTrackerWidget.reset();
		this.targetInfoWidget.reset();
		this.farmingAlertWidget.reset();
		this.clueHelperWidget.reset();
		this.barrowsTrackerWidget.resetTracker();
		this.clearTarget();
		this.resetXpSessionTracking();
		this.save();
	}

	public boolean hasSavedLayout(int slot) {
		return isValidSavedLayoutSlot(slot) && this.savedLayouts[slot] != null;
	}

	public boolean saveLayoutSlot(int slot) {
		if (!isValidSavedLayoutSlot(slot)) return false;
		this.savedLayouts[slot] = this.captureLayout();
		this.save();
		return true;
	}

	public boolean loadLayoutSlot(int slot) {
		if (!this.hasSavedLayout(slot)) return false;
		this.applyLayout(this.savedLayouts[slot]);
		this.save();
		return true;
	}

	public void resetXpSessionTracking() {
		this.totalXpSessionGained = 0.0D;
		this.totalXpSessionStartMillis = 0L;
		this.xpPauseIntervals.clear();
		this.xpTrackerPauseStartedMillis = 0L;
		this.resourcePackOverlayPauseUntilMillis = 0L;
		this.recordActivity();
		for (SkillType skill : SkillType.values()) {
			this.skillXpSessionGained.put(skill, 0.0D);
			this.skillXpSessionStartMillis.put(skill, 0L);
		}
	}

	public void resetTotalXpTracker() {
		this.totalXpSessionGained = 0.0D;
		this.totalXpSessionStartMillis = 0L;
	}

	public void resetSkillXpTracker(SkillType skill) {
		if (skill == null) return;
		this.skillXpSessionGained.put(skill, 0.0D);
		this.skillXpSessionStartMillis.put(skill, 0L);
	}

	public void addGameplayXp(SkillType skill, double amount) {
		if (skill == null || amount <= 0.0D) return;
		long now = System.currentTimeMillis();
		this.recordActivity();
		if (this.totalXpSessionStartMillis <= 0L) this.totalXpSessionStartMillis = now;
		this.totalXpSessionGained += amount;
		if (this.skillXpSessionStartMillis.getOrDefault(skill, 0L) <= 0L) {
			this.skillXpSessionStartMillis.put(skill, now);
		}
		this.skillXpSessionGained.put(skill, this.skillXpSessionGained.getOrDefault(skill, 0.0D) + amount);
		if (this.xpDropOrbsEnabled) this.xpDropOrbWidget.addDrop(skill, amount);
	}

	public void tickXpTrackerPause(Minecraft minecraft) {
		long now = System.currentTimeMillis();
		this.tickAfkState(minecraft, now);
		if (!shouldPauseXpTracker(minecraft, now)) {
			if (this.xpTrackerPauseStartedMillis > 0L) {
				this.xpPauseIntervals.add(new XpPauseInterval(this.xpTrackerPauseStartedMillis, now));
				this.xpTrackerPauseStartedMillis = 0L;
			}
			return;
		}
		if (this.xpTrackerPauseStartedMillis <= 0L) {
			this.xpTrackerPauseStartedMillis = now;
		}
	}

	public void pauseXpTrackerForResourcePackOverlay() {
		this.resourcePackOverlayPauseUntilMillis = System.currentTimeMillis() + RESOURCE_PACK_OVERLAY_PAUSE_MILLIS;
	}

	public boolean shouldHideVanillaScoreboard() { return this.customScoreboardEnabled; }

	public HudTab getSelectedTab() { return this.selectedTab; }
	public boolean isSideStatBarsEnabled() { return this.sideStatBarsEnabled; }
	public boolean isXpTrackerEnabled() { return this.xpTrackerEnabled; }
	public boolean isCustomScoreboardEnabled() { return this.customScoreboardEnabled; }
	public boolean isCustomChatEnabled() { return this.customChatEnabled; }
	public boolean isMinimapEnabled() { return this.minimapEnabled; }
	public boolean isBrowserEnabled() { return this.browserEnabled; }
	public boolean isRunOrbSprintEnabled() { return this.runOrbSprintEnabled; }
	public boolean isScoreboardBrandingRemovalEnabled() { return this.scoreboardBrandingRemovalEnabled; }
	public boolean isVirtualLevelsEnabled() { return this.virtualLevelsEnabled; }
	public boolean isArmorOverridesEnabled() { return this.armorOverridesEnabled; }
	public boolean isCapeOverridesEnabled() { return this.capeOverridesEnabled; }
	public boolean isTargetInfoEnabled() { return this.targetInfoEnabled; }
	public boolean isFarmingAlertEnabled() { return this.farmingAlertEnabled; }
	public boolean isXpDropOrbsEnabled() { return this.xpDropOrbsEnabled; }
	public boolean isAgilityShortcutOutlinesEnabled() { return this.agilityShortcutOutlinesEnabled; }
	public boolean isCustomMobOutlinesEnabled() { return this.customMobOutlinesEnabled; }
	public boolean isEntityOcclusionCullingEnabled() { return this.entityOcclusionCullingEnabled; }
	public boolean isLowHealthVignetteEnabled() { return this.lowHealthVignetteEnabled; }
	public boolean isPerformanceDebugEnabled() { return this.performanceDebugEnabled; }
	public SkillType getTrackedXpSkill() { return this.trackedXpSkill; }
	public boolean isHighAlchContainerMode() { return this.highAlchContainerMode; }
	ContentPanelWidget contentPanelWidget() { return this.contentPanel; }

	public int vanillaBottomHudOffsetX(int screenWidth, int screenHeight) {
		int hudLeft = screenWidth / 2 - 91;
		int hudRight = hudLeft + 182;
		int contentPanelCenterX = this.contentPanel.pixelX(screenWidth) + this.contentPanel.pixelWidth() / 2;
		if (contentPanelCenterX < screenWidth / 2) {
			return this.contentPanel.rightBound(screenWidth, screenHeight) - hudLeft;
		}
		return this.contentPanel.leftBound(screenWidth, screenHeight) - hudRight;
	}

	public int vanillaBottomHudOffsetY(int screenWidth, int screenHeight) {
		int hudTop = screenHeight - 59;
		int hudBottom = screenHeight;
		int contentPanelCenterY = this.contentPanel.pixelY(screenHeight) + this.contentPanel.pixelHeight() / 2;
		if (contentPanelCenterY < screenHeight / 2) {
			return this.contentPanel.topBound(screenWidth, screenHeight) - hudTop;
		}
		return this.contentPanel.bottomBound(screenWidth, screenHeight) - hudBottom;
	}

	public void toggleSideStatBars() {
		this.sideStatBarsEnabled = !this.sideStatBarsEnabled;
		this.save();
	}

	public void setSideStatBarsEnabled(boolean enabled) {
		if (this.sideStatBarsEnabled == enabled) return;
		this.sideStatBarsEnabled = enabled;
		this.save();
	}

	public void setXpTrackerEnabled(boolean enabled) {
		if (this.xpTrackerEnabled == enabled) return;
		this.xpTrackerEnabled = enabled;
		this.save();
	}

	public void setCustomScoreboardEnabled(boolean enabled) {
		if (this.customScoreboardEnabled == enabled) return;
		this.customScoreboardEnabled = enabled;
		this.save();
	}

	public void setCustomChatEnabled(boolean enabled) {
		if (this.customChatEnabled == enabled) return;
		this.customChatEnabled = enabled;
		this.save();
	}

	public void setMinimapEnabled(boolean enabled) {
		if (this.minimapEnabled == enabled) return;
		this.minimapEnabled = enabled;
		this.save();
	}

	public void setBrowserEnabled(boolean enabled) {
		if (this.browserEnabled == enabled) return;
		this.browserEnabled = enabled;
		this.save();
	}

	public void setRunOrbSprintEnabled(boolean enabled) {
		if (this.runOrbSprintEnabled == enabled) return;
		this.runOrbSprintEnabled = enabled;
		if (!enabled) {
			Minecraft minecraft = Minecraft.getInstance();
			if (minecraft != null && minecraft.player != null) minecraft.player.setSprinting(false);
			if (minecraft != null && minecraft.options != null) minecraft.options.keySprint.setDown(false);
		}
		this.save();
	}

	public void toggleRunOrbSprintEnabled() {
		this.setRunOrbSprintEnabled(!this.runOrbSprintEnabled);
	}

	public void setScoreboardBrandingRemovalEnabled(boolean enabled) {
		if (this.scoreboardBrandingRemovalEnabled == enabled) return;
		this.scoreboardBrandingRemovalEnabled = enabled;
		this.save();
	}

	public void setLowHealthVignetteEnabled(boolean enabled) {
		if (this.lowHealthVignetteEnabled == enabled) return;
		this.lowHealthVignetteEnabled = enabled;
		this.save();
	}

	public void toggleVirtualLevelsEnabled() {
		this.virtualLevelsEnabled = !this.virtualLevelsEnabled;
		this.save();
	}

	public void setArmorOverridesEnabled(boolean enabled) {
		if (this.armorOverridesEnabled == enabled) return;
		this.armorOverridesEnabled = enabled;
		this.save();
	}

	public void setCapeOverridesEnabled(boolean enabled) {
		if (this.capeOverridesEnabled == enabled) return;
		this.capeOverridesEnabled = enabled;
		this.save();
	}

	public void setTargetInfoEnabled(boolean enabled) {
		if (this.targetInfoEnabled == enabled) return;
		this.targetInfoEnabled = enabled;
		if (!enabled) this.clearTarget();
		this.save();
	}

	public void setFarmingAlertEnabled(boolean enabled) {
		if (this.farmingAlertEnabled == enabled) return;
		this.farmingAlertEnabled = enabled;
		this.save();
	}

	public void setXpDropOrbsEnabled(boolean enabled) {
		if (this.xpDropOrbsEnabled == enabled) return;
		this.xpDropOrbsEnabled = enabled;
		if (!enabled) this.xpDropOrbWidget.clearDrops();
		this.save();
	}

	public void setAgilityShortcutOutlinesEnabled(boolean enabled) {
		if (this.agilityShortcutOutlinesEnabled == enabled) return;
		this.agilityShortcutOutlinesEnabled = enabled;
		this.save();
	}

	public void setCustomMobOutlinesEnabled(boolean enabled) {
		if (this.customMobOutlinesEnabled == enabled) return;
		this.customMobOutlinesEnabled = enabled;
		this.save();
	}

	public void setEntityOcclusionCullingEnabled(boolean enabled) {
		if (this.entityOcclusionCullingEnabled == enabled) return;
		this.entityOcclusionCullingEnabled = enabled;
		this.save();
	}

	public void setPerformanceDebugEnabled(boolean enabled) {
		if (this.performanceDebugEnabled == enabled) return;
		this.performanceDebugEnabled = enabled;
		PerfDebug.setEnabled(enabled);
		this.save();
	}

	public void setTrackedXpSkill(SkillType skill) {
		if (this.trackedXpSkill == skill) return;
		this.trackedXpSkill = skill;
		this.save();
	}

	public void selectTab(HudTab tab) {
		if (tab == null || tab == this.selectedTab) return;
		this.selectedTab = tab;
		this.save();
	}

	public void beginHighAlchContainerMode() {
		if (!this.highAlchContainerMode) {
			this.highAlchPreviousTab = this.selectedTab;
		}
		this.highAlchContainerMode = true;
		this.selectedTab = HudTab.INVENTORY;
	}

	public void endHighAlchContainerMode() {
		if (!this.highAlchContainerMode) {
			return;
		}
		this.highAlchContainerMode = false;
		if (this.highAlchPreviousTab != null) {
			this.selectedTab = this.highAlchPreviousTab;
		}
		this.highAlchPreviousTab = null;
	}

	public boolean isContentPanelTabHit(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		return this.contentPanel.getClickedTabType(mouseX, mouseY, screenWidth, screenHeight) != null
			|| this.contentPanel.tabAt(mouseX, mouseY, screenWidth, screenHeight) != null;
	}

	public void updateCursor(double x, double y) {
		if (x != this.cursorX || y != this.cursorY) this.recordActivity();
		this.cursorX = x;
		this.cursorY = y;
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft != null && !minecraft.mouseHandler.isMouseGrabbed()) {
			this.browserWidget.mouseMoved(x, y, minecraft.getWindow().getGuiScaledWidth(), minecraft.getWindow().getGuiScaledHeight(), minecraft);
		}
	}

	public void recordActivity() {
		this.lastActivityMillis = System.currentTimeMillis();
	}
	public double cursorX() { return this.cursorX; }
	public double cursorY() { return this.cursorY; }
	public List<HudWidget> widgets() { return this.widgets; }
	public ChatWidget chatWidget() { return this.chatWidget; }
	public BrowserHudWidget browserHudWidget() { return this.browserWidget; }
	public boolean isRuntimeContextMenuOpen() { return this.runtimeContextMenu.isOpen(); }
	public void recordBarrowsKill(String entityName) { this.barrowsTrackerWidget.recordKill(entityName); }
	public void markBarrowsLootContainerOpened() { this.barrowsLootResetPending = true; }
	public boolean hasTarget() { return this.targetUuid != null && this.targetTotalHp > 0; }

	public void setTarget(UUID uuid, int totalHp) {
		if (uuid == null || totalHp <= 0) {
			this.clearTarget();
			return;
		}
		if (!uuid.equals(this.targetUuid) || totalHp != this.targetTotalHp) {
			this.clearTargetCache();
		}
		this.targetUuid = uuid;
		this.targetTotalHp = totalHp;
	}

	public void clearTarget() {
		this.targetUuid = null;
		this.targetTotalHp = 0;
		this.clearTargetCache();
	}

	public void recordTargetDeath(UUID uuid) {
		if (uuid == null || uuid.equals(this.targetUuid)) this.clearTarget();
	}

	public TargetInfo getTargetInfo(Minecraft minecraft) {
		if (!this.targetInfoEnabled || !this.hasTarget() || minecraft == null || minecraft.level == null) return null;
		long now = System.currentTimeMillis();
		if (this.cachedTargetInfo != null && now - this.cachedTargetInfoMillis < 100L) {
			return this.cachedTargetInfo;
		}
		Entity target = this.findTargetEntity(minecraft);
		TargetInfo info = TargetInfoWidget.resolve(minecraft, target, this.targetTotalHp);
		if (info == null) this.clearTarget();
		this.cachedTargetInfo = info;
		this.cachedTargetInfoMillis = now;
		return info;
	}

	public void tickBarrowsLootReset(Minecraft minecraft) {
		if (!this.barrowsLootResetPending || minecraft == null || minecraft.player == null) return;
		if (minecraft.player.getY() <= 30.0D) return;
		this.barrowsLootResetPending = false;
		this.barrowsTrackerWidget.resetTracker();
	}

	public HudWidget widgetAt(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		for (int index = this.widgets.size() - 1; index >= 0; index--) {
			HudWidget widget = this.widgets.get(index);
			if (!widget.shouldRenderWidget(Minecraft.getInstance(), true)) continue;
			if (widget.contains(mouseX, mouseY, screenWidth, screenHeight)) return widget;
		}
		return null;
	}

	public boolean tryMoveWidget(HudWidget widget, double deltaX, double deltaY, int screenWidth, int screenHeight) {
		if (widget == null) return false;
		WidgetState original = widget.snapshot();
		int originalOverlap = this.totalOverlapArea(widget, screenWidth, screenHeight);
		widget.moveBy(deltaX, deltaY, screenWidth, screenHeight);
		int newOverlap = this.totalOverlapArea(widget, screenWidth, screenHeight);
		if (originalOverlap == 0 && newOverlap > 0) {
			widget.apply(original);
			return false;
		}
		return true;
	}

	public boolean tryResizeWidget(HudWidget widget, double deltaScale, int screenWidth, int screenHeight) {
		if (widget == null) return false;
		WidgetState original = widget.snapshot();
		int originalOverlap = this.totalOverlapArea(widget, screenWidth, screenHeight);
		widget.resizeBy(deltaScale, screenWidth, screenHeight);
		int newOverlap = this.totalOverlapArea(widget, screenWidth, screenHeight);
		if ((originalOverlap == 0 && newOverlap > 0) || (originalOverlap > 0 && newOverlap > originalOverlap)) {
			widget.apply(original);
			return false;
		}
		return true;
	}

	public boolean clickHud(double mouseX, double mouseY, int screenWidth, int screenHeight, int button, boolean shift) {
		Minecraft minecraft = Minecraft.getInstance();
		if (this.runtimeContextMenu.isOpen()) {
			return this.runtimeContextMenu.handleClick(mouseX, mouseY, button);
		}
		if (button == 1 && this.isContentPanelInteractive(minecraft) && this.tryOpenSkillTrackerMenu(mouseX, mouseY, screenWidth, screenHeight)) {
			return true;
		}
		if (button == 0) {
			if (minecraft != null && !minecraft.mouseHandler.isMouseGrabbed() && this.browserWidget.click(mouseX, mouseY, screenWidth, screenHeight, button, minecraft)) {
				return true;
			}
			if (minecraft != null && this.barrowsTrackerWidget.clickResetButton(minecraft, mouseX, mouseY, screenWidth, screenHeight)) {
				return true;
			}
			boolean cursorInteractive = minecraft == null || minecraft.screen != null || !minecraft.mouseHandler.isMouseGrabbed();
			if (cursorInteractive && this.minimapEnabled && this.minimapWidget.clickPrayerOrb(mouseX, mouseY, screenWidth, screenHeight)) {
				this.emulateSwapHandsKeybind();
				return true;
			}
			if (cursorInteractive && this.minimapEnabled && this.minimapWidget.clickRunOrb(mouseX, mouseY, screenWidth, screenHeight)) {
				this.toggleRunOrbSprintEnabled();
				return true;
			}
			if (!this.isContentPanelInteractive(minecraft)) {
				return false;
			}
			TabType tabType = this.contentPanel.getClickedTabType(mouseX, mouseY, screenWidth, screenHeight);
			if (tabType != null) { handleSpecialTabClick(tabType); return true; }
		}
		if (!this.isContentPanelInteractive(minecraft)) {
			return false;
		}
		HudTab clicked = this.contentPanel.tabAt(mouseX, mouseY, screenWidth, screenHeight);
		HudTab activeTab = this.selectedTab;
		if (activeTab == HudTab.SKILLS && button == 0) {
			int skillIndex = this.contentPanel.skillAt(mouseX, mouseY);
			if (skillIndex >= 0) { this.clickContainerSlot(1, 1); return true; }
		}
		if (clicked == null) {
			if (activeTab == HudTab.COMBAT && button == 0 && this.contentPanel.isCombatIconHovered(mouseX, mouseY)) {
				this.clickContainerSlot(27, 0);
				return true;
			}
			if (activeTab == HudTab.INVENTORY) {
				if (this.highAlchContainerMode) {
					int slotIndex = this.contentPanel.inventorySlotAt(mouseX, mouseY);
					if (slotIndex >= 0) {
						this.clickContainerSlot(translateHighAlchSlot(slotIndex), button);
						return true;
					}
				}
				int slotIndex = this.contentPanel.inventorySlotAt(mouseX, mouseY);
				if (slotIndex >= 0) { this.contentPanel.clickInventorySlot(slotIndex, button, shift); return true; }
			}
			if (activeTab == HudTab.EQUIPMENT) {
				int inventorySlot = this.contentPanel.equipmentSlotAt(mouseX, mouseY);
				if (inventorySlot >= 0) { this.contentPanel.clickInventorySlot(inventorySlot, button, shift); return true; }
			}
			return false;
		}
			if (button == 0) { this.selectTab(clicked); return true; }
		return false;
	}

	private boolean isContentPanelInteractive(Minecraft minecraft) {
		return minecraft != null
			&& (minecraft.screen instanceof HudInventoryScreen
			|| CustomContainerScreenRegistry.isCustomContainerScreen(minecraft.screen));
	}

	public boolean scrollHud(double mouseX, double mouseY, int screenWidth, int screenHeight, double verticalAmount) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null) return false;
		if (minecraft.mouseHandler.isMouseGrabbed()) return false;
		return this.browserWidget.scroll(mouseX, mouseY, screenWidth, screenHeight, verticalAmount, minecraft);
	}

	private void handleSpecialTabClick(TabType tabType) {
		switch (tabType) {
			case QUESTS -> clickContainerSlot(1, 0);
			case PRAYER -> clickContainerSlot(4, 0);
			case MAGIC -> clickContainerSlot(3, 0);
			case CLAN_CHAT -> {}
			case FRIENDS -> clickContainerSlot(2, 0);
			case ACCOUNT_MANAGEMENT -> {}
			case LOGOUT -> disconnectFromServer();
			case OPTIONS -> clickContainerSlot(0, 0);
			case EMOTES -> clickContainerSlot(2, 1);
			case MUSIC -> {}
		}
	}

	private void emulateSwapHandsKeybind() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.options == null) return;
		minecraft.execute(() -> {
			if (minecraft.player == null || minecraft.getConnection() == null) return;
			// send server packet
			minecraft.getConnection().send(new ServerboundPlayerActionPacket(
					ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN));
			}
		);
	}

	private void clickContainerSlot(int slot, int clickType) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.player == null || minecraft.gameMode == null) return;
		if (minecraft.player.containerMenu == null) return;
		int mouseButton = clickType == 1 ? 1 : 0;
		minecraft.gameMode.handleContainerInput(
			minecraft.player.containerMenu.containerId, slot, mouseButton,
			net.minecraft.world.inventory.ContainerInput.PICKUP, minecraft.player);
	}

	private static int translateHighAlchSlot(int slot) {
		for (int[] translation : HIGH_ALCH_SLOT_TRANSLATIONS) {
			if (translation[0] == slot) {
				return translation[1];
			}
		}
		return slot;
	}

	private void disconnectFromServer() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft != null) minecraft.disconnectFromWorld(Component.literal("Logged out"));
	}

	public boolean swapHotbarSlot(double mouseX, double mouseY, int screenWidth, int screenHeight, int hotbarSlot) {
		HudTab activeTab = this.selectedTab;
		if (activeTab == HudTab.INVENTORY) {
			int slotIndex = this.contentPanel.inventorySlotAt(mouseX, mouseY);
			if (slotIndex >= 0) { this.contentPanel.swapWithHotbar(slotIndex, hotbarSlot); return true; }
		}
		if (activeTab == HudTab.EQUIPMENT) {
			int inventorySlot = this.contentPanel.equipmentSlotAt(mouseX, mouseY);
			if (inventorySlot >= 0) { this.contentPanel.swapWithHotbar(inventorySlot, hotbarSlot); return true; }
		}
		return false;
	}

	public boolean dropHoveredSlot(double mouseX, double mouseY, int screenWidth, int screenHeight, boolean dropStack) {
		HudTab activeTab = this.selectedTab;
		if (activeTab == HudTab.INVENTORY) {
			int slotIndex = this.contentPanel.inventorySlotAt(mouseX, mouseY);
			if (slotIndex >= 0) {
				this.contentPanel.dropInventorySlot(slotIndex, dropStack);
				return true;
			}
		}
		if (activeTab == HudTab.EQUIPMENT) {
			int inventorySlot = this.contentPanel.equipmentSlotAt(mouseX, mouseY);
			if (inventorySlot >= 0) {
				this.contentPanel.dropInventorySlot(inventorySlot, dropStack);
				return true;
			}
		}
		return false;
	}

	public boolean beginEquipmentModelDrag(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		if (this.selectedTab != HudTab.EQUIPMENT) return false;
		return this.contentPanel.beginEquipmentModelDrag(mouseX, mouseY, screenWidth, screenHeight);
	}

	public boolean dragEquipmentModel(double deltaX) {
		if (this.selectedTab != HudTab.EQUIPMENT) return false;
		return this.contentPanel.dragEquipmentModel(deltaX);
	}

	public void endEquipmentModelDrag() { this.contentPanel.endEquipmentModelDrag(); }

	public void render(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta, boolean editMode, HudWidget selectedWidget) {
		for (HudWidget widget : this.widgets) {
			widget.render(graphics, minecraft, mouseX, mouseY, delta, editMode, widget == selectedWidget);
		}
		if (!editMode) this.renderAfkTimer(graphics, minecraft);
	}

	public void renderInventoryPanelForBank(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		HudTab previous = this.selectedTab;
		this.selectedTab = HudTab.INVENTORY;
		this.contentPanel.render(graphics, minecraft, mouseX, mouseY, delta, false, false);
		this.selectedTab = previous;
	}

	public boolean clickInventoryPanelForBank(double mouseX, double mouseY, int screenWidth, int screenHeight, int button, boolean shift) {
		HudTab previous = this.selectedTab;
		this.selectedTab = HudTab.INVENTORY;
		int slotIndex = this.contentPanel.inventorySlotAt(mouseX, mouseY);
		if (slotIndex >= 0) {
			this.selectedTab = previous;
			return true;
		}
		this.selectedTab = previous;
		return false;
	}

	public int inventoryPanelSlotIndexForBank(double mouseX, double mouseY) {
		HudTab previous = this.selectedTab;
		this.selectedTab = HudTab.INVENTORY;
		int slotIndex = this.contentPanel.inventorySlotAt(mouseX, mouseY);
		this.selectedTab = previous;
		return slotIndex;
	}

	public void renderRuntimeContextMenu(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY) {
		this.runtimeContextMenu.render(graphics, minecraft.font, mouseX, mouseY);
	}

	public XpTrackerSession getTrackedXpSession() {
		if (this.trackedXpSkill == null) {
			return new XpTrackerSession("Total XP", this.totalXpSessionGained, this.elapsedXpSessionMillis(this.totalXpSessionStartMillis));
		}
		return new XpTrackerSession(
			formatSkillName(this.trackedXpSkill) + " XP",
			this.skillXpSessionGained.getOrDefault(this.trackedXpSkill, 0.0D),
			this.elapsedXpSessionMillis(this.skillXpSessionStartMillis.getOrDefault(this.trackedXpSkill, 0L))
		);
	}

	private long elapsedXpSessionMillis(long startMillis) {
		if (startMillis <= 0L) return 0L;
		long now = System.currentTimeMillis();
		long pausedMillis = 0L;
		for (XpPauseInterval interval : this.xpPauseIntervals) {
			pausedMillis += overlapMillis(startMillis, now, interval.startMillis(), interval.endMillis());
		}
		if (this.xpTrackerPauseStartedMillis > 0L) {
			pausedMillis += overlapMillis(startMillis, now, this.xpTrackerPauseStartedMillis, now);
		}
		return Math.max(1L, now - startMillis - pausedMillis);
	}

	private static long overlapMillis(long startA, long endA, long startB, long endB) {
		return Math.max(0L, Math.min(endA, endB) - Math.max(startA, startB));
	}

	private boolean hasAnyOverlap(HudWidget widget, int screenWidth, int screenHeight) {
		for (HudWidget other : this.widgets) {
			if (other == widget) continue;
			if (!other.shouldRenderWidget(Minecraft.getInstance(), true)) continue;
			if (widget.overlaps(other, screenWidth, screenHeight)) {
				return true;
			}
		}
		return false;
	}

	private int totalOverlapArea(HudWidget widget, int screenWidth, int screenHeight) {
		int area = 0;
		for (HudWidget other : this.widgets) {
			if (other == widget) continue;
			if (!other.shouldRenderWidget(Minecraft.getInstance(), true)) continue;
			area += widget.overlapArea(other, screenWidth, screenHeight);
		}
		return area;
	}

	private HudConfig captureConfig() {
		HudConfig config = new HudConfig();
		config.selectedTab = this.selectedTab;
		config.sideStatBarsEnabled = this.sideStatBarsEnabled;
		config.xpTrackerEnabled = this.xpTrackerEnabled;
		config.customScoreboardEnabled = this.customScoreboardEnabled;
		config.customChatEnabled = this.customChatEnabled;
		config.minimapEnabled = this.minimapEnabled;
		config.browserEnabled = this.browserEnabled;
		config.runOrbSprintEnabled = this.runOrbSprintEnabled;
		config.scoreboardBrandingRemovalEnabled = this.scoreboardBrandingRemovalEnabled;
		config.virtualLevelsEnabled = this.virtualLevelsEnabled;
		config.armorOverridesEnabled = this.armorOverridesEnabled;
		config.capeOverridesEnabled = this.capeOverridesEnabled;
		config.targetInfoEnabled = this.targetInfoEnabled;
		config.farmingAlertEnabled = this.farmingAlertEnabled;
		config.xpDropOrbsEnabled = this.xpDropOrbsEnabled;
		config.agilityShortcutOutlinesEnabled = this.agilityShortcutOutlinesEnabled;
		config.customMobOutlinesEnabled = this.customMobOutlinesEnabled;
		config.entityOcclusionCullingEnabled = this.entityOcclusionCullingEnabled;
		config.lowHealthVignetteEnabled = this.lowHealthVignetteEnabled;
		config.performanceDebugEnabled = this.performanceDebugEnabled;
		config.trackedXpSkill = this.trackedXpSkill != null ? this.trackedXpSkill.name() : null;
		config.tabStrip = this.tabStrip.snapshot();
		config.contentPanel = this.contentPanel.snapshot();
		config.scoreboardWidget = this.scoreboardWidget.snapshot();
		config.xpTrackerWidget = this.xpTrackerWidget.snapshot();
		config.xpDropOrbWidget = this.xpDropOrbWidget.snapshot();
		config.chatWidget = this.chatWidget.snapshot();
		config.minimapWidget = this.minimapWidget.snapshot();
		config.browserWidget = this.browserWidget.snapshot();
		config.barrowsTrackerWidget = this.barrowsTrackerWidget.snapshot();
		config.targetInfoWidget = this.targetInfoWidget.snapshot();
		config.farmingAlertWidget = this.farmingAlertWidget.snapshot();
		config.clueHelperWidget = this.clueHelperWidget.snapshot();
		config.savedLayouts = this.savedLayouts.clone();
		return config;
	}

	private HudLayout captureLayout() {
		HudLayout layout = new HudLayout();
		layout.tabStrip = this.tabStrip.snapshot();
		layout.contentPanel = this.contentPanel.snapshot();
		layout.scoreboardWidget = this.scoreboardWidget.snapshot();
		layout.xpTrackerWidget = this.xpTrackerWidget.snapshot();
		layout.xpDropOrbWidget = this.xpDropOrbWidget.snapshot();
		layout.chatWidget = this.chatWidget.snapshot();
		layout.minimapWidget = this.minimapWidget.snapshot();
		layout.browserWidget = this.browserWidget.snapshot();
		layout.barrowsTrackerWidget = this.barrowsTrackerWidget.snapshot();
		layout.targetInfoWidget = this.targetInfoWidget.snapshot();
		layout.farmingAlertWidget = this.farmingAlertWidget.snapshot();
		layout.clueHelperWidget = this.clueHelperWidget.snapshot();
		return layout;
	}

	private void applyLayout(HudLayout layout) {
		if (layout == null) return;
		if (layout.tabStrip != null) this.tabStrip.apply(layout.tabStrip);
		if (layout.contentPanel != null) this.contentPanel.apply(layout.contentPanel);
		if (layout.scoreboardWidget != null) this.scoreboardWidget.apply(layout.scoreboardWidget);
		if (layout.xpTrackerWidget != null) this.xpTrackerWidget.apply(layout.xpTrackerWidget);
		if (layout.xpDropOrbWidget != null) this.xpDropOrbWidget.apply(layout.xpDropOrbWidget);
		if (layout.chatWidget != null) this.chatWidget.apply(layout.chatWidget);
		if (layout.minimapWidget != null) this.minimapWidget.apply(layout.minimapWidget);
		if (layout.browserWidget != null) this.browserWidget.apply(layout.browserWidget);
		if (layout.barrowsTrackerWidget != null) this.barrowsTrackerWidget.apply(layout.barrowsTrackerWidget);
		if (layout.targetInfoWidget != null) this.targetInfoWidget.apply(layout.targetInfoWidget);
		if (layout.farmingAlertWidget != null) this.farmingAlertWidget.apply(layout.farmingAlertWidget);
		if (layout.clueHelperWidget != null) this.clueHelperWidget.apply(layout.clueHelperWidget);
	}

	private void loadSavedLayouts(HudLayout[] layouts) {
		for (int index = 0; index < this.savedLayouts.length; index++) {
			this.savedLayouts[index] = layouts != null && index < layouts.length ? layouts[index] : null;
		}
	}

	private static boolean isValidSavedLayoutSlot(int slot) {
		return slot >= 0 && slot < SAVED_LAYOUT_COUNT;
	}

	private Entity findTargetEntity(Minecraft minecraft) {
		if (this.targetUuid != null && this.targetUuid.equals(this.cachedTargetUuid) && this.cachedTargetEntity != null && this.cachedTargetEntity.isAlive()) {
			return this.cachedTargetEntity;
		}
		Entity target = minecraft.level.getEntity(this.targetUuid);

		if (target != null) {
			//target = resolveNamedArmorStandTarget(minecraft, target);
			this.cachedTargetUuid = this.targetUuid;
			this.cachedTargetEntity = target;
			return target;
		}
		for (Entity entity : minecraft.level.entitiesForRendering()) {
			if (entity.getUUID().equals(this.targetUuid)) {
				//entity = resolveNamedArmorStandTarget(minecraft, entity);
				this.cachedTargetUuid = this.targetUuid;
				this.cachedTargetEntity = entity;
				return entity;
			}
		}
		return null;
	}

	private Entity resolveNamedArmorStandTarget(Minecraft minecraft, Entity target) {
		if (target == null) return null;
		String targetName = target.getDisplayName().getString();
		if (!targetName.contains("Armor Stand")) return target;
		AABB targetBox = target.getBoundingBox();
		double maxHorizontalOffset = Math.max(Math.max(targetBox.getXsize(), targetBox.getZsize()), 1.5D);
		double targetCenterX = targetBox.getCenter().x;
		double targetCenterY = targetBox.getCenter().y;
		double targetCenterZ = targetBox.getCenter().z;
		ArmorStand closestArmorStand = null;
		double closestScore = Double.MAX_VALUE;

		for (Entity entity : minecraft.level.entitiesForRendering()) {
			if (!(entity instanceof ArmorStand armorStand) || armorStand == target) continue;
			String armorStandName = armorStand.getDisplayName().getString();
			if (armorStandName.trim().isEmpty() || armorStandName.contains("Armor Stand")) continue;
			AABB armorStandBox = armorStand.getBoundingBox();
			double horizontalOffsetX = Math.abs(armorStandBox.getCenter().x - targetCenterX);
			double horizontalOffsetZ = Math.abs(armorStandBox.getCenter().z - targetCenterZ);
			if (horizontalOffsetX > maxHorizontalOffset || horizontalOffsetZ > maxHorizontalOffset) continue;
			double verticalOffset = armorStandBox.getCenter().y - targetCenterY;
			if (verticalOffset <= 0.0D) continue;
			if (verticalOffset > 3.0D) continue;
			double score = verticalOffset + horizontalOffsetX + horizontalOffsetZ;
			if (score < closestScore) {
				closestArmorStand = armorStand;
				closestScore = score;
			}
		}

		return closestArmorStand != null ? closestArmorStand : target;
	}

	private void clearTargetCache() {
		this.cachedTargetUuid = null;
		this.cachedTargetEntity = null;
		this.cachedTargetInfo = null;
		this.cachedTargetInfoMillis = 0L;
	}

	// ─── Static helpers ───────────────────────────────────────────────────────────

	static int rgba(int alpha, int red, int green, int blue) {
		return (Mth.clamp(alpha, 0, 255) << 24) | (Mth.clamp(red, 0, 255) << 16) | (Mth.clamp(green, 0, 255) << 8) | Mth.clamp(blue, 0, 255);
	}

	private static Identifier texture(String path) {
		return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/" + path);
	}

	static void blitTexture(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height, int textureWidth, int textureHeight) {
		if (width <= 0 || height <= 0 || textureWidth <= 0 || textureHeight <= 0) return;
		if (width == textureWidth && height == textureHeight) {
			graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, textureWidth, textureHeight, textureWidth, textureHeight);
			return;
		}
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale((float) width / textureWidth, (float) height / textureHeight);
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0.0F, 0.0F, textureWidth, textureHeight, textureWidth, textureHeight);
		graphics.pose().popMatrix();
	}

	static float resolveHudTextScale(double widgetScale) {
		double scale = widgetScale - 1.25;
		if (scale <= 0.5D) return 0.5F;
		if (scale < 1.0D) return 1.0F;
		return (float) scale;
	}

	static Font resolveHudTextFont(Minecraft minecraft, float scale) {
		return minecraft.font;
	}

	static int scaledTextWidth(Minecraft minecraft, String text, float scale) {
		Font font = resolveHudTextFont(minecraft, scale);
		return Math.max(1, Math.round(font.width(text) * scale));
	}

	static int scaledTextHeight(Minecraft minecraft, float scale) {
		Font font = resolveHudTextFont(minecraft, scale);
		return Math.max(1, Math.round(font.lineHeight * scale));
	}

	static void drawScaledText(GuiGraphicsExtractor graphics, Minecraft minecraft, String text, int x, int y, int color, float scale) {
		Font font = resolveHudTextFont(minecraft, scale);
		if (Math.abs(scale - 1.0F) < 0.001F) {
			graphics.text(font, text, x, y, color, false);
			return;
		}
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(scale, scale);
		graphics.text(font, text, 0, 0, color, false);
		graphics.pose().popMatrix();
	}

	public static boolean shouldRemoveScoreboardLine(Component component) {
		return component != null && shouldRemoveScoreboardLine(component.getString());
	}

	public static boolean shouldRemoveScoreboardLine(String text) {
		if (text == null) return false;
		String strippedFormatting = text.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "");
		String visibleText = PrivateUseAsciiDecoder.decode(strippedFormatting).trim();
		return visibleText.isEmpty();
	}

	public static boolean shouldRemoveScoreboardBranding(Component component) {
		return component != null && shouldRemoveScoreboardBranding(component.getString());
	}

	public static boolean shouldRemoveScoreboardBranding(String text) {
		if (text == null) return false;
		String normalized = text.toLowerCase(Locale.ROOT);
		return normalized.contains("minescape.me") || normalized.contains("minescape.com");
	}

	public static boolean shouldRemoveBossBarTitle(Component component) {
		return component != null && shouldRemoveBossBarTitle(component.getString());
	}

	public static boolean shouldRemoveBossBarTitle(String text) {
		if (text == null) return false;
		return switch (text.trim()) {
			case "Members now have access to the Test Server! store.minescape.com",
				"Purchase Ranks, Treasure Chests & Cosmetics! store.minescape.com",
				"Purchase MineScape Merch on our new Merch Store! merch.minescape.com" -> true;
			default -> false;
		};
	}

	public static boolean isXpDropBossBar(Component component) {
		return component != null && isXpDropBossBar(component.getString());
	}

	public static boolean isXpDropBossBar(String text) {
		if (text == null) return false;
		for (String symbol : XP_DROP_BOSS_BAR_SYMBOLS) {
			if (text.contains(symbol)) {
				return true;
			}
		}
		return false;
	}

	public static String formatSkillName(SkillType skill) {
		String raw = skill.name().toLowerCase(Locale.ROOT);
		return Character.toUpperCase(raw.charAt(0)) + raw.substring(1);
	}

	private boolean tryOpenSkillTrackerMenu(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		if (this.selectedTab != HudTab.SKILLS) return false;
		SkillType skill = this.contentPanel.skillTypeAt(mouseX, mouseY);
		if (skill == null) return false;
		this.runtimeContextMenu.open(
			(int) mouseX,
			(int) mouseY,
			screenWidth,
			screenHeight,
			Minecraft.getInstance().font,
			List.of(
				ContextMenuWidget.MenuItem.of(Component.literal("Track " + formatSkillName(skill) + " XP"), () -> this.setTrackedXpSkill(skill)),
				ContextMenuWidget.MenuItem.of(Component.literal("Track Total XP"), () -> this.setTrackedXpSkill(null)),
				ContextMenuWidget.MenuItem.of(Component.literal((this.virtualLevelsEnabled ? "Disable" : "Enable") + " Virtual Levels"), this::toggleVirtualLevelsEnabled),
				ContextMenuWidget.MenuItem.of(Component.literal("Reset " + formatSkillName(skill) + " XP Tracker"), () -> this.resetSkillXpTracker(skill)),
				ContextMenuWidget.MenuItem.of(Component.literal("Reset Total XP Tracker"), this::resetTotalXpTracker)
			)
		);
		return true;
	}

	private static SkillType parseTrackedXpSkill(String value) {
		if (value == null || value.isBlank()) return null;
		try {
			return SkillType.valueOf(value);
		} catch (IllegalArgumentException ignored) {
			return null;
		}
	}

	private static boolean isClientConnected(Minecraft minecraft) {
		return minecraft != null && minecraft.getConnection() != null && minecraft.player != null && minecraft.level != null;
	}

	private boolean shouldPauseXpTracker(Minecraft minecraft, long now) {
		return !isClientConnected(minecraft) || now < this.resourcePackOverlayPauseUntilMillis || this.isAfk(now);
	}

	private void tickAfkState(Minecraft minecraft, long now) {
		if (!isClientConnected(minecraft)) {
			this.recordActivity();
			this.hasLastPlayerState = false;
			return;
		}

		if (this.hasPlayerActivity(minecraft)) {
			this.recordActivity();
		}
	}

	private boolean hasPlayerActivity(Minecraft minecraft) {
		double x = minecraft.player.getX();
		double y = minecraft.player.getY();
		double z = minecraft.player.getZ();
		float yRot = minecraft.player.getYRot();
		float xRot = minecraft.player.getXRot();
		boolean changed = !this.hasLastPlayerState
			|| Double.compare(x, this.lastPlayerX) != 0
			|| Double.compare(y, this.lastPlayerY) != 0
			|| Double.compare(z, this.lastPlayerZ) != 0
			|| Float.compare(yRot, this.lastPlayerYRot) != 0
			|| Float.compare(xRot, this.lastPlayerXRot) != 0
			|| hasActiveKeybind(minecraft);

		this.lastPlayerX = x;
		this.lastPlayerY = y;
		this.lastPlayerZ = z;
		this.lastPlayerYRot = yRot;
		this.lastPlayerXRot = xRot;
		this.hasLastPlayerState = true;
		return changed;
	}

	private static boolean hasActiveKeybind(Minecraft minecraft) {
		if (minecraft.options == null) return false;
		return minecraft.options.keyUp.isDown()
			|| minecraft.options.keyDown.isDown()
			|| minecraft.options.keyLeft.isDown()
			|| minecraft.options.keyRight.isDown()
			|| minecraft.options.keyJump.isDown()
			|| minecraft.options.keyShift.isDown()
			|| minecraft.options.keySprint.isDown()
			|| minecraft.options.keyAttack.isDown()
			|| minecraft.options.keyUse.isDown();
	}

	private boolean isAfk(long now) {
		return now - this.lastActivityMillis >= AFK_THRESHOLD_MILLIS;
	}

	private void renderAfkTimer(GuiGraphicsExtractor graphics, Minecraft minecraft) {
		long now = System.currentTimeMillis();
		if (!isClientConnected(minecraft) || !this.isAfk(now)) return;

		long elapsedSeconds = (now - this.lastActivityMillis) / 1000L;
		String afkTime = String.format(Locale.US, "%02d:%02d:%02d", elapsedSeconds / 3600L, (elapsedSeconds % 3600L) / 60L, elapsedSeconds % 60L);
		float scale = 2.0F;
		int textWidth = scaledTextWidth(minecraft, afkTime, scale);
		int textHeight = scaledTextHeight(minecraft, scale);
		int x = (minecraft.getWindow().getGuiScaledWidth() - textWidth) / 2;
		int y = (minecraft.getWindow().getGuiScaledHeight() - textHeight) / 2;
		drawScaledText(graphics, minecraft, afkTime, x + 1, y + 1, 0xAA000000, scale);
		drawScaledText(graphics, minecraft, afkTime, x, y, 0xFFFFFFFF, scale);
	}

	private record XpPauseInterval(long startMillis, long endMillis) {}

	public record XpTrackerSession(String label, double gained, long elapsedMillis) {}
	public record TargetInfo(String name, int currentHp, int totalHp) {
		double hpPercent() {
			return totalHp <= 0 ? 0.0D : Mth.clamp((double) currentHp / totalHp, 0.0D, 1.0D);
		}
	}


}
