package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.item.cape.skillcapes.SkillCapeLayer;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

import java.util.List;

public class ArmorStandListener {
    public final static List<Integer> TRIM_VALUES = List.of(0, 1, 2, 90, 97, 101, 102, 103);

    public static boolean removeCape = false;

    @SubscribeEvent
    public void onArmorStandRender(RenderLevelStageEvent e) {
        if (e.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null)
            return;

        if (Config.getArmorOverride()) {
            for (Entity entity : mc.player.getPassengers()) {
                if (entity instanceof ArmorStand armorStand) {
                    //System.out.println(armorStand.getOffhandItem().getItem() + " : " + armorStand.getMainHandItem().getDamageValue());
                    if (armorStand.getOffhandItem().getItem() == Items.IRON_SHOVEL) {
                        //armorStand.setItemSlot(EquipmentSlot.OFFHAND, Items.AIR.getDefaultInstance());
                    }
                    if (armorStand.getMainHandItem().getItem() == Items.IRON_SHOVEL) {
                        if (TRIM_VALUES.contains(armorStand.getMainHandItem().getDamageValue())) {
                            armorStand.setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                        }
                    }
                }
            }
        }
    }
}
