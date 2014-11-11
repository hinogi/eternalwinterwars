package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public class NoBluntWeapon implements BluntWeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return 0;
	}

	@Override
	public int getMaxAttackRange() {
		return 0;
	}

	@Override
	public int getMinAttackRange() {
		return 0;
	}

}
