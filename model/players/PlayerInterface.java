package com.github.scaronthesky.eternalwinterwars.model.players;

import com.github.scaronthesky.eternalwinterwars.model.gamegrid.Coordinate;

public interface PlayerInterface {

	public String getName();

	public PlayerColor getColor();

	public Coordinate getStartingPosition();

	public int getBudget();

}
