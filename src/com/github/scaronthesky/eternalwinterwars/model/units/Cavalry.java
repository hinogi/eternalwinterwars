package com.github.scaronthesky.eternalwinterwars.model.units;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntarmamentbehaviour.GoodBluntDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours.Lance;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.Expensive;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.PowerfulHealth;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.UnitVeryFastSpeed;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingarmamentbehaviours.GoodPiercingDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours.NoPiercingWeapon;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.UnitNormalSighted;

public class Cavalry extends Unit {

	public Cavalry(UUID uuid, Sprite sprite, Player owner) {
		super(uuid, sprite, owner);
		setUuid(uuid);
		setOwner(owner);
		setHealthBehaviour(new PowerfulHealth());
		setMovementBehaviour(new UnitVeryFastSpeed());
		setPiercingWeaponBehaviour(new NoPiercingWeapon());
		setPiercingArmamentBehaviour(new GoodPiercingDefense());
		setBluntWeaponBehaviour(new Lance());
		setBluntArmamentBehaviour(new GoodBluntDefense());
		setSightBehaviour(new UnitNormalSighted());
		setCostBehaviour(new Expensive());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cavalry [getUuid()=" + getUuid() + ", getOwner()=" + getOwner()
				+ "]";
	}

}
