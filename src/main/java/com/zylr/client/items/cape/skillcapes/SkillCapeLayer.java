package com.zylr.client.items.cape.skillcapes;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.MinescapeAddon;
import com.zylr.client.items.cape.CapeModel;
import com.zylr.client.items.cape.CustomCape;
import com.zylr.client.items.cape.CustomCapeRenderState;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public class SkillCapeLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    public static final Identifier TYPE = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "skill_cape");
    public static final Map<Integer, Identifier> SKILLCAPE_PICKAXE_CAPE_RESOURCES = new HashMap<>();
    public static final Map<Integer, Identifier> SKILLCAPE_SHOVEL_CAPE_RESOURCES = new HashMap<>();
    private static final Map<Integer, CustomCape> SKILLCAPE_PICKAXE_CAPES = new HashMap<>();
    private static final Map<Integer, CustomCape> SKILLCAPE_SHOVEL_CAPES = new HashMap<>();

    static {
        registerShovelCape(277, "attack");
        registerShovelCape(279, "attack_trimmed");
        registerShovelCape(1160, "defence");
        registerShovelCape(1162, "defence_trimmed");

        registerPickaxeCape(1078, "agility");
        registerPickaxeCape(1080, "agility_trimmed");
        registerPickaxeCape(1072, "construction");
        registerPickaxeCape(1074, "construction_trimmed");
        registerPickaxeCape(1108, "cooking");
        registerPickaxeCape(1110, "cooking_trimmed");
        registerPickaxeCape(1087, "crafting");
        registerPickaxeCape(1089, "crafting_trimmed");
        registerPickaxeCape(1117, "farming");
        registerPickaxeCape(1119, "farming_trimmed");
        registerPickaxeCape(1111, "firemaking");
        registerPickaxeCape(1113, "firemaking_trimmed");
        registerPickaxeCape(1105, "fishing");
        registerPickaxeCape(1107, "fishing_trimmed");
        registerPickaxeCape(1090, "fletching");
        registerPickaxeCape(1092, "fletching_trimmed");
        registerPickaxeCape(1081, "herblore");
        registerPickaxeCape(1083, "herblore_trimmed");
        registerPickaxeCape(1075, "hitpoints");
        registerPickaxeCape(1077, "hitpoints_trimmed");
        registerPickaxeCape(1096, "hunter");
        registerPickaxeCape(1098, "hunter_trimmed");
        registerPickaxeCape(1066, "magic");
        registerPickaxeCape(1068, "magic_trimmed");
        registerPickaxeCape(1099, "mining");
        registerPickaxeCape(1101, "mining_trimmed");
        registerPickaxeCape(1063, "prayer");
        registerPickaxeCape(1065, "prayer_trimmed");
        registerPickaxeCape(1060, "ranged");
        registerPickaxeCape(1062, "ranged_trimmed");
        registerPickaxeCape(1069, "runecraft");
        registerPickaxeCape(1071, "runecraft_trimmed");
        registerPickaxeCape(1093, "slayer");
        registerPickaxeCape(1095, "slayer_trimmed");
        registerPickaxeCape(1102, "smithing");
        registerPickaxeCape(1104, "smithing_trimmed");
        registerPickaxeCape(415, "strength");
        registerPickaxeCape(417, "strength_trimmed");
        registerPickaxeCape(1084, "thieving");
        registerPickaxeCape(1086, "thieving_trimmed");
        registerPickaxeCape(1114, "woodcutting");
        registerPickaxeCape(1116, "woodcutting_trimmed");
    }

    private final CapeModel skillCapeModel = new SkillCapeModel(SkillCapeModel.createBodyLayer().bakeRoot());

    public SkillCapeLayer(RenderLayerParent<AvatarRenderState, PlayerModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, AvatarRenderState renderState, float yRot, float xRot) {
        CustomCapeRenderState customCape = (CustomCapeRenderState) renderState;
        Identifier texture = customCape.minescapeaddon$getCapeTexture();
        if (texture == null || !TYPE.equals(customCape.minescapeaddon$getCapeType()) || renderState.isInvisible) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.1875F);
        submitNodeCollector.submitModel(
            this.skillCapeModel,
            renderState,
            poseStack,
            RenderTypes.entitySolid(texture),
            packedLight,
            OverlayTexture.NO_OVERLAY,
            renderState.outlineColor,
            null
        );
        poseStack.popPose();
    }

    public static CustomCape resolve(ItemStack stack) {
        if (stack.getItem() == Items.DIAMOND_PICKAXE) {
            return SKILLCAPE_PICKAXE_CAPES.get(stack.getDamageValue());
        } else if (stack.getItem() == Items.DIAMOND_SHOVEL) {
            return SKILLCAPE_SHOVEL_CAPES.get(stack.getDamageValue());
        }

        return null;
    }

    private static void registerPickaxeCape(int damage, String name) {
        Identifier capeTexture = texture(name);
        SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(damage, capeTexture);
        SKILLCAPE_PICKAXE_CAPES.put(damage, new CustomCape(TYPE, capeTexture));
    }

    private static void registerShovelCape(int damage, String name) {
        Identifier capeTexture = texture(name);
        SKILLCAPE_SHOVEL_CAPE_RESOURCES.put(damage, capeTexture);
        SKILLCAPE_SHOVEL_CAPES.put(damage, new CustomCape(TYPE, capeTexture));
    }

    private static Identifier texture(String name) {
        return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/capes/skillcapes/" + name + ".png");
    }
}
