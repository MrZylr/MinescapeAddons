package com.zylr.mixin;

import com.zylr.client.hud.HudManager;
import com.zylr.client.screen.overridescreens.GenericScreen;
import com.zylr.client.screen.overridescreens.GrandExchangeBuyScreen;
import com.zylr.client.screen.overridescreens.GrandExchangeBuyCompleteScreen;
import com.zylr.client.screen.overridescreens.GrandExchangeMainScreen;
import com.zylr.client.screen.overridescreens.GrandExchangePollingState;
import com.zylr.client.screen.overridescreens.GrandExchangeSellCompleteScreen;
import com.zylr.client.screen.overridescreens.GrandExchangeSellScreen;
import com.zylr.client.screen.overridescreens.SearchScreen;
import com.zylr.client.screen.overridescreens.ShopScreen;
import com.zylr.client.screen.overridescreens.SearchResultsScreen;
import com.zylr.client.screen.overridescreens.SlayerScreen;
import com.zylr.client.screen.overridescreens.HudTabRestoringScreen;
import com.zylr.client.screen.overridescreens.categories.CustomContainerScreenRegistry;
import com.zylr.client.screen.HudInventoryScreen;
import com.zylr.client.screen.overridescreens.BankScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Shadow
	public Screen screen;

	@Shadow
	public LocalPlayer player;

	@Shadow
	public Options options;

	@Shadow
	public abstract void setScreen(Screen screen);

	@Unique
	private static final int MINESCAPEADDON_GRAND_EXCHANGE_POLL_TICKS = 20;

	@Unique
	private ChestMenu minescapeaddon$pendingGrandExchangeMenu;

	@Unique
	private Component minescapeaddon$pendingGrandExchangeTitle;

	@Unique
	private int minescapeaddon$pendingGrandExchangePollTicks;

	@Unique
	private Screen minescapeaddon$pendingGrandExchangeVanillaScreen;

	@Unique
	private boolean minescapeaddon$allowNextGrandExchangeVanillaScreen;


	@Inject(method = "createTitle", at = @At("HEAD"), cancellable = true)
	private void onUpdateTitle(CallbackInfoReturnable<String> cir) {
		// Prevent the game from changing the window title
		cir.cancel();
		cir.setReturnValue("Minecraft: Minescape Addons");
	}

	@Inject(method = "handleKeybinds", at = @At("HEAD"))
	private void minescapeaddon$openHudInventory(CallbackInfo info) {
		if (this.player == null) {
			return;
		}

		if (!HudManager.getInstance().isRunOrbSprintEnabled()) {
			this.options.keySprint.setDown(false);
			this.player.setSprinting(false);
		}

		if (CustomContainerScreenRegistry.isCustomContainerScreen(this.screen)
			&& !(this.screen instanceof SearchScreen)
			&& this.options.keyInventory.consumeClick()) {
			this.setScreen(null);
			return;
		}

		if (this.screen instanceof HudInventoryScreen && this.options.keyInventory.consumeClick()) {
			this.setScreen(null);
			return;
		}

		if (this.screen != null) {
			return;
		}

		if (this.options.keyInventory.consumeClick()) {
			this.setScreen(new HudInventoryScreen());
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void minescapeaddon$pollGrandExchangeScreen(CallbackInfo info) {
		if (this.minescapeaddon$pendingGrandExchangeMenu == null || this.minescapeaddon$pendingGrandExchangeTitle == null) {
			return;
		}
		if (this.player == null || this.player.containerMenu != this.minescapeaddon$pendingGrandExchangeMenu) {
			this.minescapeaddon$clearPendingGrandExchangeScreen();
			return;
		}

		Screen grandExchangeScreen = this.minescapeaddon$grandExchangeScreen(this.minescapeaddon$pendingGrandExchangeMenu, this.minescapeaddon$pendingGrandExchangeTitle);
		if (grandExchangeScreen != null) {
			this.minescapeaddon$clearPendingGrandExchangeScreen();
			this.setScreen(grandExchangeScreen);
			return;
		}

		GrandExchangePollingState.tick();
		this.minescapeaddon$pendingGrandExchangePollTicks--;
		if (this.minescapeaddon$pendingGrandExchangePollTicks <= 0) {
			Screen vanillaScreen = this.minescapeaddon$pendingGrandExchangeVanillaScreen;
			this.minescapeaddon$clearPendingGrandExchangeScreen();
			if (vanillaScreen != null) {
				this.minescapeaddon$allowNextGrandExchangeVanillaScreen = true;
				this.setScreen(vanillaScreen);
			}
		}
	}

	@Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$replaceBankContainerScreen(Screen screen, CallbackInfo info) {
		if (this.screen instanceof HudTabRestoringScreen restoringScreen && !(screen instanceof HudTabRestoringScreen)) {
			restoringScreen.minescapeaddon$restoreHudTabForRemoval();
		}

		if (!(screen instanceof AbstractContainerScreen<?>)) {
			this.minescapeaddon$clearPendingGrandExchangeScreen();
		}

		boolean highAlchScreen = screen instanceof AbstractContainerScreen<?> containerScreen
			&& containerScreen.getMenu() instanceof ChestMenu
			&& CustomContainerScreenRegistry.shouldUseHighAlchScreen(containerScreen.getTitle().getString());

		if (!highAlchScreen && !(screen instanceof HudInventoryScreen)) {
			HudManager.getInstance().endHighAlchContainerMode();
		}

		if (CustomContainerScreenRegistry.isCustomContainerScreen(screen)) {
			return;
		}
		if (screen instanceof AnvilScreen anvilScreen && anvilScreen.getMenu() instanceof AnvilMenu anvilMenu) {
			if (CustomContainerScreenRegistry.shouldUseSearchScreen(anvilScreen.getTitle().getString())) {
				this.setScreen(new SearchScreen(anvilMenu, this.player.getInventory(), anvilScreen.getTitle()));
				info.cancel();
			}
			return;
		}
		if (!(screen instanceof AbstractContainerScreen<?> containerScreen)) {
			return;
		}
		if (!(containerScreen.getMenu() instanceof ChestMenu chestMenu)) {
			return;
		}
		if (this.player == null) {
			return;
		}

		String title = containerScreen.getTitle().getString();
		if (this.minescapeaddon$allowNextGrandExchangeVanillaScreen && CustomContainerScreenRegistry.shouldUseGrandExchangeScreen(title)) {
			this.minescapeaddon$allowNextGrandExchangeVanillaScreen = false;
			return;
		}
		if (CustomContainerScreenRegistry.isBankTitle(title)) {
			this.setScreen(new BankScreen(chestMenu, this.player.getInventory(), containerScreen.getTitle()));
			info.cancel();
			return;
		}
		if (CustomContainerScreenRegistry.shouldUseSearchResultsScreen(title)) {
			this.setScreen(new SearchResultsScreen(
				chestMenu,
				this.player.getInventory(),
				containerScreen.getTitle(),
				CustomContainerScreenRegistry.shouldFocusInventoryForSearchResultsScreen(title)
			));
			info.cancel();
			return;
		}
		if (CustomContainerScreenRegistry.shouldUseHighAlchScreen(title)) {
			HudManager.getInstance().beginHighAlchContainerMode();
			this.setScreen(new HudInventoryScreen());
			info.cancel();
			return;
		}
		if (CustomContainerScreenRegistry.shouldUseSlayerScreen(title)) {
			this.setScreen(new SlayerScreen(chestMenu, this.player.getInventory(), containerScreen.getTitle()));
			info.cancel();
			return;
		}
		if (CustomContainerScreenRegistry.shouldUseGrandExchangeScreen(title)) {
			this.minescapeaddon$scheduleGrandExchangeScreen(chestMenu, containerScreen.getTitle(), screen);
			if (this.minescapeaddon$isGrandExchangeScreen(this.screen)) {
				info.cancel();
			}
			return;
		}
		if (CustomContainerScreenRegistry.shouldUseGenericScreen(title)) {
			this.setScreen(new GenericScreen(
				chestMenu,
				this.player.getInventory(),
				containerScreen.getTitle(),
				CustomContainerScreenRegistry.shouldFocusInventoryForGenericScreen(title)
			));
			info.cancel();
			return;
		}
		if (CustomContainerScreenRegistry.shouldUseShopScreen(title)) {
			this.setScreen(new ShopScreen(
				chestMenu,
				this.player.getInventory(),
				containerScreen.getTitle(),
				CustomContainerScreenRegistry.shouldFocusInventoryForShopScreen(title)
			));
			info.cancel();
		}
	}

	@Unique
	private void minescapeaddon$scheduleGrandExchangeScreen(ChestMenu chestMenu, Component title, Screen vanillaScreen) {
		this.minescapeaddon$pendingGrandExchangeMenu = chestMenu;
		this.minescapeaddon$pendingGrandExchangeTitle = title;
		this.minescapeaddon$pendingGrandExchangePollTicks = MINESCAPEADDON_GRAND_EXCHANGE_POLL_TICKS;
		this.minescapeaddon$pendingGrandExchangeVanillaScreen = vanillaScreen;
		Screen fallbackScreen = this.minescapeaddon$isGrandExchangeScreen(this.screen) ? this.screen : null;
		int fallbackRenderTicks = fallbackScreen != null ? MINESCAPEADDON_GRAND_EXCHANGE_POLL_TICKS : 0;
		GrandExchangePollingState.setPendingMenu(chestMenu, fallbackScreen, fallbackRenderTicks);
	}

	@Unique
	private void minescapeaddon$clearPendingGrandExchangeScreen() {
		this.minescapeaddon$pendingGrandExchangeMenu = null;
		this.minescapeaddon$pendingGrandExchangeTitle = null;
		this.minescapeaddon$pendingGrandExchangePollTicks = 0;
		this.minescapeaddon$pendingGrandExchangeVanillaScreen = null;
		GrandExchangePollingState.clear();
	}

	private Screen minescapeaddon$grandExchangeScreen(ChestMenu chestMenu, Component title) {
		if (this.player == null || chestMenu.slots.size() <= 53) {
			return null;
		}
		Slot screenTypeSlot = chestMenu.slots.get(53);
		if (screenTypeSlot == null || !screenTypeSlot.hasItem()) {
			return null;
		}

		String itemPath = BuiltInRegistries.ITEM.getKey(screenTypeSlot.getItem().getItem()).getPath();
		if (!"stone_hoe".equals(itemPath)) {
			return null;
		}

		int damageValue = screenTypeSlot.getItem().getDamageValue();

		if (damageValue == 117) {
			System.out.println("Main");
			return new GrandExchangeMainScreen(chestMenu, this.player.getInventory(), title);
		}
		if (damageValue == 24) {
			System.out.println("Buy");
			return new GrandExchangeBuyScreen(chestMenu, this.player.getInventory(), title);
		}
		if (damageValue == 25) {
			System.out.println("Sell");
			return new GrandExchangeSellScreen(chestMenu, this.player.getInventory(), title);
		}
		if (damageValue == 27) {
			System.out.println("Sell Complete");
			return new GrandExchangeSellCompleteScreen(chestMenu, this.player.getInventory(), title);
		}
		if (damageValue == 26) {
			System.out.println("Buy Complete");
			return new GrandExchangeBuyCompleteScreen(chestMenu, this.player.getInventory(), title);
		}
		return null;
	}

	@Unique
	private boolean minescapeaddon$isGrandExchangeScreen(Screen screen) {
		return screen instanceof GrandExchangeMainScreen
			|| screen instanceof GrandExchangeBuyScreen
			|| screen instanceof GrandExchangeSellScreen
			|| screen instanceof GrandExchangeBuyCompleteScreen
			|| screen instanceof GrandExchangeSellCompleteScreen;
	}
}
