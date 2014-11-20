package com.github.scaronthesky.eternalwinterwars.controller.pathfinder;



public class AStarPathfinder implements AStarHeuristic {

	@Override
	public int getManhattanDistanceToDestination(int xStartingLocation,
			int yStartingLocation, int xDestinationLocation,
			int yDestinationLocation) {
		// TODO Auto-generated method stub
		int horizontalDistance = xDestinationLocation - xStartingLocation;
		int verticalDistance = yDestinationLocation - yStartingLocation;
		
		int manhattanDistance = horizontalDistance * horizontalDistance + verticalDistance * verticalDistance;
		
		return manhattanDistance;
	}

	
}
