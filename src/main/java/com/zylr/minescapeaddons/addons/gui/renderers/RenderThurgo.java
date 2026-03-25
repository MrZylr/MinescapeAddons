package com.zylr.minescapeaddons.addons.gui.renderers;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.utils.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class RenderThurgo {
    public static final ResourceLocation THURGO = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/notsothiccthurgo.png");
    private int lifeSpan;
    private int currentHeight;
    private int increment;
    public Timer timer;

    public RenderThurgo() {
        this.lifeSpan = 20;
        this.currentHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.timer = new Timer();
        this.timer.startTimer();
    }

    public void render(GuiGraphics gui) {
        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = width*2;
        if (currentHeight-increment > 0) {
            increment++;
        }
        gui.blit(THURGO, 0, currentHeight-increment, 0, 0, width, height, width,
                height);
    }

    public boolean shouldThurgoDie() {
        return this.timer.getSeconds() >= lifeSpan;
    }
}
