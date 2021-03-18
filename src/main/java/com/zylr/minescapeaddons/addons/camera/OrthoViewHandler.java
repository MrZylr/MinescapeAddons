package com.zylr.minescapeaddons.addons.camera;

/**
 * Key handler for keys that control the orthographic camera.
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class OrthoViewHandler implements PrivateAccessor {
    /*
    private static final Minecraft MC = Minecraft.getInstance();
    private static final String KEY_CATEGORY = "key.categories.mineshot";
    private static final float ZOOM_STEP = 0.5f;
    private static final float ROTATE_STEP = 15;
    private static final float ROTATE_SPEED = 4;
    private static final float SECONDS_PER_TICK = 1f/20f;

    private final KeyBinding keyToggle = new KeyBinding("key.mineshot.ortho.toggle", GLFW.GLFW_KEY_KP_5, KEY_CATEGORY);
    private final KeyBinding keyZoomIn = new KeyBinding("key.mineshot.ortho.zoom_in", GLFW.GLFW_KEY_KP_ADD, KEY_CATEGORY);
    private final KeyBinding keyZoomOut = new KeyBinding("key.mineshot.ortho.zoom_out", GLFW.GLFW_KEY_KP_SUBTRACT, KEY_CATEGORY);
    private final KeyBinding keyRotateL = new KeyBinding("key.mineshot.ortho.rotate_l", GLFW.GLFW_KEY_KP_4, KEY_CATEGORY);
    private final KeyBinding keyRotateR = new KeyBinding("key.mineshot.ortho.rotate_r", GLFW.GLFW_KEY_KP_6, KEY_CATEGORY);
    private final KeyBinding keyRotateU = new KeyBinding("key.mineshot.ortho.rotate_u", GLFW.GLFW_KEY_KP_8, KEY_CATEGORY);
    private final KeyBinding keyRotateD = new KeyBinding("key.mineshot.ortho.rotate_d", GLFW.GLFW_KEY_KP_2, KEY_CATEGORY);
    private final KeyBinding keyRotateT = new KeyBinding("key.mineshot.ortho.rotate_t", GLFW.GLFW_KEY_KP_7, KEY_CATEGORY);
    private final KeyBinding keyRotateF = new KeyBinding("key.mineshot.ortho.rotate_f", GLFW.GLFW_KEY_KP_1, KEY_CATEGORY);
    private final KeyBinding keyRotateS = new KeyBinding("key.mineshot.ortho.rotate_s", GLFW.GLFW_KEY_KP_3, KEY_CATEGORY);
    private final KeyBinding keyClip = new KeyBinding("key.mineshot.ortho.clip", GLFW.GLFW_KEY_KP_MULTIPLY, KEY_CATEGORY);
    private final KeyBinding keyMod = new KeyBinding("Mod", GLFW.GLFW_KEY_RIGHT_CONTROL, KEY_CATEGORY);

    //private final ToggleableClippingHelper clippingHelper = ToggleableClippingHelper.getInstance();
    private boolean clippingEnabled;

    private boolean enabled;
    private boolean freeCam;
    private boolean clip;

    private float zoom;
    private float xRot;
    private float yRot;

    private int tick;
    private int tickPrevious;
    private double partialPrevious;

    public OrthoViewHandler() {
        ClientRegistry.registerKeyBinding(keyToggle);
        ClientRegistry.registerKeyBinding(keyZoomIn);
        ClientRegistry.registerKeyBinding(keyZoomOut);
        ClientRegistry.registerKeyBinding(keyRotateL);
        ClientRegistry.registerKeyBinding(keyRotateR);
        ClientRegistry.registerKeyBinding(keyRotateU);
        ClientRegistry.registerKeyBinding(keyRotateD);
        ClientRegistry.registerKeyBinding(keyRotateT);
        ClientRegistry.registerKeyBinding(keyRotateF);
        ClientRegistry.registerKeyBinding(keyRotateS);
        ClientRegistry.registerKeyBinding(keyClip);

        reset();
    }

    private void reset() {
        freeCam = false;
        clip = false;

        zoom = 8;
        xRot = 30;
        yRot = -45;
        tick = 0;
        tickPrevious = 0;
        partialPrevious = 0;
    }

    public boolean isEnabled() {
        return enabled;
    }


    public void enable() {
        // disable in multiplayer
        // Of course, programmers could just delete this check and abuse the
        // orthographic camera, but at least the official build won't support it
        //if (!MC.isSingleplayer()) {
        //    ChatUtils.print("mineshot.orthomp");
        //    return;
        //}

        if (!enabled) {
            //clippingEnabled = clippingHelper.isEnabled();
            //clippingHelper.setEnabled(false);
            reset();
        }

        enabled = true;
    }

    public void disable() {
        //if (enabled) {
        //    clippingHelper.setEnabled(clippingEnabled);
        //}

        enabled = false;
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }


    private boolean modifierKeyPressed() {
        return keyMod.isPressed();
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent evt) {
        boolean mod = modifierKeyPressed();

        // change perspecives, using modifier key for opposite sides
        if (keyToggle.isKeyDown()) {
            if (mod) {
                freeCam = !freeCam;
            } else {
                toggle();
            }
        } else if (keyClip.isKeyDown()) {
            clip = !clip;
        } else if (keyRotateT.isKeyDown()) {
            xRot = mod ? -90 : 90;
            yRot = 0;
        } else if (keyRotateF.isKeyDown()) {
            xRot = 0;
            yRot = mod ? -90 : 90;
        } else if (keyRotateS.isKeyDown()) {
            xRot = 0;
            yRot = mod ? 180 : 0;
        }

        // update stepped rotation/zoom controls
        // note: the smooth controls are handled in onFogDensity, since they need
        // to be executed on every frame
        if (mod) {
            updateZoomAndRotation(1);

            // snap values to step units
            xRot = Math.round(xRot / ROTATE_STEP) * ROTATE_STEP;
            yRot = Math.round(yRot / ROTATE_STEP) * ROTATE_STEP;
            zoom = Math.round(zoom / ZOOM_STEP) * ZOOM_STEP;
        }
    }

    private void updateZoomAndRotation(double multi) {
        if (keyZoomIn.isKeyDown()) {
            zoom *= 1 - ZOOM_STEP * multi;
        }
        if (keyZoomOut.isKeyDown()) {
            zoom *= 1 + ZOOM_STEP * multi;
        }

        if (keyRotateL.isKeyDown()) {
            yRot += ROTATE_STEP * multi;
        }
        if (keyRotateR.isKeyDown()) {
            yRot -= ROTATE_STEP * multi;
        }

        if (keyRotateU.isKeyDown()) {
            xRot += ROTATE_STEP * multi;
        }
        if (keyRotateD.isKeyDown()) {
            xRot -= ROTATE_STEP * multi;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent evt) {
        if (!enabled || evt.phase != TickEvent.Phase.START) {
            return;
        }

        tick++;
    }

    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity evt) {
        if (!enabled) {
            return;
        }

        // update zoom and rotation
        if (!modifierKeyPressed()) {
            int ticksElapsed = tick - tickPrevious;
            double partial = evt.getRenderPartialTicks();
            double elapsed = ticksElapsed + (partial - partialPrevious);
            elapsed *= SECONDS_PER_TICK * ROTATE_SPEED;
            updateZoomAndRotation(elapsed);

            tickPrevious = tick;
            partialPrevious = partial;
        }

        float width = zoom * (MC.getMainWindow().getScaledWidth() / (float) MC.getMainWindow().getScaledHeight());
        float height = zoom;

        // override projection matrix
        GlStateManager.matrixMode(GL_PROJECTION);
        GlStateManager.loadIdentity();

        //Matrix4f.orthographic(width, height, clip ? 0 : -9999, 9999);
        Projection.ortho(-width, width, -height, height, clip ? 0 : -9999, 9999);
        // + 0.9 so the camera is centered on the player and not on its feet, but has to be + 0 for top / bottom view to be centered
        //Projection.ortho(-width, width, -height + MathHelper.cos((float) Math.toRadians(2 * xRot)) * 0.45f + 0.45f, height + MathHelper.cos((float) Math.toRadians(2 * xRot)) * 0.45f + 0.45f, clip ? 0 : -9999, 9999);

        // rotate the orthographic camera with the player view
        if (true) {
            xRot = MC.player.rotationPitch;
            yRot = MC.player.rotationYaw - 180;
        }

        // override camera view matrix
        GlStateManager.matrixMode(GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.rotatef(xRot, 1, 0, 0);
        GlStateManager.rotatef(yRot, 0, 1, 0);

        // fix particle rotation if the camera isn't following the player view
        if (!freeCam) {
            float pitch = xRot;
            float yaw = yRot + 180;
            ObfuscationReflectionHelper.setPrivateValue(ActiveRenderInfo.class, evt.getRenderer().getActiveRenderInfo(),
                    pitch, "pitch");
            ObfuscationReflectionHelper.setPrivateValue(ActiveRenderInfo.class, evt.getRenderer().getActiveRenderInfo(),
                    yaw, "yaw");
            /*
            setRotationX(MathHelper.cos(yaw * (float) Math.PI / 180f));
            setRotationZ(MathHelper.sin(yaw * (float) Math.PI / 180f));
            setRotationYZ(-Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getRotation().getZ() * MathHelper.sin(pitch * (float) Math.PI / 180f));
            setRotationXY(Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getRotation().getX() * MathHelper.sin(pitch * (float) Math.PI / 180f));
            setRotationXZ(MathHelper.cos(pitch * (float) Math.PI / 180f));


        }

    }
    */
}
