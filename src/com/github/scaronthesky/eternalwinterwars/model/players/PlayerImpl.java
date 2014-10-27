package com.github.scaronthesky.eternalwinterwars.model.players;

public class PlayerImpl implements Player {

	private final String name;
	private int budget;

	public PlayerImpl(String name, int budget) {
		super();
		this.name = name;
		this.budget = budget;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getBudget() {
		return this.budget;
	}

	@Override
	public void earnBudget(int budget) {
		this.budget += budget;

	}

	@Override
	public void looseBudget(int budget) {
		this.budget -= budget;
	}

}
