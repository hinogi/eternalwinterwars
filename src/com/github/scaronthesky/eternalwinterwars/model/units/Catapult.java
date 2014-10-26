package com.github.scaronthesky.eternalwinterwars.model.units;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntarmamentbehaviour.BadBluntDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours.CatapultThrower;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.VeryExpensive;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.NormalHealth;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.UnitSlowSpeed;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingarmamentbehaviours.BadPiercingDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours.NoPiercingWeapon;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.UnitNormalSighted;

public class Catapult extends Unit {

	public Catapult(UUID uuid, Sprite sprite, Player owner) {
		super(uuid, sprite, owner);
		setUuid(uuid);
		setOwner(owner);
		setHealthBehaviour(new NormalHealth());
		setMovementBehaviour(new UnitSlowSpeed());
		setPiercingWeaponBehaviour(new NoPiercingWeapon());
		setPiercingArmamentBehaviour(new BadPiercingDefense());
		setBluntWeaponBehaviour(new CatapultThrower());
		setBluntArmamentBehaviour(new BadBluntDefense());
		setSightBehaviour(new UnitNormalSighted());
		setCostBehaviour(new VeryExpensive());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Catapult [getUuid()=" + getUuid() + ", getOwner()="
				+ getOwner() + "]";
	}

}
