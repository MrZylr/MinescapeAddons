package com.zylr.minescapeaddons.addons.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "createTitle", at = @At("HEAD"), cancellable = true)
    private void onUpdateTitle(CallbackInfoReturnable<String> cir) {
        // Prevent the game from changing the window title
        cir.cancel();
        cir.setReturnValue("Minecraft: Minescape Addons");
    }
}
