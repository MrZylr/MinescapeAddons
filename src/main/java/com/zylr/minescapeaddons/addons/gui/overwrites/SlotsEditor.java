package com.zylr.minescapeaddons.addons.gui.overwrites;

import net.minecraft.inventory.container.Slot;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class SlotsEditor{

    public static final String xPos = "field_75223_e";
    public static final String yPos = "field_75221_f";
//    public static final String xPos = "xPos";
//    public static final String yPos = "yPos";

    public static void setxPos(Slot slot, int x) {
        ObfuscationReflectionHelper.setPrivateValue(Slot.class, slot, x, xPos);
    }

    public static void setyPos(Slot slot, int y) {
        ObfuscationReflectionHelper.setPrivateValue(Slot.class, slot, y, yPos);
    }
}
