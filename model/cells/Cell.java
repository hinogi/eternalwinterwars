package com.github.scaronthesky.eternalwinterwars.model.cells;

import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.DefenseBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.MovementBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.SightBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class Cell {

	private int id;
	private Unit unit;
	private MovementBehaviour movementBehaviour;
	private SightBehaviour sightBehaviour;
	private DefenseBehaviour defenseBehaviour;

	public Cell() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public int getMovementModifier() {
		return this.movementBehaviour.getMovementModifier();
	}

	public int getSightModifier() {
		return this.sightBehaviour.getSightModifier();
	}

	public int getDefenseModifier() {
		return this.defenseBehaviour.getDefenseModifier();
	}

	public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}

	public void setSightBehaviour(SightBehaviour sightBehaviour) {
		this.sightBehaviour = sightBehaviour;
	}

	public void setDefenseBehaviour(DefenseBehaviour defenseBehaviour) {
		this.defenseBehaviour = defenseBehaviour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((defenseBehaviour == null) ? 0 : defenseBehaviour.hashCode());
		result = prime
				* result
				+ ((movementBehaviour == null) ? 0 : movementBehaviour
						.hashCode());
		result = prime * result
				+ ((sightBehaviour == null) ? 0 : sightBehaviour.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (defenseBehaviour == null) {
			if (other.defenseBehaviour != null)
				return false;
		} else if (!defenseBehaviour.equals(other.defenseBehaviour))
			return false;
		if (movementBehaviour == null) {
			if (other.movementBehaviour != null)
				return false;
		} else if (!movementBehaviour.equals(other.movementBehaviour))
			return false;
		if (sightBehaviour == null) {
			if (other.sightBehaviour != null)
				return false;
		} else if (!sightBehaviour.equals(other.sightBehaviour))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell [unit=" + unit + ", movementBehaviour="
				+ movementBehaviour + ", sightBehaviour=" + sightBehaviour
				+ ", defenseBehaviour=" + defenseBehaviour + "]";
	}

}
