package com.github.scaronthesky.eternalwinterwars.view;

import java.util.ArrayList;
import java.util.UUID;

import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;
import com.github.scaronthesky.eternalwinterwars.model.cells.Forest;
import com.github.scaronthesky.eternalwinterwars.model.cells.Mountain;
import com.github.scaronthesky.eternalwinterwars.model.cells.Plain;
import com.github.scaronthesky.eternalwinterwars.model.cells.River;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class BoardString implements Comparable<BoardString> {
	private final String gBackgroundTypeString;
	private final int gColumns;
	private final int gRows;
	private final String gBoardString;
	private IController gController;

	// private final String[][][]gBoardInformation;

	/**
	 * @param pRaw
	 *            string from {@link SharedPreferencesManager}
	 */
	public BoardString(IController pController, String pRaw) {
		this.gBackgroundTypeString = pRaw.substring(pRaw.indexOf('{') + 1,
				pRaw.indexOf('}'));
		this.gColumns = Integer.valueOf(pRaw.substring(pRaw.indexOf('[') + 1,
				pRaw.indexOf('/')));
		this.gRows = Integer.valueOf(pRaw.substring(pRaw.indexOf('/') + 1,
				pRaw.indexOf(']')));
		this.gBoardString = pRaw.substring(pRaw.indexOf(']') + 1);
		this.gController = pController;
	}

	public ArrayList<ArrayList<Cell>> getCells() {
		ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
		for (int lRow = 0; lRow < gRows; lRow++) {
			ArrayList<Cell> internalCellArray = new ArrayList<Cell>();
			for (int lColumn = 0; lColumn < gColumns; lColumn++) {
				Cell cel = convertCharToCellType(lColumn, lRow,
						gBoardString.charAt(lRow * gColumns + lColumn));
				internalCellArray.add(cel);
			}
			cells.add(internalCellArray);
		}
		return cells;
	}

	/**
	 * Converts a char to a {@link CellType}
	 * 
	 * @param pColumn
	 * @param pRow
	 * @param pCharacter
	 *            base-char
	 * @return {@link CellType}
	 */
	private Cell convertCharToCellType(int pColumn, int pRow, char pCharacter) {
		char cellChar = getCharFromImageKey(pCharacter);
		String imageKey = String.valueOf(pCharacter);
		switch (cellChar) {
		case 'P':
		case 'B':
			return new Plain(UUID.randomUUID(), null, null, null);
		case 'F':
			return new Forest(UUID.randomUUID(), null, null, new Sprite(pColumn
					* gController.getView().getCellSideLength(), pRow
					* gController.getView().getCellSideLength(), gController
					.getView().getResourceManager().getTextureRegions()
					.get(imageKey), gController.getMainActivity()
					.getVertexBufferObjectManager()));
		case 'M':
			return new Mountain(UUID.randomUUID(), null, null, new Sprite(
					pColumn * gController.getView().getCellSideLength(), pRow
							* gController.getView().getCellSideLength(),
					gController.getView().getResourceManager()
							.getTextureRegions().get(imageKey), gController
							.getMainActivity().getVertexBufferObjectManager()));
		case 'R':
			return new River(UUID.randomUUID(), null, null, new Sprite(pColumn
					* gController.getView().getCellSideLength(), pRow
					* gController.getView().getCellSideLength(), gController
					.getView().getResourceManager().getTextureRegions()
					.get(imageKey), gController.getMainActivity()
					.getVertexBufferObjectManager()));
		default:
			return null;
		}
	}

	public char getCharFromImageKey(char pChar) {
		String key = String.valueOf(pChar);
		String imageName = gController.getView().getResourceManager()
				.getKeyMap().get(key);
		if (imageName != null) {
			if (imageName.startsWith("mountain")) {
				return 'M';
			} else if (imageName.startsWith("tree")) {
				return 'T';
			} else if (imageName.startsWith("river")) {
				return 'R';
			}
		}
		return 'B';

	}

	public String getBackgroundTypeString() {
		return this.gBackgroundTypeString;
	}

	public int getColumns() {
		return this.gColumns;
	}

	public int getRows() {
		return this.gRows;
	}

	public String getBoardString() {
		return this.gBoardString;
	}

	@Override
	public String toString() {
		return "[" + this.gColumns + "/" + this.gRows + "]" + this.gBoardString;
	}

	@Override
	public int compareTo(BoardString pAnotherBoardString) {
		return this.getColumns() * this.getRows()
				- pAnotherBoardString.getColumns()
				* pAnotherBoardString.getRows();
	}
}
