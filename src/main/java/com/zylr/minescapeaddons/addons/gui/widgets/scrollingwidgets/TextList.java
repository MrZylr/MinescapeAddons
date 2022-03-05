package com.zylr.minescapeaddons.addons.gui.widgets.scrollingwidgets;

import com.zylr.minescapeaddons.addons.gui.widgets.AnchorType;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextList extends Widget {

    private List<ITextComponent> textList = new ArrayList<>();
    private int relativeAnchorX;
    private int relativeAnchorY;
    private int scrollIndex;
    private int scrollAmount;
    private int linesVisible;
    private String title;

    public TextList(IWidget parent, String title, AnchorType anchorPointToParent, int relativeAnchorX, int relativeAnchorY, List<ITextComponent> textList, int widgetWidth, int widgetHeight) {
        super();
        this.title = title;

        // DeclareType
        // Setup Config File

        // Setup widget variables from config

        this.parent = parent;
        this.anchorPointToParent = anchorPointToParent;
        this.textList = textList;
        this.scrollIndex = 0;
        this.scrollAmount = 3;
        this.widgetWidth = widgetWidth;
        this.widgetHeight = widgetHeight;
        this.relativeAnchorX = relativeAnchorX;
        this.relativeAnchorY = relativeAnchorY;

        this.linesVisible = widgetHeight/mc.fontRenderer.FONT_HEIGHT;
    }

    public TextList(List<ITextComponent> textList, int widgetWidth, int widgetHeight) {
        super();

        // DeclareType
        // Setup Config File

        // Setup widget variables from config

        this.textList = textList;
        this.scrollIndex = 0;
        this.scrollAmount = 3;
        this.widgetWidth = widgetWidth;
        this.widgetHeight = widgetHeight;

        this.linesVisible = widgetHeight/mc.fontRenderer.FONT_HEIGHT;

    }

    public void setupFromConfig() {

    }

    public void render() {
        // TODO:: Remove Test Code
        this.setBackgroundColor(new Color(0, 0, 0, 0).getRGB());

        FontRenderer font = mc.fontRenderer;


        if (parent != null && anchorPointToParent != null) {
            this.anchorToParent(anchorPointToParent);
        }
        anchorY -= relativeAnchorY;

        if (this.anchorPointToParent == AnchorType.TOP || this.anchorPointToParent == AnchorType.BOTTOM)
            anchorX = this.parent.getLeftSide() + this.widgetWidth/2;

        // Verify scrollIndex
        if (linesVisible >= textList.size())
            scrollIndex = 0;
        else if (scrollIndex > textList.size()-linesVisible)
            scrollIndex = textList.size()-linesVisible;

        // Draw text
        if (textList.size() != 0) {
            for (int i = scrollIndex; i < linesVisible+scrollIndex && i < textList.size(); i++) {
                font.drawString(textList.get(i).getFormattedText(), this.getLeftSide()+2, this.getBottom()-font.FONT_HEIGHT*(i+1-scrollIndex), -1);
            }
        }

        // Render last to keep children layers higher than parent
        this.renderChildren();
    }

    public void scrollUp() {
        boolean canScroll = true;
        // Check if chat is full enough to scroll
        if (linesVisible >= textList.size()) {
            scrollIndex = 0;
            canScroll = false;
        }
        // Check if scrollIndex would be past the top of the list
        if (scrollIndex > textList.size()-linesVisible) {
            scrollIndex = textList.size() - linesVisible;
            canScroll = false;
        }

        if (canScroll)
            scrollIndex += scrollAmount;
    }

    public void scrollDown() {
        if (scrollIndex < 3)
            scrollIndex = 0;
        else
            scrollIndex -= scrollAmount;
    }

    @Nullable
    public ITextComponent getTextComponent(double mouseX, double mouseY) {
        FontRenderer font = mc.fontRenderer;
        int fontHeight = font.FONT_HEIGHT;

        if (this.isHovered()) {
            System.out.println("Within chat boundaries");
            // Decide which line the mouse is hovering based on the mouseY position
            int roundedMouseY = MathHelper.floor(mouseY);
            int lineNumber = 0;
            for (int i = 0; i <= linesVisible; i++) {
                if (this.getBottom()-fontHeight-(fontHeight*i) < roundedMouseY) {
                    lineNumber = i;
                    break;
                }
            }
            System.out.println(lineNumber);

            // Decide how far into the line the mouse is hovering based on the mouseX position
            ITextComponent textComponent = textList.get(lineNumber+scrollIndex);

            int chatLineWidth = this.getLeftSide() + 2;

            for (ITextComponent iTextComponent : textComponent) {
                chatLineWidth += font.getStringWidth(RenderComponentsUtil.removeTextColorsIfConfigured(((StringTextComponent)iTextComponent).getText(), false));
                System.out.println(chatLineWidth + ", " + mouseX);
                if (chatLineWidth > mouseX) {
                    System.out.println(iTextComponent);
                    return iTextComponent;
                }
            }
        }else
            System.out.println("Outside chat boundaries");

        return null;
    }

    public void addText(ITextComponent textComponent) {
        this.textList.add(0, textComponent);
        while (textList.size() > 200) {
            textList.remove(textList.size() - 1);
        }
    }

    public void removeLastLine() {
        this.textList.remove(textList.size() - 1);
    }

    public int getScrollAmount() {
        return scrollAmount;
    }

    public void setScrollAmount(int scrollAmount) {
        this.scrollAmount = scrollAmount;
    }

    public List<ITextComponent> getStringList() {
        return textList;
    }

    public void setStringList(List<ITextComponent> textList) {
        this.textList = textList;
    }

    public String getTitle() { return title; }
}
