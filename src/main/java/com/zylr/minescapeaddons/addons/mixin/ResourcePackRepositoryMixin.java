package com.zylr.minescapeaddons.addons.mixin;

import com.google.common.collect.ImmutableList;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.MinimapWidget;
import com.zylr.minescapeaddons.addons.resource.FontOverridePackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(PackRepository.class)
public class ResourcePackRepositoryMixin {
    @Shadow
    private Map<String, Pack> available;

    @Inject(method = "rebuildSelected", at = @At("RETURN"), cancellable = true)
    private void modifyPackOrder(Collection<String> ids, CallbackInfoReturnable<List<Pack>> cir) {
        MinimapWidget.clearColorCaches();

        List<Pack> packs = new java.util.ArrayList<>(cir.getReturnValue());

        // Add font pack to the list if not already there
        if (Config.getFixedMode() && Config.getSmallInventory()) {
            Pack fontPack = available.get("msaddon:font_override");
            if (fontPack != null) {
                packs.removeIf(p -> p.getId().equals("msaddon:font_override"));
                packs.add(fontPack); // add to end temporarily before sort
            }
        } else {
            packs.removeIf(p -> p.getId().equals("msaddon:font_override"));
        }

        if (Config.getForceResourcePack()) {
            packs.sort((packA, packB) -> {
                int priorityA = getPackPriority(packA.getId());
                int priorityB = getPackPriority(packB.getId());
                return Integer.compare(priorityA, priorityB);
            });
        }

        cir.setReturnValue(ImmutableList.copyOf(packs));
    }

    private int getPackPriority(String packId) {
        if (packId.equals("msaddon:font_override")) {
            return 2; // Below mod resources, above server pack
        }
        if (packId.contains("msaddon") || packId.equals("mod_resources")) {
            return 3; // Highest priority - mod resources on top
        }
        if (packId.startsWith("mod/") || packId.startsWith("file/")) {
            return 2;
        }
        if (packId.startsWith("server/")) {
            return 1;
        }
        if (packId.equals("vanilla")) {
            return 0;
        }
        return 1;
    }


    @Inject(method = "reload", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/packs/repository/PackRepository;rebuildSelected(Ljava/util/Collection;)Ljava/util/List;"
    ))
    private void injectFontPack(CallbackInfo ci) {
        if (Config.getFixedMode() && Config.getSmallInventory()) {
            PackLocationInfo info = new PackLocationInfo(
                    "msaddon:font_override",
                    net.minecraft.network.chat.Component.literal("Runescape Font Override"),
                    net.minecraft.server.packs.repository.PackSource.BUILT_IN,
                    java.util.Optional.empty()
            );

            Pack pack = Pack.readMetaAndCreate(
                    info,
                    new Pack.ResourcesSupplier() {
                        @Override
                        public PackResources openPrimary(PackLocationInfo info) {
                            return new FontOverridePackResources();
                        }

                        @Override
                        public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
                            return new FontOverridePackResources();
                        }
                    },
                    PackType.CLIENT_RESOURCES,
                    new PackSelectionConfig(true, Pack.Position.TOP, false)
            );

            if (pack != null) {
                // Replace the ImmutableMap with a mutable one containing our pack
                Map<String, Pack> mutableAvailable = new java.util.LinkedHashMap<>(available);
                mutableAvailable.put(pack.getId(), pack);
                available = mutableAvailable;
                System.out.println("[MSAddon] Font pack added to available: " + pack.getId());
            } else {
                System.out.println("[MSAddon] Font pack creation FAILED (pack is null)");
            }
        } else {
            if (available.containsKey("msaddon:font_override")) {
                Map<String, Pack> mutableAvailable = new java.util.LinkedHashMap<>(available);
                mutableAvailable.remove("msaddon:font_override");
                available = mutableAvailable;
            }
        }
    }
}