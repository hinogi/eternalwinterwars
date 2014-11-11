package com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours;


public class Sword implements WeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength - 2;
	}

	@Override
	public int getMaxAttackRange() {
		return 2;
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
