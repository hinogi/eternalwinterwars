package com.github.scaronthesky.eternalwinterwars.controller.pathfinder;

public interface AStarHeuristic {
	public int getManhattanDistanceToDestination( int xStartingLocation, int yStartingLocation, int xDestinationLocation, int yDestinationLocation);
}
