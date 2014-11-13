package com.github.scaronthesky.eternalwinterwars.view.hud;

import org.andengine.entity.scene.menu.item.AnimatedSpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.scaronthesky.eternalwinterwars.view.entities.IMeasureableEntity;


/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class AnimatedButtonSpriteMenuItem extends AnimatedSpriteMenuItem
		implements IMeasureableEntity {
	private float gImageX = 0;
	private float gImageY = 0;
	private Sprite gSpriteButtonImage;

	public AnimatedButtonSpriteMenuItem(int pId,
			ITiledTextureRegion pTiledTextureRegion,
			ITextureRegion pTextureRegionIcon,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pId, pTiledTextureRegion, pVertexBufferObjectManager);
		this.gSpriteButtonImage = new Sprite(0, 0, pTextureRegionIcon,
				pVertexBufferObjectManager);
		this.initImageBounds();
		this.attachChild(this.gSpriteButtonImage);
	}

	public void initImageBounds() {
		// Resize buttonImage
		this.gSpriteButtonImage.setHeight(this.getHeight() * 0.9f);
		this.gSpriteButtonImage.setWidth(this.gSpriteButtonImage.getHeight());
		// Position buttonImage
		this.gImageX = (this.getWidth() - this.gSpriteButtonImage.getWidth()) / 2;
		this.gImageY = (this.getHeight() - this.gSpriteButtonImage.getHeight()) / 2;
		this.gSpriteButtonImage.setPosition(this.gImageX, this.gImageY);
	}

	@Override
	public void setWidth(float pWidth) {
		super.setWidth(pWidth);
		this.initImageBounds();
	}

	@Override
	public void setHeight(float pHeight) {
		super.setHeight(pHeight);
		this.initImageBounds();
	}

	@Override
	public void onSelected() {
		this.setCurrentTileIndex(1);
		if (this.gSpriteButtonImage != null) {
			this.gSpriteButtonImage.setX(this.gImageX + 5);
			this.gSpriteButtonImage.setY(this.gImageY + 5);
		}
		super.onSelected();
	}

	@Override
	public void onUnselected() {
		this.setCurrentTileIndex(0);
		if (this.gSpriteButtonImage != null) {
			this.gSpriteButtonImage.setX(this.gImageX);
			this.gSpriteButtonImage.setY(this.gImageY);
		}
		super.onUnselected();
	}

	public Sprite getButtonImage() {
		return this.gSpriteButtonImage;
	}

	public void setButtonImage(Sprite pSpriteButtonImage) {
		this.gSpriteButtonImage = pSpriteButtonImage;
	}

}
