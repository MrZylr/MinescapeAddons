package com.zylr.minescapeaddons.addons.gui.widgets.minimap;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.Player;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class PrayerPointsOrb extends Widget {
    private static final ResourceLocation FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_frame.png");
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_prayer.png");
    private static final ResourceLocation ICON = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_prayer_icon.png");
    ResourceLocation buttonImage = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "widget/clear");

    private int currentPrayer;
    private int maxPrayer;

    private ImageButton prayerButton;

    public PrayerPointsOrb(Widget parent, boolean visible) {
        this.parent = parent;
        this.visible = visible;

        this.widgetWidth = 57;
        this.widgetHeight = 34;

        this.isParent = false;

        this.currentPrayer = 0;
        this.maxPrayer = 0;

        this.anchorX = 0;
        this.anchorY = 0;

        this.scale = parent.scale;


        this.prayerButton = new ImageButton(this.anchorX, this.anchorY, this.widgetWidth, this.widgetHeight, new WidgetSprites(buttonImage, buttonImage), button -> {
            // Handle button click
            this.buttonClick();
        });
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        if (this.visible) {
            super.render(gui);
            this.scale = parent.scale;
            Player player = MinescapeAddons.getInstance().player;

            this.anchorX = this.parent.getAnchorX() - 33;
            this.anchorY = this.parent.getAnchorY() + 80;

            this.prayerButton.setX(this.anchorX);
            this.prayerButton.setY(this.anchorY);

            int backgroundXOffset = 27;
            int backgroundYOffset = 4;

            this.currentPrayer = player.getPrayerPoints();
            this.maxPrayer = MinescapeAddons.skills.get(SkillType.PRAYER).getLevel();

            if (currentPrayer > maxPrayer) {
                currentPrayer = maxPrayer;
            }
            if (currentPrayer < 0) {
                currentPrayer = 0;
            }

            double prayerPercentage = (double) currentPrayer / maxPrayer;
            int prayerBarHeight = (int) (26 * prayerPercentage);
            int prayerBarYOffset = 26 - prayerBarHeight;

            gui.blit(FRAME, this.anchorX, this.anchorY, 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);

            // transform both corners by the current PoseStack
            Window window = Minecraft.getInstance().getWindow();
            int fbWidth = window.getScreenWidth();
            int fbHeight = window.getScreenHeight();
            int guiWidth = window.getGuiScaledWidth();
            int guiHeight = window.getGuiScaledHeight();

            double scaleX = (double) fbWidth / guiWidth;
            double scaleY = (double) fbHeight / guiHeight;

            int guiX = this.anchorX + backgroundXOffset;
            int guiY = this.anchorY + backgroundYOffset + prayerBarYOffset;
            int guiW = 26;
            int guiH = 26;

            // transform corners by the current PoseStack so any scale/translate is accounted for
            PoseStack.Pose poseEntry = gui.pose().last();
            Matrix4f mat = poseEntry.pose(); // must be org.joml.Matrix4f in your environment
            Vector4f p1 = new Vector4f((float) guiX, (float) guiY, 0f, 1f);
            Vector4f p2 = new Vector4f((float) (guiX + guiW), (float) (guiY + guiH), 0f, 1f);

            // call transform on the matrix (org.joml API)
            mat.transform(p1);
            mat.transform(p2);

            float minX = Math.min(p1.x, p2.x);
            float minY = Math.min(p1.y, p2.y);
            float maxX = Math.max(p1.x, p2.x);
            float maxY = Math.max(p1.y, p2.y);

            // convert to framebuffer pixels
            int scX = (int) Math.floor(minX * scaleX);
            int scY = (int) Math.floor(minY * scaleY);
            int scW = (int) Math.ceil((maxX - minX) * scaleX);
            int scH = (int) Math.ceil((maxY - minY) * scaleY);

            // convert Y to OpenGL bottom-left origin
            int glY = fbHeight - (scY + scH);

            RenderSystem.enableScissor(scX, glY, scW, scH);
            gui.blit(BACKGROUND, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, guiW, guiH, guiW, guiH);
            RenderSystem.disableScissor();
            gui.blit(ICON, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, guiW, guiH, guiW, guiH);

            //gui.blit(ICON, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);

            Font font = Minecraft.getInstance().font;
            if (Config.getFixedMode() && Config.getSmallInventory())
                font = MinescapeAddons.getInstance().getVanillaFont();
            gui.drawString(font, this.currentPrayer+"", this.anchorX + 10, this.anchorY + 17, getPrayerColor(prayerPercentage));
        }
    }

    public void renderResizable(GuiGraphics gui) {
        if (this.visible) {
            super.render(gui);
            this.scale = parent.scale;
            Player player = MinescapeAddons.getInstance().player;

            this.anchorX = this.parent.getAnchorX() + 7;
            this.anchorY = this.parent.getAnchorY() + 80;

            this.prayerButton.setX(this.anchorX);
            this.prayerButton.setY(this.anchorY);

            int backgroundXOffset = 27;
            int backgroundYOffset = 4;

            this.currentPrayer = player.getPrayerPoints();
            this.maxPrayer = MinescapeAddons.skills.get(SkillType.PRAYER).getLevel();

            if (currentPrayer > maxPrayer) {
                currentPrayer = maxPrayer;
            }
            if (currentPrayer < 0) {
                currentPrayer = 0;
            }

            double prayerPercentage = (double) currentPrayer / maxPrayer;
            int prayerBarHeight = (int) (26 * prayerPercentage);
            int prayerBarYOffset = 26 - prayerBarHeight;

            gui.blit(FRAME, this.anchorX, this.anchorY, 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);

            // transform both corners by the current PoseStack
            Window window = Minecraft.getInstance().getWindow();
            int fbWidth = window.getScreenWidth();
            int fbHeight = window.getScreenHeight();
            int guiWidth = window.getGuiScaledWidth();
            int guiHeight = window.getGuiScaledHeight();

            double scaleX = (double) fbWidth / guiWidth;
            double scaleY = (double) fbHeight / guiHeight;

            int guiX = this.anchorX + backgroundXOffset;
            int guiY = this.anchorY + backgroundYOffset + prayerBarYOffset;
            int guiW = 26;
            int guiH = 26;

            // transform corners by the current PoseStack so any scale/translate is accounted for
            PoseStack.Pose poseEntry = gui.pose().last();
            Matrix4f mat = poseEntry.pose(); // must be org.joml.Matrix4f in your environment
            Vector4f p1 = new Vector4f((float) guiX, (float) guiY, 0f, 1f);
            Vector4f p2 = new Vector4f((float) (guiX + guiW), (float) (guiY + guiH), 0f, 1f);

            // call transform on the matrix (org.joml API)
            mat.transform(p1);
            mat.transform(p2);

            float minX = Math.min(p1.x, p2.x);
            float minY = Math.min(p1.y, p2.y);
            float maxX = Math.max(p1.x, p2.x);
            float maxY = Math.max(p1.y, p2.y);

            // convert to framebuffer pixels
            int scX = (int) Math.floor(minX * scaleX);
            int scY = (int) Math.floor(minY * scaleY);
            int scW = (int) Math.ceil((maxX - minX) * scaleX);
            int scH = (int) Math.ceil((maxY - minY) * scaleY);

            // convert Y to OpenGL bottom-left origin
            int glY = fbHeight - (scY + scH);

            RenderSystem.enableScissor(scX, glY, scW, scH);
            gui.blit(BACKGROUND, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);
            RenderSystem.disableScissor();
            gui.blit(ICON, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);

            gui.blit(ICON, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);

            Font font = Minecraft.getInstance().font;
            if (scale < 1.0f) {
                font = MinescapeAddons.getInstance().getVanillaFont();
            }
            gui.drawString(font, this.currentPrayer+"", this.anchorX + 10, this.anchorY + 17, getPrayerColor(prayerPercentage));
        }
    }

    private int getPrayerColor(double prayerPercentage) {
        int red, green;

        if (prayerPercentage > 0.5) {
            // Green to yellow (100% to 50%)
            // Green stays at 255, red increases from 0 to 255
            red = (int) (255 * 2 * (1.0 - prayerPercentage));
            green = 255;
        } else {
            // Yellow to red (50% to 0%)
            // Red stays at 255, green decreases from 255 to 0
            red = 255;
            green = (int) (255 * 2 * prayerPercentage);
        }

        // Combine RGB into single color value (format: 0xAARRGGBB)
        return 0xFF000000 | (red << 16) | (green << 8);
    }

    private void buttonClick() {
        if (mc == null) return;
        // Run on client thread to be safe
        mc.execute(() -> {
            if (mc.player == null || mc.getConnection() == null) return;
                // send server packet
                mc.getConnection().send(new ServerboundPlayerActionPacket(
                        ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND,
                        BlockPos.ZERO, Direction.DOWN));
        });
    }

    public ImageButton getPrayerButton() {
        return this.prayerButton;
    }
}
