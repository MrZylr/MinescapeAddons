package com.zylr.minescapeaddons.addons.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.item.cape.skillcapes.SkillCapeLayer;
import com.zylr.minescapeaddons.addons.utils.IArmorStandMixin;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.lang.reflect.Field;
import java.util.List;

@Mixin(ArmorStandRenderer.class)
public class ArmorStandRendererMixin {

    private static Field cachedLayersField = null;

    private static Field getLayersField() {
        if (cachedLayersField == null) {
            try {
                Field f = LivingEntityRenderer.class.getDeclaredField("layers");
                f.setAccessible(true);
                cachedLayersField = f;
            } catch (ReflectiveOperationException ignored) {}
        }
        return cachedLayersField;
    }

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void onConstructor(net.minecraft.client.renderer.entity.EntityRendererProvider.Context context,
                               org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        try {
            Field layersField = getLayersField();
            if (layersField != null) {
                @SuppressWarnings("unchecked")
                List<Object> layers = (List<Object>) layersField.get(this);
                layers.removeIf(l -> l instanceof CustomHeadLayer);
            }
        } catch (ReflectiveOperationException ignored) {}

        ((ArmorStandRenderer) (Object) this).addLayer(
                new CustomHeadLayer<ArmorStand, ArmorStandArmorModel>(
                        ((ArmorStandRenderer) (Object) this),
                        context.getModelSet(),
                        context.getItemInHandRenderer()) {
                    @Override
                    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ArmorStand entity,
                                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                                       float netHeadYaw, float headPitch) {
                        if (((IArmorStandMixin) entity).minescape$isCapeStand())
                            return;
                        super.render(poseStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
                    }
                });
    }
}
