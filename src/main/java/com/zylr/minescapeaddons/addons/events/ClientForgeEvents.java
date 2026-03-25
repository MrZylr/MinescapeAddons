package com.zylr.minescapeaddons.addons.events;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.item.cape.skillcapes.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = MinescapeAddons.MOD_ID, value = Dist.CLIENT)
public class ClientForgeEvents {
    private static boolean layersAdded = true;
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        System.out.println("########################################## ClientForgeEvents loaded ##########################################");

        // Get both player renderers - default and slim
        event.getSkins().forEach(skinName -> {
            EntityRenderer<? extends Player> renderer = event.getSkin(skinName);
            if (renderer instanceof PlayerRenderer playerRenderer && Config.getCustomCapes()) {
                System.out.println("Adding cape layer to renderer: " + skinName);
                playerRenderer.addLayer(new SkillCapeLayer(playerRenderer, event.getEntityModels()));
                playerRenderer.addLayer(new MaxCapeLayer(playerRenderer, event.getEntityModels()));
            }
        });
    }
}