package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.ModContainerScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.XpTrackerWidget;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.util.ModHoverChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageButtons {
    // Inventory Screen Variables
    public static final ResourceLocation SETTINGS = new ResourceLocation(Main.ID, "textures/gui/clear.png");
    public static final ResourceLocation CLEAR_BUTTON = new ResourceLocation(Main.ID, "textures/gui/clear.png");
    public static final ResourceLocation TEST_BUTTON = new ResourceLocation(Main.ID, "textures/gui/redidle.png");


    private final Map<SkillType, Skill> skills = Main.getInstance().skills;
    private final ModConfiguration.Client client = ModConfiguration.CLIENT;
    private final Minecraft mc = Minecraft.getInstance();
    private Map<SkillType, ModHoverChecker> skillHovers;

    private ImageButton xpButton;
    private List<ImageButton> skillButtons;
    private ImageButton settingsButton;
    private ImageButton lockButton;

    public ImageButtons() {
        xpButton = new ImageButton(0, 0, 19, 21,
                0, 0, 0, CLEAR_BUTTON, button -> {});
        lockButton = new ImageButton(0, 0, 19, 21,
                0, 0, 0, CLEAR_BUTTON, button -> {});
        settingsButton = new ImageButton(0, 0, 19, 21,
                0, 0, 0, SETTINGS, button -> {});
        skillButtons = new ArrayList<>();
        skillHovers = new HashMap<>();

    }

    public ImageButton drawLockButton() {
        // Get screen size
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        // Xp tracker button
        if (Main.getInstance().getStatsPanel().getCurrentScreen() == 1) {
            this.lockButton = new ImageButton(Minecraft.getInstance().player.container.inventorySlots.get(37).xPos - 10,
                    Minecraft.getInstance().player.container.inventorySlots.get(37).yPos - 5, (int) (10), (int) (10),
                    width - 100, 100, width / 2,
                    CLEAR_BUTTON,
                    button -> {
                        // Button do stuff
                        client.protectedSlot.set(changeBoolean(client.protectedSlot.get()));
                    });
        }
        if (Minecraft.getInstance().currentScreen instanceof ModContainerScreen && !ModConfiguration.CLIENT.rsInventory.get()) {
            int guiLeft = (mc.getMainWindow().getScaledWidth() - 176) / 2;
            int guiTop = (mc.getMainWindow().getScaledHeight() - 166) / 2;
            this.lockButton = new ImageButton(guiLeft + 29,
                    guiTop + 160, (int) (10), (int) (10),
                    width - 100, 100, width / 2,
                    CLEAR_BUTTON,
                    button -> {
                // Button do stuff
                        client.protectedSlot.set(changeBoolean(client.protectedSlot.get()));
            });
        }
        return lockButton;
    }

    public ImageButton drawXpButton(int originX, int originY, int size) {
        // Get gui scale
        float scale = 2/ (float) Minecraft.getInstance().getMainWindow().getGuiScaleFactor();
        float posScale = (float)Minecraft.getInstance().getMainWindow().getGuiScaleFactor() / 2;
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        // Xp tracker button
        this.xpButton = new ImageButton((int)(width*posScale)-(originX)-2, (int)(height*posScale)-(originY), (int)(size), (int)(size),
                width - 100, 100, width / 2,
                CLEAR_BUTTON,
                button -> {
                    // Button do stuff
                    client.exp.set(changeBoolean(client.exp.get()));
                });

        return xpButton;
    }

    public void drawSkillButtons() {
        // Get screen size
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        skillButtons.clear();
        for (SkillType skillType : SkillType.values()) {
            Skill skill = skills.get(skillType);
            if (skillType == SkillType.TOTAL) {
                ImageButton skillButton = new ImageButton((int) (skill.getInvX()), (int) (skill.getInvY()), (int) (40), (int) (20),
                        (int) skill.getInvX(), (int) skill.getInvY(), width / 2,
                        CLEAR_BUTTON,
                        button -> {
                            Main.getInstance().getXpTrackerBuilder().setSkill(skill);
                            for (Widget widget : Main.getInstance().getRsHud().getWidgets()) {
                                if (widget instanceof XpTrackerWidget)
                                    ((XpTrackerWidget) widget).skill = skill;
                            }
                        });
                skillButtons.add(skillButton);
                skillHovers.put(skillType,new ModHoverChecker(skillButton, 1));
            } else {
                ImageButton skillButton = new ImageButton((int) (skill.getInvX() - 31), (int) (skill.getInvY() - 10), (int) (20), (int) (20),
                        (int) skill.getInvX(), (int) skill.getInvY(), width / 2,
                        CLEAR_BUTTON,
                        button -> {
                            Main.getInstance().getXpTrackerBuilder().setSkill(skill);
                            for (Widget widget : Main.getInstance().getRsHud().getWidgets()) {
                                if (widget instanceof XpTrackerWidget)
                                    ((XpTrackerWidget) widget).skill = skill;
                            }
                        });
                skillButtons.add(skillButton);
                skillHovers.put(skillType, new ModHoverChecker(skillButton, 1));
            }
        }
    }

    public void drawSettingsCog() {
        // Get screen size
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        this.settingsButton = new ImageButton(width-(int)((10)), 0, (int)(10), (int)(10),
                width - 100, 100, width / 2,
                SETTINGS,
                button -> {
                    // Button do stuff
                    new ConfigBuilder().build();
                });
    }

    public static boolean changeBoolean(boolean boo) {
        if (boo)
            return false;
        return true;
    }

    public ImageButton getXpButton() { return this.xpButton; }
    public List<ImageButton> getSkillButtons() { return this.skillButtons; }
    public Map<SkillType, ModHoverChecker> getSkillHovers() { return this.skillHovers; }
    public ImageButton getSettingsCog() { return this.settingsButton; }
}
