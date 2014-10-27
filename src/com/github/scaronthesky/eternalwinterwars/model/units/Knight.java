package com.github.scaronthesky.eternalwinterwars.model.units;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntarmamentbehaviour.BadBluntDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours.NoBluntWeapon;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.Cheap;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.NormalHealth;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.UnitNormalSpeed;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingarmamentbehaviours.GoodPiercingDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours.Sword;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.UnitNormalSighted;

public class Knight extends Unit {

	public Knight(UUID uuid, Sprite sprite, Player owner) {
		super(uuid, sprite, owner);
		setHealthBehaviour(new NormalHealth());
		setMovementBehaviour(new UnitNormalSpeed());
		setPiercingWeaponBehaviour(new Sword());
		setPiercingArmamentBehaviour(new GoodPiercingDefense());
		setBluntWeaponBehaviour(new NoBluntWeapon());
		setBluntArmamentBehaviour(new BadBluntDefense());
		setSightBehaviour(new UnitNormalSighted());
		setCostBehaviour(new Cheap());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Knight [getUuid()=" + getUuid() + ", getOwner()=" + getOwner()
				+ "]";
	}

}
