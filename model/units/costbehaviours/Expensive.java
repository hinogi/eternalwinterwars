package com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours;


public class Expensive implements CostBehaviour {

	@Override
	public int buy() {
		return 150;
	}

	@Override
	public int sell() {
		return 100;
	}

}
