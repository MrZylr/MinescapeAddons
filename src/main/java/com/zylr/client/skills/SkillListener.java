package com.zylr.client.skills;

/**
 * Callback interface for reactive skill state changes.
 * All methods are called on the Minecraft client thread.
 */
public interface SkillListener {

	/**
	 * Fired when a skill's base level changes (login init or gameplay effect).
	 * @param skill     the affected skill
	 * @param oldLevel  previous base level
	 * @param newLevel  new base level
	 */
	default void onLevelChanged(SkillType skill, int oldLevel, int newLevel) {}

	/**
	 * Fired when a skill's experience changes.
	 * @param skill     the affected skill
	 * @param oldExp    previous total experience
	 * @param newExp    new total experience
	 * @param gained    amount gained in this update (0 on login restore)
	 */
	default void onExperienceChanged(SkillType skill, double oldExp, double newExp, double gained) {}

	/**
	 * Fired when an active modifier (boost / drain) changes.
	 * @param skill       the affected skill
	 * @param oldModifier previous modifier value
	 * @param newModifier new modifier value
	 */
	default void onModifierChanged(SkillType skill, int oldModifier, int newModifier) {}

	/**
	 * Fired once after a bulk login packet restores all skills.
	 * Snapshot contains final state for every skill.
	 */
	default void onLoginRestored(java.util.Map<SkillType, Skills.SkillSnapshot> snapshots) {}
}

