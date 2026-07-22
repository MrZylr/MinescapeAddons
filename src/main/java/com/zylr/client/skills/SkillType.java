package com.zylr.client.skills;

public enum SkillType {
	ATTACK,
	STRENGTH,
	DEFENCE,
	RANGED,
	PRAYER,
	MAGIC,
	RUNECRAFTING,
	CONSTRUCTION,
	HITPOINTS,
	AGILITY,
	HERBLORE,
	THIEVING,
	CRAFTING,
	FLETCHING,
	SLAYER,
	HUNTER,
	MINING,
	SMITHING,
	FISHING,
	COOKING,
	FIREMAKING,
	WOODCUTTING,
	FARMING;

	public static SkillType fromApi(com.minescape.mod.api.types.skills.SkillType apiSkill) {
		if (apiSkill == null) {
			throw new IllegalArgumentException("apiSkill cannot be null");
		}
		return SkillType.valueOf(apiSkill.name());
	}

	public com.minescape.mod.api.types.skills.SkillType toApi() {
		return com.minescape.mod.api.types.skills.SkillType.valueOf(name());
	}

	/** Returns the texture file name (without extension) for this skill's icon. */
	public String getIconName() {
		if (this == RUNECRAFTING) return "runecraft";
		return name().toLowerCase(java.util.Locale.ROOT);
	}
}

