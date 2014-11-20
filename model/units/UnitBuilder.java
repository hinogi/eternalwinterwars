/**
 * Source code generated by Fluent Builders Generator
 * Do not modify this file
 * See generator home page at: http://code.google.com/p/fluent-builders-generator-eclipse-plugin/
 */

package com.github.scaronthesky.eternalwinterwars.model.units;

import com.github.scaronthesky.eternalwinterwars.model.armamentBehaviours.ArmamentBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.players.PlayerInterface;
import com.github.scaronthesky.eternalwinterwars.model.units.actionpointbehaviours.ActionPointBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.CostBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.HealthBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.MovementBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.SightBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours.WeaponBehaviour;

public class UnitBuilder {

	private Unit unit;

	public UnitBuilder() {

	}

	public UnitBuilder withId(int id) {
		this.unit.setId(id);
		return this;
	}

	public UnitBuilder withName(String name) {
		this.unit.setName(name);
		return this;
	}

	public UnitBuilder withHealth(int health) {
		this.unit.setHealth(health);
		return this;
	}

	public UnitBuilder withActionPoints(int actionPoints) {
		this.unit.setActionPoints(actionPoints);
		return this;
	}

	public UnitBuilder withOwner(PlayerInterface player) {
		this.unit.setOwner(player);
		return this;
	}

	public UnitBuilder withHealthBehaviour(HealthBehaviour healthBehaviour) {
		this.unit.setHealthBehaviour(healthBehaviour);
		return this;
	}

	public UnitBuilder withActionPointBehaviour(
			ActionPointBehaviour actionPointBehaviour) {
		this.unit.setActionPointBehavior(actionPointBehaviour);
		return this;
	}

	public UnitBuilder withMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.unit.setMovementBehaviour(movementBehaviour);
		return this;
	}

	public UnitBuilder withWeaponBehaviour(WeaponBehaviour weaponBehaviour) {
		this.unit.setWeaponBehaviour(weaponBehaviour);
		return this;
	}

	public UnitBuilder withSightBehaviour(SightBehaviour sightBehaviour) {
		this.unit.setSightBehaviour(sightBehaviour);
		return this;
	}

	public UnitBuilder withArmamentBehaviour(ArmamentBehaviour armamentBehaviour) {
		this.unit.setArmamentBehaviour(armamentBehaviour);
		return this;
	}
	
	public UnitBuilder withCostBehaviour(CostBehaviour costBehaviour) {
		this.unit.setCostBehaviour(costBehaviour);
		return this;
	}
	
	public Unit build() {
		return this.unit;
	}
	

}