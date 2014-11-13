package com.github.scaronthesky.eternalwinterwars.controller;

import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.view.BoardString;
import com.github.scaronthesky.eternalwinterwars.view.managers.AManager;

/**
 * @author Manu
 * 
 */
public class IOService extends AManager {
	public static final String KEYFRONT_MAP = String
			.valueOf("<MAP>".hashCode());
	private Map<String, BoardString> mapStorage;
	private int dollars;

	/**
	 * Creates an instance of {@link IOService}
	 * 
	 * @param controller
	 *            {@link Model} reference
	 */
	public IOService(Controller controller) {
		super(controller);
		// XXX Test
		mapStorage.clear();
		mapStorage
				.put("Map#1",
						new BoardString(
								controller,
								"{2}[40/40]77777777777BBBBBBBBBBBBBBBBBBBBBBBBBBBBB7777777BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB777777BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB77777BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB77777BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB777777BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB77ikoBikjkoBBBBBBBBBBBBBBBBBBBBBBBBBBBBB77c3eBeBdBfBBBBBBBBBBBBBBBBBBBBBBBBBBBBB7B8jhBdBeBeBBBBBBBBBBBBBBBBBBBBBBBBBBBBB77B8kkgBfBdBBBBBBBBBBBBBBBBBBBBBBBBBBBBB7BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"));
	}

	@Override
	public void preLoad() {
		mapStorage = new TreeMap<String, BoardString>();
	}

	@Override
	public void load() {
		SharedPreferences prefs = getController().getMainActivity()
				.getPreferences(Context.MODE_PRIVATE);
		for (String key : prefs.getAll().keySet()) {
			if (key.startsWith(KEYFRONT_MAP)) {
				mapStorage.put(
						key.substring(KEYFRONT_MAP.length()),
						new BoardString(getController(), prefs.getString(key,
								null)));
			}
		}
		dollars = prefs.getInt("dollars", 0);
	}

	public void save() {
		SharedPreferences.Editor editor = getController().getMainActivity()
				.getPreferences(Context.MODE_PRIVATE).edit();
		for (String key : mapStorage.keySet()) {
			editor.putString(KEYFRONT_MAP + key, mapStorage.get(key).toString());
		}
		editor.putInt("dollars", dollars);
		editor.commit();
	}

	public Map<String, BoardString> getMapStorage() {
		return mapStorage;
	}

	public int getDollars() {
		return dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
	}
}
