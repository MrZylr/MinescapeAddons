package com.zylr.minescapeaddons.addons.gui.widgets.chat;

import com.zylr.minescapeaddons.addons.gui.widgets.buttons.AbstractRightClickableImageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ChatTabButton extends AbstractRightClickableImageButton {
    public ChatTabButton(ResourceLocation image, Component text, int textColor, int x, int y, int width, int height) {
        super(image, text, textColor, x, y, width, height);
    }
}
