package com.zylr.minescapeaddons.addons.skills;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.skills.tracker.XpTracker;
import org.slf4j.Logger;

public class Skill {
    private String name;
    private String symbol;
    private int id;
    private int level;
    private int modifiedLevel;
    private int levelModifier;
    private int previousLevel;
    private double exp;
    private double nextLevel;
    private double tilNextLevel;
    private int slot;
    private float invX;
    private float invY;
    private SkillType type;
    private XpTracker tracker;
    public float r;
    public float g;
    public float b;

    public static final Logger LOGGER = MinescapeAddons.LOGGER;


    public Skill(String name, int id, SkillType type, int slot, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.id = id;
        this.type = type;
        this.slot = slot;
        this.level = 1;
        this.levelModifier = 0;
        this.modifiedLevel = this.level+this.levelModifier;
        this.previousLevel = 1;
        this.exp = 1;
        this.nextLevel = 1;
        this.tilNextLevel = 1;
        this.invX = 0;
        this.invY = 0;
        this.tracker = new XpTracker(this);
    }

    public boolean shouldLvlUp() {
        if (type == SkillType.TOTAL)
            return false;
        if (getLevelAtExperience(exp) > level)
            return true;
        return false;
    }

    public void levelUp() {

        if (type == SkillType.TOTAL)
            return;
        previousLevel = level;
        level = getLevelAtExperience(exp);
        nextLevel = getExperienceAtLevel(level+1);
        tilNextLevel = (int)nextLevel - (int)exp;
        Skill.calcTotalLevel();
        previousLevel = level;
    }

    public static int calcTotalLevel() {
        int totalLevel = 0;
        for (String s : MinescapeAddons.skillsList) {
            SkillType type = SkillType.valueOf(s.toUpperCase());
            if (type != SkillType.TOTAL) {
                Skill skill = MinescapeAddons.skills.get(type);
                totalLevel =  totalLevel + skill.getLevel();
            }
            MinescapeAddons.skills.get(SkillType.TOTAL).setLevel(totalLevel);
        }

        return totalLevel;
    }

    private void addLevels(int i) {
        level += i;
    }

    public void addExp(double xp) {
        double oldExp = this.exp;
        this.exp += xp;

        double expGained = this.exp - oldExp;

        // Drop the decimal places for the xp gained and total xp for the orb display
        int oldExpAsInt = (int) oldExp;
        int totalExpAsInt = (int) this.exp;
        int displayedExp = totalExpAsInt - oldExpAsInt;

        this.nextLevel = getExperienceAtLevel(this.level + 1);
        this.tilNextLevel = (int)this.nextLevel - (int)this.exp;
        this.getTracker().setXp(xp);
        if (this.type != SkillType.TOTAL)
            MinescapeAddons.skills.get(SkillType.TOTAL).addExp(xp);
        if (shouldLvlUp())
            levelUp();
        if (this.type != SkillType.TOTAL)
            MinescapeAddons.getInstance().resizableClassic.getOrbWidget().addXpOrb(new XpOrb(this, displayedExp));
    }

    // Private methods
    public int getExperienceAtLevel(int level){
        double total = 0;
        for (int i = 1; i < level; i++)
        {
            total += Math.floor(i + 300 * Math.pow(2, i / 7.0));
        }

        return (int) Math.floor(total / 4);
    }

    public int getLevelAtExperience(double experience) {
        int index;

        for (index = 0; index < 120; index++) {
            if (index == 99 && !Config.getVirtualLevels())
                break;
            if (getExperienceAtLevel(index + 1) > experience)
                break;
        }

        return index;
    }

    public double getTotalExp() {
        double total = 0;

        for (SkillType skillType:SkillType.values()) {
            if (skillType != SkillType.TOTAL) {
                total += MinescapeAddons.getInstance().skills.get(skillType).getExp();
            }
        }

        return total;
    }

    public void setupLevel(double exp) {
        this.exp = exp;
        this.level = getLevelAtExperience(this.exp);
        this.nextLevel = getExperienceAtLevel(this.level + 1);
        this.tilNextLevel = (int)this.nextLevel - (int)this.exp;
        this.previousLevel = this.level;
    }

    public static void handleLogin(SkillType skillType, double exp, int level) {
        Skill skill = MinescapeAddons.skills.get(skillType);
        skill.setExp(exp);
        skill.setLevel(level);
        skill.setupLevel(exp);
    }

    // Getters
    public int getModifiedLevel() {
        this.modifiedLevel = this.level + this.levelModifier;
        if (this.level > 99 && this.levelModifier > 0)
            this.modifiedLevel = 99 + this.levelModifier;
        return this.modifiedLevel;
    }

    public int getLevelModifier() { return levelModifier; }

    public String getName() { return name; }

    public String getSymbol() { return symbol; }

    public int getId() { return id; }

    public int getLevel() { return level; }

    public int getPreviousLevel() { return previousLevel; }

    public double getExp() { return exp; }

    public double getNextLevel() { return nextLevel; }

    public double getTilNextLevel() { return tilNextLevel; }

    public int getSlot() { return slot; }

    public SkillType getType() {return type;}

    public float getInvX() { return invX; }

    public float getInvY() { return invY; }

    public XpTracker getTracker() { return tracker; }

    // Setters
    public void setLevelModifier(int levelModifier) {
        this.levelModifier = levelModifier;
    }

    public void setLevel(int level) { this.level = level; }

    public void setPreviousLevel(int previousLevel) { this.level = previousLevel; }

    public void setInvX(float invX) { this.invX = invX; }

    public void setInvY(float invY) { this.invY = invY; }

    public void setExp(double exp) { this.exp = exp; }

    public void setNextLevel(double nextLevel) { this.nextLevel = nextLevel; }

    public void setTilNextLevel(double tilNextLevel) { this.tilNextLevel = tilNextLevel; }
}
