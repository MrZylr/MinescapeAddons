package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.skills.farming.ClickOnFarmingPatch;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputHandler {
    @SubscribeEvent
    public void onInputEvent(InputEvent e) {
        Main.getInstance().getIdleChecker().getInputTimer().startTimer();
    }

    @SubscribeEvent
    public void onClickInput(InputEvent.ClickInputEvent e) {
        if (e.isUseItem()) {
            // Check for seed in hand
                // Check general farm location
                    // Check specific farm location
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock e) {
        if (e.getHand() == Hand.OFF_HAND)
            return;
        if (FarmingUtil.isFarmPatchClick(Minecraft.getInstance().world.getBlockState(e.getPos()).getBlock(), e.getItemStack())){
            Main.getInstance().clickOnFarmingPatch = new ClickOnFarmingPatch(e.getPos(), e.getItemStack());
        }
    }
}
