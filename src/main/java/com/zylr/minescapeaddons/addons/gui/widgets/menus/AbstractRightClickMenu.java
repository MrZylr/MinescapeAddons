package com.zylr.minescapeaddons.addons.gui.widgets.menus;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.MenuButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractRightClickMenu extends Widget {
    protected static final int HORIZONTAL_PADDING = 6;
    protected static final int OPTIONS_TOP_PADDING = 6;
    protected static final int ROW_GAP = 2;
    protected static final int BOTTOM_PADDING = 6;

    protected static final ResourceLocation CLEAR = ResourceLocation.fromNamespaceAndPath(
            MinescapeAddons.MOD_ID,
            "textures/gui/clear.png"
    );

    public final List<MenuButton> selections = new ArrayList<>();
    private final String title;
    private final int spawnCenterX;

    public final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(
            MinescapeAddons.MOD_ID,
            "textures/gui/right_click_menu_background.png"
    );
    public final ResourceLocation BACKGROUND_TITLE = ResourceLocation.fromNamespaceAndPath(
            MinescapeAddons.MOD_ID,
            "textures/gui/right_click_menu_background_title.png"
    );

    public AbstractRightClickMenu(String title, int x, int y) {
        this.title = title;
        this.type = null;
        this.spawnCenterX = x;
        this.anchorY = y;

        this.widgetWidth = 100;
        this.widgetHeight = 100;
        this.isParent = true;
        this.scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;

        recenterOnSpawnPoint();
    }

    protected void buildMenu(List<MenuOption> options) {
        this.selections.clear();
        if (options.isEmpty()) {
            this.widgetWidth = 0;
            this.widgetHeight = 0;
            return;
        }

        this.widgetWidth = calculateWidth(options);
        this.widgetHeight = calculateHeight(options.size());
        recenterOnSpawnPoint();

        for (MenuOption option : options) {
            this.selections.add(new MenuButton(
                    CLEAR,
                    option.text(),
                    -1,
                    this.getLeftSide(),
                    this.getTop(),
                    this.widgetWidth,
                    mc.font.lineHeight,
                    option.onLeftClick()
            ));
        }

        updateSelectionsPositions();
    }

    @Override
    public void render(GuiGraphics gui) {
        if (this.selections.isEmpty()) {
            return;
        }

        this.scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        this.fixAnchors();
        updateSelectionsPositions();

        gui.pose().pushPose();
        gui.pose().translate(0, 0, 1500);
        this.scaleAroundAnchor(gui, this.scale);

        gui.blit(
                BACKGROUND_TITLE,
                this.getLeftSide(),
                this.getTop(),
                0,
                0,
                this.widgetWidth,
                getTitleHeight(),
                this.widgetWidth,
                getTitleHeight() + 2
        );

        gui.blit(
                BACKGROUND,
                this.getLeftSide(),
                this.getTop() + getTitleHeight(),
                0,
                0,
                this.widgetWidth,
                this.widgetHeight - getTitleHeight(),
                this.widgetWidth,
                this.widgetHeight - getTitleHeight()
        );

        gui.drawString(
                Minecraft.getInstance().font,
                this.title,
                this.getLeftSide() + 5,
                this.getTop() + 2,
                new Color(145, 131, 111, 100).getRGB(),
                false
        );

        for (MenuButton selection : this.selections) {
            selection.render(gui);
        }

        gui.pose().popPose();
    }

    /**
     * Checks whether the mouse is over a menu button using the menu's own anchor
     * as the scale pivot — matching the scaleAroundAnchor call in render().
     * Widget.isHovered() pivots at the button's own anchorX/Y which is wrong here.
     */
    public boolean isButtonHovered(MenuButton button) {
        double screenX = mc.mouseHandler.xpos() / mc.getWindow().getGuiScale();
        double screenY = mc.mouseHandler.ypos() / mc.getWindow().getGuiScale();
        // Inverse of scaleAroundAnchor(menu.anchorX, menu.anchorY)
        double wx = (screenX - this.anchorX) / this.scale + this.anchorX;
        double wy = (screenY - this.anchorY) / this.scale + this.anchorY;
        return wx >= button.getLeftSide() && wx < button.getRightSide()
                && wy >= button.getTop()  && wy < button.getBottom();
    }

    private int calculateWidth(List<MenuOption> options) {
        int widestOption = 0;
        for (MenuOption option : options) {
            widestOption = Math.max(widestOption, mc.font.width(option.text()));
        }

        int titleWidth = mc.font.width(this.title);
        return Math.max(widestOption, titleWidth) + (HORIZONTAL_PADDING * 2);
    }

    private int calculateHeight(int optionCount) {
        return getTitleHeight()
                + OPTIONS_TOP_PADDING
                + (optionCount * mc.font.lineHeight)
                + (Math.max(0, optionCount - 1) * ROW_GAP)
                + BOTTOM_PADDING;
    }

    private int getTitleHeight() {
        return mc.font.lineHeight + 2;
    }

    private void recenterOnSpawnPoint() {
        this.anchorX = this.spawnCenterX - (this.widgetWidth / 2);
    }

    protected void updateSelectionsPositions() {
        int optionY = this.getTop() + getTitleHeight() + OPTIONS_TOP_PADDING;
        for (MenuButton selection : this.selections) {
            selection.setAnchorX(this.getLeftSide());
            selection.setAnchorY(optionY);
            selection.setWidgetWidth(this.widgetWidth);
            selection.scale = this.scale;
            // Store menu's anchorY as the pivot so isHovered() scales around the menu top-left
            selection.pivotX = this.anchorX;
            selection.pivotY = this.anchorY;
            selection.hasPivotOverride = true;
            optionY += mc.font.lineHeight + ROW_GAP;
        }
    }

    public record MenuOption(Component text, MenuButton.OnLeftClick onLeftClick) {}
}
