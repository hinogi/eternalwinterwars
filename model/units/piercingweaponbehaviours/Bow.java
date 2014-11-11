package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;


public class Bow implements PiercingWeaponBehaviour {

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



}
