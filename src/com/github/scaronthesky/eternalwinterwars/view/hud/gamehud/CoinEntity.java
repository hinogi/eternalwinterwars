package com.github.scaronthesky.eternalwinterwars.view.hud.gamehud;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.HorizontalAlign;

import com.github.scaronthesky.eternalwinterwars.controller.IController;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class CoinEntity extends Entity {
	private final IController gController;
	private Text gTextScore;
	private Sprite gSpriteCoin;

	public CoinEntity(IController pController) {
		this.gController = pController;
		this.attachChild(this.getTextScore());
		this.attachChild(this.getSpriteCoin());
	}

	public Text getTextScore() {
		if (this.gTextScore == null) {
			this.gTextScore = new Text(0, 0, this.gController.getView()
					.getResourceManager().getFontCoinEntity(), "000",
					this.gController.getMainActivity()
							.getVertexBufferObjectManager());
		}
		return this.gTextScore;
	}

	public void setText(String pText) {
		this.gTextScore.setText(pText);
		this.gTextScore.getTextOptions().setHorizontalAlign(
				HorizontalAlign.RIGHT);
	}

	public Sprite getSpriteCoin() {
		if (this.gSpriteCoin == null) {
			this.gSpriteCoin = new Sprite(this.getTextScore().getWidth(), 0,
					this.getTextScore().getHeight(), this.getTextScore()
							.getHeight(), this.gController.getView()
							.getResourceManager().getTextureRegionCoinEntity(),
					this.gController.getMainActivity()
							.getVertexBufferObjectManager());
		}
		return this.gSpriteCoin;
	}

	public float getWidth() {
		return this.gTextScore.getWidth() + this.gSpriteCoin.getWidth();
	}

	public float getHeight() {
		return this.gSpriteCoin.getHeight();
	}
}
