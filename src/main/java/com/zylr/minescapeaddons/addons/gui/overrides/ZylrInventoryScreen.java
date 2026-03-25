package com.zylr.minescapeaddons.addons.gui.overrides;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.Controls;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.BrowserWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.MenuButton;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.SkillButton;
import com.zylr.minescapeaddons.addons.gui.widgets.chat.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.EquipmentStatsWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.EquipmentWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.HudMenuWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.menus.RightClickItemMenu;
import com.zylr.minescapeaddons.addons.gui.widgets.menus.RightClickSkillMenu;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ZylrInventoryScreen extends ZylrEffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {
    /** MODIFIED InventoryScreen CODE */


    /**
     * The old x position of the mouse pointer
     */
    private float xMouse;
    /**
     * The old y position of the mouse pointer
     */
    private float yMouse;
    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private boolean widthTooNarrow;
    private boolean buttonClicked;
    public RightClickItemMenu rightClickItemMenu;
    public RightClickSkillMenu rightClickSkillMenu;

    // HudMenu Buttons
    private List<SkillButton> skillButtons;
    private ImageButton invButton;
    private ImageButton equipmentButton;
    private ImageButton combatButton;
    private ImageButton skillsButton;
    private ImageButton questsButton;
    private ImageButton prayerButton;
    private ImageButton magicButton;
    private ImageButton friendsButton;
    private ImageButton logoutButton;
    private ImageButton settingsButton;
    private ImageButton cosmeticsButton;
    // Combat tab buttons
    private ImageButton combatStatsButton;
    // Combat stats buttons
    private ImageButton combatStatsCloseButton;

    ResourceLocation buttonImage = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "widget/clear");
    ResourceLocation blackImage = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "widget/black");
    ResourceLocation blackImageFull = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/black.png");



    private ChatWidget chatWidget;
    private boolean shouldFocusChatOnInit;
    private boolean shouldStartCommand;


    public ZylrInventoryScreen(Player player, boolean shouldFocusChatOnInit, boolean shouldStartCommand, String initial) {
        super(player.inventoryMenu, player.getInventory(), Component.translatable("container.crafting"), initial);
        this.titleLabelX = 97;
        Minecraft mc = Minecraft.getInstance();
        this.skillButtons = new ArrayList<>();
        rightClickItemMenu = null;
        rightClickSkillMenu = null;

        if (Config.getFixedMode()) {
            createButtonsFixed();
        } else {
            if (!Config.getSmallInventory()) {
                createButtonsLarge();
            } else {
                createButtonsSmall();
            }
        }

        this.addRenderableWidget(this.invButton);
        this.addRenderableWidget(this.equipmentButton);
        this.addRenderableWidget(this.combatButton);
        this.addRenderableWidget(this.skillsButton);
        this.addRenderableWidget(this.questsButton);
        this.addRenderableWidget(this.prayerButton);
        this.addRenderableWidget(this.magicButton);
        this.addRenderableWidget(this.friendsButton);
        this.addRenderableWidget(this.logoutButton);
        this.addRenderableWidget(this.settingsButton);
        this.addRenderableWidget(this.cosmeticsButton);
        this.addRenderableWidget(this.combatStatsButton);
        this.addRenderableWidget(MinescapeAddons.getInstance().resizableClassicChildren.getXpOrb().getXpButton());
        this.addRenderableWidget(MinescapeAddons.getInstance().resizableClassicChildren.getRunEnergyOrb().getSprintButton());
        this.addRenderableWidget(MinescapeAddons.getInstance().resizableClassicChildren.getPrayerPointsOrb().getPrayerButton());



        // Chat widget
        this.chatWidget = MinescapeAddons.getInstance().resizableClassic.getChatWidget();
        this.shouldFocusChatOnInit = shouldFocusChatOnInit;
        this.shouldStartCommand = shouldStartCommand;
    }

    private void createButtonsFixed() {
        HudMenuWidget hudMenu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
        EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;

        int i = 0;
        int x = 0;
        int i1 = 0;
        int y = 0;
        int x1 = 10;
        int y1 = -22;//
        for (String s : MinescapeAddons.skillsList) {
            Skill skill = MinescapeAddons.skills.get(SkillType.valueOf(s.toUpperCase()));
            // Do logic to arrange buttons
            if (i % 3 == 0) {
                y1 = y1 + 34;
                x1 = 10;
            }

            SkillButton skillButton = new SkillButton(skill, blackImageFull, Component.empty(), 10, hudMenu.getLeftSide()+18+x1,
                    hudMenu.getTop()+25+y1, 63, 32, button -> {
                this.imitateSlotClicked(this.menu.getSlot(1), 1, 2, ClickType.PICKUP);
            });
            this.skillButtons.add(skillButton);

            x1 = x1 + 65;
            i++;
        }
        // Top row of buttons
        this.invButton = new ImageButton(hudMenu.getLeftSide()+110, hudMenu.getTop(), 34, 37,
                new WidgetSprites(blackImage,blackImage), button -> this.onInvButtonPress());
        this.equipmentButton = new ImageButton(hudMenu.getLeftSide()+144, hudMenu.getTop(), 34, 37,
                new WidgetSprites(blackImage, blackImage), button -> this.onEquipmentButtonPress());
        this.combatButton = new ImageButton(hudMenu.getLeftSide()+4, hudMenu.getTop(), 39, 37,
                new WidgetSprites(blackImage, blackImage), button -> this.onCombatButtonPress());
        this.skillsButton = new ImageButton(hudMenu.getLeftSide()+44, hudMenu.getTop(),34, 37,
                new WidgetSprites(blackImage,blackImage), button -> this.onSkillsButtonPress());
        this.questsButton = new ImageButton(hudMenu.getLeftSide() + 76, hudMenu.getTop(), 34, 37,
                new WidgetSprites(blackImage, blackImage), button -> this.onQuestsButtonPress());
        this.prayerButton = new ImageButton(hudMenu.getLeftSide()+178, hudMenu.getTop(), 34, 37,
                new WidgetSprites(blackImage, blackImage), button -> this.onPrayerButtonPress());
        this.magicButton = new ImageButton(hudMenu.getRightSide()-41, hudMenu.getTop(), 39,37,
                new WidgetSprites(blackImage, blackImage), button -> this.onMagicButtonPress());
        // Bottom row of buttons
        this.friendsButton = new ImageButton(hudMenu.getLeftSide()+44, hudMenu.getBottom()-38, 34, 38,
                new WidgetSprites(blackImage, blackImage), button -> this.onFriendsButtonPress());
        this.logoutButton = new ImageButton(hudMenu.getLeftSide()+110, hudMenu.getBottom()-38, 34, 38,
                new WidgetSprites(blackImage, blackImage), button -> this.onLogoutButtonPress());
        this.settingsButton = new ImageButton(hudMenu.getLeftSide()+144, hudMenu.getBottom()-38, 34, 38,
                new WidgetSprites(blackImage, blackImage), button -> onSettingsButtonPress());
        this.cosmeticsButton = new ImageButton(hudMenu.getLeftSide()+178, hudMenu.getBottom()-38, 34, 38,
                new WidgetSprites(blackImage, blackImage), button -> onCosmenticsButtonPress());
        // Combat tab buttons
        this.combatStatsButton = new ImageButton(equipmentWidget.getLeftSide()+16, equipmentWidget.getBottom()-67, 43, 43,
                new WidgetSprites(blackImage, blackImage), button -> {
            MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().setVisible(true);
        });
    }

    private void createButtonsLarge() {
        HudMenuWidget hudMenu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
        EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;

        int i = 0;
        int x = 0;
        int i1 = 0;
        int y = 0;
        int x1 = 0;
        int y1 = -22;
        for (String s : MinescapeAddons.skillsList) {
            Skill skill = MinescapeAddons.skills.get(SkillType.valueOf(s.toUpperCase()));
            // Do logic to arrange buttons
            if (i % 3 == 0) {
                y1 = y1 + 22;
                x1 = 0;
            }

            SkillButton skillButton = new SkillButton(skill, blackImageFull, Component.empty(), 10, hudMenu.getLeftSide()+18+x1,
                    hudMenu.getTop()+25+y1, 40, 21, button -> {
                this.imitateSlotClicked(this.menu.getSlot(1), 1, 2, ClickType.PICKUP);
            });
            this.skillButtons.add(skillButton);

            x1 = x1 + 41;
            i++;
        }
        // Top row of buttons
        this.invButton = new ImageButton(hudMenu.getLeftSide()+68, hudMenu.getTop(), 25, 25,
                new WidgetSprites(buttonImage,buttonImage), button -> this.onInvButtonPress());
        this.equipmentButton = new ImageButton(hudMenu.getLeftSide()+89, hudMenu.getTop(), 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onEquipmentButtonPress());
        this.combatButton = new ImageButton(hudMenu.getLeftSide(), hudMenu.getTop(), 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onCombatButtonPress());
        this.skillsButton = new ImageButton(hudMenu.getLeftSide()+23, hudMenu.getTop(),25, 25,
                new WidgetSprites(buttonImage,buttonImage), button -> this.onSkillsButtonPress());
        this.questsButton = new ImageButton(hudMenu.getLeftSide() + 46, hudMenu.getTop(), 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onQuestsButtonPress());
        this.prayerButton = new ImageButton(hudMenu.getLeftSide()+111, hudMenu.getTop(), 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onPrayerButtonPress());
        this.magicButton = new ImageButton(hudMenu.getRightSide()-25, hudMenu.getTop(), 25,25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onMagicButtonPress());
        // Bottom row of buttons
        this.friendsButton = new ImageButton(hudMenu.getLeftSide()+23, hudMenu.getBottom()-25, 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onFriendsButtonPress());
        this.logoutButton = new ImageButton(hudMenu.getLeftSide()+68, hudMenu.getBottom()-25, 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onLogoutButtonPress());
        this.settingsButton = new ImageButton(hudMenu.getLeftSide()+89, hudMenu.getBottom()-25, 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> onSettingsButtonPress());
        this.cosmeticsButton = new ImageButton(hudMenu.getLeftSide()+111, hudMenu.getBottom()-25, 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> onCosmenticsButtonPress());
        // Combat tab buttons
        this.combatStatsButton = new ImageButton(equipmentWidget.getLeftSide()+10, equipmentWidget.getBottom()-39, 25, 25,
                new WidgetSprites(buttonImage, buttonImage), button -> {
            MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().setVisible(true);
        });
    }

    private void createButtonsSmall() {
        HudMenuWidget hudMenu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
        EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;

        int i = 0;
        int x = 0;
        int i1 = 0;
        int y = 0;
        int x1 = 0;
        int y1 = -21;
        for (String s : MinescapeAddons.skillsList) {
            Skill skill = MinescapeAddons.skills.get(SkillType.valueOf(s.toUpperCase()));
            // Do logic to arrange buttons
            if (i % 3 == 0) {
                y1 = y1 + 17;
                x1 = 0;
            }

            SkillButton skillButton = new SkillButton(skill, blackImageFull, Component.empty(), 10, hudMenu.getLeftSide()+14+x1,
                    hudMenu.getTop()+23+y1, 32, 16, button -> {
                this.imitateSlotClicked(this.menu.getSlot(1), 1, 2, ClickType.PICKUP);
            });
            this.skillButtons.add(skillButton);

            x1 = x1 + 33;
            i++;
        }
        // Top row of buttons
        this.combatButton = new ImageButton(hudMenu.getLeftSide(), hudMenu.getTop(), 20, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onCombatButtonPress());
        this.skillsButton = new ImageButton(hudMenu.getLeftSide()+20, hudMenu.getTop(),17, 17,
                new WidgetSprites(buttonImage,buttonImage), button -> this.onSkillsButtonPress());
        this.questsButton = new ImageButton(hudMenu.getLeftSide() + 37, hudMenu.getTop(), 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onQuestsButtonPress());
        this.invButton = new ImageButton(hudMenu.getLeftSide()+54, hudMenu.getTop(), 17, 17,
                new WidgetSprites(buttonImage,buttonImage), button -> this.onInvButtonPress());
        this.equipmentButton = new ImageButton(hudMenu.getLeftSide()+71, hudMenu.getTop(), 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onEquipmentButtonPress());
        this.prayerButton = new ImageButton(hudMenu.getLeftSide()+88, hudMenu.getTop(), 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onPrayerButtonPress());
        this.magicButton = new ImageButton(hudMenu.getLeftSide()+105, hudMenu.getTop(), 20,17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onMagicButtonPress());
        // Bottom row of buttons
        this.friendsButton = new ImageButton(hudMenu.getLeftSide()+20, hudMenu.getBottom()-17, 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onFriendsButtonPress());
        this.logoutButton = new ImageButton(hudMenu.getLeftSide()+54, hudMenu.getBottom()-17, 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> this.onLogoutButtonPress());
        this.settingsButton = new ImageButton(hudMenu.getLeftSide()+71, hudMenu.getBottom()-17, 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> onSettingsButtonPress());
        this.cosmeticsButton = new ImageButton(hudMenu.getLeftSide()+88, hudMenu.getBottom()-17, 17, 17,
                new WidgetSprites(buttonImage, buttonImage), button -> onCosmenticsButtonPress());
        // Combat tab buttons
        this.combatStatsButton = new ImageButton(equipmentWidget.getLeftSide()+8, equipmentWidget.getBottom()-30, 20, 20,
                new WidgetSprites(buttonImage, buttonImage), button -> {
            MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().setVisible(true);
        });
    }

    @Override
    public void containerTick() {
        if (this.minecraft.gameMode.hasInfiniteItems()) {
            this.minecraft
                    .setScreen(
                            new CreativeModeInventoryScreen(
                                    this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get()
                            )
                    );
        } else {
            this.recipeBookComponent.tick();
        }
    }

    @Override
    protected void init() {

        if (this.minecraft.gameMode.hasInfiniteItems()) {
            this.minecraft
                    .setScreen(
                            new CreativeModeInventoryScreen(
                                    this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get()
                            )
                    );
        } else {
            super.init();
            this.widthTooNarrow = this.width < 379;
            this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
            this.leftPos = 0;

            this.addWidget(this.recipeBookComponent);


            // create combat stats buttons after init
            HudMenuWidget hudMenu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
            EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;
            EquipmentStatsWidget equipmentStatsWidget = MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget();
            this.combatStatsCloseButton = new ImageButton(equipmentStatsWidget.getRightSide()-14, equipmentStatsWidget.getTop()+7, 10, 10,
                    new WidgetSprites(buttonImage, buttonImage), button -> {
                equipmentStatsWidget.setVisible(false);
                hudMenu.setActiveWidget(equipmentWidget);
            });
            this.addRenderableWidget(this.combatStatsCloseButton);

            // Chat widget
            // Cache the EditBox instance returned by the widget and register that instance with the screen.
            if (this.chatWidget != null) {
                //this.textInput = this.chatWidget.getTextInput();
                this.input.setCanLoseFocus(true);
                this.input.setPosition(this.chatWidget.getLeftSide()+8, this.chatWidget.getBottom()-Minecraft.getInstance().font.lineHeight-7);
                this.input.setWidth(this.chatWidget.getWidgetWidth()-18);
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        //guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    @Override
    public void onEdited(String value) {
        super.onEdited(value);
    }

    @Override
    public void setInitialFocus() {
        if (shouldFocusChatOnInit) {
            this.setFocused(this.input);
        }else {
            this.input.setFocused(false);
            this.clearFocus();
        }
    }


    /**
     * Renders the graphical user interface (GUI) element.
     *
     * @param guiGraphics the GuiGraphics object used for rendering.
     * @param mouseX      the x-coordinate of the mouse cursor.
     * @param mouseY      the y-coordinate of the mouse cursor.
     * @param partialTick the partial tick time.
     */
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
        } else {
            this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
            this.recipeBookComponent.renderGhostRecipe(guiGraphics, this.leftPos, this.topPos, false, partialTick);
            super.render(guiGraphics, mouseX, mouseY, partialTick);
        }

        this.renderTooltip(guiGraphics, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(guiGraphics, this.leftPos, this.topPos, mouseX, mouseY);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;



        if(MinescapeAddons.getInstance().resizableClassic.getHudMenu().getActiveWidget() == HudMenuWidget.SKILLS_WIDGET){
            for (SkillButton skillButton : skillButtons) {
                skillButton.setVisible(true);
            }
        }else {
            for (SkillButton skillButton : skillButtons) {
                skillButton.setVisible(false);
            }
        }

        this.combatStatsButton.visible = HudMenuWidget.EQUIPMENT_WIDGET.isVisible();
        this.combatStatsCloseButton.visible = MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().isVisible();

        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);
        for (SkillButton skillButton : skillButtons) {
            skillButton.render(guiGraphics);
        }
        guiGraphics.pose().popPose();

        if (rightClickItemMenu != null) {
            if (rightClickItemMenu.isHovered()) {
                rightClickItemMenu.render(guiGraphics);
            }else {
                rightClickItemMenu = null; // Remove the menu if not hovered
            }
        }
        if (rightClickSkillMenu != null) {
            if (rightClickSkillMenu.isHovered()) {
                rightClickSkillMenu.render(guiGraphics);
            }else {
                rightClickSkillMenu = null; // Remove the menu if not hovered
            }
        }

        // Render HudMenu
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 100);
        MinescapeAddons.getInstance().resizableClassic.getHudMenu().render(guiGraphics);
        guiGraphics.pose().popPose();
        // Render Minimap
        MinescapeAddons.getInstance().resizableClassic.getMinimapWidget().render(guiGraphics);
        // Render Tracker
        MinescapeAddons.getInstance().resizableClassic.getXpTrackerWidget().render(guiGraphics);
        // Render Scoreboard
        MinescapeAddons.getInstance().resizableClassic.getScoreboardWidget().render(guiGraphics);
        // Render Browser
        BrowserWidget browserWidget = MinescapeAddons.getInstance().resizableClassic.getBrowserWidget();
        if (browserWidget != null)
            browserWidget.render(guiGraphics);
        // Render Target Info
        MinescapeAddons.getInstance().resizableClassic.getTargetInfoWidget().render(guiGraphics);
        // Render Barrows Brother Widget
        MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().render(guiGraphics);

        // Chat widget
        if (Config.getCustomChat()) {
            float chatScale = MinescapeAddons.getInstance().resizableClassic.getChatWidget().scale;
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 900);
            this.input.setPosition(this.chatWidget.getLeftSide()+8, this.chatWidget.getBottom()-Minecraft.getInstance().font.lineHeight-7);
            this.chatWidget.render(guiGraphics);
            //this.minecraft.gui.getChat().render(guiGraphics, this.minecraft.gui.getGuiTicks(), mouseX, mouseY, true);
            guiGraphics.pose().scale(chatScale, chatScale, 1.0f);
            this.input.render(guiGraphics, (int)(mouseX/chatScale), (int)(mouseY/chatScale), partialTick);
            this.commandSuggestions.renderSuggestions(guiGraphics, (int)(mouseX/chatScale), (int)(mouseY/chatScale));
//

            guiGraphics.pose().translate(0, 0, 905);

            GuiMessageTag guimessagetag = this.minecraft.gui.getChat().getMessageTagAt((int)(mouseX/chatScale), (int)(mouseY/chatScale));
            if (guimessagetag != null && guimessagetag.text() != null) {
                guiGraphics.renderTooltip(this.font, this.font.split(guimessagetag.text(), 210), (int)(mouseX/chatScale), (int)(mouseY/chatScale));
            } else {
                Style style = this.getComponentStyleAt((mouseX/chatScale), (mouseY/chatScale));
                if (style != null && style.getHoverEvent() != null) {
                    guiGraphics.renderComponentHoverEffect(this.font, style, (int)(mouseX/chatScale), (int)(mouseY/chatScale));
                }
            }

            guiGraphics.pose().popPose();
        }else {
            guiGraphics.pose().pushPose();
            this.minecraft.gui.getChat().render(guiGraphics, this.minecraft.gui.getGuiTicks(), mouseX, mouseY, true);
            this.input.setPosition(0, this.minecraft.getWindow().getGuiScaledHeight() - minecraft.font.lineHeight);
            guiGraphics.fill(this.input.getX(), this.input.getY()-1, this.input.getX() + this.input.getWidth() + 10,
                    this.input.getY() + this.input.getHeight(), 0x80000000);
            this.input.render(guiGraphics, mouseX, mouseY, partialTick);
            guiGraphics.pose().translate(0, 0, 2000);
            this.commandSuggestions.renderSuggestions(guiGraphics, mouseX, mouseY);GuiMessageTag guimessagetag = this.minecraft.gui.getChat().getMessageTagAt((int)(mouseX), (int)(mouseY));
            if (guimessagetag != null && guimessagetag.text() != null) {
                guiGraphics.renderTooltip(this.font, this.font.split(guimessagetag.text(), 210), (int)(mouseX), (int)(mouseY));
            } else {
                Style style = this.getComponentStyleAt((mouseX), (mouseY));
                if (style != null && style.getHoverEvent() != null) {
                    guiGraphics.renderComponentHoverEffect(this.font, style, (int)(mouseX), (int)(mouseY));
                }
            }
            guiGraphics.pose().popPose();
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = this.topPos;

        EquipmentStatsWidget statsWidget = MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget();
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        if (statsWidget.isVisible()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0, 0.0, 301);
            guiGraphics.pose().scale(scale, scale, 1.0f);
            renderEntityInInventoryFollowsMouse(guiGraphics, statsWidget.getLeftSide() + 205, statsWidget.getTop() + 130, statsWidget.getLeftSide() + 75, statsWidget.getTop() + 78, 50, 0.0625F,
                    this.xMouse/scale, this.yMouse/scale, this.minecraft.player);
            guiGraphics.pose().popPose();
        }
    }

    public static void renderEntityInInventoryFollowsMouse(
            GuiGraphics guiGraphics,
            int x1,
            int y1,
            int x2,
            int y2,
            int scale,
            float yOffset,
            float mouseX,
            float mouseY,
            LivingEntity entity
    ) {
        float f = (float)(x1 + x2) / 2.0F;
        float f1 = (float)(y1 + y2) / 2.0F;
        float f2 = (float)Math.atan((double)((f - mouseX) / 40.0F));
        float f3 = (float)Math.atan((double)((f1 - mouseY) / 40.0F));
        // Forge: Allow passing in direct angle components instead of mouse position
        renderEntityInInventoryFollowsAngle(guiGraphics, x1, y1, x2, y2, scale, yOffset, f2, f3, entity);
    }

    public static void renderEntityInInventoryFollowsAngle(
            GuiGraphics p_282802_,
            int p_275688_,
            int p_275245_,
            int p_275535_,
            int p_294406_,
            int p_294663_,
            float p_275604_,
            float angleXComponent,
            float angleYComponent,
            LivingEntity p_275689_
    ) {
        float f = (float)(p_275688_ + p_275535_) / 2.0F;
        float f1 = (float)(p_275245_ + p_294406_) / 2.0F;
        float f2 = angleXComponent;
        float f3 = angleYComponent;
        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf1 = new Quaternionf().rotateX(f3 * 20.0F * (float) (Math.PI / 180.0));
        quaternionf.mul(quaternionf1);
        float f4 = p_275689_.yBodyRot;
        float f5 = p_275689_.getYRot();
        float f6 = p_275689_.getXRot();
        float f7 = p_275689_.yHeadRotO;
        float f8 = p_275689_.yHeadRot;
        p_275689_.yBodyRot = 180.0F + f2 * 20.0F;
        p_275689_.setYRot(180.0F + f2 * 40.0F);
        p_275689_.setXRot(-f3 * 20.0F);
        p_275689_.yHeadRot = p_275689_.getYRot();
        p_275689_.yHeadRotO = p_275689_.getYRot();
        float f9 = p_275689_.getScale();
        Vector3f vector3f = new Vector3f(0.0F, p_275689_.getBbHeight() / 2.0F + p_275604_ * f9, 0.0F);
        float f10 = (float)p_294663_ / f9;
        renderEntityInInventory(p_282802_, f, f1, f10, vector3f, quaternionf, quaternionf1, p_275689_);
        p_275689_.yBodyRot = f4;
        p_275689_.setYRot(f5);
        p_275689_.setXRot(f6);
        p_275689_.yHeadRotO = f7;
        p_275689_.yHeadRot = f8;
    }

    public static void renderEntityInInventory(
            GuiGraphics guiGraphics,
            float x,
            float y,
            float scale,
            Vector3f translate,
            Quaternionf pose,
            @Nullable Quaternionf cameraOrientation,
            LivingEntity entity
    ) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((double)x, (double)y, 50.0);
        guiGraphics.pose().scale(scale, scale, -scale);
        guiGraphics.pose().translate(translate.x, translate.y, translate.z);
        guiGraphics.pose().mulPose(pose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (cameraOrientation != null) {
            entityrenderdispatcher.overrideCameraOrientation(cameraOrientation.conjugate(new Quaternionf()).rotateY((float) Math.PI));
        }

        entityrenderdispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880));
        guiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        guiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

    /**
     * Called when a character is typed within the GUI element.
     * <p>
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param codePoint the code point of the typed character.
     * @param modifiers the keyboard modifiers.
     */
    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return this.recipeBookComponent.charTyped(codePoint, modifiers) ? true : super.charTyped(codePoint, modifiers);
    }

    @Override
    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
    }

    /**
     * Called when a mouse button is clicked within the GUI element.
     * <p>
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param mouseX the X coordinate of the mouse.
     * @param mouseY the Y coordinate of the mouse.
     * @param button the button that was clicked.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, button)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            float mouseScale = 1.0f;
            for (IWidget iWidget : MinescapeAddons.getInstance().activeHud.widgets){
                if (iWidget instanceof Widget widget) {
                    if (widget.isHovered()) {
                        mouseScale = widget.scale;
                    }
                }
            }
            if (this.rightClickItemMenu != null) {
                // Check if the right-click item menu is open and handle clicks on its selections
                for (MenuButton selection : this.rightClickItemMenu.selections) {
                    if (selection.isHovered()) {
                        selection.onClick(mouseX, mouseY, button);
                        this.rightClickItemMenu = null; // Close the menu after selection
                        return true;
                    }
                }
            }
            if (this.rightClickSkillMenu != null) {
                // Check if the right-click skill menu is open and handle clicks on its selections
                for (MenuButton selection : this.rightClickSkillMenu.selections) {
                    if (selection.isHovered()) {
                        selection.onClick(mouseX/mouseScale, mouseY/mouseScale, button);
                        this.rightClickSkillMenu = null; // Close the menu after selection
                        return true;
                    }
                }
            }
            for (SkillButton skillButton : this.skillButtons) {
                if(skillButton.isHovered()) {
                    skillButton.onClick(mouseX/mouseScale, mouseY/mouseScale, button);
                }
            }

            // Chat Widget click handling
            if (mouseX/mouseScale >= this.input.getX() && mouseX/mouseScale <= this.input.getX() + this.input.getWidth()
                    && mouseY/mouseScale >= this.input.getY() && mouseY/mouseScale <= this.input.getY() + this.input.getHeight()) {
                this.setFocused(this.input);
                return true;
            }else {
                this.clearFocus();
            }
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? false : super.mouseClicked(mouseX, mouseY, button);
        }
    }

    /**
     * Called when a mouse button is released within the GUI element.
     * <p>
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param mouseX the X coordinate of the mouse.
     * @param mouseY the Y coordinate of the mouse.
     * @param button the button that was released.
     */
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        // Chat Widget mouse release handling
        this.chatWidget.mouseReleased(mouseX, mouseY, button);
        if (this.buttonClicked) {
            this.buttonClicked = false;
            return true;
        } else {
            float mouseScale = 1.0f;
            for (IWidget iWidget : MinescapeAddons.getInstance().activeHud.widgets){
                if (iWidget instanceof Widget widget) {
                    if (widget.isHovered()) {
                        mouseScale = widget.scale;
                    }
                }
            }
            return super.mouseReleased(mouseX/mouseScale, mouseY/mouseScale, button);
        }
    }

    // Chat Widget
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        this.chatWidget.mouseDragged(mouseX, mouseY, dragX, dragY, button);
        return true;
    }

    // Chat Widget
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        this.chatWidget.mouseScrolled(mouseX, mouseY, scrollY);
        return true;
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        boolean flag = mouseX < (double)guiLeft
                || mouseY < (double)guiTop
                || mouseX >= (double)(guiLeft + this.imageWidth)
                || mouseY >= (double)(guiTop + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, mouseButton) && flag;
    }

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    @Override
    protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
        // Open right click menu if right-clicked on a slot
        Minecraft mc = Minecraft.getInstance();
        if (mouseButton == 1 && slot != null) {
            if(slot.hasItem()) {
                double rawX = mc.mouseHandler.xpos() / mc.getWindow().getGuiScale();
                double rawY = mc.mouseHandler.ypos() / mc.getWindow().getGuiScale();
                int menuX = (int) rawX;
                int menuY = (int) rawY - mc.font.lineHeight;
                rightClickItemMenu = new RightClickItemMenu("Choose Option", menuX, menuY, new ArrayList<>(), slot);
                if (rightClickItemMenu.selections.isEmpty())
                    rightClickItemMenu = null;

                return;
            }
        }

        super.slotClicked(slot, slotId, mouseButton, type);
        this.recipeBookComponent.slotClicked(slot);
    }
    public void imitateSlotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
        super.slotClicked(slot, slotId, mouseButton, type);
        this.recipeBookComponent.slotClicked(slot);
    }

    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }


    @Override
    public boolean keyPressed(int key, int scancode, int mods) {
        if (this.getFocused() != this.input) {
            if (Controls.NEW_INVENTORY_KEY.getKey().getValue() == key
                    || this.minecraft.options.keyInventory.getKey().getValue() == key) {
                Controls.SETTINGS_KEY.consumeClick();
                this.onClose();
                return true;
            }
        }
        // Block swap/hotbar keys from triggering slot clicks on combat widget
        if (this.minecraft.options.keySwapOffhand.getKey().getValue() == key) {
            System.out.println("Blocked offhand key");
            return false;
        }
        if (key == 264 || key == 265) {
            if (this.input != null) {
                super.keyPressed(key, scancode, mods);
                this.setFocused(this.input);
                return true;
            }
        }

        return super.keyPressed(key, scancode, mods);
    }

    @Override
    public void onClose() {
        super.onClose();
        MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget().setVisible(false);
    }

    public void onInvButtonPress() {
        MinescapeAddons.getInstance().resizableClassic.getHudMenu().setActiveWidget(HudMenuWidget.INVENTORY_WIDGET);

    }
    public void onEquipmentButtonPress() {
        MinescapeAddons.getInstance().resizableClassic.getHudMenu().setActiveWidget(HudMenuWidget.EQUIPMENT_WIDGET);
    }
    public void onCombatButtonPress() {
        MinescapeAddons.getInstance().resizableClassic.getHudMenu().setActiveWidget(HudMenuWidget.COMBAT_WIDGET);
    }
    public void onSkillsButtonPress() {
        Widget activeWidget = MinescapeAddons.getInstance().resizableClassic.getHudMenu().getActiveWidget();
        Widget skillsWidget = HudMenuWidget.SKILLS_WIDGET;
        if (activeWidget == skillsWidget) {
            this.imitateSlotClicked(this.menu.getSlot(1), 1, 2, ClickType.PICKUP);
        }
        MinescapeAddons.getInstance().resizableClassic.getHudMenu().setActiveWidget(HudMenuWidget.SKILLS_WIDGET);
    }
    public void onQuestsButtonPress() {
        this.imitateSlotClicked(this.menu.getSlot(1), 1, 1, ClickType.PICKUP_ALL);
    }

    public void onPrayerButtonPress() {
        this.imitateSlotClicked(this.menu.getSlot(4), 4, 1, ClickType.PICKUP_ALL);
    }

    public void onMagicButtonPress() {
        this.imitateSlotClicked(this.menu.getSlot(3), 3, 1, ClickType.PICKUP_ALL);
    }

    public void onFriendsButtonPress() {
        this.imitateSlotClicked(this.menu.getSlot(2), 2, 1, ClickType.PICKUP_ALL);
    }

    public void onLogoutButtonPress() {
        Minecraft.getInstance().disconnect();
        Minecraft.getInstance().setScreen(new TitleScreen());
    }

    public void onSettingsButtonPress() {
        this.imitateSlotClicked(this.menu.getSlot(0), 0, 1, ClickType.PICKUP_ALL);
    }

    public void onCosmenticsButtonPress() {
        this.imitateSlotClicked(this.menu.getSlot(2), 2, 2, ClickType.PICKUP);
    }

}
