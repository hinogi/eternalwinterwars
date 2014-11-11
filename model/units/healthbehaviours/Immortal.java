package com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours;


public class Immortal implements HealthBehaviour {

	@Override
	public int looseHealth(int points) {
		return 0;
	}

	@Override
	public int gainHealth(int points) {
		return points;
	}

}
