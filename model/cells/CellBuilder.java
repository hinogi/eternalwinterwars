package com.github.scaronthesky.eternalwinterwars.model.cells;

import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.DefenseBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.MovementBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.SightBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class CellBuilder {

	private Cell cell;

	public CellBuilder() {
		this.cell = new Cell();
	}

	public CellBuilder withId(int id) {
		this.cell.setId(id);
		return this;
	}

	public CellBuilder withUnit(Unit unit) {
		this.cell.setUnit(unit);
		return this;
	}

	public CellBuilder withMovemenentBehaviour(
			MovementBehaviour movementBehaviour) {
		this.cell.setMovementBehaviour(movementBehaviour);
		return this;
	}

	public CellBuilder withSightBehaviour(SightBehaviour sightBehaviour) {
		this.cell.setSightBehaviour(sightBehaviour);
		return this;
	}

	public CellBuilder withDefenseBehaviour(DefenseBehaviour defenseBehaviour) {
		this.cell.setDefenseBehaviour(defenseBehaviour);
		return this;
	}

	public Cell build() {
		return this.cell;
	}
}
