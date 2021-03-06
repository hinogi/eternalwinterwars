package com.github.scaronthesky.eternalwinterwars.model.cells;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.CellDefenseBonus;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.CellMovementMalus;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.CellSightBonus;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class Mountain extends Cell {

	public Mountain(final UUID uuid, final Unit unit, final Building building,
			final String spriteKey) {
		super(uuid, unit, building, spriteKey);
		setMovementBehaviour(new CellMovementMalus());
		setDefenseBehaviour(new CellDefenseBonus());
		setSightBehaviour(new CellSightBonus());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mountain [getUuid()=" + getUuid() + ", getUnit()=" + getUnit()
				+ ", getBuilding()=" + getBuilding() + "]";
	}

}
