package com.github.scaronthesky.eternalwinterwars.model;

import java.util.List;

import com.github.scaronthesky.eternalwinterwars.MainActivity;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.model.entity.FightingEntity;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.players.PlayerImpl;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

/**
 * @author Manu
 * 
 */
public class Model implements IModel {
	private IController controller;
	// -----------------------------------------------------
	// Control Classes
	// Initialized once per game
	// -----------------------------------------------------
	private CellControl cellControl;
	private EditorControl editorControl;
	private Player[] players = new Player[] { new PlayerImpl("Manuel", 100),
			new PlayerImpl("Matthias", 100) };
	private int activePlayerIndex;

	/**
	 * Creates an instance of {@link Model}
	 * 
	 * Do never change the initialization-order!
	 * 
	 * @param mainActivity
	 *            {@link MainActivity} reference
	 */
	public Model(IController controller) {
		this.setController(controller);
	}

	@Override
	public void displayBoard() {
		this.setCellControl(this.getCellControl());
	}

	public IController getController() {
		return this.controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	@Override
	public CellControl getCellControl() {
		if (this.cellControl == null) {
			this.cellControl = this.getController().getIOService()
					.randomCellControl();
		}
		return this.cellControl;
	}

	/**
	 * @param cellControl
	 *            the cellControl to set
	 */
	public void setCellControl(CellControl cellControl) {
		this.cellControl = cellControl;
	}

	@Override
	public EditorControl getEditorControl() {
		return this.editorControl;
	}

	public void setEditorControl(EditorControl editorControl) {
		this.editorControl = editorControl;
	}

	// -----------------------------------------------------
	// Overriden methods
	// -----------------------------------------------------
	@Override
	public void startEditing() {
		this.setEditorControl(new EditorControl(this.getController()));
	}

	@Override
	public void finishEditing() {
		this.controller.getIOService().getCellControls()
				.add(this.editorControl.getCellControl());
		this.editorControl = null;
	}

	@Override
	public Player getActivePlayer() {
		return this.players[this.activePlayerIndex];
	}

	@Override
	public void nextPlayer() {
		this.activePlayerIndex = (this.activePlayerIndex == this.players.length - 1) ? 0
				: this.activePlayerIndex + 1;
	}

	@Override
	public Player[] getPlayers() {
		return this.players;
	}

	@Override
	public List<int[]> moveUnit(Unit pUnit, int pColumn, int pRow) {
		// TODO
		return null;
	}

	@Override
	public int attack(Unit pAttackingUnit, FightingEntity pDefendingEntity) {
		// TODO
		return 0;
	}

}
