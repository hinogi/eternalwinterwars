package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public class Lance implements BluntWeaponBehaviour {

	@Override
	public int attack(int armamentStrength) {
		return armamentStrength - 4;
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
