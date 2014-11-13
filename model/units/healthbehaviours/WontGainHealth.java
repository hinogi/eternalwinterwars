package com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours;

public class WontGainHealth implements HealthBehaviour {

	@Override
	public int looseHealth(int points) {
		return 100;
	}

	@Override
	public int gainHealth(int points) {
		return 0;
	}

}
