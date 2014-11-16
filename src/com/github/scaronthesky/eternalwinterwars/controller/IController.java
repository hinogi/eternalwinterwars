package com.github.scaronthesky.eternalwinterwars.controller;

import org.andengine.input.touch.TouchEvent;

import com.github.scaronthesky.eternalwinterwars.model.IModel;
import com.github.scaronthesky.eternalwinterwars.view.IView;
import com.github.scaronthesky.eternalwinterwars.view.MainActivity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public interface IController {
	public boolean handleGameSceneTouch(TouchEvent pTouchEvent);

	public boolean handleEditorSceneTouch(TouchEvent pTouchEvent);

	public void startEditing();

	public void finishEditing();

	public MainActivity getMainActivity();

	public IView getView();

	public IModel getModel();

	public IOService getIOService();

	public void handleAttackButtonClicked(UnitEntity pUnitEntity);

	public void handleCancelButtonClicked(UnitEntity pUnitEntity);

	/**
	 * XXX Test
	 */
	public void startTest();
}
