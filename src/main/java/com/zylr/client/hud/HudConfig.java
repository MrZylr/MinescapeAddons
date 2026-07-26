package com.zylr.client.hud;

public final class HudConfig {
	public HudTab selectedTab = HudTab.COMBAT;
	public boolean sideStatBarsEnabled = true;
	public boolean xpTrackerEnabled = true;
	public boolean customScoreboardEnabled = true;
	public boolean customChatEnabled = true;
	public boolean minimapEnabled = true;
	public Boolean browserEnabled = true;
	public boolean runOrbSprintEnabled = true;
	public boolean scoreboardBrandingRemovalEnabled = false;
	public boolean virtualLevelsEnabled = false;
	public Boolean armorOverridesEnabled = true;
	public Boolean capeOverridesEnabled = true;
	public Boolean targetInfoEnabled = true;
	public Boolean farmingAlertEnabled = true;
	public Boolean xpDropOrbsEnabled = true;
	public Boolean agilityShortcutOutlinesEnabled = false;
	public Boolean customMobOutlinesEnabled = false;
	public Boolean entityOcclusionCullingEnabled = true;
	public Boolean lowHealthVignetteEnabled = true;
	public Boolean performanceDebugEnabled = false;
	public String trackedXpSkill = null;
	public WidgetState tabStrip = new WidgetState();
	public WidgetState contentPanel = new WidgetState();
	public WidgetState scoreboardWidget = new WidgetState();
	public WidgetState xpTrackerWidget = new WidgetState();
	public WidgetState xpDropOrbWidget = new WidgetState();
	public WidgetState chatWidget = new WidgetState();
	public WidgetState minimapWidget = new WidgetState();
	public WidgetState browserWidget = new WidgetState();
	public WidgetState barrowsTrackerWidget = new WidgetState();
	public WidgetState targetInfoWidget = new WidgetState();
	public WidgetState farmingAlertWidget = new WidgetState();
	public WidgetState clueHelperWidget = new WidgetState();
	public HudLayout[] savedLayouts = new HudLayout[3];
}

