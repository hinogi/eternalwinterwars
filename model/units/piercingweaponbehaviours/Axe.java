package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;



public class Axe implements PiercingWeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength - 1;
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
