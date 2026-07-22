package com.zylr.mixin;

import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(PackRepository.class)
public class PackRepositoryMixin {
	@Inject(method = "rebuildSelected", at = @At("RETURN"), cancellable = true)
	private void minescapeaddon$placeServerPacksAboveVanilla(
		java.util.Collection<String> selectedIds,
		CallbackInfoReturnable<List<Pack>> cir
	) {
		List<Pack> selected = cir.getReturnValue();
		int vanillaIndex = -1;
		List<Pack> serverPacks = new ArrayList<>();

		for (int i = 0; i < selected.size(); i++) {
			Pack pack = selected.get(i);
			String id = pack.getId();
			if ("vanilla".equals(id)) {
				vanillaIndex = i;
			} else if (id.startsWith("server/")) {
				serverPacks.add(pack);
			}
		}

		if (vanillaIndex < 0 || serverPacks.isEmpty()) {
			return;
		}

		List<Pack> reordered = new ArrayList<>(selected);
		reordered.removeIf(pack -> pack.getId().startsWith("server/"));

		vanillaIndex = -1;
		for (int i = 0; i < reordered.size(); i++) {
			if ("vanilla".equals(reordered.get(i).getId())) {
				vanillaIndex = i;
				break;
			}
		}

		if (vanillaIndex < 0) {
			return;
		}

		reordered.addAll(vanillaIndex + 1, serverPacks);
		cir.setReturnValue(List.copyOf(reordered));
	}
}
