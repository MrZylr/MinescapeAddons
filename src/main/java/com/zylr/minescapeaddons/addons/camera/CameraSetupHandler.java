package com.zylr.minescapeaddons.addons.camera;

public class CameraSetupHandler {
    /*
    @SubscribeEvent(   priority = EventPriority.LOWEST,
            receiveCanceled = true)
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event){


        if(Minecraft.getInstance().gameSettings.thirdPersonView == 1){
            final ActiveRenderInfo info = event.getInfo();

            float prev = ObfuscationReflectionHelper.getPrivateValue(ActiveRenderInfo.class, info, "previousHeight");
            float height = ObfuscationReflectionHelper.getPrivateValue(ActiveRenderInfo.class, info, "height");

            double x = MathHelper.lerp(event.getRenderPartialTicks(), info.getRenderViewEntity().prevPosX, info.getRenderViewEntity().getPosX());
            double y = MathHelper.lerp(event.getRenderPartialTicks(), info.getRenderViewEntity().prevPosY, info.getRenderViewEntity().getPosY()) + MathHelper.lerp(event.getRenderPartialTicks(), prev, height);
            double z = MathHelper.lerp(event.getRenderPartialTicks(), info.getRenderViewEntity().prevPosZ, info.getRenderViewEntity().getPosZ());

            try{
                Method setPosition =  ObfuscationReflectionHelper.findMethod(ActiveRenderInfo.class, "setPosition", double.class, double.class, double.class);
                Method movePosition = ObfuscationReflectionHelper.findMethod(ActiveRenderInfo.class, "movePosition", double.class, double.class, double.class);
                Method setDirection = ObfuscationReflectionHelper.findMethod(ActiveRenderInfo.class, "setDirection", float.class, float.class);
                Method setDistance = ObfuscationReflectionHelper.findMethod(ActiveRenderInfo.class, "calcCameraDistance", double.class);
                setPosition.invoke(info, x, y, z);


                //setDirection.invoke(info,   event.getPitch()* MathHelper.cos(36 * (float) Math.PI / 180f),
                 //                           MathHelper.cos(event.getYaw() * (float) Math.PI / 180f));
                //System.out.println(Minecraft.getInstance().player.getYaw(Minecraft.getInstance().getRenderPartialTicks()));
                //  setDirection.invoke(info, xRot + 180.0F, -328);

                movePosition.invoke(info, setDistance.invoke(info, -30), 2, -1);

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
    */
}
