package com.github.scaronthesky.eternalwinterwars.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.view.Constants;
import com.github.scaronthesky.eternalwinterwars.view.GameControl;
import com.github.scaronthesky.eternalwinterwars.view.MainActivity;
import com.github.scaronthesky.eternalwinterwars.view.View;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;
import com.github.scaronthesky.eternalwinterwars.view.managers.SoundManager.MusicType;
import com.github.scaronthesky.eternalwinterwars.view.managers.SoundManager.SoundType;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationProperties;
import com.github.scaronthesky.eternalwinterwars.view.particles.ParticleSystemBuilder;
import com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene.EditorScene;

public class Controller implements IController {
	private MainActivity mainActivity;
	private IOService ioService;
	private Model model;
	private View view;

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
	public Model getModel() {
		return this.model;
	}

	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public void startTest() {
		new GameControl(this, 2);
		this.getView().getSoundManager().setActualMusicType(MusicType.GAME);
		this.getView().getSoundManager().getActualMusic().play();
		// ---------------------------------------------------------------
		// XXX BuildingEntity - Test
		// ---------------------------------------------------------------
		BuildingEntity lBuildingEntity = new BuildingEntity(this, this
				.getView().getSceneManager().getGameScene(), this.getView()
				.getCellSideLength(),
				this.getView().getCellSideLength() * 0.2f, 100, 100, Color.RED,
				Color.WHITE, true, this.getView().getResourceManager()
						.getTextureRegions().get("4"), this.getView()
						.getCellSideLength());
		lBuildingEntity.setX(this.view.getCellSideLength() * 10);
		lBuildingEntity.setY(this.view.getCellSideLength() * 5);
		this.getView().getSceneManager().getGameScene()
				.attachChild(lBuildingEntity);
		// ---------------------------------------------------------------
		// XXX UnitEntity - Test
		// ---------------------------------------------------------------
		Map<String, AnimationProperties> lAnimationProperties = new HashMap<String, AnimationProperties>();
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_UP,
				new AnimationProperties(this.view.getResourceManager()
						.getTiledTextureRegionTestUnitMove(), new long[] { 150,
						150, 150, 150 }, 0, 3, true, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_DOWN,
				new AnimationProperties(this.view.getResourceManager()
						.getTiledTextureRegionTestUnitMove(), new long[] { 150,
						150, 150, 150 }, 4, 7, true, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_RIGHT,
				new AnimationProperties(this.view.getResourceManager()
						.getTiledTextureRegionTestUnitMove(), new long[] { 150,
						150, 150, 150 }, 8, 11, true, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_LEFT,
				new AnimationProperties(this.view.getResourceManager()
						.getTiledTextureRegionTestUnitMove(), new long[] { 150,
						150, 150, 150 }, 8, 11, true, true, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_IDLE,
				new AnimationProperties(this.view.getResourceManager()
						.getTiledTextureRegionTestUnitMove(), new long[] { 150,
						150, 150, 150 }, 4, 7, true, false, false));
		UnitEntity lUnitEntity = new UnitEntity(this, this.view
				.getSceneManager().getGameScene(), this.getView()
				.getCellSideLength(),
				this.getView().getCellSideLength() * 0.2f, 100, 90,
				Color.GREEN, Color.WHITE, true, lAnimationProperties,
				"move_left", this.view.getCellSideLength());
		lUnitEntity.setX(this.view.getCellSideLength() * 12);
		lUnitEntity.setY(this.view.getCellSideLength() * 7);
		this.getView().getSceneManager().getGameScene()
				.attachChild(lUnitEntity);
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
}
