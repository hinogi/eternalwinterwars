package com.github.scaronthesky.eternalwinterwars.view.entities.board;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class Board extends Entity {
	public static final float DEFAULT_CELL_BOUNDS_FACTOR = 0.1f;
	private final IController gController;
	private float gCellWidth;
	private float gCellHeight;

	/**
	 * Creates an instance of {@link Board}
	 * 
	 * @param pController
	 *            {@link IController} - reference
	 * @param pCellWidth
	 *            each cell's width
	 * @param pCellHeight
	 *            each cell's height
	 */
	public Board(IController pController, float pCellWidth, float pCellHeight) {
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
		this.initializeCellSprites(this.gController.getModel().getCellControl()
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
				.getResourceManager().getCellTextureRegions().get("bg_v3.png"),
				this.gController.getMainActivity()
						.getVertexBufferObjectManager());
		lBackgroundSprite.setHeight(this.gController.getModel()
				.getCellControl().getRowCount()
				* this.gCellWidth);
		lBackgroundSprite.setWidth(this.gController.getModel().getCellControl()
				.getColumnCount()
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
		float lCellSideLength = this.gController.getView().getCellSideLength();
		for (int lRow = 0; lRow < pCells.size(); lRow++) {
			for (int lColumn = 0; lColumn < pCells.get(0).size(); lColumn++) {
				Cell lCell = pCells.get(lRow).get(lColumn);
				float lX = lCellSideLength * lColumn;
				float lY = lCellSideLength * lRow;
				if (lCell.getSpriteKey() != null) {
					Sprite lCellSprite = new Sprite(lX, lY, this.gController
							.getView().getResourceManager()
							.getCellTextureRegions().get(lCell.getSpriteKey()),
							this.gController.getMainActivity()
									.getVertexBufferObjectManager());
					lCellSprite.setWidth(this.gCellWidth);
					lCellSprite.setHeight(this.gCellHeight);
					this.attachChild(lCellSprite);
				}
			}
		}
	}

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
		return this.gController.getModel().getCellControl().getColumnCount()
				* this.gCellWidth;
	}

	public float getHeight() {
		return this.gController.getModel().getCellControl().getRowCount()
				* this.gCellHeight;
	}
}
