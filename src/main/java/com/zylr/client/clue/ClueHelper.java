package com.zylr.client.clue;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;

import java.util.Locale;

public final class ClueHelper {
    private static ClueScrollClue cachedClue;
    private static int lastInventoryHash;
    private static int lastHeldHash;

    private ClueHelper() {
    }

    public static ClueScrollClue activeClue(Minecraft minecraft) {
        if (minecraft == null || minecraft.player == null) return null;
        Inventory inventory = minecraft.player.getInventory();
        if (!hasInventoryClueScroll(inventory)) {
            cachedClue = null;
            return null;
        }

        ItemStack held = clueScrollHeldByPlayer(minecraft);
        int inventoryHash = inventoryHash(inventory);
        int heldHash = held.isEmpty() ? 0 : stackHash(held);
        if (cachedClue != null && inventoryHash == lastInventoryHash && (held.isEmpty() || heldHash == lastHeldHash)) {
            return cachedClue;
        }

        boolean inventoryChanged = inventoryHash != lastInventoryHash;
        lastInventoryHash = inventoryHash;
        if (!held.isEmpty()) {
            lastHeldHash = heldHash;
            ClueScrollClue heldClue = ClueScrollClue.match(searchableText(held));
            if (heldClue != null) cachedClue = heldClue;
        }
        if (cachedClue == null || inventoryChanged) {
            cachedClue = findMatchingInventoryClue(inventory);
        }
        return cachedClue;
    }

    private static ItemStack clueScrollHeldByPlayer(Minecraft minecraft) {
        ItemStack mainHand = minecraft.player.getMainHandItem();
        if (isClueScroll(mainHand)) return mainHand;
        ItemStack offHand = minecraft.player.getOffhandItem();
        if (isClueScroll(offHand)) return offHand;
        return ItemStack.EMPTY;
    }

    private static boolean hasInventoryClueScroll(Inventory inventory) {
        if (inventory == null) return false;
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            if (isClueScroll(inventory.getItem(slot))) return true;
        }
        return false;
    }

    private static ClueScrollClue findMatchingInventoryClue(Inventory inventory) {
        if (inventory == null) return null;
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (!isClueScroll(stack)) continue;
            ClueScrollClue clue = ClueScrollClue.match(searchableText(stack));
            if (clue != null) return clue;
        }
        return null;
    }

    private static boolean isClueScroll(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        return normalize(stack.getHoverName().getString()).contains("clue scroll");
    }

    private static String searchableText(ItemStack stack) {
        StringBuilder text = new StringBuilder(stack.getHoverName().getString());
        ItemLore lore = stack.get(DataComponents.LORE);
        if (lore != null) {
            lore.lines().forEach(line -> text.append('\n').append(line.getString()));
        }
        return text.toString();
    }

    private static int inventoryHash(Inventory inventory) {
        int hash = 1;
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            hash = 31 * hash + stackHash(inventory.getItem(slot));
        }
        return hash;
    }

    private static int stackHash(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return 0;
        return searchableText(stack).hashCode();
    }

    private static String normalize(String text) {
        if (text == null) return "";
        return text.replaceAll("(?i)\\u00A7[0-9A-FK-OR]", "")
            .replaceAll("\\s+", " ")
            .trim()
            .toLowerCase(Locale.ROOT);
    }
}
