package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;



public class Axe implements PiercingWeaponBehaviour {

	@Override
	public int getMaxAttackRange() {
		return 1;
	}


	@Override
	public int getMinAttackRange() {
		return 0;
	}


	@Override
	public int getAttackStrength() {
		return 1;
	}

}
