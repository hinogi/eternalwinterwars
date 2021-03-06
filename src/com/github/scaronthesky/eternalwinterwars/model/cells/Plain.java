package com.github.scaronthesky.eternalwinterwars.model.cells;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.CellDefenseNoModifier;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.CellMovementNoModifier;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.CellSightNoModifier;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class Plain extends Cell {

	public Plain(final UUID uuid, final Unit unit, final Building building) {
		super(uuid, unit, building, null);
		setMovementBehaviour(new CellMovementNoModifier());
		setDefenseBehaviour(new CellDefenseNoModifier());
		setSightBehaviour(new CellSightNoModifier());
	}

	@Override
	public String toString() {
		return "Plain [getUuid()=" + getUuid() + ", getUnit()=" + getUnit()
				+ ", getBuilding()=" + getBuilding() + "]";
	}

}
