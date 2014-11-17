package com.github.scaronthesky.eternalwinterwars.controller;

import java.util.ArrayList;
import java.util.List;

import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.model.IModel;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.view.GameControl;
import com.github.scaronthesky.eternalwinterwars.view.IView;
import com.github.scaronthesky.eternalwinterwars.view.MainActivity;
import com.github.scaronthesky.eternalwinterwars.view.View;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;
import com.github.scaronthesky.eternalwinterwars.view.managers.SoundManager.MusicType;
import com.github.scaronthesky.eternalwinterwars.view.managers.SoundManager.SoundType;
import com.github.scaronthesky.eternalwinterwars.view.particles.ParticleSystemBuilder;
import com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene.EditorScene;

public class Controller implements IController {
	private MainActivity mainActivity;
	private IOService ioService;
	private IModel model;
	private IView view;

	public Controller(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.ioService = new IOService(this);
		this.model = new Model(this);
		this.view = new View(this);
	}

	@Override
	public IOService getIOService() {
		return this.ioService;
	}

	@Override
	public MainActivity getMainActivity() {
		return this.mainActivity;
	}

	@Override
	public IModel getModel() {
		return this.model;
	}

	@Override
	public IView getView() {
		return this.view;
	}

	// XXX TEST
	public static BuildingEntity testBuilding;
	public static UnitEntity testMarksman;

	@Override
	public void startTest() {
		new GameControl(this, 2);
		this.getView().getSoundManager().setActualMusicType(MusicType.GAME);
		this.getView().getSoundManager().getActualMusic().play();
		// ---------------------------------------------------------------
		// XXX BuildingEntity - Test
		// ---------------------------------------------------------------
		testBuilding = this.view.getGameBaseEntityManager().createCastleEntity(
				this.view.getCellSideLength(),
				Color.RED,
				Color.WHITE,
				this.getView().getResourceManager().getTextureRegions()
						.get("3"));
		testBuilding.setX(this.view.getCellSideLength() * 10);
		testBuilding.setY(this.view.getCellSideLength() * 5);
		this.getView().getSceneManager().getGameScene()
				.attachChild(testBuilding);
		// ---------------------------------------------------------------
		// XXX UnitEntity - Test
		// ---------------------------------------------------------------
		UnitEntity lKnight = this
				.getView()
				.getGameBaseEntityManager()
				.createKnight(this.view.getCellSideLength(), Color.BLUE,
						Color.WHITE);
		lKnight.setX(this.view.getCellSideLength() * 12);
		lKnight.setY(this.view.getCellSideLength() * 7);
		this.getView().getSceneManager().getGameScene().attachChild(lKnight);
		testMarksman = this
				.getView()
				.getGameBaseEntityManager()
				.createMarksman(this.view.getCellSideLength(), Color.RED,
						Color.WHITE);
		testMarksman.setX(this.view.getCellSideLength() * 9);
		testMarksman.setY(this.view.getCellSideLength() * 6);
		this.getView().getSceneManager().getGameScene()
				.attachChild(testMarksman);
		//
		UnitEntity lArtillery = this
				.getView()
				.getGameBaseEntityManager()
				.createArtillery(this.view.getCellSideLength(), Color.BLUE,
						Color.WHITE);
		lArtillery.setX(this.view.getCellSideLength() * 4);
		lArtillery.setY(this.view.getCellSideLength() * 7);
		this.getView().getSceneManager().getGameScene().attachChild(lArtillery);
		//
		UnitEntity lCavallery = this
				.getView()
				.getGameBaseEntityManager()
				.createCavallery(this.view.getCellSideLength(), Color.BLUE,
						Color.WHITE);
		lCavallery.setX(this.view.getCellSideLength() * 10);
		lCavallery.setY(this.view.getCellSideLength() * 10);
		this.getView().getSceneManager().getGameScene().attachChild(lCavallery);
		// ---------------------------------------------------------------
		// XXX SnowParticleSystem - Test
		// ---------------------------------------------------------------
		this.getView()
				.getSceneManager()
				.getGameScene()
				.attachChildOnTop(
						ParticleSystemBuilder
								.buildLinearSnowParticleSystem(this));
	}

