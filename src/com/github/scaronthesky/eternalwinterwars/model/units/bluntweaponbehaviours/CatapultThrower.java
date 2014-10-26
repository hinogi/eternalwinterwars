package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public class CatapultThrower implements BluntWeaponBehaviour {


	@Override
	public int getMaxAttackRange() {
		return 5;
	}

	@Override
	public int getMinAttackRange() {
		return 1;
	}

	@Override
	public int getAttackStrength() {
		return 5;
	}


}
