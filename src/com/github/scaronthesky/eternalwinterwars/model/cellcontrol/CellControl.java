package com.github.scaronthesky.eternalwinterwars.model.cellcontrol;

import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;

public class CellControl {

	private Cell[][] cells;

	public CellControl(Cell[][] cells) {
		super();
		this.cells = cells;
	}

	/**
	 * Returns all the cells which the unit on this cell can reach.
	 * 
	 * @return the cells
	 */
	public Cell[][] getReachableCells(final Cell cell) {
		// TODO: Implement
		return cells;
	}

	/**
	 * Returns all the cells which the unit on this cell can see.
	 * 
	 * @return the cells
	 */
	public Cell[][] getSeeableCells(final Cell cell) {
		// TODO: Implement
		return cells;
	}

	/**
	 * Returns all the cells which the unit on this cell can attack.
	 * 
	 * @return the cells
	 */
	public Cell[][] getAttackableCells(final Cell cell) {
		// TODO: Implement
		return cells;
	}

	/**
	 * @return the cells
	 */
	public Cell[][] getCells() {
		// TODO: Implement
		return cells;
	}

}
