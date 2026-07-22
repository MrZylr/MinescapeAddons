package com.zylr.mixin;

import com.zylr.MinescapeAddon;
import com.zylr.client.PerfDebug;
import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.StackSizeOverlay;
import com.zylr.client.screen.overridescreens.categories.CustomContainerScreenRegistry;
import com.zylr.client.screen.overridescreens.GrandExchangePollingState;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.HashedStack;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {
	@Shadow
	protected AbstractContainerMenu menu;

	@Shadow
	protected abstract void slotClicked(Slot slot, int slotId, int button, ContainerInput clickType);

	@Inject(method = "init", at = @At("TAIL"))
	private void minescapeaddon$resetBarrowsTrackerOnLootOpen(CallbackInfo info) {
		if (!((Object) this instanceof AbstractContainerScreen<?> screen)) {
			return;
		}

		String title = screen.getTitle().getString();
		if (title != null && title.toLowerCase(java.util.Locale.ROOT).contains("barrows loot")) {
			HudManager.getInstance().markBarrowsLootContainerOpened();
		}
	}

	@Inject(method = "slotClicked", at = @At("HEAD"))
	private void minescapeaddon$logVanillaInventorySlotClick(Slot slot, int slotId, int button, ContainerInput clickType, CallbackInfo info) {
		if (!((Object) this instanceof InventoryScreen) || slot == null) {
			return;
		}

		int inventorySlot = minescapeaddon$inventorySlot(slotId);
		int equipmentSlot = minescapeaddon$equipmentSlot(slotId);
		int craftingSlot = minescapeaddon$craftingSlot(slotId);

		MinescapeAddon.LOGGER.info(
			"Vanilla inventory slot click: inventorySlot={}, equipmentSlot={}, craftingSlot={}, button={}, clickType={}",
			inventorySlot,
			equipmentSlot,
			craftingSlot,
			button,
			clickType
		);
	}

	@Inject(method = "slotClicked", at = @At("HEAD"))
	private void minescapeaddon$logGrandExchangeDamageValue(Slot slot, int slotId, int button, ContainerInput clickType, CallbackInfo info) {
		if (slot == null || !slot.hasItem()) {
			return;
		}
		if (!((Object) this instanceof AbstractContainerScreen<?> screen)) {
			return;
		}
		if (!screen.getTitle().getString().contains("Grand Exchange")) {
			return;
		}

		//System.out.println("Grand Exchange container click damage=" + slot.getItem().getDamageValue());
	}

	@Inject(method = "mouseScrolled", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$clickScrollItem(double mouseX, double mouseY, double horizontalAmount, double verticalAmount, CallbackInfoReturnable<Boolean> info) {
		if (verticalAmount == 0.0) {
			return;
		}

		if (minescapeaddon$clickNamedItem(verticalAmount > 0.0 ? "scroll up" : "scroll down")) {
			info.setReturnValue(true);
		}
	}

	@Inject(method = "extractSlot", at = @At("TAIL"))
	private void minescapeaddon$extractLoreStackSize(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY, CallbackInfo info) {
		if (slot == null || !slot.hasItem()) {
			return;
		}

		ItemStack stack = slot.getItem();
		if (stack.isEmpty()) {
			return;
		}

		long start = PerfDebug.start();
		StackSizeOverlay.renderLightweight(graphics, Minecraft.getInstance().font, stack, slot.x, slot.y, 16);
		PerfDebug.record("overlay.containerStackSize", start);
	}

	@Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$renderPreviousScreenWhileGrandExchangePolling(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo info) {
		if (!(this.menu instanceof net.minecraft.world.inventory.ChestMenu chestMenu)) {
			return;
		}
		if (!this.minescapeaddon$shouldHideGrandExchangeContainer()) {
			return;
		}
		Screen fallbackScreen = GrandExchangePollingState.fallbackScreen(chestMenu);
		if (fallbackScreen != null) {
			fallbackScreen.extractRenderState(graphics, mouseX, mouseY, delta);
		}
		info.cancel();
	}

	@Inject(method = "extractSlot", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideGrandExchangeContainerWhilePolling(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY, CallbackInfo info) {
		if (!this.minescapeaddon$shouldHideGrandExchangeContainer()) {
			return;
		}
		info.cancel();
	}

	@Inject(method = "extractContents", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideGrandExchangeContainerContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo info) {
		if (!this.minescapeaddon$shouldHideGrandExchangeContainer()) {
			return;
		}
		info.cancel();
	}

	@Inject(method = "extractCarriedItem", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideGrandExchangeCarriedItem(GuiGraphicsExtractor graphics, int mouseX, int mouseY, CallbackInfo info) {
		if (!this.minescapeaddon$shouldHideGrandExchangeContainer()) {
			return;
		}
		info.cancel();
	}

	@Inject(method = "extractLabels", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideGrandExchangeContainerLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY, CallbackInfo info) {
		if (!this.minescapeaddon$shouldHideGrandExchangeContainer()) {
			return;
		}
		info.cancel();
	}

	@Inject(method = "extractTooltip", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideGrandExchangeContainerTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY, CallbackInfo info) {
		if (!this.minescapeaddon$shouldHideGrandExchangeContainer()) {
			return;
		}
		info.cancel();
	}

	private boolean minescapeaddon$shouldHideGrandExchangeContainer() {
		if (!(this.menu instanceof net.minecraft.world.inventory.ChestMenu chestMenu)) {
			return false;
		}
		if (!GrandExchangePollingState.isPending(chestMenu)) {
			return false;
		}
		if (!((Object) this instanceof AbstractContainerScreen<?> screen)) {
			return false;
		}
		if (!CustomContainerScreenRegistry.shouldUseGrandExchangeScreen(screen.getTitle().getString())) {
			return false;
		}
		return true;
	}

	private boolean minescapeaddon$clickNamedItem(String itemName) {
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slot == null || !slot.hasItem()) {
				continue;
			}

			ItemStack stack = slot.getItem();
			if (stack.isEmpty()) {
				continue;
			}

			if (itemName.equalsIgnoreCase(stack.getHoverName().getString())) {
				this.minescapeaddon$sendContainerClickWithoutPrediction(slotId, 0, ContainerInput.PICKUP);
				return true;
			}
		}

		return false;
	}

	private void minescapeaddon$sendContainerClickWithoutPrediction(int slotId, int button, ContainerInput clickType) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.player == null) {
			return;
		}

		ClientPacketListener connection = minecraft.getConnection();
		if (connection == null) {
			return;
		}

		HashedStack carriedItem = HashedStack.create(this.menu.getCarried(), connection.decoratedHashOpsGenenerator());
		connection.send(new ServerboundContainerClickPacket(
			this.menu.containerId,
			this.menu.getStateId(),
			(short) slotId,
			(byte) button,
			clickType,
			Int2ObjectMaps.emptyMap(),
			carriedItem
		));
	}

	private static int minescapeaddon$inventorySlot(int slotId) {
		if (slotId >= 9 && slotId <= 35) {
			return slotId;
		}
		if (slotId >= 36 && slotId <= 44) {
			return slotId - 36;
		}
		return -1;
	}

	private static int minescapeaddon$equipmentSlot(int slotId) {
		if (slotId >= 5 && slotId <= 8) {
			return slotId - 5;
		}
		if (slotId == 45) {
			return 4;
		}
		return -1;
	}

	private static int minescapeaddon$craftingSlot(int slotId) {
		if (slotId >= 0 && slotId <= 4) {
			return slotId;
		}
		return -1;
	}
}
