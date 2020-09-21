package com.zylr.minescapeaddons.addons.skills.hitpoints;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;

public class CurrentHealth {
    public static double calculateCurrentHealth() {
        ClientPlayerEntity p = Minecraft.getInstance().player;
/*
        double percentageMcHealth = p.getHealth()/20;
        double rsHealth = Main.getInstance().skills.get(SkillType.HITPOINTS).getLevel()*percentageMcHealth;

        double modifiedHealth = rsHealth + 0.5;

        if ((int)rsHealth < (int)modifiedHealth)
            return (int)rsHealth+1;
        return (int)rsHealth;*/

        // Prayer
        double percentageMcHealth = p.experience;
        double rsHealth = Main.getInstance().skills.get(SkillType.PRAYER).getLevel()*percentageMcHealth;

        double modifiedHealth = rsHealth + 0.5;

        if ((int)rsHealth < (int)modifiedHealth)
            return (int)rsHealth+1;
        return (int)rsHealth;
    }
}