	@Override
	public boolean handleGameSceneTouch(final TouchEvent pTouchEvent) {
		if (pTouchEvent.isActionDown()) {
			this.view.getSoundManager().getSounds().get(SoundType.ATTACK)
					.play();
			List<float[]> lAppearCoordinatesOnGameScene = new ArrayList<float[]>();
			lAppearCoordinatesOnGameScene.add(this.getAbsoluteCoordinates(this
					.getLogicalCoordinates(pTouchEvent.getX(),
							pTouchEvent.getY())));
			this.view
					.getSceneManager()
					.getGameScene()
					.addIncome(lAppearCoordinatesOnGameScene,
							(int) (Math.random() * 100));
			return true;
		}
		return false;
	}

	@Override
	public boolean handleEditorSceneTouch(TouchEvent pTouchEvent) {
		if (pTouchEvent.isActionDown()) {
			EditorScene editorScene = this.view.getSceneManager()
					.getEditorScene();
			EditorControl editorControl = this.model.getEditorControl();
			String key = editorScene.getActualKey();
			int column = (int) (pTouchEvent.getX() / editorScene
					.getSpriteSideLength());
			int row = (int) (pTouchEvent.getY() / editorScene
					.getSpriteSideLength());
			editorControl.setKey(key, column, row);
			editorScene.drawSprite(key, column, row);
		}
		return false;
	}

	@Override
	public void startEditing() {
		this.model.startEditing();
		EditorScene editorScene = this.view.getSceneManager().getEditorScene();
		EditorControl editorControl = this.model.getEditorControl();
		editorScene.setColumnsAndRows(editorControl.getColumns(),
				editorControl.getRows());
		editorScene.setBackground(editorControl.getBackgroundKey(),
				editorControl.getColumns(), editorControl.getRows());
	}

	@Override
	public void finishEditing() {
		this.model.finishEditing();
		this.view.getSceneManager().setActualSceneType(SceneType.MENU);
	}

	public float getAbsoluteCoordinate(int pColumnOrRow) {
		return this.view.getCellSideLength() * pColumnOrRow;
	}

	public int getLogicalCoordinate(float pAbsoluteCoordinate) {
		return (int) (pAbsoluteCoordinate / this.view.getCellSideLength());
	}

	public float[] getAbsoluteCoordinates(int[] pColumnAndRow) {
		float lCellSideLength = this.view.getCellSideLength();
		return new float[] { lCellSideLength * pColumnAndRow[0],
				lCellSideLength * pColumnAndRow[1] };
	}

	public int[] getLogicalCoordinates(float[] pAbsoluteCoordinates) {
		float lCellSideLength = this.view.getCellSideLength();
		return new int[] { (int) (pAbsoluteCoordinates[0] / lCellSideLength),
				(int) (pAbsoluteCoordinates[1] / lCellSideLength) };
	}

	public float[] getAbsoluteCoordinates(int pColumn, int pRow) {
		float lCellSideLength = this.view.getCellSideLength();
		return new float[] { lCellSideLength * pColumn, lCellSideLength * pRow };
	}

	public int[] getLogicalCoordinates(float pX, float pY) {
		float lCellSideLength = this.view.getCellSideLength();
		return new int[] { (int) (pX / lCellSideLength),
				(int) (pY / lCellSideLength) };
	}

	@Override
	public String toString() {
		return "Model=" + this.model + "/View=" + this.view;
	}

	@Override
	public void handleAttackButtonClicked(UnitEntity pUnitEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleCancelButtonClicked(UnitEntity pUnitEntity) {
		// TODO Auto-generated method stub

	}
}
