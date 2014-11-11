package com.github.scaronthesky.eternalwinterwars.model;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.controller.IOService;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.view.BoardString;
import com.github.scaronthesky.eternalwinterwars.view.MainActivity;

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
		// Displays the GameScene - GUI 'setVisible(true)'. Has to be done after
		// creating the SharedPreferencesManager(!)
		this.displayBoard(getRandomMap());
	}

	public void displayBoard(BoardString boardMap) {
		// Creates a CellControl, needs at least one stored BoardMap to work.
		// Has to be done after creating the SharedPreferencesManager(!)
		this.setCellControl(new CellControl(boardMap.getCells()));
	}

	/**
	 * @return a random {@link BoardMap} from the {@link IOService}
	 */
	public BoardString getRandomMap() {
		return controller
				.getIOService()
				.getMapStorage()
				.values()
				.toArray(
						new BoardString[controller.getIOService()
								.getMapStorage().values().size()])[(int) (Math
				.random() * controller.getIOService().getMapStorage().size())];
	}

	public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	@Override
	public CellControl getCellControl() {
		if (cellControl == null) {
			cellControl = new CellControl(getController().getIOService()
					.getMapStorage().get("Map#1").getCells());
		}
		return cellControl;
	}

	/**
	 * @param cellControl
	 *            the cellControl to set
	 */
	public void setCellControl(CellControl cellControl) {
		this.cellControl = cellControl;
	}

	public EditorControl getEditorControl() {
		return editorControl;
	}

	public void setEditorControl(EditorControl editorControl) {
		this.editorControl = editorControl;
	}

	// -----------------------------------------------------
	// Overriden methods
	// -----------------------------------------------------
	@Override
	public void startEditing() {
		setEditorControl(new EditorControl(getController()));
	}

	@Override
	public void finishEditing() {
		controller
				.getIOService()
				.getMapStorage()
				.put("Map#"
						+ controller.getIOService().getMapStorage().keySet()
								.size() + 1, editorControl.getBoardString());
		editorControl = null;
	}
}
