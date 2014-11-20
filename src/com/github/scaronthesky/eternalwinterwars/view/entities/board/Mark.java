package com.github.scaronthesky.eternalwinterwars.view.entities.board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class Mark extends Entity implements ITouchArea {
	private final IController gController;
	private UnitEntity gSource;
	private List<Rectangle> gMarkRectangles;
	private final Board gBoard;
	private final List<float[]> gMarkedCells;
	private final Color gMarkColor;
	private final float gMaxAlpha;
	private final float gMinAlpha;
	private final float gBlinkDuration;

	/**
	 * Creates an instance of {@link Mark}
	 * 
	 * @param pController
	 *            {@link Model} reference
	 * @param pBoard
	 *            {@link Board} which will be the {@link Mark}'s parent
	 * @param pCellsToMark
	 *            List, which contains the coordinates {column;row} which have
	 *            to be marked
	 * @param pMarkColor
	 *            the mark's color
	 * @param pAlpha
	 *            the mark's alpha
	 */
	public Mark(IController pController, UnitEntity pSource, Board pBoard,
			List<float[]> pCellsToMark, Color pMarkColor, float pAlpha,
			float pMinAlpha, float pBlinkDuration) {
		this.gController = pController;
		this.gSource = pSource;
		this.gBoard = pBoard;
		this.gMarkedCells = pCellsToMark;
		this.gMarkColor = pMarkColor;
		this.gMaxAlpha = pAlpha;
		this.gMinAlpha = pMinAlpha;
		this.gBlinkDuration = pBlinkDuration;
		this.initialize();
	}

	/**
	 * Initializes the {@link Mark}
	 */
	private void initialize() {
		this.gMarkRectangles = new LinkedList<Rectangle>();
		for (float[] lCellCoordinates : this.gMarkedCells) {
			this.markCell(lCellCoordinates);
		}
		this.registerEntityModifier(this.getUpdateHandler());
	}

	/**
	 * Creates the UpdateHandler to create a blink effect
	 * 
	 * @return {@link IUpdateHandler} to register
	 */
	private DelayModifier getUpdateHandler() {
		return new DelayModifier(this.gBlinkDuration,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						for (Rectangle lMarkRectangle : Mark.this.gMarkRectangles) {
							lMarkRectangle
									.registerEntityModifier(new AlphaModifier(
											Mark.this.gBlinkDuration,
											lMarkRectangle.getAlpha(),
											lMarkRectangle.getAlpha() > Mark.this.gMinAlpha ? Mark.this.gMinAlpha
													: Mark.this.gMaxAlpha));
						}
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						Mark.this.registerEntityModifier(Mark.this
								.getUpdateHandler());
					}
				});
	}

	/**
	 * Attaches a {@link Rectangle} at the cells location to the {@link Mark}
	 * 
	 * @param pCellCoordinates
	 *            the cell's location {column;row}
	 */
	private void markCell(float[] pCellCoordinates) {
		float lX = pCellCoordinates[0];
		float lY = pCellCoordinates[1];
		float lPaddingX = this.gBoard.getCellWidth() * 0.05f;
		float lPaddingY = this.gBoard.getCellHeight() * 0.05f;
		Rectangle lMarkRectangle = new Rectangle(lX + lPaddingX,
				lY + lPaddingY, this.gBoard.getCellWidth() - 2 * lPaddingX,
				this.gBoard.getCellHeight() - 2 * lPaddingY, this.gController
						.getMainActivity().getVertexBufferObjectManager());
		lMarkRectangle.setColor(this.gMarkColor);
		lMarkRectangle.setAlpha(this.gMaxAlpha);
		this.gMarkRectangles.add(lMarkRectangle);
		this.attachChild(lMarkRectangle);
	}

	@Override
	public boolean contains(float pX, float pY) {
		for (Rectangle lRectangle : this.gMarkRectangles) {
			if (lRectangle.contains(pX, pY)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// XXX TEST
		if (pSceneTouchEvent.isActionDown()) {
			this.gSource.setClickable(false);
			this.gController
					.getView()
					.getSceneManager()
					.getGameScene()
					.move(this.gSource,
							this.getTarget(pTouchAreaLocalX, pTouchAreaLocalY));
		}
		return true;
	}

	private List<float[]> getTarget(float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// XXX TEST
		for (Rectangle lRectangle : this.gMarkRectangles) {
			if (lRectangle.contains(pTouchAreaLocalX, pTouchAreaLocalY)) {
				ArrayList<float[]> lArrayList = new ArrayList<float[]>();
				lArrayList.add(new float[] { lRectangle.getX(),
						lRectangle.getY() });
				return lArrayList;
			}
		}
		return null;
	}
}
