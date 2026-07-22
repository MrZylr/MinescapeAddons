package com.zylr.client.hud;

import com.mojang.blaze3d.platform.NativeImage;
import com.zylr.MinescapeAddon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.GuiTextRenderState;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import org.joml.Matrix3x2f;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StackSizeOverlay {
	private static final Pattern LORE_STACK_SIZE_PATTERN = Pattern.compile("^x\\s*([\\d,]+)$", Pattern.CASE_INSENSITIVE);
	private static final Pattern LORE_STOCK_PATTERN = Pattern.compile("(?i).*\\bstock\\b.*?([\\d,]+)\\s*$");
	private static final int MAX_CACHE_ENTRIES = 512;
	private static final int MAX_LABEL_CACHE_ENTRIES = 128;
	private static final String LABEL_CACHE_VERSION = "v2";
	private static final String LABEL_GLYPHS = "0123456789KM";
	private static final int LOGICAL_FONT_CELL_SIZE = 8;
	private static final int LABEL_GLYPH_SPACING = 0;
	private static final int LABEL_INSET_X = 1;
	private static final int LABEL_INSET_Y = 1;
	private static final int LABEL_SHADOW_OFFSET = 1;
	private static final int LOGICAL_LINE_OFFSET = 7;
	private static final Identifier OVERLAY_ASCII_FONT_BITMAP = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/overlay/ascii.png");
	private static final Map<LoreKey, OverlayData> OVERLAY_CACHE = new HashMap<>();
	private static final Map<String, CachedLabel> LABEL_CACHE = new LinkedHashMap<>();
	private static final Map<PreparedLabelKey, PreparedLabel> PREPARED_LABEL_CACHE = new LinkedHashMap<>();
	private static final Map<Character, GlyphSlice> GLYPH_SLICE_CACHE = new HashMap<>();
	private static NativeImage asciiGlyphSource;
	private static ResourceManager lastAsciiResourceManager;
	private static Identifier lastAsciiSourceId;
	private static ResourceManager lastPreparedResourceManager;
	private static int nextLabelId = 0;
	private static Field guiRenderStateField;
	private static Field scissorStackField;
	private static Field preparedTextField;
	private static Field boundsField;
	private static Method scissorPeekMethod;

	private StackSizeOverlay() {
	}

	public static void render(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize) {
		OverlayData overlay = overlayData(stack);
		long stockCount = overlay.stockCount();
		long stackSize = overlay.stackSize();
		if (stockCount < 0L && stackSize <= 1L) return;
		float textScale = effectiveScaleMultiplier(Mth.clamp(drawSize / 16.0F, 0.75F, 1.0F));
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(textScale, textScale);
		int lineOffset = Math.max(7, font.lineHeight - 2);
		if (stockCount >= 0L) {
			String text = format(stockCount);
			int color = color(stockCount);
			graphics.text(font, text, 0, 0, color, true);
		}
		if (stackSize > 1L) {
			String text = format(stackSize);
			int color = color(stackSize);
			graphics.text(font, text, 0, stockCount >= 0L ? lineOffset : 0, color, true);
		}
		graphics.pose().popMatrix();
	}

	public static void renderLightweightStackSize(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize) {
		renderLightweightStackSize(graphics, font, stack, x, y, drawSize, 1.0F);
	}

	public static void renderLightweightStackSize(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize, float scaleMultiplier) {
		long stackSize = overlayData(stack).stackSize();
		if (stackSize <= 1L) return;
		CachedLabel label = cachedLabel(format(stackSize), color(stackSize));
		if (label == null) return;
		renderCachedLabel(graphics, label, x + LABEL_INSET_X, y + LABEL_INSET_Y, effectiveScaleMultiplier(scaleMultiplier));
	}

	public static void renderLightweightStackSizeOrCount(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize) {
		renderLightweightStackSizeOrCount(graphics, font, stack, x, y, drawSize, 1.0F);
	}

	public static void renderLightweightStackSizeOrCount(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize, float scaleMultiplier) {
		OverlayData overlay = overlayData(stack);
		long stackSize = overlay.stackSize() > 1L ? overlay.stackSize() : Math.max(1, stack.getCount());
		if (stackSize <= 1L) return;
		CachedLabel label = cachedLabel(format(stackSize), color(stackSize));
		if (label == null) return;
		renderCachedLabel(graphics, label, x + LABEL_INSET_X, y + LABEL_INSET_Y, effectiveScaleMultiplier(scaleMultiplier));
	}

	public static void renderLightweight(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize) {
		renderLightweight(graphics, font, stack, x, y, drawSize, 1.0F);
	}

	public static void renderLightweight(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize, float scaleMultiplier) {
		OverlayData overlay = overlayData(stack);
		long stockCount = overlay.stockCount();
		long stackSize = overlay.stackSize();
		if (stockCount < 0L && stackSize <= 1L) return;
		int logicalY = 0;
		if (stockCount >= 0L) {
			CachedLabel stockLabel = cachedLabel(format(stockCount), color(stockCount));
			if (stockLabel != null) {
				renderCachedLabel(graphics, stockLabel, x + LABEL_INSET_X, y + LABEL_INSET_Y + logicalY, effectiveScaleMultiplier(scaleMultiplier));
				logicalY += LOGICAL_LINE_OFFSET;
			}
		}
		if (stackSize > 1L) {
			CachedLabel stackLabel = cachedLabel(format(stackSize), color(stackSize));
			if (stackLabel != null) {
				renderCachedLabel(graphics, stackLabel, x + LABEL_INSET_X, y + LABEL_INSET_Y + logicalY, effectiveScaleMultiplier(scaleMultiplier));
			}
		}
	}

	public static void renderLightweightStockAndStackOrCount(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize) {
		renderLightweightStockAndStackOrCount(graphics, font, stack, x, y, drawSize, 1.0F);
	}

	public static void renderLightweightStockAndStackOrCount(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y, int drawSize, float scaleMultiplier) {
		OverlayData overlay = overlayData(stack);
		long stockCount = overlay.stockCount();
		long stackSize = overlay.stackSize() > 1L ? overlay.stackSize() : Math.max(1, stack.getCount());
		if (stockCount < 0L && stackSize <= 1L) return;
		int logicalY = 0;
		if (stockCount >= 0L) {
			CachedLabel stockLabel = cachedLabel(format(stockCount), color(stockCount));
			if (stockLabel != null) {
				renderCachedLabel(graphics, stockLabel, x + LABEL_INSET_X, y + LABEL_INSET_Y + logicalY, effectiveScaleMultiplier(scaleMultiplier));
				logicalY += LOGICAL_LINE_OFFSET;
			}
		}
		if (stackSize > 1L) {
			CachedLabel stackLabel = cachedLabel(format(stackSize), color(stackSize));
			if (stackLabel != null) {
				renderCachedLabel(graphics, stackLabel, x + LABEL_INSET_X, y + LABEL_INSET_Y + logicalY, effectiveScaleMultiplier(scaleMultiplier));
			}
		}
	}

	public static void renderLightweightLabel(GuiGraphicsExtractor graphics, String text, int color, int x, int y, float scaleMultiplier) {
		if (text == null || text.isEmpty()) return;
		CachedLabel label = cachedLabel(text, color);
		if (label == null) return;
		renderCachedLabel(graphics, label, x, y, effectiveScaleMultiplier(scaleMultiplier));
	}

	private static float effectiveScaleMultiplier(float scaleMultiplier) {
		return HudManager.getInstance().isBiggerTextEnabled() ? scaleMultiplier * HudManager.BIGGER_TEXT_SCALE : scaleMultiplier;
	}

	private static OverlayData overlayData(ItemStack stack) {
		ItemLore lore = stack.get(DataComponents.LORE);
		if (lore == null || lore.lines().isEmpty()) return OverlayData.EMPTY;
		String firstLine = lore.lines().get(0).getString();
		String secondLine = lore.lines().size() > 1 ? lore.lines().get(1).getString() : "";
		String thirdLine = lore.lines().size() > 2 ? lore.lines().get(2).getString() : "";
		LoreKey key = new LoreKey(firstLine, secondLine, thirdLine);
		OverlayData cached = OVERLAY_CACHE.get(key);
		if (cached != null) return cached;
		OverlayData parsed = new OverlayData(
			parseLoreStockCount(firstLine),
			parseLoreStackSize(firstLine, secondLine, thirdLine)
		);
		if (OVERLAY_CACHE.size() >= MAX_CACHE_ENTRIES) {
			OVERLAY_CACHE.clear();
		}
		OVERLAY_CACHE.put(key, parsed);
		return parsed;
	}

	private static long parseLoreStackSize(String firstLine, String secondLine, String thirdLine) {
		long first = parseLoreStackSize(firstLine);
		if (first > 0L) return first;
		long second = secondLine.isEmpty() ? -1L : parseLoreStackSize(secondLine);
		if (second > 0L) return second;
		if (firstLine.toLowerCase(java.util.Locale.ROOT).contains("sell")) {
			return thirdLine.isEmpty() ? -1L : parseLoreStackSize(thirdLine);
		}
		return -1L;
	}

	private static long parseLoreStackSize(String line) {
		Matcher matcher = LORE_STACK_SIZE_PATTERN.matcher(line.trim());
		if (!matcher.matches()) return -1L;
		try {
			return Long.parseLong(matcher.group(1).replace(",", ""));
		} catch (NumberFormatException ignored) {
			return -1L;
		}
	}

	private static long parseLoreStockCount(String line) {
		Matcher matcher = LORE_STOCK_PATTERN.matcher(line.trim());
		if (!matcher.matches()) return -1L;
		try {
			return Long.parseLong(matcher.group(1).replace(",", ""));
		} catch (NumberFormatException ignored) {
			return -1L;
		}
	}

	private static String format(long stackSize) {
		if (stackSize < 100_000L) return Long.toString(stackSize);
		if (stackSize < 10_000_000L) return (stackSize / 1_000L) + "K";
		return (stackSize / 1_000_000L) + "M";
	}

	private static int color(long stackSize) {
		if (stackSize < 100_000L) return 0xFFFFFF00;
		if (stackSize < 10_000_000L) return 0xFFFFFFFF;
		return 0xFF00FF80;
	}

	private static CachedLabel cachedLabel(String text, int color) {
		String key = LABEL_CACHE_VERSION + "|" + text + "|" + Integer.toHexString(color);
		CachedLabel cached = LABEL_CACHE.remove(key);
		if (cached != null) {
			LABEL_CACHE.put(key, cached);
			return cached;
		}
		CachedLabel created = buildLabel(text, color);
		if (created == null) return null;
		if (LABEL_CACHE.size() >= MAX_LABEL_CACHE_ENTRIES) {
			String eldestKey = LABEL_CACHE.keySet().iterator().next();
			CachedLabel eldest = LABEL_CACHE.remove(eldestKey);
			if (eldest != null) releaseLabel(eldest);
		}
		LABEL_CACHE.put(key, created);
		return created;
	}

	private static void renderFontOverlay(GuiGraphicsExtractor graphics, Font font, long stockCount, long stackSize, int x, int y, int drawSize, float scaleMultiplier) {
		int lineOffset = Math.max(LOGICAL_LINE_OFFSET, font.lineHeight - 2);
		int logicalY = 0;
		if (stockCount >= 0L) {
			renderFontLabel(graphics, font, format(stockCount), color(stockCount), x, y + logicalY, drawSize, scaleMultiplier);
			logicalY += lineOffset;
		}
		if (stackSize > 1L) {
			renderFontLabel(graphics, font, format(stackSize), color(stackSize), x, y + logicalY, drawSize, scaleMultiplier);
		}
	}

	private static void renderFontLabel(GuiGraphicsExtractor graphics, Font font, String text, int color, int x, int y, int drawSize, float scaleMultiplier) {
		if (text == null || text.isEmpty()) return;
		PreparedLabel label = preparedLabel(font, text, color);
		if (label == null) return;
		float textScale = Mth.clamp(drawSize / 16.0F, 0.75F, 1.0F) * scaleMultiplier;
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(textScale, textScale);
		renderPreparedLabel(graphics, font, label);
		graphics.pose().popMatrix();
	}

	private static PreparedLabel preparedLabel(Font font, String text, int color) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || font == null) return null;
		ResourceManager resourceManager = minecraft.getResourceManager();
		if (lastPreparedResourceManager != resourceManager) {
			clearPreparedLabelCache();
			lastPreparedResourceManager = resourceManager;
		}
		PreparedLabelKey key = new PreparedLabelKey(font, text, color);
		PreparedLabel cached = PREPARED_LABEL_CACHE.remove(key);
		if (cached != null) {
			PREPARED_LABEL_CACHE.put(key, cached);
			return cached;
		}
		PreparedLabel created = buildPreparedLabel(font, text, color);
		if (created == null) return null;
		if (PREPARED_LABEL_CACHE.size() >= MAX_LABEL_CACHE_ENTRIES) {
			PreparedLabelKey eldestKey = PREPARED_LABEL_CACHE.keySet().iterator().next();
			PREPARED_LABEL_CACHE.remove(eldestKey);
		}
		PREPARED_LABEL_CACHE.put(key, created);
		return created;
	}

	private static PreparedLabel buildPreparedLabel(Font font, String text, int color) {
		FormattedCharSequence sequence = Language.getInstance().getVisualOrder(FormattedText.of(text));
		Font.PreparedText preparedText = font.prepareText(sequence, 0.0F, 0.0F, color, true, false, 0);
		return new PreparedLabel(sequence, preparedText, color);
	}

	private static void renderPreparedLabel(GuiGraphicsExtractor graphics, Font font, PreparedLabel label) {
		GuiRenderState guiRenderState = guiRenderState(graphics);
		if (guiRenderState == null) return;
		ScreenRectangle scissor = currentScissor(graphics);
		Matrix3x2f pose = new Matrix3x2f(graphics.pose());
		GuiTextRenderState state = new GuiTextRenderState(
			font,
			label.text(),
			pose,
			0,
			0,
			label.color(),
			0,
			true,
			false,
			scissor
		);
		if (!setPreparedState(state, label.preparedText(), pose, scissor)) return;
		guiRenderState.addText(state);
	}

	private static GuiRenderState guiRenderState(GuiGraphicsExtractor graphics) {
		try {
			if (guiRenderStateField == null) {
				guiRenderStateField = GuiGraphicsExtractor.class.getDeclaredField("guiRenderState");
				guiRenderStateField.setAccessible(true);
			}
			return (GuiRenderState) guiRenderStateField.get(graphics);
		} catch (ReflectiveOperationException ignored) {
			return null;
		}
	}

	private static ScreenRectangle currentScissor(GuiGraphicsExtractor graphics) {
		try {
			if (scissorStackField == null) {
				scissorStackField = GuiGraphicsExtractor.class.getDeclaredField("scissorStack");
				scissorStackField.setAccessible(true);
			}
			Object scissorStack = scissorStackField.get(graphics);
			if (scissorStack == null) return null;
			if (scissorPeekMethod == null) {
				scissorPeekMethod = scissorStack.getClass().getDeclaredMethod("peek");
				scissorPeekMethod.setAccessible(true);
			}
			return (ScreenRectangle) scissorPeekMethod.invoke(scissorStack);
		} catch (ReflectiveOperationException ignored) {
			return null;
		}
	}

	private static boolean setPreparedState(GuiTextRenderState state, Font.PreparedText preparedText, Matrix3x2f pose, ScreenRectangle scissor) {
		try {
			if (preparedTextField == null) {
				preparedTextField = GuiTextRenderState.class.getDeclaredField("preparedText");
				preparedTextField.setAccessible(true);
			}
			preparedTextField.set(state, preparedText);
			if (boundsField == null) {
				boundsField = GuiTextRenderState.class.getDeclaredField("bounds");
				boundsField.setAccessible(true);
			}
			ScreenRectangle bounds = preparedText.bounds();
			if (bounds != null) {
				bounds = bounds.transformMaxBounds(pose);
				if (scissor != null) bounds = scissor.intersection(bounds);
			}
			boundsField.set(state, bounds);
			return true;
		} catch (ReflectiveOperationException ignored) {
			return false;
		}
	}

	private static CachedLabel buildLabel(String text, int color) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null) return null;
		NativeImage source = asciiGlyphSource();
		if (source == null) return null;
		int sourceCellSize = glyphCellSize(source);
		float sourceScale = Math.max(1.0F, sourceCellSize / (float) LOGICAL_FONT_CELL_SIZE);
		int shadowOffset = Math.max(1, Math.round(sourceScale));
		int textWidth = 0;
		int textHeight = sourceCellSize;
		for (int i = 0; i < text.length(); i++) {
			GlyphSlice glyph = glyphSlice(text.charAt(i), source);
			if (glyph == null) continue;
			textWidth += glyph.advance(sourceScale);
		}
		int width = textWidth + shadowOffset;
		int height = textHeight + shadowOffset;
		if (width <= 0 || height <= 0) return null;
		NativeImage image = new NativeImage(width, height, true);
		int drawX = 0;
		for (int i = 0; i < text.length(); i++) {
			GlyphSlice glyph = glyphSlice(text.charAt(i), source);
			if (glyph == null) continue;
			blitGlyph(source, glyph, image, drawX + shadowOffset, shadowOffset, shadowColor(color));
			blitGlyph(source, glyph, image, drawX, 0, color);
			drawX += glyph.advance(sourceScale);
		}
		Identifier textureId = Identifier.fromNamespaceAndPath(
			MinescapeAddon.MOD_ID,
			"dynamic/stack_count/" + nextLabelId++
		);
		DynamicTexture texture = new DynamicTexture(() -> "stack_count", image);
		minecraft.getTextureManager().register(textureId, texture);
		return new CachedLabel(textureId, texture, width, height, 1.0F / sourceScale);
	}

	private static NativeImage asciiGlyphSource() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null) return null;
		ResourceManager resourceManager = minecraft.getResourceManager();
		Identifier sourceId = OVERLAY_ASCII_FONT_BITMAP;
		if (asciiGlyphSource != null && !asciiGlyphSource.isClosed()
			&& lastAsciiResourceManager == resourceManager
			&& Objects.equals(lastAsciiSourceId, sourceId)) {
			return asciiGlyphSource;
		}
		clearLabelCache();
		if (asciiGlyphSource != null && !asciiGlyphSource.isClosed()) {
			asciiGlyphSource.close();
		}
		try (InputStream stream = resourceManager.open(sourceId)) {
			asciiGlyphSource = NativeImage.read(stream);
			lastAsciiResourceManager = resourceManager;
			lastAsciiSourceId = sourceId;
			GLYPH_SLICE_CACHE.clear();
			return asciiGlyphSource;
		} catch (IOException ignored) {
			return null;
		}
	}

	private static GlyphSlice glyphSlice(char ch, NativeImage source) {
		GlyphSlice cached = GLYPH_SLICE_CACHE.get(ch);
		if (cached != null) return cached;
		int glyphIndex = LABEL_GLYPHS.indexOf(ch);
		if (glyphIndex < 0) return null;
		int codepoint = LABEL_GLYPHS.charAt(glyphIndex);
		int cellSize = glyphCellSize(source);
		int sourceCellX = (codepoint % 16) * cellSize;
		int sourceCellY = (codepoint / 16) * cellSize;
		int maxX = -1;
		for (int y = 0; y < cellSize; y++) {
			for (int x = 0; x < cellSize; x++) {
				int pixel = source.getPixel(sourceCellX + x, sourceCellY + y);
				int alpha = (pixel >>> 24) & 0xFF;
				if (alpha == 0) continue;
				if (x > maxX) maxX = x;
			}
		}
		if (maxX < 0) return null;
		GlyphSlice slice = new GlyphSlice(
			sourceCellX,
			sourceCellY,
			maxX + 1,
			cellSize
		);
		GLYPH_SLICE_CACHE.put(ch, slice);
		return slice;
	}


	private static int glyphCellSize(NativeImage source) {
		return Math.max(1, Math.min(source.getWidth(), source.getHeight()) / 16);
	}

	private static void blitGlyph(NativeImage source, GlyphSlice glyph, NativeImage target, int targetX, int targetY, int color) {
		blitGlyph(source, glyph, target, targetX, targetY, true, color);
	}

	private static void blitGlyph(NativeImage source, GlyphSlice glyph, NativeImage target, int targetX, int targetY, boolean tint, int color) {
		for (int py = 0; py < glyph.height(); py++) {
			for (int px = 0; px < glyph.width(); px++) {
				int pixel = source.getPixel(glyph.sourceX() + px, glyph.sourceY() + py);
				int alpha = (pixel >>> 24) & 0xFF;
				if (alpha == 0) continue;
				target.setPixel(targetX + px, targetY + py, tint ? withAlpha(color, alpha) : pixel);
			}
		}
	}

	private static int withAlpha(int rgb, int alpha) {
		return ((alpha & 0xFF) << 24) | (rgb & 0x00FFFFFF);
	}

	private static void renderCachedLabel(GuiGraphicsExtractor graphics, CachedLabel label, int x, int y, float scaleMultiplier) {
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		float drawScale = label.drawScale() * scaleMultiplier;
		graphics.pose().scale(drawScale, drawScale);
		graphics.blit(
			RenderPipelines.GUI_TEXTURED,
			label.textureId(),
			0,
			0,
			0.0F,
			0.0F,
			label.width(),
			label.height(),
			label.width(),
			label.height()
		);
		graphics.pose().popMatrix();
	}

	private static int shadowColor(int color) {
		int alpha = (color >>> 24) & 0xFF;
		int shadowRgb = (color & 0x00FCFCFC) >> 2;
		return (alpha << 24) | shadowRgb;
	}

	private static void releaseLabel(CachedLabel label) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft != null) minecraft.getTextureManager().release(label.textureId());
		label.texture().close();
	}

	private static void clearLabelCache() {
		for (CachedLabel label : LABEL_CACHE.values()) {
			releaseLabel(label);
		}
		LABEL_CACHE.clear();
	}

	private static void clearPreparedLabelCache() {
		PREPARED_LABEL_CACHE.clear();
	}

	private record LoreKey(String firstLine, String secondLine, String thirdLine) {
		private LoreKey {
			firstLine = Objects.requireNonNullElse(firstLine, "");
			secondLine = Objects.requireNonNullElse(secondLine, "");
			thirdLine = Objects.requireNonNullElse(thirdLine, "");
		}
	}

	private record OverlayData(long stockCount, long stackSize) {
		private static final OverlayData EMPTY = new OverlayData(-1L, -1L);
	}

	private record CachedLabel(Identifier textureId, DynamicTexture texture, int width, int height, float drawScale) {
	}

	private record PreparedLabel(FormattedCharSequence text, Font.PreparedText preparedText, int color) {
	}

	private record PreparedLabelKey(Font font, String text, int color) {
	}

	private record GlyphSlice(int sourceX, int sourceY, int width, int height) {
		private int advance(float sourceScale) {
			return Math.max(width, Math.max(1, (int) (0.5F + width / sourceScale) + 1) * Math.max(1, Math.round(sourceScale)));
		}
	}
}
