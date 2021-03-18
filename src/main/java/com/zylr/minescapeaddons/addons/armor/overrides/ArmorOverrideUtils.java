package com.zylr.minescapeaddons.addons.armor.overrides;



import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;


public class ArmorOverrideUtils {

    public static boolean overrideArmor(PlayerEntity p) {
        //

        NonNullList<ItemStack> armor = p.inventory.armorInventory;
        boolean replacedArmor = false;

        // Replace armor in armor slots if wearing an armor piece thats implemented
        for (ArmorToOverride uglyArmor : ArmorToOverride.values()) {
            for (int i = 0; i < armor.size(); i++) {
                if (armor.get(i).getDisplayName().getFormattedText().substring(2).equalsIgnoreCase(uglyArmor.name)) {
                    // This is armor that has matched and needs to be overridden
                    ItemStack newArmor = new ItemStack(uglyArmor.newArmor);
                    // Copy info from old armor to new armor
                    newArmor = copyItem(armor.get(i), newArmor);
                    // Replace currently worn armor with new custom model armor
                    armor.set(i, newArmor);
                    // confirm that we did replace armor
                    replacedArmor = true;
                }
            }
        }

        return replacedArmor;
    }

    public static ItemStack copyItem(ItemStack uglyArmor, ItemStack prettyArmor) {

        // Copy name and lore/tooltip from old item to new item
        prettyArmor.setTagInfo("display", uglyArmor.getTag().get("display"));
        return prettyArmor;
    }

    public static void unrenderTrim(PlayerEntity p) {

        // Loop through the players passengers to look for armorstand holding the uglyArmor trim
        for (Entity passenger : p.getPassengers()) {
            if (passenger instanceof ArmorStandEntity) {
                ArmorStandEntity armorStand = (ArmorStandEntity)passenger;
                // Unrender trim if armorstand is holding correct damage value in mainhand
                for (TrimToOverride trim : TrimToOverride.values()) {
                    if (armorStand.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getDamage() == trim.damageValue)
                        armorStand.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.AIR));
                }
            }
        }
    }
}
