package com.zylr.minescapeaddons.addons.gui.screens.farming;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.screens.ImageButtons;
import com.zylr.minescapeaddons.addons.skills.farming.PatchType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class FarmingTimerOptions extends Screen {
    Minecraft mc = Minecraft.getInstance();

    public FarmingTimerOptions() {
        super(new StringTextComponent("Farming Timer Options"));

        build();
    }

    public void build() {
        drawPatchTypesList();
        FontRenderer font = mc.fontRenderer;
//        font.drawString("Test", mc.getMainWindow().getScaledWidth()/2, mc.getMainWindow().getScaledHeight()/2, 14408448);
    }

    private void drawPatchTypesList() {
        FontRenderer font = mc.fontRenderer;
        int x = mc.getMainWindow().getScaledWidth()/2 - 180;
        int y = (mc.getMainWindow().getScaledHeight()/2) - ((PatchType.values().length*font.FONT_HEIGHT*3)/2);

        font.drawString("PatchType               Alert on Complete    Alert for Stages      Only Alert For All Completed", x, y, 16777215);
        y += font.FONT_HEIGHT;
        font.drawString("________________________________________________________________________", x, y, 16777215);
        y += font.FONT_HEIGHT;
        for (PatchType patchType : PatchType.values()) {
            font.drawString(patchType.name(), x, y+font.FONT_HEIGHT, 14408448);

            this.addButton(new Button(x+100, y, 50, 20, "" + patchType.alertOnComplete,
            button -> {
                patchType.toggleAlertOnComplete();
                patchType.savePatchTypeSettings();
            }));

            this.addButton(new Button(x+180, y, 50, 20, "" + patchType.alertOnStages,
                    button -> {
                        patchType.toggleAlertOnStages();
                        patchType.savePatchTypeSettings();
                    }));

            this.addButton(new Button(x+275, y, 50, 20, "" + patchType.onlyAlertForAllSameType,
                    button -> {
                        patchType.toggleOnlyAlertForAllStages();
                        patchType.savePatchTypeSettings();
                    }));

            y += font.FONT_HEIGHT*2;
            font.drawString("________________________________________________________________________", x, y, 16777215);
            y += font.FONT_HEIGHT;
        }

        this.addButton(new Button(10, mc.getMainWindow().getScaledHeight()-25, 100, 20, "Back",
                button -> {
            mc.player.closeScreen();
            mc.displayGuiScreen(new FarmingTimersScreen());
                }));

        this.addButton(new Button(mc.getMainWindow().getScaledWidth()-110, mc.getMainWindow().getScaledHeight()-25,
                100, 20, "Render Less Crops: " + ModConfiguration.CLIENT.unrenderFarmStands.get(),
                button -> {
                    ModConfiguration.CLIENT.unrenderFarmStands.set(ImageButtons.changeBoolean(ModConfiguration.CLIENT.unrenderFarmStands.get()));
                }));
    }

    private void drawCompletedPatchAlertButtons() {

    }

    private void drawCompletedPatchStageAlertButtons() {

    }

    private void drawOnlyAlertForAllPatchesCompleteButtons() {

    }
}
