package com.github.scaronthesky.eternalwinterwars.controller.pathfinder;

import java.util.ArrayList;

public class AStarNode{
	AStarNode north;
	AStarNode northEast;
	AStarNode east;
	AStarNode southEast;
	AStarNode south;
	AStarNode southwest;
	AStarNode west;
	AStarNode northwest;
	ArrayList<AStarNode> neighborList;
	float distanceFromStart;
	float manhattanDistanceToDestination;
	AStarNode previousNode;
	int xCoord;
	int yCoord;
	boolean visited;
	boolean isObstacle;
	boolean isStart;
	boolean isDestination;
	
	AStarNode(int xCoord, int yCoord){
		neighborList = new ArrayList<AStarNode>();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.visited = false;
		this.manhattanDistanceToDestination = 100000;
		this.isObstacle = false;
		this.isStart = false;
		this.isDestination = false;
	}
	
	AStarNode(int xCoord, int yCoord, int manhattanDistanceToDestination, boolean visited, boolean isObstacle, boolean isStart, boolean isDestination){
		neighborList = new ArrayList<AStarNode>();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.visited = visited;
		this.manhattanDistanceToDestination = 100000;
		this.isObstacle = isObstacle;
		this.isStart = isStart;
		this.isDestination = isDestination;
	}

	public AStarNode getNorth() {
		return north;
	}

	public void setNorth(AStarNode north) {
		if (neighborList.contains(this.north)){
			neighborList.remove(this.north);
		}
		neighborList.add(north);
		
		this.north = north;
	}

	public AStarNode getNorthEast() {
		return northEast;
	}

	public void setNorthEast(AStarNode northEast) {
		if (neighborList.contains(this.northEast)){
			neighborList.remove(this.northEast);
		}
		neighborList.add(northEasst);
		
		this.northEast = northEast;
	}

	public AStarNode getEast() {
		return east;
	}

	public void setEast(AStarNode east) {
		if (neighborList.contains(this.east)){
			neighborList.remove(this.east);
		}
		neighborList.add(east);
		
		this.east = east;
	}

	public AStarNode getSouthEast() {
		return southEast;
	}

	public void setSouthEast(AStarNode southEast) {
		if (neighborList.contains(this.southEast)){
			neighborList.remove(this.southEast);
		}
		neighborList.add(southEast);
		
		this.southEast = southEast;
	}

	public AStarNode getSouth() {
		return south;
	}

	public void setSouth(AStarNode south) {
		if (neighborList.contains(this.south)){
			neighborList.remove(this.south);
		}
		neighborList.add(south);
		
		this.south = south;
	}

	public AStarNode getSouthwest() {
		return southwest;
	}

	public void setSouthwest(AStarNode southwest) {
		if (neighborList.contains(this.southwest)){
			neighborList.remove(this.southwest);
		}
		neighborList.add(southwest);
		
		this.southwest = southwest;
	}

	public AStarNode getWest() {
		return west;
	}

	public void setWest(AStarNode west) {
		if (neighborList.contains(this.west)){
			neighborList.remove(this.west);
		}
		neighborList.add(west);
		
		this.west = west;
	}

	public AStarNode getNorthwest() {
		return northwest;
	}

	public void setNorthwest(AStarNode northwest) {
		if (neighborList.contains(this.northwest)){
			neighborList.remove(this.northwest);
		}
		neighborList.add(northwest);
		
		this.northwest = northwest;
	}

	public ArrayList<AStarNode> getNeighborList() {
		return neighborList;
	}

	public void setNeighborList(ArrayList<AStarNode> neighborList) {
		this.neighborList = neighborList;
	}

	public float getDistanceFromStart() {
		return distanceFromStart;
	}

	public void setDistanceFromStart(float distanceFromStart) {
		this.distanceFromStart = distanceFromStart;
	}

	public float getManhattanDistanceToDestination() {
		return manhattanDistanceToDestination;
	}

	public AStarNode getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(AStarNode previousNode) {
		this.previousNode = previousNode;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public void setManhattanDistanceToDestination(
			float manhattanDistanceToDestination) {
		this.manhattanDistanceToDestination = manhattanDistanceToDestination;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isDestination() {
		return isDestination;
	}

	public void setDestination(boolean isDestination) {
		this.isDestination = isDestination;
	}
	
	public boolean equals(AStarNode node){
		return (node.xCoord == xCoord) && (node.yCoord == yCoord);
	}
	
	public int compareTo(AStarNode node){
		float totalDistance = manhattanDistanceToDestination;
		float nodeTotalDistance = node.getManhattanDistanceToDestination() + node.getDistanceFromStart();
		
		if (totalDistance < nodeTotalDistance){
			return -1;
		} else if (totalDistance > nodeTotalDistance){
			return 1;
		} else {
			return 0;
		}
	}
	
	
}
