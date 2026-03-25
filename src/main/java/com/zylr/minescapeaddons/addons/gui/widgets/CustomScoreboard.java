package com.zylr.minescapeaddons.addons.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.NumberFormat;
import net.minecraft.network.chat.numbers.StyledFormat;
import net.minecraft.world.scores.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class
CustomScoreboard extends Widget{
    private static final Comparator<PlayerScoreEntry> SCORE_DISPLAY_ORDER = Comparator.comparing(PlayerScoreEntry::value)
        .reversed()
        .thenComparing(PlayerScoreEntry::owner, String.CASE_INSENSITIVE_ORDER);

    public Scoreboard scoreboard;

    public CustomScoreboard(double x, double y) {
        this.type = WidgetType.SCOREBOARD;
        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;
        this.setupConfig();
        this.widgetWidth = 50;
        this.widgetHeight = 50;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "1")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0.35")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        super.render(gui);


        assert Minecraft.getInstance().level != null;
        this.scoreboard = Minecraft.getInstance().level.getScoreboard();
        this.anchorX = 0;
        this.anchorY = 0;
        this.fixAnchors();

        if(this.scoreboard == null)
            this.scoreboard = Minecraft.getInstance().level.getScoreboard();

        Objective objective = null;
        assert Minecraft.getInstance().player != null;
        PlayerTeam playerteam = scoreboard.getPlayersTeam(Minecraft.getInstance().player.getScoreboardName());
        if (playerteam != null) {
            DisplaySlot displayslot = DisplaySlot.teamColorToSlot(playerteam.getColor());
            if (displayslot != null) {
                objective = scoreboard.getDisplayObjective(displayslot);
            }
        }

        Objective objective1 = objective != null ? objective : scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR);
        if (objective1 != null) {
            this.displayScoreboardSidebar(gui, objective1);
        }
    }

    public void renderResizable(GuiGraphics gui) {
        super.render(gui);
        if (!Config.getScoreboard() && !Widget.isHudEditOpen) {
            return;
        }

        float scale = this.scale;

        assert Minecraft.getInstance().level != null;
        this.scoreboard = Minecraft.getInstance().level.getScoreboard();
        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.fixAnchors();

        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, scale);
        gui.pose().translate(0, 0, 100);

        if(this.scoreboard == null)
            this.scoreboard = Minecraft.getInstance().level.getScoreboard();

        Objective objective = null;
        assert Minecraft.getInstance().player != null;
        PlayerTeam playerteam = scoreboard.getPlayersTeam(Minecraft.getInstance().player.getScoreboardName());
        if (playerteam != null) {
            DisplaySlot displayslot = DisplaySlot.teamColorToSlot(playerteam.getColor());
            if (displayslot != null) {
                objective = scoreboard.getDisplayObjective(displayslot);
            }
        }

        Objective objective1 = objective != null ? objective : scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR);
        if (objective1 != null) {
            this.displayScoreboardSidebar(gui, objective1);
        }

        gui.pose().popPose();
    }

    private void displayScoreboardSidebar(GuiGraphics guiGraphics, Objective objective) {
        Scoreboard scoreboard = objective.getScoreboard();
        NumberFormat numberformat = objective.numberFormatOrDefault(StyledFormat.SIDEBAR_DEFAULT);

        @OnlyIn(Dist.CLIENT)
        record DisplayEntry(Component name, Component score, int scoreWidth) {
        }

        DisplayEntry[] agui$1displayentry = scoreboard.listPlayerScores(objective)
                .stream()
                .filter(p_313419_ -> !p_313419_.isHidden())
                .sorted(SCORE_DISPLAY_ORDER)
                .limit(15L)
                .map(p_313418_ -> {
                    PlayerTeam playerteam = scoreboard.getPlayersTeam(p_313418_.owner());
                    Component component1 = p_313418_.ownerName();
                    Component component2 = PlayerTeam.formatNameForTeam(playerteam, component1);
                    Component component3 = p_313418_.formatValue(numberformat);
                    int i1 = Minecraft.getInstance().font.width(component3);
                    return new DisplayEntry(component2, component3, i1);
                })
                .toArray(DisplayEntry[]::new);

        Component component = objective.getDisplayName();
        int i9 = 0;
        List<Component> scoreboardData = new ArrayList<>();
        int longestLine = 0;
        for (DisplayEntry gui$1displayentry : agui$1displayentry) {
            // Minus 1 here to remove "Minescape.com"
            if (Config.getRemoveBranding()) {
                if (i9 == agui$1displayentry.length - 1)
                    break;
            }else {
                if (i9 == agui$1displayentry.length)
                    break;
            }
            scoreboardData.add(gui$1displayentry.name);
            if (Minecraft.getInstance().font.width(gui$1displayentry.name)>longestLine)
                longestLine = Minecraft.getInstance().font.width(gui$1displayentry.name);
            i9++;
        }

        List<Component> scoreboardLines = new ArrayList<>();
        for (Component scoreboardDatum : scoreboardData) {
            HashSet<Object> chars = new HashSet<>();
            for (char c : scoreboardDatum.getString().toCharArray()) {
                chars.add(c);
            }

            if (chars.size() != 3 && scoreboardDatum.getString().toCharArray().length != 3) {
                scoreboardLines.add(scoreboardDatum);
            }
        }

        int lineHeight = Minecraft.getInstance().font.lineHeight;
        this.widgetHeight = Minecraft.getInstance().font.lineHeight*scoreboardLines.size()+10 + lineHeight;
        this.widgetWidth = longestLine+10;
        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        pose.translate(0, 0, 1000);
        guiGraphics.fill(this.getLeftSide(), this.getTop(), this.getLeftSide()+this.widgetWidth,
                this.getTop() + this.widgetHeight, this.backgroundColor);

        guiGraphics.drawCenteredString(Minecraft.getInstance().font, component, this.getLeftSide() + this.widgetWidth/2, this.getTop()+5, -1);

        int i = 0;
        Font font = Minecraft.getInstance().font;
        if (scale < 1.0f)
            font = MinescapeAddons.getInstance().getVanillaFont();
        for (Component scoreboardLine : scoreboardLines) {
            guiGraphics.drawString(font, scoreboardLine, this.getLeftSide()+5,
                    this.getTop()+5+lineHeight*i+lineHeight, -1);
            i++;
        }
        pose.popPose();
    }
}
