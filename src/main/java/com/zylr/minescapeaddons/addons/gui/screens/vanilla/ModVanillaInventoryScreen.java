package com.zylr.minescapeaddons.addons.gui.screens.vanilla;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.overwrites.ModDisplayEffectsScreen;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.screens.ImageButtons;
import com.zylr.minescapeaddons.addons.util.ModHoverChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ModVanillaInventoryScreen extends ModDisplayEffectsScreen<PlayerContainer> implements IRecipeShownListener {
    private static final ResourceLocation RECIPE_BUTTON_TEXTURE = new ResourceLocation("textures/gui/recipe_button.png");
    private static final ResourceLocation CLEAR_BUTTON = new ResourceLocation(Main.ID, "textures/gui/clear.png");
    private static final ResourceLocation TEST_BUTTON = new ResourceLocation(Main.ID, "textures/gui/redidle.png");
//    private String patreonLink = "https://www.patreon.com/Mr_Zylr";
    // Redirecting patreon link to Mod Download
    private String patreonLink = "https://www.patreon.com/Mr_Zylr";
    /** The old x position of the mouse pointer */
    protected float oldMouseX;
    /** The old y position of the mouse pointer */
    protected float oldMouseY;
    protected final RecipeBookGui recipeBookGui = new RecipeBookGui();
    protected boolean removeRecipeBookGui;
    protected boolean widthTooNarrow;
    protected boolean buttonClicked;
    public ModHoverChecker patreonHover;
    private ModConfiguration.Client client = ModConfiguration.CLIENT;
    private int orientation;
    private int currentInventory;
    Minecraft mc = Minecraft.getInstance();

    public ModVanillaInventoryScreen(PlayerEntity player) {
        super(player.container, player.inventory, new TranslationTextComponent("container.crafting"));
        this.passEvents = true;
        patreonHover = null;
        this.orientation = setupOrientation();
        this.currentInventory = Main.getInstance().getStatsPanel().getCurrentScreen();
    }

    public void tick() {
        if (this.minecraft.playerController.isInCreativeMode()) {
            this.minecraft.displayGuiScreen(new CreativeScreen(this.minecraft.player));
        } else {
            this.recipeBookGui.tick();
        }
    }

    protected void init() {
        drawButtons();
        // Set mouse position
        if (ModConfiguration.CLIENT.persistentMouse.get())
            setMousePosition();


        // Button for farming
        int y = Main.getInstance().getStatsPanel().topOfInventory;
        if (ModConfiguration.CLIENT.small.get())
            y *= .75;
        this.addButton( new ImageButton(mc.getMainWindow().getScaledWidth() - 40, mc.getMainWindow().getScaledHeight() -y-25, 20, 20,
                width - 100, 100, width / 2,
                ImageButtons.CLEAR_BUTTON, button -> {
                //Button do stuff
                Minecraft.getInstance().displayGuiScreen(new FarmingTimersScreen());
        }));


        if (this.minecraft.playerController.isInCreativeMode()) {
            this.minecraft.displayGuiScreen(new CreativeScreen(this.minecraft.player));
        } else {
            super.init();
            this.widthTooNarrow = this.width < 379;
            this.recipeBookGui.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.container);
            this.removeRecipeBookGui = true;
            this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
            this.children.add(this.recipeBookGui);
            this.setFocusedDefault(this.recipeBookGui);
            /*this.addButton(new ImageButton(this.guiLeft + 104, this.height / 2 - 22, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE, (p_214086_1_) -> {
                this.recipeBookGui.initSearchBar(this.widthTooNarrow);
                this.recipeBookGui.toggleVisibility();
                this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
                ((ImageButton)p_214086_1_).setPosition(this.guiLeft + 104, this.height / 2 - 22);
                this.buttonClicked = true;
            }));*/
        }
    }

    // Set mouse position
    public void setMousePosition() {
        int x = Main.getInstance().getInv().getMouseX();
        int y = Main.getInstance().getInv().getMouseY();
        GLFW.glfwSetCursorPos(Minecraft.getInstance().getMainWindow().getHandle(), x, y);
    }

    public void drawButtons() {
        this.buttons.clear();
        List<Integer> statsList = calcLocation(252,25,23,25,228,25);
        List<Integer> inventoryList = calcLocation(206,25,23,25,228,25);
        List<Integer> equipmentList = calcLocation(183,25,23,25,228,25);
        if (ModConfiguration.CLIENT.verticalBar.get()){
            statsList.clear();
            statsList = calcLocation(183,148,25,29,228,25);
            inventoryList = calcLocation(183,89,25,29,228,25);
            equipmentList = calcLocation(183,59,25,29,228,25);
        }
        // Settings cog
        // this.addButton(Main.getInstance().getImageButtons().getSettingsCog());

        // Xp tracker button
        //this.addButton(Main.getInstance().getImageButtons().getXpButton());

        if (Main.getInstance().getStatsPanel().getCurrentScreen() == 1) {
            this.addButton(Main.getInstance().getImageButtons().drawLockButton());
        }
        if (Main.getInstance().getStatsPanel().getCurrentScreen() == 1 && ModConfiguration.CLIENT.rsInventory.get()) {
            this.addButton(drawPatreonButton());
        }

        // Stats panel button
        this.addButton(new ImageButton(width - statsList.get(0), height - statsList.get(1), statsList.get(2), statsList.get(3),
                width - statsList.get(4), height - statsList.get(5), width / 2,
                CLEAR_BUTTON,
                button -> {
                    // Button do stuff
                    Main.getInstance().getStatsPanel().setCurrentScreen(0);
                }));


        // Inventory button
        this.addButton(new ImageButton(width - inventoryList.get(0), height - inventoryList.get(1), inventoryList.get(2), inventoryList.get(3),
                width - inventoryList.get(4), height - inventoryList.get(5), width / 2,
                CLEAR_BUTTON,
                button -> {
                    // Button do stuff
                    Main.getInstance().getStatsPanel().setCurrentScreen(1);
                }));


        // Equipment panel button
        this.addButton(new ImageButton(width - equipmentList.get(0), height - equipmentList.get(1), equipmentList.get(2), equipmentList.get(3),
                width - equipmentList.get(4), height - equipmentList.get(5), width / 2,
                CLEAR_BUTTON,
                button -> {
                    // Button do stuff
                    Main.getInstance().getStatsPanel().setCurrentScreen(2);
                }));

        // Skill buttons
        if (client.hotbar.get()) {
            if (Main.getInstance().getStatsPanel().getCurrentScreen() == 0) {
                for (ImageButton skillButton:Main.getInstance().getImageButtons().getSkillButtons()) {
                    this.addButton(skillButton);
                }
            }
        }
    }

    public List<Integer> calcLocation(int originX, int originY, int width, int height, int textX, int textY) {
        List<Integer> locations = new ArrayList<>();
        locations.clear();
        locations.add(originX);
        locations.add(originY);
        locations.add(width);
        locations.add(height);
        locations.add(textX);
        locations.add(textY);
        // Small
        if (ModConfiguration.CLIENT.small.get()) {
            for (int i = 0; i < locations.size(); i++) {
                locations.set(i, (int)((double)locations.get(i)*.75));
            }
        }
        return locations;
    }

    public ImageButton drawPatreonButton() {
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        int x = 59;
        int y = 53;
        int x1 = 50;
        int y1 = 19;
        if (ModConfiguration.CLIENT.verticalBar.get()) {
            x = 84;
            y = 28;
            x1 = 50;
            y1 = 19;
            if (ModConfiguration.CLIENT.small.get()) {
                x = 63;
                y = 21;
                x1 = 37;
                y1 = 14;
            }
        }else if (ModConfiguration.CLIENT.small.get()){
            x = 44;
            y = 40;
            x1 = 37;
            y1 = 14;
        }

        ImageButton patreonbttn = new ImageButton(width - x, height - y, x1, y1,
                100, 100, width / 2,
                ImageButtons.CLEAR_BUTTON, button ->
                Minecraft.getInstance().displayGuiScreen(new ConfirmOpenLinkScreen(this::confirmLink, this.patreonLink, true)));
        if (patreonHover == null) {
            patreonHover = new ModHoverChecker(patreonbttn, 1);
        }
        return patreonbttn;
    }
    private void confirmLink(boolean confirmed) {
        if (confirmed) {
            try {
                Util.getOSType().openURI(new URI(this.patreonLink));
            } catch (URISyntaxException var3) {
                var3.printStackTrace();
            }
        }

        Minecraft.getInstance().displayGuiScreen(this);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 97.0F, 8.0F, 4210752);
    }

    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        if (ModConfiguration.CLIENT.darkInventory.get()) {
            //this.renderBackground();
        }
        this.hasActivePotionEffects = !this.recipeBookGui.isVisible();
        if (this.recipeBookGui.isVisible() && this.widthTooNarrow) {
            this.drawGuiContainerBackgroundLayer(p_render_3_, p_render_1_, p_render_2_);
            this.recipeBookGui.render(p_render_1_, p_render_2_, p_render_3_);
        } else {
            this.recipeBookGui.render(p_render_1_, p_render_2_, p_render_3_);
            super.render(p_render_1_, p_render_2_, p_render_3_);
            this.recipeBookGui.renderGhostRecipe(this.guiLeft, this.guiTop, false, p_render_3_);
        }

        this.renderHoveredToolTip(p_render_1_, p_render_2_);
        this.recipeBookGui.renderTooltip(this.guiLeft, this.guiTop, p_render_1_, p_render_2_);
        this.oldMouseX = (float)p_render_1_;
        this.oldMouseY = (float)p_render_2_;
        this.func_212932_b(this.recipeBookGui);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        drawEntityOnScreen(i + 51, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.minecraft.player);
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity p_228187_5_) {
        float f = (float)Math.atan((double)(mouseX / 40.0F));
        float f1 = (float)Math.atan((double)(mouseY / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)posX, (float)posY, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.multiply(quaternion1);
        matrixstack.rotate(quaternion);
        float f2 = p_228187_5_.renderYawOffset;
        float f3 = p_228187_5_.rotationYaw;
        float f4 = p_228187_5_.rotationPitch;
        float f5 = p_228187_5_.prevRotationYawHead;
        float f6 = p_228187_5_.rotationYawHead;
        p_228187_5_.renderYawOffset = 180.0F + f * 20.0F;
        p_228187_5_.rotationYaw = 180.0F + f * 40.0F;
        p_228187_5_.rotationPitch = -f1 * 20.0F;
        p_228187_5_.rotationYawHead = p_228187_5_.rotationYaw;
        p_228187_5_.prevRotationYawHead = p_228187_5_.rotationYaw;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
        quaternion1.conjugate();
        entityrenderermanager.setCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        entityrenderermanager.renderEntityStatic(p_228187_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
        irendertypebuffer$impl.finish();
        entityrenderermanager.setRenderShadow(true);
        p_228187_5_.renderYawOffset = f2;
        p_228187_5_.rotationYaw = f3;
        p_228187_5_.rotationPitch = f4;
        p_228187_5_.prevRotationYawHead = f5;
        p_228187_5_.rotationYawHead = f6;
        RenderSystem.popMatrix();
    }

    protected boolean isPointInRegion(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) && super.isPointInRegion(x, y, width, height, mouseX, mouseY);
    }

    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        if (this.recipeBookGui.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookGui.isVisible() ? false : super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
        }
    }

    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
        if (this.buttonClicked) {
            this.buttonClicked = false;
            return true;
        } else {
            return super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
        }
    }

    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        boolean flag = mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.xSize) || mouseY >= (double)(guiTopIn + this.ySize);
        return this.recipeBookGui.func_195604_a(mouseX, mouseY, this.guiLeft, this.guiTop, this.xSize, this.ySize, mouseButton) && flag;
    }

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
        this.recipeBookGui.slotClicked(slotIn);
    }

    public void recipesUpdated() {
        this.recipeBookGui.recipesUpdated();
    }

    public void removed() {
        if (this.removeRecipeBookGui) {
            this.recipeBookGui.removed();
        }

        super.removed();
    }

    public RecipeBookGui getRecipeGui() {
        return this.recipeBookGui;
    }

    public static int setupOrientation() {
        int orient = 0;

        if (ModConfiguration.CLIENT.small.get()) {
            orient = 1;
            if (ModConfiguration.CLIENT.verticalBar.get())
                orient = 3;
        }else if (ModConfiguration.CLIENT.verticalBar.get())
            orient = 2;

        return orient;
    }

    public int getOrientation() { return orientation; }
    public int getCurrentInventory() { return currentInventory; }
}
