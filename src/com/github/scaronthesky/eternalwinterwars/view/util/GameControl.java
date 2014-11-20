package com.github.scaronthesky.eternalwinterwars.view.util;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Board;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class GameControl {
	private IController gController;
	private int gPlayerCount;

	/**
	 * Creates an instance of {@link GameControl}
	 * 
	 * @param pController
	 *            {@link Model} - reference
	 * @param pPlayerCount
	 */
	public GameControl(IController pController, int pPlayerCount) {
		this.gController = pController;
		float cellSideLength = pController.getView().getCellSideLength();
		pController
				.getView()
				.getSceneManager()
				.getGameScene()
				.setBoard(
						new Board(pController, cellSideLength, cellSideLength));
	}

	public int getPlayerCount() {
		return this.gPlayerCount;
	}

	public void setPlayerCount(int pPlayerCount) {
		this.gPlayerCount = pPlayerCount;
	}
}
