package com.github.scaronthesky.eternalwinterwars.model.players;

public class PlayerImpl implements Player {

	private static int indexCounter;
	private final String name;
	private int budget;
	private int index;

	public PlayerImpl(String name, int budget) {
		super();
		this.name = name;
		this.budget = budget;
		this.index = indexCounter++;
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

	@Override
	public int getIndex() {
		return this.index;
	}

}
