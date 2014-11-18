package com.github.scaronthesky.eternalwinterwars.model.units;


import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntarmamentbehaviour.BadBluntDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours.NoBluntWeapon;
import com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours.VeryCheap;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.WeakHealth;
import com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours.UnitFastSpeed;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingarmamentbehaviours.BadPiercingDefense;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours.Bow;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.UnitEagleEyed;

public class Archer extends Unit {

	public Archer(UUID uuid, String spriteKey, Player owner) {
		super(uuid, spriteKey, owner);
		setHealthBehaviour(new WeakHealth());
		setMovementBehaviour(new UnitFastSpeed());
		setPiercingWeaponBehaviour(new Bow());
		setPiercingArmamentBehaviour(new BadPiercingDefense());
		setBluntWeaponBehaviour(new NoBluntWeapon());
		setBluntArmamentBehaviour(new BadBluntDefense());
		setSightBehaviour(new UnitEagleEyed());
		setCostBehaviour(new VeryCheap());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Archer [getUuid()=" + getUuid() + ", getOwner()=" + getOwner()
				+ "]";
	}
	
}
