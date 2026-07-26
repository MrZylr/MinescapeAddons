package com.zylr.client.hud.minimap;

import com.mojang.blaze3d.platform.NativeImage;
import com.zylr.MinescapeAddon;
import com.zylr.client.PerfDebug;
import com.zylr.client.clue.ClueHelper;
import com.zylr.client.clue.ClueScrollClue;
import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.StackSizeOverlay;
import com.zylr.client.hud.HudWidget;
import com.zylr.client.skills.SkillType;
import com.zylr.client.skills.Skills;
import com.zylr.player.PlayerStats;
import com.zylr.utils.util;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class MinimapWidget extends HudWidget {
	private static final int BASE_WIDTH = 182;
	private static final int BASE_HEIGHT = 166;
	private static final int MAP_SIZE = 159;
	private static final int MAP_X = 22;
	private static final int MAP_Y = 4;
	private static final int COMPASS_CENTER_X = 24;
	private static final int COMPASS_CENTER_Y = 23;
	private static final int COMPASS_CENTER_OFFSET_X = -2;
	private static final int COMPASS_CENTER_OFFSET_Y = 0;
	private static final int ORB_FRAME_WIDTH = 57;
	private static final int ORB_FRAME_HEIGHT = 34;
	private static final int ORB_CONTENT_SIZE = 26;
	private static final int ORB_HITPOINTS_X = -29;
	private static final int ORB_HITPOINTS_Y = 47;
	private static final int ORB_PRAYER_X = -29;
	private static final int ORB_PRAYER_Y = 81;
	private static final int ORB_RUN_X = -17;
	private static final int ORB_RUN_Y = 113;
	private static final int ORB_CONTENT_OFFSET_X = 27;
	private static final int ORB_CONTENT_OFFSET_Y = 4;
	private static final int ORB_ICON_OFFSET_X = 27;
	private static final int ORB_ICON_OFFSET_Y = 4;
	private static final int ORB_VALUE_OFFSET_X = 20;
	private static final int ORB_VALUE_OFFSET_Y = 17;
	private static final int ORB_VALUE_MAX_WIDTH = 24;
	private static final int COMPASS_SOURCE_SIZE = 101;
	private static final int COMPASS_CONTENT_SIZE = 51;
	private static final float COMPASS_SOURCE_CENTER = COMPASS_SOURCE_SIZE / 2.0F;
	private static final int COMPASS_SIZE = 38;
	private static final float COMPASS_RENDER_SCALE = 40.0F / COMPASS_SIZE;
	private static final int TERRAIN_UPDATE_INTERVAL_MS = 100;
	private static final int RESOURCE_PACK_CHECK_INTERVAL_MS = 1000;
	private static final int ENTITY_DOT_UPDATE_INTERVAL_MS = 250;
	private static final int WAYPOINT_UPDATE_INTERVAL_MS = 500;
	private static final long RESOURCE_COLOR_CACHE_TTL_MS = 5000L;
	private static final double CACHE_MOVE_THRESHOLD = 1.0D;
	private static final double WAYPOINT_MOVE_THRESHOLD = 8.0D;
	private static final double BLOCKS_PER_PIXEL = 1.0D;
	private static final int CLUE_ARROW_MARGIN = 18;
	private static final int CLUE_ARROW_COLOR = 0xFFFF0000;
	private static final int MAX_BLOCK_COLOR_CACHE_SIZE = 2048;
	private static final int CAVE_MODE_Y = 55;
	private static final int CAVE_MODE_BLOCKS_ABOVE_FLOOR = 6;
	private static final int CAVE_MODE_BLOCKS_BELOW_FLOOR = 16;
	private static final int CAVE_MODE_FLOOR_SEARCH_BLOCKS = 16;
	private static final int CAVE_MODE_SURFACE_DEPTH = 14;
	private static final int CAVE_MODE_SURFACE_SAMPLE_RADIUS = 12;
	private static final int CAVE_MODE_SURFACE_SAMPLE_STEP = 6;
	private static final int CAVE_MODE_SURFACE_MIN_DEEP_SAMPLES = 16;
	private static final int CAVE_MODE_OPEN_SKY_RADIUS = 2;
	private static final int CAVE_MODE_OPEN_SKY_MIN_SAMPLES = 3;
	private static final double SURFACE_ENTITY_Y_RANGE = 8.0D;
	private static final double CAVE_ENTITY_Y_RANGE = 5.0D;
	private static final int CAVE_SOLID_COLOR = 0xFF000000;
	private static final Path WAYPOINT_PATH = FabricLoader.getInstance().getGameDir()
		.resolve("xaero")
		.resolve("minimap")
		.resolve("Multiplayer_Any Address")
		.resolve("dim%0")
		.resolve("mw$default_1.txt");
	private static final String WAYPOINT_SET_SURFACE = "gui.xaero_default";
	private static final String WAYPOINT_SET_UNDERGROUND = "Underground";
	private static final CaveZone[] CAVE_MODE_ZONES = {
			// Baxtorian falls
			new CaveZone(-1479, 83, -541, -1569, 56, -665),
			// Slayer Tower
			new CaveZone(1150, 67, -900, 1306, 100, -743)
	};
	private static final Identifier FRAME = texture("resizeable_mode/minimap_and_compass_frame.png");
	private static final Identifier COMPASS_FRAME = texture("resizeable_mode/compass_frame_overlay.png");
	private static final Identifier COMPASS = texture("other/compass.png");
	private static final Identifier ORB_FRAME = texture("other/minimap_orb_frame.png");
	private static final Identifier ORB_FRAME_HOVERED = texture("other/minimap_orb_frame_hovered.png");
	private static final Identifier ORB_HITPOINTS = texture("other/minimap_orb_hitpoints.png");
	private static final Identifier ORB_HITPOINTS_ICON = texture("other/minimap_orb_hitpoints_icon.png");
	private static final Identifier ORB_PRAYER = texture("other/minimap_orb_prayer.png");
	private static final Identifier ORB_PRAYER_ICON = texture("other/minimap_orb_prayer_icon.png");
	private static final Identifier ORB_RUN = texture("other/minimap_orb_run.png");
	private static final Identifier ORB_RUN_ICON = texture("other/minimap_orb_run_icon.png");
	private static final Identifier ORB_RUN_ACTIVATED = texture("other/minimap_orb_run_activated.png");
	private static final Identifier ORB_RUN_ICON_ACTIVATED = texture("other/minimap_orb_run_icon_activated.png");
	private static final Identifier MINIMAP_TEXTURE = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "dynamic/minimap");
	private static final Field SPRITE_IMAGE_FIELD = findSpriteImageField();
	private static final Predicate<Entity> VISIBLE_ENTITY = entity -> entity != null
		&& entity.isAlive()
		&& !entity.isSpectator()
		&& shouldRenderEntityDot(entity);

	private final Map<BlockState, Integer> blockColorCache = new HashMap<>();
	private final Map<Identifier, Integer> textureColorCache = new HashMap<>();
	private final List<Waypoint> waypoints = new ArrayList<>();
	private final List<Waypoint> visibleWaypoints = new ArrayList<>();
	private final List<EntityDot> entityDots = new ArrayList<>();
	private final BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
	private final List<BlockStateModelPart> blockModelParts = new ArrayList<>();
	private NativeImage minimapImage;
	private DynamicTexture minimapTexture;
	private long lastTerrainUpdateMillis;
	private double lastTerrainX = Double.NaN;
	private double lastTerrainZ = Double.NaN;
	private int lastTerrainY = Integer.MIN_VALUE;
	private int lastTerrainDimensionHash;
	private boolean lastTerrainCaveMode;
	private Object lastResourceManager;
	private Object lastModelManager;
	private String lastResourcePackSignature = "";
	private long lastResourcePackCheckMillis;
	private long lastResourceColorRefreshMillis;
	private long lastEntityDotUpdateMillis;
	private int lastEntityDotDimensionHash;
	private long lastWaypointUpdateMillis;
	private double lastWaypointX = Double.NaN;
	private double lastWaypointZ = Double.NaN;
	private int lastWaypointDimensionHash;
	private boolean lastWaypointCaveMode;
	private int lastCaveModeDimensionHash;
	private int lastCaveModeX = Integer.MIN_VALUE;
	private int lastCaveModeY = Integer.MIN_VALUE;
	private int lastCaveModeZ = Integer.MIN_VALUE;
	private boolean lastCaveModeResult;
	private int loggedTextureSamples;
	private boolean loggedModelSampleFailure;
	private String lastLoggedCenterSample = "";
	private boolean waypointsLoaded;

	public MinimapWidget(double defaultX, double defaultY, double defaultScale) {
		super("minimapWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return BASE_WIDTH; }

	@Override
	protected int baseHeight() { return BASE_HEIGHT; }

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isMinimapEnabled();
	}

	@Override
	protected int extraLeftBounds(int screenWidth, int screenHeight) {
		int leftmostOrb = Math.min(ORB_HITPOINTS_X, Math.min(ORB_PRAYER_X, ORB_RUN_X));
		int valueLeft = leftmostOrb + ORB_VALUE_OFFSET_X - ORB_VALUE_MAX_WIDTH;
		return Math.max(0, Math.round(-Math.min(leftmostOrb, valueLeft) * (float) this.scale()));
	}

	public boolean clickRunOrb(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		int x = this.pixelX(screenWidth);
		int y = this.pixelY(screenHeight);
		int width = this.pixelWidth();
		int height = this.pixelHeight();
		return this.isOrbHovered(mouseX, mouseY, x, y, width, height, ORB_RUN_X, ORB_RUN_Y);
	}

	public boolean clickPrayerOrb(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		int x = this.pixelX(screenWidth);
		int y = this.pixelY(screenHeight);
		int width = this.pixelWidth();
		int height = this.pixelHeight();
		return this.isOrbHovered(mouseX, mouseY, x, y, width, height, ORB_PRAYER_X, ORB_PRAYER_Y);
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		if (!HudManager.getInstance().isMinimapEnabled()) return;

		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int x = this.pixelX(screenWidth);
		int y = this.pixelY(screenHeight);
		int width = this.pixelWidth();
		int height = this.pixelHeight();

		if (minecraft.player != null && minecraft.level != null) {
			long textureStart = PerfDebug.start();
			this.ensureTexture(minecraft);
			PerfDebug.record("minimap.texture", textureStart);
			long cacheStart = PerfDebug.start();
			this.refreshResourceSensitiveCaches(minecraft);
			PerfDebug.record("minimap.caches", cacheStart);
			long terrainStart = PerfDebug.start();
			this.updateTerrainIfNeeded(minecraft);
			PerfDebug.record("minimap.terrain", terrainStart);
			long mapStart = PerfDebug.start();
			this.drawMinimap(graphics, minecraft, x, y, width, height);
			PerfDebug.record("minimap.map", mapStart);
			long waypointStart = PerfDebug.start();
			this.drawWaypoints(graphics, minecraft, x, y, width, height);
			PerfDebug.record("minimap.waypoints", waypointStart);
			long clueStart = PerfDebug.start();
			this.drawClueDot(graphics, minecraft, x, y, width, height);
			this.drawClueArrow(graphics, minecraft, x, y, width, height);
			PerfDebug.record("minimap.clues", clueStart);
			long entityStart = PerfDebug.start();
			this.drawEntityDots(graphics, minecraft, x, y, width, height);
			PerfDebug.record("minimap.entities", entityStart);
			long markerStart = PerfDebug.start();
			this.drawPlayerMarker(graphics, x, y, width, height);
			PerfDebug.record("minimap.marker", markerStart);
		}

		long frameStart = PerfDebug.start();
		blitTexture(graphics, FRAME, x, y, width, height, BASE_WIDTH, BASE_HEIGHT);
		PerfDebug.record("minimap.frame", frameStart);
		long orbStart = PerfDebug.start();
		this.drawStatOrbs(graphics, minecraft, mouseX, mouseY, x, y, width, height);
		PerfDebug.record("minimap.orbs", orbStart);
		long compassStart = PerfDebug.start();
		this.drawCompass(graphics, minecraft, x, y, width, height);
		PerfDebug.record("minimap.compass", compassStart);
		long compassFrameStart = PerfDebug.start();
		blitTexture(graphics, COMPASS_FRAME, x, y, width, height, BASE_WIDTH, BASE_HEIGHT);
		PerfDebug.record("minimap.compassFrame", compassFrameStart);

		//System.out.println(isCaveMode(Minecraft.getInstance().level, Minecraft.getInstance().player));
	}

	private void drawCompass(GuiGraphicsExtractor graphics, Minecraft minecraft, int x, int y, int width, int height) {
		int compassSize = Math.max(1, scaleX(COMPASS_SIZE, width));
		int compassDrawSize = Math.max(1, Math.round(compassSize * COMPASS_SOURCE_SIZE / (float) COMPASS_CONTENT_SIZE));
		float compassCenterX = x + scaleX(COMPASS_CENTER_X + COMPASS_CENTER_OFFSET_X, width);
		float compassCenterY = y + scaleY(COMPASS_CENTER_Y + COMPASS_CENTER_OFFSET_Y, height);
		float sourceScale = compassDrawSize / (float) COMPASS_SOURCE_SIZE;
		float drawCenter = COMPASS_SOURCE_CENTER * sourceScale;
		int compassX = Math.round(compassCenterX - drawCenter);
		int compassY = Math.round(compassCenterY - drawCenter);
		Player player = minecraft.player;
		if (player == null) {
			blitTexture(graphics, COMPASS, compassX, compassY, compassDrawSize, compassDrawSize, COMPASS_SOURCE_SIZE, COMPASS_SOURCE_SIZE);
			return;
		}

		graphics.pose().pushMatrix();
		graphics.pose().rotateAbout((float) Math.toRadians(180.0F - player.getYRot()), compassCenterX, compassCenterY);
		graphics.pose().scaleAround(COMPASS_RENDER_SCALE, COMPASS_RENDER_SCALE, compassCenterX, compassCenterY);
		graphics.blit(RenderPipelines.GUI_TEXTURED, COMPASS, compassX, compassY, 0.0F, 0.0F, compassDrawSize, compassDrawSize, COMPASS_SOURCE_SIZE, COMPASS_SOURCE_SIZE, COMPASS_SOURCE_SIZE, COMPASS_SOURCE_SIZE);
		graphics.pose().popMatrix();
	}

	private void drawStatOrbs(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, int x, int y, int width, int height) {
		int hitpoints = PlayerStats.getHealth();
		int prayer = PlayerStats.getPrayer();
		Skills skills = Skills.getInstance();
		float hitpointsPercent = statPercent(hitpoints, skills.getLevel(SkillType.HITPOINTS));
		float prayerPercent = statPercent(prayer, skills.getLevel(SkillType.PRAYER));
		boolean runEnabled = HudManager.getInstance().isRunOrbSprintEnabled();
		Identifier runFill = runEnabled ? ORB_RUN_ACTIVATED : ORB_RUN;
		Identifier runIcon = runEnabled ? ORB_RUN_ICON_ACTIVATED : ORB_RUN_ICON;
		boolean allowHover = minecraft.screen != null || !minecraft.mouseHandler.isMouseGrabbed();
		int hoverMouseX = allowHover ? mouseX : Integer.MIN_VALUE;
		int hoverMouseY = allowHover ? mouseY : Integer.MIN_VALUE;
		Font orbFont = minecraft.font;
		if (orbFont == null) return;
		float minimapTextScale = Math.max(HudManager.minimumScaledTextScale(minecraft), Math.max(0.75F, (float) this.scale()));
		OrbRenderMetrics metrics = new OrbRenderMetrics(
			orbFont,
			minimapTextScale,
			width / (float) BASE_WIDTH,
			height / (float) BASE_HEIGHT,
			Math.max(1, scaleX(ORB_FRAME_WIDTH, width)),
			Math.max(1, scaleY(ORB_FRAME_HEIGHT, height)),
			scaleX(ORB_CONTENT_OFFSET_X, width),
			scaleY(ORB_CONTENT_OFFSET_Y, height),
			Math.max(1, scaleX(ORB_CONTENT_SIZE, width)),
			Math.max(1, scaleY(ORB_CONTENT_SIZE, height)),
			scaleX(ORB_ICON_OFFSET_X, width),
			scaleY(ORB_ICON_OFFSET_Y, height),
			scaleX(ORB_VALUE_OFFSET_X, width),
			scaleY(ORB_VALUE_OFFSET_Y, height)
		);
		this.drawStatOrb(graphics, hoverMouseX, hoverMouseY, x, y, metrics, ORB_HITPOINTS_X, ORB_HITPOINTS_Y, ORB_HITPOINTS, ORB_HITPOINTS_ICON, hitpointsPercent, hitpoints);
		this.drawStatOrb(graphics, hoverMouseX, hoverMouseY, x, y, metrics, ORB_PRAYER_X, ORB_PRAYER_Y, ORB_PRAYER, ORB_PRAYER_ICON, prayerPercent, prayer);
		this.drawStatOrb(graphics, hoverMouseX, hoverMouseY, x, y, metrics, ORB_RUN_X, ORB_RUN_Y, runFill, runIcon, 1.0F, 100);
	}

	private void drawStatOrb(
		GuiGraphicsExtractor graphics,
		int mouseX,
		int mouseY,
		int x,
		int y,
		OrbRenderMetrics metrics,
		int offsetX,
		int offsetY,
		Identifier fillTexture,
		Identifier iconTexture,
		float fillPercent,
		Integer value
	) {
		int frameX = x + metrics.scaleWidgetX(offsetX);
		int frameY = y + metrics.scaleWidgetY(offsetY);
		Identifier frameTexture = isInside(mouseX, mouseY, frameX, frameY, metrics.frameWidth(), metrics.frameHeight()) ? ORB_FRAME_HOVERED : ORB_FRAME;
		blitTexture(graphics, frameTexture, frameX, frameY, metrics.frameWidth(), metrics.frameHeight(), ORB_FRAME_WIDTH, ORB_FRAME_HEIGHT);

		int contentX = frameX + metrics.contentOffsetX();
		int contentY = frameY + metrics.contentOffsetY();
		int visibleHeight = Mth.clamp(Math.round(metrics.contentHeight() * fillPercent), 0, metrics.contentHeight());
		if (visibleHeight > 0) {
			int visibleY = contentY + metrics.contentHeight() - visibleHeight;
			int sourceY = ORB_CONTENT_SIZE - Math.max(1, Math.round(ORB_CONTENT_SIZE * (visibleHeight / (float) metrics.contentHeight())));
			graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				fillTexture,
				contentX,
				visibleY,
				0.0F,
				sourceY,
				metrics.contentWidth(),
				visibleHeight,
				ORB_CONTENT_SIZE,
				Math.max(1, ORB_CONTENT_SIZE - sourceY),
				ORB_CONTENT_SIZE,
				ORB_CONTENT_SIZE
			);
		}

		int iconX = frameX + metrics.iconOffsetX();
		int iconY = frameY + metrics.iconOffsetY();
		graphics.blit(RenderPipelines.GUI_TEXTURED, iconTexture, iconX, iconY, 0.0F, 0.0F, metrics.contentWidth(), metrics.contentHeight(), ORB_CONTENT_SIZE, ORB_CONTENT_SIZE, ORB_CONTENT_SIZE, ORB_CONTENT_SIZE);
		if (value != null) this.drawOrbValue(graphics, value, fillPercent, frameX, frameY, metrics);
	}

	private void drawStatOrb(
		GuiGraphicsExtractor graphics,
		int mouseX,
		int mouseY,
		int x,
		int y,
		OrbRenderMetrics metrics,
		int offsetX,
		int offsetY,
		Identifier fillTexture,
		Identifier iconTexture,
		float fillPercent
	) {
		this.drawStatOrb(graphics, mouseX, mouseY, x, y, metrics, offsetX, offsetY, fillTexture, iconTexture, fillPercent, null);
	}

	private static boolean isInside(int mouseX, int mouseY, int x, int y, int width, int height) {
		return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
	}

	private boolean isOrbHovered(double mouseX, double mouseY, int x, int y, int width, int height, int offsetX, int offsetY) {
		int frameX = x + scaleX(offsetX, width);
		int frameY = y + scaleY(offsetY, height);
		int frameWidth = Math.max(1, scaleX(ORB_FRAME_WIDTH, width));
		int frameHeight = Math.max(1, scaleY(ORB_FRAME_HEIGHT, height));
		return mouseX >= frameX && mouseX < frameX + frameWidth && mouseY >= frameY && mouseY < frameY + frameHeight;
	}

	private void drawOrbValue(GuiGraphicsExtractor graphics, int value, float percent, int frameX, int frameY, OrbRenderMetrics metrics) {
		int color = statTextColor(percent);
		String text = Integer.toString(value);
		int textWidth = metrics.orbLabelWidth(text, color);
		int textX = frameX + metrics.valueOffsetX() - textWidth;
		int textY = frameY + metrics.valueOffsetY();
		StackSizeOverlay.renderLightweightLabelUnclamped(graphics, text, color, textX, textY, metrics.textScale());
	}

	private static float statPercent(int current, int maximum) {
		if (maximum <= 0) return 0.0F;
		return Mth.clamp(current / (float) maximum, 0.0F, 1.0F);
	}

	private static int statTextColor(float percent) {
		percent = Mth.clamp(percent, 0.0F, 1.0F);
		if (percent >= 0.5F) return lerpColor(0xFFFFAA00, 0xFF00FF00, (percent - 0.5F) * 2.0F);
		return lerpColor(0xFFFF0000, 0xFFFFAA00, percent * 2.0F);
	}

	private static int lerpColor(int start, int end, float amount) {
		amount = Mth.clamp(amount, 0.0F, 1.0F);
		int red = Mth.lerpInt(amount, (start >> 16) & 0xFF, (end >> 16) & 0xFF);
		int green = Mth.lerpInt(amount, (start >> 8) & 0xFF, (end >> 8) & 0xFF);
		int blue = Mth.lerpInt(amount, start & 0xFF, end & 0xFF);
		return 0xFF000000 | (red << 16) | (green << 8) | blue;
	}

	private void ensureTexture(Minecraft minecraft) {
		if (this.minimapTexture != null && this.minimapImage != null) return;
		this.minimapImage = new NativeImage(MAP_SIZE, MAP_SIZE, true);
		this.minimapTexture = new DynamicTexture(() -> "minescapeaddon_minimap", this.minimapImage);
		minecraft.getTextureManager().register(MINIMAP_TEXTURE, this.minimapTexture);
		this.lastTerrainUpdateMillis = 0L;
		this.loadWaypoints();
	}

	private void loadWaypoints() {
		if (this.waypointsLoaded) return;
		this.waypointsLoaded = true;
		this.waypoints.clear();
		if (!Files.exists(WAYPOINT_PATH)) {
			MinescapeAddon.LOGGER.warn("Minimap waypoint file not found: {}", WAYPOINT_PATH);
			return;
		}
		try (BufferedReader reader = Files.newBufferedReader(WAYPOINT_PATH, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				Waypoint waypoint = parseWaypoint(line);
				if (waypoint != null) this.waypoints.add(waypoint);
			}
		} catch (IOException exception) {
			MinescapeAddon.LOGGER.warn("Failed to load minimap waypoints from " + WAYPOINT_PATH, exception);
		}
	}

	private static Waypoint parseWaypoint(String line) {
		if (line == null || !line.startsWith("waypoint:")) return null;
		String[] parts = line.split(":", -1);
		if (parts.length < 14) return null;
		if (Boolean.parseBoolean(parts[7])) return null;
		String initials = parts[2];
		if (initials == null || initials.isEmpty()) return null;
		try {
			int x = Integer.parseInt(parts[3]);
			int y = Integer.parseInt(parts[4]);
			int z = Integer.parseInt(parts[5]);
			int color = xaeroColor(Integer.parseInt(parts[6]));
			String set = parts[9];
			return new Waypoint(initials, x, y, z, color, set);
		} catch (NumberFormatException ignored) {
			return null;
		}
	}

	private void refreshResourceSensitiveCaches(Minecraft minecraft) {
		Object resourceManager = minecraft.getResourceManager();
		Object modelManager = minecraft.getModelManager();
		long now = System.currentTimeMillis();
		if (
			resourceManager == this.lastResourceManager
				&& modelManager == this.lastModelManager
				&& now - this.lastResourcePackCheckMillis < RESOURCE_PACK_CHECK_INTERVAL_MS
		) return;
		this.lastResourcePackCheckMillis = now;

		String packSignature = minecraft.getResourceManager().listPacks()
			.map(pack -> pack.packId())
			.reduce("", (left, right) -> left + "|" + right);
		if (
			resourceManager == this.lastResourceManager
				&& modelManager == this.lastModelManager
				&& packSignature.equals(this.lastResourcePackSignature)
				&& now - this.lastResourceColorRefreshMillis < RESOURCE_COLOR_CACHE_TTL_MS
		) return;
		this.lastResourceManager = resourceManager;
		this.lastModelManager = modelManager;
		this.lastResourcePackSignature = packSignature;
		this.lastResourceColorRefreshMillis = now;
		this.blockColorCache.clear();
		this.textureColorCache.clear();
		this.loggedTextureSamples = 0;
		this.loggedModelSampleFailure = false;
		this.lastTerrainUpdateMillis = 0L;
		this.lastTerrainX = Double.NaN;
		this.lastTerrainZ = Double.NaN;
		this.lastTerrainY = Integer.MIN_VALUE;
	}

	private void updateTerrainIfNeeded(Minecraft minecraft) {
		Player player = minecraft.player;
		Level level = minecraft.level;
		if (player == null || level == null || this.minimapTexture == null || this.minimapImage == null) return;

		long now = System.currentTimeMillis();
		int dimensionHash = level.dimension().identifier().hashCode();
		boolean dimensionChanged = dimensionHash != this.lastTerrainDimensionHash;
		int playerY = terrainBaseY(level, player);
		boolean caveMode = this.caveModeFor(level, player);
		boolean caveModeChanged = caveMode != this.lastTerrainCaveMode;
		boolean yChanged = playerY != this.lastTerrainY;
		double movedX = Double.isNaN(this.lastTerrainX) ? Double.MAX_VALUE : Math.abs(player.getX() - this.lastTerrainX);
		double movedZ = Double.isNaN(this.lastTerrainZ) ? Double.MAX_VALUE : Math.abs(player.getZ() - this.lastTerrainZ);
		boolean movedFarEnough = Math.max(movedX, movedZ) >= CACHE_MOVE_THRESHOLD;
		if (!dimensionChanged && !movedFarEnough && !yChanged && !caveModeChanged) return;
		if (!dimensionChanged && now - this.lastTerrainUpdateMillis < TERRAIN_UPDATE_INTERVAL_MS) return;

		this.lastTerrainUpdateMillis = now;
		this.lastTerrainX = player.getX();
		this.lastTerrainZ = player.getZ();
		this.lastTerrainY = playerY;
		this.lastTerrainDimensionHash = dimensionHash;
		this.lastTerrainCaveMode = caveMode;
		long start = PerfDebug.start();
		this.rebuildTerrain(minecraft, level, player);
		this.minimapTexture.upload();
		PerfDebug.record("minimap.terrainUpload", start);
	}

	private void rebuildTerrain(Minecraft minecraft, Level level, Player player) {
		int center = MAP_SIZE / 2;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		int playerY = Mth.floor(player.getY());
		int terrainBaseY = terrainBaseY(level, player);
		boolean caveMode = this.caveModeFor(level, player);

		for (int py = 0; py < MAP_SIZE; py++) {
			for (int px = 0; px < MAP_SIZE; px++) {
				int dx = px - center;
				int dz = py - center;
				double distance = Math.sqrt(dx * dx + dz * dz);
				if (distance > center) {
					this.minimapImage.setPixelABGR(px, py, 0x00000000);
					continue;
				}

				double worldOffsetX = dx * BLOCKS_PER_PIXEL;
				double worldOffsetZ = dz * BLOCKS_PER_PIXEL;
				int worldX = Mth.floor(player.getX() + worldOffsetX);
				int worldZ = Mth.floor(player.getZ() + worldOffsetZ);
				TerrainSample sample = this.sampleTerrain(level, pos, worldX, worldZ, terrainBaseY, playerY, caveMode);
				if (px == center && py == center) {
					//this.logCenterSample(level, player, sample, worldX, worldZ, terrainBaseY, caveMode);
				}
				if (sample == null) {
					this.minimapImage.setPixelABGR(px, py, argbToAbgr(0xFF000000));
					continue;
				}

				int argb = sample.solidCaveBlock()
					? CAVE_SOLID_COLOR
					: this.colorFor(minecraft, level, pos, sample.state(), sample.y(), playerY);
				this.minimapImage.setPixelABGR(px, py, argbToAbgr(argb));
			}
		}
	}

	private void logCenterSample(Level level, Player player, TerrainSample sample, int worldX, int worldZ, int terrainBaseY, boolean caveMode) {
		String message;
		if (sample == null) {
			message = "null|" + worldX + "|" + worldZ + "|" + terrainBaseY + "|" + caveMode;
		} else {
			Identifier blockId = BuiltInRegistries.BLOCK.getKey(sample.state().getBlock());
			message = blockId + "|" + sample.y() + "|" + worldX + "|" + worldZ + "|" + terrainBaseY + "|" + caveMode;
		}
		if (message.equals(this.lastLoggedCenterSample)) return;
		this.lastLoggedCenterSample = message;

		if (sample == null) {
			MinescapeAddon.LOGGER.info(
				"Minimap center sample: none at ({}, {}), terrainBaseY={}, caveMode={}, player=({}, {}, {})",
				worldX,
				worldZ,
				terrainBaseY,
				caveMode,
				Mth.floor(player.getX()),
				Mth.floor(player.getY()),
				Mth.floor(player.getZ())
			);
			return;
		}

		MinescapeAddon.LOGGER.info(
			"Minimap center sample: block={} pos=({}, {}, {}), terrainBaseY={}, caveMode={}, solidCaveBlock={}, player=({}, {}, {})",
			BuiltInRegistries.BLOCK.getKey(sample.state().getBlock()),
			worldX,
			sample.y(),
			worldZ,
			terrainBaseY,
			caveMode,
			sample.solidCaveBlock(),
			Mth.floor(player.getX()),
			Mth.floor(player.getY()),
			Mth.floor(player.getZ())
		);
	}

	private TerrainSample sampleTerrain(Level level, BlockPos.MutableBlockPos pos, int worldX, int worldZ, int terrainBaseY, int playerY, boolean caveMode) {
		int maxLevelY = level.getMinY() + level.getHeight() - 1;
		if (caveMode) return this.sampleCaveTerrain(level, pos, worldX, worldZ, terrainBaseY, maxLevelY);

		int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, worldX, worldZ) - 1;
		int fallbackStartY = playerY + CAVE_MODE_BLOCKS_ABOVE_FLOOR;
		int startY = Mth.clamp(Math.max(surfaceY, fallbackStartY), level.getMinY(), maxLevelY);
		int stopY = level.getMinY();
		for (int y = startY; y >= stopY; y--) {
			pos.set(worldX, y, worldZ);
			if (!level.isLoaded(pos)) return null;
			BlockState state = level.getBlockState(pos);
			if (state.isAir()) continue;
			if (isClearForMinimap(state)) continue;
			if (isTransparentForMinimap(state)) continue;
			return new TerrainSample(state, y, false);
		}

		return null;
	}

	private TerrainSample sampleCaveTerrain(Level level, BlockPos.MutableBlockPos pos, int worldX, int worldZ, int floorY, int maxLevelY) {
		int startY = Mth.clamp(floorY - CAVE_MODE_BLOCKS_BELOW_FLOOR, level.getMinY(), maxLevelY);
		int stopY = Mth.clamp(floorY + CAVE_MODE_BLOCKS_ABOVE_FLOOR, level.getMinY(), maxLevelY);
		TerrainSample topSample = null;
		boolean feetBlocked = false;
		boolean headBlocked = false;
		for (int y = startY; y <= stopY; y++) {
			pos.set(worldX, y, worldZ);
			if (!level.isLoaded(pos)) return null;
			BlockState state = level.getBlockState(pos);
			boolean visible = !state.isAir() && !isTransparentForMinimap(state);
			if (visible) {
				topSample = new TerrainSample(state, y, false);
			}
			if (y == floorY + 1) {
				feetBlocked = isSolidForCaveMode(state);
			} else if (y == floorY + 2) {
				headBlocked = isSolidForCaveMode(state);
			}
		}

		if (feetBlocked && headBlocked && extendsAboveHead(topSample, floorY)) {
			return new TerrainSample(Blocks.STONE.defaultBlockState(), floorY + 1, true);
		}
		return topSample;
	}

	private static boolean extendsAboveHead(TerrainSample sample, int floorY) {
		return sample != null && sample.y() > floorY + 5;
	}

	private static int terrainBaseY(Level level, Player player) {
		int maxLevelY = level.getMinY() + level.getHeight() - 1;
		int worldX = Mth.floor(player.getX());
		int worldZ = Mth.floor(player.getZ());
		int startY = Mth.clamp(Mth.floor(player.getY()) - 1, level.getMinY(), maxLevelY);
		int stopY = Math.max(level.getMinY(), startY - CAVE_MODE_FLOOR_SEARCH_BLOCKS);
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		for (int y = startY; y >= stopY; y--) {
			pos.set(worldX, y, worldZ);
			if (!level.isLoaded(pos)) break;
			BlockState state = level.getBlockState(pos);
			if (isSolidForCaveMode(state)) return y;
		}
		return startY;
	}

	private static boolean isCaveMode(Level level, Player player) {
		if (level == null || player == null) return false;
		int playerX = Mth.floor(player.getX());
		int playerY = Mth.floor(player.getY());
		int playerZ = Mth.floor(player.getZ());
		if (isInsideCaveZone(playerX, playerY, playerZ)) return true;
		if (playerY < CAVE_MODE_Y) return true;
		if (hasOpenSkyNearby(level, playerX, playerY, playerZ)) return false;
		return isDeepBelowSurface(level, playerX, playerY, playerZ);
	}

	private boolean caveModeFor(Level level, Player player) {
		if (level == null || player == null) return false;
		int dimensionHash = level.dimension().identifier().hashCode();
		int playerX = Mth.floor(player.getX());
		int playerY = Mth.floor(player.getY());
		int playerZ = Mth.floor(player.getZ());
		if (
			dimensionHash == this.lastCaveModeDimensionHash
				&& playerX == this.lastCaveModeX
				&& playerY == this.lastCaveModeY
				&& playerZ == this.lastCaveModeZ
		) return this.lastCaveModeResult;

		this.lastCaveModeDimensionHash = dimensionHash;
		this.lastCaveModeX = playerX;
		this.lastCaveModeY = playerY;
		this.lastCaveModeZ = playerZ;
		this.lastCaveModeResult = isCaveMode(level, player);
		return this.lastCaveModeResult;
	}

	private static boolean isInsideCaveZone(int x, int y, int z) {
		for (CaveZone zone : CAVE_MODE_ZONES) {
			if (zone.contains(x, y, z)) return true;
		}
		return false;
	}

	private static boolean isDeepBelowSurface(Level level, int playerX, int playerY, int playerZ) {
		int samples = 0;
		int deepSamples = 0;
		for (int dz = -CAVE_MODE_SURFACE_SAMPLE_RADIUS; dz <= CAVE_MODE_SURFACE_SAMPLE_RADIUS; dz += CAVE_MODE_SURFACE_SAMPLE_STEP) {
			for (int dx = -CAVE_MODE_SURFACE_SAMPLE_RADIUS; dx <= CAVE_MODE_SURFACE_SAMPLE_RADIUS; dx += CAVE_MODE_SURFACE_SAMPLE_STEP) {
				int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, playerX + dx, playerZ + dz) - 1;
				samples++;
				if (surfaceY - playerY >= CAVE_MODE_SURFACE_DEPTH) deepSamples++;
			}
		}
		return samples > 0 && deepSamples >= CAVE_MODE_SURFACE_MIN_DEEP_SAMPLES;
	}

	private static boolean hasOpenSkyNearby(Level level, int playerX, int playerY, int playerZ) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		int openSkySamples = 0;
		for (int dz = -CAVE_MODE_OPEN_SKY_RADIUS; dz <= CAVE_MODE_OPEN_SKY_RADIUS; dz++) {
			for (int dx = -CAVE_MODE_OPEN_SKY_RADIUS; dx <= CAVE_MODE_OPEN_SKY_RADIUS; dx++) {
				pos.set(playerX + dx, playerY, playerZ + dz);
				if (!level.isLoaded(pos)) continue;
				if (!level.canSeeSky(pos)) continue;
				openSkySamples++;
				if (openSkySamples >= CAVE_MODE_OPEN_SKY_MIN_SAMPLES) return true;
			}
		}
		return false;
	}

	private static boolean isSolidForCaveMode(BlockState state) {
		if (state.isAir() || !state.getFluidState().isEmpty() || isTransparentForMinimap(state)) return false;
		return state.blocksMotion() || state.isSolidRender();
	}

	private static boolean isTransparentForMinimap(BlockState state) {
		if (!state.getFluidState().isEmpty()) return false;
		if (isClearForMinimap(state)) return true;
		return state.getRenderShape() == RenderShape.INVISIBLE;
	}

	private static boolean isClearForMinimap(BlockState state) {
		return state.is(Blocks.BARRIER) || state.is(Blocks.LIGHT) || state.is(Blocks.STRUCTURE_VOID);
	}

	private int colorFor(Minecraft minecraft, Level level, BlockPos pos, BlockState state, int terrainY, int playerY) {
		if (state.isAir()) return 0xFF000000;
		if (state.is(Blocks.LAVA)) return 0xFFFF7A1A;
		if (state.is(Blocks.WATER)) return 0xFF2B638A;
		if (state.getFluidState().isSource()) {
			if (state.getFluidState().is(net.minecraft.tags.FluidTags.LAVA)) return 0xFFFF7A1A;
			return 0xFF2B638A;
		}
		int color = this.cachedBlockColor(minecraft, level, pos, state);
		int heightDelta = Mth.clamp(terrainY - playerY, -18, 18);
		if (heightDelta > 0) return adjustBrightness(color, 1.0F + heightDelta * 0.018F);
		if (heightDelta < 0) return adjustBrightness(color, 1.0F + heightDelta * 0.014F);
		return color;
	}

	private int cachedBlockColor(Minecraft minecraft, Level level, BlockPos pos, BlockState state) {
		Integer cached = this.blockColorCache.get(state);
		if (cached != null) return cached;
		if (this.blockColorCache.size() > MAX_BLOCK_COLOR_CACHE_SIZE) this.blockColorCache.clear();

		int color = this.sampleBlockModelColor(minecraft, level, pos, state);
		if (color == 0) color = this.paletteColor(level, pos, state);
		this.blockColorCache.put(state, color);
		return color;
	}

	private int sampleBlockModelColor(Minecraft minecraft, Level level, BlockPos pos, BlockState state) {
		if (minecraft == null) return 0;
		try {
			this.blockModelParts.clear();
			minecraft.getModelManager()
				.getBlockStateModelSet()
				.get(state)
				.collectParts(this.blockModelRenderState.scratchRandomSource(42L), this.blockModelParts);
			List<BlockStateModelPart> parts = this.blockModelParts;
			int color = this.averagePartColor(minecraft, level, pos, state, parts, Direction.UP);
			if (color != 0) return color;
			for (Direction direction : Direction.values()) {
				color = this.averagePartColor(minecraft, level, pos, state, parts, direction);
				if (color != 0) return color;
			}
			color = this.averagePartColor(minecraft, level, pos, state, parts, null);
			if (color != 0) return color;
			color = this.averageParticleColor(minecraft, level, pos, state, parts);
			if (color != 0) return color;
		} catch (RuntimeException exception) {
			if (!this.loggedModelSampleFailure) {
				this.loggedModelSampleFailure = true;
				MinescapeAddon.LOGGER.warn("Minimap block texture sampling failed; falling back to map colors.", exception);
			}
			return 0;
		}
		return 0;
	}

	private int averagePartColor(Minecraft minecraft, Level level, BlockPos pos, BlockState state, List<BlockStateModelPart> parts, Direction direction) {
		long red = 0L;
		long green = 0L;
		long blue = 0L;
		int samples = 0;
		for (BlockStateModelPart part : parts) {
			for (BakedQuad quad : part.getQuads(direction)) {
				TextureAtlasSprite sprite = quad.materialInfo().sprite();
				int color = this.averageResourceTextureColor(minecraft, sprite);
				if (color == 0) color = averageSpriteColor(sprite);
				if (color == 0) continue;
				color = this.applyQuadTint(minecraft, level, pos, state, quad, color);
				red += (color >> 16) & 0xFF;
				green += (color >> 8) & 0xFF;
				blue += color & 0xFF;
				samples++;
			}
		}
		if (samples == 0) return 0;
		return 0xFF000000 | ((int) (red / samples) << 16) | ((int) (green / samples) << 8) | (int) (blue / samples);
	}

	private int averageParticleColor(Minecraft minecraft, Level level, BlockPos pos, BlockState state, List<BlockStateModelPart> parts) {
		long red = 0L;
		long green = 0L;
		long blue = 0L;
		int samples = 0;
		for (BlockStateModelPart part : parts) {
			TextureAtlasSprite sprite = part.particleMaterial().sprite();
			int color = this.averageResourceTextureColor(minecraft, sprite);
			if (color == 0) color = averageSpriteColor(sprite);
			if (color == 0) continue;
			color = this.applyBlockTint(minecraft, level, pos, state, 0, color);
			red += (color >> 16) & 0xFF;
			green += (color >> 8) & 0xFF;
			blue += color & 0xFF;
			samples++;
		}
		if (samples == 0) return 0;
		return 0xFF000000 | ((int) (red / samples) << 16) | ((int) (green / samples) << 8) | (int) (blue / samples);
	}

	private int applyQuadTint(Minecraft minecraft, Level level, BlockPos pos, BlockState state, BakedQuad quad, int color) {
		if (!quad.materialInfo().isTinted()) return color;
		return this.applyBlockTint(minecraft, level, pos, state, quad.materialInfo().tintIndex(), color);
	}

	private int applyBlockTint(Minecraft minecraft, Level level, BlockPos pos, BlockState state, int tintIndex, int color) {
		if (minecraft == null || level == null || pos == null || state == null) return color;
		BlockTintSource tintSource = minecraft.getBlockColors().getTintSource(state, tintIndex);
		if (tintSource == null) return color;
		int tint = tintSource.colorInWorld(state, (BlockAndTintGetter) level, pos);
		int red = (((color >> 16) & 0xFF) * ((tint >> 16) & 0xFF)) / 255;
		int green = (((color >> 8) & 0xFF) * ((tint >> 8) & 0xFF)) / 255;
		int blue = ((color & 0xFF) * (tint & 0xFF)) / 255;
		return (color & 0xFF000000) | (red << 16) | (green << 8) | blue;
	}

	private int averageResourceTextureColor(Minecraft minecraft, TextureAtlasSprite sprite) {
		if (minecraft == null || sprite == null) return 0;
		Identifier texture = sprite.contents().name();
		if (texture == null) return 0;
		Integer cached = this.textureColorCache.get(texture);
		if (cached != null) return cached;

		int spriteColor = averageSpriteColor(sprite);
		if (spriteColor != 0) {
			this.textureColorCache.put(texture, spriteColor);
			return spriteColor;
		}

		Identifier resourceId = Identifier.fromNamespaceAndPath(texture.getNamespace(), "textures/" + texture.getPath() + ".png");
		int color = minecraft.getResourceManager().getResource(resourceId)
			.map(resource -> {
				try (InputStream stream = resource.open(); NativeImage image = NativeImage.read(stream)) {
					int sampledColor = averageImageColor(image);
					return sampledColor;
				} catch (IOException | RuntimeException exception) {
					return 0;
				}
			})
			.orElse(0);
		this.textureColorCache.put(texture, color);
		return color;
	}

	private static int averageImageColor(NativeImage image) {
		if (image == null || image.isClosed()) return 0;
		long red = 0L;
		long green = 0L;
		long blue = 0L;
		int samples = 0;
		int stepX = Math.max(1, image.getWidth() / 8);
		int stepY = Math.max(1, image.getHeight() / 8);
		for (int y = 0; y < image.getHeight(); y += stepY) {
			for (int x = 0; x < image.getWidth(); x += stepX) {
				int argb = image.getPixel(x, y);
				int alpha = (argb >>> 24) & 0xFF;
				if (alpha < 64) continue;
				red += (argb >> 16) & 0xFF;
				green += (argb >> 8) & 0xFF;
				blue += argb & 0xFF;
				samples++;
			}
		}
		if (samples == 0) return 0;
		return 0xFF000000 | ((int) (red / samples) << 16) | ((int) (green / samples) << 8) | (int) (blue / samples);
	}

	private static int averageSpriteColor(TextureAtlasSprite sprite) {
		if (sprite == null || SPRITE_IMAGE_FIELD == null) return 0;
		try {
			SpriteContents contents = sprite.contents();
			NativeImage image = (NativeImage) SPRITE_IMAGE_FIELD.get(contents);
			if (image == null || image.isClosed()) return 0;

			long red = 0L;
			long green = 0L;
			long blue = 0L;
			int samples = 0;
			int stepX = Math.max(1, image.getWidth() / 8);
			int stepY = Math.max(1, image.getHeight() / 8);
			for (int y = 0; y < image.getHeight(); y += stepY) {
				for (int x = 0; x < image.getWidth(); x += stepX) {
					int argb = image.getPixel(x, y);
					int alpha = (argb >>> 24) & 0xFF;
					if (alpha < 64) continue;
					red += (argb >> 16) & 0xFF;
					green += (argb >> 8) & 0xFF;
					blue += argb & 0xFF;
					samples++;
				}
			}
			if (samples == 0) return 0;
			return 0xFF000000 | ((int) (red / samples) << 16) | ((int) (green / samples) << 8) | (int) (blue / samples);
		} catch (IllegalAccessException | RuntimeException exception) {
			return 0;
		}
	}

	private int paletteColor(Level level, BlockPos pos, BlockState state) {
		if (state.is(BlockTags.GRASS_BLOCKS)) return 0xFF4E8E38;
		if (state.is(BlockTags.LEAVES)) return 0xFF3F7F32;
		if (state.is(BlockTags.LOGS)) return 0xFF6E4F2C;
		if (state.is(BlockTags.PLANKS)) return 0xFF9B7440;
		if (state.is(BlockTags.SAND)) return 0xFFCDBB74;
		if (state.is(BlockTags.SNOW)) return 0xFFE4ECF0;
		if (state.is(BlockTags.ICE)) return 0xFF9DC8E8;
		if (state.is(BlockTags.TERRACOTTA)) return 0xFF9A5C3E;
		if (state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.STONE_BRICKS)) return 0xFF777777;
		if (state.is(BlockTags.DIRT) || state.is(BlockTags.MUD)) return 0xFF795337;
		if (state.is(BlockTags.WOOL) || state.is(BlockTags.CONCRETE_POWDER)) return registryColor(state);

		MapColor mapColor = state.getMapColor(level, pos);
		if (mapColor != MapColor.NONE && mapColor != MapColor.GRASS && mapColor != MapColor.PLANT) {
			return mapColor.calculateARGBColor(MapColor.Brightness.NORMAL);
		}
		return registryColor(state);
	}

	private void drawMinimap(GuiGraphicsExtractor graphics, Minecraft minecraft, int x, int y, int width, int height) {
		int mapX = x + scaleX(MAP_X, width);
		int mapY = y + scaleY(MAP_Y, height);
		int mapSize = Math.min(scaleX(MAP_SIZE, width), scaleY(MAP_SIZE, height));
		Player player = minecraft.player;
		if (player == null || Double.isNaN(this.lastTerrainX) || Double.isNaN(this.lastTerrainZ)) {
			graphics.blit(RenderPipelines.GUI_TEXTURED, MINIMAP_TEXTURE, mapX, mapY, 0.0F, 0.0F, mapSize, mapSize, MAP_SIZE, MAP_SIZE, MAP_SIZE, MAP_SIZE);
			return;
		}

		float pixelScale = mapSize / (float) MAP_SIZE;
		float offsetX = (float) ((player.getX() - this.lastTerrainX) / BLOCKS_PER_PIXEL) * pixelScale;
		float offsetZ = (float) ((player.getZ() - this.lastTerrainZ) / BLOCKS_PER_PIXEL) * pixelScale;
		float center = mapSize / 2.0F;
		graphics.pose().pushMatrix();
		graphics.pose().translate(mapX + center, mapY + center);
		graphics.pose().rotate((float) Math.toRadians(180.0F - player.getYRot()));
		graphics.pose().translate(-center - offsetX, -center - offsetZ);
		graphics.blit(RenderPipelines.GUI_TEXTURED, MINIMAP_TEXTURE, 0, 0, 0.0F, 0.0F, mapSize, mapSize, MAP_SIZE, MAP_SIZE, MAP_SIZE, MAP_SIZE);
		graphics.pose().popMatrix();
	}

	private void drawEntityDots(GuiGraphicsExtractor graphics, Minecraft minecraft, int x, int y, int width, int height) {
		Player player = minecraft.player;
		Level level = minecraft.level;
		if (player == null || level == null) return;

		this.updateEntityDotsIfNeeded(level, player);
		for (EntityDot dot : this.entityDots) {
			this.drawWorldDot(graphics, player, dot.x(), dot.z(), dot.color(), x, y, width, height, 1);
		}
	}

	private void drawClueDot(GuiGraphicsExtractor graphics, Minecraft minecraft, int x, int y, int width, int height) {
		Player player = minecraft.player;
		if (player == null) return;
		ClueScrollClue clue = ClueHelper.activeClue(minecraft);
		if (clue == null) return;
		BlockPos pos = clue.blockPos();
		this.drawWorldDot(graphics, player, pos.getX() + 0.5D, pos.getZ() + 0.5D, CLUE_ARROW_COLOR, x, y, width, height, 2);
	}

	private void drawClueArrow(GuiGraphicsExtractor graphics, Minecraft minecraft, int x, int y, int width, int height) {
		Player player = minecraft.player;
		if (player == null) return;
		ClueScrollClue clue = ClueHelper.activeClue(minecraft);
		if (clue == null) return;
		BlockPos pos = clue.blockPos();
		double relX = (pos.getX() + 0.5D - player.getX()) / BLOCKS_PER_PIXEL;
		double relZ = (pos.getZ() + 0.5D - player.getZ()) / BLOCKS_PER_PIXEL;
		double yawRadians = Math.toRadians(player.getYRot());
		double sin = Math.sin(yawRadians);
		double cos = Math.cos(yawRadians);
		double mapX = -relX * cos - relZ * sin;
		double mapY = relX * sin - relZ * cos;
		double center = MAP_SIZE / 2.0D;
		double distance = Math.sqrt(mapX * mapX + mapY * mapY);
		if (distance <= center || distance <= 0.001D) return;

		int mapLeft = x + scaleX(MAP_X, width);
		int mapTop = y + scaleY(MAP_Y, height);
		int mapSize = Math.min(scaleX(MAP_SIZE, width), scaleY(MAP_SIZE, height));
		double edgeRadius = Math.max(1.0D, center - CLUE_ARROW_MARGIN);
		double arrowMapX = mapX / distance * edgeRadius;
		double arrowMapY = mapY / distance * edgeRadius;
		int px = mapLeft + (int) Math.round((center + arrowMapX) / MAP_SIZE * mapSize);
		int py = mapTop + (int) Math.round((center + arrowMapY) / MAP_SIZE * mapSize);
		float arrowScale = Math.max(0.75F, (float) this.scale());
		float angle = (float) Math.atan2(mapY, mapX);

		graphics.pose().pushMatrix();
		graphics.pose().translate(px, py);
		graphics.pose().rotate(angle);
		graphics.pose().scale(arrowScale, arrowScale);
		graphics.fill(-7, -1, 2, 2, CLUE_ARROW_COLOR);
		graphics.fill(2, -4, 4, 5, CLUE_ARROW_COLOR);
		graphics.fill(4, -3, 5, 4, CLUE_ARROW_COLOR);
		graphics.fill(5, -2, 6, 3, CLUE_ARROW_COLOR);
		graphics.fill(6, -1, 7, 2, CLUE_ARROW_COLOR);
		graphics.fill(7, 0, 8, 1, CLUE_ARROW_COLOR);
		graphics.pose().popMatrix();
	}

	private void updateEntityDotsIfNeeded(Level level, Player player) {
		long now = System.currentTimeMillis();
		int dimensionHash = level.dimension().identifier().hashCode();
		if (dimensionHash == this.lastEntityDotDimensionHash && now - this.lastEntityDotUpdateMillis < ENTITY_DOT_UPDATE_INTERVAL_MS) {
			return;
		}
		this.lastEntityDotUpdateMillis = now;
		this.lastEntityDotDimensionHash = dimensionHash;
		this.entityDots.clear();

		double radiusBlocks = (MAP_SIZE / 2.0D) * BLOCKS_PER_PIXEL;
		boolean caveMode = this.caveModeFor(level, player);
		double yRange = caveMode ? CAVE_ENTITY_Y_RANGE : SURFACE_ENTITY_Y_RANGE;
		AABB area = new AABB(
			player.getX() - radiusBlocks,
			player.getY() - yRange,
			player.getZ() - radiusBlocks,
			player.getX() + radiusBlocks,
			player.getY() + yRange,
			player.getZ() + radiusBlocks
		);
		List<Entity> entities = level.getEntities(player, area, VISIBLE_ENTITY);
		for (Entity entity : entities) {
			int color = util.isRealPlayer(entity) ? 0xFFFFFFFF : 0xFFFFFF00;
			this.entityDots.add(new EntityDot(entity.getX(), entity.getZ(), color));
		}
	}

	private void drawWaypoints(GuiGraphicsExtractor graphics, Minecraft minecraft, int x, int y, int width, int height) {
		Player player = minecraft.player;
		Level level = minecraft.level;
		if (player == null || level == null || this.waypoints.isEmpty()) return;
		this.updateVisibleWaypointsIfNeeded(level, player);
		for (Waypoint waypoint : this.visibleWaypoints) {
			this.drawWorldText(graphics, minecraft, player, waypoint.x(), waypoint.z(), waypoint.initials(), waypoint.color(), x, y, width, height);
		}
	}

	private void updateVisibleWaypointsIfNeeded(Level level, Player player) {
		long now = System.currentTimeMillis();
		int dimensionHash = level.dimension().identifier().hashCode();
		boolean caveMode = this.caveModeFor(level, player);
		double movedX = Double.isNaN(this.lastWaypointX) ? Double.MAX_VALUE : Math.abs(player.getX() - this.lastWaypointX);
		double movedZ = Double.isNaN(this.lastWaypointZ) ? Double.MAX_VALUE : Math.abs(player.getZ() - this.lastWaypointZ);
		boolean movedFarEnough = Math.max(movedX, movedZ) >= WAYPOINT_MOVE_THRESHOLD;
		if (
			dimensionHash == this.lastWaypointDimensionHash
				&& caveMode == this.lastWaypointCaveMode
				&& !movedFarEnough
				&& now - this.lastWaypointUpdateMillis < WAYPOINT_UPDATE_INTERVAL_MS
		) return;

		this.lastWaypointUpdateMillis = now;
		this.lastWaypointX = player.getX();
		this.lastWaypointZ = player.getZ();
		this.lastWaypointDimensionHash = dimensionHash;
		this.lastWaypointCaveMode = caveMode;
		this.visibleWaypoints.clear();

		double radiusBlocks = (MAP_SIZE / 2.0D) * BLOCKS_PER_PIXEL;
		double maxDistanceSq = radiusBlocks * radiusBlocks;
		for (Waypoint waypoint : this.waypoints) {
			if (!waypoint.visibleIn(caveMode)) continue;
			double dx = waypoint.x() - player.getX();
			double dz = waypoint.z() - player.getZ();
			if (dx * dx + dz * dz <= maxDistanceSq) {
				this.visibleWaypoints.add(waypoint);
			}
		}
	}

	private static boolean shouldRenderEntityDot(Entity entity) {
		if (entity instanceof Player) return true;
		if (util.isMob(entity) || util.isImpling(entity)) return true;
		if (entity instanceof ArmorStand armorStand) {
			return util.isArmorStandMob(armorStand) || util.isArmorstandNPC(armorStand);
		}
		return false;
	}

	private void drawWorldDot(GuiGraphicsExtractor graphics, Player player, double worldX, double worldZ, int color, int x, int y, int width, int height, int dotSize) {
		double relX = (worldX - player.getX()) / BLOCKS_PER_PIXEL;
		double relZ = (worldZ - player.getZ()) / BLOCKS_PER_PIXEL;
		double yawRadians = Math.toRadians(player.getYRot());
		double sin = Math.sin(yawRadians);
		double cos = Math.cos(yawRadians);
		double mapX = -relX * cos - relZ * sin;
		double mapY = relX * sin - relZ * cos;
		double center = MAP_SIZE / 2.0D;
		if (mapX * mapX + mapY * mapY > center * center) return;

		int mapLeft = x + scaleX(MAP_X, width);
		int mapTop = y + scaleY(MAP_Y, height);
		int mapSize = Math.min(scaleX(MAP_SIZE, width), scaleY(MAP_SIZE, height));
		int px = mapLeft + (int) Math.round((center + mapX) / MAP_SIZE * mapSize);
		int py = mapTop + (int) Math.round((center + mapY) / MAP_SIZE * mapSize);
		int scaledDot = Math.max(1, (int) Math.round(dotSize * this.scale()));
		graphics.fill(px - scaledDot, py - scaledDot, px + scaledDot + 1, py + scaledDot + 1, color);
	}

	private void drawWorldText(GuiGraphicsExtractor graphics, Minecraft minecraft, Player player, double worldX, double worldZ, String text, int color, int x, int y, int width, int height) {
		double relX = (worldX - player.getX()) / BLOCKS_PER_PIXEL;
		double relZ = (worldZ - player.getZ()) / BLOCKS_PER_PIXEL;
		double yawRadians = Math.toRadians(player.getYRot());
		double sin = Math.sin(yawRadians);
		double cos = Math.cos(yawRadians);
		double mapX = -relX * cos - relZ * sin;
		double mapY = relX * sin - relZ * cos;
		double center = MAP_SIZE / 2.0D;
		if (mapX * mapX + mapY * mapY > center * center) return;

		int mapLeft = x + scaleX(MAP_X, width);
		int mapTop = y + scaleY(MAP_Y, height);
		int mapSize = Math.min(scaleX(MAP_SIZE, width), scaleY(MAP_SIZE, height));
		int px = mapLeft + (int) Math.round((center + mapX) / MAP_SIZE * mapSize);
		int py = mapTop + (int) Math.round((center + mapY) / MAP_SIZE * mapSize);
		float textScale = Math.max(HudManager.minimumScaledTextScale(minecraft), Math.max(0.5F, (float) this.scale()));
		int textWidth = Math.round(minecraft.font.width(text) * textScale);
		int textHeight = Math.round(minecraft.font.lineHeight * textScale);
		int textX = px - textWidth / 2;
		int textY = py - textHeight / 2;
		graphics.pose().pushMatrix();
		graphics.pose().translate(textX, textY);
		graphics.pose().scale(textScale, textScale);
		graphics.text(minecraft.font, text, 0, 0, color, true);
		graphics.pose().popMatrix();
	}

	private void drawPlayerMarker(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		int mapLeft = x + scaleX(MAP_X, width);
		int mapTop = y + scaleY(MAP_Y, height);
		int mapSize = Math.min(scaleX(MAP_SIZE, width), scaleY(MAP_SIZE, height));
		int centerX = mapLeft + mapSize / 2;
		int centerY = mapTop + mapSize / 2;
		int radius = Math.max(1, (int) Math.round(this.scale()));
		graphics.fill(centerX - radius, centerY - radius, centerX + radius + 1, centerY + radius + 1, 0xFFFFFFFF);
	}

	private static int scaleX(int value, int width) {
		return Math.round(value * width / (float) BASE_WIDTH);
	}

	private static int scaleY(int value, int height) {
		return Math.round(value * height / (float) BASE_HEIGHT);
	}

	private static void blitTexture(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height, int textureWidth, int textureHeight) {
		if (width <= 0 || height <= 0 || textureWidth <= 0 || textureHeight <= 0) return;
		if (width == textureWidth && height == textureHeight) {
			graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, textureWidth, textureHeight, textureWidth, textureHeight);
			return;
		}
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale((float) width / textureWidth, (float) height / textureHeight);
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0.0F, 0.0F, textureWidth, textureHeight, textureWidth, textureHeight);
		graphics.pose().popMatrix();
	}

	private static Identifier texture(String path) {
		return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/" + path);
	}

	private static int argbToAbgr(int argb) {
		return (argb & 0xFF00FF00) | ((argb & 0x00FF0000) >> 16) | ((argb & 0x000000FF) << 16);
	}

	private static int abgrToArgb(int abgr) {
		return (abgr & 0xFF00FF00) | ((abgr & 0x00FF0000) >> 16) | ((abgr & 0x000000FF) << 16);
	}

	private static int registryColor(BlockState state) {
		Identifier id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
		String path = id == null ? state.getBlock().getClass().getSimpleName().toLowerCase() : id.getPath();
		if (path.contains("grass") || path.contains("moss") || path.contains("leaf") || path.contains("leaves")) return 0xFF4C8B35;
		if (path.contains("lava")) return 0xFFFF7A1A;
		if (path.contains("water")) return 0xFF2B638A;
		if (path.contains("sand")) return 0xFFCDBB74;
		if (path.contains("snow") || path.contains("quartz")) return 0xFFE2E0D2;
		if (path.contains("ice")) return 0xFF9DC8E8;
		if (path.contains("stone") || path.contains("cobble") || path.contains("brick")) return 0xFF777777;
		if (path.contains("log") || path.contains("wood") || path.contains("plank")) return 0xFF8C6438;
		if (path.contains("dirt") || path.contains("mud") || path.contains("podzol")) return 0xFF795337;
		if (path.contains("clay")) return 0xFF9AA0A3;
		if (path.contains("terracotta")) return 0xFF9A5C3E;
		if (path.contains("black")) return 0xFF303030;
		if (path.contains("white")) return 0xFFE0E0D8;
		if (path.contains("gray") || path.contains("grey")) return 0xFF777777;
		if (path.contains("brown")) return 0xFF745033;
		if (path.contains("orange")) return 0xFFC07034;
		if (path.contains("yellow")) return 0xFFD2B13F;
		if (path.contains("lime")) return 0xFF74A73D;
		if (path.contains("green")) return 0xFF4C8B35;
		if (path.contains("cyan")) return 0xFF3E8F92;
		if (path.contains("light_blue")) return 0xFF6FA9D6;
		if (path.contains("purple")) return 0xFF73539D;
		if (path.contains("magenta")) return 0xFFAA5AA2;
		if (path.contains("pink")) return 0xFFD68AA4;
		if (path.contains("gold")) return 0xFFE2B83E;
		if (path.contains("diamond")) return 0xFF5ACBC5;
		if (path.contains("emerald")) return 0xFF3DBB54;
		if (path.contains("redstone") || path.contains("red_")) return 0xFF9E3030;
		if (path.contains("lapis") || path.contains("blue_")) return 0xFF3C579D;

		int hash = state.toString().hashCode();
		int red = 64 + Math.floorMod(hash, 128);
		int green = 64 + Math.floorMod(hash >> 8, 128);
		int blue = 64 + Math.floorMod(hash >> 16, 128);
		return 0xFF000000 | (red << 16) | (green << 8) | blue;
	}

	private static int xaeroColor(int color) {
		return Color.WHITE.getRGB();
	}

	private static boolean shouldRejectSampledColor(int color, BlockState state) {
		if (state.is(BlockTags.GRASS_BLOCKS) || state.is(BlockTags.LEAVES) || state.is(BlockTags.MOSS_BLOCKS) || state.is(BlockTags.CROPS)) {
			return false;
		}
		int red = (color >> 16) & 0xFF;
		int green = (color >> 8) & 0xFF;
		int blue = color & 0xFF;
		boolean oliveOrGrassGreen = green > red * 1.15F && green > blue * 1.15F && red > blue * 0.75F;
		boolean almostFlatDefault = Math.abs(red - 78) <= 12 && Math.abs(green - 108) <= 18 && Math.abs(blue - 46) <= 12;
		return oliveOrGrassGreen || almostFlatDefault;
	}

	private static int blendColors(int primary, int secondary, float primaryWeight) {
		float secondaryWeight = 1.0F - primaryWeight;
		int red = Mth.clamp(Math.round(((primary >> 16) & 0xFF) * primaryWeight + ((secondary >> 16) & 0xFF) * secondaryWeight), 0, 255);
		int green = Mth.clamp(Math.round(((primary >> 8) & 0xFF) * primaryWeight + ((secondary >> 8) & 0xFF) * secondaryWeight), 0, 255);
		int blue = Mth.clamp(Math.round((primary & 0xFF) * primaryWeight + (secondary & 0xFF) * secondaryWeight), 0, 255);
		return 0xFF000000 | (red << 16) | (green << 8) | blue;
	}

	private static Field findSpriteImageField() {
		try {
			Field field = SpriteContents.class.getDeclaredField("originalImage");
			field.setAccessible(true);
			return field;
		} catch (ReflectiveOperationException | RuntimeException exception) {
			MinescapeAddon.LOGGER.warn("Minimap texture color sampling unavailable; falling back to block map colors.", exception);
			return null;
		}
	}

	private static int adjustBrightness(int argb, float factor) {
		int alpha = argb & 0xFF000000;
		int red = Mth.clamp(Math.round(((argb >> 16) & 0xFF) * factor), 0, 255);
		int green = Mth.clamp(Math.round(((argb >> 8) & 0xFF) * factor), 0, 255);
		int blue = Mth.clamp(Math.round((argb & 0xFF) * factor), 0, 255);
		return alpha | (red << 16) | (green << 8) | blue;
	}

	private record Waypoint(String initials, int x, int y, int z, int color, String set) {
		private boolean visibleIn(boolean caveMode) {
			if (caveMode) return WAYPOINT_SET_UNDERGROUND.equals(this.set);
			return WAYPOINT_SET_SURFACE.equals(this.set);
		}
	}

	private record EntityDot(double x, double z, int color) {}

	private record OrbRenderMetrics(
		Font font,
		float textScale,
		float widgetScaleX,
		float widgetScaleY,
		int frameWidth,
		int frameHeight,
		int contentOffsetX,
		int contentOffsetY,
		int contentWidth,
		int contentHeight,
		int iconOffsetX,
		int iconOffsetY,
		int valueOffsetX,
		int valueOffsetY
	) {
		private int scaleWidgetX(int value) {
			return Math.round(value * widgetScaleX);
		}

		private int scaleWidgetY(int value) {
			return Math.round(value * widgetScaleY);
		}

		private int orbLabelWidth(String text, int color) {
			if (font == null || text == null || text.isEmpty()) return 0;
			return Math.round(font.width(text) * textScale);
		}
	}

	private record CaveZone(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
		private CaveZone {
			int x1 = minX;
			int y1 = minY;
			int z1 = minZ;
			int x2 = maxX;
			int y2 = maxY;
			int z2 = maxZ;
			minX = Math.min(x1, x2);
			minY = Math.min(y1, y2);
			minZ = Math.min(z1, z2);
			maxX = Math.max(x1, x2);
			maxY = Math.max(y1, y2);
			maxZ = Math.max(z1, z2);
		}

		private boolean contains(int x, int y, int z) {
			return x >= this.minX && x <= this.maxX
				&& y >= this.minY && y <= this.maxY
				&& z >= this.minZ && z <= this.maxZ;
		}
	}

	private record TerrainSample(BlockState state, int y, boolean solidCaveBlock) {}
}
