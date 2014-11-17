package com.github.scaronthesky.eternalwinterwars.view;

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
public interface IView {

	public HUDManager getHUDManager();

	public SceneManager getSceneManager();

	public SoundManager getSoundManager();

	public ResourceManager getResourceManager();

	public AnimationPropertiesManager getAnimationPropertiesManager();

	public GameBaseEntityManager getGameBaseEntityManager();

	public void limitCameraBoundsToBoard(MainActivity pMainActivity,
			Board pBoard);

	public float getCellSideLength();

	public void instantiateManagers();
}
