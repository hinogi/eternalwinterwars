package com.github.scaronthesky.eternalwinterwars.view.entities.board;

import java.util.LinkedList;
import java.util.List;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.constants.Constants;

public class FogOfWar extends Entity {
	private IController gController;
	private Rectangle[][] gRectangles;
	private List<Rectangle> gHiddenRectangles;

	public FogOfWar(IController pController) {
		this.gController = pController;
		this.initialize();
	}

	private void initialize() {
		this.gHiddenRectangles = new LinkedList<Rectangle>();
		int lRows = this.gController.getModel().getCellControl().getRowCount();
		int lColumns = this.gController.getModel().getCellControl()
				.getColumnCount();
		this.gRectangles = new Rectangle[lColumns][lRows];
		float lCellSideLength = this.gController.getView().getCellSideLength();
		for (int lRow = 0; lRow < lRows; lRow++) {
			for (int lColumn = 0; lColumn < lColumns; lColumn++) {
				Rectangle lFogRectangle = this.createFogRectangle(
						lCellSideLength * lColumn, lCellSideLength * lRow,
						lCellSideLength, lCellSideLength);
				this.attachChild(lFogRectangle);
				this.gRectangles[lColumn][lRow] = lFogRectangle;
			}
		}
	}

	private Rectangle createFogRectangle(float pX, float pY, float pWidth,
			float pHeight) {
		Rectangle lFogRectangle = new Rectangle(pX, pY, pWidth, pHeight,
				this.gController.getMainActivity()
						.getVertexBufferObjectManager());
		lFogRectangle.setColor(Constants.FOG_OF_WAR_COLOR);
		return lFogRectangle;
	}

	public void enableFog() {
		for (Rectangle lFogRectangle : this.gHiddenRectangles) {
			lFogRectangle.setVisible(true);
		}
	}

	public void disableFog(int pColumn, int pRow) {
		if (pColumn >= 0
				&& pRow >= 0
				&& pColumn < this.gController.getModel().getCellControl()
						.getColumnCount()
				&& pRow < this.gController.getModel().getCellControl()
						.getRowCount()) {
			Rectangle lFogRectangle = this.gRectangles[pColumn][pRow];
			lFogRectangle.setVisible(false);
			this.gHiddenRectangles.add(lFogRectangle);
		}
	}

	public void enableFog(int pColumn, int pRow) {
		Rectangle lFogRectangle = this.gRectangles[pColumn][pRow];
		if (!lFogRectangle.isVisible()) {
			lFogRectangle.setVisible(true);
			this.gHiddenRectangles.remove(lFogRectangle);
		}
	}

	public void lightenFog(int pColumn, int pRow) {
		Rectangle lFogRectangle = this.gRectangles[pColumn][pRow];
		if (lFogRectangle.isVisible()) {
			lFogRectangle.setAlpha(Constants.FOG_OF_WAR_ALPHA_LIGHTENED);
		}
	}
}
