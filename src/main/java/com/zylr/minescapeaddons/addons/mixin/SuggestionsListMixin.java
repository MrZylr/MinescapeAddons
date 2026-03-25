package com.zylr.minescapeaddons.addons.mixin;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.suggestion.Suggestion;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.world.phys.Vec2;
import net.minecraft.client.renderer.Rect2i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CommandSuggestions.SuggestionsList.class)
public abstract class SuggestionsListMixin {

    @Shadow @Final private CommandSuggestions this$0;
    @Shadow @Final private Rect2i rect;
    @Shadow @Final private List<Suggestion> suggestionList;
    @Shadow private int offset;
    @Shadow private int current;
    @Shadow private Vec2 lastMouse;

    @Shadow protected abstract void select(int index);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(GuiGraphics guiGraphics, int mouseX, int mouseY, CallbackInfo ci) {
        if (Config.getCustomChat()) {
            ci.cancel();

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 2000);
            CommandSuggestionsAccessor outer = (CommandSuggestionsAccessor) this.this$0;

            int height = MinescapeAddons.getInstance().resizableClassic.getChatWidget().getBottom() -
                    Minecraft.getInstance().font.lineHeight - 9 - (Math.min(suggestionList.size(), 10) * 12);

            int i = Math.min(this.suggestionList.size(), outer.minescapeaddons$getSuggestionLineLimit());
            int j = -5592406;
            boolean flag = this.offset > 0;
            boolean flag1 = this.suggestionList.size() > this.offset + i;
            boolean flag2 = flag || flag1;
            boolean flag3 = this.lastMouse.x != (float) mouseX || this.lastMouse.y != (float) mouseY;
            if (flag3) {
                this.lastMouse = new Vec2((float) mouseX, (float) mouseY);
            }

            if (flag2) {
                guiGraphics.fill(this.rect.getX(), height - 1, this.rect.getX() + this.rect.getWidth(), height, outer.minescapeaddons$getFillColor());
                guiGraphics.fill(this.rect.getX(), height + this.rect.getHeight(), this.rect.getX() + this.rect.getWidth(), height + this.rect.getHeight() + 1, outer.minescapeaddons$getFillColor());

                if (flag) {
                    for (int k = 0; k < this.rect.getWidth(); ++k) {
                        if (k % 2 == 0) {
                            guiGraphics.fill(this.rect.getX() + k, height - 1, this.rect.getX() + k + 1, height, -1);
                        }
                    }
                }

                if (flag1) {
                    for (int i1 = 0; i1 < this.rect.getWidth(); ++i1) {
                        if (i1 % 2 == 0) {
                            guiGraphics.fill(this.rect.getX() + i1, height + this.rect.getHeight(), this.rect.getX() + i1 + 1, height + this.rect.getHeight() + 1, -1);
                        }
                    }
                }
            }

            boolean flag4 = false;

            for (int l = 0; l < i; ++l) {
                Suggestion suggestion = this.suggestionList.get(l + this.offset);
                guiGraphics.fill(this.rect.getX(), height + 12 * l, this.rect.getX() + this.rect.getWidth(), height + 12 * l + 12, outer.minescapeaddons$getFillColor());

                if (mouseX > this.rect.getX() && mouseX < this.rect.getX() + this.rect.getWidth() && mouseY > height + 12 * l && mouseY < height + 12 * l + 12) {
                    if (flag3) {
                        this.select(l + this.offset);
                    }
                    flag4 = true;
                }

                guiGraphics.drawString(outer.minescapeaddons$getFont(), suggestion.getText(), this.rect.getX() + 1, height + 2 + 12 * l, l + this.offset == this.current ? -256 : j);
            }

            if (flag4) {
                Message message = this.suggestionList.get(this.current).getTooltip();
                if (message != null) {
                    guiGraphics.renderTooltip(outer.minescapeaddons$getFont(), ComponentUtils.fromMessage(message), mouseX, mouseY);
                }
            }

            guiGraphics.pose().popPose();
        }
    }
}
