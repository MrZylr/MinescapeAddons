package com.zylr.client.items.armor.client.provider;

import com.zylr.client.items.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.EnumMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleModelProvider implements ArmorModelProvider{
    private final Supplier<LayerDefinition> definitionSupplier;
    private final Function<ModelPart, ArmorModel> modelFactory;
    private final EnumMap<EquipmentSlot, ArmorModel> models = new EnumMap<>(EquipmentSlot.class);

    public SimpleModelProvider(Supplier<LayerDefinition> definitionSupplier, Function<ModelPart, ArmorModel> model) {
        this.definitionSupplier = definitionSupplier;
        this.modelFactory = model;
    }

    @Override
    public ArmorModel getModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot) {
        EquipmentSlot slot = equipmentSlot != null ? equipmentSlot : EquipmentSlot.CHEST;
        return this.models.computeIfAbsent(slot, ignored -> this.modelFactory.apply(this.definitionSupplier.get().bakeRoot()));
    }
}
