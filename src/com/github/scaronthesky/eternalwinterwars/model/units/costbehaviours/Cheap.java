package com.github.scaronthesky.eternalwinterwars.model.units.costbehaviours;


public class Cheap implements CostBehaviour {

	@Override
	public int buy() {
		return 50;
	}

	@Override
	public int sell() {
		return 35;
	}

}
