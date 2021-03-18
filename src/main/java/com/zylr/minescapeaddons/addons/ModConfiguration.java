package com.zylr.minescapeaddons.addons;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.text.StyledEditorKit;

public class ModConfiguration {

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    public static class Client {

        public final ForgeConfigSpec.BooleanValue small;
        public final ForgeConfigSpec.BooleanValue hotbar;
        public final ForgeConfigSpec.BooleanValue exp;
        public final ForgeConfigSpec.BooleanValue thurgo;
        public final ForgeConfigSpec.BooleanValue idleAlert;
        public final ForgeConfigSpec.BooleanValue verticalBar;
        public final ForgeConfigSpec.BooleanValue virtualLevel;
        public final ForgeConfigSpec.BooleanValue rsInventory;
        public final ForgeConfigSpec.BooleanValue darkInventory;
        public final ForgeConfigSpec.BooleanValue renderHealth;
        public final ForgeConfigSpec.BooleanValue renderHotbar;
        public final ForgeConfigSpec.BooleanValue renderXpBar;
        public final ForgeConfigSpec.BooleanValue renderMapOverlay;
        public final ForgeConfigSpec.BooleanValue protectedSlot;
        public final ForgeConfigSpec.BooleanValue persistentMouse;
        public final ForgeConfigSpec.BooleanValue farmingLoginMessage;
        public final ForgeConfigSpec.BooleanValue customArmor;
        public final ForgeConfigSpec.BooleanValue unrenderFarmStands;
        public final ForgeConfigSpec.BooleanValue debugOff;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Variables to set Gui").push("gui");
            small = builder.define("small", false);

            hotbar = builder.define("show_hotbar_and_tabs", true);

            exp = builder.define("exp_tracker", true);

            thurgo = builder.define("thurgo", false);

            idleAlert = builder.define("idle_alert", false);

            verticalBar = builder.define("vertical_hotbar", false);

            virtualLevel = builder.define("virtual_level", false);

            rsInventory = builder.define("runescape_style_inventory", false);

            darkInventory = builder.define("darkened_inventory", false);

            renderHealth = builder.define("render_health", false);

            renderHotbar = builder.define("redner_hotbar", false);

            renderXpBar = builder.define("render_xp_bar", false);

            renderMapOverlay = builder.define("render_map_overlay", true);

            protectedSlot = builder.define("protectedSlot", false);

            persistentMouse = builder.define("persistent_inventory_mouse", true);

            farmingLoginMessage = builder.define("farming_login_message", true);

            customArmor = builder.define("use_custom_armor_overrides", true);

            unrenderFarmStands = builder.define("unrender_farming_armorstands", false);

            debugOff = builder.define("debugOff", false);

            builder.pop();
        }
    }
    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading e) {

    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading event) {

    }
}
