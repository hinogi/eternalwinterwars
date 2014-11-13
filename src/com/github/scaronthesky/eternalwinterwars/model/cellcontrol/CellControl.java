package com.github.scaronthesky.eternalwinterwars.model.cellcontrol;

import java.util.ArrayList;

import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;

public class CellControl {

	private ArrayList<ArrayList<Cell>> cells;
	
	private ArrayList<CellWithMovement> reachableCells = new ArrayList<CellWithMovement>();
	

	public CellControl(ArrayList<ArrayList<Cell>> cells) {
		super();
		this.cells = cells;
	}

	/**
	 * Returns all the cells which the unit on this cell can reach.
	 * 
	 * @return the cells
	 */
	public ArrayList<ArrayList<Cell>> getReachableCells(final Cell cell) {
		final int maximumMovement = cell.getUnit().getMaxMovementRange();
		int cellXPos = 0, cellYPos = 0;
		boolean cellFound = false;
		for (int i = 0; i < cells.size(); i++) {
			for (int j = 0; j < cells.get(i).size(); j++) {
				if (cells.get(i).get(j).equals(cell)) {
					cellXPos = i;
					cellYPos = j;
					cellFound = true;
				}
			}
		}
		if (!cellFound) {
			throw new IllegalArgumentException(
					"Given cell is not part of this CellControls cells");
		}
		ArrayList<CellWithMovement> reachableCells = new ArrayList<CellWithMovement>();
		int remainingMovement = maximumMovement;
		getReachableCellsInDirection(cellXPos, cellYPos,remainingMovement,Direction.NORTH);
		// TODO: Get reachable cells in other directions
		
		return cells;
	}

	private void getReachableCellsInDirection(int cellXPos, int cellYPos, int remainingMovement, Direction direction) {
		// TODO: Add switch case for directions
		int steps = 1;
		while (remainingMovement > 0) {
			Cell nextCell = cells.get(cellXPos - steps).get(cellYPos);
			remainingMovement -= nextCell.getMovementModifier();
			if (remainingMovement >= 0) {
				this.reachableCells.add(new CellWithMovement(nextCell,
						remainingMovement));
			}
			steps++;
		}
	}

	/**
	 * Returns all the cells which the unit on this cell can see.
	 * 
	 * @return the cells
	 */
	public ArrayList<ArrayList<Cell>> getSeeableCells(final Cell cell) {
		// TODO: Implement
		return cells;
	}

	/**
	 * Returns all the cells which the unit on this cell can attack.
	 * 
	 * @return the cells
	 */
	public ArrayList<ArrayList<Cell>> getAttackableCells(final Cell cell) {
		// TODO: Implement
		return cells;
	}

	/**
	 * @return the cells
	 */
	public ArrayList<ArrayList<Cell>> getCells() {
		// TODO: Implement
		return cells;
	}

	private class CellWithMovement {

		private Cell cell;
		private int remainingMovement;

		public CellWithMovement(Cell cell, int remainingMovement) {
			super();
			this.cell = cell;
			this.remainingMovement = remainingMovement;
		}

		public Cell getCell() {
			return cell;
		}

		public void setCell(Cell cell) {
			this.cell = cell;
		}

		public int getRemainingMovement() {
			return remainingMovement;
		}

		public void setRemainingMovement(int remainingMovement) {
			this.remainingMovement = remainingMovement;
		}

	}
}
