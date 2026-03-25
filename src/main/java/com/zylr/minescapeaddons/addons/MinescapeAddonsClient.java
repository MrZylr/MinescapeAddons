package com.zylr.minescapeaddons.addons;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.minescape.mod.api.channel.ChannelDataHandler;
import com.minescape.mod.api.channel.Channels;
import com.minescape.mod.api.channel.general.GeneralType;
import com.minescape.mod.api.channel.general.skills.GameplaySkillEffectData;
import com.minescape.mod.api.channel.general.skills.GameplaySkillsExperienceData;
import com.minescape.mod.api.channel.general.skills.LoginSkillEffectData;
import com.minescape.mod.api.channel.general.skills.LoginSkillsData;
import com.minescape.mod.api.channel.general.target.PlayerTargetData;
import com.minescape.mod.api.channel.general.target.PlayerTargetDeathData;
import com.mojang.logging.LogUtils;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingPatchClicked;
import com.zylr.minescapeaddons.addons.skills.tracker.XpTracker;
import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MinescapeAddons.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MinescapeAddons.MOD_ID, value = Dist.CLIENT)
public class MinescapeAddonsClient {


    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // Packet Handeling code provided by Dablakbandit
    public MinescapeAddonsClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        MinescapeAddons.LOGGER.info("HELLO FROM CLIENT SETUP");
        MinescapeAddons.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    public record MinescapePacket(String jsonData) implements CustomPacketPayload {

        public static final Type<MinescapePacket> TYPE =
                new Type<>(ResourceLocation.parse(Channels.GENERAL.getChannelName()));

        private static final ChannelDataHandler<GeneralType> HANDLER =
                new ChannelDataHandler<>(Channels.GENERAL, GeneralType.class);

        public static final StreamCodec<ByteBuf, MinescapePacket> STREAM_CODEC = StreamCodec.of(
                (byteBuf, packet) ->{
                    ByteArrayDataOutput output = ByteStreams.newDataOutput();
                    output.writeUTF(packet.jsonData);
                    byteBuf.writeBytes(output.toByteArray());
                }, (data) -> {
                    // Create a byte array and copy data from ByteBuf
                    byte[] bytes = new byte[data.readableBytes()];
                    data.readBytes(bytes);
                    ByteArrayDataInput input = ByteStreams.newDataInput(bytes);
                    String json = input.readUTF();
                    return new MinescapePacket(json);
                }
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event){
        PayloadRegistrar registrar = event.registrar("1").optional();
        registrar.playToClient( MinescapePacket.TYPE,
                MinescapePacket.STREAM_CODEC,
                MinescapeAddonsClient::handleDataOnMain);
        LOGGER.info("Registered payload handler for MyData on main thread");
    }

    public static void handleDataOnMain(final MinescapePacket packet, final IPayloadContext context) {
        // You can send a response back to the server if needed
        // context.sendToServer(new MyData("Response from client"));

        try {
            // Handle incoming JSON data
            JsonObject jsonObject = JsonParser.parseString(packet.jsonData()).getAsJsonObject();
            GeneralType type;

            try {
                type = MinescapePacket.HANDLER.getType(jsonObject);
            } catch(Exception ex) {
                ex.printStackTrace();
                LOGGER.info("Received unknown packet type: " + jsonObject.get("type").getAsString());
                return;
            }

            Object data = MinescapePacket.HANDLER.getData(jsonObject);

            switch (type) {
                case LOGIN_SKILLS -> {
                    LoginSkillsData loginData = (LoginSkillsData) data;
                    loginData.levels().forEach((skill, level) -> {
                        double exp = loginData.getExperience(skill);
                        if (skill == com.minescape.mod.api.types.skills.SkillType.RUNECRAFTING) {
                            Skill.handleLogin(SkillType.RUNECRAFT, exp, level);
                            LOGGER.info("RUNECRAFT: " + level + " : " + exp);
                        }else {
                            Skill.handleLogin(SkillType.valueOf(skill.name().toUpperCase()), exp, level);
                            LOGGER.info(skill.name() + ": " + level + " : " + exp);
                        }
                    });
                    Skill.calcTotalLevel();
                    LOGGER.info("Unpausing all trackers");
                    XpTracker.unpauseAllTrackers();
                }
                case LOGIN_SKILL_EFFECTS -> {
                    LoginSkillEffectData loginSkillsData = (LoginSkillEffectData) data;
                    LOGGER.info(data.toString());

                    loginSkillsData.modifiers().forEach((skillType, modifier) -> {
                        if (skillType == com.minescape.mod.api.types.skills.SkillType.RUNECRAFTING) {
                            Skill skill = MinescapeAddons.skills.get(SkillType.RUNECRAFT);
                            skill.setLevelModifier(modifier);
                        } else {
                            SkillType mySkillType = SkillType.valueOf(skillType.name().toUpperCase());
                            Skill skill = MinescapeAddons.skills.get(mySkillType);
                            skill.setLevelModifier(modifier);
                        }
                    });
                }
                case GAMEPLAY_SKILLS_EXPERIENCE -> {
                    GameplaySkillsExperienceData expData = (GameplaySkillsExperienceData) data;
                    com.minescape.mod.api.types.skills.SkillType skillType = expData.skillType();
                    double expGained = expData.experienceGained();
                    double totalExp = expData.totalExperience();

                    Skill skill = null;
                    if (skillType == com.minescape.mod.api.types.skills.SkillType.RUNECRAFTING)
                        skill = MinescapeAddons.skills.
                                get(com.zylr.minescapeaddons.addons.skills.SkillType.RUNECRAFT);
                    else
                        skill = MinescapeAddons.skills.
                                get(SkillType.valueOf(skillType.name().toUpperCase()));
                    skill.addExp(expGained);
                    MinescapeAddons.getInstance().player.resetAfkTimer();
                    LOGGER.info(skill.getName() + ": " + expGained + " Total Exp: " + totalExp);

                    // Code for Farming timers
                    if (skill.getType() == SkillType.FARMING) {
                        if (FarmingPatchClicked.patchClicked)
                            FarmingPatchClicked.xpGained = true;
                    }
                }
                case GAMEPLAY_SKILL_EFFECT -> {
                    GameplaySkillEffectData skillEffectData = (GameplaySkillEffectData) data;
                    LOGGER.info(skillEffectData.toString());
                    Skill skill;
                    if (skillEffectData.skillType() == com.minescape.mod.api.types.skills.SkillType.RUNECRAFTING) {
                        skill = MinescapeAddons.skills.get(SkillType.RUNECRAFT);
                    } else {
                        SkillType skillType = SkillType.valueOf(skillEffectData.skillType().name().toUpperCase());
                        skill = MinescapeAddons.skills.get(skillType);
                    }
                    if (skill != null) {
                        skill.setLevelModifier(skillEffectData.newModifier());
                    }
                }
                case PLAYER_TARGET -> {
                    PlayerTargetData targetData = (PlayerTargetData) data;
                    LOGGER.info(targetData.toString());

                    UUID uuid = targetData.uuid();
                    Double range = EntityTracker.getRange();
                    if (uuid == null) {
                        //EntityTracker.setTargetedEntity(null);
                        //EntityTracker.setTotalHp(0);
                    } else if (EntityTracker.isEntityNearby(uuid, range)) {
                        System.out.println("SETTING TARGET");
                        EntityTracker.setTargetedEntity(EntityTracker.findNearbyEntityByUUID(uuid, range).get());
                        EntityTracker.setTotalHp(targetData.totalHp());
                    }
                }
                case PLAYER_TARGET_DEATH -> {
                    PlayerTargetDeathData targetDeathData = (PlayerTargetDeathData) data;
                    LOGGER.info(targetDeathData.toString());

                    UUID uuid = targetDeathData.uuid();
                    if (EntityTracker.getTargetedEntity() != null && EntityTracker.getTargetedEntity().getUUID().equals(uuid)) {
                        EntityTracker.setTargetState(EntityTracker.TargetState.DEAD);
                        MinescapeAddons.getInstance().resizableClassic.getTargetInfoWidget().triggerDeath();
                        EntityTracker.resetTargetedEntity();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to process Minescape packet: " + e.getMessage());
        }
    }

}
