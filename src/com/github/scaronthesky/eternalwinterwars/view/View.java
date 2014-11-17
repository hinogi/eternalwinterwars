package com.github.scaronthesky.eternalwinterwars.view;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Board;
import com.github.scaronthesky.eternalwinterwars.view.managers.AnimationPropertiesManager;
import com.github.scaronthesky.eternalwinterwars.view.managers.GameBaseEntityManager;
import com.github.scaronthesky.eternalwinterwars.view.managers.HUDManager;
import com.github.scaronthesky.eternalwinterwars.view.managers.ResourceManager;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager;
import com.github.scaronthesky.eternalwinterwars.view.managers.SoundManager;

/**
 * @author Manuel Seiche
 * @since 27.09.2014
 * 
 */
public class View implements IView {

	private IController gController;
	private ResourceManager gResourceManager;
	private HUDManager gHUDManager;
	private SceneManager gSceneManager;
	private SoundManager gSoundManager;
	private AnimationPropertiesManager gAnimationPropertiesManager;
	private GameBaseEntityManager gGameBaseEntityManager;

	/**
	 * Creates an instance of {@link View}
	 * 
	 * @param gController
	 *            {@link Controller} - reference
	 */
	public View(IController gController) {
		this.gController = gController;
	}

	@Override
	public void instantiateManagers() {
		this.gResourceManager = new ResourceManager(this.gController);
		this.gHUDManager = new HUDManager(this.gController);
		this.gSceneManager = new SceneManager(this.gController);
		this.gSoundManager = new SoundManager(this.gController);
		this.gAnimationPropertiesManager = new AnimationPropertiesManager(
				this.gController);
		this.gGameBaseEntityManager = new GameBaseEntityManager(
				this.gController);
	}

	@Override
	public void limitCameraBoundsToBoard(MainActivity pMainActivity,
			Board pBoard) {
		pMainActivity.getSmoothCamera().setBounds(0, 0, pBoard.getWidth(),
				pBoard.getHeight());
	}

	@Override
	public SceneManager getSceneManager() {
		return this.gSceneManager;
	}

	@Override
	public ResourceManager getResourceManager() {
		return this.gResourceManager;
	}

	@Override
	public SoundManager getSoundManager() {
		return this.gSoundManager;
	}

	@Override
	public HUDManager getHUDManager() {
		return this.gHUDManager;
	}

	@Override
	public AnimationPropertiesManager getAnimationPropertiesManager() {
		return this.gAnimationPropertiesManager;
	}

	@Override
	public GameBaseEntityManager getGameBaseEntityManager() {
		return this.gGameBaseEntityManager;
	}

	@Override
	public float getCellSideLength() {
		return this.gController.getMainActivity().getSmoothCamera().getWidth()
				* Board.DEFAULT_CELL_BOUNDS_FACTOR;
	}
}
