package com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours;



public class Slow implements MovementBehaviour {



	@Override
	public int getMaxMovementRange() {
		return 1;
	}

	@Override
	public int getCostsOfMoving() {
		return 1;
	}

}
