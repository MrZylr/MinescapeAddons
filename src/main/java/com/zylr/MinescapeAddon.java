package com.zylr;

import com.zylr.mixin.FontManagerAccessor;
import com.zylr.mixin.MinecraftAccessor;
import com.zylr.client.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GlyphSource;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.font.glyphs.EffectGlyph;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MinescapeAddon implements ModInitializer {
	public static final String MOD_ID = "minescapeaddon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Identifier VANILLA_FONT_ID = Identifier.fromNamespaceAndPath(MOD_ID, "default");
	private static final Identifier VANILLA_FONT_JSON = Identifier.fromNamespaceAndPath(MOD_ID, "font/default.json");
	private static final Identifier VANILLA_FONT_INCLUDE_JSON = Identifier.fromNamespaceAndPath(MOD_ID, "font/include/default.json");
	private static final Identifier VANILLA_FONT_BITMAP = Identifier.fromNamespaceAndPath(MOD_ID, "textures/font/nonlatin_european.png");
	private static final Identifier ACCENTED_FONT_BITMAP = Identifier.fromNamespaceAndPath(MOD_ID, "textures/font/accented.png");
	private static final Identifier ASCII_FONT_BITMAP = Identifier.fromNamespaceAndPath(MOD_ID, "textures/font/ascii.png");
	private static Font vanilla;
	private static ResourceManager lastFontResourceManager;
	private static boolean loggedMissingVanillaAssets;

	// Make sure to accept the resourcepack or do: /urp

	@Override
	public void onInitialize() {
		ModItems.register();
		LOGGER.info("MinescapeAddon loaded");
	}

	public static Identifier getVanillaFontId() {
		return VANILLA_FONT_ID;
	}

	/*public static Font getVanilla() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null) {
			return vanilla;
		}

		ResourceManager resourceManager = minecraft.getResourceManager();
		if (lastFontResourceManager != resourceManager) {
			lastFontResourceManager = resourceManager;
			vanilla = null;
			loggedMissingVanillaAssets = false;
		}

		if (!hasVanillaFontAssets(minecraft)) {
			return minecraft.font;
		}

		if (vanilla == null) {
			vanilla = createVanillaFont(minecraft);
		}

		return vanilla != null ? vanilla : minecraft.font;
	}*/

	private static boolean hasVanillaFontAssets(Minecraft minecraft) {
		boolean ok = minecraft.getResourceManager().getResource(VANILLA_FONT_JSON).isPresent()
				&& minecraft.getResourceManager().getResource(VANILLA_FONT_INCLUDE_JSON).isPresent()
				&& minecraft.getResourceManager().getResource(VANILLA_FONT_BITMAP).isPresent()
				&& minecraft.getResourceManager().getResource(ACCENTED_FONT_BITMAP).isPresent()
				&& minecraft.getResourceManager().getResource(ASCII_FONT_BITMAP).isPresent();

		if (!ok && !loggedMissingVanillaAssets) {
			loggedMissingVanillaAssets = true;
			LOGGER.warn("Custom font assets missing for {}. Falling back to minecraft default font.", VANILLA_FONT_ID);
		}

		return ok;
	}

	private static Font createVanillaFont(Minecraft minecraft) {
		if (!(minecraft instanceof MinecraftAccessor minecraftAccessor)) {
			return minecraft.font;
		}

		FontManager fontManager = minecraftAccessor.minescapeaddon$getFontManager();
		if (!(fontManager instanceof FontManagerAccessor fontManagerAccessor)) {
			return minecraft.font;
		}

		Map<Identifier, FontSet> fontSetCache = new HashMap<>();
		FontSet defaultFontSet = resolveFontSet(fontManagerAccessor, FontDescription.DEFAULT);
		FontSet vanillaFontSet = fontManagerAccessor.minescapeaddon$getFontSetRaw(VANILLA_FONT_ID);
		if (defaultFontSet != null) {
			fontSetCache.put(Minecraft.DEFAULT_FONT, defaultFontSet);
		}
		if (vanillaFontSet != null) {
			fontSetCache.put(VANILLA_FONT_ID, vanillaFontSet);
		}

		return new Font(new Font.Provider() {
			@Override
			public GlyphSource glyphs(FontDescription description) {
				FontSet fontSet = resolveFontSet(fontManagerAccessor, description, fontSetCache, defaultFontSet, vanillaFontSet);
				return fontSet != null ? fontSet.source(false) : resolveFontSet(fontManagerAccessor, FontDescription.DEFAULT).source(false);
			}

			@Override
			public EffectGlyph effect() {
				FontSet fontSet = defaultFontSet != null ? defaultFontSet : vanillaFontSet;
				return fontSet != null ? fontSet.whiteGlyph() : resolveFontSet(fontManagerAccessor, FontDescription.DEFAULT).whiteGlyph();
			}
		});
	}

	private static FontSet resolveFontSet(FontManagerAccessor fontManagerAccessor, FontDescription description) {
		FontSet fontSet = null;
		if (description instanceof FontDescription.Resource resource) {
			fontSet = fontManagerAccessor.minescapeaddon$getFontSetRaw(resource.id());
		}
		if (fontSet == null) fontSet = fontManagerAccessor.minescapeaddon$getFontSetRaw(Minecraft.DEFAULT_FONT);
		if (fontSet == null) fontSet = fontManagerAccessor.minescapeaddon$getFontSetRaw(VANILLA_FONT_ID);
		return fontSet;
	}

	private static FontSet resolveFontSet(
		FontManagerAccessor fontManagerAccessor,
		FontDescription description,
		Map<Identifier, FontSet> fontSetCache,
		FontSet defaultFontSet,
		FontSet vanillaFontSet
	) {
		if (description instanceof FontDescription.Resource resource) {
			Identifier id = resource.id();
			FontSet cached = fontSetCache.get(id);
			if (cached != null) {
				return cached;
			}
			FontSet resolved = fontManagerAccessor.minescapeaddon$getFontSetRaw(id);
			if (resolved != null) {
				fontSetCache.put(id, resolved);
				return resolved;
			}
		}
		if (defaultFontSet != null) {
			return defaultFontSet;
		}
		if (vanillaFontSet != null) {
			return vanillaFontSet;
		}
		return resolveFontSet(fontManagerAccessor, description);
	}
}
