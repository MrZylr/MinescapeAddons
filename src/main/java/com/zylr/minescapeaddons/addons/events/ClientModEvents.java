package com.zylr.minescapeaddons.addons.events;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.item.cape.skillcapes.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = MinescapeAddons.MOD_ID)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        System.out.println("################################REGISTERING LAYERS###############################");
        event.registerLayerDefinition(SkillCapeLayer.createModelLayer(), SkillCapeModel::createBodyLayer);
        event.registerLayerDefinition(MaxCapeLayer.createModelLayer(), MaxCapeModel::createBodyLayer);
    }
}
