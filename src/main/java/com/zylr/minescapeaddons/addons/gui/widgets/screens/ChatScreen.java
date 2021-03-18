package com.zylr.minescapeaddons.addons.gui.widgets.screens;

import com.zylr.minescapeaddons.addons.gui.hud.Hud;
import com.zylr.minescapeaddons.addons.gui.overwrites.ModCommandSuggestion;
import com.zylr.minescapeaddons.addons.gui.widgets.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.scrollingwidgets.TextList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import org.lwjgl.glfw.GLFW;

public class ChatScreen extends ModScreen {

    private ChatWidget chat;
    private TextList textList;
    private TextList splitChat;
    private TextFieldWidget textField;
    private boolean openCommand, openAltKey;
    private ModCommandSuggestion commandSuggestionHelper;

    public ChatScreen (Hud hud, boolean openCommand, boolean openAltKey) {
        super(new StringTextComponent("Chat Screen"));

        this.openCommand = openCommand;
        this.openAltKey = openAltKey;

        this.widgetsFromHud(hud);
        for (IWidget widget : widgets) {
            if (widget instanceof ChatWidget) {
                chat = (ChatWidget) widget;
                for (IWidget child : chat.getChildren()) {
                    if (child instanceof TextList) {
                        if (((TextList) child).getTitle().equalsIgnoreCase("Main Chat"))
                            textList = (TextList) child;
                        else if (((TextList) child).getTitle().equalsIgnoreCase("Split Private Chat"))
                            splitChat = (TextList) child;
                    }
                }
            } else {
                System.out.println("NO CHAT PRESENT");
            }
        }

    }


    public void init() {
        this.minecraft.keyboardListener.enableRepeatEvents(true);

        textField = new TextFieldWidget(mc.fontRenderer, this.chat.getTextFieldX(),
                this.chat.getTextFieldY(), this.chat.getRightSide()-this.chat.getTextFieldX()-5, 12, chat.getTextFieldString());

        textField.setMaxStringLength(256);
        textField.setEnableBackgroundDrawing(false);
        textField.setFocused2(true);
        if (openCommand)
            textField.setText("/");
        else
            textField.setText(chat.getTextFieldString());
        textField.setResponder(this::getResponder);

        this.children.add(textField);

        this.setFocusedDefault(textField);
        this.commandSuggestionHelper = new ModCommandSuggestion(this.minecraft, this, this.textField, this.font, false, false, 10, 10, false, -805306368, this.chat);
        this.commandSuggestionHelper.init();
        this.setFocusedDefault(this.textField);
    }

    private void getResponder(String string) {
        String s = this.textField.getText();
        this.commandSuggestionHelper.func_228124_a_(!s.equals(chat.getTextFieldString()));
        this.commandSuggestionHelper.init();
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        textField.render(p_render_1_, p_render_2_, p_render_3_);
        this.commandSuggestionHelper.render(p_render_1_, p_render_2_);
        if (openAltKey)
            if (!textField.getText().isEmpty()) {
                textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                openAltKey = false;
            }
            else {
                System.out.println("is empty");
                openAltKey = false;
                // TODO:: canifis farm patch not lag reducing
            }
    }

    @Override
    public void tick() {
        super.tick();
//        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//        textField.render((int)mc.mouseHelper.getMouseX(), (int)mc.mouseHelper.getMouseY(), mc.getRenderPartialTicks());
        textField.tick();
    }

    @Override
    public void removed() {
        this.mc.keyboardListener.enableRepeatEvents(false);
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) {
        super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);

        // Determine the list being interacted
        TextList hoveredList = null;
        if (textList.isHovered())
            hoveredList = textList;
        else if (splitChat.isHovered())
            hoveredList = textList;

        if (hoveredList != null) {
            System.out.println(hoveredList.getTextComponent(mouseX, mouseY));
            ITextComponent textComponent = hoveredList.getTextComponent(mouseX, mouseY);
            if (textComponent != null)
                if (textComponent.getStyle() != null)
                    if (textComponent.getStyle().getClickEvent() != null) {
                        ClickEvent.Action action = textComponent.getStyle().getClickEvent().getAction();
                        String value = textComponent.getStyle().getClickEvent().getValue();

                        switch (action) {
                            case RUN_COMMAND:
                                System.out.println("running command");
                                System.out.println(value);
                                Minecraft.getInstance().player.sendChatMessage(value);
                                break;
                        }
                    }
        }else {
            // Check if chat button was clicked
            for (ChatWidget.ButtonType button : ChatWidget.ButtonType.values()) {
                System.out.println("checking button press");
                if (button.isHovered(mouseX, mouseY)) {
                    System.out.println("button pressed");
                    if (button != ChatWidget.ButtonType.REPORT)
                        chat.setCurrentChat(button);
                    button.runCommand();
                }
            }
        }


        return true;
    }

    @Override
    public void onClose() {
        super.onClose();

        System.out.println("saving text");
        chat.setTextFieldString(textField.getText());
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double mouseScroll) {
        super.mouseScrolled(mouseX, mouseY, mouseScroll);

        // Determine the list being interacted
        TextList hoveredList = null;
        if (textList.isHovered())
            hoveredList = textList;
        else if (splitChat.isHovered())
            hoveredList = textList;

        if (hoveredList == null)
            return false;

        if (this.commandSuggestionHelper.onScroll(mouseScroll))
            return true;

        if (mouseScroll == 1)
            hoveredList.scrollUp();
        if (mouseScroll == -1)
            hoveredList.scrollDown();
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int p_keyPressed_2_, int p_keyPressed_3_) {
        super.keyPressed(keyCode, p_keyPressed_2_, p_keyPressed_3_);
        // TODO:: Setup tabbing
        System.out.println(keyCode);
        System.out.println(p_keyPressed_2_);
        System.out.println(p_keyPressed_3_);

        if (this.commandSuggestionHelper.onKeyPressed(keyCode, p_keyPressed_2_, p_keyPressed_3_))
            return true;
        else if (keyCode == GLFW.GLFW_KEY_ENTER) {
            if (!textField.getText().isEmpty() & textField.getText() != null) {
                mc.player.sendChatMessage(textField.getText());
                chat.setTextFieldString("");
                mc.player.closeScreen();
            }else
                this.minecraft.displayGuiScreen((Screen)null);
        }

        return true;
    }
}
