package com.github.scaronthesky.eternalwinterwars.view.managers;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.Constants;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;

public class GameBaseEntityManager extends AManager {

	public GameBaseEntityManager(IController pController) {
		super(pController);
	}

	@Override
	public void preLoad() {
		// Do nothing
	}

	@Override
	public void load() {
		// Do nothing
	}

	public UnitEntity createKnight(float pCellSideLength,
			Color pForegroundRect, Color pBackgroundRect) {
		return new UnitEntity(this.getController(), this.getController()
				.getView().getSceneManager().getGameScene().getInstance(),
				pCellSideLength, pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_KNIGHT_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_KNIGHT_HEALTH, pForegroundRect,
				pBackgroundRect, true, this.getController().getView()
						.getAnimationPropertiesManager()
						.getAnimationPropertiesKnight(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
	}

	public UnitEntity createMarksman(float pCellSideLength,
			Color pForegroundRect, Color pBackgroundRect) {
		UnitEntity lMarksman = new UnitEntity(this.getController(), this
				.getController().getView().getSceneManager().getGameScene()
				.getInstance(), pCellSideLength, pCellSideLength
				* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_MARKSMAN_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_MARKSMAN_HEALTH,
				pForegroundRect, pBackgroundRect, true, this.getController()
						.getView().getAnimationPropertiesManager()
						.getAnimationPropertiesMarksman(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
		lMarksman.setRangeSprite(new Sprite(0, 0, pCellSideLength,
				pCellSideLength, this.getController().getView()
						.getResourceManager().getTextureRegionArrow(), this
						.getController().getMainActivity()
						.getVertexBufferObjectManager()));
		return lMarksman;
	}

	public UnitEntity createArtillery(float pCellSideLength,
			Color pForegroundRect, Color pBackgroundRect) {
		UnitEntity lArtillery = new UnitEntity(this.getController(), this
				.getController().getView().getSceneManager().getGameScene()
				.getInstance(), pCellSideLength, pCellSideLength
				* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_ARTILLERY_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_ARTILLERY_HEALTH,
				pForegroundRect, pBackgroundRect, true, this.getController()
						.getView().getAnimationPropertiesManager()
						.getAnimationPropertiesArtillery(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
		lArtillery.setRangeSprite(new Sprite(0, 0, pCellSideLength,
				pCellSideLength, this.getController().getView()
						.getResourceManager().getTextureRegionStone(), this
						.getController().getMainActivity()
						.getVertexBufferObjectManager()));
		return lArtillery;
	}

	public UnitEntity createCavallery(float pCellSideLength,
			Color pForegroundRect, Color pBackgroundRect) {
		return new UnitEntity(this.getController(), this.getController()
				.getView().getSceneManager().getGameScene().getInstance(),
				pCellSideLength, pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_CAVALLERY_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_CAVALLERY_HEALTH,
				pForegroundRect, pBackgroundRect, true, this.getController()
						.getView().getAnimationPropertiesManager()
						.getAnimationPropertiesCavallery(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
	}

	public BuildingEntity createCastleEntity(float pCellSideLength,
			Color pForegroundRect, Color pBackgroundRect,
			ITextureRegion pTextureRegion) {
		return new BuildingEntity(this.getController(), this.getController()
				.getView().getSceneManager().getGameScene().getInstance(),
				pCellSideLength, pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_BUILDING_CASTLE_HEALTH,
				Constants.GAME_BASE_ENTITY_BUILDING_CASTLE_HEALTH,
				pForegroundRect, pBackgroundRect, true, pTextureRegion,
				pCellSideLength);
	}
}
