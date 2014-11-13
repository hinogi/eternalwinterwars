package com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours;

public interface BluntWeaponBehaviour {

	public int attack(int armamentStrength);

	public int getMaxAttackRange();

	public int getMinAttackRange();

}
