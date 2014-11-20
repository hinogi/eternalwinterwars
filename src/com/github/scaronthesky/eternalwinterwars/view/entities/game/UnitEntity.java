package com.github.scaronthesky.eternalwinterwars.view.entities.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.controller.constants.Constants;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.multitexture.MultiTextureAnimatedSprite;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationProperties;
import com.github.scaronthesky.eternalwinterwars.view.util.AnimationExecutor;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class UnitEntity extends AGameBaseEntity {
	private MultiTextureAnimatedSprite gMultiTextureAnimatedSprite;
	private Map<String, AnimationProperties> gAnimationProperties;
	private boolean gMarked;
	private Sprite gRangeSprite;

	public UnitEntity(IController pController, Scene pParentScene,
			float pHealthBarWidth, float pHealthBarHeight, int pMaxHealth,
			int pStartHealth, Color pColorFront, Color pColorBack,
			boolean pShowBackRect,
			Map<String, AnimationProperties> pAnimationProperties,
			String pStartTileId, float pSpriteHeight) {
		super(pController, pParentScene, pHealthBarWidth, pHealthBarHeight,
				pMaxHealth, pStartHealth, pColorFront, pColorBack,
				pShowBackRect);
		this.gAnimationProperties = pAnimationProperties;
		this.gMultiTextureAnimatedSprite = new MultiTextureAnimatedSprite(0, 0,
				pHealthBarWidth, pSpriteHeight, pAnimationProperties,
				pStartTileId, this.getController().getMainActivity()
						.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// XXX TEST!!!
				if (UnitEntity.this.isClickable()
						&& pSceneTouchEvent.isActionDown()) {
					// UnitEntity.this.getController().getView().getSceneManager()
					// .getGameScene()
					// .showAttackOrCancelDialogue(UnitEntity.this);
					if (!UnitEntity.this.gMarked) {
						List<float[]> lStartCoordinates = new ArrayList<float[]>();
						Controller lController = (Controller) UnitEntity.this
								.getController();
						int lColumn = lController
								.getLogicalCoordinate(pSceneTouchEvent.getX());
						int lRow = lController
								.getLogicalCoordinate(pSceneTouchEvent.getY());
						lStartCoordinates.add(new float[] {
								lController.getAbsoluteCoordinate(lColumn),
								lController.getAbsoluteCoordinate(lRow) });
						lStartCoordinates.add(new float[] {
								lController.getAbsoluteCoordinate(lColumn + 1),
								lController.getAbsoluteCoordinate(lRow) });
						lStartCoordinates.add(new float[] {
								lController.getAbsoluteCoordinate(lColumn),
								lController.getAbsoluteCoordinate(lRow + 1) });
						lStartCoordinates.add(new float[] {
								lController.getAbsoluteCoordinate(lColumn - 1),
								lController.getAbsoluteCoordinate(lRow) });
						lStartCoordinates.add(new float[] {
								lController.getAbsoluteCoordinate(lColumn),
								lController.getAbsoluteCoordinate(lRow - 1) });
						UnitEntity.this
								.getController()
								.getView()
								.getSceneManager()
								.getGameScene()
								.markCells(
										UnitEntity.this,
										lStartCoordinates,
										Constants.PLAYER_COLORS[lController
												.getBaseGameEntityMapper()
												.getPlayerIndex(UnitEntity.this)]);
					}
					UnitEntity.this.gMarked = !UnitEntity.this.gMarked;
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		this.registerTouchArea();
		this.attachChild(this.gMultiTextureAnimatedSprite);
		// AnimationExecutor.execute(this.gMultiTextureAnimatedSprite,
		// pAnimationProperties.get(pStartTileId));
	}

	public MultiTextureAnimatedSprite getMultiTextureAnimatedSprite() {
		return this.gMultiTextureAnimatedSprite;
	}

	public AnimationProperties getAnimationProperties(String pAnimationKey) {
		return this.gAnimationProperties.get(pAnimationKey);
	}

	public boolean hasRangeSprite() {
		return this.gRangeSprite != null;
	}

	public void setRangeSprite(Sprite pRangeSprite) {
		this.gRangeSprite = pRangeSprite;
	}

	public Sprite getRangeSprite() {
		return this.gRangeSprite;
	}

	public void animate(String pAnimationKey) {
		if (this.gAnimationProperties.containsKey(pAnimationKey)) {
			AnimationExecutor.execute(this.gMultiTextureAnimatedSprite,
					this.gAnimationProperties.get(pAnimationKey));
		} else if (this.gMultiTextureAnimatedSprite.isAnimationRunning()) {
			// IDLE missing
			this.gMultiTextureAnimatedSprite.stopAnimation();
		}
	}

	@Override
	public void sufferFromAttack() {

	}

	@Override
	public float getWidth() {
		return this.getHealthBar().getWidth();
	}

	@Override
	public float getHeight() {
		return this.gMultiTextureAnimatedSprite.getHeight();
	}

	public void setMarked(boolean pMarked) {
		this.gMarked = pMarked;
	}

	public void registerTouchArea() {
		this.getParentScene().registerTouchArea(
				this.gMultiTextureAnimatedSprite);
	}
}
