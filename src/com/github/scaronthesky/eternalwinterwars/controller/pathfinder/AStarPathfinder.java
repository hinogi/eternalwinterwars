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
		if (directPath.blocked(mover, xDestinationLocation, yDestinationLocation)){
			return null;
		}
		
		// set cost 0 to starting cell
		ALLNODES.get(xStartingLocation, yStartingLocation).cost = 0;
		
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
}
