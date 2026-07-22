package com.zylr.client;

import com.zylr.client.items.OverrideArmors;
import com.zylr.client.items.OverrideArmorsSlim;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class ArmorOverrideResolver {
    private static final Map<String, Item> OVERRIDES = buildOverrideMap();
    private static final Map<String, Item> SLIM_CHEST_OVERRIDES = buildSlimOverrideMap();

    private ArmorOverrideResolver() {
    }

    public static Item resolveOverride(ItemStack stack, EquipmentSlot slot, HumanoidRenderState renderState) {
        if (stack.isEmpty()) {
            return null;
        }

        String itemName = normalize(stack.getHoverName().getString());
        if (itemName.isEmpty()) {
            return null;
        }

        if (slot == EquipmentSlot.CHEST && isSlim(renderState)) {
            Item slimOverride = SLIM_CHEST_OVERRIDES.get(itemName);
            if (slimOverride != null) return slimOverride;
        }

        return OVERRIDES.get(itemName);
    }

    private static Map<String, Item> buildOverrideMap() {
        Map<String, Item> overrides = new HashMap<>();
        for (OverrideArmors value : OverrideArmors.values()) {
            overrides.put(normalize(value.name), value.item);
        }
        return Map.copyOf(overrides);
    }

    private static Map<String, Item> buildSlimOverrideMap() {
        Map<String, Item> overrides = new HashMap<>();
        for (OverrideArmorsSlim value : OverrideArmorsSlim.values()) {
            overrides.put(normalize(value.name), value.item);
        }
        return Map.copyOf(overrides);
    }

    private static boolean isSlim(HumanoidRenderState renderState) {
        return renderState instanceof AvatarRenderState avatarRenderState
            && avatarRenderState.skin != null
            && avatarRenderState.skin.model().name().equals("SLIM");
    }

    private static String normalize(String value) {
        String normalized = value == null ? "" : value.trim().toLowerCase();
        if (normalized.length() > 1 && normalized.charAt(0) == '[' && normalized.charAt(normalized.length() - 1) == ']') {
            return normalized.substring(1, normalized.length() - 1).trim();
        }
        return normalized;
    }
}
