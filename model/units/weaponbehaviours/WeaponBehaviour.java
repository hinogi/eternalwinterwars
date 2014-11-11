package com.github.scaronthesky.eternalwinterwars.model.units.weaponbehaviours;

public interface WeaponBehaviour {

	public int attack(int armamentStrength);

	public int getMaxAttackRange();
	
	public int getMinAttackRange();

	public int getCostsOfAttacking();
	
}
