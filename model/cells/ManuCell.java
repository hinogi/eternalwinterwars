package com.github.scaronthesky.eternalwinterwars.model.cells;

/**
 * Cell Shape - Is going to be extended soon
 * 
 * @author Manu
 * 
 */
public class ManuCell {
	public enum CellType {
		PLAIN, FOREST, MOUNTAIN, RIVER
	}

	private CellType cellType;

	/**
	 * Creates an instance of {@link ManuCell}
	 * 
	 * @param cellType
	 *            the cell's type
	 */
	public ManuCell(CellType cellType) {
		this.cellType = cellType;
	}

	/**
	 * @return the value, which is subtracted from the amount of steps a unit
	 *         can do that turn when entering this cell
	 */
	public int getMovementSubtractionValue() {
		switch (cellType) {
		case PLAIN:
			return 1;
		case FOREST:
			return 2;
		case MOUNTAIN:
			return 3;
		case RIVER:
			return 1;
		default:
			return -1;
		}
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
}
