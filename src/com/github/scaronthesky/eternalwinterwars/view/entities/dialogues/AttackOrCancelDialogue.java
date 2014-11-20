package com.github.scaronthesky.eternalwinterwars.view.entities.dialogues;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.hud.gamehud.ImageButtonSprite;

/**
 * @author Manuel Seiche [aka Hakenadu]
 * @since 16.11.2014
 * 
 */
public class AttackOrCancelDialogue extends Entity {
	private IController gController;
	private ImageButtonSprite gButtonAttack;
	private ImageButtonSprite gButtonCancel;

	/**
	 * Instatiates the {@link AttackOrCancelDialogue}
	 * 
	 * @param pController
	 *            {@link IController} - Reference
	 * @param pParentScene
	 *            ParentScene used for TouchArea-Registration
	 * @param pX
	 *            dialogue-x
	 * @param pY
	 *            dialogue-y
	 * @param pButtonWidth
	 *            each button's width
	 * @param pButtonHeight
	 *            each button's height
	 */
	public AttackOrCancelDialogue(IController pController, Scene pParentScene,
			final UnitEntity pUnitEntity, float pButtonWidth,
			float pButtonHeight) {
		this.gController = pController;
		// Instantiate the attack-Button
		this.gButtonAttack = new ImageButtonSprite(0, 0, pButtonWidth,
				pButtonHeight, this.gController.getView().getResourceManager()
						.getTiledTextureRegionButton(), pController.getView()
						.getResourceManager().getTextureRegionAttack(),
				pParentScene, pController.getMainActivity()
						.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				boolean iReturnValue = super.onAreaTouched(pSceneTouchEvent,
						pTouchAreaLocalX, pTouchAreaLocalY);
				AttackOrCancelDialogue.this.gController
						.handleAttackButtonClicked(pUnitEntity);
				AttackOrCancelDialogue.this.detachSelf();
				return iReturnValue;
			}
		};
		this.attachChild(this.gButtonAttack);
		// Instantiate the cancel-Button
		this.gButtonCancel = new ImageButtonSprite(0, pButtonHeight,
				pButtonWidth, pButtonHeight, this.gController.getView()
						.getResourceManager().getTiledTextureRegionButton(),
				pController.getView().getResourceManager()
						.getTextureRegionCancel(), pParentScene, pController
						.getMainActivity().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				boolean iReturnValue = super.onAreaTouched(pSceneTouchEvent,
						pTouchAreaLocalX, pTouchAreaLocalY);
				AttackOrCancelDialogue.this.gController
						.handleAttackButtonClicked(pUnitEntity);
				AttackOrCancelDialogue.this.detachSelf();
				return iReturnValue;
			}
		};
		this.attachChild(this.gButtonCancel);
	}

	public float getWidth() {
		return this.gButtonAttack.getWidth();
	}

	public float getHeight() {
		return this.gButtonAttack.getHeight() + this.gButtonCancel.getHeight();
	}
}
