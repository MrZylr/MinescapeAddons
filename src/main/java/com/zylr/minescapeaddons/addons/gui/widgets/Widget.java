package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//  Widgets implement IWidget and not extending this class:
//      ChatWidget
public class Widget implements IWidget {

    public Properties config = null;
    protected IWidget parent;
    protected AnchorType anchorPointToParent;
    protected List<IWidget> children = new ArrayList<>();
    protected int backgroundColor;

    // Position variables
    protected double relativeAnchorX;
    protected double relativeAnchorY;
    protected int anchorX;
    protected int anchorY;
    protected int widgetWidth;
    protected int widgetHeight;
    protected int width;
    protected int height;
    protected WidgetType type;
    protected boolean visible;
    List<Button> buttons;


    // TODO:: Change anchor from being center based to the matching quadrant on the screen the widget is currently in
    // Implement a top/bottom & left/right check

    // Minecraft variables
    public Minecraft mc = Minecraft.getInstance();
    public MainWindow window = mc.getMainWindow();

    public Widget() {
        this.type = null;
        this.width = window.getScaledWidth();
        this.height = window.getScaledHeight();
        this.visible = true;
        buttons = new ArrayList<>();
    }

    // Config File
    public void setupConfig() {
        File configFile = this.getType().getFile();
        Properties chatConfig = new Properties();
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
                // Load defaults for config files
                InputStream chatConfigInput = this.getClass().getClassLoader().getResourceAsStream(this.getType().getDefaultFile());
                chatConfig.load(chatConfigInput);
                // Save default config files
                OutputStream chatConfigOutput = new FileOutputStream(this.getType().getFile().getPath());
                chatConfig.store(chatConfigOutput, null);
            }
            InputStream input = new FileInputStream(this.getType().getFile().getPath());
            this.config = new Properties();
            // Load config
            this.config.load(input);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveConfig() {
        try (OutputStream output = new FileOutputStream(this.getType().getFile().getPath())) {
            // Save the config
            this.config.store(output, null);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Check if this widget is being hovered by the mouse or not
    public boolean isHovered() {
        double pointerX = mc.mouseHelper.getMouseX()/window.getGuiScaleFactor();
        double pointerY = mc.mouseHelper.getMouseY()/window.getGuiScaleFactor();

        if (pointerX > getLeftSide() && pointerX < getRightSide() && pointerY > getTop() && pointerY < getBottom())
            return true;
        return false;
    }

    public void render() {
        // Setup anchor points based on the percentage screen location
        relativeXToPixelX();
        relativeYToPixelY();
        // Fix the anchor point if it goes off screen
        fixAnchors();
        // Render background image
        this.drawBackground();
        if (parent != null && anchorPointToParent != null) {
            this.anchorToParent(anchorPointToParent);
        }
    }

    private void relativeXToPixelX() {
        anchorX = (int) (this.mc.getMainWindow().getScaledWidth()*this.relativeAnchorX);
    }

    private void relativeYToPixelY() {
        anchorY = (int) (this.mc.getMainWindow().getScaledHeight()*this.relativeAnchorY);
    }

    public void pixelXToRelativeX(int xPos) {
        System.out.println("Xpos: " + xPos);
        System.out.println("Screen Width: " + this.mc.getMainWindow().getScaledWidth());
        relativeAnchorX = (double) xPos/(double)this.mc.getMainWindow().getScaledWidth();
        System.out.println("Relative X: " + relativeAnchorX);
        relativeXToPixelX();
    }

    public void pixelYToRelativeY(int yPos) {
        relativeAnchorY = (double)yPos/(double)this.mc.getMainWindow().getScaledHeight();
        relativeYToPixelY();
    }

    public void renderChildren() {
        for (IWidget child : children) {
            child.render();
        }
    }

    public void drawBackground() {
        GuiUtils.drawGradientRect(0, this.getLeftSide(), this.getTop(), this.getRightSide(), this.getBottom(), this.backgroundColor, this.backgroundColor);
    }

    public List<Button> getButtons() {
        buttons.clear();
        return buttons;
    }

    public boolean isLeftHalf() {
        if (anchorX < window.getScaledWidth()/2)
            return true;
        return false;
    }

    public boolean isTopHalf() {
        if (anchorY < window.getScaledHeight()/2)
            return true;
        return false;
    }

    public void fixAnchors() {
        if (getRightSide() > window.getScaledWidth())
            anchorX = window.getScaledWidth() - widgetWidth/2;
        if (getLeftSide() < 0)
            anchorX = widgetWidth/2;
        if (getBottom() > window.getScaledHeight())
            anchorY = window.getScaledHeight() - widgetHeight/2;
        if (getTop() < 0 )
            anchorY = widgetHeight/2;
    }

    public int getLeftSide() {
        int halfWidth = widgetWidth/2;
        return anchorX - halfWidth;
    }

    public int getRightSide() {
        int halfWidth = widgetWidth/2;
        return anchorX + halfWidth;
    }

    public int getTop() {
        int halfHeight = widgetHeight/2;
        return anchorY - halfHeight;
    }

    public int getBottom() {
        int halfHeight = widgetHeight/2;
        return anchorY + halfHeight;
    }

    // This method will be useful for anchoring widgets together without knowing the parent or anchortype beforehand
    public void anchorToParent(AnchorType anchorPointToParent, IWidget parent) {
        this.parent = parent;
        this.anchorPointToParent = anchorPointToParent;
        this.anchorToParent(anchorPointToParent);
    }

    public void anchorToParent(AnchorType anchorPointToParent) {
        switch (anchorPointToParent) {
            case LEFT:
                this.anchorX = this.parent.getLeftSide() - this.widgetWidth/2;
                break;
            case TOP:
                this.anchorY = this.parent.getTop() - this.widgetHeight/2;
                break;
            case RIGHT:
                this.anchorX = this.parent.getRightSide() + this.widgetWidth/2;
                break;
            case BOTTOM:
                this.anchorY = this.parent.getBottom() + this.widgetHeight/2;
                break;
            case CENTER:
                this.anchorX = this.parent.getAnchorX();
                this.anchorY = this.parent.getAnchorY();
                break;
        }
    }

    public void unAnchor() {
        parent = null;
        anchorPointToParent = null;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getAnchorX() {
        return anchorX;
    }

    public double getRelativeAnchorX() { return this.relativeAnchorX; }

    public void setAnchorX(int anchorX) {
        this.anchorX = anchorX;
    }

    public void setRelativeAnchorX(double relativeAnchorX) { this.relativeAnchorX = relativeAnchorX; }

    public int getAnchorY() {
        return anchorY;
    }

    public double getRelativeAnchorY() { return this.relativeAnchorY; }

    public void setAnchorY(int anchorY) {
        this.anchorY = anchorY;
    }

    public void setRelativeAnchorY(double relativeAnchorY) { this.relativeAnchorY = relativeAnchorY; }

    public int getWidgetHeight() { return widgetHeight; }

    public int getWidgetWidth() { return widgetWidth; }

    public WidgetType getType() { return type; }

    public Properties getConfig() { return config;}

    public List<IWidget> getChildren() { return children; }

    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) { this.visible = visible; }

    public AnchorType getAnchorPointToParent() { return anchorPointToParent; }

    public void setAnchorPointToParent(AnchorType anchorPointToParent) { this.anchorPointToParent = anchorPointToParent; }
}