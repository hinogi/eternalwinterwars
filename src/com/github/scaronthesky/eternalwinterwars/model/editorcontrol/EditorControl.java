package com.github.scaronthesky.eternalwinterwars.model.editorcontrol;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.BoardString;

public class EditorControl {
	public static final String DEFAULT_BACKGROUND_KEY = "0";
	public static final int DEFAULT_COLUMNS = 40;
	public static final int DEFAULT_ROWS = 40;
	private int columns;
	private int rows;
	private String backgroundKey;
	private String[][] spriteKeys;
	private IController gController;

	/**
	 * Creates an instance of {@link EditorControl}
	 */
	public EditorControl(IController pController) {
		this.columns = DEFAULT_COLUMNS;
		this.rows = DEFAULT_ROWS;
		this.backgroundKey = DEFAULT_BACKGROUND_KEY;
		this.gController = pController;
		initKeys();
	}

	/**
	 * Initializes the key-array
	 */
	private void initKeys() {
		spriteKeys = new String[columns][rows];
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
				spriteKeys[column][row] = backgroundKey;
			}
		}
	}

	/**
	 * @return an instance of {@link BoardMap}
	 */
	public BoardString getBoardString() {
		return new BoardString(gController, generateRawString());
	}

	/**
	 * @return a generated String-shape used as {@link BoardMap} constructor
	 *         parameter
	 */
	private String generateRawString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('{').append(backgroundKey).append('}');
		stringBuilder.append('[').append(columns).append('/').append(rows)
				.append(']');
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				stringBuilder.append(spriteKeys[column][row]);
			}
		}
		return stringBuilder.toString();
	}

	public void setBackground(String key) {
		backgroundKey = key;
	}

	public void setKey(String key, int column, int row) {
		spriteKeys[column][row] = key;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		initKeys();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		initKeys();
	}

	public String getBackgroundKey() {
		return backgroundKey;
	}
}
