package com.github.scaronthesky.eternalwinterwars.model.units.actionpointbehaviours;

public class NeverLooseActionPoints implements ActionPointBehaviour {

	@Override
	public int spendActionPoints(int points) {
		return 0;
	}

	@Override
	public int gainActionPoints(int points) {
		return points;
	}

}
