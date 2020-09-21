package com.zylr.minescapeaddons.addons.camera;


public interface PrivateAccessor {
/*
    static final Logger L = LogManager.getLogger();
/*
    static final String[] MINECRAFT_TIMER = new String[] {"timer", "field_71428_T"};
    static final String[] ACTIVERENDERINFO_ROTATIONX = new String[]{"rotationX", "field_74588_d"};
    static final String[] ACTIVERENDERINFO_ROTATIONZ = new String[]{"rotationZ", "field_74586_f"};
    static final String[] ACTIVERENDERINFO_ROTATIONYZ = new String[]{"rotationYZ", "field_74587_g"};
    static final String[] ACTIVERENDERINFO_ROTATIONXZ = new String[]{"rotationXZ", "field_74589_e"};
    static final String[] ACTIVERENDERINFO_ROTATIONXY = new String[]{"rotationXY", "field_74596_h"};


    static final String MINECRAFT_TIMER = "field_71428_T";
    static final String ACTIVERENDERINFO_ROTATIONX = "field_74588_d";
    static final String ACTIVERENDERINFO_ROTATIONZ = "field_74586_f";
    static final String ACTIVERENDERINFO_ROTATIONYZ = "field_74587_g";
    static final String ACTIVERENDERINFO_ROTATIONXZ = "field_74589_e";
    static final String ACTIVERENDERINFO_ROTATIONXY = "field_74596_h";

    default Timer getTimer(Minecraft mc) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, MINECRAFT_TIMER);
        } catch (Exception ex) {
            L.error("getTimer() failed", ex);
            return null;
        }
    }

    default void setRotationX(float rotationX) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(Quaternion.class, null, rotationX, ACTIVERENDERINFO_ROTATIONX);
        } catch (Exception ex) {
            L.error("setRotationX() failed", ex);
        }
    }

    default void setRotationXZ(float rotationXZ) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(Quaternion.class, null, rotationXZ, ACTIVERENDERINFO_ROTATIONXZ);
        } catch (Exception ex) {
            L.error("setRotationXZ() failed", ex);
        }
    }

    default void setRotationZ(float rotationZ) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(Quaternion.class, null, rotationZ, ACTIVERENDERINFO_ROTATIONZ);
        } catch (Exception ex) {
            L.error("setRotationZ() failed", ex);
        }
    }

    default void setRotationYZ(float rotationYZ) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(Quaternion.class, null, rotationYZ, ACTIVERENDERINFO_ROTATIONYZ);
        } catch (Exception ex) {
            L.error("setRotationYZ() failed", ex);
        }
    }

    default void setRotationXY(float rotationXY) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(Quaternion.class, null, rotationXY, ACTIVERENDERINFO_ROTATIONXY);
        } catch (Exception ex) {
            L.error("setRotationXY() failed", ex);
        }
    }
    */
}
