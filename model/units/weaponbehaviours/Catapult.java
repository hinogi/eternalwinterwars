package com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours;

public class Catapult implements WeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength -5;
	}

	@Override
	public int getMaxAttackRange() {
		return 5;
	}

	@Override
	public int getMinAttackRange() {
		return 1;
	}

	@Override
	public int getCostsOfAttacking() {
		return 5;
	}

}
