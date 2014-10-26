package com.github.scaronthesky.eternalwinterwars.model.cells;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.CellDefenseBonus;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.CellMovementMalus;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.CellSightMalus;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class Forest extends Cell {

	public Forest(final UUID uuid, final Unit unit, final Building building,
			final Sprite sprite) {
		super(uuid, unit, building, sprite);
		setMovementBehaviour(new CellMovementMalus());
		setDefenseBehaviour(new CellDefenseBonus());
		setSightBehaviour(new CellSightMalus());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Forest [getUuid()=" + getUuid() + ", getUnit()=" + getUnit()
				+ ", getBuilding()=" + getBuilding() + "]";
	}

}
