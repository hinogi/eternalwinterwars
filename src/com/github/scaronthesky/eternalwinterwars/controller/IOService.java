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
			for (int lColumn = 0; lColumn < 35; lColumn++) {
				lRowList.add(CellBuilder.buildDefaultPlain());
			}
			lCells.add(lRowList);
		}
		lCells.get(0).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(1,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(2,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(3,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(4,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(5,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(6,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(7,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(8,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(0).set(9,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(10,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(11,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(12,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(0).set(13,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(14,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(0).set(15,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(16,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(17,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(18,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(19,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(20,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(21,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(22,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(23,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(24,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(0).set(25,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(26,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(0).set(27,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(28,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(0).set(29,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(0).set(30, CellBuilder.buildDefaultRiver("river_ns_v1.png"));
		lCells.get(0).set(31,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(0).set(32,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(33,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(0).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(1,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(5, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(6,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(1).set(7,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(8,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(1).set(9,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(10, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(11, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(12, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(14,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(15,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(17, CellBuilder.buildDefaultForest("tree_v4.png"));
		lCells.get(1).set(18, CellBuilder.buildDefaultForest("tree_v4.png"));
		lCells.get(1).set(19, CellBuilder.buildDefaultForest("tree_v4.png"));
		lCells.get(1).set(20,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(24,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(1).set(25, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(26, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(27,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(1).set(28, CellBuilder.buildDefaultRiver("river_os_v1.png"));
		lCells.get(1).set(29, CellBuilder.buildDefaultRiver("river_ow_v1.png"));
		lCells.get(1).set(30, CellBuilder.buildDefaultRiver("river_nw_v1.png"));
		lCells.get(1).set(31, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(1).set(32,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(1).set(33,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(1).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(2).set(0,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(2).set(2, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(5, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(6, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(7,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(2).set(8, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(9, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(10, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(14,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(2).set(19, CellBuilder.buildDefaultForest("tree_v4.png"));
		lCells.get(2).set(24,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(2).set(28, CellBuilder.buildDefaultRiver("river_ns_v4.png"));
		lCells.get(2).set(32, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(2).set(33,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(2).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(3).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(3).set(16,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(3).set(18, CellBuilder.buildDefaultForest("tree_v4.png"));
		lCells.get(3).set(21, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(3).set(27, CellBuilder.buildDefaultRiver("river_o_v1.png"));
		lCells.get(3).set(28, CellBuilder.buildDefaultRiver("river_nw_v1.png"));
		lCells.get(3).set(33,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(3).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(4).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(4).set(10, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(4).set(33,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(4).set(34,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(5).set(0,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(5).set(2, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(5).set(8, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(5).set(14, CellBuilder.buildDefaultRiver("river_s_v1.png"));
		lCells.get(5).set(21, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(5).set(31,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(5).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(6).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(6).set(1, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(6).set(13, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(6).set(14, CellBuilder.buildDefaultRiver("river_ns_v3.png"));
		lCells.get(6).set(30,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(6).set(31,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(6).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(7).set(0,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(7).set(1, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(2, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(3, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(4, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(5, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(8,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(7).set(9,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(7).set(12, CellBuilder.buildDefaultRiver("river_os_v1.png"));
		lCells.get(7).set(13, CellBuilder.buildDefaultRiver("river_ow_v1.png"));
		lCells.get(7).set(14, CellBuilder.buildDefaultRiver("river_nw_v1.png"));
		lCells.get(7).set(16, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(21,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(7).set(26, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(27, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(28, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(7).set(30,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(7).set(31,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(7).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(1,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(2, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(8).set(3, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(8).set(4, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(8).set(5,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(8).set(7,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(8,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(9,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(12, CellBuilder.buildDefaultRiver("river_ns_v4.png"));
		lCells.get(8).set(15,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(16,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(19,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(20,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(21,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(8).set(22,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(23,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(25, CellBuilder.buildDefaultForest("tree_v5.png"));
		lCells.get(8).set(26,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(27,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(28,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(29,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(30,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(8).set(31,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(32,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(33,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(8).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(0,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(1,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(2,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(3,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(4,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(5,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(6,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(7,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(8,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(9,
				CellBuilder.buildDefaultMountain("mountain_v2.png"));
		lCells.get(9).set(10,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(11,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(12, CellBuilder.buildDefaultRiver("river_ns_v4.png"));
		lCells.get(9).set(13,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(14,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(9).set(15,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(16,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(17,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(9).set(18,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(19,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(20,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(9).set(21,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(22,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(23,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(24,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(25,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(26,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(27,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(28,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(9).set(29,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(30,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(31,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(32,
				CellBuilder.buildDefaultMountain("mountain_v3.png"));
		lCells.get(9).set(33,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		lCells.get(9).set(34,
				CellBuilder.buildDefaultMountain("mountain_v1.png"));
		this.gCellControls.add(new CellControl(lCells));

		// ArrayList<ArrayList<Cell>> lCells = new ArrayList<ArrayList<Cell>>();
		// for (int lRow = 0; lRow < 10; lRow++) {
		// ArrayList<Cell> lRowList = new ArrayList<Cell>();
		// for (int lColumn = 0; lColumn < 40; lColumn++) {
		// lRowList.add(CellBuilder.buildDefaultPlain());
		// }
		// lCells.add(lRowList);
		// }
		// lCells.get(5).set(10,
		// CellBuilder.buildDefaultMountain("mountain_v3.png"));
		// lCells.get(5).set(11, CellBuilder.buildDefaultForest("tree_v5.png"));
		// lCells.get(5).set(12,
		// CellBuilder.buildDefaultRiver("river_nosw_v1.png"));
		// this.gCellControls.add(new CellControl(lCells));
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
