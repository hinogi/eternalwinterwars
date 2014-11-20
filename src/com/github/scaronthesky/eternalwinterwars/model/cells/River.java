package com.github.scaronthesky.eternalwinterwars.model.cells;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.CellDefenseMalus;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.CellMovementMalus;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.CellSightNoModifier;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class River extends Cell {

	public River(final UUID uuid, final Unit unit, final Building building,
			final String spriteKey) {
		super(uuid, unit, building, spriteKey);
		setMovementBehaviour(new CellMovementMalus());
		setDefenseBehaviour(new CellDefenseMalus());
		setSightBehaviour(new CellSightNoModifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "River [getUuid()=" + getUuid() + ", getUnit()=" + getUnit()
				+ ", getBuilding()=" + getBuilding() + "]";
	}

}
