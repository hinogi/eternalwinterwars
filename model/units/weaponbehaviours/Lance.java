package com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours;

public class Lance implements WeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength - 4;
	}

	@Override
	public int getMaxAttackRange() {
		return 1;
	}

	@Override
	public int getMinAttackRange() {
		return 0;
	}

	@Override
	public int getCostsOfAttacking() {
		return 2;
	}

}
