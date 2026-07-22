package com.zylr.client.items.armor.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Pre-made model for custom armor models.
 */
public class ArmorModel extends HumanoidModel<HumanoidRenderState> {
    public final ModelPart leftBoot;
    public final ModelPart rightBoot;
    public final ModelPart waist;
    private final float leftBootXOffset;
    private final float leftBootYOffset;
    private final float leftBootZOffset;
    private final float rightBootXOffset;
    private final float rightBootYOffset;
    private final float rightBootZOffset;

    public float xRotAdjustment;
    public float yAdjustment;
    public float zAdjustment;
    private EquipmentSlot visibleSlot = EquipmentSlot.CHEST;

    public ArmorModel(ModelPart pRoot) {
        super(ensureHumanoidRoot(pRoot));
        this.leftBoot = getOrEmpty(this.root(), "left_boot");
        this.rightBoot = getOrEmpty(this.root(), "right_boot");
        this.waist = getOrEmpty(this.body, "waist");
        this.leftBootXOffset = this.leftBoot.x - this.leftLeg.x;
        this.leftBootYOffset = this.leftBoot.y - this.leftLeg.y;
        this.leftBootZOffset = this.leftBoot.z - this.leftLeg.z;
        this.rightBootXOffset = this.rightBoot.x - this.rightLeg.x;
        this.rightBootYOffset = this.rightBoot.y - this.rightLeg.y;
        this.rightBootZOffset = this.rightBoot.z - this.rightLeg.z;
    }

    public ArmorModel(ModelPart pRoot, Function<Identifier, RenderType> pRenderType) {
        super(ensureHumanoidRoot(pRoot), pRenderType);
        this.leftBoot = getOrEmpty(this.root(), "left_boot");
        this.rightBoot = getOrEmpty(this.root(), "right_boot");
        this.waist = getOrEmpty(this.body, "waist");
        this.leftBootXOffset = this.leftBoot.x - this.leftLeg.x;
        this.leftBootYOffset = this.leftBoot.y - this.leftLeg.y;
        this.leftBootZOffset = this.leftBoot.z - this.leftLeg.z;
        this.rightBootXOffset = this.rightBoot.x - this.rightLeg.x;
        this.rightBootYOffset = this.rightBoot.y - this.rightLeg.y;
        this.rightBootZOffset = this.rightBoot.z - this.rightLeg.z;
    }

    public void setAllVisible(boolean pVisible) {
        setPartTreeVisible(this.head, pVisible);
        setPartTreeVisible(this.hat, pVisible);
        setPartTreeVisible(this.body, pVisible);
        setPartTreeVisible(this.rightArm, pVisible);
        setPartTreeVisible(this.leftArm, pVisible);
        setPartTreeVisible(this.rightLeg, pVisible);
        setPartTreeVisible(this.leftLeg, pVisible);
        setPartTreeVisible(this.leftBoot, pVisible);
        setPartTreeVisible(this.rightBoot, pVisible);
        this.body.skipDraw = false;
    }

    /**
     * Makes only the armor part for the selected slot visible.
     */
    public void partVisible(EquipmentSlot slot) {
        this.visibleSlot = slot;
        this.setAllVisible(false);
        switch (slot) {
            case HEAD:
                this.head.visible = true;
                this.hat.visible = true;
                break;
            case CHEST:
                setPartTreeVisible(this.body, true);
                this.waist.visible = false;
                setPartTreeVisible(this.rightArm, true);
                setPartTreeVisible(this.leftArm, true);
                break;
            case LEGS:
                showLegParts();
                break;
            case FEET:
                setPartTreeVisible(this.leftBoot, true);
                setPartTreeVisible(this.rightBoot, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void setupAnim(HumanoidRenderState renderState) {
        super.setupAnim(renderState);

        syncBootPosition(this.leftBoot, this.leftLeg, this.leftBootXOffset, this.leftBootYOffset, this.leftBootZOffset);
        syncBootPosition(this.rightBoot, this.rightLeg, this.rightBootXOffset, this.rightBootYOffset, this.rightBootZOffset);

        float speedDivisor = renderState.isFallFlying ? Math.max(renderState.speedValue, 1.0F) : 1.0F;
        this.rightBoot.xRot = Mth.cos(renderState.walkAnimationPos * 0.6662F) * 1.4F * renderState.walkAnimationSpeed / speedDivisor;
        this.leftBoot.xRot = Mth.cos(renderState.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * renderState.walkAnimationSpeed / speedDivisor;
        this.rightBoot.yRot = 0.005F;
        this.leftBoot.yRot = -0.005F;
        this.rightBoot.zRot = 0.005F;
        this.leftBoot.zRot = -0.005F;
        if (renderState.isPassenger) {
            this.rightBoot.xRot = -1.4137167F;
            this.rightBoot.yRot = ((float) Math.PI / 10F);
            this.rightBoot.zRot = 0.07853982F;
            this.leftBoot.xRot = -1.4137167F;
            this.leftBoot.yRot = (-(float) Math.PI / 10F);
            this.leftBoot.zRot = -0.07853982F;
        }

        this.waist.resetPose();

        if (this.visibleSlot == EquipmentSlot.LEGS) {
            showLegParts();
        }
    }

    private static void syncBootPosition(ModelPart boot, ModelPart leg, float xOffset, float yOffset, float zOffset) {
        boot.x = leg.x + xOffset;
        boot.y = leg.y + yOffset;
        boot.z = leg.z + zOffset;
    }

    private void showLegParts() {
        setPartTreeVisible(this.body, false);
        this.body.visible = true;
        this.body.skipDraw = true;
        setPartTreeVisible(this.waist, true);
        setPartTreeVisible(this.leftLeg, true);
        setPartTreeVisible(this.rightLeg, true);
    }

    private static void setPartTreeVisible(ModelPart part, boolean visible) {
        for (ModelPart child : part.getAllParts()) {
            child.visible = visible;
        }
    }

    private static ModelPart ensureHumanoidRoot(ModelPart root) {
        String[] requiredChildren = {
            "head", "body", "right_arm", "left_arm", "right_leg", "left_leg", "left_boot", "right_boot"
        };

        boolean missingPart = false;
        for (String child : requiredChildren) {
            if (!root.hasChild(child)) {
                missingPart = true;
                break;
            }
        }
        if (!missingPart && !root.getChild("head").hasChild("hat")) {
            missingPart = true;
        }

        if (!missingPart) {
            return root;
        }

        Map<String, ModelPart> children = new HashMap<>();
        for (String child : requiredChildren) {
            children.put(child, getOrEmpty(root, child));
        }
        ModelPart head = children.get("head");
        if (!head.hasChild("hat")) {
            children.put("head", new ModelPart(List.of(), Map.of("hat", new ModelPart(List.of(), Map.of()))));
        }
        return new ModelPart(List.of(), children);
    }

    private static ModelPart getOrEmpty(ModelPart root, String child) {
        return root.hasChild(child) ? root.getChild(child) : new ModelPart(List.of(), Map.of());
    }
}
