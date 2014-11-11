package com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours;

public class VeryCheap implements CostBehaviour {

	@Override
	public int buy() {
		return 10;
	}

	@Override
	public int sell() {
		return 5;
	}

}
