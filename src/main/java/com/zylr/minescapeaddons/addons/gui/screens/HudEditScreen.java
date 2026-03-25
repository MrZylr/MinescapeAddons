package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;

import java.util.List;

public class HudEditScreen extends ModScreens {

    IWidget draggedWidget;
    double dragOffsetX;
    double dragOffsetY;
    // Frozen at drag-start so the pivot doesn't shift as the widget moves (prevents jitter)
    double dragPivotX;
    double dragPivotY;
    float dragScale;

    public HudEditScreen() {
        super(Component.literal("Hud Edit Screen"));
        this.draggedWidget = null;
        this.dragOffsetX = 0;
        this.dragOffsetY = 0;
        this.dragPivotX = 0;
        this.dragPivotY = 0;
        this.dragScale = 1.0f;
        Widget.isHudEditOpen = true;
    }

    @Override
    public void onClose() {
        Widget.isHudEditOpen = false;
        super.onClose();
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        String instructions = "Click and drag widgets to move them around. Scroll Wheel or +/- to resize hovered widgets. Middle click a widget to change its background color.";

        int maxWidth = mc.getWindow().getGuiScaledWidth() - 20;
        int actualWidth = getWordWrappedWidth(instructions, maxWidth);
        int actualHeight = getWordWrappedHeight(instructions, maxWidth);

        int x = (mc.getWindow().getGuiScaledWidth() / 2) - (actualWidth / 2);

        gui.fillGradient(0, 0, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(), -1072689136, -804253680);
        gui.drawWordWrap(mc.font, FormattedText.of(instructions), x, 10, maxWidth, 0xFFFFFF);

        renderWidgets(gui);
    }

    // For word wrapped text, you need to simulate the wrapping
    private int getWordWrappedWidth(String text, int maxWidth) {
        List<FormattedText> lines = mc.font.getSplitter().splitLines(text, maxWidth, Style.EMPTY);
        int actualWidth = 0;

        for (FormattedText line : lines) {
            int lineWidth = mc.font.width(line);
            actualWidth = Math.max(actualWidth, lineWidth);
        }

        return actualWidth;
    }

    // Get the height of word wrapped text
    private int getWordWrappedHeight(String text, int maxWidth) {
        List<FormattedText> lines = mc.font.getSplitter().splitLines(text, maxWidth, Style.EMPTY);
        return lines.size() * mc.font.lineHeight;
    }

