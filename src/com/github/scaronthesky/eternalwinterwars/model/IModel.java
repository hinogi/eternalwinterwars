package com.github.scaronthesky.eternalwinterwars.model;

import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;

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
}
