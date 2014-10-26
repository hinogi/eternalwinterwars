package com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours;


public class NormalHealth implements HealthBehaviour {

	@Override
	public int looseHealth(int points) {
		return points;
	}

	@Override
	public int gainHealth(int points) {
		return points;
	}

	@Override
	public int getFullHealth() {
		return 100;
	}

}
