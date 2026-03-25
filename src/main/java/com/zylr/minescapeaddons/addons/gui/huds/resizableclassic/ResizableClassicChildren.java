package com.zylr.minescapeaddons.addons.gui.huds.resizableclassic;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.*;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.HitpointsOrb;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.PrayerPointsOrb;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.RunEnergyOrb;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.XpOrbButton;

import java.util.ArrayList;
import java.util.List;

public class ResizableClassicChildren {
    private final EquipmentStatsWidget equipmentStatsWidget;
    private final HitpointsOrb hitpointsOrb;
    private final PrayerPointsOrb prayerPointsOrb;
    private final RunEnergyOrb runEnergyOrb;
    private final XpOrbButton xpOrb;

    private List<Widget> widgetList;

    public ResizableClassicChildren() {
        this.equipmentStatsWidget = new EquipmentStatsWidget();
        this.hitpointsOrb =  new HitpointsOrb(MinescapeAddons.getInstance().resizableClassic.getMinimapWidget(), true);
        this.prayerPointsOrb = new PrayerPointsOrb(MinescapeAddons.getInstance().resizableClassic.getMinimapWidget(), true);
        this.runEnergyOrb = new RunEnergyOrb(MinescapeAddons.getInstance().resizableClassic.getMinimapWidget(), true);
        this.xpOrb = new XpOrbButton(MinescapeAddons.getInstance().resizableClassic.getMinimapWidget(), true);

        this.widgetList = new ArrayList<>();
        this.widgetList.add(this.equipmentStatsWidget);
        this.widgetList.add(this.hitpointsOrb);
        this.widgetList.add(this.prayerPointsOrb);
        this.widgetList.add(this.runEnergyOrb);
        this.widgetList.add(this.xpOrb);
    }

    public EquipmentStatsWidget getEquipmentStatsWidget() { return this.equipmentStatsWidget; }

    public HitpointsOrb getHitpointsOrb() { return this.hitpointsOrb; }

    public PrayerPointsOrb getPrayerPointsOrb() { return this.prayerPointsOrb; }

    public RunEnergyOrb getRunEnergyOrb() { return this.runEnergyOrb; }

    public XpOrbButton getXpOrb() { return this.xpOrb; }


    public List<Widget> getWidgetList() { return this.widgetList; }
}
