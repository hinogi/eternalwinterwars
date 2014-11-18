package com.github.scaronthesky.eternalwinterwars.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.github.scaronthesky.eternalwinterwars.controller.constants.Constants;
import com.github.scaronthesky.eternalwinterwars.controller.util.CellBuilder;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.model.cellcontrol.CellControl;
import com.github.scaronthesky.eternalwinterwars.model.cells.Cell;
import com.github.scaronthesky.eternalwinterwars.view.managers.AManager;

/**
 * @author Manu
 * 
 */
public class IOService extends AManager {

	private List<CellControl> gCellControls;
	private int gDollars;

	/**
	 * Creates an instance of {@link IOService}
	 * 
	 * @param controller
	 *            {@link Model} reference
	 */
	public IOService(Controller controller) {
		super(controller);
		// XXX Test
		this.gCellControls = new ArrayList<CellControl>();
		this.gCellControls.clear();
		this.insertNewMaps();
	}

	private void insertNewMaps() {
		ArrayList<ArrayList<Cell>> lCells = new ArrayList<ArrayList<Cell>>();
		for (int lRow = 0; lRow < 10; lRow++) {
			ArrayList<Cell> lRowList = new ArrayList<Cell>();
			for (int lColumn = 0; lColumn < 40; lColumn++) {
				lRowList.add(CellBuilder.buildDefaultPlain());
			}
			lCells.add(lRowList);
		}
		lCells.get(5).set(10,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(5).set(11, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(5).set(12,
				CellBuilder.buildDefaultRiver("river_nosw_v1.png"));
		this.gCellControls.add(new CellControl(lCells));
	}

	public CellControl randomCellControl() {
		return this.gCellControls.get((int) (Math.random() * this.gCellControls
				.size()));
	}

	@Override
	public void preLoad() {
		// Do nothing
	}

	@Override
	public void load() {
		// this.readCellControls();
		// SharedPreferences prefs = getController().getMainActivity()
		// .getPreferences(Context.MODE_PRIVATE);
		// for (String key : prefs.getAll().keySet()) {
		// if (key.startsWith(Constants.KEYFRONT_MAP)) {
		// gMapStorage.put(
		// key.substring(Constants.KEYFRONT_MAP.length()),
		// new BoardString(getController(), prefs.getString(key,
		// null)));
		// }
		// }
		// gDollars = prefs.getInt("dollars", 0);
	}

	private boolean saveCellControls() {
		try {
			FileOutputStream lFileOutputStream = this
					.getController()
					.getMainActivity()
					.getApplication()
					.openFileOutput(Constants.IOSERVICE_FILE_PATH_CELLCONTROLS,
							Context.MODE_PRIVATE);
			ObjectOutputStream lObjectOutputStream = new ObjectOutputStream(
					lFileOutputStream);
			for (CellControl lCellControl : this.gCellControls) {
				lObjectOutputStream.writeObject(lCellControl);
			}
			lObjectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void readCellControls() {
		this.gCellControls = new ArrayList<CellControl>();
		try {
			FileInputStream lFileInputStream = this.getController()
					.getMainActivity().getApplication()
					.openFileInput(Constants.IOSERVICE_FILE_PATH_CELLCONTROLS);
			ObjectInputStream lObjectInputStream = new ObjectInputStream(
					lFileInputStream);
			Object lReadObject = lObjectInputStream.readObject();
			while (lReadObject != null) {
				if (lReadObject instanceof CellControl) {
					this.gCellControls.add((CellControl) lReadObject);
				}
				lReadObject = lObjectInputStream.readObject();
			}
			lObjectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		this.saveCellControls();
		// SharedPreferences.Editor editor = getController().getMainActivity()
		// .getPreferences(Context.MODE_PRIVATE).edit();
		// for (String key : gMapStorage.keySet()) {
		// editor.putString(Constants.KEYFRONT_MAP + key, gMapStorage.get(key)
		// .toString());
		// }
		// editor.putInt("dollars", gDollars);
		// editor.commit();
	}

	public List<CellControl> getCellControls() {
		return this.gCellControls;
	}

	public int getDollars() {
		return this.gDollars;
	}

	public void setDollars(int dollars) {
		this.gDollars = dollars;
	}
}