    /** Converts SNAKE_CASE or camelCase to "Title Case With Spaces". */
    private String toTitleCase(String input) {
        StringBuilder sb = new StringBuilder();
        for (String word : input.split("[_\\s]+")) {
            if (word.isEmpty()) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(word.charAt(0)));
            sb.append(word.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    @Override
    public void renderWidgets(GuiGraphics gui) {
        for (IWidget iWidget : widgets) {
            if (iWidget == null) continue;
            if (!(iWidget instanceof Widget widget)) {
                iWidget.render(gui);
                continue;
            }

            boolean wasVisible = widget.isVisible();

            // Force visible so the widget renders even if its condition (e.g. no target,
            // wrong region, config disabled) would normally hide it.
            if (!wasVisible) widget.setVisible(true);

            widget.render(gui);

            // Draw a red outline around widgets that are normally invisible so the
            // user knows they're there but currently inactive.
            if (!wasVisible) {
                widget.setVisible(false);
                // Refresh anchor so bounds are correct before drawing outline
                widget.getAnchorXFromRelative(widget.relativeAnchorx);
                widget.getAnchorYFromRelative(widget.relativeAnchorY);
                int x1 = widget.getLeftSide();
                int y1 = widget.getTop();
                int x2 = (int)(x1 + widget.getWidgetWidth()  * widget.scale);
                int y2 = (int)(y1 + widget.getWidgetHeight() * widget.scale);
                int col = 0xAAFF4444; // semi-transparent red

                // Build a readable label from the widget type, e.g. "Barrows Brothers Widget"
                String rawName = widget.getType() != null
                        ? widget.getType().name()
                        : widget.getClass().getSimpleName();
                String label = toTitleCase(rawName);

                gui.pose().pushPose();
                gui.pose().translate(0, 0, 400);
                // top / bottom / left / right 1-px borders
                gui.fill(x1,   y1,   x2,   y1+1, col);
                gui.fill(x1,   y2-1, x2,   y2,   col);
                gui.fill(x1,   y1,   x1+1, y2,   col);
                gui.fill(x2-1, y1,   x2,   y2,   col);
                // Label centred inside the outline
                int labelW = mc.font.width(label);
                int labelX = x1 + (x2 - x1) / 2 - labelW / 2;
                int labelY = y1 + (y2 - y1) / 2 - mc.font.lineHeight / 2;
                gui.drawString(mc.font, label, labelX, labelY, 0xFFFF4444, false);
                gui.pose().popPose();
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int click) {
        // Check for if being clicked/dragged
        for (IWidget widget : widgets) {
            if (widget != null) {
                System.out.println("Checking widget: " + widget.getType());
                if (widget.isHovered()) {
                    if (widget instanceof Widget widgetInstance) {
                        if (click == 2) {
                            // Middle click to pick color
                            Widget hoveredWidget = (Widget) widget;
                            ColorPickerScreen colorPickerScreen = new ColorPickerScreen(hoveredWidget, this);
                            Minecraft.getInstance().setScreen(colorPickerScreen);
                            return true;
                        }
                        this.draggedWidget = widget;

                        // Freeze pivot and scale at drag-start — must not change during the drag
                        dragPivotX = widgetInstance.getAnchorX();
                        dragPivotY = widgetInstance.getAnchorY();
                        dragScale  = widgetInstance.scale;

                        // Offset in screen space: how far the anchor is from the click point
                        dragOffsetX = dragPivotX - mouseX;
                        dragOffsetY = dragPivotY - mouseY;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
        if (draggedWidget != null) {
            dragWidget(draggedWidget);
            return true;
        }
        return false;
    }
    private void dragWidget(IWidget widget) {
        if (widget instanceof Widget widgetInstance) {
            double screenX = mc.mouseHandler.xpos() / mc.getWindow().getGuiScale();
            double screenY = mc.mouseHandler.ypos() / mc.getWindow().getGuiScale();
            double newX = screenX + dragOffsetX;
            double newY = screenY + dragOffsetY;

            // Current position
            double curX = widgetInstance.getAnchorX();
            double curY = widgetInstance.getAnchorY();

            boolean canMoveX = !overlapsAny(widget, newX, curY, widgetInstance);
            boolean canMoveY = !overlapsAny(widget, curX, newY, widgetInstance);
            boolean canMoveBoth = canMoveX && !overlapsAny(widget, newX, newY, widgetInstance);

            if (canMoveBoth) {
                widget.setRelativeX((int) newX);
                widget.setRelativeY((int) newY);
            } else if (canMoveX) {
                widget.setRelativeX((int) newX);
            } else if (canMoveY) {
                widget.setRelativeY((int) newY);
            }
            // else: fully blocked on both axes — stay put
        }
    }

    private boolean overlapsAny(IWidget moving, double testX, double testY, Widget movingInstance) {
        double testRight  = testX + movingInstance.getWidgetWidth()  * movingInstance.scale;
        double testBottom = testY + movingInstance.getWidgetHeight() * movingInstance.scale;
        for (IWidget other : widgets) {
            if (other == moving || !(other instanceof Widget o)) continue;
            double oLeft   = o.getLeftSide();
            double oTop    = o.getTop();
            double oRight  = oLeft + o.getWidgetWidth()  * o.scale;
            double oBottom = oTop  + o.getWidgetHeight() * o.scale;
            if (testX < oRight && testRight > oLeft && testY < oBottom && testBottom > oTop)
                return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {

        // Set the anchor point in the file
        if (draggedWidget != null) {
            if (draggedWidget.getConfig() == null)
                System.out.println("Config is null");
            else {
                // Config work
                draggedWidget.getConfig().setProperty("x", draggedWidget.getRelativeAnchorX() + "");
                draggedWidget.getConfig().setProperty("y", draggedWidget.getRelativeAnchorY() + "");
                draggedWidget.saveConfig();
                // Reset draggedWidget
                draggedWidget = null;
            }
        }

        // Redraw the screen once the mouse has been let go and all data saved
        // This removes "ghost" buttons
        mc.setScreen(new HudEditScreen());
        return true;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        for (IWidget widget : widgets) {
            if (widget instanceof Widget widgetInstance) {
                if (widgetInstance.isHovered()) {
                    if (scrollY > 0) {
                        float scale = widgetInstance.scale;
                        if (scale < 1.0)
                            scale = scale + 0.05f;
                        widgetInstance.scale = scale;
                        widgetInstance.config.setProperty("scale", scale+"");
                        widgetInstance.saveConfig();
                        return true;
                    } else if (scrollY < 0) {
                        float scale = widgetInstance.scale;
                        if (scale > 0.25)
                            scale = scale - 0.05f;
                        widgetInstance.scale = scale;
                        widgetInstance.config.setProperty("scale", scale+"");
                        widgetInstance.saveConfig();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (IWidget widget : widgets) {
            if (widget instanceof Widget widgetInstance) {
                if (widgetInstance.isHovered()) {
                    if (keyCode == 61) {
                        float scale = widgetInstance.scale;
                        if (scale < 1.0)
                            scale = scale + 0.05f;
                        widgetInstance.scale = scale;
                        widgetInstance.config.setProperty("scale", scale+"");
                        widgetInstance.saveConfig();
                        return true;
                    } else if (keyCode == 45) {
                        float scale = widgetInstance.scale;
                        if (scale > 0.25)
                            scale = scale - 0.05f;
                        widgetInstance.scale = scale;
                        widgetInstance.config.setProperty("scale", scale+"");
                        widgetInstance.saveConfig();
                        return true;
                    }
                }
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
