package com.github.scaronthesky.eternalwinterwars.model.combat;

import com.github.scaronthesky.eternalwinterwars.model.entity.FightingEntity;
import com.github.scaronthesky.eternalwinterwars.model.entity.NoFightingEntity;

/**
 * Class for resolving combats between fighting entities.
 * 
 * @author MatthiasTon
 * 
 */
public class Combat {

	/**
	 * Method for handling fights between FightingEntities. The result of this
	 * method has to be assigned to the defender specified in the parameter.
	 * 
	 * @param attacker
	 * @param defender
	 * @return a new instance of the defender. Might be a null object
	 *         (NoFightingEntity) if the defender is dead.
	 */
	public static FightingEntity fight(final FightingEntity attacker,
			final FightingEntity defender) {

		final FightingEntity a = attacker;
		final FightingEntity b = defender;
		
		int damageToEnemy;

		damageToEnemy = b.getPiercingArmamentDefenseStrength()
				- a.getPiercingWeaponAttackStrength();
		
		damageToEnemy += b.getBluntArmamentDefenseStrength() - 
				a.getBluntWeaponAttackStrength();
		
		b.looseHealth(damageToEnemy);
		
		if (b.getHealth() <= 0) {
			return new NoFightingEntity();
		} else {
			return b;
		}
		
	}
}
