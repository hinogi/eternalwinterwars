package com.github.scaronthesky.eternalwinterwars.model.buildings;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.buildings.producebehaviours.ProducesUnits;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntarmamentbehaviour.BadBluntDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours.NoBluntWeapon;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.WeakHealth;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingarmamentbehaviours.BadPiercingDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours.Bow;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.UnitEagleEyed;

public class Castle extends Building {

	public Castle(UUID uuid, String spriteKey, Player owner) {
		super(uuid, owner, spriteKey);
		setProduceBehavior(new ProducesUnits());
		setHealthBehaviour(new WeakHealth());
		setPiercingWeaponBehaviour(new Bow());
		setPiercingArmamentBehaviour(new BadPiercingDefense());
		setBluntWeaponBehaviour(new NoBluntWeapon());
		setBluntArmamentBehaviour(new BadBluntDefense());
		setSightBehaviour(new UnitEagleEyed());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Castle [getUuid()=" + getUuid() + ", getOwner()=" + getOwner()
				+ "]";
	}

}
