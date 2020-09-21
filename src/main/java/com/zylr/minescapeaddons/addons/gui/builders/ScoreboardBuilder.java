package com.zylr.minescapeaddons.addons.gui.builders;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.zylr.minescapeaddons.addons.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardBuilder extends IngameGui {
    private Minecraft mc;
    private PlayerEntity player;
    private int scaledWidth;
    private int scaledHeight;


    public ScoreboardBuilder() {
        super(Minecraft.getInstance());
        this.mc= Minecraft.getInstance();
        this.player = Minecraft.getInstance().player;
        this.scaledWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.scaledHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
    }

    public void testScoreboard(){
        Scoreboard scoreboard = mc.player.getWorldScoreboard();
        ScoreObjective scoreobjective = null;
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(this.mc.player.getScoreboardName());
        if (scoreplayerteam != null) {
            int j2 = scoreplayerteam.getColor().getColorIndex();
            if (j2 >= 0) {
                scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + j2);
            }
        }

        ScoreObjective scoreobjective1 = scoreobjective != null ? scoreobjective : scoreboard.getObjectiveInDisplaySlot(1);
        if (scoreobjective1 != null) {
           this.renderScoreboard(scoreobjective1);
        }
    }


    @Override
    protected void renderScoreboard(ScoreObjective objective) {
        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);
        List<Score> list = collection.stream().filter((p_212911_0_) -> {
            return p_212911_0_.getPlayerName() != null && !p_212911_0_.getPlayerName().startsWith("#");
        }).collect(Collectors.toList());
        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        String s = objective.getDisplayName().getFormattedText();
        int i = this.getFontRenderer().getStringWidth(s);
        int j = i;

        for(Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s1 = ScorePlayerTeam.formatMemberName(scoreplayerteam, new StringTextComponent(score.getPlayerName())).getFormattedText() + ": " + TextFormatting.RED + score.getScorePoints();
            j = Math.max(j, this.getFontRenderer().getStringWidth(s1));
        }

        int l1 = collection.size() * 9;
        int i2 = this.mc.getMainWindow().getScaledHeight() / 2 + l1 / 3;
        int j2 = 3;
        int k2 = this.mc.getMainWindow().getScaledWidth() - j - 3;
        int k = 0;
        int k5 = 0;
        int l = this.mc.gameSettings.getTextBackgroundColor(0.3F);
        int i1 = this.mc.gameSettings.getTextBackgroundColor(0.4F);

        for(Score score1 : collection) {
            ++k;
            if (k != 1) {
                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s2 = ScorePlayerTeam.formatMemberName(scoreplayerteam1, new StringTextComponent(score1.getPlayerName())).getFormattedText();
                String s3 = TextFormatting.RED + "" + score1.getScorePoints();



                char[] chars = s2.toCharArray();
                boolean blank = true;

                for (int i4 = 0; i4 < chars.length; i4 += 2) {
                    if (chars[i4] != '§')
                        blank = false;
                }

                if (!blank) {
                    ++k5;
                    int j1 = i2 - k5 * 9;
                    int k1 = this.mc.getMainWindow().getScaledWidth() - 3 + 2;
                    fill(k2 - 2, j1, k1, j1 + 9, l);
                    this.getFontRenderer().drawString(s2, (float) k2, (float) j1, -1);
                }
                if (k == collection.size()) {
                    int j1 = i2 - k5 * 9;
                    int k1 = this.mc.getMainWindow().getScaledWidth() - 3 + 2;
                    fill(k2 - 2, j1 - 9 - 1, k1, j1 - 1, i1);
                    fill(k2 - 2, j1 - 1, k1, j1, l);
                    this.getFontRenderer().drawString(s, (float) (k2 + j / 2 - i / 2), (float) (j1 - 9), -1);
                }
            }
        }

    }

    protected void renderMyScoreboard(ScoreObjective objective) {
        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);

        FontRenderer font = Minecraft.getInstance().fontRenderer;

        List<Score> list = collection.stream().filter((p_212911_0_) -> {
            return p_212911_0_.getPlayerName() != null && !p_212911_0_.getPlayerName().startsWith("#");
        }).collect(Collectors.toList());
        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        String s = objective.getDisplayName().getFormattedText();
        int i = font.getStringWidth(s);
        int j = i;

        for(Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s1 = ScorePlayerTeam.formatMemberName(scoreplayerteam, new StringTextComponent(score.getPlayerName())).getFormattedText() + ": " + TextFormatting.RED + score.getScorePoints();
            j = Math.max(j, font.getStringWidth(s1));
        }

        int l1 = collection.size() * 9;
        int i2 = this.mc.getMainWindow().getScaledHeight() - Main.getInstance().getStatsPanel().getYOrigin() + 125 + l1 / 3;
        int i3 = this.mc.getMainWindow().getScaledHeight() - Main.getInstance().getStatsPanel().getYOrigin() ;
        int j2 = 3;
        int k2 = Minecraft.getInstance().getMainWindow().getScaledWidth() - Main.getInstance().getStatsPanel().getXOrigin() + 10;
        int k3 = Minecraft.getInstance().getMainWindow().getScaledWidth() - Main.getInstance().getStatsPanel().getXOrigin() + 10;
//        int k2 = this.scaledWidth - j - 3;
        int k = 0;
        int k1 = 0;
        int l = this.mc.gameSettings.getTextBackgroundColor(0.3F);
        int i1 = this.mc.gameSettings.getTextBackgroundColor(0.4F);

        for(Score score1 : collection) {
            ++k;
            if (k != 1) {
                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s2 = ScorePlayerTeam.formatMemberName(scoreplayerteam1, new StringTextComponent(score1.getPlayerName())).getFormattedText();
                char[] chars = s2.toCharArray();
                boolean blank = true;
                for (int i4 = 0; i4 < chars.length; i4+=2) {
//                    System.out.println(s2);
//                    System.out.println(chars[i4]);
                    if (chars[i4] != '§')
                        blank = false;
                }
                
                if (!blank) {
                    ++k1;
                    int j1 = i2 - k1 * 9;
                    font.drawString(s2, (float) k2, (float) j1, -1);
                }
            }
            if (k == collection.size()) {
                font.drawString(s, (float)(k3 + font.getStringWidth(s) / 2 - i / 2), (float)(i3 - 9), -1);
            }
        }

    }
}
