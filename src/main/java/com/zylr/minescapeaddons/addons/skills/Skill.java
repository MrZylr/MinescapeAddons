package com.zylr.minescapeaddons.addons.skills;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.skills.tracker.XpTracker;

public class Skill {
    private String name;
    private String symbol;
    private int id;
    private int level;
    private int previousLevel;
    private int exp;
    private int nextLevel;
    private int tilNextLevel;
    private int slot;
    private float invX;
    private float invY;
    private SkillType type;
    private XpTracker tracker;


    public Skill(String name, int id, SkillType type, int slot, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.id = id;
        this.type = type;
        this.slot = slot;
        this.level = 1;
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
        if (!ModConfiguration.CLIENT.virtualLevel.get() && level >= 99)
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
        tilNextLevel = nextLevel - exp;
        Main.getInstance().skills.get(SkillType.TOTAL).addLevels(level - previousLevel);
        previousLevel = level;
    }

    private void addLevels(int i) {
        level += i;
    }

    public void addExp(int xp) {
        this.exp += xp;
        this.getTracker().setXp(xp);
        PersistentExp.saveExp();
        if (shouldLvlUp())
            levelUp();
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

    public int getLevelAtExperience(int experience) {
        int index;

        for (index = 0; index < 120; index++) {
            if (getExperienceAtLevel(index + 1) > experience)
                break;
        }

        return index;
    }

    public int getTotalExp() {
        int total = 0;

        for (SkillType skillType:SkillType.values()) {
            if (skillType != SkillType.TOTAL) {
                total += Main.getInstance().skills.get(skillType).getExp();
            }
        }

        return total;
    }

    public void setupLevel(int exp) {
        this.exp = exp;
        this.level = getLevelAtExperience(this.exp);
        this.nextLevel = getExperienceAtLevel(this.level + 1);
        this.tilNextLevel = this.nextLevel - this.exp;
        this.previousLevel = this.level;
    }

    // Getters
    public String getName() { return name; }

    public String getSymbol() { return symbol; }

    public int getId() { return id; }

    public int getLevel() { return level; }

    public int getPreviousLevel() { return previousLevel; }

    public int getExp() { return exp; }

    public int getNextLevel() { return nextLevel; }

    public int getTilNextLevel() { return tilNextLevel; }

    public int getSlot() { return slot; }

    public SkillType getType() {return type;}

    public float getInvX() { return invX; }

    public float getInvY() { return invY; }

    public XpTracker getTracker() { return tracker; }

    // Setters
    public void setLevel(int level) { this.level = level; }

    public void setPreviousLevel(int previousLevel) { this.level = previousLevel; }

    public void setInvX(float invX) { this.invX = invX; }

    public void setInvY(float invY) { this.invY = invY; }

    public void setExp(int exp) { this.exp = exp; }

    public void setNextLevel(int nextLevel) { this.nextLevel = nextLevel; }

    public void setTilNextLevel(int tilNextLevel) { this.tilNextLevel = tilNextLevel; }
}
