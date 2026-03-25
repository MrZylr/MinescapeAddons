package com.zylr.minescapeaddons.addons.gui.widgets.menus;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.MenuButton;
import com.zylr.minescapeaddons.addons.gui.widgets.menus.items.*;
import com.zylr.minescapeaddons.addons.listeners.InputListener;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RightClickItemMenu extends AbstractRightClickMenu {
    private final Minecraft mc = Minecraft.getInstance();

    private static final List<ItemActionDefinition> EXTRA_ACTIONS = List.of(
            new ItemActionDefinition(RubItems.menuTitle, toNameList(RubItems.values(), RubItems::getName)),
            new ItemActionDefinition(PolishItems.menuTitle, toNameList(PolishItems.values(), PolishItems::getName)),
            new ItemActionDefinition(EmptyItems.menuTitle, toNameList(EmptyItems.values(), EmptyItems::getName)),
            new ItemActionDefinition(OpenItems.menuTitle, toNameList(OpenItems.values(), OpenItems::getName)),
            new ItemActionDefinition(TeleportItems.menuTitle, toNameList(TeleportItems.values(), TeleportItems::getName)),
            new ItemActionDefinition(SearchItems.menuTitle, toNameList(SearchItems.values(), SearchItems::getName)),
            new ItemActionDefinition(BuryItems.menuTitle, toNameList(BuryItems.values(), BuryItems::getName)),
            new ItemActionDefinition(CheckItems.menuTitle, toNameList(CheckItems.values(), CheckItems::getName)),
            new ItemActionDefinition(BloomItems.menuTitle, toNameList(BloomItems.values(), BloomItems::getName)),
            new ItemActionDefinition(RemoveLegsItems.menuTitle, toNameList(RemoveLegsItems.values(), RemoveLegsItems::getName)),
            new ItemActionDefinition(ExtractItems.menuTitle, toNameList(ExtractItems.values(), ExtractItems::getName))
    );

    public RightClickItemMenu(String title, int x, int y, List<MenuButton> ignoredSelections, Slot slot) {
        this(title, x, y, slot);
    }

    public RightClickItemMenu(String title, int x, int y, Slot slot) {
        super(title, x, y);
        this.scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        setupButtons(slot);
    }

    public void setupButtons(Slot slot) {
        this.selections.clear();

        String itemName = getDisplayName(slot);
        String upperItemName = itemName.toUpperCase();

        if (shouldBypassMenu(slot, upperItemName)) {
            clickSlot(slot, 1, ClickType.PICKUP);
            return;
        }

        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption(actionWithItem("Use ", itemName), b -> clickSlot(slot, 0, ClickType.PICKUP_ALL)));

        for (ItemActionDefinition definition : EXTRA_ACTIONS) {
            if (definition.matches(upperItemName)) {
                options.add(new MenuOption(goldAction(definition.actionTitle()), b -> clickSlot(slot, 1, ClickType.PICKUP)));
            }
        }

        if (upperItemName.contains("GRIMY")) {
            options.add(new MenuOption(actionWithItem("Clean ", itemName), b -> clickSlot(slot, 1, ClickType.PICKUP)));
        }

        options.add(new MenuOption(actionWithItem("Drop ", itemName), b -> clickSlot(slot, 0, ClickType.THROW)));
        options.add(new MenuOption(Component.literal("Cancel").withStyle(ChatFormatting.WHITE), b -> closeMenu()));

        buildMenu(options);
    }

    public void rightClickItem(Slot slot) {
        clickSlot(slot, 1, ClickType.PICKUP);
    }

    private boolean shouldBypassMenu(Slot slot, String upperItemName) {
        if (InputListener.isCtrlPressed || !Config.getRightClickMenu()) {
            return true;
        }

        List<Component> tooltipLines = slot.getItem().getTooltipLines(
                Item.TooltipContext.EMPTY,
                mc.player,
                TooltipFlag.NORMAL
        );

        for (Component tooltipLine : tooltipLines) {
            if (tooltipLine.getString().toUpperCase().contains("HEALS")) {
                return true;
            }
        }

        for (BypassItems bypassItem : BypassItems.values()) {
            if (upperItemName.contains(bypassItem.getName().toUpperCase()) && !upperItemName.contains("UNF")) {
                return true;
            }
        }

        return false;
    }

    private void clickSlot(Slot slot, int mouseButton, ClickType clickType) {
        if (mc.screen instanceof ZylrInventoryScreen screen) {
            mc.gameMode.handleInventoryMouseClick(
                    screen.getMenu().containerId,
                    slot.index,
                    mouseButton,
                    clickType,
                    mc.player
            );
        }
    }

    private void closeMenu() {
        if (mc.screen instanceof ZylrInventoryScreen screen) {
            screen.rightClickItemMenu = null;
        }
    }

    private Component actionWithItem(String verb, String itemName) {
        return Component.empty()
                .append(Component.literal(verb).withStyle(ChatFormatting.WHITE))
                .append(Component.literal(itemName).withStyle(ChatFormatting.GOLD));
    }

    private Component goldAction(String action) {
        return Component.literal(action).withStyle(ChatFormatting.GOLD);
    }

    private String getDisplayName(Slot slot) {
        String rawName = slot.getItem().getDisplayName().getString();
        if (rawName.length() > 2 && rawName.startsWith("[") && rawName.endsWith("]")) {
            return rawName.substring(1, rawName.length() - 1);
        }

        return rawName;
    }

    private static <E extends Enum<E>> List<String> toNameList(E[] values, Function<E, String> nameMapper) {
        List<String> names = new ArrayList<>(values.length);
        for (E value : values) {
            names.add(nameMapper.apply(value).toUpperCase());
        }
        return names;
    }

    private record ItemActionDefinition(String actionTitle, List<String> matchNames) {
        private boolean matches(String upperItemName) {
            for (String matchName : this.matchNames) {
                if (upperItemName.contains(matchName)) {
                    return true;
                }
            }
            return false;
        }
    }
}

