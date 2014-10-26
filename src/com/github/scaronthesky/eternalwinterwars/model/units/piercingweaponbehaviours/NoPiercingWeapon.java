package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;

public class NoPiercingWeapon implements PiercingWeaponBehaviour {

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
