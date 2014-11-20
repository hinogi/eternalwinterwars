package com.github.scaronthesky.eternalwinterwars.model.editorcontrol;

import java.util.ArrayList;
import java.util.UUID;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.controller.util.CellBuilder;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;
import com.github.scaronthesky.eternalwinterwars.model.cells.Plain;

public class EditorControl {
	public static final String DEFAULT_BACKGROUND_KEY = "bg_v3.png";
	public static final int DEFAULT_COLUMNS = 40;
	public static final int DEFAULT_ROWS = 40;
	private int columns;
	private int rows;
	private String backgroundKey;
	private IController gController;
	private ArrayList<ArrayList<Cell>> gCells;

	/**
	 * Creates an instance of {@link EditorControl}
	 */
	public EditorControl(IController pController) {
		this.columns = DEFAULT_COLUMNS;
		this.rows = DEFAULT_ROWS;
		this.backgroundKey = DEFAULT_BACKGROUND_KEY;
		this.gController = pController;
		initCells();
	}

	private void initCells() {
		gCells = new ArrayList<ArrayList<Cell>>();
		for (int row = 0; row < rows; row++) {
			ArrayList<Cell> lRowList = new ArrayList<Cell>();
			for (int column = 0; column < columns; column++) {
				lRowList.add(CellBuilder.buildDefaultPlain());
			}
			gCells.add(lRowList);
		}
	}

	public void setCell(Cell pCell, int pColumn, int pRow) {
		gCells.get(pRow).set(pColumn, pCell);
	}

	public void setBackground(String key) {
		backgroundKey = key;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		initCells();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		initCells();
	}

	public String getBackgroundKey() {
		return backgroundKey;
	}

	public CellControl getCellControl() {
		return new CellControl(gCells);
	}
}
