package com.github.scaronthesky.eternalwinterwars.model.buildings;

import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.model.buildings.producebehaviours.ProduceBehaviour;
import com.github.scaronthesky.eternalwinterwars.model.entity.FightingEntity;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;

public abstract class Building extends FightingEntity {

	private ProduceBehaviour produceBehavior;
	
	public Building(UUID uuid, Player owner, String spriteKey) {
		super(uuid, spriteKey, owner);
	}
	
	/**
	 * @return the isProducingUnits
	 */
	public boolean isProducingUnits() {
		return this.produceBehavior.isProducingUnits();
	}

	/**
	 * @param produceBehavior the produceBehavior to set
	 */
	public void setProduceBehavior(ProduceBehaviour produceBehavior) {
		this.produceBehavior = produceBehavior;
	}

}
