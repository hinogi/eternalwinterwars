package com.github.scaronthesky.eternalwinterwars.view.hud;

import org.andengine.engine.camera.hud.HUD;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public abstract class ControllerHUD extends HUD {
	private IController gController;

	public ControllerHUD(IController pController) {
		this.gController = pController;
		this.initialize();
	}

	public abstract void initialize();

	public IController getController() {
		return this.gController;
	}
}
