package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.WidgetType;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.slf4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HudMenuWidget extends Widget {
    private static final Logger LOGGER = MinescapeAddons.LOGGER;
    private Minecraft mc = Minecraft.getInstance();

    // Pre-computed color constants — avoids Color.X.getRGB() and new Color(...).getRGB() every frame
    private static final int COLOR_BLACK      = Color.BLACK.getRGB();
    private static final int COLOR_WHITE      = Color.WHITE.getRGB();
    private static final int COLOR_ORANGE     = Color.ORANGE.getRGB();
    private static final int COLOR_YELLOW     = Color.YELLOW.getRGB();
    private static final int COLOR_HP_BAR     = new Color(121, 27, 4).getRGB();
    private static final int COLOR_PRAYER_BAR = new Color(38, 142, 144).getRGB();


    Widget activeWidget;
    private int healAmount;
    private int prayerRestoreAmount;

    private Button inventoryButton;

    private List<ResourceLocation> tabIcons;

    public final ResourceLocation FARMING_ALERT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/alert_circle.png");
    public final ResourceLocation HUDMENU = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/inventory_border.png");
    public final ResourceLocation ACCOUNT_MANAGEMENT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/account_management.png");
    public final ResourceLocation CLAN_CHAT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/clan_chat.png");
    public final ResourceLocation COMABAT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/combat.png");
    public final ResourceLocation EMOTES = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/emotes.png");
    public final ResourceLocation EQUIPMENT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/equipment.png");
    public final ResourceLocation FRIENDS = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/friends.png");
    public final ResourceLocation INVENTORY = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/inventory.png");
    public final ResourceLocation LOGOUT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/logout.png");
    public final ResourceLocation MAGIC = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/magic.png");
    public final ResourceLocation MUSIC = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/music.png");
    public final ResourceLocation OPTIONS = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/options.png");
    public final ResourceLocation PRAYER = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/prayer.png");
    public final ResourceLocation QUESTS = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/quests.png");
    public final ResourceLocation STATS = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/stats.png");
    public static final ResourceLocation STONE_BOTTOM_LEFT_SELECTED = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/stone_bottom_left_selected.png");
    public static final ResourceLocation STONE_BOTTOM_RIGHT_SELECTED = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/stone_bottom_right_selected.png");
    public static final ResourceLocation STONE_MIDDLE_SELECTED = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/stone_middle_selected.png");
    public static final ResourceLocation SOTNE_TOP_LEFT_SELECTED = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/stone_top_left_selected.png");
    public static final ResourceLocation STONE_TOP_RIGHT_SELECTED = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/tab_icons/stone_top_right_selected.png");
    public final ResourceLocation HPORBHEART = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap_orb_hitpoints_icon.png");
    public final ResourceLocation PRAYERORBICON = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/prayer.png");

    public static final InventoryWidget INVENTORY_WIDGET;
    public static final EquipmentWidget EQUIPMENT_WIDGET;
    public static final CombatWidget COMBAT_WIDGET;
    public static final SkillsWidget SKILLS_WIDGET;

    static {
        INVENTORY_WIDGET = new InventoryWidget(false);
        EQUIPMENT_WIDGET = new EquipmentWidget(false);
        COMBAT_WIDGET = new CombatWidget(false);
        SKILLS_WIDGET = new SkillsWidget(false);
    }

    public HudMenuWidget(int x, int y) {
        super();
        this.type = WidgetType.HUDMENU;

        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);

        this.widgetHeight = 225;
        this.widgetWidth = (int)(this.widgetHeight*0.7175);
        this.isParent = true;
        this.setupConfig();

        this.makeTabList();
        this.healAmount = 0;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "1")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "1")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "0"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));

        INVENTORY_WIDGET.setParent(this);
        EQUIPMENT_WIDGET.setParent(this);
        COMBAT_WIDGET.setParent(this);
        SKILLS_WIDGET.setParent(this);
    }

    public HudMenuWidget() {
        super();
        this.type = WidgetType.HUDMENU;

        this.widgetWidth = 250;
        this.widgetHeight = (int)(this.widgetWidth * 1.398);

        this.anchorX = mc.getWindow().getGuiScaledWidth() - this.widgetWidth;
        this.anchorY = mc.getWindow().getGuiScaledHeight() - this.widgetHeight;

        this.isParent = true;
        this.setupConfig();

        this.makeTabList();
        this.healAmount = 0;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "1")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "1")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "0"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));

        INVENTORY_WIDGET.setParent(this);
        EQUIPMENT_WIDGET.setParent(this);
        COMBAT_WIDGET.setParent(this);
        SKILLS_WIDGET.setParent(this);
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode()) {
            this.scale = 1.0f;
            this.renderFixed(gui);
        } else {
            this.renderResizable(gui);
        }
    }

    public void renderFixed(GuiGraphics gui) {
        super.render(gui);
        this.widgetWidth = 250;
        this.widgetHeight = (int)(this.widgetWidth * 1.398);

        gui.pose().pushPose();
        gui.pose().translate(0, 0, -9000);
        gui.fill(0, mc.getWindow().getGuiScaledHeight()-142, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(), COLOR_BLACK);
        gui.pose().popPose();

        this.anchorX = mc.getWindow().getGuiScaledWidth() - this.widgetWidth;
        this.anchorY = mc.getWindow().getGuiScaledHeight() - this.widgetHeight;
        this.fixAnchors();

        if (this.children.isEmpty()) {
            this.children.add(INVENTORY_WIDGET);
            this.children.add(EQUIPMENT_WIDGET);
            this.children.add(COMBAT_WIDGET);
            this.children.add(SKILLS_WIDGET);
        }

        // Update Equipment stats widget
        MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().render(gui, 0, 0);

        if (this.activeWidget == null)
            this.activeWidget = INVENTORY_WIDGET;

        for (IWidget child : children) {
            if (child != this.activeWidget)
                child.setVisible(false);
            else
                child.setVisible(true);
        }

        this.renderChildren(gui);
        gui.pose().pushPose();
        gui.pose().translate(0, 0, 100);
        renderIconsFixed(gui, 0, 0);

        gui.pose().translate(0, 0, 100);
        if (Config.getHpPrayerBars())
            this.renderBars(gui, 0, 0);


        if (FarmingUtil.checkForCompletedTimers()) {
            if (this.isLeftSide()) {
                gui.blit(FARMING_ALERT, this.getRightSide() + 10, this.getTop() - 4, 0, 0, 16, 16, 16, 16);
            } else {
                gui.blit(FARMING_ALERT, this.getLeftSide() - 26, this.getTop() - 4, 0, 0, 16, 16, 16, 16);
            }
        }

        gui.pose().popPose();
    }

    public void renderResizable(GuiGraphics gui) {

        super.render(gui);
        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        if(!Config.getSmallInventory())
            this.widgetHeight = 225;
        else
            this.widgetHeight = 175;
        this.widgetWidth = (int)(this.widgetHeight*0.7175);
        this.fixAnchors();

        if (this.children.isEmpty()) {
            this.children.add(INVENTORY_WIDGET);
            this.children.add(EQUIPMENT_WIDGET);
            this.children.add(COMBAT_WIDGET);
            this.children.add(SKILLS_WIDGET);
        }

        // Update Equipment stats widget
        MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().render(gui, 0, 0);

        if (this.activeWidget == null)
            this.activeWidget = INVENTORY_WIDGET;

        for (IWidget child : children) {
            if (child != this.activeWidget)
                child.setVisible(false);
            else
                child.setVisible(true);
        }

        this.renderChildren(gui);
        float scale = this.scale;
        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, scale);
        gui.pose().translate(0, 0, 100);
        drawImage(HUDMENU, gui);
        if (!Config.getSmallInventory())
            this.renderIconsLarge(gui, 0, 0);
        else
            this.renderIconsSmall(gui, 0, 0);

        gui.pose().translate(0, 0, 100);
        if (Config.getHpPrayerBars())
            this.renderBars(gui, 0, 0);


        if (FarmingUtil.checkForCompletedTimers()) {
            if (this.isLeftSide()) {
                gui.blit(FARMING_ALERT, this.getRightSide() + 10, this.getTop() - 4, 0, 0, 16, 16, 16, 16);
            } else {
                gui.blit(FARMING_ALERT, this.getLeftSide() - 26, this.getTop() - 4, 0, 0, 16, 16, 16, 16);
            }
        }

        gui.pose().popPose();
    }

    public void onInventoryButtonPress() {
        this.setActiveWidget(INVENTORY_WIDGET);
    }

    public void onEquipmentButtonPress() {
        this.setActiveWidget(EQUIPMENT_WIDGET);
    }

    public Widget getActiveWidget() {
        return this.activeWidget;
    }

    public void setActiveWidget(Widget activeWidget) {
        if (MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().isVisible())
            this.activeWidget = INVENTORY_WIDGET;
        else
            this.activeWidget = activeWidget;
    }

    public void renderChildren(GuiGraphics gui) {
        INVENTORY_WIDGET.render(gui);
        EQUIPMENT_WIDGET.render(gui);
        COMBAT_WIDGET.render(gui);
        SKILLS_WIDGET.render(gui);
    }

    public void renderIconsFixed(GuiGraphics gui, int xOffset, int yOffset) {
        int tabWidth = 36;
        int tabWidth2 = 33;
        int tabHeight = 39;
        int x = this.getLeftSide()+8;
        int y = this.getTop()+1;
        int i = 0;
        int i1 = 0;

        ResourceLocation highlight = this.activeWidget.getHighlight();
        if (this.activeWidget == COMBAT_WIDGET)
            i1 = 0;
        if (this.activeWidget == EQUIPMENT_WIDGET)
            i1 = 4;
        if (this.activeWidget == INVENTORY_WIDGET)
            i1 = 3;
        if (this.activeWidget == SKILLS_WIDGET)
            i1 = 1;

        for (ResourceLocation tabIcon : this.tabIcons) {
            if(i == i1) {
                if (i==0)
                    gui.blit(highlight, x - 2, y, 0, 0, tabWidth + 2, tabHeight - 2, tabWidth + 2,
                            tabHeight - 2);
                else
                    gui.blit(highlight, x + 1, y, 0, 0, tabWidth + 2, tabHeight - 2, tabWidth + 2,
                            tabHeight - 2);
            }
            gui.blit(tabIcon, x, y, 0, 0, tabWidth, tabHeight, tabWidth, tabHeight);
            // LOGGER.info(i1+"");
            if (i==0 || i==5 || i==6 || i==12)
                x = x+tabWidth;
            else
                x = x+tabWidth2;
            i++;
            if (i == 7) {
                i1 = 0;
                y = this.getBottom()-tabHeight;
                x = this.getLeftSide()+9;
            }
        }
    }

    public void renderIconsLarge(GuiGraphics gui, int xOffset, int yOffset) {
        int tabWidth = 23;
        int tabWidth2 = 22;
        int tabHeight = 26;
        int x = this.getLeftSide()+2;
        int y = this.getTop();
        int i = 0;
        int i1 = 0;

        ResourceLocation highlight = this.activeWidget.getHighlight();
        if (this.activeWidget == COMBAT_WIDGET)
            i1 = 0;
        if (this.activeWidget == EQUIPMENT_WIDGET)
            i1 = 4;
        if (this.activeWidget == INVENTORY_WIDGET)
            i1 = 3;
        if (this.activeWidget == SKILLS_WIDGET)
            i1 = 1;

        for (ResourceLocation tabIcon : this.tabIcons) {
            if(i == i1) {
                if (i==0)
                    gui.blit(highlight, x + xOffset - 2, y + yOffset, 0, 0, tabWidth + 2, tabHeight - 2, tabWidth + 2,
                            tabHeight - 2);
                else
                    gui.blit(highlight, x + xOffset + 1, y + yOffset, 0, 0, tabWidth + 2, tabHeight - 2, tabWidth + 2,
                            tabHeight - 2);
            }
            gui.blit(tabIcon, x+xOffset, y+yOffset, 0, 0, tabWidth, tabHeight, tabWidth, tabHeight);
            // LOGGER.info(i1+"");
            if (i==0 || i==5 || i==6 || i==12)
                x = x+tabWidth;
            else
                x = x+tabWidth2;
            i++;
            if (i == 7) {
                i1 = 0;
                y = this.getBottom()-tabHeight;
                x = this.getLeftSide()+3;
            }
        }
    }

    public void renderIconsSmall(GuiGraphics gui, int xOffset, int yOffset) {
        int tabWidth = 18;
        int tabWidth2 = 17;
        int tabHeight = 20;
        int x = this.getLeftSide()+2;
        int y = this.getTop();
        int i = 0;
        int i1 = 0;

        ResourceLocation highlight = this.activeWidget.getHighlight();
        if (this.activeWidget == COMBAT_WIDGET)
            i1 = 0;
        if (this.activeWidget == EQUIPMENT_WIDGET)
            i1 = 4;
        if (this.activeWidget == INVENTORY_WIDGET)
            i1 = 3;
        if (this.activeWidget == SKILLS_WIDGET)
            i1 = 1;

        for (ResourceLocation tabIcon : this.tabIcons) {
            if(i == i1) {
                if (i==0)
                    gui.blit(highlight, x + xOffset - 2, y + yOffset, 0, 0, tabWidth + 2, tabHeight - 2, tabWidth + 2,
                            tabHeight);
                else
                    gui.blit(highlight, x + xOffset, y + yOffset, 0, 0, tabWidth + 2, tabHeight - 2, tabWidth + 2,
                            tabHeight - 2);
            }
            gui.blit(tabIcon, x+xOffset, y+yOffset, 0, 0, tabWidth, tabHeight, tabWidth, tabHeight);
            // LOGGER.info(i1+"");
            if (i==0 || i==5 || i==6 || i==12)
                x = x+tabWidth;
            else
                x = x+tabWidth2;
            i++;
            if (i == 7) {
                i1 = 0;
                y = this.getBottom()-tabHeight;
                x = this.getLeftSide()+3;
            }
        }
    }

    private void makeTabList() {
        this.tabIcons = new ArrayList<>();

        this.tabIcons.add(this.COMABAT);
        this.tabIcons.add(this.STATS);
        this.tabIcons.add(this.QUESTS);
        this.tabIcons.add(this.INVENTORY);
        this.tabIcons.add(this.EQUIPMENT);
        this.tabIcons.add(this.PRAYER);
        this.tabIcons.add(this.MAGIC);
        this.tabIcons.add(this.CLAN_CHAT);
        this.tabIcons.add(this.FRIENDS);
        this.tabIcons.add(this.ACCOUNT_MANAGEMENT);
        this.tabIcons.add(this.LOGOUT);
        this.tabIcons.add(this.OPTIONS);
        this.tabIcons.add(this.EMOTES);
        this.tabIcons.add(this.MUSIC);
    }

    public void renderBars(GuiGraphics gui, int xOffset, int yOffset) {
        int hpBarX = this.getLeftSide() + 4;
        int hpBarY = this.getTop() + 25;
        int hpBarWidth = 13;
        int hpBarHeight = 175;
        if (Config.getSmallInventory()) {
            hpBarX = this.getLeftSide() + 3;
            hpBarY = this.getTop() + 22;
            hpBarHeight = 131;
            hpBarWidth = 10;
        }
        if (Config.getFixedMode()) {
            hpBarX = this.getLeftSide()+11;
            hpBarY = this.getTop() + 38;
            hpBarWidth = 20;
            hpBarHeight = 272;
        }

        int hpLevel = MinescapeAddons.skills.get(SkillType.HITPOINTS).getLevel();
        if (hpLevel > 99)
            hpLevel = 99;

        double hpPercentage = MinescapeAddons.getInstance().player.getHealthPoints() /
                (double) hpLevel;
        double hpRedBarHeight = hpPercentage* hpBarHeight;
        int hpRedBarHeightFinal = (int) hpRedBarHeight;


        gui.fill(hpBarX + xOffset, hpBarY + yOffset, hpBarX + hpBarWidth + xOffset, hpBarY + hpBarHeight + yOffset, COLOR_BLACK);

        int healedHitPoints = MinescapeAddons.getInstance().player.getHealthPoints() + this.healAmount;
        if (healedHitPoints > MinescapeAddons.getInstance().player.getHealthPoints()
                && MinescapeAddons.getInstance().player.getHealthPoints() <= hpLevel) {
            int color = COLOR_ORANGE;
            if (healedHitPoints > hpLevel) {
                healedHitPoints = hpLevel;
                color = Color.GREEN.getRGB();
            }
            double healedPercentage = healedHitPoints /
                    (double) hpLevel;
            double healedRedBarHeight = healedPercentage * hpBarHeight;
            gui.fill(hpBarX + xOffset, (hpBarY + hpBarHeight - (int)healedRedBarHeight) + yOffset,
                    hpBarX + hpBarWidth + xOffset, hpBarY + hpBarHeight + yOffset, color);
            this.healAmount = 0;
        }

        gui.fill(hpBarX + xOffset, (hpBarY + hpBarHeight - hpRedBarHeightFinal) + yOffset, hpBarX + hpBarWidth+xOffset, hpBarY + hpBarHeight + yOffset, COLOR_HP_BAR);

        if (!Config.getSmallInventory())
            gui.blit(HPORBHEART, hpBarX + hpBarWidth/2 - 8, hpBarY + 4+yOffset, 0, 0, 16, 16, 16, 16);
        else
            gui.blit(HPORBHEART, hpBarX + hpBarWidth/2 - 7, hpBarY + 4+yOffset, 0, 0, 13, 13, 13, 13);
        gui.drawCenteredString(mc.font, healedHitPoints + "", hpBarX + hpBarWidth/2,
                hpBarY + 20+yOffset, COLOR_WHITE);

        int prayerBarX = this.getRightSide() - 17;
        int prayerBarY = this.getTop() + 25;
        int prayerBarWidth = 13;
        int prayerBarHeight = 175;
        if (Config.getSmallInventory()) {
            prayerBarX = this.getRightSide() - 13;
            prayerBarY = this.getTop() + 22;
            prayerBarHeight = 131;
            prayerBarWidth = 10;
        }
        if (Config.getFixedMode()) {
            prayerBarX = this.getRightSide() - 28;
            prayerBarY = this.getTop() + 38;
            prayerBarWidth = 20;
            prayerBarHeight = 272;
        }
        int prayerLevel = MinescapeAddons.skills.get(SkillType.PRAYER).getLevel();
        if (prayerLevel > 99)
            prayerLevel = 99;

        double prayerPercentage = MinescapeAddons.getInstance().player.getPrayerPoints() /
                (double) prayerLevel;
        double prayerBlueBarHeight = prayerPercentage * prayerBarHeight;
        int prayerBlueBarHeightFinal = (int) prayerBlueBarHeight;

        gui.fill(prayerBarX + xOffset, prayerBarY + yOffset, prayerBarX + prayerBarWidth + xOffset, prayerBarY + prayerBarHeight + yOffset, COLOR_BLACK);

        int restoredPrayerPoints = MinescapeAddons.getInstance().player.getPrayerPoints() + this.prayerRestoreAmount;
        if (restoredPrayerPoints > MinescapeAddons.getInstance().player.getPrayerPoints()
                && MinescapeAddons.getInstance().player.getPrayerPoints() <= prayerLevel) {
            int color = COLOR_ORANGE;
            if (restoredPrayerPoints > prayerLevel) {
                restoredPrayerPoints = prayerLevel;
                color = Color.GREEN.getRGB();
            }
            double restoredPercentage = restoredPrayerPoints /
                    (double) prayerLevel;
            double restoredBlueBarHeight = restoredPercentage * prayerBarHeight;
            gui.fill(prayerBarX + xOffset, (prayerBarY + prayerBarHeight - (int)restoredBlueBarHeight) + yOffset,
                    prayerBarX + prayerBarWidth + xOffset, prayerBarY + prayerBarHeight + yOffset, color);
            this.prayerRestoreAmount = 0;
        }

        gui.fill(prayerBarX + xOffset, (prayerBarY + prayerBarHeight - prayerBlueBarHeightFinal) + yOffset, prayerBarX + prayerBarWidth + xOffset,
                prayerBarY + prayerBarHeight + yOffset, COLOR_PRAYER_BAR);

        if (!Config.getSmallInventory())
            gui.blit(PRAYERORBICON, prayerBarX + prayerBarWidth/2 - 6, prayerBarY + 4+yOffset, 0, 0, 13, 13, 13, 13);
        else
            gui.blit(PRAYERORBICON, prayerBarX + prayerBarWidth/2 - 5, prayerBarY + 4+yOffset, 0, 0, 11, 11, 11, 11);

        gui.drawCenteredString(mc.font, restoredPrayerPoints + "", prayerBarX + prayerBarWidth/2,
                prayerBarY + 20+yOffset, COLOR_WHITE);

    }

    public int getHealAmount() {
        return this.healAmount;
    }
    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public int getPrayerRestoreAmount() {
        return this.prayerRestoreAmount;
    }
    public void setPrayerRestoreAmount(int prayerRestoreAmount) {
        this.prayerRestoreAmount = prayerRestoreAmount;
    }
}
