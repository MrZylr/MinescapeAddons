package com.zylr.client.hud;

import com.mojang.blaze3d.platform.NativeImage;
import com.zylr.MinescapeAddon;
import com.zylr.client.skills.SkillType;
import com.zylr.client.skills.Skills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class XpDropOrbWidget extends HudWidget {
	private static final int ORB_SIZE = 34;
	private static final int ICON_SIZE = 20;
	private static final int RING_THICKNESS = 2;
	private static final int SLOT_WIDTH = 48;
	private static final int BASE_WIDTH = SLOT_WIDTH * 3;
	private static final int BASE_HEIGHT = 78;
	private static final long DROP_LIFETIME_MILLIS = 5_000L;
	private static final long FALL_ANIMATION_MILLIS = 850L;
	private static final int FALL_DISTANCE = 30;
	private static final int MAX_DROPS = 8;
	private static final int ORB_FILL = 0xEE221F1B;
	private static final int ORB_EDGE = 0xFF0B0A08;
	private static final int RING_EMPTY = 0xFF40362B;
	private static final int ORB_TEXTURE_SIZE = 128;
	private static final Identifier ORB_TEXTURE = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "xp_drop_orb");
	private static final Map<Integer, Identifier> RING_TEXTURES = new HashMap<>();
	private static final Map<Integer, DynamicTexture> RING_DYNAMIC_TEXTURES = new HashMap<>();
	private static final Map<SkillType, Identifier> SKILL_ICONS;
	private static DynamicTexture orbDynamicTexture;

	static {
		EnumMap<SkillType, Identifier> map = new EnumMap<>(SkillType.class);
		for (SkillType skill : SkillType.values()) {
			map.put(skill, texture("skill/" + skill.getIconName() + ".png"));
		}
		SKILL_ICONS = map;
	}

	private final List<XpDrop> drops = new ArrayList<>();

	XpDropOrbWidget(double defaultX, double defaultY, double defaultScale) {
		super("xpDropOrbWidget", defaultX, defaultY, defaultScale);
	}

	public void addDrop(SkillType skill, double amount) {
		if (skill == null || amount <= 0.0D) return;
		long now = System.currentTimeMillis();
		this.removeExpiredDrops(now);
		for (XpDrop drop : this.drops) {
			if (drop.skill() == skill) {
				drop.update(now);
				return;
			}
		}
		this.drops.add(new XpDrop(skill, now));
		while (this.drops.size() > MAX_DROPS) {
			this.drops.remove(0);
		}
	}

	public void clearDrops() {
		this.drops.clear();
	}

	@Override
	protected int baseWidth() {
		return Math.max(BASE_WIDTH, Math.max(1, this.drops.size()) * SLOT_WIDTH);
	}

	@Override
	protected int baseHeight() {
		return BASE_HEIGHT;
	}

	@Override
	protected boolean shouldRenderWidget(Minecraft minecraft, boolean editMode) {
		if (!editMode) {
			this.removeExpiredDrops(System.currentTimeMillis());
		}
		return editMode || (HudManager.getInstance().isXpDropOrbsEnabled() && !this.drops.isEmpty());
	}

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isXpDropOrbsEnabled();
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		if (!HudManager.getInstance().isXpDropOrbsEnabled() && minecraft.screen == null) return;
		long now = System.currentTimeMillis();
		this.removeExpiredDrops(now);

		if (this.drops.isEmpty()) {
			if (!this.isRenderingInEditMode()) return;
			this.renderDrop(graphics, minecraft, new XpDrop(SkillType.ATTACK, now), 0, 1, now, true);
			return;
		}

		List<XpDrop> snapshot = List.copyOf(this.drops);
		int count = snapshot.size();
		for (int i = 0; i < count; i++) {
			this.renderDrop(graphics, minecraft, snapshot.get(i), i, count, now, false);
		}
	}

	private void renderDrop(GuiGraphicsExtractor graphics, Minecraft minecraft, XpDrop drop, int index, int count, long now, boolean editPreview) {
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int widgetX = this.pixelX(screenWidth);
		int widgetY = this.pixelY(screenHeight);
		int width = this.pixelWidth();
		float scale = (float) this.scale();
		int orbSize = Math.max(16, Math.round(ORB_SIZE * scale));
		int slotWidth = Math.max(orbSize + 8, Math.round(SLOT_WIDTH * scale));
		int totalWidth = slotWidth * count;
		int orbX = widgetX + (width - totalWidth) / 2 + index * slotWidth + (slotWidth - orbSize) / 2;
		int orbY = widgetY;
		double agePercent = editPreview ? 0.0D : Mth.clamp((double) (now - drop.createdMillis()) / DROP_LIFETIME_MILLIS, 0.0D, 1.0D);
		int alpha = editPreview ? 255 : Mth.clamp((int) Math.round(255.0D * (1.0D - Math.max(0.0D, agePercent - 0.8D) / 0.2D)), 0, 255);
		double levelProgress = levelProgress(drop.skill());
		Identifier ringTexture = ringTexture(minecraft, levelProgress);

		graphics.blit(RenderPipelines.GUI_TEXTURED, orbTexture(minecraft), orbX, orbY, 0.0F, 0.0F, orbSize, orbSize, ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, ringTexture, orbX, orbY, 0.0F, 0.0F, orbSize, orbSize, ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE);

		Identifier icon = SKILL_ICONS.get(drop.skill());
		if (icon != null) {
			int iconSize = Math.max(10, Math.round(ICON_SIZE * scale));
			int iconX = orbX + (orbSize - iconSize) / 2;
			int iconY = orbY + (orbSize - iconSize) / 2;
			graphics.blit(RenderPipelines.GUI_TEXTURED, icon, iconX, iconY, 0.0F, 0.0F, iconSize, iconSize, 25, 25, 25, 25);
		}

		double xpAsDouble = (int)drop.amount();
		String xpText = "+" + String.format(Locale.US, "%,.0f", xpAsDouble);
		float textScale = Math.max(0.7F, scale);
		int textWidth = HudManager.scaledTextWidth(minecraft, xpText, textScale);
		int textX = orbX + (orbSize - textWidth) / 2;
		float fall = editPreview ? 0.0F : easedFallOffset(now - drop.createdMillis(), scale);
		float textY = orbY + orbSize + Math.max(2, Math.round(3 * scale)) + fall;
		drawSmoothScaledText(graphics, minecraft, xpText, textX + 1.0F, textY + 1.0F, withAlpha(0xFF000000, alpha), textScale);
		drawSmoothScaledText(graphics, minecraft, xpText, textX, textY, withAlpha(0xFFFFFF00, alpha), textScale);
	}

	private void removeExpiredDrops(long now) {
		Iterator<XpDrop> iterator = this.drops.iterator();
		while (iterator.hasNext()) {
			if (now - iterator.next().createdMillis() >= DROP_LIFETIME_MILLIS) {
				iterator.remove();
			}
		}
	}

	private static double levelProgress(SkillType skill) {
		Skills skills = Skills.getInstance();
		double experience = skills.getExperience(skill);
		int level = skills.getLevelForExperience(experience, Skills.MAX_VIRTUAL_LEVEL);
		if (level >= Skills.MAX_VIRTUAL_LEVEL) return 1.0D;
		int currentLevelXp = skills.getExperienceAtLevel(level);
		int nextLevelXp = skills.getExperienceAtLevel(level + 1);
		if (nextLevelXp <= currentLevelXp) return 1.0D;
		return Mth.clamp((experience - currentLevelXp) / (nextLevelXp - currentLevelXp), 0.0D, 1.0D);
	}

	private static int progressColor(double progress, int alpha) {
		int red = Mth.clamp((int) Math.round(255 - progress * 95), 0, 255);
		int green = Mth.clamp((int) Math.round(90 + progress * 165), 0, 255);
		int blue = Mth.clamp((int) Math.round(35 + progress * 45), 0, 255);
		return HudManager.rgba(alpha, red, green, blue);
	}

	private static float easedFallOffset(long ageMillis, float scale) {
		double progress = Mth.clamp((double) ageMillis / FALL_ANIMATION_MILLIS, 0.0D, 1.0D);
		double eased = 1.0D - Math.pow(1.0D - progress, 3.0D);
		return (float) (eased * Math.max(16.0F, FALL_DISTANCE * scale));
	}

	private static void drawSmoothScaledText(GuiGraphicsExtractor graphics, Minecraft minecraft, String text, float x, float y, int color, float scale) {
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(scale, scale);
		graphics.text(HudManager.resolveHudTextFont(minecraft, scale), text, 0, 0, color, false);
		graphics.pose().popMatrix();
	}

	private static int withAlpha(int color, int alpha) {
		return (Mth.clamp(alpha, 0, 255) << 24) | (color & 0x00FFFFFF);
	}

	private static Identifier orbTexture(Minecraft minecraft) {
		if (orbDynamicTexture != null) return ORB_TEXTURE;
		NativeImage image = new NativeImage(ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE, true);
		float inset = ORB_TEXTURE_SIZE / (float) ORB_SIZE;
		writeCircle(image, 0.0F, 0.0F, ORB_TEXTURE_SIZE, ORB_EDGE);
		writeCircle(image, inset, inset, ORB_TEXTURE_SIZE - inset * 2.0F, ORB_FILL);
		orbDynamicTexture = new DynamicTexture(() -> "minescapeaddon_xp_drop_orb", image);
		minecraft.getTextureManager().register(ORB_TEXTURE, orbDynamicTexture);
		return ORB_TEXTURE;
	}

	private static Identifier ringTexture(Minecraft minecraft, double progress) {
		int bucket = Mth.clamp((int) Math.round(progress * 100.0D), 0, 100);
		Identifier cached = RING_TEXTURES.get(bucket);
		if (cached != null) return cached;

		double bucketProgress = bucket / 100.0D;
		Identifier identifier = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "xp_drop_ring_" + bucket);
		NativeImage image = new NativeImage(ORB_TEXTURE_SIZE, ORB_TEXTURE_SIZE, true);
		writeProgressRing(image, bucketProgress, progressColor(bucketProgress, 255), RING_EMPTY);
		DynamicTexture texture = new DynamicTexture(() -> "minescapeaddon_xp_drop_ring_" + bucket, image);
		minecraft.getTextureManager().register(identifier, texture);
		RING_TEXTURES.put(bucket, identifier);
		RING_DYNAMIC_TEXTURES.put(bucket, texture);
		return identifier;
	}

	private static void writeCircle(NativeImage image, float x, float y, float size, int color) {
		float radius = size / 2.0F;
		float centerX = x + radius;
		float centerY = y + radius;
		int minX = Math.max(0, (int) Math.floor(x));
		int minY = Math.max(0, (int) Math.floor(y));
		int maxX = Math.min(image.getWidth(), (int) Math.ceil(x + size));
		int maxY = Math.min(image.getHeight(), (int) Math.ceil(y + size));
		for (int py = minY; py < maxY; py++) {
			for (int px = minX; px < maxX; px++) {
				double distance = Math.hypot(px + 0.5D - centerX, py + 0.5D - centerY);
				double coverage = Mth.clamp(radius + 0.5D - distance, 0.0D, 1.0D);
				writePixel(image, px, py, color, coverage);
			}
		}
	}

	private static void writeProgressRing(NativeImage image, double progress, int progressColor, int emptyColor) {
		float radius = ORB_TEXTURE_SIZE / 2.0F;
		float centerX = radius;
		float centerY = radius;
		float ringThickness = Math.max(1.0F, RING_THICKNESS * ORB_TEXTURE_SIZE / (float) ORB_SIZE);
		float ringCenterRadius = radius - ringThickness / 2.0F;
		double clampedProgress = Mth.clamp(progress, 0.0D, 1.0D);
		for (int py = 0; py < ORB_TEXTURE_SIZE; py++) {
			for (int px = 0; px < ORB_TEXTURE_SIZE; px++) {
				double dx = px + 0.5D - centerX;
				double dy = py + 0.5D - centerY;
				double distance = Math.hypot(dx, dy);
				double radialCoverage = Mth.clamp(ringThickness / 2.0D + 0.5D - Math.abs(distance - ringCenterRadius), 0.0D, 1.0D);
				if (radialCoverage <= 0.0D) continue;
				double angle = Math.atan2(dy, dx) + Math.PI / 2.0D;
				if (angle < 0.0D) angle += Math.PI * 2.0D;
				double angleProgress = angle / (Math.PI * 2.0D);
				writePixel(image, px, py, angleProgress <= clampedProgress ? progressColor : emptyColor, radialCoverage);
			}
		}
	}

	private static void writePixel(NativeImage image, int x, int y, int color, double coverage) {
		int alpha = (color >>> 24) & 0xFF;
		int coveredAlpha = Mth.clamp((int) Math.round(alpha * coverage), 0, 255);
		if (coveredAlpha <= 2) return;
		image.setPixelABGR(x, y, argbToAbgr((coveredAlpha << 24) | (color & 0x00FFFFFF)));
	}

	private static int argbToAbgr(int argb) {
		return (argb & 0xFF00FF00) | ((argb & 0x00FF0000) >> 16) | ((argb & 0x000000FF) << 16);
	}

	private static Identifier texture(String path) {
		return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/" + path);
	}

	private static final class XpDrop {
		private final SkillType skill;
		private double amount;
		private long createdMillis;

		private XpDrop(SkillType skill, long createdMillis) {
			this.skill = skill;
			this.amount = Skills.getInstance().getGainedXp(skill);
			this.createdMillis = createdMillis;
		}

		private SkillType skill() {
			return this.skill;
		}

		private double amount() {
			return this.amount;
		}

		private long createdMillis() {
			return this.createdMillis;
		}

		private void update(long now) {
			this.amount = Skills.getInstance().getGainedXp(skill);
			this.createdMillis = now;
		}
	}
}
