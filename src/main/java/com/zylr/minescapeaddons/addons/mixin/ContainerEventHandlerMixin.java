package com.zylr.minescapeaddons.addons.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerEventHandler.class)
public interface ContainerEventHandlerMixin {
    @Inject(method = "mouseScrolled", at = @At("HEAD"), cancellable = true)
    public default void mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY, CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().screen.getClass() == null)
            return;

        // Handle Scrolling in Chest Menus
        if (Minecraft.getInstance().screen.getClass() == ContainerScreen.class) {
            ContainerScreen screen = (ContainerScreen) Minecraft.getInstance().screen;
            int slotNumber = 0;
            if (scrollY > 0) {
                for (Slot slot : screen.getMenu().slots) {
                    if (slot.getItem().getDisplayName().getString().contains("Scroll Up")) {
                        Minecraft.getInstance().gameMode.handleInventoryMouseClick(screen.getMenu().containerId,
                                slotNumber, 1, ClickType.PICKUP_ALL, Minecraft.getInstance().player);
                        return;
                    }
                    slotNumber++;
                }
            }
            if (scrollY < 0) {
                for (Slot slot : screen.getMenu().slots) {
                    if (slot.getItem().getDisplayName().getString().contains("Scroll Down")) {
                        Minecraft.getInstance().gameMode.handleInventoryMouseClick(screen.getMenu().containerId,
                                slotNumber, 1, ClickType.PICKUP_ALL, Minecraft.getInstance().player);
                        return;
                    }
                    slotNumber++;
                }
            }
        }
    }
}
