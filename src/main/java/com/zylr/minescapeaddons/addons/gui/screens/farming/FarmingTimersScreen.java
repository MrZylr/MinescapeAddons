package com.zylr.minescapeaddons.addons.gui.screens.farming;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import com.zylr.minescapeaddons.addons.gui.screens.ImageButtons;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingLocations;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingPatch;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingPatchLocations;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class FarmingTimersScreen extends Screen {
    Minecraft mc = Minecraft.getInstance();
    private boolean draw;
    private int totalWidth;
    public static final ResourceLocation FARMING_ICON = new ResourceLocation(Main.ID, "textures/gui/farming_icon.png");
    public static final ResourceLocation ALERT_CIRCLE = new ResourceLocation(Main.ID, "textures/gui/alert_circle.png");

    public FarmingTimersScreen() {
        super(new StringTextComponent("Farming Timers"));
        this.draw = false;
        this.totalWidth = 0;

        drawScreen();
    }

    @Override
    public void onClose() {
        // Set alerts as checked
        FarmingUtil.setAlertsAsChecked();
        this.minecraft.displayGuiScreen((Screen)null);
    }

    public void drawScreen() {
        drawTimers();
        drawButtons();
    }


    public void drawTimers() {
        //this.renderDirtBackground(0);

        FontRenderer font = mc.fontRenderer;
        int screenWidth = mc.getMainWindow().getScaledWidth();
        int screenHeight = mc.getMainWindow().getScaledHeight();

        // Screen size dependent variables
        int startingHeight = 50;
        int columnGap = 150;
        int totalColumnWidth = totalWidth;

        // Setup the initial starting point
        int x = (screenWidth - totalColumnWidth)/2;
        int initialX = x;
        int y = startingHeight;

        // Colors
        int white = 16777215;
        int lightGrey = 8421504;
        int green = 5354836;
        int yellow = 14408448;
        int red = 16711680;

        // Loop through farming locations to create the list
        for (FarmingLocations location: FarmingLocations.values()) {
            if (location.ingame) {
                int lines = location.patches.size() + 1;
                int tempY = y + font.FONT_HEIGHT*lines;
                if (screenHeight-25 < tempY) {
                    x += columnGap;
                    y = startingHeight;
                }
                // Set initialX to decide position after dry run
                font.drawString(location.name(), x, y, green);
                y += font.FONT_HEIGHT;
                // Get the general farming areas
                for (FarmingPatchLocations patchLocation: FarmingPatchLocations.values()) {
                    // Widest farm patch name is 98, using 100
                    // Check what patch locations are in the current general area
                    if (location.patches.containsKey(patchLocation)) {
                        FarmingPatch patch = location.patches.get(patchLocation);
                        // Get the patch name and timer
                        String patchName = patch.getPatchName() + " ";
                        String patchTimer = patch.timer.timeLeft();

                        // Set name color
                        int nameColor = lightGrey;
                        if (patch.timer.hasStarted() && patch.timer.isCompleted())
                            nameColor = red;
                        if (patch.timer.hasStarted() && !patch.timer.isCompleted())
                            nameColor = yellow;

                        // Draw stage alerts
                        if (!patch.timer.alertChecked && this.draw) {
                            BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth()-x, mc.getMainWindow().getScaledHeight()-y, 6, 6, ALERT_CIRCLE, false);
                        }

                        // Draw font
                        font.drawString(patchName, x+10, y, nameColor);
                        font.drawString(patchTimer, ((x+120) - 50), y, lightGrey);
                        resetPatchButton(location, patchLocation, ((x+120) - 50), y, font.getStringWidth(patchTimer), font.FONT_HEIGHT);
                        y += font.FONT_HEIGHT;
                    }
                }
                if (mc.getMainWindow().getWidth() >= 1360 && mc.getMainWindow().getHeight() >= 768)
                    y += font.FONT_HEIGHT*2;
            }
        }
        this.draw = true;
        totalWidth = (x + 120) - initialX;
        font.drawString("Click the timer to manually reset.", 10, mc.getMainWindow().getScaledHeight() - 10, 14408448);
    }

    public static void buildOnScreenButton() {
        Minecraft mc = Minecraft.getInstance();
        int color;
        if (isTimerFinished())
            color = 0x904CFF00;
        else
            color = 0x90FF0000;

        int y = Main.getInstance().getStatsPanel().topOfInventory;
        if (ModConfiguration.CLIENT.small.get())
            y *= .75;
        BuilderUtils.drawRect(40, y+25, 20, 20, color, color, false);
    }

    public static void buildOnSreenFarmIcon() {
        int y = Main.getInstance().getStatsPanel().topOfInventory;
        if (ModConfiguration.CLIENT.small.get())
            y *= .75;
        BuilderUtils.rectangle(40, y+25, 20, 20, FARMING_ICON, false);

    }

    public static void buildStageNotification() {
        int y = Main.getInstance().getStatsPanel().topOfInventory;
        if (hasStageCompleted())
            BuilderUtils.rectangle(43, y+28, 6, 6, ALERT_CIRCLE, false);
    }

    public static boolean isTimerFinished() {
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations farmingPatchLocation : location.patches.keySet()) {
                FarmingPatch patch = location.patches.get(farmingPatchLocation);
                patch.timer.timeLeft();

                if (patch.isCompleted()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasStageCompleted() {
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations farmingPatchLocation : location.patches.keySet()) {
                FarmingPatch patch = location.patches.get(farmingPatchLocation);
                patch.timer.timeLeft();
                patch.timer.isNewStage();

                if (!patch.timer.alertChecked) {
                    return true;
                }
            }
        }
        return false;
    }

    private void resetPatchButton(FarmingLocations location, FarmingPatchLocations key, int xPos, int yPos, int width, int height) {

        this.addButton(new ImageButton(xPos, yPos, width, height,
                xPos, yPos, width,
                ImageButtons.CLEAR_BUTTON,
                button -> {
                    location.patches.get(key).timer.reset();
                }));

        FarmingUtil.writeTimersToFile();
    }

    private void drawButtons() {
        this.addButton(new Button(mc.getMainWindow().getScaledWidth()-100,
                mc.getMainWindow().getScaledHeight()-25, 90, 20, "Farming Timer Options",
                button -> {
            mc.player.closeScreen();
            mc.displayGuiScreen(new FarmingTimerOptions());
                }));
    }
}
