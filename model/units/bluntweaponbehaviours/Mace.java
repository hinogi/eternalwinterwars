package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public class Mace implements BluntWeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return 3;
	}

	@Override
	public int getMaxAttackRange() {
		return 1;
	}

	@Override
	public int getMinAttackRange() {
		return 0;
	}

}
