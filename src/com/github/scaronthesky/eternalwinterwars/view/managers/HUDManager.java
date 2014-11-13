package com.github.scaronthesky.eternalwinterwars.view.managers;

import org.andengine.engine.camera.hud.HUD;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.hud.gamehud.GameHUD;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class HUDManager extends AManager {
	public enum HUDType {
		GAME, EDITOR
	}

	private GameHUD gControllerHUD;

	public HUDManager(IController pController) {
		super(pController);
	}

	@Override
	public void preLoad() {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		this.gControllerHUD = new GameHUD(this.getController());
	}

	public void attachHUD(HUDType pHUDType) {
		this.getController().getMainActivity().getSmoothCamera()
				.setHUD(this.getHUD(pHUDType));
	}

	private HUD getHUD(HUDType pHUDType) {
		switch (pHUDType) {
			case GAME :
				return this.gControllerHUD;
			default :
				return null;
		}
	}

	public GameHUD getGameHUD() {
		return this.gControllerHUD;
	}

}
