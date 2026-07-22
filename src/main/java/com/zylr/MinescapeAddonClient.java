package com.zylr;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonElement;
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
import com.zylr.client.AgilityShortcutOutlines;
import com.zylr.client.ArmorStandListener;
import com.zylr.client.ChatListener;
import com.zylr.client.CustomMobOutlines;
import com.zylr.client.PerfDebug;
import com.zylr.client.clue.ClueWorldMarker;
import com.zylr.client.farming.FarmingUtil;
import com.zylr.client.hud.HudManager;
import com.zylr.client.items.ModClientEventbusEvents;
import com.zylr.client.screen.BrowserScreen;
import com.zylr.client.skills.Skills;
import com.mojang.blaze3d.platform.InputConstants;
import io.netty.buffer.ByteBuf;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

import static com.zylr.MinescapeAddon.LOGGER;

public class MinescapeAddonClient implements ClientModInitializer {
	private static final KeyMapping.Category KEY_CATEGORY = KeyMapping.Category.register(
		Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "minescapeaddons")
	);
	private static final KeyMapping OPEN_VANILLA_INVENTORY_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
		"key.minescapeaddon.open_vanilla_inventory",
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_I,
		KEY_CATEGORY
	));
	private static final KeyMapping OPEN_BROWSER_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
		"key.minescapeaddon.open_browser",
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_HOME,
		KEY_CATEGORY
	));

	@Override
	public void onInitializeClient() {
		HudManager.getInstance().load();
		FarmingUtil.setTimersFromFile();
		ChatListener.register();
		ArmorStandListener.register();
		AgilityShortcutOutlines.register();
		CustomMobOutlines.register();
		ClueWorldMarker.register();
		ModClientEventbusEvents.register();
		PayloadTypeRegistry.clientboundPlay().register(MinescapePacket.TYPE, MinescapePacket.STREAM_CODEC);
		ClientPlayNetworking.registerGlobalReceiver(MinescapePacket.TYPE, (packet, context) ->
				context.client().execute(() -> handleDataOnMain(packet))
		);
		LOGGER.info("Registered Fabric payload handler for Minescape general channel");

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			HudManager.getInstance().tickXpTrackerPause(client);
			HudManager.getInstance().tickBarrowsLootReset(client);
			FarmingUtil.tickTimerCache();
			while (OPEN_VANILLA_INVENTORY_KEY.consumeClick()) {
				if (client.player == null || client.gameMode == null || client.screen != null) {
					continue;
				}

				client.setScreen(new InventoryScreen(client.player));
			}
			while (OPEN_BROWSER_KEY.consumeClick()) {
				if (client.screen != null) {
					continue;
				}

				client.setScreen(new BrowserScreen());
			}
		});

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (!world.isClientSide() || hand != InteractionHand.MAIN_HAND) {
				return InteractionResult.PASS;
			}

			if (FarmingUtil.startTimerForPatch(
				hitResult.getBlockPos(),
				world.getBlockState(hitResult.getBlockPos()).getBlock(),
				player.getMainHandItem()
			)) {
				return InteractionResult.SUCCESS;
			}

			return InteractionResult.PASS;
		});
	}



	public record MinescapePacket(String jsonData) implements CustomPacketPayload {

		public static final Type<MinescapePacket> TYPE =
				new Type<>(Identifier.parse(Channels.GENERAL.getChannelName()));

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



	public static void handleDataOnMain(final MinescapePacket packet) {
		long packetStart = PerfDebug.start();
		GeneralType type = null;
		try {
			// Handle incoming JSON data
			JsonObject jsonObject = JsonParser.parseString(packet.jsonData()).getAsJsonObject();
			Skills skills = Skills.getInstance();

			try {
				type = MinescapePacket.HANDLER.getType(jsonObject);
			} catch(Exception ex) {
				LOGGER.warn("Failed to resolve packet type from payload: {}", packet.jsonData(), ex);
				LOGGER.info("Received unknown packet type: " + jsonObject.get("type").getAsString());
				return;
			}

			HudManager hudManager = HudManager.getInstance();

			switch (type) {
				case LOGIN_SKILLS -> {
					Object data = MinescapePacket.HANDLER.getData(jsonObject);
					LoginSkillsData loginData = (LoginSkillsData) data;
					skills.applyLoginSkills(loginData);
					LOGGER.info("Loaded {} skills from login packet", loginData.levels().size());
				}
				case LOGIN_SKILL_EFFECTS -> {
					Object data = MinescapePacket.HANDLER.getData(jsonObject);
					LoginSkillEffectData loginSkillsData = (LoginSkillEffectData) data;
					skills.applyLoginSkillEffects(loginSkillsData);
					LOGGER.info("Loaded {} active skill effects from login packet", loginSkillsData.modifiers().size());
				}
				case GAMEPLAY_SKILLS_EXPERIENCE -> {
					Object data = MinescapePacket.HANDLER.getData(jsonObject);
					GameplaySkillsExperienceData expData = (GameplaySkillsExperienceData) data;
					skills.applyGameplayExperience(expData);
					hudManager.addGameplayXp(com.zylr.client.skills.SkillType.fromApi(expData.skillType()), expData.experienceGained());

				}
				case GAMEPLAY_SKILL_EFFECT -> {
					Object data = MinescapePacket.HANDLER.getData(jsonObject);
					GameplaySkillEffectData skillEffectData = (GameplaySkillEffectData) data;
					skills.applyGameplaySkillEffect(skillEffectData);
				}
				case PLAYER_TARGET -> {
					if (!hudManager.isTargetInfoEnabled()) {
						return;
					}
					Object data = MinescapePacket.HANDLER.getData(jsonObject);
					PlayerTargetData targetData = (PlayerTargetData) data;
					int totalHP = targetData.totalHp();
					UUID uuid = targetData.uuid();
					if (targetData.nameplateUuid() != null)
						uuid = targetData.nameplateUuid();
					hudManager.setTarget(uuid, totalHP);
				}
				case PLAYER_TARGET_DEATH -> {
					Object data = MinescapePacket.HANDLER.getData(jsonObject);
					PlayerTargetDeathData targetDeathData = (PlayerTargetDeathData) data;
					hudManager.recordTargetDeath(targetDeathData.uuid());
					String targetName = resolveTargetDeathName(targetDeathData, jsonObject);
					if (targetName != null) hudManager.recordBarrowsKill(targetName);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed to process Minescape packet", e);
		} finally {
			PerfDebug.record(type == null ? "packet.unknown" : "packet." + type.name(), packetStart);
		}
	}

	private static String resolveTargetDeathName(PlayerTargetDeathData targetDeathData, JsonObject jsonObject) {
		Entity target = net.minecraft.client.Minecraft.getInstance().level != null
			? net.minecraft.client.Minecraft.getInstance().level.getEntity(targetDeathData.uuid())
			: null;
		if (target != null) return target.getDisplayName().getString();
		String directName = getJsonString(jsonObject, "name", "mobName", "targetName", "entityName");
		if (directName != null) return directName;
		JsonElement data = jsonObject.get("data");
		if (data != null && data.isJsonObject()) {
			return getJsonString(data.getAsJsonObject(), "name", "mobName", "targetName", "entityName");
		}
		return null;
	}

	private static String getJsonString(JsonObject jsonObject, String... keys) {
		if (jsonObject == null) return null;
		for (String key : keys) {
			JsonElement element = jsonObject.get(key);
			if (element != null && element.isJsonPrimitive()) {
				String value = element.getAsString();
				if (value != null && !value.isBlank()) return value;
			}
		}
		return null;
	}
}
