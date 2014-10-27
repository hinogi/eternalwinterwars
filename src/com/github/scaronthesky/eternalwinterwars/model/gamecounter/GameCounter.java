package com.github.scaronthesky.eternalwinterwars.model.gamecounter;

import com.github.scaronthesky.eternalwinterwars.model.players.Player;

public class GameCounter {

	private Player playerA;
	private Player playerB;

	/**
	 * The game counter class.
	 * 
	 * @param playerA
	 * @param playerB
	 */
	public GameCounter(Player playerA, Player playerB) {
		super();
		this.playerA = playerA;
		this.playerB = playerB;
	}

	/**
	 * Checks whether the player has lost because he has no budget left.
	 * 
	 * @param player
	 * @return
	 */
	public boolean isGameOverForPlayerA() {
		return playerA.getBudget() > 0;
	}

	/**
	 * Checks whether the player has lost because he has no budget left.
	 * 
	 * @param player
	 * @return
	 */
	public boolean isGameOverForPlayerB() {
		return playerB.getBudget() > 0;
	}

}
