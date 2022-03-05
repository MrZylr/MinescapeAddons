package com.zylr.minescapeaddons.addons.armor;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.armor.item.MetalArmorItem;
import com.zylr.minescapeaddons.addons.armor.item.barrows.DharoksArmorItem;
import com.zylr.minescapeaddons.addons.armor.item.barrows.GuthansArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MinescapeItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, Main.ID);

    // Rune
    public static final RegistryObject<MetalArmorItem> RUNE_CHESTPLATE = ITEMS.register("rune_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.RUNE, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> RUNE_LEGGINGS = ITEMS.register("rune_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.RUNE, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> RUNE_BOOTS = ITEMS.register("rune_boots",
            () -> new MetalArmorItem(MinescapeArmorMaterial.RUNE, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    // Bronze
    public static final RegistryObject<MetalArmorItem> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZE, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZE, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZE, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZESKIRT_LEGGINGS = ITEMS.register("bronzeskirt_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZESKIRT, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Bronze Trim
    public static final RegistryObject<MetalArmorItem> BRONZETRIM_CHESTPLATE = ITEMS.register("bronzetrim_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZETRIM, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZETRIM_LEGGINGS = ITEMS.register("bronzetrim_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZETRIM, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZESKIRTTRIM_LEGGINGS = ITEMS.register("bronzeskirttrim_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZESKIRTTRIM, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Bronze Gold
    public static final RegistryObject<MetalArmorItem> BRONZEGOLD_CHESTPLATE = ITEMS.register("bronzegold_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZEGOLD, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZEGOLD_LEGGINGS = ITEMS.register("bronzegold_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZEGOLD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BRONZESKIRTGOLD_LEGGINGS = ITEMS.register("bronzeskirtgold_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BRONZESKIRTGOLD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Iron
    public static final RegistryObject<MetalArmorItem> IRON_CHESTPLATE = ITEMS.register("iron_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRON, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRON_LEGGINGS = ITEMS.register("iron_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRON, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRON_BOOTS = ITEMS.register("iron_boots",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRON, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRONSKIRT_LEGGINGS = ITEMS.register("ironskirt_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONSKIRT, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Iron Trim
    public static final RegistryObject<MetalArmorItem> IRONTRIM_CHESTPLATE = ITEMS.register("irontrim_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONTRIM, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRONTRIM_LEGGINGS = ITEMS.register("irontrim_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONTRIM, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRONSKIRTTRIM_LEGGINGS = ITEMS.register("ironskirttrim_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONSKIRTTRIM, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Iron Gold
    public static final RegistryObject<MetalArmorItem> IRONGOLD_CHESTPLATE = ITEMS.register("irongold_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONGOLD, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRONGOLD_LEGGINGS = ITEMS.register("irongold_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONGOLD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> IRONSKIRTGOLD_LEGGINGS = ITEMS.register("ironskirtgold_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.IRONSKIRTGOLD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Black
    public static final RegistryObject<MetalArmorItem> BLACK_CHESTPLATE = ITEMS.register("black_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACK, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACK_LEGGINGS = ITEMS.register("black_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACK, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACK_BOOTS = ITEMS.register("black_boots",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACK, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACKSKIRT_LEGGINGS = ITEMS.register("blackskirt_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKSKIRT, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Black Trim
    public static final RegistryObject<MetalArmorItem> BLACKTRIM_CHESTPLATE = ITEMS.register("blacktrim_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKTRIM, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACKTRIM_LEGGINGS = ITEMS.register("blacktrim_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKTRIM, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACKSKIRTTRIM_LEGGINGS = ITEMS.register("blackskirttrim_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKSKIRTTRIM, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    // Black Gold
    public static final RegistryObject<MetalArmorItem> BLACKGOLD_CHESTPLATE = ITEMS.register("blackgold_chestplate",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKGOLD, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACKGOLD_LEGGINGS = ITEMS.register("blackgold_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKGOLD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<MetalArmorItem> BLACKSKIRTGOLD_LEGGINGS = ITEMS.register("blackskirtgold_leggings",
            () -> new MetalArmorItem(MinescapeArmorMaterial.BLACKSKIRTGOLD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));

    // Barrows Sets
    public static final RegistryObject<GuthansArmorItem> GUTHANS_CHESTPLATE = ITEMS.register("guthans_chestplate",
            () -> new GuthansArmorItem(MinescapeArmorMaterial.GUTHANS, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<DharoksArmorItem> DHAROKS_CHESTPLATE = ITEMS.register("dharoks_chestplate",
            () -> new DharoksArmorItem(MinescapeArmorMaterial.DHAROKS, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
            ;

}
