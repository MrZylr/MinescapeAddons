package com.zylr.client.screen.overridescreens.categories;

public enum ShopScreenTitles {
	LOWES_ARCHERY_EMPORIUM("lowe's archery emporium", true),
	AUBURYS_RUNE_SHOP("aubury's rune shop", true),
	FANCY_CLOTHES_STORE("fancy clothes store", true),
	ZAFFS_SUPERIOR_STAFFS("zaff's superior staffs", true),
	THESSALIAS_FINE_CLOTHES("thessalia's fine clothes", true),
	VARROCK_SWORDSHOP("varrock swordshop", true),
	CANIFIS_GENERAL_STORE("canifis general store", true),
	BARKERS_HABERDASHERY("barkers' haberdashery", true),
	RUFUS_MEAT_EMPORIUM("rufus' meat emporium", true),
	ALICES_FARMING_SHOP("alice's farming shop", true),
	ZEKES_SUPERIOR_SCIMITARS("zeke's superior scimitars", true),
	RANAELS_SUPER_SKIRT_STORE("ranael's super skirt store", true),
	LOUIES_ARMOURED_LEGS_BAZAAR("louie's armoured legs bazaar", true),
	AL_KHARID_GENERAL_STORE("al-kharid general store", true),
	DOMMIKS_CRAFTING_STORE("dommik's crafting store", true),
	GEM_TRADER("gem trader", true),
	BOBS_BRILLIANT_AXES("bob's brilliant axes", true),
	LUMBRIDGE_GENERAL_STORE("lumbridge general store", true),
	DIANGOS_TOY_STORE("diango's toy store.", true),
	DRAYNOR_SEED_MARKET("draynor seed market", true),
	BRIANS_BATTLEAXE_BAZAAR("brian's battleaxe bazaar", true),
	GRUMS_GOLD_EXCHANGE("grum's gold exchange", true),
	BETTYS_MAGIC_EMPORIUM("betty's magic emporium", true),
	GERRANTS_FISHY_BUSINESS("gerrant's fishy business", true),
	FOOD_STORE("food store", true),
	BRIANS_ARCHERY_SUPPLIES("brian's archery supplies", true),
	ROMMIKS_CRAFTY_SUPPLIES("rommik's crafty supplies", true),
	RIMMINGTON_GENERAL_STORE("rimmington general store", true),
	TOOL_STORE("tool store", true),
	KARAMJA_WINES_SPIRITS_AND_BEERS("karamja wines spirits and beers", true),
	KARAMJA_GENERAL_STORE("karamja general store", true),
	DAVONS_AMULET_STORE("davon's amulet store.", true),
	THE_SHRIMP_AND_PARROT("the shrimp and parrot.", true),
	TRADER_CREWMEMBER_SHOP("trader crewmember shop", true),
	SARAHS_FARMING_SHOP("sarah's farming shop", true),
	GARDEN_CENTRE("garden centre", true),
	CASSIES_SHIELD_SHOP("cassie's shield shop", true),
	FALADOR_GENERAL_STORE("falador general store", true),
	FLYNNS_MACE_MARKET("flynn's mace market", true),
	HERQUINS_GEMS("herquin's gems", true),
	WAYNES_CHAINS("wayne's chains - chainmail specialist", true),
	DUSURIS_STAR_SHOP("dusuri's star shop", true),
	MINING_GUILD_MINERAL_EXCHANGE("mining guild mineral exchange", true),
	DROGOS_MINING_EMPORIUM("drogo's mining emporium", true),
	CROSSBOW_SHOP("crossbow shop", true),
	NURMOFS_PICKAXE_SHOP("nurmof's pickaxe shop", true),
	HELMET_SHOP("helmet shop", true),
	SLAYER_EQUIPMENT_SHOP("slayer equipment shop", true),
	EDGEVILLE_GENERAL_STORE("edgeville general store", true),
	OZIACHS_ARMOUR("oziach's armour", true),
	JATIXS_HERBLORE_SHOP("jatix's herblore shop", true),
	GAIUS_TWO_HANDED_SHOP("gaius' two handed shop", true),
	BURTHOPE_SUPPLIES("burthope supplies", true),
	MARTIN_THWAITS_LOST_AND_FOUND("martin thwait's lost and found", true),
	WARRIORS_GUILD_POTIONS("warriors guild potions", true),
	WARRIORS_GUILD_FOOD("warriors guild food", true),
	WARRIORS_GUILD_ARMOURY("warriors guild armoury", true),
	PIE_SHOP("pie shop", true),
	MAKE_PLANKS("make planks", true),
	CONSTRUCTION_SUPPLIES("construction supplies", true),
	HARRYS_FISHING_SHOP("harry's fishing shop", true),
	HICKTONS_ARCHERY_EMPORIUM("hickton's archery emporium", true),
	ARHEINS_STORE("arhein's store", true),
	CANDLE_SHOP("candle shop", true),
	VANESSAS_FARMING_SHOP("vanessa's farming shop", true),
	BANDIT_DUTY_FREE("bandit duty free", true),
	AARONS_ARCHERY_APPENDAGES("aaron's archery appendages.", true),
	SCAVVOS_RUNE_STORE("scavvo's rune store", true),
	ARDOUGNE_SILVER_STALL("ardougne silver stall.", true),
	ARDOUGNE_BAKERS_STALL("ardougne baker's stall.", true),
	ARDOUGNE_GEM_STALL("ardougne gem stall.", true),
	ARDOUGNE_SPICE_STALL("ardougne spice stall.", true),
	ARDOUGNE_FUR_STALL("ardougne fur stall.", true),
	ZENESHAS_PLATE_MAIL_BODY_SHOP("zenesha's plate mail body shop.", true),
	AEMADS_ADVENTURING_SUPPLIES("aemad's adventuring supplies.", true),
	LOVECRAFTS_TACKLE("lovecraft's tackle", true),
	WEST_ARDOUGNE_GENERAL_STORE("west ardougne general store", true),
	BOLKOYS_VILLAGE_SHOP("bolkoy's village shop", true),
	BALKOYS_VILLAGE_SHOP("balkoy's village shop", true),
	FRENITAS_COOKERY_SHOP("frenita's cookery shop.", true),
	ALECKS_HUNTER_EMPORIUM("aleck's hunter emporium.", true),
	MAGIC_GUILD_STORE("magic guild store", true),
	KHAZARD_GENERAL_STORE("khazard general store", true),
	RASOLO_THE_WANDERING_MERCHANT("rasolo the wandering merchant", true),
	HORVIKS_ARMOUR_SHOP("horvik's armour shop", true),
	VARROCK_GENERAL_STORE("varrock general store", true),
	YE_OLDE_TEA_SHOPPE("ye olde tea shoppe", true),
	TONYS_PIZZA_BASES("tony's pizza bases", true);

	private final String title;
	private final boolean focusInventory;

	ShopScreenTitles(String title, boolean focusInventory) {
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
