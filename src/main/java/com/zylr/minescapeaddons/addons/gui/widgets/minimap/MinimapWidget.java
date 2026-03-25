package com.zylr.minescapeaddons.addons.gui.widgets.minimap;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.WidgetType;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MinimapWidget extends Widget {
    private static final ResourceLocation FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_frame.png");
    private static final ResourceLocation COMPASS_FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/compass_frame.png");
    private static final ResourceLocation CLASSIC_FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/classic_minimap_frame.png");
    private static final ResourceLocation COMPASS = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/compass.png");
    private static final ResourceLocation CLASSIC_FIXED_FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/classic_fixed_minimap_frame.png");


    private int minimapSize;
    private float zoomLevel;
    private List<Waypoint> waypoints;
    private long lastWaypointLoad = 0;
    private static final long WAYPOINT_RELOAD_INTERVAL = 5000;

    // Texture-based rendering for performance
    private NativeImage minimapImage;
    private DynamicTexture minimapTexture;
    private ResourceLocation minimapTextureLocation;
    private boolean textureInitialized = false;

    // Async terrain loading - NO WORK ON RENDER THREAD
    private int[][] terrainCache;
    private int[][] backBuffer; // Double buffering to prevent tearing
    private double lastCacheX = Double.MAX_VALUE;
    private double lastCacheZ = Double.MAX_VALUE;
    private long lastTerrainUpdate = 0;
    private static final long TERRAIN_UPDATE_INTERVAL = 250; // Update every 250ms instead of 100ms
    private static final int CACHE_UPDATE_THRESHOLD = 10; // Update after moving 10 blocks instead of 5
    private boolean needsTextureUpdate = false;

    // Waypoint type to display
    public WaypointType waypointType;

    // Cave mode settings
    private static final int CAVE_MODE_Y_THRESHOLD = 60;
    private boolean isCaveMode = false;

    // Async update thread
    private static ExecutorService updateExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "MinimapUpdate");
        t.setDaemon(true);
        return t;
    });
    private Future<?> currentUpdate = null;
    private volatile boolean updateReady = false;

    // Texture color caches - like Xaero's Minimap
    private static final Map<String, Integer> textureColours = new HashMap<>();
    private static final int MAX_CACHE_SIZE = 1000;

    // Thread-safe RandomSource for background texture sampling
    private static final RandomSource BACKGROUND_RANDOM = RandomSource.create();

    // Track if we need to clear caches on next update (for resource pack loading)
    private static boolean needsCacheClear = false;

    // New: optional rotate minimap flag (defaults to false)
    private boolean rotateMinimap;

    public MinimapWidget(int x, int y) {
        super();
        this.type = WidgetType.MINIMAP;

        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;
        this.setupConfig();

        this.minimapSize = 159;
        this.widgetWidth = 216;
        this.widgetHeight = 166;
        this.zoomLevel = Float.parseFloat(config.getProperty("zoomLevel", "1.0"));

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0.85")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0.05")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));

        // Load rotate option from config (new)
        this.rotateMinimap = Config.getMinimapRotation();

        this.waypoints = new ArrayList<>();
        this.terrainCache = new int[minimapSize][minimapSize];
        this.backBuffer = new int[minimapSize][minimapSize];

        textureColours.clear();
        needsCacheClear = true;

        this.waypointType = WaypointType.XAERO_DEFAULT;
        loadWaypoints();
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode()) {
            this.renderFixed(gui);
        } else {
            this.renderResizable(gui);
        }
    }

    public void renderFixed(GuiGraphics gui) {
        if (mc.player == null || mc.level == null) return;
        scale = 1.0f; // Fixed mode ignores scale config

        // Lazy-initialize texture on first render when Minecraft is fully loaded
        if (!textureInitialized) {
            initializeTexture();
            if (!textureInitialized) return; // Failed to initialize, skip rendering
        }

        this.anchorX = 603;
        this.anchorY = 0;
        this.widgetWidth = 216;
        this.widgetHeight = 166;

        if (this.children.isEmpty()) {
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getHitpointsOrb());
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getPrayerPointsOrb());
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getRunEnergyOrb());
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getXpOrb());
        }

        super.render(gui);

        gui.pose().pushPose();
        gui.pose().translate(0, 0, 150);

        this.fixAnchors();

        // Reload waypoints periodically
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastWaypointLoad > WAYPOINT_RELOAD_INTERVAL) {
            loadWaypoints();
            lastWaypointLoad = currentTime;
        }

        // Check for cave mode based on player Y position
        double playerY = mc.player.getY();
        boolean wasInCaveMode = isCaveMode;
        isCaveMode = playerY < CAVE_MODE_Y_THRESHOLD;

        // Automatically switch waypoint type when entering/exiting caves
        if (isCaveMode && !wasInCaveMode) {
            waypointType = WaypointType.UNDERGROUND;
            System.out.println("Cave mode enabled - switched to UNDERGROUND waypoints");
        } else if (!isCaveMode && wasInCaveMode) {
            waypointType = WaypointType.XAERO_DEFAULT;
            System.out.println("Cave mode disabled - switched to DEFAULT waypoints");
        }

        // Render the circular minimap
        renderMinimap(gui);

        // Render Frame
        // Render the compass on top of the frame
        int minimapLeft = this.getLeftSide() + (this.widgetWidth - this.minimapSize) - 48;
        int minimapTop = this.getTop() + (this.widgetHeight - this.minimapSize) - 4;
        this.renderCompass(gui, mc.player.getYRot(), minimapLeft, minimapTop);
        gui.blit(CLASSIC_FIXED_FRAME, mc.getWindow().getGuiScaledWidth()-250, 0, 0, 0, 250, 169, 250, 169);

        this.renderChildren(gui);

        gui.pose().popPose();
    }

    public void renderResizable(GuiGraphics gui) {
        if (mc.player == null || mc.level == null) return;
        if (!Config.getMinimap()) return;

        // Lazy-initialize texture on first render when Minecraft is fully loaded
        if (!textureInitialized) {
            initializeTexture();
            if (!textureInitialized) return; // Failed to initialize, skip rendering
        }

        if (this.children.isEmpty()) {
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getHitpointsOrb());
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getPrayerPointsOrb());
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getRunEnergyOrb());
            this.children.add(MinescapeAddons.getInstance().resizableClassicChildren.getXpOrb());
        }

        super.render(gui);

        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.fixAnchors();

        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, this.scale);
        gui.pose().translate(0, 0, 150);

        // Reload waypoints periodically
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastWaypointLoad > WAYPOINT_RELOAD_INTERVAL) {
            loadWaypoints();
            lastWaypointLoad = currentTime;
        }

        // Check for cave mode based on player Y position
        double playerY = mc.player.getY();
        boolean wasInCaveMode = isCaveMode;
        isCaveMode = playerY < CAVE_MODE_Y_THRESHOLD;

        // Automatically switch waypoint type when entering/exiting caves
        if (isCaveMode && !wasInCaveMode) {
            waypointType = WaypointType.UNDERGROUND;
            System.out.println("Cave mode enabled - switched to UNDERGROUND waypoints");
        } else if (!isCaveMode && wasInCaveMode) {
            waypointType = WaypointType.XAERO_DEFAULT;
            System.out.println("Cave mode disabled - switched to DEFAULT waypoints");
        }

        // Render the circular minimap
        renderMinimap(gui);

        // Render Frame
        if (!Config.getFixedMode()) {
            int frameMargin = Config.getClassicMinimapFrame() ? 12 : 2;
            int minimapLeft = this.getLeftSide() + (this.widgetWidth - this.minimapSize) - frameMargin;
            int minimapTop = this.getTop() + (this.widgetHeight - this.minimapSize) - 2;
            //this.renderCompass(gui, mc.player.getYRot(), minimapLeft, minimapTop);

            // Draw frame on top
            int frameBuffer = 35;
            ResourceLocation frameToUse = Config.getClassicMinimapFrame() ? CLASSIC_FRAME : FRAME;

            if (Config.getClassicMinimapFrame()) {
                // Classic frame has a different aspect ratio, so adjust height to prevent distortion
                frameBuffer = 25;
            }

            // Render Map Frame
            gui.blit(frameToUse, this.getLeftSide() + frameBuffer, this.getTop(), 0, 0, this.widgetWidth - frameBuffer, this.widgetHeight - 1, this.widgetWidth - frameBuffer, this.widgetHeight - 1);

            // Render the compass on top of the frame
            this.renderCompass(gui, mc.player.getYRot(), minimapLeft, minimapTop);

            // Render Compass Frame
            gui.blit(COMPASS_FRAME, this.getLeftSide() + frameBuffer, this.getTop(), 0, 0, 43, 43, 43, 43);

            gui.pose().translate(0, 0, 10);
            this.renderChildren(gui);
        } else {
            // Render the compass on top of the frame
            int minimapLeft = this.getLeftSide() + (this.widgetWidth - this.minimapSize) - 48;
            int minimapTop = this.getTop() + (this.widgetHeight - this.minimapSize) - 4;
            this.renderCompass(gui, mc.player.getYRot(), minimapLeft, minimapTop);
            gui.blit(CLASSIC_FIXED_FRAME, mc.getWindow().getGuiScaledWidth()-250, 0, 0, 0, 250, 169, 250, 169);
        }

        gui.pose().popPose();
    }

    private void initializeTexture() {
        try {
            if (mc.getTextureManager() == null) {
                return; // TextureManager not ready yet
            }

            this.minimapImage = new NativeImage(minimapSize, minimapSize, true);
            this.minimapTexture = new DynamicTexture(minimapImage);
            this.minimapTextureLocation = mc.getTextureManager().register("minimap_dynamic", minimapTexture);
            this.textureInitialized = true;
            System.out.println("MinimapWidget: Texture initialized successfully");
        } catch (Exception e) {
            System.err.println("MinimapWidget: Failed to initialize texture: " + e.getMessage());
            this.textureInitialized = false;
        }
    }

    private void renderMinimap(GuiGraphics gui) {
        // Right-align the minimap within the widget area with a margin from right depending on frame choice
        int frameMargin = Config.getClassicMinimapFrame() ? 15 : 2;
        int minimapLeft = this.getLeftSide() + (this.widgetWidth - this.minimapSize) - frameMargin;
        // Bottom-align the minimap within the 166-pixel tall widget
        int minimapTop = this.getTop() + (this.widgetHeight - this.minimapSize)-2;
        if (Config.getFixedMode()) {
            minimapLeft = mc.getWindow().getGuiScaledWidth() - this.widgetWidth +  15;
            minimapTop = this.getTop() + (this.widgetHeight - this.minimapSize) - 2;
        }

        int centerX = minimapLeft + this.minimapSize / 2;
        int centerY = minimapTop + this.minimapSize / 2;
        int radius = this.minimapSize / 2;

        Player player = mc.player;
        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();
        float playerYaw = player.getYRot();

        updateTerrainCacheIfNeeded(playerX, playerY, playerZ, playerYaw);

        if (needsTextureUpdate) {
            minimapTexture.upload();
            needsTextureUpdate = false;
        }

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        this.rotateMinimap = Config.getMinimapRotation();

        // If rotation is enabled, draw map and dynamic items in a rotated pose so the player faces up.
        if (rotateMinimap) {
            gui.pose().pushPose();
            // Translate to center, rotate, then draw map centered at origin
            gui.pose().translate(centerX, centerY, 0);
            gui.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(-playerYaw - 180));

            // Draw minimap texture centered at origin
            gui.blit(minimapTextureLocation,
                    -radius, -radius,
                    0, 0,
                    minimapSize, minimapSize,
                    minimapSize, minimapSize);

            // Render waypoints/entities in rotated map-space (coordinates relative to map center)
            renderWaypoints(gui, centerX, centerY, radius, playerX, playerZ, playerYaw, true);

            gui.pose().pushPose();
            gui.pose().translate(0, 0, 100);
            renderEntitiesOnMinimap(gui, centerX, centerY, radius, playerX, playerY, playerZ, Math.max(1, (int)zoomLevel), true);
            gui.pose().popPose();

            gui.pose().popPose();
        } else {
            // Non-rotated (north-up) drawing - same as before
            gui.blit(minimapTextureLocation,
                    minimapLeft, minimapTop,
                    0, 0,
                    minimapSize, minimapSize,
                    minimapSize, minimapSize);

            renderWaypoints(gui, centerX, centerY, radius, playerX, playerZ, playerYaw, false);

            gui.pose().pushPose();
            gui.pose().translate(0, 0, 100);
            renderEntitiesOnMinimap(gui, centerX, centerY, radius, playerX, playerY, playerZ, Math.max(1, (int)zoomLevel), false);
            gui.pose().popPose();
        }

        //System.out.println("Minimap Left: " + minimapLeft + " Top: " + minimapTop);

        if (Config.getMapCoords()) {
            Font font = mc.font;
            if (this.scale < 1.0f) {
                font = MinescapeAddons.getInstance().getVanillaFont();
            }
            String coordText = String.format("X: %d Z: %d", (int) playerX, (int) playerZ);
            if (Config.getFixedMode()) {
                gui.drawString(font, coordText, centerX - mc.font.width(coordText) / 2,
                        minimapTop + minimapSize - 2, 0xFFFFFFFF);
            } else {
                gui.drawString(font, coordText, centerX - mc.font.width(coordText) / 2,
                        minimapTop + minimapSize + 2, 0xFFFFFFFF);
            }
        }
    }

    private void renderCompass(GuiGraphics gui, float playerYaw, int minimapLeft, int minimapTop) {
        int compassRadius = 21;
        int minimapRadius = minimapSize / 2;
        int horizontalOffset = -7;
        int compassVerticalOffset = 8;

        double angle45 = Math.toRadians(45);
        int offsetX = (int)((minimapRadius + compassRadius) * Math.cos(angle45));
        int offsetY = (int)((minimapRadius + compassRadius) * Math.sin(angle45));

        int compassCenterX = (minimapLeft + minimapRadius) - offsetX + horizontalOffset;
        int compassCenterY = (minimapTop + minimapRadius) - offsetY + compassVerticalOffset;

        int compassSize = compassRadius * 2;

        // Negate player yaw to make north point upward
        blitRotatedCircular(gui, COMPASS, compassCenterX, compassCenterY, compassSize, compassSize, -playerYaw + 180, 0);
    }

    private void renderChildren(GuiGraphics gui) {
        for (IWidget child : this.children) {
            child.render(gui);
        }
    }

    private void blitRotatedCircular(GuiGraphics gui, ResourceLocation texture, int centerX, int centerY, int width, int height, float angleDegrees, float zOffset) {
        gui.pose().pushPose();
        gui.pose().translate(centerX, centerY, zOffset);
        gui.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(angleDegrees));

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // Draw centered at origin with circular mask
        int radius = width / 2;

        // Draw compass rotating
        gui.blit(texture,
                -width / 2, -height / 2,
                0, 0,
                width, height,
                width, height);

        gui.pose().popPose();
    }

    private void updateTerrainCacheIfNeeded(double playerX, double playerY, double playerZ, float playerYaw) {
        long currentTime = System.currentTimeMillis();
        double distanceMoved = Math.sqrt(Math.pow(playerX - lastCacheX, 2) + Math.pow(playerZ - lastCacheZ, 2));

        // Check if we need an update
        boolean needsUpdate = (distanceMoved > CACHE_UPDATE_THRESHOLD ||
                currentTime - lastTerrainUpdate > TERRAIN_UPDATE_INTERVAL ||
                lastCacheX == Double.MAX_VALUE);

        // Check if previous update completed and has data ready
        if (updateReady) {
            // Data is ready, just signal texture upload needed
            updateReady = false;
            needsTextureUpdate = true;
        }

        if (needsUpdate && (currentUpdate == null || currentUpdate.isDone())) {
            // Start new async update
            final double finalX = playerX;
            final double finalY = playerY;
            final double finalZ = playerZ;
            lastCacheX = playerX;
            lastCacheZ = playerZ;
            lastTerrainUpdate = currentTime;

            currentUpdate = updateExecutor.submit(() -> updateTerrainCacheAsync(finalX, finalY, finalZ));
        }
    }

    private void updateTerrainCacheAsync(double playerX, double playerY, double playerZ) {
        try {
            // Clear caches if flagged (happens after world join to ensure resource packs are loaded)
            if (needsCacheClear) {
                textureColours.clear();
                needsCacheClear = false;
                System.out.println("MinimapWidget: Cleared caches before first terrain update (resource pack should be loaded)");
            }

            int radius = minimapSize / 2;
            int blocksPerPixel = Math.max(1, (int)zoomLevel);
            int radiusSquared = radius * radius;

            // Update entire backbuffer on background thread
            for (int y = 0; y < minimapSize; y++) {
                for (int x = 0; x < minimapSize; x++) {
                    int dx = x - radius;
                    int dy = y - radius;

                    int worldX = (int)(playerX + dx * blocksPerPixel);
                    int worldZ = (int)(playerZ + dy * blocksPerPixel);

                    backBuffer[x][y] = getTerrainColorFast(worldX, worldZ, (int)playerY);
                }
            }

            // NOW update the texture pixels on background thread too
            for (int y = 0; y < minimapSize; y++) {
                int dy = y - radius;
                int dySquared = dy * dy;

                // Calculate x range for this row that's within the circle
                int maxDx = (int) Math.sqrt(radiusSquared - dySquared);
                int xStart = Math.max(0, radius - maxDx);
                int xEnd = Math.min(minimapSize, radius + maxDx + 1);

                // Clear pixels outside circle on left
                for (int x = 0; x < xStart; x++) {
                    minimapImage.setPixelRGBA(x, y, 0);
                }

                // Copy terrain data for pixels inside circle
                for (int x = xStart; x < xEnd; x++) {
                    int argb = backBuffer[x][y];
                    if (argb == 0) argb = 0x80404040;

                    // NativeImage.setPixelRGBA expects ABGR format
                    // Convert ARGB (0xAARRGGBB) to ABGR (0xAABBGGRR) by swapping R and B
                    int a = (argb >> 24) & 0xFF;
                    int r = (argb >> 16) & 0xFF;
                    int g = (argb >> 8) & 0xFF;
                    int b = argb & 0xFF;

                    // Pack as ABGR: alpha stays at top, swap red and blue positions
                    int abgr = (a << 24) | (b << 16) | (g << 8) | r;

                    minimapImage.setPixelRGBA(x, y, abgr);
                }

                // Clear pixels outside circle on right
                for (int x = xEnd; x < minimapSize; x++) {
                    minimapImage.setPixelRGBA(x, y, 0);
                }
            }

            updateReady = true;
        } catch (Exception e) {
            System.err.println("Error updating minimap terrain: " + e.getMessage());
        }
    }

    private int getTerrainColorFast(int worldX, int worldZ, int playerY) {
        try {
            ChunkPos chunkPos = new ChunkPos(worldX >> 4, worldZ >> 4);

            if (mc.level == null || !mc.level.hasChunk(chunkPos.x, chunkPos.z)) {
                return 0x80404040; // Semi-transparent gray
            }

            LevelChunk chunk = mc.level.getChunk(chunkPos.x, chunkPos.z);
            int localX = worldX & 15;
            int localZ = worldZ & 15;

            int height;
            boolean isInCaveMode = playerY < CAVE_MODE_Y_THRESHOLD;

            if (isInCaveMode) {
                // In cave mode: show blocks at player's level (cave floor/ceiling)
                // Start at player Y and search for the topmost solid block within a small range
                int startY = (int)playerY + 4; // Look a bit above player
                int endY = (int)playerY - 10;   // And below

                BlockPos.MutableBlockPos scanPos = new BlockPos.MutableBlockPos(worldX, startY, worldZ);
                height = endY; // Default to lowest point

                // Scan from above player to below, find the highest solid block
                for (int y = startY; y >= endY; y--) {
                    scanPos.setY(y);
                    BlockState checkState = chunk.getBlockState(scanPos);

                    if (!checkState.isAir()) {
                        // Found a solid block - this is our cave ceiling/floor
                        height = y;
                        break;
                    }
                }

                // If no solid block found in range, look for any block at player level
                if (height == endY) {
                    scanPos.setY((int)playerY);
                    BlockState playerLevelState = chunk.getBlockState(scanPos);
                    if (!playerLevelState.isAir()) {
                        height = (int)playerY;
                    } else {
                        // Show air as dark gray in caves
                        return 0xFF202020;
                    }
                }
            } else {
                // Surface mode: use normal heightmap
                height = chunk.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, localX, localZ);
            }

            if (height <= mc.level.getMinBuildHeight()) {
                return 0x80404040;
            }

            // Sample the actual block at the determined height
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(worldX, height, worldZ);
            BlockState blockState = chunk.getBlockState(mutablePos);

            // Skip through transparent decorations (NOT solid blocks) to find the actual visible surface
            int maxDepth = 100;
            while (maxDepth > 0 && mutablePos.getY() > mc.level.getMinBuildHeight()) {
                // Only skip actual decorations, NOT solid blocks
                String blockName = blockState.getBlock().toString();
                boolean isDecoration = blockState.isAir() ||
                        blockName.contains("tall_grass") ||
                        blockName.contains("short_grass") ||
                        blockName.contains("fern") ||
                        blockName.contains("flower") ||
                        blockName.contains("sapling") ||
                        blockName.contains("dead_bush") ||
                        blockName.contains("barrier");

                if (isDecoration) {
                    // Skip decorations
                    mutablePos.move(0, -1, 0);
                    blockState = chunk.getBlockState(mutablePos);
                    maxDepth--;
                } else {
                    // Found a solid block (including grass_block, dirt, stone, etc.)
                    break;
                }
            }

            BlockPos pos = mutablePos.immutable();

            // In cave mode, render solid wall blocks (stone, deepslate, etc.) as black
            if (isInCaveMode && isWallBlock(blockState, pos, playerY)) {
                return 0xFF000000; // Black for walls in cave mode
            }
            if (blockState.isAir()) {
                return 0xFF000000;
            }

            // Use block registry name for color lookup
            return getColorFromBlockId(blockState, pos);
        } catch (Exception e) {
            return 0x80404040;
        }
    }

    /**
     * Determines if a block should be considered a "wall" in cave mode.
     * A block is considered a wall if the vertical column (10 blocks below to 4 blocks above the player)
     * is completely filled with solid blocks, indicating the player cannot access that area.
     */
    private boolean isWallBlock(BlockState blockState, BlockPos pos, int playerY) {
        if (mc.level == null) return false;

        // Check the vertical column from player Y - 10 to player Y + 4
        int startY = playerY - 10;
        int endY = playerY + 4;

        // Count how many solid blocks are in this vertical range
        int solidCount = 0;
        int totalChecked = 0;

        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(pos.getX(), startY, pos.getZ());

        for (int y = startY; y <= endY; y++) {
            checkPos.setY(y);
            BlockState state = mc.level.getBlockState(checkPos);
            totalChecked++;

            if (!state.isAir()) {
                solidCount++;
            }
        }

        // If the column is mostly solid (at least 80% filled), it's a wall
        // This means the player can't access this area - it's inside solid rock
        float solidRatio = (float) solidCount / totalChecked;
        return solidRatio >= 1.0f;
    }

    private int getColorFromBlockId(BlockState blockState, BlockPos pos) {
        // Don't cache final colors because biome tints are position-dependent
        // Moving between biomes changes the tint, so we must always apply fresh biome colors

        int color;
        try {
            // Load color from texture (this is cached in textureColours)
            color = loadBlockColourFromTexture(blockState, pos);
        } catch (Exception e) {
            // Fallback to MapColor
            color = blockState.getMapColor(mc.level, pos).calculateRGBColor(MapColor.Brightness.NORMAL);
        }

        return color;
    }

    private int loadBlockColourFromTexture(BlockState state, BlockPos pos) {
        try {
            // Ensure we're on a thread that can access resources
            if (mc.getResourceManager() == null) {
                return state.getMapColor(mc.level, pos).calculateRGBColor(MapColor.Brightness.NORMAL);
            }

            BlockModelShaper bms = mc.getBlockRenderer().getBlockModelShaper();
            BakedModel model = bms.getBlockModel(state);

            // Get texture sprite - try UP face first, then particle icon
            TextureAtlasSprite texture = null;
            TextureAtlasSprite missingTexture = mc.getModelManager().getAtlas(TextureAtlas.LOCATION_BLOCKS)
                    .getSprite(MissingTextureAtlasSprite.getLocation());

            // Use thread-safe random source for background thread
            List<BakedQuad> upQuads = model.getQuads(state, Direction.UP, BACKGROUND_RANDOM);
            if (upQuads != null && !upQuads.isEmpty() && upQuads.get(0).getSprite() != missingTexture) {
                texture = upQuads.get(0).getSprite();
            } else {
                texture = model.getParticleIcon();
                if (texture == missingTexture) {
                    // Try other faces
                    for (Direction dir : Direction.values()) {
                        if (dir != Direction.UP) {
                            List<BakedQuad> quads = model.getQuads(state, dir, BACKGROUND_RANDOM);
                            if (!quads.isEmpty()) {
                                texture = quads.get(0).getSprite();
                                if (texture != missingTexture) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if (texture == null || texture == missingTexture) {
                return state.getMapColor(mc.level, pos).calculateRGBColor(MapColor.Brightness.NORMAL);
            }

            // Get texture file name
            String name = texture.contents().name() + ".png";

            // Check texture color cache
            Integer cachedColour = textureColours.get(name);
            if (cachedColour != null) {
                // Apply biome multipliers to cached texture color
                return applyBlockColorMultipliers(cachedColour, state, pos);
            }

            // Load and sample the texture file
            String[] args = name.split(":");
            if (args.length < 2) {
                args = new String[]{"minecraft", args[0]};
            }

            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(args[0], "textures/" + args[1]);

            // Try-with-resources to ensure stream is closed
            try (InputStream input = mc.getResourceManager().getResource(location)
                    .orElseThrow(() -> new IOException("Resource not found: " + location))
                    .open()) {

                BufferedImage img = ImageIO.read(input);
                if (img == null) {
                    // ImageIO.read returned null - corrupted/unsupported format
                    int mapColor = state.getMapColor(mc.level, pos).calculateRGBColor(MapColor.Brightness.NORMAL);
                    textureColours.put(name, mapColor);
                    return mapColor;
                }

                int red = 0;
                int green = 0;
                int blue = 0;
                int alpha = 0;
                int total = 0;

                int ts = Math.min(img.getWidth(), img.getHeight());
                if (ts > 0) {
                    int diff = Math.max(1, Math.min(4, ts / 8));
                    int parts = ts / diff;

                    for (int i = 0; i < parts; i++) {
                        for (int j = 0; j < parts; j++) {
                            int rgb = img.getRGB(i * diff, j * diff);
                            int a = (rgb >> 24) & 255;

                            if (a > 10) { // Consider pixels with alpha > 10 instead of just non-zero
                                red += (rgb >> 16) & 255;
                                green += (rgb >> 8) & 255;
                                blue += rgb & 255;
                                alpha += a;
                                total++;
                            }
                        }
                    }
                }

                if (total == 0) {
                    // Texture was fully transparent, use MapColor
                    int mapColor = state.getMapColor(mc.level, pos).calculateRGBColor(MapColor.Brightness.NORMAL);
                    textureColours.put(name, mapColor);
                    return mapColor;
                }

                red /= total;
                green /= total;
                blue /= total;
                alpha = 255; // Force full opacity for minimap rendering

                // Return in ARGB format
                int result = (alpha << 24) | (red << 16) | (green << 8) | blue;
                textureColours.put(name, result);

                return applyBlockColorMultipliers(result, state, pos);

            }
        } catch (Exception e) {
            // Fallback to MapColor if texture loading fails
            return state.getMapColor(mc.level, pos).calculateRGBColor(MapColor.Brightness.NORMAL);
        }
    }

    private int applyBlockColorMultipliers(int baseColor, BlockState state, BlockPos pos) {
        try {
            // Extract components from base color (ARGB format)
            int baseRed = (baseColor >> 16) & 0xFF;
            int baseGreen = (baseColor >> 8) & 0xFF;
            int baseBlue = baseColor & 0xFF;

            // Check if the texture already has a strong color (not grayscale/neutral)
            // This is for resource packs that replace block textures with colored versions
            // Grayscale textures have R≈G≈B, colored textures have significant differences
            int maxComponent = Math.max(baseRed, Math.max(baseGreen, baseBlue));
            int minComponent = Math.min(baseRed, Math.min(baseGreen, baseBlue));
            int colorDeviation = maxComponent - minComponent;

            // If texture already has strong color (deviation > 30), skip biome tinting
            // This preserves resource pack textures like purple grass, pink leaves, etc.
            if (colorDeviation > 30) {
                return baseColor;
            }

            // For grayscale/neutral textures, apply biome-specific tinting
            BlockColors blockColors = mc.getBlockColors();
            int tintColor = blockColors.getColor(state, mc.level, pos, 0);

            if (tintColor == -1 || tintColor == 0xFFFFFF) {
                // No tinting available, return base color as-is
                return baseColor;
            }

            // Extract alpha from base color (ARGB format)
            int baseAlpha = (baseColor >> 24) & 0xFF;

            // Extract tint multipliers (RGB format, no alpha)
            int tintRed = (tintColor >> 16) & 0xFF;
            int tintGreen = (tintColor >> 8) & 0xFF;
            int tintBlue = tintColor & 0xFF;

            // Apply tint by multiplying (this is how Minecraft does biome coloring)
            int finalRed = (baseRed * tintRed) / 255;
            int finalGreen = (baseGreen * tintGreen) / 255;
            int finalBlue = (baseBlue * tintBlue) / 255;

            // Return tinted color in ARGB format
            return (baseAlpha << 24) | (finalRed << 16) | (finalGreen << 8) | finalBlue;

        } catch (Exception e) {
            // If tinting fails, return original color
            return baseColor;
        }
    }

    private void renderWaypoints(GuiGraphics gui, int centerX, int centerY, int radius,
                                 double playerX, double playerZ, float playerYaw, boolean rotated) {
        // Use the same blocksPerPixel calculation as terrain rendering for consistency
        int blocksPerPixel = Math.max(1, (int)zoomLevel);

        // Precompute rotation math used when minimap is rotated
        double rotationDegrees = -playerYaw - 180.0;
        double rotationRad = Math.toRadians(rotationDegrees);
        double cos = Math.cos(rotationRad);
        double sin = Math.sin(rotationRad);

        for (Waypoint waypoint : waypoints) {
            // Calculate relative position
            double dx = waypoint.x - playerX;
            double dz = waypoint.z - playerZ;

            // Map directly without rotation in world-space; rotation of drawing handled by pose when rotated==true
            double rotX = dx / blocksPerPixel;
            double rotZ = dz / blocksPerPixel;

            int screenX;
            int screenY;

            if (rotated) {
                // Compute the rotated offsets so the waypoint moves with the rotated map
                // Standard 2D rotation: x' = x*cos - y*sin, y' = x*sin + y*cos
                int rotatedX = (int) Math.round(rotX * cos - rotZ * sin);
                int rotatedY = (int) Math.round(rotX * sin + rotZ * cos);

                // In rotated pose the origin is the map center, so keep offsets relative to that origin
                screenX = rotatedX;
                screenY = rotatedY;
            } else {
                // North-up mode: translate by center
                screenX = centerX + (int)rotX;
                screenY = centerY + (int)rotZ;
            }

            // Check if waypoint is within minimap bounds (distance invariant under rotation)
            double distFromCenter = Math.sqrt((rotX * rotX) + (rotZ * rotZ));

            // Only render waypoints that are visible within the minimap circle
            if (distFromCenter < radius - 10) {
                String label = waypoint.getShortName();
                int labelWidth = mc.font.width(label);

                if (waypoint.group == waypointType) {
                    if (rotated) {
                        // Cancel the map rotation so labels remain upright, but keep their rotated map position.
                        // We're still inside the rotated pose where origin == map center.
                        gui.pose().pushPose();
                        // Apply inverse rotation to cancel the map rotation (map rotation was rotationDegrees)
                        gui.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(playerYaw + 180f));
                        // Ensure labels render above the map
                        gui.pose().translate(0, 0, 200);
                        // Draw using coordinates relative to map center (rotated offsets computed above)
                        gui.drawString(mc.font, label, screenX - labelWidth / 2, screenY - 3, waypoint.color);
                        gui.pose().popPose();
                    } else {
                        gui.drawString(mc.font, label, screenX - labelWidth / 2, screenY - 3, waypoint.color);
                    }
                }
            }
        }
    }

    private void drawCircle(GuiGraphics gui, int centerX, int centerY, int radius, int color) {
        // Use Bresenham's circle algorithm for filled circles - more efficient
        int radiusSquared = radius * radius;
        for (int y = -radius; y <= radius; y++) {
            int width = (int)Math.sqrt(radiusSquared - y * y);
            gui.fill(centerX - width, centerY + y, centerX + width + 1, centerY + y + 1, color);
        }
    }

    private void drawCircleOutline(GuiGraphics gui, int centerX, int centerY, int radius, int color, int thickness) {
        // Optimized circle outline using midpoint circle algorithm
        for (int t = 0; t < thickness; t++) {
            int r = radius - t;
            int x = 0;
            int y = r;
            int d = 3 - 2 * r;

            while (y >= x) {
                // Draw 8 octants
                drawPixel(gui, centerX + x, centerY + y, color);
                drawPixel(gui, centerX - x, centerY + y, color);
                drawPixel(gui, centerX + x, centerY - y, color);
                drawPixel(gui, centerX - x, centerY - y, color);
                drawPixel(gui, centerX + y, centerY + x, color);
                drawPixel(gui, centerX - y, centerY + x, color);
                drawPixel(gui, centerX + y, centerY - x, color);
                drawPixel(gui, centerX - y, centerY - x, color);

                x++;
                if (d > 0) {
                    y--;
                    d = d + 4 * (x - y) + 10;
                } else {
                    d = d + 4 * x + 6;
                }
            }
        }
    }

    private void drawPixel(GuiGraphics gui, int x, int y, int color) {
        gui.fill(x, y, x + 1, y + 1, color);
    }

    // Modified: added rotated flag
    private void renderEntitiesOnMinimap(GuiGraphics gui, int centerX, int centerY, int radius,
                                         double playerX, double playerY, double playerZ, int blocksPerPixel, boolean rotated) {
        try {
            // Search radius in world blocks
            int searchRadiusBlocks = radius * blocksPerPixel + 2;
            AABB box = new AABB(playerX - searchRadiusBlocks, playerY - 5, playerZ - searchRadiusBlocks,
                    playerX + searchRadiusBlocks, playerY + 5, playerZ + searchRadiusBlocks);

            List<Entity> entities = mc.level.getEntitiesOfClass(Entity.class, box, e -> true);

            for (Entity e : entities) {
                if (e == null) continue;
                // Skip invisible/removed entities
                if (e.isRemoved() || e.isSpectator()) continue;

                double dx = e.getX() - playerX;
                double dz = e.getZ() - playerZ;

                double mapX = dx / blocksPerPixel;
                double mapZ = dz / blocksPerPixel;

                int screenX;
                int screenY;

                if (rotated) {
                    // In rotated pose, origin is map center
                    screenX = (int) Math.round(mapX);
                    screenY = (int) Math.round(mapZ);
                } else {
                    screenX = centerX + (int) mapX;
                    screenY = centerY + (int) mapZ;
                }

                double dist = Math.sqrt(mapX * mapX + mapZ * mapZ);
                if (dist >= radius - 2) continue;

                int color = 0;
                // Players
                if (e instanceof Player) {
                    // white for real players, yellow for not-real players
                    boolean real = Util.isRealPlayer(e);
                    color = real ? 0xFFFFFFFF : 0xFFFFFF00;
                }
                // Armor stands that are considered mobs by Util
                else if (e instanceof ArmorStand armorStand) {
                    if (Util.isArmorStandMob(armorStand) || Util.isImpling(armorStand) || Util.isArmorstandNPC(armorStand)) {
                        color = 0xFFFFFF00;
                    } else {
                        continue; // ignore other entities
                    }
                } else {
                    continue; // ignore other entities
                }

                // Draw a small 2x2 dot for visibility
                gui.fill(screenX - 1, screenY - 1, screenX + 1, screenY + 1, color);
            }
        } catch (Exception ex) {
            // swallow - don't break rendering
            System.err.println("MinimapWidget: entity minimap render error: " + ex.getMessage());
        }
    }

    private void drawDirectionArrowSimple(GuiGraphics gui, int centerX, int centerY, float yaw) {
        // Simplified arrow with fewer draw calls
        double angle = Math.toRadians(-yaw);
        int arrowLength = 8;

        int tipX = centerX + (int)(arrowLength * Math.sin(angle));
        int tipY = centerY + (int)(arrowLength * Math.cos(angle));

        // Draw just 3 points for the arrow instead of thick lines
        gui.fill(centerX, centerY, centerX + 1, centerY + 1, 0xFFFFFFFF);
        gui.fill(tipX, tipY, tipX + 1, tipY + 1, 0xFFFFFFFF);

        // Simple arrowhead with just 2 pixels
        double leftAngle = angle + Math.toRadians(135);
        double rightAngle = angle + Math.toRadians(-135);
        int headSize = 3;

        int leftX = tipX + (int)(headSize * Math.sin(leftAngle));
        int leftY = tipY + (int)(headSize * Math.cos(leftAngle));
        int rightX = tipX + (int)(headSize * Math.sin(rightAngle));
        int rightY = tipY + (int)(headSize * Math.cos(rightAngle));

        gui.fill(leftX, leftY, leftX + 1, leftY + 1, 0xFFFFFFFF);
        gui.fill(rightX, rightY, rightX + 1, rightY + 1, 0xFFFFFFFF);
    }

    private void drawLine(GuiGraphics gui, int x1, int y1, int x2, int y2, int color, int thickness) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            // Draw thick point
            for (int tx = -thickness/2; tx <= thickness/2; tx++) {
                for (int ty = -thickness/2; ty <= thickness/2; ty++) {
                    gui.fill(x1 + tx, y1 + ty, x1 + tx + 1, y1 + ty + 1, color);
                }
            }

            if (x1 == x2 && y1 == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    private void loadWaypoints() {
        waypoints.clear();

        try {
            // Get the game directory
            Path gameDir = Paths.get(".");
            Path waypointFile = gameDir.resolve("xaero")
                    .resolve("minimap")
                    .resolve("Multiplayer_Any Address")
                    .resolve("dim%0")
                    .resolve("mw$default_1.txt");

            if (!Files.exists(waypointFile)) {
                System.out.println("Waypoint file not found: " + waypointFile.toAbsolutePath());
                return;
            }

            List<String> lines = Files.readAllLines(waypointFile);
            for (String line : lines) {
                if (line.startsWith("waypoint:")) {
                    Waypoint waypoint = parseWaypoint(line);
                    if (waypoint != null) {
                        waypoints.add(waypoint);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading waypoints: " + e.getMessage());
        }
    }

    private Waypoint parseWaypoint(String line) {
        try {
            // Format: waypoint:WaypointName:MiniMapDisplayName:X:Y:Z:8:false:0:gui.xaero_default:false:0:0:false
            //         waypoint:Mining Spot:═:31:43:-88:8:false:0:Underground:false:0:0
            String[] parts = line.split(":");

            if (parts.length < 6) return null;

            String name = parts[1];
            String displayName = parts[2];
            int x = Integer.parseInt(parts[3]);
            int y = Integer.parseInt(parts[4]);
            int z = Integer.parseInt(parts[5]);
            int colorCode = parts.length > 6 ? Integer.parseInt(parts[6]) : 0;
            String group = parts[9].toUpperCase();
            if (group.contains(".")) {
                group = group.substring(group.lastIndexOf(".") + 1);
            }

            WaypointType groupType;
            switch (group) {
                case "UNDERGROUND":
                    groupType = WaypointType.UNDERGROUND;
                    break;
                default:
                    groupType = WaypointType.XAERO_DEFAULT;
            }

            // Convert Xaero's color code to RGB
            int color = getWaypointColor(colorCode);

            return new Waypoint(name, displayName, x, y, z, color, groupType);
        } catch (Exception e) {
            System.err.println("Error parsing waypoint: " + line + " - " + e.getMessage());
            return null;
        }
    }

    private int getWaypointColor(int colorCode) {
        // Xaero's minimap color codes

        return 0xFFFFFFFF; // White

    }

    // Inner class for waypoint data
    private static class Waypoint {
        String name;
        String displayName;
        WaypointType group;
        int x, y, z;
        int color;

        public Waypoint(String name, String displayName, int x, int y, int z, int color, WaypointType group) {
            this.name = name;
            this.displayName = displayName.isEmpty() ? name : displayName;
            this.group = group;
            this.x = x;
            this.y = y;
            this.z = z;
            this.color = color;
        }

        public String getShortName() {
            String useName = displayName.isEmpty() ? name : displayName;
            // Limit length but keep all characters including Unicode
            if (useName.length() > 8) {
                return useName.substring(0, 8);
            }
            return useName;
        }
    }

    public enum WaypointType {
        XAERO_DEFAULT,
        UNDERGROUND

    }

    // Getters and setters for configuration
    public void setMinimapSize(int size) {
        this.minimapSize = size;
        this.widgetWidth = size;
        this.widgetHeight = size;
        config.setProperty("minimapSize", String.valueOf(size));
        saveConfig();
    }

    public void setZoomLevel(float zoom) {
        this.zoomLevel = Math.max(0.5f, Math.min(5.0f, zoom));
        config.setProperty("zoomLevel", String.valueOf(this.zoomLevel));
        saveConfig();
    }

    // New: setter/getter for rotate option (persisted)
    public void setRotateMinimap(boolean rotate) {
        this.rotateMinimap = rotate;
        Config.setMinimapRotation(rotate);
        saveConfig();
    }

    public boolean getRotateMinimap() {
        return this.rotateMinimap;
    }

    public int getMinimapSize() {
        return this.minimapSize;
    }

    public float getZoomLevel() {
        return this.zoomLevel;
    }

    /**
     * Clears all cached texture colors.
     * Should be called when resource packs are reloaded.
     */
    public static void clearColorCaches() {
        textureColours.clear();
        needsCacheClear = true; // Flag for deferred clear before next terrain update
        System.out.println("MinimapWidget: Color caches cleared for resource pack reload");
    }
}