package com.github.scaronthesky.eternalwinterwars.view.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.Model;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public abstract class AControllerMenuScene extends MenuScene
		implements
			IControllerScene,
			IOnMenuItemClickListener {
	private IController gController;

	/**
	 * Creates an instance of the {@link AControllerScene} subclass
	 * 
	 * @param model
	 *            {@link Model} reference
	 */
	public AControllerMenuScene(IController pController, Camera pCamera) {
		super(pCamera);
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
