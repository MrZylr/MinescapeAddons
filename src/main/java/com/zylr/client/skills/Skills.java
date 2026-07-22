package com.zylr.client.skills;

import com.minescape.mod.api.channel.general.skills.GameplaySkillEffectData;
import com.minescape.mod.api.channel.general.skills.GameplaySkillsExperienceData;
import com.minescape.mod.api.channel.general.skills.LoginSkillEffectData;
import com.minescape.mod.api.channel.general.skills.LoginSkillsData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Skills {
	private static final Skills INSTANCE = new Skills();
	public static final int MAX_REAL_LEVEL = 99;
	public static final int MAX_VIRTUAL_LEVEL = 126;
	private static final int[] EXPERIENCE_AT_LEVEL = buildExperienceTable();

	private final EnumMap<SkillType, Integer> levels = new EnumMap<>(SkillType.class);
	private final EnumMap<SkillType, Double> experiences = new EnumMap<>(SkillType.class);
	private final EnumMap<SkillType, Integer> modifiers = new EnumMap<>(SkillType.class);
	private final EnumMap<SkillType, Double> lastExperienceGained = new EnumMap<>(SkillType.class);
	private final EnumMap<SkillType, Integer> gainedXp = new EnumMap<>(SkillType.class);

	private final List<SkillListener> listeners = new ArrayList<>();

	private Skills() {
		resetAll();
	}

	public static Skills getInstance() {
		return INSTANCE;
	}

	// ----- Listener registration -----

	public void addListener(SkillListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeListener(SkillListener listener) {
		listeners.remove(listener);
	}

	// ----- Packet handlers -----

	public void applyLoginSkills(LoginSkillsData packet) {
		for (SkillType skill : SkillType.values()) {
			Integer level = packet.getLevel(skill.toApi());
			Double experience = packet.getExperience(skill.toApi());
			levels.put(skill, level != null ? level : 1);
			experiences.put(skill, experience != null ? experience : 0.0D);
			lastExperienceGained.put(skill, 0.0D);
		}
		Map<SkillType, SkillSnapshot> snapshots = getAllSnapshots();
		for (SkillListener l : listeners) {
			l.onLoginRestored(snapshots);
		}
	}

	public void applyLoginSkillEffects(LoginSkillEffectData packet) {
		for (SkillType skill : SkillType.values()) {
			int oldModifier = getModifier(skill);
			Integer modifier = packet.getModifier(skill.toApi());
			int newModifier = modifier != null ? modifier : 0;
			modifiers.put(skill, newModifier);
			if (oldModifier != newModifier) {
				for (SkillListener l : listeners) {
					l.onModifierChanged(skill, oldModifier, newModifier);
				}
			}
		}
	}

	public void applyGameplayExperience(GameplaySkillsExperienceData packet) {
		SkillType skill = SkillType.fromApi(packet.skillType());
		int oldLevel = getLevel(skill);
		double oldExp = getExperience(skill);
		double newExp = packet.totalExperience();
		double gained = packet.experienceGained();
		gainedXp.put(skill, (int)newExp - (int)oldExp);
		int newLevel = getLevelForExperience(newExp);
		levels.put(skill, newLevel);
		experiences.put(skill, newExp);
		lastExperienceGained.put(skill, gained);
		for (SkillListener l : listeners) {
			l.onExperienceChanged(skill, oldExp, newExp, gained);
		}
		if (oldLevel != newLevel) {
			for (SkillListener l : listeners) {
				l.onLevelChanged(skill, oldLevel, newLevel);
			}
		}
	}

	public void applyGameplaySkillEffect(GameplaySkillEffectData packet) {
		SkillType skill = SkillType.fromApi(packet.skillType());
		int oldLevel = getLevel(skill);
		int oldModifier = getModifier(skill);
		int newLevel = packet.skillLevel();
		int newModifier = packet.newModifier();
		levels.put(skill, newLevel);
		modifiers.put(skill, newModifier);
		if (oldLevel != newLevel) {
			for (SkillListener l : listeners) {
				l.onLevelChanged(skill, oldLevel, newLevel);
			}
		}
		if (oldModifier != newModifier) {
			for (SkillListener l : listeners) {
				l.onModifierChanged(skill, oldModifier, newModifier);
			}
		}
	}

	public int getLevel(SkillType skill) {
		return levels.getOrDefault(skill, 1);
	}

	public double getExperience(SkillType skill) { return experiences.getOrDefault(skill, 0.0D); }

	public int getModifier(SkillType skill) {
		return modifiers.getOrDefault(skill, 0);
	}

	public int getEffectiveLevel(SkillType skill) {
		return getLevel(skill) + getModifier(skill);
	}

	public int getGainedXp(SkillType skill) { return gainedXp.get(skill); }

	public boolean hasEffect(SkillType skill) {
		return getModifier(skill) != 0;
	}

	public int getExperienceAtLevel(int level) {
		if (level <= 1) return 0;
		if (level >= EXPERIENCE_AT_LEVEL.length) return EXPERIENCE_AT_LEVEL[EXPERIENCE_AT_LEVEL.length - 1];
		return EXPERIENCE_AT_LEVEL[level];
	}

	public int getLevelForExperience(double experience) {
		return getLevelForExperience(experience, MAX_REAL_LEVEL);
	}

	public int getVirtualLevel(SkillType skill) {
		return getLevelForExperience(getExperience(skill), MAX_VIRTUAL_LEVEL);
	}

	public int getLevelForExperience(double experience, int maxLevel) {
		int highestLevel = Math.min(maxLevel, MAX_VIRTUAL_LEVEL);
		int low = 1;
		int high = highestLevel;
		while (low < high) {
			int mid = (low + high + 1) >>> 1;
			if (experience >= EXPERIENCE_AT_LEVEL[mid]) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	public double getLastExperienceGained(SkillType skill) {
		return lastExperienceGained.getOrDefault(skill, 0.0D);
	}

	public SkillSnapshot getSnapshot(SkillType skill) {
		return new SkillSnapshot(
				skill,
				getLevel(skill),
				getEffectiveLevel(skill),
				getExperience(skill),
				getModifier(skill),
				getLastExperienceGained(skill)
		);
	}

	public Map<SkillType, SkillSnapshot> getAllSnapshots() {
		EnumMap<SkillType, SkillSnapshot> snapshots = new EnumMap<>(SkillType.class);
		for (SkillType skill : SkillType.values()) {
			snapshots.put(skill, getSnapshot(skill));
		}
		return Collections.unmodifiableMap(snapshots);
	}

	public void resetAll() {
		for (SkillType skill : SkillType.values()) {
			levels.put(skill, 1);
			experiences.put(skill, 0.0D);
			modifiers.put(skill, 0);
			lastExperienceGained.put(skill, 0.0D);
			gainedXp.put(skill, 0);
		}
	}

	private static int[] buildExperienceTable() {
		int[] table = new int[MAX_VIRTUAL_LEVEL + 1];
		double total = 0.0D;
		table[1] = 0;
		for (int level = 2; level <= MAX_VIRTUAL_LEVEL; level++) {
			int previousLevel = level - 1;
			total += Math.floor(previousLevel + 300 * Math.pow(2, previousLevel / 7.0D));
			table[level] = (int) Math.floor(total / 4.0D);
		}
		return table;
	}

	public record SkillSnapshot(
			SkillType skill,
			int level,
			int effectiveLevel,
			double experience,
			int modifier,
			double lastExperienceGained
	) {
	}
}


