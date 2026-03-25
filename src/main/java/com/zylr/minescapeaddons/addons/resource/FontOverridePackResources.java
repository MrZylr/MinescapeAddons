package com.zylr.minescapeaddons.addons.resource;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;

public class FontOverridePackResources implements PackResources {

    private static final ResourceLocation FONT_TARGET =
            ResourceLocation.fromNamespaceAndPath("minecraft", "font/default.json");

    private static final String FONT_JSON = """
            {
                "providers": [
                    {
                        "type": "reference",
                        "id": "msaddon:include/space"
                    },
                    {
                        "type": "reference",
                        "id": "msaddon:include/default",
                        "filter": {
                            "uniform": false
                        }
                    }
                ]
            }
            """;

    @Override
    public IoSupplier<InputStream> getRootResource(String... paths) {
        return null;
    }

    @Override
    public IoSupplier<InputStream> getResource(PackType type, ResourceLocation location) {
        if (type == PackType.CLIENT_RESOURCES && location.equals(FONT_TARGET)) {
            return () -> new ByteArrayInputStream(FONT_JSON.getBytes(StandardCharsets.UTF_8));
        }
        return null;
    }

    @Override
    public void listResources(PackType type, String namespace, String path, ResourceOutput output) {
        if (type == PackType.CLIENT_RESOURCES
                && namespace.equals("minecraft")
                && path.startsWith("font")) {
            output.accept(FONT_TARGET, () -> new ByteArrayInputStream(FONT_JSON.getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return type == PackType.CLIENT_RESOURCES ? Set.of("minecraft") : Set.of();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer) {
        if (deserializer == PackMetadataSection.TYPE) {
            return (T) new PackMetadataSection(
                    Component.literal("Runescape Font Override"),
                    SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES),
                    Optional.empty()
            );
        }
        return null;
    }

    @Override
    public PackLocationInfo location() {
        return new PackLocationInfo(
                "msaddon:font_override",
                Component.literal("Runescape Font Override"),
                PackSource.BUILT_IN,
                Optional.empty()
        );
    }

    @Override
    public String packId() {
        return "msaddon:font_override";
    }

    @Override
    public void close() {}
}