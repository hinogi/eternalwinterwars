package com.github.scaronthesky.eternalwinterwars.view.entities.game;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.view.ShapeBuilder;


/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class HealthBar extends Entity {
	private float gWidth;
	private float gHeight;
	private VertexBufferObjectManager gVertexBufferObjectManager;
	private int gMaxHealth;
	private int gActualHealth;
	private Color gColorFront;
	private Color gColorBack;
	private final boolean gShowBackRect;
	private Rectangle gRectangleFront;
	private Rectangle gRectangleBack;

	/**
	 * Creates an instance of {@link HealthBar}
	 * 
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pVertexBufferObjectManager
	 * @param pMaxHealth
	 * @param pStartHealth
	 * @param pColorFront
	 * @param pColorBack
	 */
	public HealthBar(float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager,
			int pMaxHealth, int pStartHealth, Color pColorFront,
			Color pColorBack, boolean pShowBackRect) {
		super(pX, pY);
		this.gWidth = pWidth;
		this.gHeight = pHeight;
		this.gVertexBufferObjectManager = pVertexBufferObjectManager;
		this.gMaxHealth = pMaxHealth;
		this.gActualHealth = pStartHealth;
		this.gColorFront = pColorFront;
		this.gColorBack = pColorBack;
		this.gShowBackRect = pShowBackRect;
		this.initialize();
	}

	/**
	 * Initialize the {@link HealthBar}
	 */
	private void initialize() {
		this.gRectangleBack = ShapeBuilder.createRectangle(this.getX(),
				this.getY(), this.gWidth, this.gHeight,
				this.gVertexBufferObjectManager, this.gColorBack, 1);
		this.gRectangleFront = ShapeBuilder.createRectangle(this.getX(),
				this.getY(), this.calculateFrontWidth(), this.gHeight,
				this.gVertexBufferObjectManager, this.gColorFront, 1);
		if (this.gShowBackRect) {
			this.attachChild(this.gRectangleBack);
		}
		this.attachChild(this.gRectangleFront);
	}

	public void showFront() {
		if (!this.gRectangleFront.hasParent()) {
			this.attachChild(this.gRectangleFront);
		}
	}

	public void showBack() {
		if (!this.gRectangleBack.hasParent()) {
			this.attachChild(this.gRectangleBack);
		}
	}

	public void hideFront() {
		if (this.gRectangleFront.hasParent()) {
			this.detachChild(this.gRectangleFront);
		}
	}

	public void hideBack() {
		if (this.gRectangleBack.hasParent()) {
			this.detachChild(this.gRectangleBack);
		}
	}

	public void hide() {
		this.setVisible(false);
	}

	public void show() {
		this.setVisible(true);
		// showFront();
		// showBack();
	}

	private float calculateFrontWidth() {
		return this.gWidth * this.gActualHealth / this.gMaxHealth;
	}

	public float getFrontWidth() {
		return this.gRectangleFront.getWidth();
	}

	public float getBackWidth() {
		return this.gRectangleBack.getWidth();
	}

	public float getWidth() {
		return this.gWidth;
	}

	public void setWidth(float pWidth) {
		this.gWidth = pWidth;
	}

	public float getHeight() {
		return this.gHeight;
	}

	public void setHeight(float pHeight) {
		this.gHeight = pHeight;
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.gVertexBufferObjectManager;
	}

	public void setVertexBufferObjectManager(
			VertexBufferObjectManager pVertexBufferObjectManager) {
		this.gVertexBufferObjectManager = pVertexBufferObjectManager;
	}

	public int getMaxHealth() {
		return this.gMaxHealth;
	}

	public void setMaxHealth(int pMaxHealth) {
		this.gMaxHealth = pMaxHealth;
		this.gRectangleFront.setWidth(this.calculateFrontWidth());
	}

	public int getActualHealth() {
		return this.gActualHealth;
	}

	public void setActualHealth(int pActualHealth) {
		if (pActualHealth < 0) {
			this.gActualHealth = 0;
		} else {
			this.gActualHealth = pActualHealth;
		}
		this.gRectangleFront.setWidth(this.calculateFrontWidth());
	}

	public Color getColorFront() {
		return this.gColorFront;
	}

	public void setColorFront(Color pColorFront) {
		this.gColorFront = pColorFront;
	}

	public Color getColorBack() {
		return this.gColorBack;
	}

	public void setColorBack(Color pColorBack) {
		this.gColorBack = pColorBack;
	}
}