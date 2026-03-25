package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.cluehelper.Clue;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import org.slf4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClueWidget extends Widget{
    private static final Logger LOGGER = MinescapeAddons.LOGGER;

    private List<Clue> clueList;
    private boolean doingClue;
    private Clue activeClue;

    public ClueWidget(int x, int y) {
        super();
        this.type = WidgetType.CLUEHELPER;

        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;
        this.setupConfig();
        this.clueList = new ArrayList<>();
        this.populateClueList();
        this.doingClue = false;
        this.setVisible(false);
        this.activeClue = null;

        this.widgetWidth = 75;
        this.widgetHeight = 50;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));
    }

    @Override
    public void render(GuiGraphics gui){
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        super.render(gui);

        Minecraft mc = Minecraft.getInstance();
        this.anchorX = mc.getWindow().getGuiScaledWidth() - ViewportAdjustmentListener.PANEL_WIDTH - this.widgetWidth;
        this.anchorY = 0;
        if (MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().isVisible()) {
            this.anchorY = MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().getBottom() + 5;
        }
        this.fixAnchors();

        if (mc.player.getMainHandItem().getDisplayName().getString().contains("Newcomer Map"))
            return;

        if (mc.player.getMainHandItem().getItem() == Items.FILLED_MAP) {
            this.doingClue = true;
            List<Component> toolTipLines = mc.player.getMainHandItem().getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL);
            String idLine = toolTipLines.get(1).getString();
            int id = Integer.valueOf(idLine.substring(16));
            this.activeClue = this.clueList.get(id-1);
        }
        boolean hasMap = false;
        for (ItemStack item : mc.player.containerMenu.getItems()) {
            //LOGGER.info(item.getItem().toString());
            if (item.getItem() == Items.FILLED_MAP) {
                hasMap = true;
            }
        }

        if (!hasMap)
            this.doingClue = false;

        if (this.doingClue || hasMap)
            this.setVisible(true);
        else
            this.setVisible(false);

        if (this.visible) {
            if (this.activeClue != null) {
                int stepHeight = mc.font.wordWrapHeight(activeClue.getClueStep(), this.widgetWidth - 10);
                int answerHeight = mc.font.wordWrapHeight(activeClue.getAnswer(), this.widgetWidth - 10);
                int coordsHeight = mc.font.wordWrapHeight(activeClue.getCoords(), this.widgetWidth - 10);
                int  textHeight = stepHeight + answerHeight + coordsHeight + mc.font.lineHeight;
                this.widgetHeight = textHeight;
                gui.fill(this.anchorX, this.anchorY, this.anchorX + this.widgetWidth, this.anchorY + textHeight,
                        this.backgroundColor);
                gui.drawWordWrap(mc.font, FormattedText.of(activeClue.getClueStep()), this.getLeftSide()+5, this.getTop()+5, this.widgetWidth-10, Color.WHITE.getRGB());
                gui.drawWordWrap(mc.font, FormattedText.of(activeClue.getAnswer()), this.getLeftSide()+5, this.getTop()+5+stepHeight, this.widgetWidth-10, Color.WHITE.getRGB());
                gui.drawWordWrap(mc.font, FormattedText.of(activeClue.getCoords()), this.getLeftSide()+5, this.getTop()+5+stepHeight+answerHeight, this.widgetWidth-10, Color.WHITE.getRGB());
            }
        }
    }

    public void renderResizable(GuiGraphics gui) {
        super.render(gui);
        float scale = this.scale;

        Minecraft mc = Minecraft.getInstance();
        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.fixAnchors();

        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, scale);

        if (mc.player.getMainHandItem().getDisplayName().getString().contains("Newcomer Map"))
            return;

        if (mc.player.getMainHandItem().getItem() == Items.FILLED_MAP) {
            this.doingClue = true;
            List<Component> toolTipLines = mc.player.getMainHandItem().getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL);
            String idLine = toolTipLines.get(1).getString();
            int id = Integer.valueOf(idLine.substring(16));
            this.activeClue = this.clueList.get(id-1);
        }
        boolean hasMap = false;
        for (ItemStack item : mc.player.containerMenu.getItems()) {
            //LOGGER.info(item.getItem().toString());
            if (item.getItem() == Items.FILLED_MAP) {
                hasMap = true;
            }
        }

        if (!hasMap)
            this.doingClue = false;

        if (this.doingClue || hasMap)
            this.setVisible(true);
        else
            this.setVisible(false);

        if (this.visible) {
            if (this.activeClue != null) {
                int stepHeight = mc.font.wordWrapHeight(activeClue.getClueStep(), this.widgetWidth - 10);
                int answerHeight = mc.font.wordWrapHeight(activeClue.getAnswer(), this.widgetWidth - 10);
                int coordsHeight = mc.font.wordWrapHeight(activeClue.getCoords(), this.widgetWidth - 10);
                int  textHeight = stepHeight + answerHeight + coordsHeight + mc.font.lineHeight;
                this.widgetHeight = textHeight;
                gui.fill(this.anchorX, this.anchorY, this.anchorX + this.widgetWidth, this.anchorY + textHeight,
                        this.backgroundColor);
                gui.drawWordWrap(mc.font, FormattedText.of(activeClue.getClueStep()), this.getLeftSide()+5, this.getTop()+5, this.widgetWidth-10, Color.WHITE.getRGB());
                gui.drawWordWrap(mc.font, FormattedText.of(activeClue.getAnswer()), this.getLeftSide()+5, this.getTop()+5+stepHeight, this.widgetWidth-10, Color.WHITE.getRGB());
                gui.drawWordWrap(mc.font, FormattedText.of(activeClue.getCoords()), this.getLeftSide()+5, this.getTop()+5+stepHeight+answerHeight, this.widgetWidth-10, Color.WHITE.getRGB());
            }
        }

        gui.pose().popPose();
    }

    public void checkForCasket() {
        for (ItemStack item : mc.player.containerMenu.getItems()) {
            if (item.getDisplayName().getString().contains("Reward Casket")) {
                this.clueComplete();
                return;
            }
        }
    }

    private void clueComplete () {
        this.setVisible(false);
        this.activeClue = null;
        this.doingClue = false;
    }

    private void populateClueList() {
        this.clueList.add(new Clue(1, "", "Between G.E. and Varrock Treasure Chest area", "X:455 Y:63 Z:-541"));
        this.clueList.add(new Clue(2, "", "South of Varrock Platebody store / West of Varrock Archery store", "X:612 Y:65 Z:-410"));
        this.clueList.add(new Clue(3, "", "South-east of Fred the Farmer's house / Right next to the Lumbridge treasure chest", "X:548 Y:65 Z:84"));
        this.clueList.add(new Clue(4, "", "South of Lumbridge East Mine", "X: 623 Y:65 Z:399"));
        this.clueList.add(new Clue(5, "", "West of Lumbridge Castle / East of encampment at the edge of the Lumbridge Swamp", "X: 459 Y:63 Z:217"));
        this.clueList.add(new Clue(6, "", "West of the Champion's Guild / West of the Varrock West Mine", "X: 430 Y:65 Z:-243"));
        this.clueList.add(new Clue(7, "", "South of Port Sarim docks", "X: 4 Y:72 Z:355"));
        this.clueList.add(new Clue(8, "", "West of Karamja General Store / East of Karamja Volcano", "X:-258 Y:65 Z:395"));
        this.clueList.add(new Clue(9, "", "South of Rimmington Mine", "X:-131 Y:65 Z:179"));
        this.clueList.add(new Clue(10, "", "West of the Falador farm / North of Port Sarim", "X:4 Y:65 Z:-18"));
        this.clueList.add(new Clue(11, "", "North of Sorceress' Garden / South-east of Al Kharid", "X:930 Y:67 Z:390"));
        this.clueList.add(new Clue(12, "", "West of the Al Kharid Mine", "X:809 Y:65 Z:-46"));
        this.clueList.add(new Clue(13, "", "West of the Lumbridge Cow pen (Eastern) / East of the Lumbridge Chicken coop (Eastern)", "X:660 Y:65 Z:18"));
        this.clueList.add(new Clue(14, "", "South of Varrock, inside the fencing West of the Varrock East Mine", "X:676 Y:65 Z:-192"));
        this.clueList.add(new Clue(15, "", "West of the G.E.", "X:338 Y:65 Z:-648"));
        this.clueList.add(new Clue(16, "", "North-west of the H.A.M. Hideout / South of the two farms", "X:357 Y:65 Z:64"));
        this.clueList.add(new Clue(17, "", "The northern tip on the inside of the Lumbridge cow pen (Eastern)", "X:687 Y:63 Z:-35"));
        this.clueList.add(new Clue(18, "", "West of Barbarian Village / South-east of the Body Altar (?", "X:134 Y:68 Z:-477"));
        this.clueList.add(new Clue(19, "YSITARLIK", "Krystilia", "X:264 Y:63 Z:-695"));
        this.clueList.add(new Clue(20, "UESRIQ", "Squire", "X:-132 Y:68 Z:-174"));
        this.clueList.add(new Clue(21, "GEGIA", "Aggie", "X:202 Y:62 Z:70"));
        this.clueList.add(new Clue(22, "NED", "Ned", "X:237 Y:63 Z:78"));
        this.clueList.add(new Clue(23, "LEEAL", "Leela", "X:296 Y:65 Z:65"));
        this.clueList.add(new Clue(24, "ELLKAIDY", "Lady Keli", "X:321 Y:63 Z:133"));
        this.clueList.add(new Clue(25, "OEJ", "Joe", "X:309 Y:63 Z:133"));
        this.clueList.add(new Clue(26, "LAASIS RMREON", "Seaman Lorris", "x:48 Y:66 Z:247"));
        this.clueList.add(new Clue(27, "RBUAUY", "Aubury", "X:708 Y:63 Z:-358"));
        this.clueList.add(new Clue(28, "OSMCFSEFROTIUC", "Customs Officer", "X:-204 Y:65 Z:415"));
        this.clueList.add(new Clue(29, "ETTYH", "Hetty", "X:-162 Y:63 Z:239"));
        this.clueList.add(new Clue(30, "YNIRVVISV", "Sir Vyvin", "X:-112 Y:79 Z:-174"));
        this.clueList.add(new Clue(31, "GRTUHO", "Thurgo", "X:-87 Y:65 Z:408"));
        this.clueList.add(new Clue(32, "IDOCR", "Doric", "X:-207 Y:61 Z:-508"));
        this.clueList.add(new Clue(33, "DORLE", "Reldo", "X:569 Y:62 Z:-639"));
        this.clueList.add(new Clue(34, "OHRUKA ECDOI", "Duke Horacio", "X:576 Y:71 Z:185"));
        this.clueList.add(new Clue(35, "RDRRIS ODIWZAE", "Wizard Sedridor", "X:284 Y:49 Z:376"));
        this.clueList.add(new Clue(36, "TAFEERCKARNE", "Father Aereck", "X:687 Y:64 Z:228"));
        this.clueList.add(new Clue(37, "RAMIK", "Karim", "X:751 Y:66 Z:292"));
    }

    public void setActiveClue (Clue activeClue) {
        this.activeClue = activeClue;
    }
}
