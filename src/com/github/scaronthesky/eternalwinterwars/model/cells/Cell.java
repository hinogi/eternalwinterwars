package com.github.scaronthesky.eternalwinterwars.model.cells;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.cells.defensebehaviours.CellDefenseBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.movementbehaviours.CellMovementBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.cells.sightbehaviours.CellSightBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

public class Cell {

	private UUID uuid;
	private Sprite sprite;
	private CellMovementBehaviour movementBehaviour;
	private CellSightBehaviour sightBehaviour;
	private CellDefenseBehaviour defenseBehaviour;
	private Unit unit;
	private Building building;

	/**
	 * Use CellBuilder or sub classes.
	 */
	public Cell() {
	}

	/**
	 * A model cell.
	 * 
	 * @param uuid
	 * @param unit
	 * @param building
	 */
	public Cell(final UUID uuid, final Unit unit, final Building building, final Sprite sprite) {
		this.uuid = uuid;
		this.unit = unit;
		this.building = building;
		this.sprite = sprite;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * 
	 * @return the movement modifier
	 */
	public int getMovementModifier() {
		return this.movementBehaviour.getMovementModifier();
	}

	/**
	 * 
	 * @return the defense modifier
	 */
	public int getDefenseModifier() {
		return this.defenseBehaviour.getDefenseModifier();
	}

	/**
	 * 
	 * @return the sight modifier
	 */
	public int getSightModifier() {
		return this.sightBehaviour.getSightModifier();
	}

	/**
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @param building
	 *            the building to set
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * @param movementBehaviour
	 *            the movementBehaviour to set
	 */
	public void setMovementBehaviour(CellMovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}

	/**
	 * @param sightBehaviour
	 *            the sightBehaviour to set
	 */
	public void setSightBehaviour(CellSightBehaviour sightBehaviour) {
		this.sightBehaviour = sightBehaviour;
	}

	/**
	 * @param defenseBehaviour
	 *            the defenseBehaviour to set
	 */
	public void setDefenseBehaviour(CellDefenseBehaviour defenseBehaviour) {
		this.defenseBehaviour = defenseBehaviour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell [uuid=" + uuid + ", unit=" + unit + ", building="
				+ building + "]";
	}

	

}
