package com.github.scaronthesky.eternalwinterwars.view.entities.board;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.AGameBaseEntity;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class Board extends Entity {
	public static final float DEFAULT_CELL_BOUNDS_FACTOR = 0.05f;
	private final IController gController;
	private List<AGameBaseEntity> gGameBaseEntities;
	private float gCellWidth;
	private float gCellHeight;

	/**
	 * Creates an instance of {@link Board}
	 * 
	 * @param pColumns
	 *            the board's column count
	 * @param pRows
	 *            the board's row count
	 * @param pCellWidth
	 *            each cell's width
	 * @param pCellHeight
	 *            each cell's height
	 * @param cellControl
	 *            .getCells() {@link Cell} - array
	 */
	public Board(IController pController, int pColumns, int pRows,
			float pCellWidth, float pCellHeight) {
		this.gController = pController;
		this.gCellWidth = pCellWidth;
		this.gCellHeight = pCellHeight;
		this.initialize();
	}

	/**
	 * Initializes the board
	 */
	private void initialize() {
		this.initializeBackgroundSprite();
		this.initializeCellSprites(gController.getModel().getCellControl()
				.getCells());
		// TODO
		this.gController.getView().limitCameraBoundsToBoard(
				this.gController.getMainActivity(), this);
	}

	/**
	 * Resets the board using another base-array
	 * 
	 * @param pCells
	 *            {@link Cell} - array
	 */
	public void reset(Cell[][] pCells) {
		this.reset(pCells);
	}

	/**
	 * Puts a Sprite on the first Layer to simulate a background
	 */
	private void initializeBackgroundSprite() {
		Sprite lBackgroundSprite = new Sprite(0, 0, this.gController.getView()
				.getResourceManager().getTextureRegions().get(2),
				this.gController.getMainActivity()
						.getVertexBufferObjectManager());
		lBackgroundSprite.setWidth(this.gController.getModel().getCellControl()
				.getCells().size()
				* this.gCellWidth);
		lBackgroundSprite.setHeight(this.gController.getModel()
				.getCellControl().getCells().get(0).size()
				* this.gCellHeight);
		this.attachChild(lBackgroundSprite);
	}

	/**
	 * Draws Sprites if a Cell has another {@link CellType} than CellType.PLAIN
	 * 
	 * @param pCells
	 *            {@link Cell} - array
	 */
	private void initializeCellSprites(ArrayList<ArrayList<Cell>> pCells) {
		for (int lColumn = 0; lColumn < pCells.size(); lColumn++) {
			for (int lRow = 0; lRow < pCells.get(0).size(); lRow++) {
				Sprite lCellSprite = pCells.get(lColumn).get(lRow).getSprite();
				lCellSprite.setWidth(this.gCellWidth);
				lCellSprite.setHeight(this.gCellHeight);
				this.attachChild(lCellSprite);
			}
		}
	}

	// /**
	// * If cell has another CellType than CellType.PLAIN, a Sprite is attached
	// at
	// * the Cells location
	// *
	// * @param pColumn
	// * the cell's column
	// * @param pRow
	// * the cell's row
	// * @param pCell
	// */
	// private void createCellSpriteIfNeccessary(int pColumn, int pRow, Cell
	// pCell) {
	// int lKeyLocation = pRow
	// * this.gController.getModel().getCellControl().getBoardString()
	// .getColumns() + pColumn;
	// String lImageKey = this.gController.getModel().getCellControl()
	// .getBoardString().getBoardString()
	// .substring(lKeyLocation, lKeyLocation + 1);
	// ITextureRegion lTextureRegion = this.gController.getView()
	// .getResourceManager().getTextureRegions().get(lImageKey);
	// if (!lImageKey.startsWith("bg") && lTextureRegion != null) {
	// Sprite lCellSprite = new Sprite(pColumn * this.gCellWidth, pRow
	// * this.gCellHeight, lTextureRegion, this.gController
	// .getMainActivity().getVertexBufferObjectManager());
	// lCellSprite.setWidth(this.gCellWidth);
	// lCellSprite.setHeight(this.gCellHeight);
	// this.attachChild(lCellSprite);
	// }
	// }

	/**
	 * Calculates a cells start-coordinates
	 * 
	 * @param pColumn
	 *            target cell's column;
	 * @param pRow
	 *            target cell's row;
	 * @return
	 */
	public float[] getCellStartCoordinates(int pColumn, int pRow) {
		return new float[] { pColumn * this.gCellWidth, pRow * this.gCellHeight };
	}

	/**
	 * Finds a {@link Cell} per click coordinates
	 * 
	 * @param pX
	 *            click x location on the {@link Board}
	 * @param pY
	 *            click y location on the {@link Board}
	 * @return clicked {@link Cell}
	 */
	public int[] getClickedColumnAndRow(float pX, float pY) {
		for (int lColumn = 0; lColumn < this.gController.getModel()
				.getCellControl().getCells().size(); lColumn++) {
			for (int lRow = 0; lRow < this.gController.getModel()
					.getCellControl().getCells().get(0).size(); lRow++) {
				if (pX >= lColumn * this.gCellWidth
						&& pX < this.gCellWidth + lColumn * this.gCellWidth
						&& pY >= lRow * this.gCellHeight
						&& pY < this.gCellHeight + lRow * this.gCellHeight) {
					return new int[] { lColumn, lRow };
				}
			}
		}
		return null;
	}

	public float getCellWidth() {
		return this.gCellWidth;
	}

	public void setCellWidth(float pCellWidth) {
		this.gCellWidth = pCellWidth;
	}

	public float getCellHeight() {
		return this.gCellHeight;
	}

	public void setCellHeight(float pCellHeight) {
		this.gCellHeight = pCellHeight;
	}

	public float getWidth() {
		return this.gController.getModel().getCellControl().getCells().size()
				* this.gCellWidth;
	}

	public float getHeight() {
		return this.gController.getModel().getCellControl().getCells().get(0)
				.size()
				* this.gCellHeight;
	}
}
