package com.zylr.minescapeaddons.addons.mixin;

import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Placeholder — viewport narrowing is handled entirely by ViewportAdjustmentListener
 * via glViewport + projection matrix correction on RenderLevelStageEvent.
 */
@Mixin(Camera.class)
public class CameraMixin {
}
