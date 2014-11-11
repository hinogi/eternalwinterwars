package com.github.scaronthesky.eternalwinterwars.view.managers.effects;

import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.scene.Scene;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public abstract class AEffect extends DelayModifier {

	private final IController gController;
	private final float gX;
	private final float gY;
	private final Scene gParentScene;

	public AEffect(IController pController, float pX, float pY,
			Scene pParentScene, float pDuration,
			IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pEntityModifierListener);
		this.gController = pController;
		this.gX = pX;
		this.gY = pY;
		this.gParentScene = pParentScene;
		this.setAutoUnregisterWhenFinished(true);
	}

	public IController getController() {
		return this.gController;
	}

	public float getX() {
		return this.gX;
	}

	public float getY() {
		return this.gY;
	}

	public Scene getParentScene() {
		return this.gParentScene;
	}
}
