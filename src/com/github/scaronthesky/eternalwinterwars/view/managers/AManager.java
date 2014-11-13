package com.github.scaronthesky.eternalwinterwars.view.managers;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.scenes.splashscene.SplashScene;

/**
 * @author Manuel Seiche
 * @since 27.09.2014
 * 
 */
public abstract class AManager {
	private IController gController;

	/**
	 * {@link AManager} - Constructor
	 * 
	 * @param pController
	 *            {@link Controller} reference
	 */
	public AManager(IController pController) {
		this.setController(pController);
		this.preLoad();
	}

	/**
	 * Is called when the {@link AManager} is instantiated, preLoads are needed
	 * for the {@link SplashScene}
	 */
	public abstract void preLoad();

	/**
	 * Everything which is contained inside load() is not needed for the
	 * {@link SplashScene} therefore this method is called from the SplashScene
	 */
	public abstract void load();

	public IController getController() {
		return this.gController;
	}

	public void setController(IController pController) {
		this.gController = pController;
	}
}
