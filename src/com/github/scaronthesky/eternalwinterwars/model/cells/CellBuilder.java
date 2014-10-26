package com.github.scaronthesky.eternalwinterwars.model.cells;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.CellDefenseBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.CellMovementBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.CellSightBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class CellBuilder {

	private Cell cell;

	public CellBuilder() {
		this.cell = new Cell();
	}

	public CellBuilder withUUID(UUID uuid) {
		this.cell.setUuid(uuid);
		return this;
	}

	public CellBuilder withMovemenentBehaviour(
			CellMovementBehaviour movementBehaviour) {
		this.cell.setMovementBehaviour(movementBehaviour);
		return this;
	}

	public CellBuilder withSightBehaviour(CellSightBehaviour sightBehaviour) {
		this.cell.setSightBehaviour(sightBehaviour);
		return this;
	}

	public CellBuilder withDefenseBehaviour(CellDefenseBehaviour defenseBehaviour) {
		this.cell.setDefenseBehaviour(defenseBehaviour);
		return this;
	}

	public CellBuilder withUnit(Unit unit) {
		this.cell.setUnit(unit);
		return this;
	}

	public CellBuilder withBuilding(Building building) {
		this.cell.setBuilding(building);
		return this;
	}

	public Cell build() {
		return this.cell;
	}
}
