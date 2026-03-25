package com.zylr.minescapeaddons.addons.gui.widgets.chat;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.WidgetType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.transformer.MixinInheritanceTracker;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatWidget extends Widget {
    private static final ResourceLocation backgroundTexture = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/chatbox/background.png");
    private static final ResourceLocation darkBackgroundTexture = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/chatbox/dark_background.png");


    // Store components so original text content is available (no styles retained)
    private List<Component> chatMessages;
    private Component chatMessage;
    String[] symbols = {"\u9000","\u9001","\u9002","\u9003","\u9004","\u9005","\u9006","\u9007","\u9008","\u9009","\u900A","\u900B","\u900C","\u900D","\u900E","\u900F","\u9010","\u9011","\u9012","\u9013","\u9014","\u9015","\u9016","\u9017","\u9018","\u9019","\u901A","\u901B","\u901C","\u901D","\u901E","\u901F","\u9020","\u9021","\u9022","\u9023","\u9024","\u9025","\u9026","\u9027","\u9028","\u9029","\u902A","\u902B","\u902C","\u902D","\u902E","\u902F","\u9030","\u9031","\u9032","\u9033","\u9034","\u9035","\u9036","\u9037","\u9038","\u9039","\u903A","\u903B","\u903C","\u903D","\u903E","\u903F","\u9040","\u9041","\u9042","\u9043","\u9044","\u9045","\u9046","\u9047","\u9048","\u9049","\u904A","\u904B","\u904C","\u904D","\u904E","\u904F","\u9050","\u9051","\u9052","\u9053","\u9054","\u9055","\u9056","\u9057","\u9058","\u9059","\u905A","\u905B","\u905C","\u905D","\u905E","\u905F","\u9060","\u9061","\u9062","\u9063","\u9064","\u9065","\u9066","\u9067","\u9068","\u9069","\u906A","\u906B","\u906C","\u906D","\u906E","\u906F","\u9070","\u9071","\u9072","\u9073","\u9074","\u9075","\u9076","\u9077","\u9078","\u9079","\u907A","\u907B","\u907C","\u907D","\u907E","\u907F","\u9080","\u9081","\u9082","\u9083","\u9084","\u9085","\u9086","\u9087","\u9088","\u9089","\u908A","\u908B","\u908C","\u908D","\u908E","\u908F","\u9090","\u9091","\u9092","\u9093","\u9094","\u9095","\u9096","\u9097","\u9098","\u9099","\u909A","\u909B","\u909C","\u909D","\u909E","\u909F","\u90A0","\u90A1","\u90A2","\u90A3","\u90A4","\u90A5","\u90A6","\u90A7","\u90A8","\u90A9","\u90AA","\u90AB","\u90AC","\u90AD","\u90AE","\u90AF","\u90B0","\u90B1","\u90B2","\u90B3","\u90B4","\u90B5","\u90B6","\u90B7","\u90B8","\u90B9","\u90BA","\u90BB","\u90BC","\u90BD","\u90BE","\u90BF","\u90C0","\u90C1","\u90C2","\u90C3","\u90C4","\u90C5","\u90C6","\u90C7","\u90C8","\u90C9","\u90CA","\u90CB","\u90CC","\u90CD","\u90CE","\u90CF","\u90D0","\u90D1","\u90D2","\u90D3","\u90D4","\u90D5","\u90D6","\u90D7","\u90D8","\u90D9","\u90DA","\u90DB","\u90DC","\u90DD","\u90DE","\u90DF","\u90E0","\u90E1","\u90E2","\u90E3","\u90E4","\u90E5","\u90E6","\u90E7","\u90E8","\u90E9","\u90EA","\u90EB","\u90EC","\u90ED","\u90EE","\u90EF","\u90F0","\u90F1","\u90F2","\u90F3","\u90F4","\u90F5","\u90F6","\u90F7","\u90F8","\u90F9","\u90FA","\u90FB","\u90FC","\u90FD","\u90FE","\u90FF"};


    // Cached wrapped lines for current messages + content width
    private List<WrappedLine> cachedWrappedLines;
    private int cachedContentWidth = -1;
    private boolean wrappedDirty = true;

    // Scrolling state
    private int scrollIndex = 0; // index of first visible line
    private boolean dragging = false;
    private int dragStartY = 0;
    private int dragStartIndex = 0;
    private final int scrollbarWidth = 8;
    private final int padding = 10;

    // Colors (ARGB)
    private static final int ARGB_BLACK = 0xFF000000;
    private static final int ARGB_BLUE = 0xFF0000FF;
    private static final int ARGB_YELLOW = 0xFFFFFF00;
    private static final int ARGB_WHITE = 0xFFFFFFFF;

    public ChatWidget(int x, int y) {
        this.type = WidgetType.CHAT;
        this.widgetWidth = 341;
        this.widgetHeight = 142;

        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;

        this.setupConfig();

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "1")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));

        this.chatMessages = new ArrayList<>();
    }

    // Holder for a wrapped line with variable segments (each segment has its own sequence and color)
    private static class WrappedLine {
        private static class Segment {
            final FormattedCharSequence seq;
            final int color;
            Segment(FormattedCharSequence seq, int color) {
                this.seq = seq;
                this.color = color;
            }
        }
        final List<Segment> segments;
        WrappedLine() {
            this.segments = new ArrayList<>();
        }
        void addSegment(FormattedCharSequence seq, int color) {
            this.segments.add(new Segment(seq, color));
        }
    }

    // Helper: split plain text into visual lines using font width (avoids relying on FormattedCharSequence->toString)
    private List<String> splitPlainLines(String text, int contentWidth, net.minecraft.client.gui.Font font) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }
        int n = text.length();
        int i = 0;
        while (i < n) {
            int j = i;
            int lastBreak = -1;
            for (int k = i; k < n; k++) {
                String sub = text.substring(i, k + 1);
                int w = font.width(sub);
                if (w <= contentWidth) {
                    j = k + 1;
                    char c = text.charAt(k);
                    if (Character.isWhitespace(c)) lastBreak = k;
                } else {
                    break;
                }
            }
            if (j == i) {
                j = Math.min(i + 1, n);
            } else if (j < n && lastBreak >= i) {
                j = lastBreak + 1;
            }
            lines.add(text.substring(i, j));
            i = j;
        }
        return lines;
    }

    private boolean isSkillSymbol(char c) {
        if (symbols == null) return false;
        String s = String.valueOf(c);
        for (String sym : symbols) {
            if (sym != null && sym.equals(s)) return true;
        }
        return false;
    }

    // Safe helper to get first sequence or an empty fallback
    private FormattedCharSequence firstOrEmpty(List<FormattedCharSequence> list) {
        return (list == null || list.isEmpty()) ? FormattedCharSequence.EMPTY : list.get(0);
    }

    // Build segments for a single display line: skill-symbol characters -> white, others -> defaultColor
    private List<WrappedLine.Segment> buildSegmentsForLine(String lineText, int defaultColor, net.minecraft.client.gui.Font font, int contentWidth) {
        List<WrappedLine.Segment> segments = new ArrayList<>();
        if (lineText == null || lineText.isEmpty()) {
            Component comp = Component.literal("");
            FormattedCharSequence seq = firstOrEmpty(font.split((FormattedText) comp, contentWidth));
            segments.add(new WrappedLine.Segment(seq, defaultColor));
            return segments;
        }

        StringBuilder sb = new StringBuilder();
        int currentColor = isSkillSymbol(lineText.charAt(0)) ? ARGB_WHITE : defaultColor;
        for (int i = 0; i < lineText.length(); i++) {
            char c = lineText.charAt(i);
            int chColor = isSkillSymbol(c) ? ARGB_WHITE : defaultColor;
            if (chColor != currentColor) {
                // flush
                String part = sb.toString();
                if (!part.isEmpty()) {
                    Component comp = Component.literal(part);
                    FormattedCharSequence seq = firstOrEmpty(font.split((FormattedText) comp, contentWidth));
                    segments.add(new WrappedLine.Segment(seq, currentColor));
                }
                sb.setLength(0);
                currentColor = chColor;
            }
            sb.append(c);
        }
        if (sb.length() > 0) {
            Component comp = Component.literal(sb.toString());
            FormattedCharSequence seq = firstOrEmpty(font.split((FormattedText) comp, contentWidth));
            segments.add(new WrappedLine.Segment(seq, currentColor));
        }
        return segments;
    }

    // Build wrapped lines with caching; avoids rebuilding unless messages or width changed.
    // Applies color rules (no original styles retained):
    // - if message contains ':' -> text before ':' black, text after ':' blue
    // - if no ':' -> whole message yellow
    // Additionally: characters matching `skillSymbols` are rendered white.
    private List<WrappedLine> buildWrappedLines(int contentWidth) {
        if (!wrappedDirty && cachedWrappedLines != null && cachedContentWidth == contentWidth) {
            return cachedWrappedLines;
        }

        Minecraft mc = Minecraft.getInstance();
        net.minecraft.client.gui.Font font = mc.font;
        List<WrappedLine> wrapped = new ArrayList<>();

        for (Component comp : this.chatMessages) {
            String msg = comp.getString();

            if (!msg.contains(":")) {
                // Entire message -> yellow
                List<String> plainLines = splitPlainLines(msg, contentWidth, font);
                for (String plainLine : plainLines) {
                    WrappedLine wl = new WrappedLine();
                    List<WrappedLine.Segment> segs = buildSegmentsForLine(plainLine, ARGB_YELLOW, font, contentWidth);
                    for (WrappedLine.Segment seg : segs) wl.addSegment(seg.seq, seg.color);
                    wrapped.add(wl);
                }
            } else {
                int colonIndex = msg.indexOf(':');
                int prefixEnd = colonIndex + 1; // include ':'
                List<String> plainLines = splitPlainLines(msg, contentWidth, font);
                int consumed = 0;
                for (String plainLine : plainLines) {
                    int lineLen = plainLine.length();

                    if (consumed + lineLen <= prefixEnd) {
                        // Entire display line is part of the prefix -> base black
                        WrappedLine wl = new WrappedLine();
                        List<WrappedLine.Segment> segs = buildSegmentsForLine(plainLine, ARGB_BLACK, font, contentWidth);
                        for (WrappedLine.Segment seg : segs) wl.addSegment(seg.seq, seg.color);
                        wrapped.add(wl);
                    } else if (consumed >= prefixEnd) {
                        // Entire display line is part of the suffix -> base blue
                        WrappedLine wl = new WrappedLine();
                        List<WrappedLine.Segment> segs = buildSegmentsForLine(plainLine, ARGB_BLUE, font, contentWidth);
                        for (WrappedLine.Segment seg : segs) wl.addSegment(seg.seq, seg.color);
                        wrapped.add(wl);
                    } else {
                        // This display line contains both prefix and suffix parts.
                        int leftLen = prefixEnd - consumed;
                        String leftText = plainLine.substring(0, leftLen);
                        String rightText = plainLine.substring(leftLen);

                        WrappedLine wl = new WrappedLine();
                        List<WrappedLine.Segment> leftSegs = buildSegmentsForLine(leftText, ARGB_BLACK, font, contentWidth);
                        for (WrappedLine.Segment seg : leftSegs) wl.addSegment(seg.seq, seg.color);
                        List<WrappedLine.Segment> rightSegs = buildSegmentsForLine(rightText, ARGB_BLUE, font, contentWidth);
                        for (WrappedLine.Segment seg : rightSegs) wl.addSegment(seg.seq, seg.color);

                        wrapped.add(wl);
                    }
                    consumed += lineLen;
                }
            }
        }

        cachedWrappedLines = wrapped;
        cachedContentWidth = contentWidth;
        wrappedDirty = false;
        return wrapped;
    }

    private int getContentWidth() {
        return this.widgetWidth - padding * 2 - scrollbarWidth - 4;
    }

    private int getContentHeight() {
        return this.widgetHeight - padding * 2 - Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        if (!Config.getCustomChat())
            return;

        super.render(gui);
        this.anchorX = 0;
        if (this.mc.screen instanceof ZylrInventoryScreen) {
            this.anchorY = mc.getWindow().getGuiScaledHeight() - 215;
            this.widgetHeight = 215;
        } else {
            this.anchorY = mc.getWindow().getGuiScaledHeight() - 142;
            this.widgetHeight = 142;
        }
        this.widgetWidth = 348;


        this.fixAnchors();
        ResourceLocation bg;

        if (Config.getDarkChatMode())
            bg = darkBackgroundTexture;
        else
            bg = backgroundTexture;

        gui.blit(bg, this.getLeftSide(), this.getTop(), 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);

        net.minecraft.client.gui.Font font = Minecraft.getInstance().font;
        int contentHeight = getContentHeight();

        // Draw Chat Line Divider
        gui.fill(this.getLeftSide() + 7, this.getTop() + contentHeight + padding, this.getRightSide() - 7, this.getTop() + contentHeight + padding + 1, Color.BLACK.getRGB());


        Minecraft.getInstance().gui.getChat().render(gui, Minecraft.getInstance().gui.getGuiTicks(), 0, 0, true);
    }

    public void renderResizable(GuiGraphics gui) {
        if (!Config.getCustomChat())
            return;

        super.render(gui);
        if (this.mc.screen instanceof ZylrInventoryScreen)
            this.widgetHeight = 215;
        else
            this.widgetHeight = 142;
        this.widgetWidth = 348;

        float scale = this.scale;

        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.fixAnchors();

        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, scale);
        ResourceLocation bg;

        if (Config.getDarkChatMode())
            bg = darkBackgroundTexture;
        else
            bg = backgroundTexture;

        gui.blit(bg, this.getLeftSide(), this.getTop(), 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);

        net.minecraft.client.gui.Font font = Minecraft.getInstance().font;
        int contentHeight = getContentHeight();

        // Draw Chat Line Divider
        gui.fill(this.getLeftSide() + 7, this.getTop() + contentHeight + padding, this.getRightSide() - 7, this.getTop() + contentHeight + padding + 1, Color.BLACK.getRGB());


        Minecraft.getInstance().gui.getChat().render(gui, Minecraft.getInstance().gui.getGuiTicks(), 0, 0, true);

        gui.pose().popPose();
    }

    public void addChatMessage(List<Component> messages, Component message) {
        this.chatMessage = message;

        // Replace messages and mark wrapped lines dirty
        this.chatMessages = new ArrayList<>(Objects.requireNonNullElse(messages, List.of()));
        this.wrappedDirty = true;

        // Recompute scroll index with new wrapped lines and current content width
        int contentWidth = getContentWidth();
        int fontLineHeight = Minecraft.getInstance().font.lineHeight;
        int contentHeight = getContentHeight();
        int visibleLines = Math.max(1, contentHeight / fontLineHeight);

        List<WrappedLine> wrappedLines = buildWrappedLines(contentWidth);
        this.scrollIndex = Math.max(0, wrappedLines.size() - visibleLines);
    }

    public List<Component> getChatMessages() {
        return chatMessages;
    }

    // Callbacks to be forwarded from the containing screen/gui
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int fontLineHeight = Minecraft.getInstance().font.lineHeight;
        int contentHeight = getContentHeight();
        int visibleLines = Math.max(1, contentHeight / fontLineHeight);

        int contentWidth = getContentWidth();
        List<WrappedLine> wrappedLines = buildWrappedLines(contentWidth);
        int totalLines = wrappedLines.size();
        int maxScrollIndex = Math.max(0, totalLines - visibleLines);

        if (this.isMouseOver(mouseX, mouseY)) {
            if (delta > 0) this.scrollIndex = Math.max(0, this.scrollIndex - 3);
            else if (delta < 0) this.scrollIndex = Math.min(maxScrollIndex, this.scrollIndex + 3);
            return true;
        }
        return false;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        int trackX = this.getRightSide() - scrollbarWidth - 7;
        int trackY = this.getTop() + 7;
        int trackHeight = this.widgetHeight - 13;

        if (mouseX >= trackX && mouseX <= trackX + scrollbarWidth && mouseY >= trackY && mouseY <= trackY + trackHeight) {
            this.dragging = true;
            this.dragStartY = (int) mouseY;
            this.dragStartIndex = this.scrollIndex;
            return true;
        }
        return false;
    }

    public boolean mouseDragged(double mouseX, double mouseY, double deltaX, double deltaY, int button) {
        if (!this.dragging) return false;

        int fontLineHeight = Minecraft.getInstance().font.lineHeight;
        int contentHeight = getContentHeight();
        int visibleLines = Math.max(1, contentHeight / fontLineHeight);

        int contentWidth = getContentWidth();
        List<WrappedLine> wrappedLines = buildWrappedLines(contentWidth);
        int totalLines = wrappedLines.size();
        int maxScrollIndex = Math.max(0, totalLines - visibleLines);

        if (totalLines <= visibleLines) {
            this.scrollIndex = 0;
            return true;
        }

        int trackY = this.getTop() + 2;
        int trackHeight = this.widgetHeight - 4;
        double proportionVisible = (double) visibleLines / (double) totalLines;
        int thumbHeight = Math.max(10, (int) (proportionVisible * trackHeight));
        int usableTrack = (trackHeight - thumbHeight);
        if (usableTrack <= 0) return true;

        int dy = (int) (mouseY - this.dragStartY);
        double deltaProportion = (double) dy / (double) usableTrack;
        int newIndex = this.dragStartIndex + (int) Math.round(deltaProportion * maxScrollIndex);
        if (newIndex < 0) newIndex = 0;
        if (newIndex > maxScrollIndex) newIndex = maxScrollIndex;
        this.scrollIndex = newIndex;
        return true;
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.dragging) {
            this.dragging = false;
            return true;
        }
        return false;
    }

    // Utility: check if mouse over widget area (scaled coordinates)
    private boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.getLeftSide() && mouseX <= this.getLeftSide() + this.widgetWidth
                && mouseY >= this.getTop() && mouseY <= this.getTop() + this.widgetHeight;
    }
}
