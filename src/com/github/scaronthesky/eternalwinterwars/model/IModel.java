package com.github.scaronthesky.eternalwinterwars.model;

import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public interface IModel {
	public void startEditing();

	public void finishEditing();

	public CellControl getCellControl();
}
