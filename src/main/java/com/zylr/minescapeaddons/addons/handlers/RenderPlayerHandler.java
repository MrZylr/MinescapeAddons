package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.armor.overrides.ArmorOverrideUtils;
import com.zylr.minescapeaddons.addons.armor.overrides.OverrideArmor;
import com.zylr.minescapeaddons.addons.skills.farming.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderPlayerHandler {
    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent e) {
        PlayerEntity p = e.getPlayer();
        PlayerModel model = e.getRenderer().getEntityModel();

        // Attempt to override armor if possible
        OverrideArmor armorOverrider = new OverrideArmor(p);
        if (armorOverrider.isOverrideArmor()) {
            armorOverrider.unrenderTrim();
            // Remove the outer cage if necessary
            if (armorOverrider.isOverrideChest()) {
                model.bipedLeftArmwear.showModel = false;
                model.bipedRightArmwear.showModel = false;
                model.bipedBodyWear.showModel = false;
            }
            if (armorOverrider.isOverrideLegs()) {
                model.bipedLeftLegwear.showModel = false;
                model.bipedRightLegwear.showModel = false;
            }
        }
//        PacketHandler.INSTANCE.send(PacketDistributor.NEAR.noArg(), );
    }

    @SubscribeEvent
    public void renderEntity(RenderLivingEvent e) {
        if (e.getEntity() instanceof ArmorStandEntity) {
            if (ModConfiguration.CLIENT.unrenderFarmStands.get())
                FarmingUtil.reduceArmorStandsInFarmingPatch((ArmorStandEntity)e.getEntity());
        }
    }
}
