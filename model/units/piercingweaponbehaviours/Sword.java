package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;



public class Sword implements PiercingWeaponBehaviour {

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

}
