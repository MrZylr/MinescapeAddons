package com.zylr.minescapeaddons.addons;

import com.mojang.logging.LogUtils;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.events.ModClientEventbusEvents;
import com.zylr.minescapeaddons.addons.gui.huds.Hud;
import com.zylr.minescapeaddons.addons.gui.huds.resizableclassic.ResizableClassic;
import com.zylr.minescapeaddons.addons.gui.huds.resizableclassic.ResizableClassicChildren;
import com.zylr.minescapeaddons.addons.gui.renderers.RenderThurgo;
import com.zylr.minescapeaddons.addons.gui.widgets.CustomScoreboard;
import com.zylr.minescapeaddons.addons.item.ModItems;
import com.zylr.minescapeaddons.addons.properties.MainProperties;
import com.zylr.minescapeaddons.addons.properties.PersistenceFile;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.objectweb.asm.Type;
import org.slf4j.Logger;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MinescapeAddons.MOD_ID)
public class MinescapeAddons {
    public Scoreboard scoreboard;
    public static Map<SkillType, Skill> skills;
    public static String[] skillsList = {"Attack", "Hitpoints", "Mining", "Strength", "Agility", "Smithing", "Defence",
            "Herblore", "Fishing", "Ranged", "Thieving", "Cooking", "Prayer", "Crafting", "Firemaking", "Magic",
            "Fletching", "Woodcutting", "Runecraft", "Slayer", "Farming", "Construction", "Hunter", "Total"};
    public static String[] skillsSymbolList = {"退", "逈", "逐", "送", "选", "逑", "适", "逊", "递", "逃", "逋", "逓",
            "逄", "逌", "途", "逅", "逍", "逕", "逆", "逎", "逖", "逇", "透", "速"};
    public int timesXpShown;
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "msaddon";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public Player player;

    private static MinescapeAddons instance;
    private static final ResourceLocation RUNESCAPE_SMALL_FONT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "runescape_bold");
    private static final ResourceLocation VANILLA_FONT = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "default");
    private Font runescapeSmallFont;
    private final Font vanillaFont;

    public ResizableClassic resizableClassic;
    public ResizableClassicChildren resizableClassicChildren;

    CustomScoreboard customScoreboard;
    public Hud activeHud;
    public RenderThurgo thurgo;


    static  {
        skills = new HashMap<SkillType, Skill>();
    }
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MinescapeAddons(IEventBus modEventBus, ModContainer modContainer) {

        instance = this;
        this.player = new Player();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ModItems.REGISTRY.register(modEventBus);

        // Register this separately because the instance already exists. We could make these all singleton pattern, with an interface implementation to return the instance, but I dont wanna.
        NeoForge.EVENT_BUS.register(this);

        RegisterEvents();

        modEventBus.addListener(ModClientEventbusEvents::onRegisterClientExtensions);
        modEventBus.addListener(ModClientEventbusEvents::onRegisterClientReloadListeners);

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
            Skill skill = new Skill(ss, i, s, slot, skillsSymbolList[i]);
            skill.r = s.r;
            skill.g = s.g;
            skill.b = s.b;
            System.out.println(skill.getName() + " " + skill.getSymbol());

            skills.put(s, skill);

            i++;
        }
        //Create Widgets
        PersistenceFile.createFiles();
        FarmingUtil.setTimersFromFile();
        setupConfigs();
        this.resizableClassic = new ResizableClassic();
        this.resizableClassicChildren = new ResizableClassicChildren();
        this.activeHud = this.resizableClassic;
        this.thurgo = null;

        // create custom font

        Minecraft mc = Minecraft.getInstance();

        runescapeSmallFont = new Font(location -> {
            return mc.fontManager.getFontSetRaw(RUNESCAPE_SMALL_FONT);
        }, false);
        vanillaFont = new Font(location -> {
            return mc.fontManager.getFontSetRaw(VANILLA_FONT);
        }, false);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

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

    private void RegisterEvents() {
        ModList.get().getAllScanData().forEach(scanData -> {
            scanData.getAnnotatedBy(Listener.class, ElementType.TYPE).forEach(annotationData -> {
                Type type = annotationData.clazz();
                try {
                    Class<?> clazz = Class.forName(type.getClassName());
                    if(clazz.isAnnotationPresent(Listener.class)) {
                        Constructor<?> constructor = clazz.getConstructor();
                        Object instance = constructor.newInstance();
                        NeoForge.EVENT_BUS.register(instance);
                    }
                } catch (ReflectiveOperationException e) {
                    //Soft fail in case some other mod the user is using somehow does some weird shit.
                    LogUtils.getLogger().error("Exception for " + type.getClassName() + " : " + type.getDescriptor() + "\n" + e.getMessage());
                } catch (NoClassDefFoundError ignored) { }

            });
        });
    }

    public Font getCustomFont() {
        return this.runescapeSmallFont;
    }
    public Font getVanillaFont() { return this.vanillaFont; }

    public static MinescapeAddons getInstance() {
        return instance;
    }
    public CustomScoreboard getScoreboard() { return this.customScoreboard; }
    public void setScoreboard(CustomScoreboard scoreboard) { this.customScoreboard = scoreboard; }
    public RenderThurgo getThurgo() { return thurgo; }
    public void setThurgo(RenderThurgo thurgo) { this.thurgo = thurgo; }
}
