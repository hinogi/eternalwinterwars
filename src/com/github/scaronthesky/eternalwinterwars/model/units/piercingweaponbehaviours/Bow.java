package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;

public class Bow implements PiercingWeaponBehaviour {

	@Override
	public int getMaxAttackRange() {
		return 3;
	}

	@Override
	public int getMinAttackRange() {
		return 1;
	}

	@Override
	public int getAttackStrength() {
		return 3;
	}

}
