package com.zylr.client.hud;

import net.minecraft.resources.Identifier;

final class TabSlot {
	final Identifier icon;
	final HudTab linkedTab;

	TabSlot(Identifier icon, HudTab linkedTab) {
		this.icon = icon;
		this.linkedTab = linkedTab;
	}
}

