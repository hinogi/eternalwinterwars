package com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours;

public interface PiercingWeaponBehaviour {

	public int attack(int armamentStrength);

	public int getMaxAttackRange();
	
	public int getMinAttackRange();
}
