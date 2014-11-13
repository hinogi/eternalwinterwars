package com.github.scaronthesky.eternalwinterwars.model.units.actionpointbehaviours;

public class Normal implements ActionPointBehaviour {

	@Override
	public int spendActionPoints(int points) {
		return points;
	}

	@Override
	public int gainActionPoints(int points) {
		return points;
	}

}
