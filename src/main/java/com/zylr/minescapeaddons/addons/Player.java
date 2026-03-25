package com.zylr.minescapeaddons.addons;

import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.utils.Timer;
import net.minecraft.client.gui.GuiGraphics;

public class Player {

    private int healthPoints;
    private int prayerPoints;
    private Timer afkTimer;

    public Player() {
        this.healthPoints = 0;
        this.prayerPoints = 0;
        afkTimer = new Timer();
        afkTimer.startTimer();
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        Skill skill = MinescapeAddons.skills.get(SkillType.HITPOINTS);
        skill.setLevelModifier(this.healthPoints - skill.getLevel());
    }

    public int getPrayerPoints() {
        return prayerPoints;
    }

    public void setPrayerPoints(int prayerPoints) {
        this.prayerPoints = prayerPoints;
        Skill skill = MinescapeAddons.skills.get(SkillType.PRAYER);
        skill.setLevelModifier(this.prayerPoints - skill.getLevel());
    }

    public boolean isAfk() {
        return this.afkTimer.getElapsedTime() >= 300000; // 5 minutes in milliseconds
    }
    public void resetAfkTimer() {
        this.afkTimer.reset();
        this.afkTimer.startTimer();
    }
    public Timer getAfkTimer() {
        return this.afkTimer;
    }

    public static void renderRedVignette(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        //System.out.println("Rendering red vignette");

        float health = MinescapeAddons.getInstance().player.getHealthPoints();
        float maxHealth = MinescapeAddons.skills.get(SkillType.HITPOINTS).getLevel();
        float healthRatio = health / maxHealth;
        float intensity = Math.max(0.0f, 0.6f - healthRatio * 0.6f);

        int alpha = (int)(intensity * 255) << 24;
        int redColor = alpha | 0x00FF0000;
        int transparent = 0x00FF0000;

        int vignetteSize = Math.min(screenWidth, screenHeight) / 3;

        // Top gradient
        guiGraphics.fillGradient(0, 0, screenWidth, vignetteSize, redColor, transparent);
        // Bottom gradient
        guiGraphics.fillGradient(0, screenHeight - vignetteSize, screenWidth, screenHeight, transparent, redColor);

        // Left gradient - full height
        for (int i = 0; i < vignetteSize; i++) {
            float progress = (float) i / vignetteSize;
            int currentAlpha = (int)((1.0f - progress) * intensity * 255) << 24;
            int currentColor = currentAlpha | 0x00FF0000;
            guiGraphics.fill(i, 0, i + 1, screenHeight, currentColor);
        }

        // Right gradient - full height
        for (int i = 0; i < vignetteSize; i++) {
            float progress = (float) i / vignetteSize;
            int currentAlpha = (int)(progress * intensity * 255) << 24;
            int currentColor = currentAlpha | 0x00FF0000;
            guiGraphics.fill(screenWidth - vignetteSize + i, 0, screenWidth - vignetteSize + i + 1, screenHeight, currentColor);
        }
    }
}
