package com.zylr.client.items.cape;

import com.zylr.client.items.cape.skillcapes.MaxCapeLayer;
import com.zylr.client.items.cape.skillcapes.SkillCapeLayer;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Function;

public final class CustomCapeRegistry {
    private static final List<Function<ItemStack, CustomCape>> RESOLVERS = List.of(
        MaxCapeLayer::resolve,
        SkillCapeLayer::resolve
    );

    private CustomCapeRegistry() {
    }

    public static CustomCape resolve(ItemStack stack) {
        for (Function<ItemStack, CustomCape> resolver : RESOLVERS) {
            CustomCape cape = resolver.apply(stack);
            if (cape != null) {
                return cape;
            }
        }
        return null;
    }
}
