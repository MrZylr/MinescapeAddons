package com.zylr.client.screen.overridescreens;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.inventory.ContainerInput;

final class MenuInteractionGate {
	private static final GateProfile MENU_PROFILE = new GateProfile(20.0D, 3.0D, 1.0D, 50L);
	private static final GateProfile SCROLL_PROFILE = new GateProfile(20.0D, 3.0D, 1.0D, 50L);

	private static final Map<String, GateState> GATE_STATES = new HashMap<>();

	private MenuInteractionGate() {
	}

	static synchronized boolean allowMenuClick(int containerId, int slotId, int button, ContainerInput clickType) {
		return allow(containerId, "menu", slotId + ":" + button + ':' + clickType, MENU_PROFILE);
	}

	static synchronized boolean allowScrollAction(int containerId, String actionName) {
		return allow(containerId, "scroll", actionName, SCROLL_PROFILE);
	}

	private static boolean allow(int containerId, String gateType, String actionKey, GateProfile profile) {
		long now = System.currentTimeMillis();
		String stateKey = containerId + ":" + gateType;
		GateState state = GATE_STATES.computeIfAbsent(stateKey, key -> new GateState(profile.burstCapacity, now));
		long elapsedMs = Math.max(0L, now - state.lastUpdatedAtMs);
		if (elapsedMs > 0L) {
			double replenishedTokens = elapsedMs / 1000.0D * profile.refillPerSecond;
			state.tokens = Math.min(profile.burstCapacity, state.tokens + replenishedTokens);
			state.lastUpdatedAtMs = now;
		}

		if (actionKey.equals(state.lastActionKey) && now - state.lastActionAtMs < profile.sameActionMinGapMs) {
			return false;
		}

		if (state.tokens < profile.costPerAction) {
			return false;
		}

		state.tokens -= profile.costPerAction;
		state.lastActionKey = actionKey;
		state.lastActionAtMs = now;
		return true;
	}

	private record GateProfile(double burstCapacity, double refillPerSecond, double costPerAction, long sameActionMinGapMs) {
	}

	private static final class GateState {
		private double tokens;
		private long lastUpdatedAtMs;
		private String lastActionKey = "";
		private long lastActionAtMs;

		private GateState(double tokens, long lastUpdatedAtMs) {
			this.tokens = tokens;
			this.lastUpdatedAtMs = lastUpdatedAtMs;
		}
	}
}
