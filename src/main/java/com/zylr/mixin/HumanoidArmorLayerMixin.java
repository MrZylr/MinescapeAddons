package com.zylr.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.client.ArmorOverrideResolver;
import com.zylr.client.PerfDebug;
import com.zylr.client.hud.HudManager;
import com.zylr.client.items.armor.AbstractArmorItem;
import com.zylr.client.items.armor.client.ArmorClientExtension;
import com.zylr.client.items.armor.client.model.ArmorModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.IdentityHashMap;
import java.util.Map;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin {
    private static final Map<Item, ItemStack> OVERRIDE_RENDER_STACKS = new IdentityHashMap<>();

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    private void minescapeaddon$renderCustomArmor(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        ItemStack itemStack,
        EquipmentSlot equipmentSlot,
        int packedLight,
        HumanoidRenderState renderState,
        CallbackInfo ci
    ) {
        long start = PerfDebug.start();
        try {
        if (!HudManager.getInstance().isArmorOverridesEnabled()) {
            return;
        }

        if (itemStack.isEmpty()) {
            return;
        }

        Item item = itemStack.getItem();
        ItemStack renderStack = itemStack;
        AbstractArmorItem armorItem;
        if (item instanceof AbstractArmorItem directArmorItem) {
            armorItem = directArmorItem;
        } else {
            Item overrideItem = ArmorOverrideResolver.resolveOverride(itemStack, equipmentSlot, renderState);
            if (!(overrideItem instanceof AbstractArmorItem overrideArmorItem)) {
                return;
            }
            item = overrideItem;
            armorItem = overrideArmorItem;
            renderStack = OVERRIDE_RENDER_STACKS.computeIfAbsent(item, ItemStack::new);
        }

        Identifier texture = armorItem.getCustomArmorTexture();
        if (texture == null) {
            return;
        }

        ItemStack finalRenderStack = renderStack;
        ArmorClientExtension.providerFor(finalRenderStack).ifPresent(provider -> {
            ArmorModel model = provider.getModel(null, finalRenderStack, equipmentSlot);
            model.partVisible(equipmentSlot);
            submitNodeCollector.order(1).submitModel(
                model,
                renderState,
                poseStack,
                RenderTypes.armorCutoutNoCull(texture),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                -1,
                null,
                renderState.outlineColor,
                null
            );

            if (itemStack.hasFoil()) {
                submitNodeCollector.order(2).submitModel(
                    model,
                    renderState,
                    poseStack,
                    RenderTypes.armorEntityGlint(),
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    -1,
                    null,
                    renderState.outlineColor,
                    null
                );
            }

            ci.cancel();
        });
        } finally {
            PerfDebug.record("armor.mixin", start);
        }
    }
}
