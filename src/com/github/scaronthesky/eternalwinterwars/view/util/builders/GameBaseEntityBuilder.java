package com.github.scaronthesky.eternalwinterwars.view.util.builders;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.constants.Constants;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;

public abstract class GameBaseEntityBuilder {

	public static UnitEntity createKnight(IController pController,
			float pCellSideLength, Color pForegroundRect, Color pBackgroundRect) {
		return new UnitEntity(pController, pController.getView()
				.getSceneManager().getGameScene(), pCellSideLength,
				pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_KNIGHT_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_KNIGHT_HEALTH, pForegroundRect,
				pBackgroundRect, true, pController.getView()
						.getAnimationPropertiesManager()
						.getAnimationPropertiesKnight(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
	}

	public static UnitEntity createMarksman(IController pController,
			float pCellSideLength, Color pForegroundRect, Color pBackgroundRect) {
		UnitEntity lMarksman = new UnitEntity(pController, pController
				.getView().getSceneManager().getGameScene(), pCellSideLength,
				pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_MARKSMAN_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_MARKSMAN_HEALTH,
				pForegroundRect, pBackgroundRect, true, pController.getView()
						.getAnimationPropertiesManager()
						.getAnimationPropertiesMarksman(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
		lMarksman.setRangeSprite(new Sprite(0, 0, pCellSideLength,
				pCellSideLength, pController.getView().getResourceManager()
						.getTextureRegionArrow(), pController.getMainActivity()
						.getVertexBufferObjectManager()));
		return lMarksman;
	}

	public static UnitEntity createArtillery(IController pController,
			float pCellSideLength, Color pForegroundRect, Color pBackgroundRect) {
		UnitEntity lArtillery = new UnitEntity(pController, pController
				.getView().getSceneManager().getGameScene(), pCellSideLength,
				pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_ARTILLERY_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_ARTILLERY_HEALTH,
				pForegroundRect, pBackgroundRect, true, pController.getView()
						.getAnimationPropertiesManager()
						.getAnimationPropertiesArtillery(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
		lArtillery.setRangeSprite(new Sprite(0, 0, pCellSideLength,
				pCellSideLength, pController.getView().getResourceManager()
						.getTextureRegionStone(), pController.getMainActivity()
						.getVertexBufferObjectManager()));
		return lArtillery;
	}

	public static UnitEntity createCavallery(IController pController,
			float pCellSideLength, Color pForegroundRect, Color pBackgroundRect) {
		return new UnitEntity(pController, pController.getView()
				.getSceneManager().getGameScene(), pCellSideLength,
				pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_UNIT_CAVALLERY_HEALTH,
				Constants.GAME_BASE_ENTITY_UNIT_CAVALLERY_HEALTH,
				pForegroundRect, pBackgroundRect, true, pController.getView()
						.getAnimationPropertiesManager()
						.getAnimationPropertiesCavallery(),
				Constants.ANIMATION_KEY_MOVE_DOWN, pCellSideLength);
	}

	public static BuildingEntity createCastleEntity(IController pController,
			float pCellSideLength, Color pForegroundRect,
			Color pBackgroundRect, ITextureRegion pTextureRegion) {
		return new BuildingEntity(pController, pController.getView()
				.getSceneManager().getGameScene(), pCellSideLength,
				pCellSideLength
						* Constants.GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC,
				Constants.GAME_BASE_ENTITY_BUILDING_CASTLE_HEALTH,
				Constants.GAME_BASE_ENTITY_BUILDING_CASTLE_HEALTH,
				pForegroundRect, pBackgroundRect, true, pTextureRegion,
				pCellSideLength);
	}
}
