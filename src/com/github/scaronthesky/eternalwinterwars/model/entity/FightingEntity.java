package com.github.scaronthesky.eternalwinterwars.model.entity;

import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntarmamentbehaviour.BluntArmamentBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.bluntweaponbehaviours.BluntWeaponBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours.HealthBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingarmamentbehaviours.PiercingArmamentBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.piercingweaponbehaviours.PiercingWeaponBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.units.sightbehaviours.UnitSightBehaviour;

public abstract class FightingEntity {

	private UUID uuid;
	private String spriteKey;
	private Player owner;
	private int health;
	private HealthBehaviour healthBehaviour;
	private PiercingWeaponBehaviour piercingWeaponBehaviour;
	private PiercingArmamentBehaviour piercingArmamentBehaviour;
	private BluntWeaponBehaviour bluntWeaponBehaviour;
	private BluntArmamentBehaviour bluntArmamentBehaviour;
	private UnitSightBehaviour sightBehaviour;

	/**
	 * Constructor for abstract class.
	 * 
	 * @param uuid
	 *            the UUID
	 * @param spriteKey
	 *            the graphics sprite key
	 */
	public FightingEntity(UUID uuid, String spriteKey, Player owner) {
		super();
		this.uuid = uuid;
		this.spriteKey = spriteKey;
		this.owner = owner;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the spriteKey
	 */
	public String getSpriteKey() {
		return spriteKey;
	}

	/**
	 * @param spriteKey
	 *            the spriteKey to set
	 */
	public void setSpriteKey(String spriteKey) {
		this.spriteKey = spriteKey;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Gain health.
	 * 
	 * @param points
	 */
	public void gainHealth(int points) {
		this.health = this.healthBehaviour.gainHealth(points);
	}

	/**
	 * Loose health.
	 * 
	 * @param points
	 */
	public void looseHealth(int points) {
		this.health = this.healthBehaviour.looseHealth(points);
	}

	/**
	 * Returns the minimum distance of a piercing weapon.
	 * 
	 * @return number of fields.
	 */
	public int getMinPiercingWeaponAttackRange() {
		return this.piercingWeaponBehaviour.getMinAttackRange();
	}

	/**
	 * Returns the number of fields a unit can attack with a piercing weapon.
	 * 
	 * @return number of fields.
	 */
	public int getMaxPiercingWeaponAttackRange() {
		return this.piercingWeaponBehaviour.getMaxAttackRange();
	}

	/**
	 * Returns the minimum distance of a blunt weapon.
	 * 
	 * @return number of fields.
	 */
	public int getMinBluntWeaponAttackRange() {
		return this.bluntWeaponBehaviour.getMinAttackRange();
	}

	/**
	 * Returns the number of fields a unit can attack with a blunt weapon.
	 * 
	 * @return number of fields.
	 */
	public int getMaxBluntWeaponAttackRange() {
		return this.bluntWeaponBehaviour.getMaxAttackRange();
	}

	/**
	 * Returns the attack strength of the piercing weapon.
	 * 
	 * @return strength as int.
	 */
	public int getPiercingWeaponAttackStrength() {
		return this.piercingWeaponBehaviour.getAttackStrength();
	}

	/**
	 * Returns the defense strength against piercing weapons.
	 * 
	 * @return strength as int.
	 */
	public int getPiercingArmamentDefenseStrength() {
		return this.piercingArmamentBehaviour.getArmourStrength();
	}

	/**
	 * Returns the attack strength of the blunt weapon.
	 * 
	 * @return strength as int.
	 */
	public int getBluntWeaponAttackStrength() {
		return this.bluntWeaponBehaviour.getAttackStrength();

	}

	/**
	 * Returns the attack strength of the blunt weapon.
	 * 
	 * @return strength as int.
	 */
	public int getBluntArmamentDefenseStrength() {
		return this.bluntArmamentBehaviour.getArmourStrength();
	}

	/**
	 * Returns the distance over which a unit can see.
	 * 
	 * @param fields
	 * @return the actual number of fields a unit can see.
	 */
	public int getMaxSightRange() {
		return this.sightBehaviour.getMaxSightRange();
	}

	/**
	 * Set the initial health when the unit is created.
	 * 
	 * @param healthBehaviour
	 */
	public void setInitialHealth(HealthBehaviour healthBehaviour) {
		this.health = healthBehaviour.getFullHealth();
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param piercingWeaponBehaviour
	 *            the piercingWeaponBehaviour to set
	 */
	public void setPiercingWeaponBehaviour(
			PiercingWeaponBehaviour piercingWeaponBehaviour) {
		this.piercingWeaponBehaviour = piercingWeaponBehaviour;
	}

	/**
	 * @param piercingArmamentBehaviour
	 *            the piercingArmamentBehaviour to set
	 */
	public void setPiercingArmamentBehaviour(
			PiercingArmamentBehaviour piercingArmamentBehaviour) {
		this.piercingArmamentBehaviour = piercingArmamentBehaviour;
	}

	/**
	 * @param bluntWeaponBehaviour
	 *            the bluntWeaponBehaviour to set
	 */
	public void setBluntWeaponBehaviour(
			BluntWeaponBehaviour bluntWeaponBehaviour) {
		this.bluntWeaponBehaviour = bluntWeaponBehaviour;
	}

	/**
	 * @param bluntArmamentBehaviour
	 *            the bluntArmamentBehaviour to set
	 */
	public void setBluntArmamentBehaviour(
			BluntArmamentBehaviour bluntArmamentBehaviour) {
		this.bluntArmamentBehaviour = bluntArmamentBehaviour;
	}

	/**
	 * @param sightBehaviour
	 *            the sightBehaviour to set
	 */
	public void setSightBehaviour(UnitSightBehaviour sightBehaviour) {
		this.sightBehaviour = sightBehaviour;
	}

	/**
	 * @param healthBehaviour
	 *            the healthBehaviour to set
	 */
	public void setHealthBehaviour(HealthBehaviour healthBehaviour) {
		this.healthBehaviour = healthBehaviour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FightingEntity other = (FightingEntity) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FightingEntity [uuid=" + uuid + ", owner=" + owner + "]";
	}

}
