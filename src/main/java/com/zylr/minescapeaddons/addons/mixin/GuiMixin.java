package com.zylr.minescapeaddons.addons.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.HudMenuWidget;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import net.minecraft.ChatFormatting;
import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    // Shadow Variables from Gui
    @Final
    @Shadow
    private Minecraft minecraft;
    @Final
    @Shadow private RandomSource random;
    @Shadow private int tickCount;
    @Shadow private int toolHighlightTimer;
    @Shadow private ItemStack lastToolHighlight;
    @Shadow private Component overlayMessageString;
    @Shadow private int overlayMessageTime;
    @Shadow private boolean animateOverlayMessageColor;

    // Shadow methods from Gui
    @Shadow
    protected abstract Player getCameraPlayer();
    @Shadow
    protected abstract void renderSlot(GuiGraphics guiGraphics, int x, int y, DeltaTracker deltaTracker, Player player, ItemStack stack, int index);
    @Shadow
    protected abstract boolean isExperienceBarVisible();
    @Shadow
    public abstract Font getFont();
    @Shadow
    protected abstract void renderHeart(GuiGraphics guiGraphics, Gui.HeartType type, int x, int y, boolean hardcore, boolean highlight, boolean blinking);

    // Shadow the sprite/static identifiers used by Gui
    @Final
    @Shadow private static ResourceLocation HOTBAR_SPRITE;
    @Final
    @Shadow private static ResourceLocation HOTBAR_SELECTION_SPRITE;
    @Final
    @Shadow private static ResourceLocation HOTBAR_OFFHAND_LEFT_SPRITE;
    @Final
    @Shadow private static ResourceLocation HOTBAR_OFFHAND_RIGHT_SPRITE;
    @Final
    @Shadow private static ResourceLocation HOTBAR_ATTACK_INDICATOR_BACKGROUND_SPRITE;
    @Final
    @Shadow private static ResourceLocation HOTBAR_ATTACK_INDICATOR_PROGRESS_SPRITE;
    @Final
    @Shadow private static ResourceLocation EXPERIENCE_BAR_BACKGROUND_SPRITE;
    @Final
    @Shadow private static ResourceLocation EXPERIENCE_BAR_PROGRESS_SPRITE;
    @Final
    @Shadow private static ResourceLocation ARMOR_FULL_SPRITE;
    @Final
    @Shadow private static ResourceLocation ARMOR_HALF_SPRITE;
    @Final
    @Shadow private static ResourceLocation ARMOR_EMPTY_SPRITE;
    @Final
    @Shadow private static ResourceLocation FOOD_FULL_SPRITE;
    @Final
    @Shadow private static ResourceLocation FOOD_HALF_SPRITE;
    @Final
    @Shadow private static ResourceLocation FOOD_EMPTY_SPRITE;
    @Final
    @Shadow private static ResourceLocation FOOD_FULL_HUNGER_SPRITE;
    @Final
    @Shadow private static ResourceLocation FOOD_HALF_HUNGER_SPRITE;
    @Final
    @Shadow private static ResourceLocation FOOD_EMPTY_HUNGER_SPRITE;
    @Shadow private int titleTime;
    @Shadow private int titleFadeInTime;
    @Shadow private int titleStayTime;
    @Shadow private int titleFadeOutTime;
    @Shadow private Component title;
    @Shadow private Component subtitle;

    // ── Crosshair shift ──────────────────────────────────────────────────────
    // The crosshair is drawn at guiWidth/2, guiHeight/2 (full-window centre).
    // With the viewport narrowed by PANEL_WIDTH on the right, the visual centre
    // is at (guiWidth - PANEL_WIDTH)/2, which is PANEL_WIDTH/2 pixels to the
    // LEFT of the full-window centre. We push a translated pose at HEAD and
    // pop it at TAIL so that every draw call inside renderCrosshair is shifted.

    @Inject(method = "renderCrosshair", at = @At("HEAD"))
    private void shiftCrosshairPre(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!Config.getFixedMode()) return;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(-ViewportAdjustmentListener.PANEL_WIDTH / 2.0f,
                -ViewportAdjustmentListener.PANEL_HEIGHT / 2.0f, 0);
    }

    @Inject(method = "renderCrosshair", at = @At("TAIL"))
    private void shiftCrosshairPost(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!Config.getFixedMode()) return;
        guiGraphics.pose().popPose();
    }

    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true)
    private void onRenderChat(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        // Cancel the original chat rendering to prevent it from drawing over our custom chat
        if (Config.getCustomChat())
            ci.cancel();
    }


    @Inject(method = "renderItemHotbar", at = @At("HEAD"), cancellable = true)
    private void onRenderHotbar(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();

            Player player = this.getCameraPlayer();
            if (player != null) {
                ItemStack itemstack = player.getOffhandItem();
                HumanoidArm humanoidarm = player.getMainArm().getOpposite();
                int i = guiGraphics.guiWidth() / 2;
                int j = 182;
                int k = 91;
                int x = 0;
                int y = 0;
                RenderSystem.enableBlend();
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0.0F, 0.0F, -90.0F);

                if (menu.isRightSide())
                    x = menu.getLeftSide() - 182 - 5;
                if (menu.isLeftSide())
                    x = menu.getScaledRightSide() + 5;
                if (menu.isBottomHalf())
                    y = menu.getScaledBottom() - 22;
                if (menu.isTopHalf())
                    y = menu.getTop();
                guiGraphics.blitSprite(HOTBAR_SPRITE, x, y, 182, 22);
                if (menu.isRightSide())
                    x = menu.getLeftSide() - 182 - 5 - 1 + player.getInventory().selected * 20;
                if (menu.isLeftSide())
                    x = menu.getScaledRightSide() + 4 + player.getInventory().selected * 20;
                if (menu.isBottomHalf())
                    y = menu.getScaledBottom() - 22 - 1;
                if (menu.isTopHalf())
                    y = menu.getTop() - 1;
                guiGraphics.blitSprite(HOTBAR_SELECTION_SPRITE, x, y, 24, 23);
                if (!itemstack.isEmpty()) {
                    if (menu.isRightSide())
                        x = menu.getLeftSide() - 182 - 29 - 5;
                    if (menu.isLeftSide())
                        x = menu.getScaledRightSide() + 182 + 10;
                    if (menu.isBottomHalf())
                        y = menu.getScaledBottom() - 23;
                    if (menu.isTopHalf())
                        y = menu.getTop() - 1;
                    guiGraphics.blitSprite(HOTBAR_OFFHAND_LEFT_SPRITE, x, y, 29, 24);
                }

                guiGraphics.pose().popPose();
                RenderSystem.disableBlend();
                int l = 1;

                for (int i1 = 0; i1 < 9; ++i1) {
                    if (menu.isRightSide())
                        x = (menu.getLeftSide() - 96) - 90;
                    if (menu.isLeftSide())
                        x = menu.getScaledRightSide() + 6;
                    if (menu.isBottomHalf())
                        y = menu.getScaledBottom() - 16 - 3;
                    if (menu.isTopHalf())
                        y = menu.getTop() + 3;
                    int j1 = x + i1 * 20 + 2;
                    int k1 = y;
                    this.renderSlot(guiGraphics, j1, k1, deltaTracker, player, (ItemStack) player.getInventory().items.get(i1), l++);
                }

                if (!itemstack.isEmpty()) {
                    if (menu.isRightSide())
                        x = menu.getLeftSide() - 182 - 23 - 8;
                    if (menu.isLeftSide())
                        x = menu.getScaledRightSide() + 182 + 13;
                    if (menu.isBottomHalf())
                        y = menu.getScaledBottom() - 16 - 3;
                    if (menu.isTopHalf())
                        y = menu.getTop() + 3;
                    this.renderSlot(guiGraphics, x, y, deltaTracker, player, itemstack, l++);
                }

                if (this.minecraft.options.attackIndicator().get() == AttackIndicatorStatus.HOTBAR) {
                    RenderSystem.enableBlend();
                    float f = this.minecraft.player.getAttackStrengthScale(0.0F);
                    if (f < 1.0F) {
                        if (menu.isRightSide())
                            x = menu.getLeftSide() - 182 - 23 - 8 - 6 - 18;
                        if (menu.isLeftSide())
                            x = menu.getScaledRightSide() + 182 + 13 + 6 + 18;
                        if (menu.isBottomHalf())
                            y = menu.getScaledBottom() - 16 - 3;
                        if (menu.isTopHalf())
                            y = menu.getTop() + 3;
                        int l1 = (int)(f * 19.0F);
                        guiGraphics.blitSprite(HOTBAR_ATTACK_INDICATOR_BACKGROUND_SPRITE, x, y, 18, 18);
                        guiGraphics.blitSprite(HOTBAR_ATTACK_INDICATOR_PROGRESS_SPRITE, 18, 18, 0, 18 - l1, x, y + 18 - l1, 18, l1);
                    }
                    RenderSystem.disableBlend();
                }
            }
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void onRenderExperienceBar(GuiGraphics guiGraphics, int x, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
            this.minecraft.getProfiler().push("expBar");
            int i = this.minecraft.player.getXpNeededForNextLevel();
            if (i > 0) {
                int y = 0;
                if (menu.isRightSide())
                    x = menu.getLeftSide() - 182 - 5;
                if (menu.isLeftSide())
                    x = menu.getScaledRightSide() + 5;
                if (menu.isBottomHalf())
                    y = menu.getScaledBottom() - 32 - 3;
                if (menu.isTopHalf())
                    y = menu.getTop() + 27 + 3;
                int k = (int)(this.minecraft.player.experienceProgress * 183.0F);
                RenderSystem.enableBlend();
                guiGraphics.blitSprite(EXPERIENCE_BAR_BACKGROUND_SPRITE, x, y, 182, 5);
                if (k > 0) {
                    guiGraphics.blitSprite(EXPERIENCE_BAR_PROGRESS_SPRITE, 182, 5, 0, 0, x, y, k, 5);
                }
                RenderSystem.disableBlend();
            }
            this.minecraft.getProfiler().pop();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void onRenderExperienceLevel(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
            int i = this.minecraft.player.experienceLevel;
            if (this.isExperienceBarVisible() && i > 0) {
                this.minecraft.getProfiler().push("expLevel");
                String s = i + "";
                int j = 0;
                int k = 0;
                if (menu.isRightSide())
                    j = menu.getLeftSide() - 91 - 5 - (this.getFont().width(s) / 2);
                if (menu.isLeftSide())
                    j = menu.getScaledRightSide() + 91 + 5 - (this.getFont().width(s) / 2);
                if (menu.isBottomHalf())
                    k = menu.getScaledBottom() - 35 - this.getFont().lineHeight - 4;
                if (menu.isTopHalf())
                    k = menu.getTop() + 35 + 4;
                guiGraphics.drawString(this.getFont(), s, j + 1, k, 0, false);
                guiGraphics.drawString(this.getFont(), s, j - 1, k, 0, false);
                guiGraphics.drawString(this.getFont(), s, j, k + 1, 0, false);
                guiGraphics.drawString(this.getFont(), s, j, k - 1, 0, false);
                guiGraphics.drawString(this.getFont(), s, j, k, 8453920, false);
                this.minecraft.getProfiler().pop();
            }
        }
    }

    @Inject(method = "renderHearts", at = @At("HEAD"), cancellable = true)
    private void onRenderHearts(GuiGraphics guiGraphics, Player player, int x, int y, int height, int offsetHeartIndex, float maxHealth, int currentHealth, int displayHealth, int absorptionAmount, boolean renderHighlight, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
            Gui.HeartType gui$hearttype = HeartTypeInvoker.invokeForPlayer(player);
            boolean flag = player.level().getLevelData().isHardcore();
            int i = Mth.ceil((double)maxHealth / 2.0);
            int j = Mth.ceil((double)absorptionAmount / 2.0);
            int k = i * 2;

            for (int l = i + j - 1; l >= 0; l--) {
                int i1 = l / 10;
                int j1 = l % 10;
                int k1 = x + j1 * 8;
                int l1 = y - i1 * height;

                if (menu.isRightSide())
                    k1 = (menu.getLeftSide() - 182 - 5) + j1 * 8;
                if (menu.isLeftSide())
                    k1 = (menu.getScaledRightSide() + 5) + j1 * 8;
                if (menu.isBottomHalf())
                    l1 = (menu.getScaledBottom() - 48) - i1 * height;
                if (menu.isTopHalf())
                    l1 = (menu.getTop() + 38) - i1 * height;
                if (currentHealth + absorptionAmount <= 4)
                    l1 += this.random.nextInt(2);
                if (l < i && l == offsetHeartIndex)
                    l1 -= 2;

                this.renderHeart(guiGraphics, Gui.HeartType.CONTAINER, k1, l1, flag, renderHighlight, false);
                int i2 = l * 2;
                boolean flag1 = l >= i;
                if (flag1) {
                    int j2 = i2 - k;
                    if (j2 < absorptionAmount) {
                        boolean flag2 = j2 + 1 == absorptionAmount;
                        this.renderHeart(guiGraphics, gui$hearttype == Gui.HeartType.WITHERED ? gui$hearttype : Gui.HeartType.ABSORBING, k1, l1, flag, false, flag2);
                    }
                }
                if (renderHighlight && i2 < displayHealth) {
                    boolean flag3 = i2 + 1 == displayHealth;
                    this.renderHeart(guiGraphics, gui$hearttype, k1, l1, flag, true, flag3);
                }
                if (i2 < currentHealth) {
                    boolean flag4 = i2 + 1 == currentHealth;
                    this.renderHeart(guiGraphics, gui$hearttype, k1, l1, flag, false, flag4);
                }
            }
        }
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void onRenderArmor(GuiGraphics guiGraphics, Player player, int y, int heartRows, int height, int x, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();

            if (menu.isRightSide())
                x = menu.getLeftSide() - 182 - 5;
            if (menu.isLeftSide())
                x = menu.getScaledRightSide() + 5;
            if (menu.isBottomHalf())
                y = menu.getScaledBottom() - 48;
            if (menu.isTopHalf())
                y = menu.getTop() + 48;

            int i = player.getArmorValue();
            if (i > 0) {
                RenderSystem.enableBlend();
                int j = 0;
                if (menu.isTopHalf())
                    j = y - (heartRows + 1) * height;
                else
                    j = y - (heartRows - 1) * height - 10;

                for (int k = 0; k < 10; k++) {
                    int l = x + k * 8;
                    if (k * 2 + 1 < i)  guiGraphics.blitSprite(ARMOR_FULL_SPRITE,  l, j, 9, 9);
                    if (k * 2 + 1 == i) guiGraphics.blitSprite(ARMOR_HALF_SPRITE,  l, j, 9, 9);
                    if (k * 2 + 1 > i)  guiGraphics.blitSprite(ARMOR_EMPTY_SPRITE, l, j, 9, 9);
                }
                RenderSystem.disableBlend();
            }
        }
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void onRenderFood(GuiGraphics guiGraphics, Player player, int y, int x, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();

            if (menu.isRightSide())
                x = menu.getLeftSide() - 5;
            if (menu.isLeftSide())
                x = menu.getScaledRightSide() + 182 + 5;
            if (menu.isBottomHalf())
                y = menu.getScaledBottom() - 48;
            if (menu.isTopHalf())
                y = menu.getTop() + 38;

            FoodData fooddata = player.getFoodData();
            int i = fooddata.getFoodLevel();
            RenderSystem.enableBlend();
            for (int j2 = 0; j2 < 10; j2++) {
                int k = y;
                ResourceLocation resourcelocation, resourcelocation1, resourcelocation2;
                if (player.hasEffect(MobEffects.HUNGER)) {
                    resourcelocation = FOOD_EMPTY_HUNGER_SPRITE;
                    resourcelocation1 = FOOD_HALF_HUNGER_SPRITE;
                    resourcelocation2 = FOOD_FULL_HUNGER_SPRITE;
                } else {
                    resourcelocation = FOOD_EMPTY_SPRITE;
                    resourcelocation1 = FOOD_HALF_SPRITE;
                    resourcelocation2 = FOOD_FULL_SPRITE;
                }
                if (player.getFoodData().getSaturationLevel() <= 0.0F && this.tickCount % (i * 3 + 1) == 0)
                    k = y + (this.random.nextInt(3) - 1);
                int l = x - j2 * 8 - 9;
                guiGraphics.blitSprite(resourcelocation, l, k, 9, 9);
                if (j2 * 2 + 1 < i)  guiGraphics.blitSprite(resourcelocation2, l, k, 9, 9);
                if (j2 * 2 + 1 == i) guiGraphics.blitSprite(resourcelocation1, l, k, 9, 9);
            }
            RenderSystem.disableBlend();
        }
    }

    @Inject(method = "renderOverlayMessage", at = @At("HEAD"), cancellable = true)
    private void onRenderOverlayMessage(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            if (Config.getMinimap()) return;
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
            Font font = this.getFont();
            if (this.overlayMessageString != null && this.overlayMessageTime > 0) {
                this.minecraft.getProfiler().push("overlayMessage");
                float f = (float)this.overlayMessageTime - deltaTracker.getGameTimeDeltaPartialTick(false);
                int alpha = (int)(f * 255.0F / 20.0F);
                if (alpha > 255) alpha = 255;
                if (alpha > 8) {
                    int color;
                    if (this.animateOverlayMessageColor)
                        color = Mth.hsvToArgb(f / 50.0F, 0.7F, 0.6F, alpha);
                    else
                        color = FastColor.ARGB32.color(alpha, -1);
                    int x, y;
                    if (menu.isRightSide())
                        x = menu.getLeftSide() - 5 - 91 - (font.width(this.overlayMessageString) / 2);
                    else
                        x = menu.getScaledRightSide() + 5 + 91 - (font.width(this.overlayMessageString) / 2);
                    if (menu.isBottomHalf())
                        y = menu.getScaledBottom() - 78 - 3;
                    else
                        y = menu.getTop() + 8 + 3;
                    guiGraphics.drawStringWithBackdrop(font, this.overlayMessageString, x, y, 0, color);
                }
                this.minecraft.getProfiler().pop();
            }
        }
    }

    @Inject(method = "renderSelectedItemName*", at = @At("HEAD"), cancellable = true)
    private void onRenderSelectedItemName(GuiGraphics guiGraphics, int yShift, CallbackInfo ci) {
        if (Config.getCustomHotbar()) {
            ci.cancel();
            HudMenuWidget menu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
            this.minecraft.getProfiler().push("selectedItemName");
            if (this.toolHighlightTimer > 0 && !this.lastToolHighlight.isEmpty()) {
                MutableComponent mutablecomponent = Component.empty()
                        .append(this.lastToolHighlight.getHoverName())
                        .withStyle(this.lastToolHighlight.getRarity().getStyleModifier());
                if (this.lastToolHighlight.has(DataComponents.CUSTOM_NAME))
                    mutablecomponent.withStyle(ChatFormatting.ITALIC);
                Component highlightTip = this.lastToolHighlight.getHighlightTip(mutablecomponent);
                int i = this.getFont().width(highlightTip);
                int j, k;
                if (menu.isRightSide())
                    j = menu.getLeftSide() - 5 - 91 - (i / 2);
                else
                    j = menu.getScaledRightSide() + 5 + 91 - (i / 2);
                if (menu.isBottomHalf())
                    k = menu.getScaledBottom() - 10 - Math.max(yShift, 59);
                else
                    k = menu.getTop() + Math.max(yShift, 59);
                if (!this.minecraft.gameMode.canHurtPlayer()) k += 14;
                int l = (int)((float)this.toolHighlightTimer * 256.0F / 10.0F);
                if (l > 255) l = 255;
                if (l > 0) {
                    Font font = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(lastToolHighlight).getFont(lastToolHighlight, net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.FontContext.SELECTED_ITEM_NAME);
                    if (font == null) {
                        guiGraphics.drawStringWithBackdrop(this.getFont(), highlightTip, j, k, i, FastColor.ARGB32.color(l, -1));
                    } else {
                        j = (guiGraphics.guiWidth() - font.width(highlightTip)) / 2;
                        guiGraphics.drawStringWithBackdrop(font, highlightTip, j, k, i, FastColor.ARGB32.color(l, -1));
                    }
                }
            }
            this.minecraft.getProfiler().pop();
        }
    }

    @Inject(method = "renderTitle", at = @At("HEAD"), cancellable = true)
    private void renderTitle(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Config.getFixedMode()) {
            ci.cancel();

            if (this.title != null && this.titleTime > 0) {
                Font font = this.getFont();
                this.minecraft.getProfiler().push("titleAndSubtitle");
                float f = (float) this.titleTime - deltaTracker.getGameTimeDeltaPartialTick(false);
                int i = 255;
                if (this.titleTime > this.titleFadeOutTime + this.titleStayTime) {
                    float f1 = (float) (this.titleFadeInTime + this.titleStayTime + this.titleFadeOutTime) - f;
                    i = (int) (f1 * 255.0F / (float) this.titleFadeInTime);
                }

                if (this.titleTime <= this.titleFadeOutTime) {
                    i = (int) (f * 255.0F / (float) this.titleFadeOutTime);
                }

                i = Mth.clamp(i, 0, 255);
                if (i > 8) {
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate((float) ((guiGraphics.guiWidth() - ViewportAdjustmentListener.PANEL_WIDTH) / 2),
                            (float) ((guiGraphics.guiHeight() - ViewportAdjustmentListener.PANEL_HEIGHT) / 2), 0.0F);
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(4.0F, 4.0F, 4.0F);
                    int l = font.width(this.title);
                    int j = FastColor.ARGB32.color(i, -1);
                    guiGraphics.drawStringWithBackdrop(font, this.title, -l / 2, -10, l, j);
                    guiGraphics.pose().popPose();
                    if (this.subtitle != null) {
                        guiGraphics.pose().pushPose();
                        guiGraphics.pose().scale(2.0F, 2.0F, 2.0F);
                        int k = font.width(this.subtitle);
                        guiGraphics.drawStringWithBackdrop(font, this.subtitle, -k / 2, 5, k, j);
                        guiGraphics.pose().popPose();
                    }

                    guiGraphics.pose().popPose();
                }

                this.minecraft.getProfiler().pop();
            }
        }
    }
}
