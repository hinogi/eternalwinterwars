package com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours;

public class VeryExpensive implements CostBehaviour {

	@Override
	public int buy() {
		return 500;
	}

	@Override
	public int sell() {
		return 300;
	}

}
