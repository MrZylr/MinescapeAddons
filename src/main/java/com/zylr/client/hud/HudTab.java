package com.zylr.client.hud;

import com.zylr.MinescapeAddon;
import net.minecraft.resources.Identifier;

public enum HudTab {
	COMBAT("Combat", "tab/combat.png"),
	SKILLS("Skills", "tab/stats.png"),
	INVENTORY("Inventory", "tab/inventory.png"),
	EQUIPMENT("Equipment", "tab/equipment.png");

	private final String label;
	private final Identifier icon;

	HudTab(String label, String iconPath) {
		this.label = label;
		this.icon = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/resource-packs-sample-vanilla/" + iconPath);
	}

	public String label() {
		return this.label;
	}

	public Identifier icon() {
		return this.icon;
	}
}



