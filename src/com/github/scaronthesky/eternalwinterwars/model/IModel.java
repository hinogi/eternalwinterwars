package com.github.scaronthesky.eternalwinterwars.model;

import java.util.List;

import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.model.entity.FightingEntity;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public interface IModel {
	public void startEditing();

	public void finishEditing();

	public CellControl getCellControl();

	public EditorControl getEditorControl();

	public void displayBoard();

	public Player getActivePlayer();

	public void nextPlayer();

	public Player[] getPlayers();

	/**
	 * @param pUnit
	 *            moving {@link Unit}
	 * @param pColumn
	 *            target column
	 * @param pRow
	 *            target row
	 * @return List of logical cell coordinates on the way to the target cell
	 *         (includes target cell!)
	 */
	public List<int[]> moveUnit(Unit pUnit, int pColumn, int pRow);

	/**
	 * @param pAttackingUnit
	 *            attacking {@link Unit}
	 * @param pDefendingEntity
	 *            defending {@link FightingEntity}
	 * @return damage done to the pDefendingEntity by pAttackingUnit
	 */
	public int attack(Unit pAttackingUnit, FightingEntity pDefendingEntity);
}
