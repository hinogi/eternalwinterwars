package com.github.scaronthesky.eternalwinterwars.model.players;

import com.github.scaronthesky.eternalwinterwars.model.gamegrid.Coordinate;

public class Player implements PlayerInterface {

	private String name;
	private PlayerColor playerColor;
	private Coordinate startingPosition;
	private int budget;

	public Player(String name, PlayerColor playerColor,
			Coordinate startingPosition, int budget) {
		this.name = name;
		this.playerColor = playerColor;
		this.startingPosition = startingPosition;
		this.budget = budget;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public PlayerColor getColor() {
		return this.playerColor;
	}

	@Override
	public Coordinate getStartingPosition() {
		return this.startingPosition;
	}

	@Override
	public int getBudget() {
		return this.budget;
	}

}
