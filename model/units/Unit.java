package com.github.scaronthesky.eternalwinterwars.model.units;

import com.github.scaronthesky.eternalwinterwars.model.armamentBehaviours.ArmamentBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.players.PlayerInterface;
import com.github.scaronthesky.eternalwinterwars.model.units.actionpointbehaviours.ActionPointBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.CostBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.HealthBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.MovementBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.SightBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours.WeaponBehaviour;

public class Unit {

	private int id;
	private String name;
	private int health;
	private int actionPoints;
	private PlayerInterface owner;
	private HealthBehaviour healthBehaviour;
	private ActionPointBehaviour actionPointBehavior;
	private MovementBehaviour movementBehaviour;
	private WeaponBehaviour weaponBehaviour;
	private SightBehaviour sightBehaviour;
	private ArmamentBehaviour armamentBehaviour;
	private CostBehaviour costBehaviour;

	public Unit() {
		super();
	}

	/**
	 * A unit.
	 * 
	 * @param id
	 * @param name
	 * @param health
	 * @param actionPoints
	 * @param owner
	 * @param healthBehaviour
	 * @param actionPointBehavior
	 * @param movementBehaviour
	 * @param weaponBehaviour
	 * @param sightBehaviour
	 * @param armamentBehaviour
	 * @param costBehaviour
	 */
	public Unit(int id, String name, int health, int actionPoints,
			PlayerInterface owner, HealthBehaviour healthBehaviour,
			ActionPointBehaviour actionPointBehavior,
			MovementBehaviour movementBehaviour,
			WeaponBehaviour weaponBehaviour, SightBehaviour sightBehaviour,
			ArmamentBehaviour armamentBehaviour, CostBehaviour costBehaviour) {
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.actionPoints = actionPoints;
		this.owner = owner;
		this.healthBehaviour = healthBehaviour;
		this.actionPointBehavior = actionPointBehavior;
		this.movementBehaviour = movementBehaviour;
		this.weaponBehaviour = weaponBehaviour;
		this.sightBehaviour = sightBehaviour;
		this.armamentBehaviour = armamentBehaviour;
		this.costBehaviour = costBehaviour;
	}

	/**
	 * Gain health.
	 * 
	 * @param points
	 */
	public void gainHealth(int points) {
		this.health = this.healthBehaviour.gainHealth(points);
	}

	/**
	 * Loose health.
	 * 
	 * @param points
	 */
	public void looseHealth(int points) {
		this.health = this.healthBehaviour.looseHealth(points);
	}

	/**
	 * Gain action points.
	 * 
	 * @param points
	 */
	public void gainActionPoints(int points) {
		this.actionPoints += this.actionPointBehavior.gainActionPoints(points);
	}

	/**
	 * Loose action points.
	 * 
	 * @param points
	 */
	public void looseActionPoints(int points) {
		this.actionPoints -= this.actionPointBehavior.spendActionPoints(points);
	}

	/**
	 * Move
	 */
	public void move() {
		final int movementCosts = this.movementBehaviour.getCostsOfMoving();
		this.actionPoints -= this.actionPointBehavior
				.spendActionPoints(movementCosts);
	}

	/**
	 * Returns the number of fields a unit can move.
	 * 
	 * @return
	 */
	public int getMaxMovementRange() {
		return this.movementBehaviour.getMaxMovementRange();
	}

	/**
	 * Attack an enemy unit.
	 * 
	 * @param enemyUnit
	 */
	public void attack(Unit enemyUnit) {
		int damageToEnemy = this.weaponBehaviour.attack(enemyUnit.defend());
		enemyUnit.looseHealth(damageToEnemy);
		final int attackCosts = this.weaponBehaviour.getCostsOfAttacking();
		this.actionPoints -= this.actionPointBehavior
				.spendActionPoints(attackCosts);
	}

	/**
	 * Returns the distance over which a unit can attack.
	 * 
	 * @return
	 */
	public int getMaxAttackRange() {
		return this.weaponBehaviour.getMaxAttackRange();
	}

	/**
	 * Returns the armament strength of the unit.
	 * 
	 * @return the armament strength.
	 */
	public int defend() {
		return this.armamentBehaviour.defend();
	}

	/**
	 * Returns the distance over which a unit can see.
	 * 
	 * @param fields
	 * @return the actual number of fields a unit can see.
	 */
	public int getMaxSightRange() {
		return this.sightBehaviour.getMaxSightRange();
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
	 * Units are equal if the have the same owner, id and name.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	/**
	 * Units have the same hash code if the have the same owner, id and name.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	/**
	 * @return the owner
	 */
	public PlayerInterface getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(PlayerInterface owner) {
		this.owner = owner;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return the actionPoints
	 */
	public int getActionPoints() {
		return actionPoints;
	}

	/**
	 * @param healthBehaviour
	 *            the healthBehaviour to set
	 */
	public void setHealthBehaviour(HealthBehaviour healthBehaviour) {
		this.healthBehaviour = healthBehaviour;
	}

	/**
	 * @param actionPointBehavior
	 *            the actionPointBehavior to set
	 */
	public void setActionPointBehavior(ActionPointBehaviour actionPointBehavior) {
		this.actionPointBehavior = actionPointBehavior;
	}

	/**
	 * @param movementBehaviour
	 *            the movementBehaviour to set
	 */
	public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}

	/**
	 * @param weaponBehaviour
	 *            the weaponBehaviour to set
	 */
	public void setWeaponBehaviour(WeaponBehaviour weaponBehaviour) {
		this.weaponBehaviour = weaponBehaviour;
	}

	/**
	 * @param sightBehaviour
	 *            the sightBehaviour to set
	 */
	public void setSightBehaviour(SightBehaviour sightBehaviour) {
		this.sightBehaviour = sightBehaviour;
	}

	/**
	 * @param armamentBehaviour
	 *            the armamentBehaviour to set
	 */
	public void setArmamentBehaviour(ArmamentBehaviour armamentBehaviour) {
		this.armamentBehaviour = armamentBehaviour;
	}

	/**
	 * @param costBehaviour
	 *            the costBehaviour to set
	 */
	public void setCostBehaviour(CostBehaviour costBehaviour) {
		this.costBehaviour = costBehaviour;
	}

}
