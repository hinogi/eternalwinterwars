package com.github.scaronthesky.eternalwinterwars.controller.pathfinder;

import java.util.ArrayList;
import java.util.Collections;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.pathfinder.AStarClosestHeuristic;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;

public class AStarPathfinder {

	private Model model = Controller.model;
	private CellControl map = model.getCellControl();
	private AStarHeuristic heuristic;
	private ArrayList<AStarNode> closedList = new ArrayList<AStarNode>();
	private SortedList openList = new SortedList();
	private int maxSearchDistance;
	private ArrayList<ArrayList<Cell>> ALLNODES = model.getCellControl().getCells();
	private boolean allowCuttingCorners = false;
	private int mapWidth = map.getColumnCount();
	private int mapHeight = map.getRowCount();
	

	public AStarPathfinder(CellControl map, int maxSearchDistance, boolean allowCuttingCorners ){
		this.(map, maxSearchDistance, allowCuttingCorners, new AStarClosestHeuristic());
	}
	
	public AStarPathfinder(CellControl map, int maxSearchDistance, boolean allowCuttingCorners, AStarHeuristic heuristic){
		this.heuristic = heuristic;
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
		this.allowCuttingCorners = allowCuttingCorners;
	}
	
	public AStarPath findPath(AStarMover mover, int xStartingLocation, int yStartingLocation, int xDestinationLocation, int yDestinationLocation){
		// break if destination is totally blocked
		if (map.blocked(mover, xDestinationLocation, yDestinationLocation)){
			return null;
		}
		
		// set cost 0 to starting cell
		AStarNode startingNode = ALLNODES.get(xStartingLocation, yStartingLocation);
		startingNode.cost = 0;
		startingNode.depth = 0;
		closedList.clear();
		openList.clear();
		openList.add(startingNode);
		
		AStarNode destinationNode = ALLNODES.get(xDestinationLocation, yDestinationLocation);
		destinationNode.parent = null;
		
		int maxDistance = 0;
		
		while ( ( maxDistance < maxSearchDistance) && (openList.size() != 0)){
			AStarNode current = getFirstInOpenList();
			
			if ( current == destinationNode) {
				break;
			}
			
			removeFromOpenList( current );
			addToClosedList( current );
			
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if ( ( i == 0 ) && (j == 0) ) {
						continue;
					}
					
					if (!allowCuttingCorners){
						if ( (i != 0 ) && ( j != 0) ) {
							continue;
						}
					}
					
					int xNextLocation = i + current.x;
					int yNextLocation = j + current.y;
					
					if ( isValidLocation ( mover, xStartingLocation, yStartingLocation, xNextLocation, yNextLocation){
						float nextStepCost = current.cost + getMovementCost( mover, current.x, current.y, xNextLocation, yNextLocation  );
					})
				}
			}
		}
		
		
	}
	
	private class SortedList {

		private ArrayList<Cell> list = new ArrayList<Cell>();
		
		public Cell first() {
			return list.get(0);
		}
		
		public void clear(){
			list.clear();
		}
		
		public void add(Cell c){
			list.add(c);
			Collections.sort(list);
		}
		
		public int size(){
			return list.size();
		}
		
		public boolean contains( Cell c){
			return list.contains(c);
		}
	}
	
	protected AStarNode getFirstInOpenList(){
		return (AStarNode) openList.first();
	}
	
	protected void addToOpenList( AStarNode node ){
		openList.add( node );
	}
	
	protected boolean inOpenList( AStarNode node ){
		return openList.contains( node );
	}
	
	protected void removeFromOpenList( AStarNode node ){
		openList.remove( node );
	}
	
	protected void addToClosedList ( AStarNode node){
		closedList.add( node )
	}
	
	protected boolean inClosedList ( AStarNode node){
		return closedList.contains( node );
	}
	
	protected void removeFromClosedList( AStarNode node ){
		closedList.remove( node );
	}
}
