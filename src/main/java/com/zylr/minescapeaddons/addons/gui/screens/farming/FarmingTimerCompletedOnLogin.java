package com.zylr.minescapeaddons.addons.gui.screens.farming;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class FarmingTimerCompletedOnLogin extends Screen {
    Minecraft mc = Minecraft.getInstance();

    public FarmingTimerCompletedOnLogin() {
        super(new StringTextComponent("Farming Timer Completed On Login"));
        buildLoginCheck();
    }

    public void buildLoginCheck(){
        if (ModConfiguration.CLIENT.farmingLoginMessage.get()) {
            buildTextBox();
            drawFont("You have one or more patches ready to be harvested.");
            drawLoginButtons();
        }
    }

    private void buildTextBox() {
        BuilderUtils.drawRect(width/2 + 100, height/2 + 40, 200, 100, 0x90000000, 0x90000000, false);
    }

    private void drawFont(String message) {
        FontRenderer font = mc.fontRenderer;

        font.drawString(message, width/2 - font.getStringWidth(message)/2, height/2 - 25, 14408448);
    }

    private void drawLoginButtons() {
        this.addButton(new Button(width/2 -90, height/2, 80, 20,
                "Open Timers", button -> {
            mc.player.closeScreen();
            mc.displayGuiScreen(new FarmingTimersScreen());
        }));


        this.addButton(new Button(width/2 +10, height/2, 80, 20,
                "Do Not Show Again", button -> {
            ModConfiguration.CLIENT.farmingLoginMessage.set(false);
            mc.player.closeScreen();
        }));

        this.addButton(new Button(width/2 -50, height/2 +30, 100, 20,
                "Close", button -> {
            mc.player.closeScreen();
        }));
    }
}
