package com.zylr.minescapeaddons.addons.gui.widgets;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardWidget extends Widget {

    private static final File FILE = PersistenceFile.SCOREBOARDFILE;
    private int backgroundColor;
    public int anchor;

    public ScoreboardWidget() {
        super();
        this.type = WidgetType.SCOREBOARD;

        backgroundColor = -1;
        anchorX = 100;
        anchorY = 100;

        if (!setFromFile()) {
            defaultFile();
            setFromFile();
        }
    }

    @Override
    public void render() {
        super.render();
        // If anchored to right side
        switch(anchor) {
            case 1:
                this.anchorX = this.widgetWidth/2 + 4;
                break;
            case 3:
                this.anchorX = window.getScaledWidth() - this.widgetWidth / 2 - 4;
                break;
        }

        // Built off of vanilla Minecraft render scoreboard code
        Scoreboard scoreboard = this.mc.world.getScoreboard();
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
            this.buildBackground();
            this.buildScoreboard(scoreobjective1);
        }
    }
    @Override
    public List<Button> getButtons() {
        super.getButtons();
        buttons.add(anchorLeftButton());
        buttons.add(anchorRightButton());
        buttons.add(freeModeButton());
        return buttons;
    }

    private void buildScoreboard(ScoreObjective obj) {
        FontRenderer font = mc.fontRenderer;
        // Built off of vanilla Minecraft render scoreboard code

        Scoreboard scoreboard = obj.getScoreboard();

        // Lines on the scoreboard
        Collection<Score> lines = scoreboard.getSortedScores(obj);

        // Still unsure on this
        List<Score> list = lines.stream().filter((score) -> {
            return score.getPlayerName() != null && !score.getPlayerName().startsWith("#");
        }).collect(Collectors.toList());

        // This also just does stuff
        if (list.size() > 15) {
            lines = Lists.newArrayList(Iterables.skip(list, lines.size() - 15));
        } else {
            lines = list;
        }

        // Get lines out of collection
        String title = obj.getDisplayName().getFormattedText();
        List<String> strings = new ArrayList<>();
        for (Score score : lines) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String line = ScorePlayerTeam.formatMemberName(scoreplayerteam, new StringTextComponent(score.getPlayerName())).getFormattedText();
            strings.add(line);
        }
        strings.add(title);
        Collections.reverse(strings);
        // Remove blank lines
        for (int i = strings.size()-1; i >= 0; i--) {
            // Remove blank lines with only color codes
            char[] chars = strings.get(i).toCharArray();
            boolean blank = true;

            for (int i1 = 0; i1 < chars.length; i1 += 2) {
                if (chars[i1] != '§')
                    blank = false;
            }
            if (blank)
                strings.remove(i);
        }

        // Set widget size
        setHeight(strings);
        setWidth(strings);

        // Begin drawing lines
        int k = 0;

        for (String string : strings) {
            if (k != strings.size()-1) {
                // Only draw line if not a blank line
//               System.out.println(string);
                if (k == 0)
                    font.drawString(string, this.anchorX - font.getStringWidth(string)/2, this.getTop() + (font.FONT_HEIGHT * k), -1);
                else
                    font.drawString(string, this.getLeftSide(), this.getTop() + (font.FONT_HEIGHT * k), -1);
                // Setup title
            }
            ++k;
        }
    }

    private void buildBackground() {
        GuiUtils.drawGradientRect(0, this.getLeftSide()-1, this.getTop()-1, this.getRightSide()+3,
                (this.getTop()-1)+mc.fontRenderer.FONT_HEIGHT, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(0, this.getLeftSide()-1, this.getTop()-1, this.getRightSide()+3,
                this.getBottom()+1, backgroundColor, backgroundColor);
    }
    private void setWidth(List<String> strings) {
        List<Integer> stringWidths = new ArrayList<>();

        for (String string : strings) {
            stringWidths.add(mc.fontRenderer.getStringWidth(string));
        }

        this.widgetWidth = Collections.max(stringWidths);
    }

    private void setHeight(List<String> lines) {
        int stringHeight = mc.fontRenderer.FONT_HEIGHT;

        this.widgetHeight = (lines.size()-1) * stringHeight;
    }

    public ChangePageButton anchorLeftButton() {
        // Determine if the left arrow should go top or bottom
        if (isTopHalf()) {
            return new ChangePageButton(this.getLeftSide()-3, this.getBottom(), false, button -> {
                if (anchor != 1)
                    anchor = 1;
            }, false);
        }
        return new ChangePageButton(this.getLeftSide(), this.getTop() - 13, false, button -> {
            if (anchor != 1)
                anchor = 1;
            }, false);
    }

    public Button freeModeButton() {
        // Determine if the unpin button should go top or bottom
        if (isTopHalf()) {
            return new Button(this.getAnchorX()-12, this.getBottom()+1, 24, mc.fontRenderer.FONT_HEIGHT+2, "Unpin", button -> {
               if (anchor != 2)
                   anchor = 2;
            });
        }
        return new Button(this.getAnchorX()-12, this.getTop() - 13, 24, mc.fontRenderer.FONT_HEIGHT+3, "Unpin", button -> {
            if (anchor != 2)
                anchor = 2;
        });
    }

    public ChangePageButton anchorRightButton() {
        // Determine if the right arrow should go top or bottom
        if (isTopHalf()) {
            return new ChangePageButton(this.getRightSide()-20, this.getBottom(), true, button -> {
                if (anchor != 3)
                    anchor = 3;
            }, false);
        }
        return new ChangePageButton(this.getRightSide()-20, this.getTop() - 13, true, button -> {
            if (anchor != 3)
                anchor = 3;
        }, false);
    }

    private void defaultFile() {
        System.out.println("Creating default scoreboard file.");
        List<String> data = new ArrayList<>();
        // AnchorX
        data.add("AnchorX:0");
        // AnchorY
        data.add("AnchorY:0");
        // Color
        data.add("Color:" + new Color(0, 0, 0, 75).getRGB());
        // Anchor position 1=left, 2= free, 3=right
        data.add("anchor:3");
        PersistenceFile.writeFile(PersistenceFile.SCOREBOARDFILE.getPath(), data);
    }

    private boolean setFromFile() {
        Scanner reader = PersistenceFile.readFile(FILE.getPath());
        while (reader.hasNext()) {
            String[] data = reader.nextLine().split(PersistenceFile.SPLIT);

            // Loop through xp tracker file
            try {
                switch(data[0].toLowerCase()) {
                    case "anchorx":
                        anchorX = Integer.parseInt(data[1]);
                        break;
                    case "anchory":
                        anchorY = Integer.parseInt(data[1]);
                        break;
                     case "color":
                         backgroundColor = Integer.parseInt(data[1]);
                         break;
                    case"anchor":
                        anchor = Integer.parseInt((data[1]));
                         return true;
                     default:
                         return true;
                }
            } catch(Exception ex) {
                System.out.println("SCOREBOARD FILE CORRUPTED. MAKING DEFAULT FILE.");
                return false;
            }
        }
        return false;
    }
}
