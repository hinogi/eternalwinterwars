package com.github.scaronthesky.eternalwinterwars.view.scenes;

import org.andengine.entity.scene.Scene;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.Model;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public abstract class AControllerScene extends Scene
		implements
			IControllerScene {
	private IController gController;

	/**
	 * Creates an instance of the {@link AControllerScene} subclass
	 * 
	 * @param model
	 *            {@link Model} reference
	 */
	public AControllerScene(IController pController) {
		this.setController(pController);
		this.initialize();
	}

	public IController getController() {
		return this.gController;
	}

	public void setController(IController pController) {
		this.gController = pController;
	}

}
