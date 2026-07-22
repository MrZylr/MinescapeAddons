package com.zylr.client.screen;

import com.zylr.client.farming.FarmingLocations;
import com.zylr.client.farming.FarmingPatch;
import com.zylr.client.farming.FarmingPatchLocations;
import com.zylr.client.farming.FarmingTimer;
import com.zylr.client.farming.FarmingUtil;
import com.zylr.client.farming.SeedType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class FarmingTimersScreen extends Screen {
    private static final int CONTENT_WIDTH = 740;
    private static final int MODULE_WIDTH = 420;
    private static final int MODULE_HEIGHT = 44;
    private static final int BORDER_SIZE = 2;
    private static final int PADDING = 10;
    private static final int HEADER_HEIGHT = 64;
    private static final int TAB_HEIGHT = 24;
    private static final int TAB_MARGIN = 8;
    private static final int SCROLL_SPEED = 20;
    private static final int SCROLLBAR_WIDTH = 6;
    private static final int MIN_THUMB_HEIGHT = 20;

    private static final int SCREEN_BG = 0xCC0C0A08;
    private static final int PANEL_BG = 0xCC2B1E14;
    private static final int PANEL_INNER = 0xCC3A2818;
    private static final int CONTENT_BG = 0xAA1B120C;
    private static final int HEADER_BG = 0xEE3A2818;
    private static final int BORDER = 0xFFBFA882;
    private static final int ACCENT = 0xFFFFD54A;
    private static final int TEXT = 0xFFF0D7B0;
    private static final int MUTED_TEXT = 0xFFBFA882;
    private static final int DONE = 0xFF7DFF8A;
    private static final int DANGER = 0xFFFF8A80;

    private final Screen parent;
    private final List<TabBounds> tabs = new ArrayList<>();
    private final List<PatchPanel> panels = new ArrayList<>();
    private final List<ClickBounds> rowResetButtons = new ArrayList<>();
    private FarmingLocations selectedLocation;
    private double scrollY;
    private int contentStartX;
    private int availableContentWidth;
    private int panelWidth;
    private int tabsBottomY;
    private int panelsStartY;
    private int totalContentHeight;

    public FarmingTimersScreen(Screen parent) {
        super(Component.literal("Farming Timers"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.updateLayoutValues();
        this.initTabs();
        this.buildPatchPanels();
        FarmingUtil.setAlertsAsChecked();
        FarmingUtil.writeTimersToFile();
    }

    @Override
    public void onClose() {
        FarmingUtil.writeTimersToFile();
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        int previousWidth = this.availableContentWidth;
        this.updateLayoutValues();
        if (previousWidth != this.availableContentWidth) {
            this.initTabs();
            this.buildPatchPanels();
            this.clampScroll();
        }

        this.rowResetButtons.clear();
        graphics.fill(0, 0, this.width, this.height, SCREEN_BG);
        this.renderHeader(graphics);
        this.renderContentBackground(graphics);
        this.renderTabs(graphics, mouseX, mouseY);
        this.renderPanels(graphics, mouseX, mouseY);
        this.renderScrollbar(graphics);
        this.renderBottomButtons(graphics, mouseX, mouseY);
        this.renderAbbreviatedTabTooltip(graphics, mouseX, mouseY);
        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (super.mouseClicked(event, doubleClick)) {
            return true;
        }
        if (event.button() != 0) {
            return false;
        }

        int mouseX = (int) event.x();
        int mouseY = (int) event.y();

        for (TabBounds tab : this.tabs) {
            if (tab.contains(mouseX, mouseY)) {
                this.selectedLocation = tab.location;
                this.scrollY = 0;
                this.buildPatchPanels();
                return true;
            }
        }

        for (ClickBounds reset : this.rowResetButtons) {
            if (reset.contains(mouseX, mouseY)) {
                reset.patch.timer.reset();
                FarmingUtil.writeTimersToFile();
                FarmingUtil.tickTimerCache();
                return true;
            }
        }

        ClickBounds done = this.doneButton();
        if (done.contains(mouseX, mouseY)) {
            this.onClose();
            return true;
        }

        ClickBounds clearDone = this.clearDoneButton();
        if (clearDone.contains(mouseX, mouseY)) {
            FarmingUtil.clearCompletedTimers();
            this.buildPatchPanels();
            this.clampScroll();
            return true;
        }

        ClickBounds resetAll = this.resetAllButton();
        if (resetAll.contains(mouseX, mouseY)) {
            FarmingUtil.resetAllTimers();
            this.buildPatchPanels();
            this.clampScroll();
            return true;
        }

        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        this.scrollY -= verticalAmount * SCROLL_SPEED;
        this.clampScroll();
        return true;
    }

    private void updateLayoutValues() {
        int margin = 20;
        int maxAvailable = Math.max(200, this.width - margin * 2);
        this.availableContentWidth = Math.min(CONTENT_WIDTH, maxAvailable);
        this.contentStartX = Math.max(margin, (this.width - this.availableContentWidth) / 2);
        int usable = this.availableContentWidth - PADDING * 2 - SCROLLBAR_WIDTH - 8;
        this.panelWidth = Math.max(140, Math.min(MODULE_WIDTH, usable));
    }

    private void initTabs() {
        this.tabs.clear();
        int x = this.contentStartX + PADDING;
        int y = HEADER_HEIGHT + 12;
        int maxRight = this.contentStartX + this.availableContentWidth - PADDING;
        boolean useInitials = this.usingInitialTabs();
        int tabHeight = useInitials ? Math.max(16, TAB_HEIGHT - 4) : TAB_HEIGHT;

        for (FarmingLocations location : FarmingLocations.values()) {
            if (!location.ingame) continue;

            String label = getTabLabel(location, useInitials);
            int tabWidth = Math.max(useInitials ? 44 : 80, this.font.width(label) + (useInitials ? 12 : 18));
            tabWidth = Math.min(tabWidth, this.availableContentWidth - PADDING * 2);

            if (x + tabWidth > maxRight) {
                x = this.contentStartX + PADDING;
                y += tabHeight + TAB_MARGIN;
            }

            this.tabs.add(new TabBounds(x, y, tabWidth, tabHeight, location, label));
            x += tabWidth + TAB_MARGIN;

            if (this.selectedLocation == null) {
                this.selectedLocation = location;
            }
        }

        this.tabsBottomY = y + tabHeight;
    }

    private void buildPatchPanels() {
        this.panels.clear();
        if (this.selectedLocation == null) {
            this.totalContentHeight = 0;
            return;
        }

        int y = Math.max(HEADER_HEIGHT + 20, this.tabsBottomY + 12);
        this.panelsStartY = y;
        int x = this.contentStartX + PADDING;

        for (Map.Entry<FarmingPatchLocations, FarmingPatch> entry : this.selectedLocation.patches.entrySet()) {
            this.panels.add(new PatchPanel(x, y, this.panelWidth, MODULE_HEIGHT, entry.getValue()));
            y += MODULE_HEIGHT + PADDING;
        }

        this.totalContentHeight = Math.max(0, y - this.panelsStartY - PADDING);
    }

    private void renderHeader(GuiGraphicsExtractor graphics) {
        graphics.fill(0, 0, this.width, HEADER_HEIGHT, HEADER_BG);
        graphics.centeredText(this.font, Component.literal("Farming Timers"), this.width / 2, 18, ACCENT);
        graphics.centeredText(this.font, Component.literal("Track and reset farming patch timers"), this.width / 2, 36, MUTED_TEXT);
        graphics.fill(0, HEADER_HEIGHT - 2, this.width, HEADER_HEIGHT, BORDER);
    }

    private void renderContentBackground(GuiGraphicsExtractor graphics) {
        graphics.fill(this.contentStartX, HEADER_HEIGHT, this.contentStartX + this.availableContentWidth, this.height - 24, CONTENT_BG);
        this.drawOutline(graphics, this.contentStartX, HEADER_HEIGHT, this.availableContentWidth, this.height - HEADER_HEIGHT - 24, BORDER);
    }

    private void renderTabs(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        for (TabBounds tab : this.tabs) {
            boolean selected = tab.location == this.selectedLocation;
            boolean hovered = tab.contains(mouseX, mouseY);
            int bg = selected ? 0xEE4A351F : hovered ? 0xDD3A2818 : 0xBB2B1E14;
            graphics.fill(tab.x - 2, tab.y - 2, tab.x + tab.width + 2, tab.y + tab.height + 2, bg);
            this.drawOutline(graphics, tab.x - 2, tab.y - 2, tab.width + 4, tab.height + 4, selected ? ACCENT : BORDER);
            graphics.centeredText(this.font, Component.literal(tab.label), tab.x + tab.width / 2, tab.y + (tab.height - this.font.lineHeight) / 2, selected ? 0xFFFFFF00 : TEXT);
        }
    }

    private void renderAbbreviatedTabTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        if (!this.usingInitialTabs()) {
            return;
        }

        for (TabBounds tab : this.tabs) {
            if (!tab.contains(mouseX, mouseY)) {
                continue;
            }

            String fullName = formatEnumName(tab.location.name());
            int padding = 6;
            int tooltipWidth = this.font.width(fullName) + padding * 2;
            int tooltipHeight = this.font.lineHeight + padding * 2;
            int tooltipX = mouseX + 12;
            int tooltipY = tab.y - tooltipHeight - 8;
            int minX = this.contentStartX + PADDING;
            int maxX = this.contentStartX + this.availableContentWidth - PADDING - tooltipWidth;

            if (tooltipX > maxX) tooltipX = maxX;
            if (tooltipX < minX) tooltipX = minX;
            if (tooltipY < HEADER_HEIGHT) tooltipY = tab.y + tab.height + 8;
            if (tooltipY + tooltipHeight > this.height - 26) tooltipY = this.height - 26 - tooltipHeight;

            graphics.fill(tooltipX, tooltipY, tooltipX + tooltipWidth, tooltipY + tooltipHeight, PANEL_BG);
            this.drawOutline(graphics, tooltipX, tooltipY, tooltipWidth, tooltipHeight, BORDER);
            graphics.text(this.font, Component.literal(fullName), tooltipX + padding, tooltipY + padding, TEXT, false);
            return;
        }
    }

    private void renderPanels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        int viewportTop = this.panelsStartY;
        int viewportBottom = this.height - 24;

        if (this.panels.isEmpty()) {
            graphics.text(this.font, Component.literal("No patches for this location."), this.contentStartX + PADDING, viewportTop, TEXT, true);
            return;
        }

        for (PatchPanel panel : this.panels) {
            int y = (int) Math.floor(panel.baseY - this.scrollY);
            if (y + MODULE_HEIGHT <= viewportTop || y >= viewportBottom) continue;
            this.renderPanel(graphics, panel, y, mouseX, mouseY);
        }
    }

    private void renderPanel(GuiGraphicsExtractor graphics, PatchPanel panel, int y, int mouseX, int mouseY) {
        int x = panel.x;
        FarmingPatch patch = panel.patch;
        FarmingTimer timer = patch.timer;
        SeedType seed = patch.getSeed();
        String seedName = seed == null || seed.name == null || seed.name.isBlank() ? "No seed" : seed.name;
        String time = timer.timeLeft();
        String timerText = time == null ? "No timer" : "Time: " + time;
        String stageText = timer.hasStarted() && seed != null ? "Stage: " + timer.getCurrentStage() + "/" + seed.stages : "Stage: -";
        int statusColor = !timer.hasStarted() ? MUTED_TEXT : timer.isCompleted() ? DONE : TEXT;

        graphics.fill(x, y, x + panel.width, y + panel.height, PANEL_BG);
        graphics.fill(x + BORDER_SIZE, y + BORDER_SIZE, x + panel.width - BORDER_SIZE, y + panel.height - BORDER_SIZE, PANEL_INNER);
        graphics.fill(x + BORDER_SIZE + 2, y + BORDER_SIZE + 2, x + panel.width - BORDER_SIZE - 2, y + BORDER_SIZE + 16, HEADER_BG);
        this.drawOutline(graphics, x, y, panel.width, panel.height, BORDER);

        graphics.text(this.font, Component.literal(patch.getPatchName()), x + PADDING, y + 7, timer.isCompleted() ? DONE : TEXT, false);
        if (timer.isCompleted()) {
            graphics.text(this.font, Component.literal("!"), x + panel.width - 18, y + 7, DANGER, true);
        }

        int detailY = y + 26;
        graphics.text(this.font, Component.literal(seedName), x + PADDING, detailY, MUTED_TEXT, false);
        graphics.text(this.font, Component.literal(stageText), x + 145, detailY, statusColor, false);
        graphics.text(this.font, Component.literal(timerText), x + 245, detailY, statusColor, false);

        ClickBounds reset = this.panelResetButton(panel, y);
        if (reset.width > 0) {
            this.rowResetButtons.add(reset);
            this.drawSmallButton(graphics, reset, "R", reset.contains(mouseX, mouseY), timer.hasStarted());
        }
    }

    private void renderScrollbar(GuiGraphicsExtractor graphics) {
        int viewport = this.viewportHeight();
        if (this.totalContentHeight <= viewport) return;

        int x = this.contentStartX + this.availableContentWidth - PADDING - SCROLLBAR_WIDTH;
        int y = this.panelsStartY;
        int height = viewport;
        graphics.fill(x, y, x + SCROLLBAR_WIDTH, y + height, 0x884A351F);

        float viewportRatio = (float) viewport / Math.max(1, this.totalContentHeight);
        int thumbHeight = Math.max(MIN_THUMB_HEIGHT, (int) (height * viewportRatio));
        float scrollRatio = (float) this.scrollY / Math.max(1, this.totalContentHeight - viewport);
        int thumbY = y + (int) ((height - thumbHeight) * scrollRatio);
        graphics.fill(x, thumbY, x + SCROLLBAR_WIDTH, thumbY + thumbHeight, BORDER);
    }

    private void renderBottomButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.drawBottomButton(graphics, this.doneButton(), "Done", mouseX, mouseY);
        this.drawBottomButton(graphics, this.clearDoneButton(), "Clear Done", mouseX, mouseY);
        this.drawBottomButton(graphics, this.resetAllButton(), "Reset All", mouseX, mouseY);
    }

    private void drawBottomButton(GuiGraphicsExtractor graphics, ClickBounds bounds, String label, int mouseX, int mouseY) {
        boolean hovered = bounds.contains(mouseX, mouseY);
        graphics.fill(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, hovered ? 0xEE4A351F : PANEL_BG);
        this.drawOutline(graphics, bounds.x, bounds.y, bounds.width, bounds.height, hovered ? ACCENT : BORDER);
        graphics.centeredText(this.font, Component.literal(label), bounds.x + bounds.width / 2, bounds.y + 6, hovered ? 0xFFFFFF00 : TEXT);
    }

    private void drawSmallButton(GuiGraphicsExtractor graphics, ClickBounds bounds, String label, boolean hovered, boolean enabled) {
        int border = enabled ? (hovered ? ACCENT : BORDER) : 0xFF6E5B42;
        int text = enabled ? (hovered ? 0xFFFFFF00 : TEXT) : 0xFF6E5B42;
        graphics.fill(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, enabled ? PANEL_BG : 0x882B1E14);
        this.drawOutline(graphics, bounds.x, bounds.y, bounds.width, bounds.height, border);
        graphics.centeredText(this.font, Component.literal(label), bounds.x + bounds.width / 2, bounds.y + 5, text);
    }

    private void drawOutline(GuiGraphicsExtractor graphics, int x, int y, int width, int height, int color) {
        graphics.fill(x, y, x + width, y + 1, color);
        graphics.fill(x, y + height - 1, x + width, y + height, color);
        graphics.fill(x, y, x + 1, y + height, color);
        graphics.fill(x + width - 1, y, x + width, y + height, color);
    }

    private ClickBounds panelResetButton(PatchPanel panel, int panelY) {
        int size = 18;
        int outsideX = panel.x + panel.width + 12;
        int rightLimit = this.contentStartX + this.availableContentWidth - PADDING - SCROLLBAR_WIDTH - size - 6;
        int x = Math.min(outsideX, rightLimit);
        int insideMin = panel.x + panel.width - size - PADDING;
        if (x < insideMin) {
            x = Math.max(insideMin, panel.x + panel.width - size - 4);
        }
        return new ClickBounds(x, panelY + (panel.height - size) / 2, size, size, panel.patch);
    }

    private ClickBounds doneButton() {
        int width = 78;
        int gap = 8;
        int total = width * 3 + gap * 2;
        int x = this.width / 2 - total / 2;
        return new ClickBounds(x, this.height - 22, width, 18, null);
    }

    private ClickBounds clearDoneButton() {
        ClickBounds done = this.doneButton();
        return new ClickBounds(done.x + done.width + 8, done.y, 78, 18, null);
    }

    private ClickBounds resetAllButton() {
        ClickBounds clear = this.clearDoneButton();
        return new ClickBounds(clear.x + clear.width + 8, clear.y, 78, 18, null);
    }

    private void clampScroll() {
        this.scrollY = Math.max(0, Math.min(this.scrollY, Math.max(0, this.totalContentHeight - this.viewportHeight())));
    }

    private int viewportHeight() {
        return Math.max(0, this.height - this.panelsStartY - 24);
    }

    private boolean usingInitialTabs() {
        return this.width < 760 || this.height < 520;
    }

    private String getTabLabel(FarmingLocations location, boolean useInitials) {
        String full = formatEnumName(location.name());
        if (!useInitials) return full;

        StringBuilder builder = new StringBuilder();
        for (String part : full.split(" ")) {
            if (!part.isBlank()) {
                builder.append(Character.toUpperCase(part.charAt(0)));
            }
        }
        return builder.toString();
    }

    private static String formatEnumName(String name) {
        String[] parts = name.toLowerCase().split("_");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return builder.toString();
    }

    private record TabBounds(int x, int y, int width, int height, FarmingLocations location, String label) {
        boolean contains(int mouseX, int mouseY) {
            return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
        }
    }

    private record PatchPanel(int x, int baseY, int width, int height, FarmingPatch patch) {
    }

    private record ClickBounds(int x, int y, int width, int height, FarmingPatch patch) {
        boolean contains(int mouseX, int mouseY) {
            return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
        }
    }
}
