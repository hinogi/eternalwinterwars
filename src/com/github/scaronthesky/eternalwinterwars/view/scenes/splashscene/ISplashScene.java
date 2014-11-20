package com.github.scaronthesky.eternalwinterwars.view.scenes.splashscene;

import org.andengine.entity.scene.Scene;

import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;

public interface ISplashScene {
	/**
	 * Loads Resources and changes the {@link Scene}
	 * 
	 * @param pNewScene
	 *            the new Scene
	 */
	public void loadAndChangeScene(SceneType pNewScene);

	/**
	 * @return Scene-Instance of implementing class
	 */
	public Scene getInstance();
}
