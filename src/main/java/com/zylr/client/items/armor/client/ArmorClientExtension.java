package com.zylr.client.items.armor.client;

import com.zylr.client.items.armor.client.model.ArmorModel;
import com.zylr.client.items.armor.client.provider.ArmorModelProvider;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;

public class ArmorClientExtension {
    private static final Map<Item, ArmorModelProvider> MODEL_PROVIDERS = new IdentityHashMap<>();

    private final ArmorModelProvider provider;

    public ArmorClientExtension(ArmorModelProvider provider) {
        this.provider = provider;
    }

    public static void register(Item item, ArmorModelProvider provider) {
        MODEL_PROVIDERS.put(item, provider);
    }

    public static Optional<ArmorModelProvider> providerFor(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return Optional.empty();
        return Optional.ofNullable(MODEL_PROVIDERS.get(stack.getItem()));
    }

    public @NotNull ArmorModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
        ArmorModel armorModel = provider.getModel(living, stack, slot);
        armorModel.partVisible(slot);
        copyModelProperties(original, armorModel);
        return armorModel;
    }

    public @NotNull Model<?> getGenericArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        ArmorModel model = getHumanoidArmorModel(livingEntity, itemStack, equipmentSlot, original);
        return model;
    }

    private void copyModelProperties(HumanoidModel<?> original, ArmorModel replacement) {
        copyPose(original.head, replacement.head);
        copyPose(original.hat, replacement.hat);
        copyPose(original.body, replacement.body);
        copyPose(original.rightArm, replacement.rightArm);
        copyPose(original.leftArm, replacement.leftArm);
        copyPose(original.rightLeg, replacement.rightLeg);
        copyPose(original.leftLeg, replacement.leftLeg);
        copyPose(original.rightLeg, replacement.rightBoot);
        copyPose(original.leftLeg, replacement.leftBoot);
    }

    private static void copyPose(ModelPart source, ModelPart target) {
        target.x = source.x;
        target.y = source.y;
        target.z = source.z;
        target.xRot = source.xRot;
        target.yRot = source.yRot;
        target.zRot = source.zRot;
        target.xScale = source.xScale;
        target.yScale = source.yScale;
        target.zScale = source.zScale;
    }
}
