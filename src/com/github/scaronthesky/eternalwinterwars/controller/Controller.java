package com.github.scaronthesky.eternalwinterwars.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.andengine.input.touch.TouchEvent;

import com.github.scaronthesky.eternalwinterwars.MainActivity;
import com.github.scaronthesky.eternalwinterwars.controller.mapping.BaseGameEntityMapper;
import com.github.scaronthesky.eternalwinterwars.controller.util.CellBuilder;
import com.github.scaronthesky.eternalwinterwars.model.IModel;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.model.editorcontrol.EditorControl;
import com.github.scaronthesky.eternalwinterwars.model.entity.FightingEntity;
import com.github.scaronthesky.eternalwinterwars.model.units.Archer;
import com.github.scaronthesky.eternalwinterwars.model.units.Knight;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;
import com.github.scaronthesky.eternalwinterwars.view.IView;
import com.github.scaronthesky.eternalwinterwars.view.View;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.AGameBaseEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;
import com.github.scaronthesky.eternalwinterwars.view.managers.SoundManager.MusicType;
import com.github.scaronthesky.eternalwinterwars.view.particles.ParticleSystemBuilder;
import com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene.IEditorScene;
import com.github.scaronthesky.eternalwinterwars.view.util.GameControl;

public class Controller implements IController {
	private MainActivity mainActivity;
	private IOService ioService;
	private BaseGameEntityMapper baseGameEntityMapper;
	private IModel model;
	private IView view;

