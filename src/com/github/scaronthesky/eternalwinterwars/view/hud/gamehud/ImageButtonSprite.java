package com.github.scaronthesky.eternalwinterwars.view.hud.gamehud;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.scaronthesky.eternalwinterwars.view.entities.IMeasureableEntity;


/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class ImageButtonSprite extends ButtonSprite implements
		IMeasureableEntity {
	private final float gImageX;
	private final float gImageY;
	private final Sprite gButtonImage;
	private boolean gSelected;

	/**
	 * Creates an instance of {@link ImageButtonSprite}
	 * 
	 * @param pX
	 *            local x translation
	 * @param pY
	 *            local y translation
	 * @param width
	 *            ImageButtonSprite's width
	 * @param height
	 *            ImageButtonSprite's height
	 * @param pTiledTextureRegion
	 *            three tiled region used for: pressed, not pressed, disabled
	 * @param pTextureRegion
	 *            button icon
	 * @param pParentScene
	 *            parent scene
	 * @param pVertexBufferObjectManager
	 *            {@link VertexBufferObjectManager} used to initialize the
	 *            sprites
	 */
	public ImageButtonSprite(float pX, float pY, float width, float height,
			ITiledTextureRegion pTiledTextureRegion,
			ITextureRegion pTextureRegion, Scene pParentScene,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.setWidth(width);
		this.setHeight(height);
		this.gButtonImage = new Sprite(0, 0, pTextureRegion,
				pVertexBufferObjectManager);
		// Initialize bounds to fit every device
		float lResizeFactor = this.getHeight() * 0.5f
				/ this.gButtonImage.getHeight();
		this.gButtonImage
				.setWidth(this.gButtonImage.getWidth() * lResizeFactor);
		this.gButtonImage.setHeight(this.gButtonImage.getHeight()
				* lResizeFactor);
		this.gImageX = (this.getWidth() - this.gButtonImage.getWidth()) / 2;
		this.gImageY = (this.getHeight() - this.gButtonImage.getHeight()) / 2;
		this.gButtonImage.setPosition(this.gImageX, this.gImageY);
		this.attachChild(this.gButtonImage);
		pParentScene.registerTouchArea(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.andengine.entity.sprite.ButtonSprite#onAreaTouched(org.andengine.
	 * input.touch.TouchEvent, float, float)
	 */
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			this.onSelected();
		} else if (pSceneTouchEvent.isActionUp()) {
			this.onUnselected();
		}
		return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
				pTouchAreaLocalY);
	}

	/**
	 * Visual reaction on selection
	 */
	public void onSelected() {
		this.gSelected = true;
		this.setCurrentTileIndex(1);
		if (this.gButtonImage != null) {
			this.gButtonImage.setX(this.gImageX + 5);
			this.gButtonImage.setY(this.gImageY + 5);
		}
	}

	/**
	 * Visual reaction on unselect
	 */
	public void onUnselected() {
		this.gSelected = false;
		this.setCurrentTileIndex(0);
		if (this.gButtonImage != null) {
			this.gButtonImage.setX(this.gImageX);
			this.gButtonImage.setY(this.gImageY);
		}
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return this.gSelected;
	}

	/**
	 * @param pSelected
	 *            the selected to set
	 */
	public void setSelected(boolean pSelected) {
		this.gSelected = pSelected;
	}
}