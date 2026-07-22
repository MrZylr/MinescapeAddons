package com.zylr.client.screen.overridescreens.categories;

public enum SearchResultsScreenTitles {
	BANK_SEARCH("bank search:", true),
	ITEM_SEARCH("item search", true),
	VIEW_MOBS("view mobs", false),
	SLAYER_VANNAKA("slayer - vannaka", false),
	SLAYER_KRYSTILIA("slayer - krystilia", false),
	SLAYER_TURAEL("slayer - turael", false),
	SLAYER_MAZCHNA("slayer - mazchna", false),
	SLAYER_CHAELDAR("slayer - chaeldar", false),
	SELECT_SPELL("select spell", false),
	INFO_CLOSE_TO_RETURN("info - close to return", false),
	SELECT_ITEM("select item", true);

	private final String title;
	private final boolean focusInventory;

	SearchResultsScreenTitles(String title, boolean focusInventory) {
		this.title = title;
		this.focusInventory = focusInventory;
	}

	public String title() {
		return this.title;
	}

	public boolean focusInventory() {
		return this.focusInventory;
	}
}
