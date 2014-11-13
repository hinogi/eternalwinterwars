package com.github.scaronthesky.eternalwinterwars.model.units.movementbehaviours;


public class Fast implements MovementBehaviour {


	@Override
	public int getMaxMovementRange() {
		return 3;
	}

	@Override
	public int getCostsOfMoving() {
		return 3;
	}

}
