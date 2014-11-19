package com.github.scaronthesky.eternalwinterwars.controller.util;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;
import com.github.scaronthesky.eternalwinterwars.model.cells.Forest;
import com.github.scaronthesky.eternalwinterwars.model.cells.Mountain;
import com.github.scaronthesky.eternalwinterwars.model.cells.Plain;
import com.github.scaronthesky.eternalwinterwars.model.cells.River;

public abstract class CellBuilder {
	public static Cell buildCell(String pSpriteKey) {
		if (pSpriteKey.startsWith("tree")) {
			return buildDefaultForest(pSpriteKey);
		} else if (pSpriteKey.startsWith("mountain")) {
			return buildDefaultMountain(pSpriteKey);
		} else if (pSpriteKey.startsWith("river")) {
			return buildDefaultRiver(pSpriteKey);
		} else {
			return buildDefaultPlain();
		}
	}

	/**
	 * XXX For MapBuilderExt - Generation
	 */
	public static Plain buildDefaultPlain(String pSpriteKey) {
		return new Plain(UUID.randomUUID(), null, null);
	}

	public static Plain buildDefaultPlain() {
		return new Plain(UUID.randomUUID(), null, null);
	}

	public static Forest buildDefaultForest(String pSpriteKey) {
		return new Forest(UUID.randomUUID(), null, null, pSpriteKey);
	}

	public static Mountain buildDefaultMountain(String pSpriteKey) {
		return new Mountain(UUID.randomUUID(), null, null, pSpriteKey);
	}

	public static River buildDefaultRiver(String pSpriteKey) {
		return new River(UUID.randomUUID(), null, null, pSpriteKey);
	}
}
