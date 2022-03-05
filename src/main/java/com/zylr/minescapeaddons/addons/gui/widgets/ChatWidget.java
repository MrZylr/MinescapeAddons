package com.zylr.minescapeaddons.addons.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.corba.se.impl.encoding.CodeSetConversion;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import com.zylr.minescapeaddons.addons.gui.widgets.scrollingwidgets.TextList;
import javafx.scene.control.ButtonType;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ChatWidget extends Widget {

    // Class variables
    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/background.png");
    private static final ResourceLocation BUTTON_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/button.png");
    private static final ResourceLocation BUTTON_HOVERED_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/button_hovered.png");
    private static final ResourceLocation BUTTON_SELECTED_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/button_selected.png");
    private static final ResourceLocation BUTTON_SELECTED_HOVERED_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/button_selected_hovered.png");
    private static final ResourceLocation BUTTON_REPORT_HOVERED_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/report_button_hovered.png");
    private static final ResourceLocation BUTTON_REPORT_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/report_button.png");
    private static final ResourceLocation BUTTONS_BACKGROUND_STONES_LOCATION = new ResourceLocation(Main.ID, "textures/gui/chatbox/buttons_background_stones.png");
    private static final String DEFAULTSTRING = "Press Enter to Chat";

    private List<ChatLine> chatLines = new ArrayList<>();
    private Properties config;
    private int highlightColor;
    private int numberOfChatLines;
    private ButtonType currentChat;
    private int chatButtonsTop;
    private float transparency;
    private float red;
    private float green;
    private float blue;
    private int rainbowColorIncrement;
    private String textField;
    private String textFieldPrefix;
    private TextList mainChat;
    private TextList splitChat;
    private boolean isPrivateSplit;

    // Minecraft variables
    Minecraft mc = Minecraft.getInstance();
    MainWindow window = mc.getMainWindow();


    public ChatWidget() {
        super();
        this.type = WidgetType.CHAT;
        this.setupConfig();
        this.width = window.getScaledWidth();
        this.height = window.getScaledHeight();
        this.buttons = new ArrayList<>();
        this.currentChat = ButtonType.ALL;
        this.textFieldPrefix = "";
        this.isPrivateSplit = true;
        this.setVisible(ModConfiguration.CLIENT.osrsChat.get());

        this.numberOfChatLines = 12;

        // Get properties file
        config = this.getConfig();
        // Grab position/color/settings from properties file
        this.anchorX = Integer.parseInt(config.getProperty("x"));
        this.anchorY = Integer.parseInt(config.getProperty("y"));

        // Setup colors
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor"));
//        this.highlightColor = Integer.parseInt(config.getProperty("highlightColor"));

        // set widget width & height
        widgetWidth = 350;
        widgetHeight = 108;
        chatButtonsTop = this.getBottom()-15;



        // Setup font color (text for chat tabs)

        // Setup position

        textField = "";

        this.rainbowColorIncrement = 1;
    }

    public void render() {
        super.render();
        // TODO:: Remove Test Code
        highlightColor = new Color(85, 0, 67, 50).getRGB();
        this.setBackgroundColor(new Color(211, 0, 21, 0).getRGB());
        this.transparency = 0.5F;
//        this.rainbowColor();
        red = 1F;
        green = 1F;
        blue = 1F;
        rainbowColorIncrement = 1;

        textFieldPrefix = mc.player.getName().getFormattedText();

        widgetWidth = 360;
        widgetHeight = 150;
        chatButtonsTop = this.getBottom() -15;

        FontRenderer font = mc.fontRenderer;
        int fontHeight = font.FONT_HEIGHT;

        this.drawBackground();
        this.renderBackgroundImage();
        this.buildChatButtons();
        if (mc.currentScreen == null)
            font.drawString(DEFAULTSTRING, this.getTextFieldX(), this.getTextFieldY(), -1);

        int mouseX = (int)(mc.mouseHelper.getMouseX()/mc.getMainWindow().getGuiScaleFactor());
        int mouseY = (int)(mc.mouseHelper.getMouseY()/mc.getMainWindow().getGuiScaleFactor());
        float partialTicks = mc.getRenderPartialTicks();

        createTextList();

        numberOfChatLines = widgetHeight/fontHeight;

        font.drawString(textFieldPrefix +": ", this.getLeftSide()+7, this.chatButtonsTop-16, Color.black.getRGB());

        float p1 = transparency-(transparency*0.2F);
        int chatSeparateTransparency = (int)(255*p1);
        int chatSeparatorColor = new Color(128, 118, 96, chatSeparateTransparency).getRGB();

        GuiUtils.drawGradientRect(1, this.getLeftSide()+5, this.chatButtonsTop-18,
                this.getRightSide()-5, this.chatButtonsTop-17, chatSeparatorColor, chatSeparatorColor);

        // Render last to keep children layers higher than parent
        this.renderChildren();
    }

    private void renderBackgroundImage() {
        // Build rectangle
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.color4f(red, green, blue, transparency);
        drawImageOnChat(this.getLeftSide(), this.getTop(), this.getRightSide(), chatButtonsTop, BACKGROUND_LOCATION);
        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();
    }

    private void createTextList() {
        // Create TextList child widget
        boolean hasTextList = false;
        boolean hasSplitPrivateList = false;

        for (IWidget child : children) {
            if (child instanceof TextList) {
                if (((TextList) child).getTitle().equalsIgnoreCase("Main Chat"))
                    hasTextList = true;
                else if (((TextList) child).getTitle().equalsIgnoreCase("Split Private Chat"))
                    hasSplitPrivateList = true;
            }

        }
        if (!hasTextList) {
            mainChat = new TextList(this, "Main Chat", AnchorType.CENTER, 0, 13, new ArrayList<>(), 350, 108);
            children.add(mainChat);
        }
        if (!hasSplitPrivateList) {
            splitChat = new TextList(this, "Split Private Chat", AnchorType.TOP, 0, 0, new ArrayList<>(), 150, 60);
            children.add(splitChat);
        }
    }

    private void drawHighlight() {
        GuiUtils.drawGradientRect(10, this.getLeftSide(), this.getTop(), this.getRightSide(), this.getBottom(), this.highlightColor, this.highlightColor);
    }

    public void addTextComponent(ITextComponent iTextComponent) {
        boolean isMessagePM = false;

        /*
        Sent to:
        § 7 § 7 [ § r § a § a w § r § 7 § 7 ]   § r § c § c     - >         § r § 7 § n § 7 § n             _ Z o m b i e _ _ § r :   a b s o l u t e   l m f a o

        From:
        § 7 § 7 [ § r § a § a w § r § 7 § 7 ]                   - >         § r § 7 § n § 7 § n             _ Z o m b i e _ _ § r :   y o u   m a d e   h i m   l e a v e
         */
/*

        for (ITextComponent textComponent : iTextComponent) {

        }
        // Check if correct length to match beginning of private chat message

        if (iTextComponent.getFormattedText().length() >= 19) {
            // Grab substring to test if private message
            String substring1 = iTextComponent.getFormattedText().substring(0, 19);
            if (substring1.equalsIgnoreCase("§7§7[§r§a§aw§r§7§7]")) {
                // Test if message is being received or sent
                String substring2 = iTextComponent.getFormattedText().substring(20, 28);
                String pm = iTextComponent.getFormattedText().substring(29);
                if (substring2.equalsIgnoreCase("§r§c§c->")) {
                    // Is a sent message
                    ITextComponent text = new StringTextComponent("To " + pm);
                    text.setStyle(text.getStyle());
                    this.splitChat.addText(text);
                }else {
                    // Is a received message
                    ITextComponent text = new StringTextComponent("From " + pm);
                    this.splitChat.addText(text);
                }
                if (!isPrivateSplit)
                    this.mainChat.addText(iTextComponent);
                return;
            }
        }*/


        List<ITextComponent> iTextComponents = RenderComponentsUtil.splitText(iTextComponent, this.getWidgetWidth()-10, mc.fontRenderer, false, false);
        for (ITextComponent textComponent : iTextComponents) {
            // This gets the first 5 characters and checks for the built in buttons in chat and doesnt add them
            String s = "";
            if (textComponent.getFormattedText().length() > 5)
                s = textComponent.getFormattedText().substring(0, 5);
            if (!s.equalsIgnoreCase("§9§9|")) {

                this.mainChat.addText(textComponent);
                System.out.println(textComponent.getFormattedText());
            }
        }
    }

    private void buildChatButtons() {
        FontRenderer font = mc.fontRenderer;

        // Draw buttons background stones
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.color4f(red, green, blue, transparency);
        drawImageOnChat(this.getLeftSide(), this.getBottom()-15, this.getRightSide(), this.getBottom(), BUTTONS_BACKGROUND_STONES_LOCATION);
        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();

        int buttonPadding = 3;
        int buttonLeftSide = 0;
        int paddingSpace = buttonPadding*16;
        int buttonWidth = (this.widgetWidth-paddingSpace)/9;

        ButtonType[] buttons = new ButtonType[]{ButtonType.ALL, ButtonType.GAME, ButtonType.PUBLIC, ButtonType.PRIVATE,
                ButtonType.CLAN, ButtonType.TRADE, ButtonType.OTHER, ButtonType.REPORT};

        for (ButtonType button : buttons) {
            // Add button padding before button
            buttonLeftSide += buttonPadding;

            // Create button boundaries
            button.setLeftSide(this.getLeftSide()+buttonLeftSide);
            button.setRightSide(this.getLeftSide()+buttonLeftSide+buttonWidth);
            if (button == ButtonType.REPORT)
                button.setRightSide(this.getLeftSide()+buttonLeftSide+buttonWidth*2+buttonPadding*2);
            button.setTop(chatButtonsTop+1);
            button.setBottom(this.getBottom()-1);

            // Determine the button resource to be used
            ResourceLocation buttonResource = BUTTON_LOCATION;
            if (currentChat == button) {
                buttonResource = BUTTON_SELECTED_LOCATION;
            }
            if (button == ButtonType.REPORT)
                buttonResource = BUTTON_REPORT_LOCATION;

            // Get mouse postion
            int mouseX = (int)(mc.mouseHelper.getMouseX()/mc.getMainWindow().getGuiScaleFactor());
            int mouseY = (int)(mc.mouseHelper.getMouseY()/mc.getMainWindow().getGuiScaleFactor());

            if (button.isHovered(mouseX, mouseY)) {
                if (currentChat == button)
                    buttonResource = BUTTON_SELECTED_HOVERED_LOCATION;
                else if (button == ButtonType.REPORT)
                    buttonResource = BUTTON_REPORT_HOVERED_LOCATION;
                else
                    buttonResource = BUTTON_HOVERED_LOCATION;
            }
            // Draw button
            RenderSystem.enableAlphaTest();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.defaultAlphaFunc();
            RenderSystem.color4f(red, green, blue, transparency);
            drawImageOnChat(button.getLeftSide(), button.getTop(), button.getRightSide(), button.getBottom(), buttonResource);
            RenderSystem.disableAlphaTest();
            RenderSystem.disableBlend();


            // Write text on button
            int buttonHeight = (button.getBottom() - button.getTop())/2;
            int buttonCenterX = button.getLeftSide() + buttonWidth/2;
            if (button == ButtonType.REPORT)
                buttonCenterX = button.getLeftSide() +buttonWidth+buttonPadding;
            int buttonCenterY = button.getBottom()-buttonHeight;
            int textWidth = font.getStringWidth(button.getName());
            font.drawString(button.getName(), buttonCenterX-textWidth/2, buttonCenterY-font.FONT_HEIGHT/2, -1);

            // Add the button padding after button
            buttonLeftSide += buttonPadding+buttonWidth;
        }
    }

    private void rainbowColor() {
        if (red == 1.0F && blue == 1.0F && green == 1.0F) {
            red = 0;
            blue = 0;
            green = 0;
        }
        switch (rainbowColorIncrement) {
            case 1:
                if (red < 1.0F)
                    red += 0.01F;
                else
                    rainbowColorIncrement = 2;
                break;
            case 2:
                if (green < 1.0F && red >= 1.0F)
                    green += 0.01F;
                else if (green >= 1.0F && red >= 0.0F)
                    red -= 0.01F;
                else
                    rainbowColorIncrement = 3;
                break;
            case 3:
                if (blue < 1.0F && green >= 1.0F)
                    blue += 0.01F;
                else if (blue > 1.0F && green >= 0.0F)
                    green -= 0.01F;
                else if (blue >= 1.0F && red < 1.0F)
                    red += 0.01F;
                else if (blue >= 0.0F)
                    blue -= 0.01F;
                else
                    rainbowColorIncrement = 1;
                break;
        }
    }

    public List<Button> getButtons() {
        buttons.clear();
        return buttons;
    }

    public void clearChat() {
        this.chatLines.clear();
    }

    public List<ChatLine> getChatLines() {
        return chatLines;
    }

    public ButtonType getCurrentChat() {
        return currentChat;
    }

    public void setCurrentChat(ButtonType currentChat) {
        this.currentChat = currentChat;
    }

    private void drawImageOnChat(int leftSide, int top, int rightSide, int bottom, ResourceLocation resource) {
        // Setup for building
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        // Scale the gui
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();

        Minecraft.getInstance().textureManager.bindTexture(resource);


        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        // Build rectangle
        bufferBuilder.pos(leftSide, top, -90.0D).tex(0.0F, 1.0F).endVertex();
        bufferBuilder.pos(leftSide, bottom, -90.0D).tex(0.0F, 0.0F).endVertex();
        bufferBuilder.pos(rightSide, bottom, -90.0D).tex(1.0F, 0.0F).endVertex();
        bufferBuilder.pos(rightSide, top, -90.0D).tex(1.0F, 1.0F).endVertex();
        tessellator.draw();

        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }
        //this.getLeftSide()+7, this.chatButtonsTop-16
    public int getTextFieldX() {
        return this.getLeftSide()+7 + mc.fontRenderer.getStringWidth( textFieldPrefix + ": ");
    }

    public int getTextFieldY() {
        return this.chatButtonsTop-16;
    }

    public String getTextFieldString() {
        return textField;
    }

    public void setTextFieldString(String textIn) {
        this.textField = textIn;
    }

    public enum ButtonType {
        ALL("All", "/chat tab all"),
        GAME("Game", "/chat tab game"),
        PUBLIC("Public", "/chat tab public"),
        PRIVATE("Private", "/chat tab private"),
        CLAN("Clan", "/chat tab clan"),
        TRADE("Trade", "/chat tab trade"),
        OTHER("Other", "/chat tab other"),
        REPORT("Report", "/report");

        private String name;
        private String command;
        private int leftSide;
        private int top;
        private int rightSide;
        private int bottom;
        private Minecraft mc = Minecraft.getInstance();

        ButtonType(String name, String command) {
            this.name = name;
            this.command = command;
            leftSide = 0;
            top = 0;
            rightSide = 0;
            bottom = 0;
        }

        public boolean isHovered(double mouseX, double mouseY) {
            if (mouseX > leftSide && mouseX < rightSide && mouseY > top && mouseY < bottom)
                return true;
            return false;
        }

        public void runCommand() {
            mc.player.sendChatMessage(command);
        }

        public String getName() { return name; }

        public int getLeftSide() { return leftSide; }

        public void setLeftSide(int leftSide) { this.leftSide = leftSide; }

        public int getTop() { return top; }

        public void setTop(int top) { this.top = top; }

        public int getRightSide() { return rightSide; }

        public void setRightSide(int rightSide) { this.rightSide = rightSide; }

        public int getBottom() { return bottom; }

        public void setBottom(int bottom) { this.bottom = bottom; }
    }
}
