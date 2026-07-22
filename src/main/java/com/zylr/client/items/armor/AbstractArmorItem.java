package com.zylr.client.items.armor;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import static com.zylr.MinescapeAddon.MOD_ID;

/**
 * Basic custom armor item for Minecraft 26.1's component-based equipment API.
 */
public abstract class AbstractArmorItem extends Item {
    private static final ThreadLocal<ResourceKey<Item>> CURRENT_REGISTRY_KEY = new ThreadLocal<>();

    private final ArmorMaterial material;
    private final ArmorType type;

    public AbstractArmorItem(ArmorMaterial material, ArmorType type, Properties properties) {
        super(configureProperties(material, type, properties));
        this.material = material;
        this.type = type;
    }

    public ArmorMaterial getMaterial() {
        return this.material;
    }

    public ArmorType getType() {
        return this.type;
    }

    public boolean isFullSetActive(LivingEntity living) {
        return isFullSetActive(living, this.material);
    }

    public static boolean isFullSetActive(LivingEntity living, ArmorMaterial material) {
        if (living == null) return false;
        return hasMaterial(living, EquipmentSlot.HEAD, material)
            && hasMaterial(living, EquipmentSlot.CHEST, material)
            && hasMaterial(living, EquipmentSlot.LEGS, material)
            && hasMaterial(living, EquipmentSlot.FEET, material);
    }

    public Identifier getCustomArmorTexture() {
        return null;
    }

    public static Identifier makeCustomTextureLocation(String namespace, String id) {
        return Identifier.fromNamespaceAndPath(namespace, "textures/models/armor/custom/" + id + ".png");
    }

    public static <T extends AbstractArmorItem> Map<ArmorType, RegisteredItem<T>> createRegistry(String baseName, Function<ArmorType, T> creator) {
        EnumMap<ArmorType, RegisteredItem<T>> map = new EnumMap<>(ArmorType.class);
        for (ArmorType type : ArmorType.values()) {
            if (type == ArmorType.BODY) continue;
            String itemName = baseName + "_" + type.getName();
            Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, itemName);
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
            CURRENT_REGISTRY_KEY.set(key);
            T item;
            try {
                item = creator.apply(type);
            } finally {
                CURRENT_REGISTRY_KEY.remove();
            }
            Registry.register(BuiltInRegistries.ITEM, id, item);
            map.put(type, new RegisteredItem<>(item));
        }
        return map;
    }

    private static Properties configureProperties(ArmorMaterial material, ArmorType type, Properties properties) {
        ResourceKey<Item> key = CURRENT_REGISTRY_KEY.get();
        if (key != null) properties.setId(key);
        return properties.humanoidArmor(material, type);
    }

    private static boolean hasMaterial(LivingEntity living, EquipmentSlot slot, ArmorMaterial material) {
        Item item = living.getItemBySlot(slot).getItem();
        return item instanceof AbstractArmorItem armorItem && armorItem.getMaterial() == material;
    }

    public record RegisteredItem<T extends AbstractArmorItem>(T item) {
        public T get() { return this.item; }
        public T value() { return this.item; }
    }
}
