package com.github.scaronthesky.eternalwinterwars.model.controls;

import com.github.scaronthesky.eternalwinterwars.view.BoardString;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Cell;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Cell.CellType;

/**
 * @author Manu
 * 
 */
public class CellControl {
	private Cell[][] cells;
	private BoardString boardString;

	/**
	 * Creates an instance of {@link CellManager}
	 * 
	 * @param columns
	 *            column count
	 * @param row
	 *            count
	 */
	public CellControl(BoardString boardString) {
		this.boardString = boardString;
		this.cells = createCellArray(convertStringToCellTypeArray(boardString
				.getBoardString()));
	}

	/**
	 * Converts a String to an array of CellTypes
	 * 
	 * @param baseString
	 *            String-pattern for creating an array of cells
	 * @return {@link CellType} - array
	 */
	private CellType[][] convertStringToCellTypeArray(String baseString) {
		CellType[][] cellTypes = new CellType[boardString.getColumns()][boardString
				.getRows()];
		for (int row = 0; row < boardString.getRows(); row++) {
			for (int column = 0; column < boardString.getColumns(); column++) {
				cellTypes[column][row] = convertCharToCellType(baseString
						.charAt(row * boardString.getColumns() + column));
			}
		}
		return cellTypes;
	}

	/**
	 * Converts a char to a {@link CellType}
	 * 
	 * @param character
	 *            base-char
	 * @return {@link CellType}
	 */
	private CellType convertCharToCellType(char character) {
		switch (character) {
		case 'P':
			return CellType.PLAIN;
		case 'F':
			return CellType.FOREST;
		case 'M':
			return CellType.MOUNTAIN;
		case 'R':
			return CellType.RIVER;
		default:
			return null;
		}
	}

	private Cell[][] createCellArray(CellType[][] cellTypes) {
		Cell[][] cells = new Cell[boardString.getColumns()][boardString
				.getRows()];
		for (int column = 0; column < boardString.getColumns(); column++) {
			for (int row = 0; row < boardString.getRows(); row++) {
				cells[column][row] = new Cell(cellTypes[column][row]);
			}
		}
		return cells;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public BoardString getBoardString() {
		return boardString;
	}
}
