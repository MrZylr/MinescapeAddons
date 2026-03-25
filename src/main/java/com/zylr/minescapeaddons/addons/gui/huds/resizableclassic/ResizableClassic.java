package com.zylr.minescapeaddons.addons.gui.huds.resizableclassic;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.gui.huds.Hud;
import com.zylr.minescapeaddons.addons.gui.widgets.*;
import com.zylr.minescapeaddons.addons.gui.widgets.chat.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.HudMenuWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.MinimapWidget;

public class ResizableClassic extends Hud {
    public static final int widgetWidth = 134;
    public static final int widgetHeight = 185;

    private HudMenuWidget hudMenuWidget;
    private XpTrackerWidget xpTrackerWidget;
    private ClueWidget clueWidget;
    private CustomScoreboard scoreboardWidget;
    private XpOrbWidget orbWidget;
    private BrowserWidget browserWidget;
    private BarrrowsBrothersWidget barrrowsBrothersWidget;
    private TargetInfoWidget targetInfoWidget;
    private ChatWidget chatWidget;
    private MinimapWidget minimapWidget;

    public ResizableClassic () {
        super();
        this.hudMenuWidget = new HudMenuWidget(1, 1);
        this.xpTrackerWidget = new XpTrackerWidget(1, 1);
        this.clueWidget = new ClueWidget(0, 0);
        this.scoreboardWidget = new CustomScoreboard(0.5, 1);
        this.orbWidget = new XpOrbWidget();
        this.browserWidget = null;
        this.barrrowsBrothersWidget = new BarrrowsBrothersWidget(0, 0.2);
        this.targetInfoWidget = new TargetInfoWidget(0, 0.7);
        this.chatWidget = new ChatWidget(0, 1);
        this.minimapWidget = new MinimapWidget(1, 0);;

        fillHudList();
    }

    public HudMenuWidget getHudMenu() { return this.hudMenuWidget; }
    public XpTrackerWidget getXpTrackerWidget() { return this.xpTrackerWidget; }
    public ClueWidget getClueWidget() { return this.clueWidget; }
    public CustomScoreboard getScoreboardWidget() { return this.scoreboardWidget; }
    public XpOrbWidget getOrbWidget() { return this.orbWidget; }
    public BrowserWidget getBrowserWidget() { return this.browserWidget; }
    public void setBrowserWidget(BrowserWidget browserWidget) {
        this.browserWidget = browserWidget;
    }
    public BarrrowsBrothersWidget getBarrrowsBrothersWidget() { return this.barrrowsBrothersWidget; }
    public TargetInfoWidget getTargetInfoWidget() { return this.targetInfoWidget; }
    public ChatWidget getChatWidget() { return this.chatWidget; }
    public MinimapWidget getMinimapWidget() { return this.minimapWidget; }

    @Override
    public void fillHudList() {
        this.widgets.clear();
        this.widgets.add(this.hudMenuWidget);
        this.widgets.add(this.xpTrackerWidget);
        this.widgets.add(this.clueWidget);
        this.widgets.add(this.scoreboardWidget);
        this.widgets.add(this.orbWidget);
        this.widgets.add(this.browserWidget);
        this.widgets.add(this.barrrowsBrothersWidget);
        this.widgets.add(this.targetInfoWidget);
        this.widgets.add(this.chatWidget);
        this.widgets.add(this.minimapWidget);
    }
}
