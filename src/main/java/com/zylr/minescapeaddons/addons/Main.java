package com.zylr.minescapeaddons.addons;

import com.zylr.minescapeaddons.addons.Proxy.IProxy;
import com.zylr.minescapeaddons.addons.Proxy.ClientProxy;
import com.zylr.minescapeaddons.addons.armor.MinescapeItems;
import com.zylr.minescapeaddons.addons.gui.builders.*;
import com.zylr.minescapeaddons.addons.gui.hud.RSHud;
import com.zylr.minescapeaddons.addons.gui.screens.ImageButtons;
import com.zylr.minescapeaddons.addons.gui.widgets.ChatWidget;
import com.zylr.minescapeaddons.addons.handlers.*;
import com.zylr.minescapeaddons.addons.idle.IdleChecker;
import com.zylr.minescapeaddons.addons.skills.PersistentExp;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.skills.farming.ClickOnFarmingPatch;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import com.zylr.minescapeaddons.addons.skills.farming.PatchType;
import com.zylr.minescapeaddons.addons.util.files.properties.MainProperties;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Mod("ms_addon")
public class Main
{
    // TODO:: Add auto update to patreons 5+
    // TODO:: Move xp drops to xp counter
    // TODO:: Make xp goals on tracker
    // TODO:: Persistent mouse in bank
    // TODO:: suggestion, death logger and alarm?
    //  if i died say at 8:20pm could you put a little grave marker in the corner with a countdown till my grave
    //  collapse and maybe also play a tone to alert me if i died kinda like idle alert with its red flash


    // TODO:: Orthoview camera
    // Make walls between camera and player semi transparent
    // Derender 2 blocks above head if in building
    // Add glow effect when behind wall
    // Middle mouse moves camera



    // TODO:: Main HUD & Individual Widgets
    // Setup a main HUD where widgets can be added similar to buttons on a Screen
    // The HUD should be able to convery into a Screen a back
    // The Widgets should have the ability to be interacted with
    // All Widgets should have a central fixed point that is based on the relative postion in the HUD class
    //
    // The settings Screen should be a child of the new Screen class
    // All options in the Settings Screen should be built relative to a "scroll" value in the Screen class
    // The scroll wheel when the Settings Screen is open should change the "scroll" value everything is relative to
    // The width should be relative to the Screens width, so the screen can grow wider when opened to create a slide in animation
    //
    // The OSRS style hotbar needs a new HUD/Screen/Widget for each menu thats currently open
    // Widget for when on the HUD and clicking between them should call for a new screen to be made
    //
    // Make option for OSRS hotbar to only appear when in inventory


    public static final String ID = "ms_addon";

    public static final IProxy PROXY = new ClientProxy();

    private static final Logger LOGGER = LogManager.getLogger();
    public static boolean XAERO = false;
    public boolean inServer;
    public ClickOnFarmingPatch clickOnFarmingPatch;
    private XpTrackerBuilder xpTrackerBuilder;
    private HotbarBuilder statsPanel;
    private IdleChecker idleChecker;
//    private MinimapOverlayBuilder minimap;
    private InventoryBuilder inv;
    private ImageButtons imageButtons;
    private ScoreboardBuilder scoreboard;
    private IdleChecker idle;

    // Huds
    private RSHud rsHud;



    private static Main instance;
    public static Map<SkillType, Skill> skills;
    public static String[] skillsList = {"Attack", "Hitpoints", "Mining", "Strength", "Agility", "Smithing", "Defence",
            "Herblore", "Fishing", "Range", "Thieving", "Cooking", "Prayer", "Crafting", "Firemaking", "Magic",
            "Fletching", "Woodcutting", "Runecraft", "Slayer", "Farming", "Construction", "Hunter", "Total"};
    public static String[] skillsSymbolList = {"退", "逈", "逐", "送", "选", "逑", "适", "逊", "递", "逃", "逋", "逓",
            "逄", "逌", "途", "逅", "逍", "逕", "逆", "逎", "逖", "逇", "透", "速"};
    // Keybinding
    // TODO:: add keybinds to switch chats
    public static final KeyBinding settingsKey = new KeyBinding("Open Settings", GLFW.GLFW_KEY_RIGHT_ALT, "MinescapeAddon");
    public static final KeyBinding heldInventoryKey = new KeyBinding("Hold to open inventory", GLFW.GLFW_KEY_LEFT_CONTROL, "MinescapeAddon");
    public static final KeyBinding farmTimersKey = new KeyBinding("Open The Farming Timers Screen", GLFW.GLFW_KEY_LEFT_SHIFT + GLFW.GLFW_KEY_F, "MinescapeAddon");
//    public static final KeyBinding chatKey = new KeyBinding("Open The Chat to Type", GLFW.GLFW_KEY_ENTER, "MinescapeAddon");
//    public static final KeyBinding altChatKey = new KeyBinding("Alt Key to Open The Chat to Type", GLFW.GLFW_KEY_T, "MinescapeAddon");
//    public static final KeyBinding commandKey = new KeyBinding("Open The Chat to Type Commands", GLFW.GLFW_KEY_SLASH, "MinescapeAddon");


