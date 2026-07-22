package com.zylr.client.screen.overridescreens;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;

public final class GrandExchangeBuyCompleteScreen extends AbstractGrandExchangeOfferCompleteScreen {
	public GrandExchangeBuyCompleteScreen(ChestMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	protected String getOfferHeaderLabel() {
		return "Buy offer";
	}
}
