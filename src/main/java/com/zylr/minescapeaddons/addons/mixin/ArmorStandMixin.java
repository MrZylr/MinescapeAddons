package com.zylr.minescapeaddons.addons.mixin;

import com.zylr.minescapeaddons.addons.item.cape.skillcapes.SkillCapeLayer;
import com.zylr.minescapeaddons.addons.utils.IArmorStandMixin;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStand.class)
public class ArmorStandMixin implements IArmorStandMixin {

    @Unique
    private int minescape$cachedDamage = -1;
    @Unique
    private Item minescape$cachedItem = null;
    @Unique
    private boolean minescape$isCapeStand = false;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        ArmorStand self = (ArmorStand) (Object) this;
        if (!self.isPassenger()) {
            minescape$reset();
            return;
        }

        ItemStack headItem = self.getItemBySlot(EquipmentSlot.HEAD);
        if (headItem.isEmpty()) {
            minescape$reset();
            return;
        }

        Item item = headItem.getItem();
        int damage = headItem.getDamageValue();

        if (item == minescape$cachedItem && damage == minescape$cachedDamage) return;

        minescape$cachedItem = item;
        minescape$cachedDamage = damage;
        minescape$isCapeStand = (item == Items.DIAMOND_SHOVEL && SkillCapeLayer.SKILLCAPE_SHOVEL_CAPE_RESOURCES.containsKey(damage))
                || (item == Items.DIAMOND_PICKAXE && SkillCapeLayer.SKILLCAPE_PICKAXE_CAPE_RESOURCES.containsKey(damage));
    }

    private void minescape$reset() {
        minescape$isCapeStand = false;
        minescape$cachedDamage = -1;
        minescape$cachedItem = null;
    }

    @Override
    public boolean minescape$isCapeStand() {
        return minescape$isCapeStand;
    }
}