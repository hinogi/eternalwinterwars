package com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours;

public class Bow implements WeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength -3;
	}

	@Override
	public int getMaxAttackRange() {
		return 3;
	}

	@Override
	public int getMinAttackRange() {
		return 1;
	}

	@Override
	public int getCostsOfAttacking() {
		return 3;
	}


}