    static {
        skills = new HashMap<SkillType, Skill>();
        ClientRegistry.registerKeyBinding(settingsKey);
        ClientRegistry.registerKeyBinding(heldInventoryKey);
        ClientRegistry.registerKeyBinding(farmTimersKey);
//        ClientRegistry.registerKeyBinding(chatKey);
//        ClientRegistry.registerKeyBinding(altChatKey);
//        ClientRegistry.registerKeyBinding(commandKey);
    }

    public Main() throws IOException {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfiguration.CLIENT_SPEC, "Minescape_Addons_client.toml");

        // Register Items
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus(), forgeEventBus = MinecraftForge.EVENT_BUS;
        addRegistries(modEventBus);

        // Setup files
        PersistenceFile.createFiles();
        FarmingUtil.setTimersFromFile();
        setupConfigs();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
        MinecraftForge.EVENT_BUS.register(new ChatReceivedHandler());
        MinecraftForge.EVENT_BUS.register(new GuiContainerHandler());
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        MinecraftForge.EVENT_BUS.register(new GuiOpenHandler());
        MinecraftForge.EVENT_BUS.register(new ContainerCloseHandler());
        MinecraftForge.EVENT_BUS.register(new WorldLoadHandler());
        MinecraftForge.EVENT_BUS.register(new RenderPlayerHandler());
//        MinecraftForge.EVENT_BUS.register(new RenderTickHandler());

        for (ModInfo info:ModList.get().getMods()) {
            if (info.getModId().equalsIgnoreCase("xaerominimap"))
                XAERO = true;
        }

        // Populate Skills
        int i = 0;
        int i1 = -1;
        for (String ss: skillsList) {
            SkillType s = SkillType.valueOf(ss.toUpperCase());
            int slot = 9;
            slot = slot*(i + 2);
            if (i % 3 == 0)
            {
                i1++;
            }
            slot = slot - (i1*26);
            Skill skill = new Skill(ss, i, SkillType.valueOf(ss.toUpperCase()), slot, skillsSymbolList[i]);
            System.out.println(skill.getName() + " " + skill.getSymbol());

            skills.put(s, skill);

            i++;
        }

        xpTrackerBuilder = new XpTrackerBuilder();
        statsPanel = new HotbarBuilder();
        idleChecker = new IdleChecker();
//        minimap = new MinimapOverlayBuilder();
        inv = new InventoryBuilder();
        imageButtons = new ImageButtons();
        scoreboard = new ScoreboardBuilder();

        rsHud = new RSHud();


        // Create this instance
        instance = this;

        inServer = false;

        // Load Settings
        PatchType.loadPatchTypeSettings();
        PersistentExp.loadExp();
    }

    public XpTrackerBuilder getXpTrackerBuilder() {
        return xpTrackerBuilder;
    }
    public HotbarBuilder getStatsPanel() { return statsPanel; }
    public IdleChecker getIdleChecker() { return idleChecker; }
//    public MinimapOverlayBuilder getMinimap() { return minimap; }
    public InventoryBuilder getInv() { return inv; }
    public ImageButtons getImageButtons() { return imageButtons; }
    public ScoreboardBuilder getScoreboard() { return scoreboard; }


    public RSHud getRsHud() { return rsHud; }


    public static Main getInstance() {
        return instance;
    }


    private void addRegistries(final IEventBus modEventBus) {
        MinescapeItems.ITEMS.register(modEventBus);
    }


    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void setupConfigs() {
        // Verify directories
        File propDirectory = new File(MainProperties.PROPDIR);
        File widgetDir = new File("minescape/properties/widgets");
        // Primary config
        File mainConfigPath = new File(MainProperties.PATH);

        // Configs
        Properties mainConfig = new Properties();
        try {
            // Check if directories exist
            if (!propDirectory.exists())
                propDirectory.mkdirs();
            if (!widgetDir.exists())
                widgetDir.mkdirs();
            // Check if config files exist
            if (!mainConfigPath.exists()) {
                mainConfigPath.createNewFile();
                // Load defaults for config files
                InputStream mainConfigInput = this.getClass().getClassLoader().getResourceAsStream("configs/mainconfig.properties");
                mainConfig.load(mainConfigInput);
                // Save default config files
                OutputStream mainConfigOutput = new FileOutputStream(MainProperties.PATH);
                mainConfig.store(mainConfigOutput, null);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
