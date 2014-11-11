package com.github.scaronthesky.eternalwinterwars.view.entities.game;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.entities.IMeasureableEntity;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public abstract class AGameBaseEntity extends Entity implements
		IMeasureableEntity {
	public static final float SUFFER_DURATION = 1f;
	public IController gController;
	private final Scene gParentScene;
	private final HealthBar gHealthBar;

	public AGameBaseEntity(IController pController, Scene pParentScene,
			float pHealthBarWidth, float pHealthBarHeight, int pMaxHealth,
			int pStartHealth, Color pColorFront, Color pColorBack,
			boolean pShowBackRect) {
		this.gController = pController;
		this.gParentScene = pParentScene;
		this.gHealthBar = new HealthBar(0, 0, pHealthBarWidth,
				pHealthBarHeight, this.gController.getMainActivity()
						.getVertexBufferObjectManager(), pMaxHealth,
				pStartHealth, pColorFront, pColorBack, pShowBackRect);
		this.gHealthBar.setY(-this.gHealthBar.getHeight());
		this.attachChild(this.gHealthBar);
	}

	public void changeHealthBarVisibility() {
		if (this.gHealthBar.isVisible()) {
			this.gHealthBar.hide();
		} else {
			this.gHealthBar.show();
		}
	}

	public abstract void sufferFromAttack();

	public Scene getParentScene() {
		return this.gParentScene;
	}

	public HealthBar getHealthBar() {
		return this.gHealthBar;
	}

	public IController getController() {
		return this.gController;
	}
}
