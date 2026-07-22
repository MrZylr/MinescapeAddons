package com.zylr.mixin;

import com.zylr.client.items.cape.CustomCape;
import com.zylr.client.items.cape.CustomCapeRegistry;
import com.zylr.client.items.cape.CustomCapeRenderState;
import com.zylr.client.PerfDebug;
import com.zylr.client.hud.HudManager;
import com.zylr.client.items.cape.skillcapes.MaxCapeLayer;
import com.zylr.client.items.cape.skillcapes.SkillCapeLayer;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin extends LivingEntityRenderer<Avatar, AvatarRenderState, PlayerModel> {
    protected AvatarRendererMixin(EntityRendererProvider.Context context, PlayerModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void minescapeaddon$addCustomCapeLayer(EntityRendererProvider.Context context, boolean slim, CallbackInfo ci) {
        if (!HudManager.getInstance().isCapeOverridesEnabled()) {
            return;
        }
        this.addLayer(new SkillCapeLayer(this));
        this.addLayer(new MaxCapeLayer(this));
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At("TAIL"))
    private void minescapeaddon$extractCustomCapeState(Avatar avatar, AvatarRenderState renderState, float partialTick, CallbackInfo ci) {
        long start = PerfDebug.start();
        try {
        CustomCapeRenderState customCape = (CustomCapeRenderState) renderState;
        customCape.minescapeaddon$setCapeType(null);
        customCape.minescapeaddon$setCapeTexture(null);
        if (!HudManager.getInstance().isCapeOverridesEnabled()) {
            return;
        }

        for (var passenger : avatar.getPassengers()) {
            if (!(passenger instanceof ArmorStand armorStand)) {
                continue;
            }

            ItemStack headItem = armorStand.getItemBySlot(EquipmentSlot.HEAD);
            CustomCape cape = CustomCapeRegistry.resolve(headItem);
            if (cape != null) {
                customCape.minescapeaddon$setCapeType(cape.type());
                customCape.minescapeaddon$setCapeTexture(cape.texture());
                return;
            }
        }
        } finally {
            PerfDebug.record("cape.extract", start);
        }
    }
}
