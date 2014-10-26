package com.github.scaronthesky.eternalwinterwars.model.units;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.entity.FightingEntity;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.CostBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.UnitMovementBehaviour;

public class Unit extends FightingEntity {

	private UnitMovementBehaviour movementBehaviour;
	private CostBehaviour costBehaviour;

	/**
	 * A unit. Use UnitBuilder class to create new units.
	 */
	public Unit(UUID uuid, Sprite sprite, Player owner) {
		super(uuid, sprite, owner);
	}

	/**
	 * Returns the number of fields a unit can move.
	 * 
	 * @return number of fields.
	 */
	public int getMaxMovementRange() {
		return this.movementBehaviour.getMaxMovementRange();
	}


	/**
	 * Returns the cost of buying this unit.
	 * 
	 * @return the cost of the unit.
	 */
	public int buy() {
		return this.costBehaviour.buy();
	}

	/**
	 * Returns the profit of selling this unit.
	 * 
	 * @return the profit of selling this unit.
	 */
	public int sell() {
		return this.costBehaviour.sell();
	}

	/**
	 * @param movementBehaviour
	 *            the movementBehaviour to set
	 */
	public void setMovementBehaviour(UnitMovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}
	
	/**
	 * @param costBehaviour
	 *            the costBehaviour to set
	 */
	public void setCostBehaviour(CostBehaviour costBehaviour) {
		this.costBehaviour = costBehaviour;
	}

	

}
