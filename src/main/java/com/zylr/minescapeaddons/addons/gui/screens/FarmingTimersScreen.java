package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingLocations;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingPatchLocations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarmingTimersScreen extends Screen {
    private static final ResourceLocation FARMING_ALERT = ResourceLocation.fromNamespaceAndPath(
            MinescapeAddons.MOD_ID, "textures/gui/alert_circle.png");

    private static final int MODULE_WIDTH = 420;
    private static final int MODULE_HEIGHT = 44;
    private static final int BORDER_SIZE = 2;
    private static final int PADDING = 10;
    private static final int TAB_HEIGHT = 24;
    private static final int TAB_MARGIN = 8;
    private static final int SCROLL_SPEED = 20;
    private static final int SCROLLBAR_WIDTH = 6;
    private static final int MIN_THUMB_HEIGHT = 20;

    private static final Color PANEL_BG = new Color(30, 34, 40, 230);
    private static final Color CONTENT_BG = new Color(20, 22, 26, 200);
    private static final int HEADER_COLOR = new Color(40, 44, 50, 240).getRGB();
    private static final int ACCENT = new Color(100, 100, 255, 200).getRGB();
    private static final int TEXT_COLOR = Color.WHITE.getRGB();

    private final Minecraft mc = Minecraft.getInstance();

    // layout
    private final int contentWidth = 740;
    private int contentStartX;
    private int availableContentWidth;
    private int panelWidth;
    private final int headerHeight = 64;
    private int tabsBottomY = 0;
    private int panelsStartY = 0;

    private double scrollY = 0;
    private int totalContentHeight = 0;
    private int titleHeight;

    private FarmingLocations selectedLocation = null;
    private final List<Button> tabButtons = new ArrayList<>();
    private final List<PatchPanel> patchPanels = new ArrayList<>();
    private final List<FarmingLocations> tabLocations = new ArrayList<>();

    public FarmingTimersScreen() {
        super(Component.literal("Farming Timers"));
    }

    @Override
    protected void init() {
        super.init();
        this.titleHeight = this.font.lineHeight + 6;
        updateLayoutValues();
        initTabs();
        buildPatchPanels();
    }

    private void updateLayoutValues() {
        // leave a small margin and ensure content fits within screen
        int margin = 20;
        int maxAvailable = Math.max(200, this.width - margin * 2);
        this.availableContentWidth = Math.min(contentWidth, maxAvailable);
        int newContentStartX = Math.max(margin, (this.width - this.availableContentWidth) / 2);
        this.contentStartX = newContentStartX;
        // panel width must fit inside content area and leave room for scrollbar + padding
        int usable = this.availableContentWidth - PADDING * 2 - SCROLLBAR_WIDTH - 8;
        this.panelWidth = Math.max(120, Math.min(MODULE_WIDTH, usable));
    }

    private void initTabs() {
        this.tabButtons.clear();
        this.tabLocations.clear();
        int x = this.contentStartX + PADDING;
        int tabY = headerHeight + 12;
        int maxRight = this.contentStartX + this.availableContentWidth - PADDING;

        boolean useInitials = this.mc.getWindow().getWidth() < 1500
                || this.mc.getWindow().getHeight() < 900;

        int minTabWidth = useInitials ? 44 : 80;
        int labelPadding = useInitials ? 12 : 18;
        int tabH = useInitials ? Math.max(16, TAB_HEIGHT - 4) : TAB_HEIGHT;

        for (FarmingLocations loc : FarmingLocations.values()) {
            if (!loc.ingame) continue;
            final FarmingLocations locRef = loc;
            String label = getTabLabel(locRef, useInitials);
            int tabWidth = Math.max(minTabWidth, this.font.width(label) + labelPadding);

            // clamp and wrap if needed
            if (tabWidth > this.availableContentWidth - PADDING * 2) tabWidth = this.availableContentWidth - PADDING * 2;
            if (x + tabWidth > maxRight) {
                x = this.contentStartX + PADDING;
                tabY += tabH + TAB_MARGIN;
            }

            Button tab = Button.builder(Component.literal(label), btn -> {
                        this.selectedLocation = locRef;
                        this.scrollY = 0;
                        buildPatchPanels();
                    })
                    .bounds(x, tabY, tabWidth, tabH)
                    .build();
            this.tabButtons.add(tab);
            this.tabLocations.add(locRef);
            x += tabWidth + TAB_MARGIN;

            if (this.selectedLocation == null) {
                this.selectedLocation = locRef;
            }
        }

        this.tabsBottomY = tabY + (useInitials ? Math.max(16, TAB_HEIGHT - 4) : TAB_HEIGHT);
    }

    private void buildPatchPanels() {
        // re-lay out tabs in case width changed
        initTabs();

        this.clearWidgets();
        for (Button tb : this.tabButtons) {
            this.addRenderableWidget(tb);
        }

        this.patchPanels.clear();
        if (this.selectedLocation == null) return;

        int yStart = Math.max(headerHeight + 20, this.tabsBottomY + 12);
        this.panelsStartY = yStart;

        int x = this.contentStartX + PADDING;
        int y = yStart;

        Map<FarmingPatchLocations, ?> patches = this.selectedLocation.patches;
        for (FarmingPatchLocations patchLoc : FarmingPatchLocations.values()) {
            if (!patches.containsKey(patchLoc)) continue;
            PatchPanel panel = new PatchPanel(x, y, this.panelWidth, MODULE_HEIGHT, this.selectedLocation, patchLoc);
            panel.addWidgetsToScreen();
            this.patchPanels.add(panel);
            y += MODULE_HEIGHT + PADDING;
        }
        // Subtract the trailing padding so totalContentHeight represents the true content extent
        this.totalContentHeight = Math.max(0, y - this.panelsStartY - PADDING);
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        // update layout if the window width changed (avoid constant rebuilds unnecessarily)
        int prevAvailable = this.availableContentWidth;
        updateLayoutValues();
        if (this.availableContentWidth != prevAvailable) {
            buildPatchPanels();
        }

        // custom background + header
        drawCustomBackground(gui);

        // header
        gui.fill(0, 0, this.width, headerHeight, HEADER_COLOR);
        gui.drawCenteredString(this.font, "Farming Timers", this.width / 2, 18, TEXT_COLOR);
        gui.drawCenteredString(this.font, "Track and reset farming patch timers", this.width / 2, 36, 0xA0A0A0);
        gui.fill(0, headerHeight - 2, this.width, headerHeight, ACCENT);

        // content background box
        gui.fill(this.contentStartX, headerHeight, this.contentStartX + this.availableContentWidth,
                this.height - 24, CONTENT_BG.getRGB());


        // scissor panels area and render panels
        int scissorX = this.contentStartX + PADDING;
        int scissorY = this.panelsStartY;
        int scissorW = this.availableContentWidth - PADDING * 2 - SCROLLBAR_WIDTH - 4;
        int scissorH = this.height - 24;
        if (scissorW < 0) scissorW = 0;
        gui.enableScissor(scissorX, scissorY, scissorW, scissorH);
        renderPanels(gui, mouseX, mouseY);
        gui.disableScissor();

        // scrollbar for panels
        renderScrollbar(gui);

        // render widgets (tabs and reset buttons) on top
        super.render(gui, mouseX, mouseY, partialTick);
        // draw tabs (tab buttons are also added as widgets; draw pill background/highlight here)
        this.renderTabs(gui, mouseX, mouseY);
    }

    private void drawCustomBackground(GuiGraphics gui) {
        gui.fillGradient(0, 0, this.width, this.height, 0xC0101010, Color.BLACK.getRGB());
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // prevent default darkening background when super.render is called
    }

    private void renderPanels(GuiGraphics gui, int mouseX, int mouseY) {
        for (PatchPanel panel : this.patchPanels) {
            panel.render(gui, mouseX, mouseY);
        }
    }

    private void renderTabs(GuiGraphics gui, int mouseX, int mouseY) {
        boolean useInitials = this.mc.getWindow().getWidth() < 1500
                || this.mc.getWindow().getHeight() < 900;
        int inset = useInitials ? 2 : 4;

        for (int i = 0; i < this.tabButtons.size(); i++) {
            Button tb = this.tabButtons.get(i);
            FarmingLocations loc = this.tabLocations.size() > i ? this.tabLocations.get(i) : null;
            boolean isSelected = loc != null && loc == this.selectedLocation;

            int bg = isSelected ? new Color(80, 80, 120, 220).getRGB() : new Color(50, 50, 60, 200).getRGB();
            int tx = tb.getX();
            int ty = tb.getY();
            int tw = tb.getWidth();
            int th = tb.getHeight();
            gui.fill(tx - inset, ty - inset, tx + tw + inset, ty + th + inset, bg);

            // If label choice changed (due to resize) update the button text so super.render draws correct label.
            String expected = getTabLabel(loc, useInitials);
            if (!tb.getMessage().getString().equals(expected)) {
                tb.setMessage(Component.literal(expected));
            }

            // Show tooltip with full name when using initials and hovered
            if (useInitials && loc != null) {
                boolean hovered = mouseX >= tx && mouseX <= tx + tw && mouseY >= ty && mouseY <= ty + th;
                if (hovered) {
                    String fullName = getTabLabel(loc, false);
                    if (fullName != null && !fullName.isEmpty()) {
                        int pad = 6;
                        int textW = this.font.width(fullName);
                        int tooltipW = textW + pad * 2;
                        int tooltipH = this.font.lineHeight + 6;

                        int tipX = mouseX + 12;
                        int tipY = ty - tooltipH - 6; // try above the tab

                        int contentRight = this.contentStartX + this.availableContentWidth - PADDING;
                        if (tipX + tooltipW > contentRight) {
                            tipX = contentRight - tooltipW;
                        }
                        if (tipX < this.contentStartX + PADDING) {
                            tipX = this.contentStartX + PADDING;
                        }
                        if (tipY < headerHeight) {
                            // not enough space above, place below the tab
                            tipY = ty + th + 6;
                        }

                        // tooltip background
                        gui.pose().pushPose();
                        gui.pose().translate(0, 0, 500); // ensure tooltip is on top
                        gui.fill(tipX, tipY, tipX + tooltipW, tipY + tooltipH, new Color(20, 22, 26, 230).getRGB());
                        // subtle border
                        gui.fill(tipX, tipY, tipX + tooltipW, tipY + 1, new Color(120,120,255,180).getRGB());
                        // text
                        gui.drawString(this.font, fullName, tipX + pad, tipY + 3, TEXT_COLOR);
                        gui.pose().popPose();
                    }
                }
            }
        }
    }

    private void renderScrollbar(GuiGraphics gui) {
        int viewport = this.height - this.panelsStartY - 24;
        if (this.totalContentHeight <= viewport) return;

        int scrollbarX = this.contentStartX + this.availableContentWidth - PADDING - SCROLLBAR_WIDTH;
        int scrollbarY = this.panelsStartY;
        int scrollbarHeight = viewport;

        gui.fill(scrollbarX, scrollbarY, scrollbarX + SCROLLBAR_WIDTH,
                scrollbarY + scrollbarHeight, new Color(50, 50, 50, 140).getRGB());

        float viewportRatio = (float) viewport / Math.max(1, totalContentHeight);
        int thumbHeight = Math.max(MIN_THUMB_HEIGHT, (int) (scrollbarHeight * viewportRatio));
        float scrollRatio = (float) scrollY / Math.max(1, totalContentHeight - viewport);
        int thumbY = scrollbarY + (int) ((scrollbarHeight - thumbHeight) * scrollRatio);

        gui.fill(scrollbarX, thumbY, scrollbarX + SCROLLBAR_WIDTH, thumbY + thumbHeight,
                new Color(120, 120, 255, 200).getRGB());
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollDelta) {
        int viewport = this.height - this.panelsStartY - 24;
        double maxScroll = Math.max(0, totalContentHeight - viewport);
        this.scrollY = Math.max(0, Math.min(this.scrollY - scrollDelta * SCROLL_SPEED, maxScroll));
        buildPatchPanels();
        return true;
    }

    private boolean shouldRenderPanelAtY(int y) {
        // Use the same scissor area used in render()
        int viewportTop = this.panelsStartY;
        int viewportHeight = Math.max(0, this.height - viewportTop - 24);
        int viewportBottom = viewportTop + viewportHeight;

        // Panel spans [y, y + panelHeight). Use panel height (MODULE_HEIGHT) for overlap.
        // Consider any overlap as visible.
        return (y + MODULE_HEIGHT) > viewportTop && y < viewportBottom;
    }

    private String getTabLabel(FarmingLocations loc, boolean useInitials) {
        if (loc == null) return "";
        String full = formatLocationName(loc.name());
        if (!useInitials) return full;
        String[] parts = full.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (!p.isEmpty()) sb.append(Character.toUpperCase(p.charAt(0)));
        }
        return sb.toString();
    }

    private String formatLocationName(String name) {
        String[] parts = name.replace("_", " ").split(" ");
        if (parts.length == 1) {
            return capitalize(parts[0]);
        }
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(capitalize(part)).append(" ");
        }
        return result.toString().trim();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.toLowerCase().substring(1);
    }

    // Inner class for panel + reset button
    private class PatchPanel {
        private final Minecraft mc = Minecraft.getInstance();
        private final Font font = mc.font;

        private final int baseX;
        private final int baseYOrig;
        private final int width;
        private final int height;
        private final FarmingLocations location;
        private final FarmingPatchLocations patchLocation;
        private Button resetButton;

        PatchPanel(int x, int y, int w, int h, FarmingLocations location, FarmingPatchLocations patchLocation) {
            this.baseX = x;
            this.baseYOrig = y;
            this.width = w;
            this.height = h;
            this.location = location;
            this.patchLocation = patchLocation;
        }

        void addWidgetsToScreen() {
            // Compute the on-screen y with fractional scroll considered
            int y = (int) Math.floor(this.baseYOrig - scrollY);

            // Only create the reset button when the panel would be visible (or partially visible)
            if (!shouldRenderPanelAtY(y)) {
                if (this.resetButton != null) {
                    removeWidget(this.resetButton);
                    this.resetButton = null;
                }
                return;
            }

            int btnSize = 18;
            int outBtnX = this.baseX + this.width + 12; // preferred outside position
            int rightLimit = contentStartX + availableContentWidth - PADDING - btnSize;
            // choose a position that doesn't go off-screen; if not enough space, place inside panel right edge
            int btnX = Math.min(outBtnX, rightLimit);
            int insideBtnMin = this.baseX + this.width - btnSize - PADDING;
            if (btnX < insideBtnMin) btnX = Math.max(insideBtnMin, this.baseX + this.width - btnSize - 4);
            int btnY = y + (this.height - btnSize) / 2;

            if (this.resetButton != null) {
                removeWidget(this.resetButton);
            }
            this.resetButton = Button.builder(Component.literal("R"), btn -> {
                        location.patches.get(patchLocation).timer.reset();
                    })
                    .bounds(btnX, btnY, btnSize, btnSize)
                    .build();
            addRenderableWidget(this.resetButton);
        }

        void render(GuiGraphics gui, int mouseX, int mouseY) {
            int y = (int) (this.baseYOrig - scrollY);
            if (!shouldRenderPanelAtY(y)) return;

            int bx = this.baseX;
            int by = y;

            // panel background + border
            gui.fill(bx, by, bx + this.width, by + this.height, PANEL_BG.getRGB());
            gui.fill(bx + BORDER_SIZE, by + BORDER_SIZE,
                    bx + this.width - BORDER_SIZE, by + this.height - BORDER_SIZE,
                    new Color(48, 52, 60, 200).getRGB());

            // small title band
            gui.fill(bx + BORDER_SIZE + 2, by + BORDER_SIZE + 2,
                    bx + this.width - BORDER_SIZE - 2,
                    by + BORDER_SIZE + 2 + titleHeight, new Color(60, 66, 80, 200).getRGB());

            // patch name
            String patchName = location.patches.get(patchLocation).getPatchName();
            int nameX = bx + PADDING;
            int nameY = by + BORDER_SIZE + 6;
            gui.drawString(font, patchName, nameX, nameY, TEXT_COLOR);

            // alert icon
            if (location.patches.get(patchLocation).timer.isCompleted()) {
                gui.blit(FARMING_ALERT, bx + this.width - 28, nameY - 2, 0, 0, 8, 8, 8, 8);
            }

            // timer text
            String time = location.patches.get(patchLocation).timer.timeLeft();
            String timerText = time != null ? "Time: " + time : "No timer";
            gui.drawString(font, timerText, nameX, by + BORDER_SIZE + 2 + titleHeight + 8, 0xCCCCCC);

            // update reset button position for scrolling (reuse same clamping logic)
            if (this.resetButton != null) {
                int btnSize = 18;
                int outBtnX = bx + this.width + 12;
                int rightLimit = contentStartX + availableContentWidth - PADDING - btnSize;
                int btnX = Math.min(outBtnX, rightLimit);
                int insideBtnMin = bx + this.width - btnSize - PADDING;
                if (btnX < insideBtnMin) btnX = Math.max(insideBtnMin, bx + this.width - btnSize - 4);
                int btnY = by + (this.height - btnSize) / 2;
                this.resetButton.setPosition(btnX, btnY);
            }
        }
    }
}