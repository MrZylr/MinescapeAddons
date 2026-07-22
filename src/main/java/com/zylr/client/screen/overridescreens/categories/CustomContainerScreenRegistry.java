package com.zylr.client.screen.overridescreens.categories;

import com.zylr.client.screen.overridescreens.*;
import com.zylr.utils.PrivateUseAsciiDecoder;
import net.minecraft.client.gui.screens.Screen;

import java.util.Locale;

public final class CustomContainerScreenRegistry {
	private CustomContainerScreenRegistry() {
	}

	public static boolean isBankTitle(String title) {
		return "bank".equals(normalize(title));
	}

	public static boolean shouldUseSearchResultsScreen(String title) {
		return searchResultsScreenTitle(title) != null;
	}

	public static boolean shouldFocusInventoryForSearchResultsScreen(String title) {
		SearchResultsScreenTitles searchResultsScreenTitle = searchResultsScreenTitle(title);
		return searchResultsScreenTitle != null && searchResultsScreenTitle.focusInventory();
	}

	private static SearchResultsScreenTitles searchResultsScreenTitle(String title) {
		String normalized = normalize(title);
		for (SearchResultsScreenTitles searchResultsScreenTitle : SearchResultsScreenTitles.values()) {
			if (normalized.contains(searchResultsScreenTitle.title())) {
				return searchResultsScreenTitle;
			}
		}
		return null;
	}

	public static boolean shouldUseSearchScreen(String title) {
		String normalized = normalize(title);
		return normalized.contains("search")
				|| normalized.contains("enter")
				|| normalized.contains("deposit");
	}

	public static boolean shouldUseGenericScreen(String title) {
		return genericScreenTitle(title) != null;
	}

	public static boolean shouldFocusInventoryForGenericScreen(String title) {
		GenericScreenTitle genericScreenTitle = genericScreenTitle(title);
		return genericScreenTitle != null && genericScreenTitle.focusInventory();
	}

	private static GenericScreenTitle genericScreenTitle(String title) {
		String normalized = normalize(title);
		if (normalized.isEmpty()) return GenericScreenTitle.BLANK;
		for (GenericScreenTitle genericScreenTitle : GenericScreenTitle.values()) {
			if (genericScreenTitle == GenericScreenTitle.BLANK) continue;
			if (normalized.contains(genericScreenTitle.title())) {
				return genericScreenTitle;
			}
		}
		return null;
	}

	public static boolean shouldUseGrandExchangeScreen(String title) {
		/*System.out.println(PrivateUseAsciiDecoder.containsEncodedAscii(title));
		if (PrivateUseAsciiDecoder.containsEncodedAscii(normalize(title))) {
			System.out.println(PrivateUseAsciiDecoder.decode(title));
			for (String decodeSegment : PrivateUseAsciiDecoder.decodeSegments(normalize(title))) {
				System.out.println(decodeSegment);
			}
		} else {
			System.out.println(title);
		}*/
		return normalize(title).contains("grand exchange");
	}

	public static boolean shouldUseHighAlchScreen(String title) {
		String normalized = normalize(title);
		return normalized.contains("select slot to alch");
	}

	public static boolean shouldUseSlayerScreen(String title) {
		String normalized = normalize(title);
		if (normalized.contains("slayer - tasks") ||
				normalized.contains("slayer - unlock") ||
				normalized.contains("slayer - extend") ||
				normalized.contains("slayer - buy")) {
			return true;
		}

		String decoded = normalize(PrivateUseAsciiDecoder.decode(title));
		return decoded.contains("slayer - tasks") ||
				decoded.contains("slayer - unlock") ||
				decoded.contains("slayer - extend") ||
				decoded.contains("slayer - buy");
	}

	public static boolean shouldUseShopScreen(String title) {
		return shopScreenTitle(title) != null;
	}

	public static boolean shouldFocusInventoryForShopScreen(String title) {
		ShopScreenTitles shopScreenTitle = shopScreenTitle(title);
		return shopScreenTitle != null && shopScreenTitle.focusInventory();
	}

	private static ShopScreenTitles shopScreenTitle(String title) {
		String normalized = normalize(title);
		for (ShopScreenTitles shopScreenTitle : ShopScreenTitles.values()) {
			if (normalized.contains(shopScreenTitle.title())) {
				return shopScreenTitle;
			}
		}
		return null;
	}

	public static boolean isCustomContainerScreen(Screen screen) {
		return screen instanceof BankScreen
			|| screen instanceof ShopScreen
			|| screen instanceof SearchResultsScreen
			|| screen instanceof SearchScreen
			|| screen instanceof GenericScreen
			|| screen instanceof GrandExchangeMainScreen
			|| screen instanceof GrandExchangeBuyScreen
			|| screen instanceof GrandExchangeSellScreen
			|| screen instanceof GrandExchangeBuyCompleteScreen
			|| screen instanceof GrandExchangeSellCompleteScreen
			|| screen instanceof SlayerScreen;
	}

	private static String normalize(String title) {
		return title == null ? "" : title.trim().toLowerCase(Locale.ROOT);
	}
}
