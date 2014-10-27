package com.github.scaronthesky.eternalwinterwars.model.players;

public interface Player {

	public String getName();

	public void earnBudget(int budget);

	public void looseBudget(int budget);

	public int getBudget();

}
