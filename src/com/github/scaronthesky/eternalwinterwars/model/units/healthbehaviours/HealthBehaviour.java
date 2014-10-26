package com.github.scaronthesky.eternalwinterwars.model.units.healthbehaviours;

public interface HealthBehaviour {

	public int looseHealth(int points);

	public int gainHealth(int points);

	public int getFullHealth();
}
