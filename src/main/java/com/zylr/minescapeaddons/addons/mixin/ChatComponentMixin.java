package com.zylr.minescapeaddons.addons.mixin;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.util.List;

@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private List<GuiMessage.Line> trimmedMessages;

    @Shadow
    private int chatScrollbarPos;

    @Shadow
    private boolean newMessageSinceScroll;

    @Shadow
    protected abstract int getLinesPerPage();

    @Shadow
    protected abstract int getWidth();

    @Shadow
    protected abstract double getScale();

    @Shadow
    protected abstract int getMessageEndIndexAt(double mouseX, double mouseY);

    @Shadow
    protected abstract double screenToChatX(double x);

    @Shadow
    protected abstract double screenToChatY(double y);

    @Shadow
    protected abstract int getLineHeight();

    private static double getTimeFactor(int counter) {
        double d0 = (double)counter / 200.0;
        d0 = 1.0 - d0;
        d0 *= 10.0;
        d0 = Mth.clamp(d0, 0.0, 1.0);
        return d0 * d0;
    }

    @Shadow
    protected abstract int getTagIconLeft(GuiMessage.Line line);

    @Shadow
    protected abstract void drawTagIcon(GuiGraphics guiGraphics, int x, int y, GuiMessageTag.Icon icon);


    @Inject(method = "getWidth()I", at = @At("HEAD"), cancellable = true)
    public void onGetWidth(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Integer> cir) {
        if (Config.getCustomChat()) {
            cir.setReturnValue(MinescapeAddons.getInstance().resizableClassic.getChatWidget().getWidgetWidth() - 28);
        }

    }

    @Inject(method = "getHeight()I", at = @At("HEAD"), cancellable = true)
    public void onGetHeight(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Integer> cir) {
        if (Config.getCustomChat()) {
            cir.setReturnValue(MinescapeAddons.getInstance().resizableClassic.getChatWidget().getWidgetHeight() - Minecraft.getInstance().font.lineHeight * 3 - 3);
        }
    }

    @Inject(method = "screenToChatX", at = @At("HEAD"), cancellable = true)
    public void onScreenToChatX(double x, CallbackInfoReturnable<Double> cir) {
        if (Config.getCustomChat()) {
            cir.cancel();
            cir.setReturnValue(x / this.getScale() -
                    (double)MinescapeAddons.getInstance().resizableClassic.getChatWidget().getLeftSide() - 12);
        }
    }

    @Inject(method = "screenToChatY", at = @At("HEAD"), cancellable = true)
    public void onScreenToChatY(double y, CallbackInfoReturnable<Double> cir) {
        if (Config.getCustomChat()) {
            cir.cancel();
            double d0 = MinescapeAddons.getInstance().resizableClassic.getChatWidget().getBottom() - y - (double)22.0F;
            cir.setReturnValue(d0 / (this.getScale() * (double)this.getLineHeight()));
        }
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void onRenderChat(GuiGraphics guiGraphics, int tickCount, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        if (Config.getCustomChat()) {
            ci.cancel();
            //System.out.println("TestChatRender");

            int i = this.getLinesPerPage();
            int j = this.trimmedMessages.size();
            if (j > 0) {
                this.minecraft.getProfiler().push("chat");
                float f = (float)this.getScale();
                int k = Mth.ceil((float)this.getWidth() / f);
                int l = guiGraphics.guiHeight();
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(f, f, 1.0F);
                guiGraphics.pose().translate(4.0F, 0.0F, 1005.0F);
                int i1 = Mth.floor((float)(l - 40) / f);
                int j1 = this.getMessageEndIndexAt(this.screenToChatX((double)mouseX), this.screenToChatY((double)mouseY));
                double d0 = this.minecraft.options.chatOpacity().get() * 0.9F + 0.1F;
                double d1 = this.minecraft.options.textBackgroundOpacity().get();
                double d2 = this.minecraft.options.chatLineSpacing().get();
                int k1 = this.getLineHeight();
                int l1 = (int)Math.round(-8.0 * (d2 + 1.0) + 4.0 * d2);
                int i2 = 0;

                for (int j2 = 0; j2 + this.chatScrollbarPos < this.trimmedMessages.size() && j2 < i; j2++) {
                    int iterationIndex = j2 + this.chatScrollbarPos;
                    int k2 = j2 + this.chatScrollbarPos;
                    GuiMessage.Line guimessage$line = this.trimmedMessages.get(k2);
                    if (guimessage$line != null) {
                        int l2 = tickCount - guimessage$line.addedTime();
                        if (l2 < 200 || focused) {
                            double d3 = focused ? 1.0 : getTimeFactor(l2);
                            int j3 = (int)(255.0 * d3 * d0);
                            int k3 = (int)(255.0 * d3 * d1);
                            i2++;
                            if (j3 > 3) {
                                int l3 = 0;
                                int i4 = i1 - j2 * k1;
                                int j4 = i4 + l1;
                                //guiGraphics.fill(-4, i4 - k1, 0 + k + 4 + 4, i4, k3 << 24);
                                GuiMessageTag guimessagetag = guimessage$line.tag();

                                // Draw Chat Message

                                int widgetLeft = MinescapeAddons.getInstance().resizableClassic.getChatWidget().getLeftSide();
                                int widgetBottom = MinescapeAddons.getInstance().resizableClassic.getChatWidget().getBottom();

                                // original scaled/chat-local Y
                                int j4Scaled = i4 + l1;
                                // convert scaled/chat-local Y to widget-local Y by aligning i1 (the scaled baseline) with widgetBottom
                                j4 = ((widgetBottom - i1 + j4Scaled) - this.minecraft.font.lineHeight * 2) - 3;

                                // draw tag icon / message using widget-local coordinates
                                if (guimessagetag != null) {
                                    int k4 = guimessagetag.indicatorColor() | j3 << 24;
                                    //guiGraphics.fill(widgetLeft - 4, j4 - k1, widgetLeft - 2, j4, k4);
                                    if (k2 == j1 && guimessagetag.icon() != null) {
                                        int l4 = this.getTagIconLeft(guimessage$line);
                                        int i5 = j4 + 9;
                                        this.drawTagIcon(guiGraphics, l4, i5, guimessagetag.icon());
                                    }
                                }

                                guiGraphics.pose().pushPose();
                                guiGraphics.pose().translate(0.0F, 0.0F, 50.0F);
                                // Draw Chat Message at widget-local coordinates
                                guiGraphics.drawString(this.minecraft.font, guimessage$line.content(), widgetLeft + 8, j4, 16777215 + (j3 << 24));
                                guiGraphics.pose().popPose();
                            }
                        }
                    }
                }

                long j5 = this.minecraft.getChatListener().queueSize();
                if (j5 > 0L) {
                    int k5 = (int)(128.0 * d0);
                    int i6 = (int)(255.0 * d1);
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate(0.0F, (float)i1, 0.0F);
                    guiGraphics.fill(-2, 0, k + 4, 9, i6 << 24);
                    guiGraphics.pose().translate(0.0F, 0.0F, 50.0F);
                    guiGraphics.drawString(this.minecraft.font, Component.translatable("chat.queue", j5), 0, 1, 16777215 + (k5 << 24));
                    guiGraphics.pose().popPose();
                }

                guiGraphics.pose().popPose();
                this.minecraft.getProfiler().pop();
            }
        }
    }
}
