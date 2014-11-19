package com.github.scaronthesky.eternalwinterwars.view.managers;

import org.andengine.entity.scene.Scene;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.scenes.AControllerMenuScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene.EditorScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene.IEditorScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.gamescene.GameScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.gamescene.IGameScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.menuscene.MenuScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.splashscene.ISplashScene;
import com.github.scaronthesky.eternalwinterwars.view.scenes.splashscene.SplashScene;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class SceneManager extends AManager {
	public static enum SceneType {
		SPLASH, MENU, GAME, EDITOR
	}

	private SceneType gActualSceneType;
	private IGameScene gGameScene;
	private ISplashScene gSplashScene;
	private AControllerMenuScene gMenuScene;
	private IEditorScene gEditorScene;

	/**
	 * Creates an instance of {@link SceneManager}
	 * 
	 * @param pController
	 */
	public SceneManager(IController pController) {
		super(pController);
	}

	@Override
	public void preLoad() {
		this.gSplashScene = new SplashScene(this.getController());
	}

	@Override
	public void load() {
		this.gMenuScene = new MenuScene(this.getController());
		this.gGameScene = new GameScene(this.getController());
		this.gEditorScene = new EditorScene(this.getController());
	}

	/**
	 * @return the actual selected {@link Scene}
	 */
	public Scene getActualScene() {
		switch (this.gActualSceneType) {
		case GAME:
			return this.gGameScene.getInstance();
		case MENU:
			return this.gMenuScene;
		case SPLASH:
			return this.gSplashScene.getInstance();
		case EDITOR:
			return this.gEditorScene.getInstance();
		default:
			return null;
		}
	}

	public IGameScene getGameScene() {
		return this.gGameScene;
	}

	public ISplashScene getSplashScene() {
		return this.gSplashScene;
	}

	public AControllerMenuScene getMenuScene() {
		return this.gMenuScene;
	}

	public IEditorScene getEditorScene() {
		return this.gEditorScene;
	}

	/**
	 * Sets the actual {@link Scene}
	 * 
	 * @param pActualSceneType
	 *            the actualSceneType to set
	 */
	public Scene setActualSceneType(SceneType pActualSceneType) {
		this.gActualSceneType = pActualSceneType;
		Scene lActualScene = this.getActualScene();
		if (lActualScene != null) {
			this.getController().getMainActivity().getEngine()
					.setScene(lActualScene);
		}
		return lActualScene;
	}

	public SceneType getActualSceneType() {
		return this.gActualSceneType;
	}

}
