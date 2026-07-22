package com.zylr.mixin;

import com.zylr.client.AgilityShortcutOutlines;
import com.zylr.client.hud.HudManager;
import com.zylr.client.items.cape.CustomCapeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandRenderer.class)
public class ArmorStandRendererMixin {
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/ArmorStand;Lnet/minecraft/client/renderer/entity/state/ArmorStandRenderState;F)V", at = @At("TAIL"))
    private void minescapeaddon$hideCustomCapeCarrierItem(ArmorStand armorStand, ArmorStandRenderState renderState, float partialTick, CallbackInfo ci) {
        minescapeaddon$highlightAgilityShortcut(armorStand, renderState);

        if (!HudManager.getInstance().isCapeOverridesEnabled()) {
            return;
        }

        if (!(armorStand.getVehicle() instanceof Avatar)) {
            return;
        }

        ItemStack headItem = armorStand.getItemBySlot(EquipmentSlot.HEAD);
        if (CustomCapeRegistry.resolve(headItem) == null) {
            return;
        }

        renderState.headItem.clear();
        renderState.wornHeadType = null;
        renderState.wornHeadProfile = null;
    }

    private static void minescapeaddon$highlightAgilityShortcut(ArmorStand armorStand, ArmorStandRenderState renderState) {
        if (!HudManager.getInstance().isAgilityShortcutOutlinesEnabled()) {
            return;
        }

        if (!AgilityShortcutOutlines.isAgilityShortcut(armorStand)) {
            return;
        }

        Player player = Minecraft.getInstance().player;
        if (player != null) {
            renderState.outlineColor = AgilityShortcutOutlines.outlineColor(player, armorStand);
        }
    }
}
