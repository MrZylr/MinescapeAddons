package com.zylr.minescapeaddons.addons.gui.builders;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.overwrites.SlotsEditor;
import com.zylr.minescapeaddons.addons.gui.screens.vanilla.ModVanillaInventoryScreen;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryBuilder {
    private int height;
    private int width;
    private int mouseX;
    private int mouseY;
    private List <ItemStack> inventory;
    private Container container;
    private int highlightColorStart;
    private int getHighlightColorEnd;
    private boolean defaultPosition;
    private Minecraft mc = Minecraft.getInstance();

    // Slot order
    public final int[] INVENTORY_SLOT_ORDER = new int[]{37,38,39,40,41,42,43,44,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
    public final int[] EQUIPEMENT_SLOT_ORDER = new int[]{6,12,7,45,36,13,9,8,10,5,11};
    public final int[] BUTTONS_ORDER = new int[]{16,15,4,3,17,0,1,2,14};
    public final int[] xSlotDefaultPosition = new int[]{154,98,116,98,116,8,8,8,8,8,26,44,62,80,98,116,134,152,8,26,44,62,80,98,116,134,152,8,26,44,62,80,98,116,134,152,8,26,44,62,80,98,116,134,152,77};
    public final int[] ySlotDefaultPosition = new int[]{28,18,18,36,36,8,26,44,62,84,84,84,84,84,84,84,84,84,102,102,102,102,102,102,102,102,102,120,120,120,120,120,120,120,120,120,142,142,142,142,142,142,142,142,142,62};

    /*
    16 = combat
    15 = quests
    4 = prayer
    3 = spellbook
    17 = social
    0 = settings
    1 = cosmetics
    2 = music
    14 = skills
    */

    private Map<Integer, Integer> invXPostions = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> invYPostions = new HashMap<Integer, Integer>();


    public InventoryBuilder() {
        height = 0;
        width = 0;
        mouseX = mc.getMainWindow().getFramebufferWidth()/2;
        mouseY = mc.getMainWindow().getFramebufferHeight()/2;
        inventory = new ArrayList<>();
        defaultPosition = !ModConfiguration.CLIENT.rsInventory.get();
    }

    public void build() {
        // SkyEXE UUID to set his color
        String uuid = "000";
        if (mc.player != null) {
            uuid = mc.player.getUniqueID().toString();
        }
        if (uuid.equalsIgnoreCase("5737d5b5-bd30-437f-b9ad-41fcb15c221a")) {
            highlightColorStart = 0x50FF0000;
            getHighlightColorEnd = 0x10FF0000;
        }else {
            highlightColorStart = 0x50000000;
            getHighlightColorEnd = 0x10000000;
        }


        if (this.container == null)
            this.setupContianer();

        // test
        this.container = Minecraft.getInstance().player.container;


        defaultPosition = !ModConfiguration.CLIENT.rsInventory.get();
        createInventory();
        createEquipment();
        createButtons();
    }

    public void setDefaultPositions() {
        for (int i = 0; i < this.container.inventorySlots.size(); ++i) {
            SlotsEditor.setxPos(this.container.inventorySlots.get(i), xSlotDefaultPosition[i]);
            SlotsEditor.setyPos(this.container.inventorySlots.get(i), ySlotDefaultPosition[i]);
        }
    }

    // Grab only what is considered rs inventory
    private List<ItemStack> grabPlayerInventory() {
        inventory.clear();

        // Ignore slots 0, 9-17
        int i = 0;
        for (ItemStack item:Minecraft.getInstance().player.inventory.mainInventory) {
            if(!(i == 0 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17))
                inventory.add(item);
            i++;
        }

        return inventory;
    }

    // TODO:: /value
    private void createEquipment() {
        boolean small = ModConfiguration.CLIENT.small.get();
        boolean vertical = ModConfiguration.CLIENT.verticalBar.get();

        grabPlayerInventory();
        // Decide item in hand to render background
        int currentItem = Minecraft.getInstance().player.inventory.currentItem;

        // Get screen size
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        ItemRenderer render = Minecraft.getInstance().getItemRenderer();

        // If not on equipment screen render equipment offscreen
        if (Main.getInstance().getStatsPanel().getCurrentScreen() != 2) {
            if (ModConfiguration.CLIENT.rsInventory.get()) {
                for (int position : EQUIPEMENT_SLOT_ORDER) {
                    SlotsEditor.setxPos(container.getSlot(position), Minecraft.getInstance().getMainWindow().getHeight() * 2);
                    SlotsEditor.setyPos(container.getSlot(position), Minecraft.getInstance().getMainWindow().getWidth() * 2);
                }
            }
        }

        // Decide the size and orientation, then set appropriate position values
        List<Integer> itemPos = new ArrayList<>();
        itemPos.add(122);
        itemPos.add(165);
        itemPos.add(96);
        itemPos.add(27);
        if (vertical) {
            itemPos.clear();
            itemPos.add(147);
            itemPos.add(139);
            itemPos.add(96);
            itemPos.add(27);
        }
        if (small) {
            itemPos.clear();
            itemPos.add(126);
            itemPos.add(167);
            itemPos.add(96);
            itemPos.add(27);
            if (vertical) {
                itemPos.clear();
                itemPos.add(150);
                itemPos.add(143);
                itemPos.add(96);
                itemPos.add(27);
            }
            for (int i1 = 0; i1 < itemPos.size(); i1++) {
                itemPos.set(i1, (int)(itemPos.get(i1)*.75));
            }
        }

        // Loop values
        int i = 0;
        int w = 0;
        int h = 0;
        for (int position:EQUIPEMENT_SLOT_ORDER) {

            // Starting position
            int itemWidth = itemPos.get(0);
            int itemHeight = itemPos.get(1);
            // Spacing between skills | Get the origin then multiply by the loop values
            int widthAdjust = w * itemPos.get(2);
            int heightAdjust = h * itemPos.get(3);
            // Adjust the base position values
            itemWidth -= widthAdjust;
            itemHeight -= heightAdjust;

            // Special case equipment slots | 8=Amulet  9=Helmet  10=Ring
            if (i > 7) {
                switch(i) {
                    case 8:
                        itemWidth = 103;
                        itemHeight = 192;
                        if (vertical){
                            itemWidth = 128;
                            itemHeight = 167;
                        }
                        if (small) {
                            itemWidth = 79;
                            itemHeight = 146;
                            if (vertical){
                                itemWidth = 98;
                                itemHeight = 128;
                            }
                        }
                        break;
                    case 9:
                        itemWidth = 75;
                        itemHeight = 192;
                        if (vertical){
                            itemWidth = 100;
                            itemHeight = 167;
                        }
                        if (small) {
                            itemWidth = 58;
                            itemHeight = 146;
                            if (vertical){
                                itemWidth = 77;
                                itemHeight = 128;
                            }
                        }
                        break;
                    case 10:
                        itemWidth = 46;
                        itemHeight = 192;
                        if (vertical){
                            itemWidth = 71;
                            itemHeight = 167;
                        }
                        if (small) {
                            itemWidth = 36;
                            itemHeight = 146;
                            if (vertical){
                                itemWidth = 55;
                                itemHeight = 128;
                            }
                        }
                        break;
                }
            }

            // Determine if on the equipment screen to know if slots should render
            if (Main.getInstance().getStatsPanel().getCurrentScreen() == 2) {
                // Render current item background
                if (currentItem+36 == position)
                    if (ModConfiguration.CLIENT.hotbar.get())
                        BuilderUtils.drawRect(itemWidth+2, itemHeight+2, 20, 20,
                            highlightColorStart, getHighlightColorEnd, false);
                // Start item rendering by setting up for scaling
                RenderSystem.pushMatrix();
                RenderSystem.enableBlend();
                // Render the Item on hud
                if (ModConfiguration.CLIENT.hotbar.get())
                    render.renderItemIntoGUI(container.inventorySlots.get(position).getStack(), (int) (width) - itemWidth, (int) (height) - itemHeight);
                RenderSystem.popMatrix();
                // Set slot position
                if (ModConfiguration.CLIENT.rsInventory.get()) {
                    SlotsEditor.setxPos(container.getSlot(position), (int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(position), (int) (height) - itemHeight);
                }
                // Render player model
                renderPlayer();
            }

            w++;
            i++;
            if (i % 2 == 0) {
                w = 0;
                h++;
            }
        }
    }

    private void createInventory() {
        //
        boolean small = ModConfiguration.CLIENT.small.get();
        boolean vertical = ModConfiguration.CLIENT.verticalBar.get();

        grabPlayerInventory();
        // Decide item in hand to render background
        int currentItem = Minecraft.getInstance().player.inventory.currentItem;
        // Get screen size
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        ItemRenderer render = Minecraft.getInstance().getItemRenderer();

        // If not on Inventory screen render inventory off screen
        if (Main.getInstance().getStatsPanel().getCurrentScreen() != 1) {
            if (ModConfiguration.CLIENT.rsInventory.get()) {
                for (int position : INVENTORY_SLOT_ORDER) {
                    SlotsEditor.setxPos(container.getSlot(position), Minecraft.getInstance().getMainWindow().getHeight() * 2);
                    SlotsEditor.setyPos(container.getSlot(position), Minecraft.getInstance().getMainWindow().getWidth() * 2);
                }
            }
        }

        // Decide the size and orientation, then set appropriate position values
        List<Integer> itemPos = new ArrayList<>();
        itemPos.add(115);
        itemPos.add(188);
        itemPos.add(27);
        itemPos.add(23);
        if (vertical) {
            itemPos.clear();
            itemPos.add(138);
            itemPos.add(165);
            itemPos.add(27);
            itemPos.add(23);
        }
        if (small) {
            itemPos.clear();
            itemPos.add(91);
            itemPos.add(145);
            itemPos.add(22);
            itemPos.add(17);
            if (vertical) {
                itemPos.clear();
                itemPos.add(111);
                itemPos.add(126);
                itemPos.add(22);
                itemPos.add(17);
            }
        }
        // Loop values
        int i = 0;
        int w = 0;
        int h = 0;
        for (int position:INVENTORY_SLOT_ORDER) {
            // Starting position
            int itemWidth = itemPos.get(0);
            int itemHeight = itemPos.get(1);
            // Spacing between skills | Get the origin then multiply by the loop values
            int widthAdjust = w * itemPos.get(2);
            int heightAdjust = h * itemPos.get(3);
            // Adjust the base position values
            itemWidth -= widthAdjust;
            itemHeight -= heightAdjust;

            // Render locked icon for vanilla
            if (!ModConfiguration.CLIENT.rsInventory.get()) {
                if (Minecraft.getInstance().currentScreen instanceof ModVanillaInventoryScreen) {
                        drawLock(((ModVanillaInventoryScreen) Minecraft.getInstance().currentScreen).getGuiLeft()+138,
                            ((ModVanillaInventoryScreen) Minecraft.getInstance().currentScreen).getGuiTop(),
                            12, 16, ModConfiguration.CLIENT.protectedSlot.get(), false);
                }
            }
            // Determine if on the equipment screen to know if slots should render
            if (Main.getInstance().getStatsPanel().getCurrentScreen() == 1) {
                //
                if (position == 37) {
                        drawLock(itemWidth, itemHeight, 12, 16, ModConfiguration.CLIENT.protectedSlot.get(), true);
                }
                // Render current item background
                if (currentItem+36 == position)
                    if (ModConfiguration.CLIENT.hotbar.get())
                        BuilderUtils.drawRect(itemWidth+2, itemHeight+2, 20, 20,
                            highlightColorStart, getHighlightColorEnd, false);
                // Start item rendering by setting up for scaling
                RenderSystem.pushMatrix();
                RenderSystem.enableBlend();
                // Render the Item on hud
                if (ModConfiguration.CLIENT.hotbar.get())
                    render.renderItemIntoGUI(container.inventorySlots.get(position).getStack(), (int) (width) - itemWidth, (int) (height) - itemHeight);
                RenderSystem.disableBlend();
                RenderSystem.popMatrix();
                // Set slot position
                if (ModConfiguration.CLIENT.rsInventory.get()) {
                    SlotsEditor.setxPos(container.getSlot(position), (int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(position), (int) (height) - itemHeight);
                }
            }

            w++;
            i++;
            if (i % 4 == 0) {
                w = 0;
                h++;
            }
        }
    }

    private void createButtons() {
        // Return if runescape style inventory is off
        if (!ModConfiguration.CLIENT.rsInventory.get()) {
            return;
        }
        boolean small = ModConfiguration.CLIENT.small.get();
        boolean vertical = ModConfiguration.CLIENT.verticalBar.get();


        grabPlayerInventory();
        // Get screen size
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        // Decide the size and orientation, then set appropriate position values
        // Loop values
        int buttonIndex = 0;
        for (int i = 0; i < 14; i++) {
            // Starting position
            int itemWidth = 0;
            int itemHeight = 0;

            switch (i) {
                // Combat
                case 0:
                    itemWidth = 272;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 178;
                        itemHeight = 171;
                    }
                    if (small) {
                        itemWidth = 206;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 136;
                            itemHeight = 130;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Quest
                case 2:
                    itemWidth = 226;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 178;
                        itemHeight = 112;
                    }
                    if (small) {
                        itemWidth = 171;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 136;
                            itemHeight = 86;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Prayer
                case 5:
                    itemWidth = 157;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 178;
                        itemHeight = 23;
                    }
                    if (small) {
                        itemWidth = 119;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 136;
                            itemHeight = 20;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Magic
                case 6:
                    itemWidth = 134;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 20;
                        itemHeight = 170;
                    }
                    if (small) {
                        itemWidth = 102;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 17;
                            itemHeight = 130;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Friends
                case 7:
                    itemWidth = 111;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 21;
                        itemHeight = 112;
                    }
                    if (small) {
                        itemWidth = 85;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 17;
                            itemHeight = 108;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Settings
                case 10:
                    itemWidth = 65;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 21;
                        itemHeight = 82;
                    }
                    if (small) {
                        itemWidth = 51;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 17;
                            itemHeight = 64;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Emotes
                case 11:
                    itemWidth = 42;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 21;
                        itemHeight = 53;
                    }
                    if (small) {
                        itemWidth = 33;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 17;
                            itemHeight = 42;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // Music
                case 12:
                    itemWidth = 19;
                    itemHeight = 21;
                    if (vertical) {
                        itemWidth = 21;
                        itemHeight = 24;
                    }
                    if (small) {
                        itemWidth = 16;
                        itemHeight = 17;
                        if (vertical) {
                            itemWidth = 17;
                            itemHeight = 20;
                        }
                    }
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
                // skills
                case 13:
                    itemWidth = 249;
                    itemHeight = 42;
                    if (vertical) {
                        itemWidth = 200;
                        itemHeight = 140;
                    }
                    if (small) {
                        itemWidth = 189;
                        itemHeight = 35;
                        if (vertical) {
                            itemWidth = 154;
                            itemHeight = 108;
                        }
                    }
                    if (Main.getInstance().getStatsPanel().getCurrentScreen() == 0)
                        BuilderUtils.rectangle(itemWidth, itemHeight, 16, 16,
                            new ResourceLocation(Main.ID, "textures/gui/skills.png"), false);
                    // Set slot position
                    SlotsEditor.setxPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (width) - itemWidth);
                    SlotsEditor.setyPos(container.getSlot(BUTTONS_ORDER[buttonIndex]),(int) (height) - itemHeight);
                    buttonIndex++;
                    break;
            }
        }
    }

    public void renderPlayer() {
        boolean small = ModConfiguration.CLIENT.small.get();
        boolean vertical = ModConfiguration.CLIENT.verticalBar.get();
        if (!ModConfiguration.CLIENT.hotbar.get())
            return;

            // Set model position values
        List<Integer> pos = new ArrayList<>();
        pos.add(66);
        pos.add(70);
        pos.add(41);
        if (vertical){
            pos.clear();
            pos.add(90);
            pos.add(45);
            pos.add(41);
        }
        if (small) {
            for (int i1 = 0; i1 < pos.size(); i1++) {
                pos.set(i1, (int)(pos.get(i1)*.75));
            }
        }

        // Render Player model
        InventoryScreen.drawEntityOnScreen(Minecraft.getInstance().getMainWindow().getScaledWidth()- pos.get(0),
                Minecraft.getInstance().getMainWindow().getScaledHeight()-pos.get(1),
                pos.get(2), 50, 0, Minecraft.getInstance().player);
    }

    public static void drawLock (int originX, int originY, int width, int height, boolean locked, boolean background) {
        if (!ModConfiguration.CLIENT.hotbar.get())
            return;
            ResourceLocation lockIcon = new ResourceLocation(Main.ID, "textures/gui/unlock.png");
        if (locked) {
            lockIcon = new ResourceLocation(Main.ID, "textures/gui/lock.png");

            if (background)
                BuilderUtils.drawRect(originX+2, originY+2, 20, 20,
                    0x50FFD800, 0x10FFD800, false);
        }
        BuilderUtils.rectangle(originX+8, originY+4, width/2, height/2, lockIcon, false);
    }

    public int getMouseX() { return mouseX; }

    public int getMouseY() { return mouseY; }

    public void setMouseX(int mouseX) { this.mouseX = mouseX; }

    public void setMouseY(int mouseY) { this.mouseY = mouseY; }

    public void resetMouse() {
        this.mouseX = (int)Main.getInstance().skills.get(SkillType.THIEVING).getInvX()*(int)mc.getMainWindow().getGuiScaleFactor();
        this.mouseY = (int)Main.getInstance().skills.get(SkillType.THIEVING).getInvY()*(int)mc.getMainWindow().getGuiScaleFactor();
    }

    public void setupContianer() {
        this.container = Minecraft.getInstance().player.container;
        resetMouse();
    }

    public List<ItemStack> getInventory() { return inventory; }

    public Map<Integer, Integer> getInvXPostions() { return invXPostions; }
    public Map<Integer, Integer> getInvYPostions() { return invYPostions; }

    public void setDefaultPosition(boolean defaultPosition) { this.defaultPosition = defaultPosition; }

    public boolean getDefaultPostion() { return defaultPosition; }


}
