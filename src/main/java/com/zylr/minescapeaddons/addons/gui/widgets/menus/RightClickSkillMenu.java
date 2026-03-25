package com.zylr.minescapeaddons.addons.gui.widgets.menus;

import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.MenuButton;
import com.zylr.minescapeaddons.addons.skills.Skill;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ClickType;

import java.util.ArrayList;
import java.util.List;

public class RightClickSkillMenu extends AbstractRightClickMenu {
    private final Minecraft mc = Minecraft.getInstance();
    private final Skill skill;

    public RightClickSkillMenu(String title, int x, int y, List<MenuButton> ignoredSelections, Skill skill) {
        this(title, x, y, skill);
    }

    public RightClickSkillMenu(String title, int x, int y, Skill skill) {
        super(title, x, y);
        this.skill = skill;
        buildMenu(createOptions());
    }

    private List<MenuOption> createOptions() {
        List<MenuOption> options = new ArrayList<>();

        Component guide = actionText("View ", " guide");
        Component tracker = actionText("Enable ", " tracker");
        Component resetTracker = actionText("Reset ", " tracker");
        Component cancel = Component.literal("Cancel").withStyle(ChatFormatting.WHITE);

        options.add(new MenuOption(guide, b -> {
            if (mc.screen instanceof ZylrInventoryScreen screen && mc.gameMode != null && mc.player != null) {
                mc.gameMode.handleInventoryMouseClick(
                        screen.getMenu().containerId,
                        1,
                        1,
                        ClickType.PICKUP,
                        mc.player
                );
            }
        }));

        options.add(new MenuOption(tracker, b -> {
            com.zylr.minescapeaddons.addons.MinescapeAddons.getInstance().resizableClassic
                    .getXpTrackerWidget().setActiveSkill(this.skill);
            closeMenu();
        }));

        options.add(new MenuOption(resetTracker, b -> {
            this.skill.getTracker().resetTracker();
            closeMenu();
        }));

        options.add(new MenuOption(cancel, b -> closeMenu()));

        return options;
    }

    private Component actionText(String prefix, String suffix) {
        return Component.empty()
                .append(Component.literal(prefix).withStyle(ChatFormatting.WHITE))
                .append(Component.literal(this.skill.getName()).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(suffix).withStyle(ChatFormatting.WHITE));
    }

    private void closeMenu() {
        if (mc.screen instanceof ZylrInventoryScreen screen) {
            screen.rightClickSkillMenu = null;
        }
    }
}
