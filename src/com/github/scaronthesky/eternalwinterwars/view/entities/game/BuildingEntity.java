package com.github.scaronthesky.eternalwinterwars.view.entities.game;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationProperties;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationSuddenAppearEffect;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class BuildingEntity extends AGameBaseEntity implements Cloneable {
	private Sprite gSingleTextureSprite;

	/**
	 * Creates an instance of {@link BuildingEntity}
	 * 
	 * @param pController
	 * @param pHealthBarWidth
	 * @param pHealthBarHeight
	 * @param pMaxHealth
	 * @param pStartHealth
	 * @param pColorFront
	 * @param pColorBack
	 * @param pShowBackRect
	 */
	public BuildingEntity(IController pController, Scene pParentScene,
			float pHealthBarWidth, float pHealthBarHeight, int pMaxHealth,
			int pStartHealth, Color pColorFront, Color pColorBack,
			boolean pShowBackRect, ITextureRegion pTextureRegion,
			float pSpriteHeight) {
		super(pController, pParentScene, pHealthBarWidth, pHealthBarHeight,
				pMaxHealth, pStartHealth, pColorFront, pColorBack,
				pShowBackRect);
		this.gSingleTextureSprite = new Sprite(0, 0, this.getHealthBar()
				.getWidth(), pSpriteHeight, pTextureRegion, this
				.getController().getMainActivity()
				.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					BuildingEntity.this
							.getController()
							.getView()
							.getSceneManager()
							.getGameScene()
							.attack(Controller.testMarksman,
									BuildingEntity.this, 5, false);
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		this.registerTouchArea();
		this.attachChild(this.gSingleTextureSprite);
	}

	@Override
	public void sufferFromAttack() {
		AnimationProperties lAnimationProperties = new AnimationProperties(this
				.getController().getView().getResourceManager()
				.getTiledTextureRegionBlood(), new long[] { 150, 150, 150, 150,
				150, 150 }, 0, 5, false, false, false);
		this.getParentScene().registerEntityModifier(
				new AnimationSuddenAppearEffect(this.getController(), this
						.getX(),
						this.getY() + this.gSingleTextureSprite.getY(), this
								.getParentScene(), SUFFER_DURATION,
						this.gSingleTextureSprite.getWidth(),
						this.gSingleTextureSprite.getHeight(),
						lAnimationProperties, this.getController()
								.getMainActivity()
								.getVertexBufferObjectManager()));
	}

	@Override
	public float getWidth() {
		return this.getHealthBar().getWidth();
	}

	@Override
	public float getHeight() {
		return this.gSingleTextureSprite.getHeight();
	}

	public BuildingEntity copy() {
		try {
			return (BuildingEntity) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void registerTouchArea() {
		this.getParentScene().registerTouchArea(this.gSingleTextureSprite);
	}
}
