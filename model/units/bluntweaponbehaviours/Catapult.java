package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public class Catapult implements BluntWeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength -5;
	}

	@Override
	public int getMaxAttackRange() {
		return 5;
	}

	@Override
	public int getMinAttackRange() {
		return 1;
	}


}
