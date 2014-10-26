package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public class NoBluntWeapon implements BluntWeaponBehaviour {

	@Override
	public int getMaxAttackRange() {
		return 0;
	}

	@Override
	public int getMinAttackRange() {
		return 0;
	}

	@Override
	public int getAttackStrength() {
		return 0;
	}

}
