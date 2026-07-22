package com.zylr.mixin;

import com.zylr.client.EntityOcclusionCulling;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(
        method = "shouldRender(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/culling/Frustum;DDD)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private <E extends Entity> void minescapeaddon$suppressOccludedEntityRender(
        E entity,
        Frustum frustum,
        double x,
        double y,
        double z,
        CallbackInfoReturnable<Boolean> cir
    ) {
        if (EntityOcclusionCulling.shouldSuppress(entity)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
        method = "shouldRender(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/culling/Frustum;DDD)Z",
        at = @At("RETURN"),
        cancellable = true
    )
    private <E extends Entity> void minescapeaddon$forceVisibleLargeDisplayModel(
        E entity,
        Frustum frustum,
        double x,
        double y,
        double z,
        CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue() && EntityOcclusionCulling.shouldForceVisible(entity, frustum)) {
            cir.setReturnValue(true);
        }
    }
}
