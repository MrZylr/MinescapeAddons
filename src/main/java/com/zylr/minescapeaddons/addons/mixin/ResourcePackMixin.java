package com.zylr.minescapeaddons.addons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Pack.class)
public class ResourcePackMixin {
    @ModifyReturnValue(method = "isFixedPosition", at = @At(value = "RETURN"))
    private boolean isAlwaysTop(boolean original) {
        return false; // Always return false to ensure this pack is not fixed at the top
    }
}
