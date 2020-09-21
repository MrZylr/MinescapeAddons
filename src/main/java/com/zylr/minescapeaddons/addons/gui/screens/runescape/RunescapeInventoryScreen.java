package com.zylr.minescapeaddons.addons.gui.screens.runescape;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.screens.vanilla.ModVanillaInventoryScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class RunescapeInventoryScreen extends ModVanillaInventoryScreen {
    private static final ResourceLocation CLEAR_BUTTON = new ResourceLocation(Main.ID, "textures/gui/clear.png");
    private static final ResourceLocation TEST_BUTTON = new ResourceLocation(Main.ID, "textures/gui/redidle.png");

    private ModConfiguration.Client client = ModConfiguration.CLIENT;


    public RunescapeInventoryScreen(PlayerEntity player){
        super(player);
    }

    @Override
    protected void init() {
        // Additions: Mr_Zylr
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();

        setMousePosition();

        // Source
        if (this.minecraft.playerController.isInCreativeMode()) {
            this.minecraft.displayGuiScreen(new CreativeScreen(this.minecraft.player));
        } else {
            super.init();
        }
        RenderSystem.popMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {}
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {}
}