	public Controller(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.ioService = new IOService(this);
		this.baseGameEntityMapper = new BaseGameEntityMapper(this);
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

	@Override
	public void startTest() {
		new GameControl(this, 2);
		this.getView().getSoundManager().setActualMusicType(MusicType.GAME);
		this.getView().getSoundManager().getActualMusic().play();
		Unit marksmanOne = new Archer(UUID.randomUUID(), "Marksmen_v1.png",
				this.model.getPlayers()[0]);
		this.baseGameEntityMapper.mapUnit(marksmanOne);
		UnitEntity testMarksman = this.baseGameEntityMapper
				.getUnitEntity(marksmanOne);
		testMarksman.setX(this.getView().getCellSideLength() * 10);
		testMarksman.setY(this.getView().getCellSideLength() * 5);
		this.getView().getSceneManager().getGameScene()
				.attachChildOnTop(testMarksman);
		Unit marksmanTwo = new Archer(UUID.randomUUID(), "Marksmen_v1.png",
				this.model.getPlayers()[0]);
		this.baseGameEntityMapper.mapUnit(marksmanTwo);
		UnitEntity testMarksmanTwo = this.baseGameEntityMapper
				.getUnitEntity(marksmanTwo);
		testMarksmanTwo.setX(this.getView().getCellSideLength() * 5);
		testMarksmanTwo.setY(this.getView().getCellSideLength() * 5);
		this.getView().getSceneManager().getGameScene()
				.attachChildOnTop(testMarksmanTwo);
		Unit knight = new Knight(UUID.randomUUID(), "Knight_v1.png",
				this.model.getPlayers()[1]);
		this.baseGameEntityMapper.mapUnit(knight);
		UnitEntity testKnight = this.baseGameEntityMapper.getUnitEntity(knight);
		testKnight.setClickable(false);
		testKnight.setX(this.getView().getCellSideLength() * 10);
		testKnight.setY(this.getView().getCellSideLength() * 3);
		this.getView().getSceneManager().getGameScene()
				.attachChildOnTop(testKnight);
		this.showFogOfWar(this.model.getActivePlayer().getIndex());
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
	public void showFogOfWar(int pPlayerIndex) {
		List<int[]> lVisibleFogRectangles = new LinkedList<int[]>();
		for (Unit lUnit : this.baseGameEntityMapper.getAllUnits(pPlayerIndex)) {
			UnitEntity lUnitEntity = this.baseGameEntityMapper
					.getUnitEntity(lUnit);
			int lColumn = (int) (lUnitEntity.getX() / this.view
					.getCellSideLength());
			int lRow = (int) (lUnitEntity.getY() / this.view
					.getCellSideLength());
			lVisibleFogRectangles.add(new int[] { lColumn - 2, lRow - 1 });
			lVisibleFogRectangles.add(new int[] { lColumn - 2, lRow });
			lVisibleFogRectangles.add(new int[] { lColumn - 2, lRow + 1 });
			lVisibleFogRectangles.add(new int[] { lColumn - 1, lRow - 2 });
			lVisibleFogRectangles.add(new int[] { lColumn - 1, lRow - 1 });
			lVisibleFogRectangles.add(new int[] { lColumn - 1, lRow });
			lVisibleFogRectangles.add(new int[] { lColumn - 1, lRow + 1 });
			lVisibleFogRectangles.add(new int[] { lColumn - 1, lRow + 2 });
			lVisibleFogRectangles.add(new int[] { lColumn, lRow - 2 });
			lVisibleFogRectangles.add(new int[] { lColumn, lRow - 1 });
			lVisibleFogRectangles.add(new int[] { lColumn, lRow });
			lVisibleFogRectangles.add(new int[] { lColumn, lRow + 1 });
			lVisibleFogRectangles.add(new int[] { lColumn, lRow + 2 });
			lVisibleFogRectangles.add(new int[] { lColumn + 1, lRow - 2 });
			lVisibleFogRectangles.add(new int[] { lColumn + 1, lRow - 1 });
			lVisibleFogRectangles.add(new int[] { lColumn + 1, lRow });
			lVisibleFogRectangles.add(new int[] { lColumn + 1, lRow + 1 });
			lVisibleFogRectangles.add(new int[] { lColumn + 1, lRow + 2 });
			lVisibleFogRectangles.add(new int[] { lColumn + 2, lRow - 1 });
			lVisibleFogRectangles.add(new int[] { lColumn + 2, lRow });
			lVisibleFogRectangles.add(new int[] { lColumn + 2, lRow + 1 });
		}
		List<float[]> lVisibleRectanglesAbsoluteCoordinates = this
				.getAbsoluteCoordinates(lVisibleFogRectangles);
		this.view
				.getSceneManager()
				.getGameScene()
				.exploreCells(this.model.getActivePlayer().getIndex(),
						lVisibleRectanglesAbsoluteCoordinates);
		this.view
				.getSceneManager()
				.getGameScene()
				.showFogOfWar(this.model.getActivePlayer().getIndex(),
						lVisibleRectanglesAbsoluteCoordinates);
	}

	@Override
	public boolean handleGameSceneTouch(final TouchEvent pTouchEvent) {
		if (pTouchEvent.isActionDown()) {
			// XXX ANNOYING
			// this.view.getSoundManager().getSounds().get(SoundType.ATTACK)
			// .play();
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
			IEditorScene editorScene = this.view.getSceneManager()
					.getEditorScene();
			EditorControl editorControl = this.model.getEditorControl();
			String key = editorScene.getActualKey();
			int column = (int) (pTouchEvent.getX() / editorScene
					.getSpriteSideLength());
			int row = (int) (pTouchEvent.getY() / editorScene
					.getSpriteSideLength());
			editorControl.setCell(CellBuilder.buildCell(key), column, row);
			editorScene.drawSprite(key, column, row);
		}
		return false;
	}

	@Override
	public void startEditing() {
		this.model.startEditing();
		IEditorScene editorScene = this.view.getSceneManager().getEditorScene();
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

	@Override
	public float getAbsoluteCoordinate(int pColumnOrRow) {
		return this.view.getCellSideLength() * pColumnOrRow;
	}

	@Override
	public int getLogicalCoordinate(float pAbsoluteCoordinate) {
		return (int) (pAbsoluteCoordinate / this.view.getCellSideLength());
	}

	private float[] getAbsoluteCoordinates(int[] pColumnAndRow) {
		float lCellSideLength = this.view.getCellSideLength();
		return new float[] { lCellSideLength * pColumnAndRow[0],
				lCellSideLength * pColumnAndRow[1] };
	}

	private int[] getLogicalCoordinates(float[] pAbsoluteCoordinates) {
		float lCellSideLength = this.view.getCellSideLength();
		return new int[] { (int) (pAbsoluteCoordinates[0] / lCellSideLength),
				(int) (pAbsoluteCoordinates[1] / lCellSideLength) };
	}

	private float[] getAbsoluteCoordinates(int pColumn, int pRow) {
		float lCellSideLength = this.view.getCellSideLength();
		return new float[] { lCellSideLength * pColumn, lCellSideLength * pRow };
	}

	private int[] getLogicalCoordinates(float pX, float pY) {
		float lCellSideLength = this.view.getCellSideLength();
		return new int[] { (int) (pX / lCellSideLength),
				(int) (pY / lCellSideLength) };
	}

	private List<int[]> getLogicalCoordinates(List<float[]> pAbsoluteCoordinates) {
		List<int[]> lLogicalCoordinates = new ArrayList<int[]>();
		for (float[] lAbsoluteCoordinatesSet : pAbsoluteCoordinates) {
			lLogicalCoordinates.add(this
					.getLogicalCoordinates(lAbsoluteCoordinatesSet));
		}
		return lLogicalCoordinates;
	}

	private List<float[]> getAbsoluteCoordinates(List<int[]> pLogicalCoordinates) {
		List<float[]> lAbsoluteCoordinates = new ArrayList<float[]>();
		for (int[] lLogicalCoordinatesSet : pLogicalCoordinates) {
			lAbsoluteCoordinates.add(this
					.getAbsoluteCoordinates(lLogicalCoordinatesSet));
		}
		return lAbsoluteCoordinates;
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

	@Override
	public BaseGameEntityMapper getBaseGameEntityMapper() {
		return this.baseGameEntityMapper;
	}

	@Override
	public void moveUnit(UnitEntity pUnitEntity, float pX, float pY) {
		int lColumn = this.getLogicalCoordinate(pX);
		int lRow = this.getLogicalCoordinate(pY);
		List<int[]> lMovedCells = this.model.moveUnit(
				this.baseGameEntityMapper.getUnit(pUnitEntity), lColumn, lRow);
		this.view.getSceneManager().getGameScene()
				.move(pUnitEntity, this.getAbsoluteCoordinates(lMovedCells));
	}

	@Override
	public void attack(UnitEntity pAttackingUnitEntity,
			AGameBaseEntity pDefendingEntity) {
		FightingEntity lDefendingEntity = pDefendingEntity instanceof BuildingEntity ? this.baseGameEntityMapper
				.getBuilding((BuildingEntity) pDefendingEntity)
				: this.baseGameEntityMapper
						.getUnit((UnitEntity) pDefendingEntity);
		int lDamageDoneToDefendingUnit = this.model.attack(
				this.baseGameEntityMapper.getUnit(pAttackingUnitEntity),
				lDefendingEntity);
		this.view
				.getSceneManager()
				.getGameScene()
				.attack(pAttackingUnitEntity,
						pDefendingEntity,
						lDamageDoneToDefendingUnit,
						lDamageDoneToDefendingUnit >= lDefendingEntity
								.getHealth());
	}

	@Override
	public void changePlayer() {
		// TODO Auto-generated method stub

	}

}
