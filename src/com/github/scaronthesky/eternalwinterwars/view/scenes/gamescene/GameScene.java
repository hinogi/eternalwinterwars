package com.github.scaronthesky.eternalwinterwars.view.scenes.gamescene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

import android.graphics.Point;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.constants.Constants;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Board;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.FogOfWar;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Mark;
import com.github.scaronthesky.eternalwinterwars.view.entities.dialogues.AttackOrCancelDialogue;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.AGameBaseEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.hud.gamehud.GameHUD;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.IncomeAddEffect;
import com.github.scaronthesky.eternalwinterwars.view.scenes.AControllerScene;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class GameScene extends AControllerScene implements
		IOnSceneTouchListener, IGameScene {
	private Board gBoard;
	private Mark gMark;
	private FogOfWar gFogOfWar;
	private boolean gLocked;
	private AttackOrCancelDialogue gAttackOrCancelDialogue;
	private Map<Integer, List<float[]>> gExploredBoardCells;

	/**
	 * Creates an instance of {@link GameScene}
	 * 
	 * @param pController
	 *            {@link Controller} reference
	 */
	public GameScene(IController pController) {
		super(pController);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.mmssb.aweproject.scenes.ModelScene#initialize()
	 */
	@Override
	public void initialize() {
		this.gExploredBoardCells = new HashMap<Integer, List<float[]>>();
		this.gFogOfWar = new FogOfWar(this.getController());
		this.attachChildOnLayer(this.gFogOfWar, 4);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setOnSceneTouchListener(this);
	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pTouchEvent) {
		this.getController().handleGameSceneTouch(pTouchEvent);
		return true;
	}

	@Override
	public void setBoard(Board pBoard) {
		if (pBoard != null) {
			pBoard.detachSelf();
		}
		this.gBoard = pBoard;
		this.attachChild(pBoard);
	}

	@Override
	public Board getBoard() {
		return this.gBoard;
	}

	@Override
	public void markCells(UnitEntity pSource,
			List<float[]> pCellsToMarkCoordinates, Color pMarkColor) {
		this.removeMark();
		this.gMark = new Mark(this.getController(), pSource, this.gBoard,
				pCellsToMarkCoordinates, Color.RED, 0.7f, 0.3f, 1f);
		this.attachChild(this.gMark);
		this.registerTouchArea(this.gMark);
	}

	@Override
	public void removeMark() {
		if (this.gMark != null) {
			this.unregisterTouchArea(this.gMark);
			this.detachChild(this.gMark);
		}
	}

	@Override
	public void addIncome(List<float[]> pAppearCoordinatesOnGameScene,
			int pNewCoinCount) {
		GameHUD lGameHUD = this.getController().getView().getHUDManager()
				.getGameHUD();
		List<float[]> lAppearCoordinatesOnGameHUD = this
				.convertSceneToHUDCoordinates(pAppearCoordinatesOnGameScene);
		lGameHUD.registerEntityModifier(new IncomeAddEffect(this
				.getController(), lGameHUD.getCoinEntity().getX()
				+ lGameHUD.getCoinEntity().getSpriteCoin().getX(), lGameHUD
				.getCoinEntity().getY()
				+ lGameHUD.getCoinEntity().getSpriteCoin().getY(), lGameHUD,
				Constants.INCOME_MOVE_TO_DEST_DURATION, this.getController()
						.getView().getResourceManager()
						.getTextureRegionCoinEntity(), this.getController()
						.getMainActivity().getVertexBufferObjectManager(),
				lAppearCoordinatesOnGameHUD, lGameHUD.getCoinEntity()
						.getSpriteCoin().getWidth(), lGameHUD.getCoinEntity()
						.getSpriteCoin().getHeight(), pNewCoinCount));
	}

	private List<float[]> convertSceneToHUDCoordinates(
			List<float[]> pCoordinatesScene) {
		List<float[]> lCoordinatesHUD = new ArrayList<float[]>();
		for (float[] lCoordinateSetScene : pCoordinatesScene) {
			lCoordinatesHUD.add(this
					.getController()
					.getMainActivity()
					.getSmoothCamera()
					.getCameraSceneCoordinatesFromSceneCoordinates(
							lCoordinateSetScene));
		}
		return lCoordinatesHUD;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(final UnitEntity pUnitEntity,
			final AGameBaseEntity pGameBaseEntity, final int pDamageDone,
			final boolean pAttackedUnitOrBuildingKilled) {
		if (!this.gLocked) {
			final String lAttackKey = this.getAttackKey(pUnitEntity.getX(),
					pGameBaseEntity.getX(), pUnitEntity.getY(),
					pGameBaseEntity.getY());
			final float lAttackDuration = pUnitEntity.getAnimationProperties(
					lAttackKey).getAccumulatedDuration() / 1000;
			DelayModifier lDelayModifier = new DelayModifier(lAttackDuration,
					new IEntityModifierListener() {

						@Override
						public void onModifierStarted(
								IModifier<IEntity> pModifier, IEntity pItem) {
							GameScene.this.gLocked = true;
							pUnitEntity.animate(lAttackKey);
						}

						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							if (pUnitEntity.hasRangeSprite()) {
								GameScene.this.finishRangedAttack(pUnitEntity,
										pGameBaseEntity, pDamageDone,
										pAttackedUnitOrBuildingKilled);
							} else {
								GameScene.this.finishAttack(pUnitEntity,
										pGameBaseEntity, pDamageDone,
										pAttackedUnitOrBuildingKilled);
							}
						}
					});
			this.registerEntityModifier(lDelayModifier);
		}
	}

	private void finishRangedAttack(final UnitEntity pUnitEntity,
			final AGameBaseEntity pGameBaseEntity, final int pDamageDone,
			final boolean pAttackedUnitOrBuildingKilled) {
		final float lRangedSpriteFlyDuration = (Math.abs(pUnitEntity.getX()
				- pGameBaseEntity.getX()) + Math.abs(pUnitEntity.getY()
				- pGameBaseEntity.getY()))
				* Constants.RANGED_ATTACK_FLY_DURATION_PER_PIXEL;
		DelayModifier lDelayModifier = new DelayModifier(
				lRangedSpriteFlyDuration, new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						Sprite lRangeSprite = pUnitEntity.getRangeSprite();
						lRangeSprite.setX(pUnitEntity.getX());
						lRangeSprite.setY(pUnitEntity.getY());
						lRangeSprite.setRotation(GameScene.this.getRangedSpriteAngle(
								new Point(
										(int) (pGameBaseEntity.getX() + pGameBaseEntity
												.getWidth() / 2),
										(int) (pGameBaseEntity.getY() + pGameBaseEntity
												.getHeight() / 2)),
								new Point(
										(int) (lRangeSprite.getX() + lRangeSprite
												.getWidth() / 2),
										(int) (lRangeSprite.getY() + lRangeSprite
												.getHeight() / 2))));
						MoveModifier lMoveModifier = new MoveModifier(
								lRangedSpriteFlyDuration, lRangeSprite.getX(),
								pGameBaseEntity.getX(), lRangeSprite.getY(),
								pGameBaseEntity.getY());
						GameScene.this.attachChildOnMidLayer(lRangeSprite);
						lRangeSprite.registerEntityModifier(lMoveModifier);
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						GameScene.this.finishAttack(pUnitEntity,
								pGameBaseEntity, pDamageDone,
								pAttackedUnitOrBuildingKilled);
					}
				});
		this.registerEntityModifier(lDelayModifier);
	}

	private float getRangedSpriteAngle(Point target, Point source) {
		float angle = (float) Math.toDegrees(Math.atan2(target.y - source.y,
				target.x - source.x));
		if (angle < 0) {
			angle += 360;
		}

		return angle;
	}

	private void showDamageText(final float pSourceX, final float pSourceY,
			final float pDestX, final float pDestY, final int pDamageDone) {
		DelayModifier lDelayModifier = new DelayModifier(
				AGameBaseEntity.SUFFER_DURATION, new IEntityModifierListener() {
					private Text iText;

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						this.iText = new Text(pSourceX, pSourceY,
								GameScene.this.getController().getView()
										.getResourceManager().getFontDamage(),
								String.valueOf(pDamageDone), GameScene.this
										.getController().getMainActivity()
										.getVertexBufferObjectManager());
						GameScene.this.attachChild(this.iText);
						this.iText.registerEntityModifier(new MoveModifier(
								Constants.DAMAGE_MOVE_TO_DEST_DURATION,
								this.iText.getX(), pDestX, this.iText.getY(),
								pDestY));
						this.iText.registerEntityModifier(new AlphaModifier(
								Constants.DAMAGE_MOVE_TO_DEST_DURATION,
								this.iText.getAlpha(), 0));
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						GameScene.this.detachChild(this.iText);
					}
				});
		this.registerEntityModifier(lDelayModifier);
	}

	private void finishAttack(final UnitEntity pUnitEntity,
			final AGameBaseEntity pGameBaseEntity, final int pDamageDone,
			final boolean pAttackedUnitOrBuildingKilled) {
		if (pUnitEntity.hasRangeSprite()) {
			this.detachChild(pUnitEntity.getRangeSprite());
		}
		DelayModifier lDelayModifier = new DelayModifier(
				AGameBaseEntity.SUFFER_DURATION, new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						pGameBaseEntity.sufferFromAttack();
						GameScene.this.showDamageText(
								pGameBaseEntity.getX(),
								pGameBaseEntity.getY(),
								pGameBaseEntity.getX(),
								pGameBaseEntity.getY()
										- pGameBaseEntity.getHeight(),
								pDamageDone);
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						if (pAttackedUnitOrBuildingKilled) {
							GameScene.this.detachChild(pGameBaseEntity);
						}
						GameScene.this.gLocked = false;
					}
				});
		this.registerEntityModifier(lDelayModifier);
	}

	@Override
	public void create(UnitEntity pUnit, float pX, float pY) {
		pUnit.setX(pX);
		pUnit.setY(pY);
		pUnit.setAlpha(0);
		this.attachChild(pUnit);
		pUnit.registerEntityModifier(new AlphaModifier(
				Constants.UNIT_CREATE_DURATION, pUnit.getAlpha(), 1));
	}

	@Override
	public void move(final UnitEntity pUnit, List<float[]> pTargetCoordinates) {
		for (final float[] lTargetCoordinateSet : pTargetCoordinates) {
			DelayModifier lDelayModifier = new DelayModifier(
					Constants.UNIT_MOVE_DURATION_PER_CELL,
					new IEntityModifierListener() {

						@Override
						public void onModifierStarted(
								IModifier<IEntity> pModifier, IEntity pItem) {
							pUnit.registerEntityModifier(new MoveModifier(
									Constants.UNIT_MOVE_DURATION_PER_CELL,
									pUnit.getX(), lTargetCoordinateSet[0],
									pUnit.getY(), lTargetCoordinateSet[1]));
							pUnit.animate(GameScene.this.getMoveKey(
									pUnit.getX(), lTargetCoordinateSet[0],
									pUnit.getY(), lTargetCoordinateSet[1]));
						}

						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							pUnit.animate(Constants.ANIMATION_KEY_IDLE);
							pUnit.setMarked(false);
							GameScene.this.getController().showFogOfWar(
									GameScene.this.getController()
											.getBaseGameEntityMapper()
											.getUnit(pUnit).getOwner()
											.getIndex());
							pUnit.setClickable(true);
						}
					});
			this.registerEntityModifier(lDelayModifier);
		}
		this.removeMark();
	}

	private String getAttackKey(float pOldX, float pNewX, float pOldY,
			float pNewY) {
		if (pNewX > pOldX) {
			return Constants.ANIMATION_KEY_ATTACK_RIGHT;
		} else if (pNewX < pOldX) {
			return Constants.ANIMATION_KEY_ATTACK_LEFT;
		} else {
			if (pNewY > pOldY) {
				return Constants.ANIMATION_KEY_ATTACK_DOWN;
			} else if (pNewY < pOldY) {
				return Constants.ANIMATION_KEY_ATTACK_UP;
			}
			return null;
		}
	}

	private String getMoveKey(float pOldX, float pNewX, float pOldY, float pNewY) {
		if (pNewX > pOldX) {
			return Constants.ANIMATION_KEY_MOVE_RIGHT;
		} else if (pNewX < pOldX) {
			return Constants.ANIMATION_KEY_MOVE_LEFT;
		} else {
			if (pNewY > pOldY) {
				return Constants.ANIMATION_KEY_MOVE_DOWN;
			} else if (pNewY < pOldY) {
				return Constants.ANIMATION_KEY_MOVE_UP;
			}
			return null;
		}
	}

	@Override
	public void attachChild(IEntity pEntity) throws IllegalStateException {
		this.attachChildOnLayer(pEntity, 1);
	}

	@Override
	public void attachChildOnMidLayer(IEntity pEntity) {
		this.attachChildOnLayer(pEntity, 2);
	}

	@Override
	public void attachChildOnTop(IEntity pEntity) {
		this.attachChildOnLayer(pEntity, 3);
	}

	private void attachChildOnLayer(IEntity pEntity, int pZIndex) {
		super.attachChild(pEntity);
		pEntity.setZIndex(pZIndex);
		this.sortChildren();
	}

	@Override
	public void changePlayer(int pPlayerIndex) {
		this.getController().showFogOfWar(pPlayerIndex);
		this.showAllEntitiesOfPlayer(pPlayerIndex);
	}

	@Override
	public void showAttackOrCancelDialogue(UnitEntity pSourceUnitEntity) {
		if (this.gAttackOrCancelDialogue != null) {
			this.detachChild(this.gAttackOrCancelDialogue);
		}
		float lButtonSideLength = pSourceUnitEntity
				.getMultiTextureAnimatedSprite().getWidth();
		this.gAttackOrCancelDialogue = new AttackOrCancelDialogue(
				this.getController(), this, pSourceUnitEntity,
				lButtonSideLength, lButtonSideLength);
		float lStartX = pSourceUnitEntity.getX();
		float lLeftStartX = lStartX - lButtonSideLength;
		float lRightStartX = lStartX + lButtonSideLength;
		float lCameraCenterX = this.getController().getMainActivity()
				.getSmoothCamera().getCenterX();
		if (Math.abs(lCameraCenterX - lLeftStartX) < Math.abs(lCameraCenterX
				- lRightStartX)) {
			this.gAttackOrCancelDialogue.setX(lLeftStartX);
		} else {
			this.gAttackOrCancelDialogue.setX(lRightStartX);
		}
		this.gAttackOrCancelDialogue.setY(pSourceUnitEntity.getY()
				- lButtonSideLength / 2);
		this.attachChildOnTop(this.gAttackOrCancelDialogue);
	}

	@Override
	public boolean isLocked() {
		return this.gLocked;
	}

	@Override
	public void hideFogOfWar() {
		if (this.gFogOfWar.isVisible()) {
			this.gFogOfWar.setVisible(false);
		}
	}

	@Override
	public Scene getInstance() {
		return this;
	}

	@Override
	public void showFogOfWar(int pPlayerIndex,
			List<float[]> pVisibleRectanglesCoordinates) {
		this.gFogOfWar.enableFog();
		for (float[] lVisibleFogRectangle : pVisibleRectanglesCoordinates) {
			this.gFogOfWar.disableFog(
					this.getController().getLogicalCoordinate(
							lVisibleFogRectangle[0]),
					this.getController().getLogicalCoordinate(
							lVisibleFogRectangle[1]));
		}
		for (float[] lExploredRectangle : this.gExploredBoardCells
				.get(pPlayerIndex)) {
			this.gFogOfWar.lightenFog(this.getController()
					.getLogicalCoordinate(lExploredRectangle[0]), this
					.getController()
					.getLogicalCoordinate(lExploredRectangle[1]));
		}
		this.hideUnitEntitiesInFog(pPlayerIndex, pVisibleRectanglesCoordinates);
		if (!this.gFogOfWar.isVisible()) {
			this.gFogOfWar.setVisible(true);
		}
	}

	private void showAllEntitiesOfPlayer(int pPlayerIndex) {
		for (UnitEntity lUnitEntity : this.getController()
				.getBaseGameEntityMapper().getAllUnitEntities(pPlayerIndex)) {
			if (this.isExplored(pPlayerIndex, lUnitEntity.getX(),
					lUnitEntity.getY())) {
				if (!lUnitEntity.isVisible()) {
					lUnitEntity.setVisible(true);
				}
			}
		}
	}

	private boolean isExplored(int pPlayerIndex, float pX, float pY) {
		if (this.gExploredBoardCells != null
				&& this.gExploredBoardCells.containsKey(pPlayerIndex)) {
			for (float[] lExploredCellCoordinates : this.gExploredBoardCells
					.get(pPlayerIndex)) {
				if (pX == lExploredCellCoordinates[0]
						&& pY == lExploredCellCoordinates[1]) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * XXX
	 * 
	 * @param pVisibleRectanglesCoordinates
	 */
	private void hideUnitEntitiesInFog(int pActivePlayerIndex,
			List<float[]> pVisibleRectanglesCoordinates) {
		for (int lPlayerIndex = 0; lPlayerIndex < this.getController()
				.getModel().getPlayers().length; lPlayerIndex++) {
			if (lPlayerIndex != pActivePlayerIndex) {
				List<UnitEntity> lPlayerEntities = this.getController()
						.getBaseGameEntityMapper()
						.getAllUnitEntities(lPlayerIndex);
				for (UnitEntity lUnitEntity : lPlayerEntities) {
					if (this.isExplored(pActivePlayerIndex, lUnitEntity.getX(),
							lUnitEntity.getY())) {
						if (this.isInsideFog(lUnitEntity,
								pVisibleRectanglesCoordinates)) {
							if (lUnitEntity.isVisible()) {
								lUnitEntity.setVisible(false);
							}
						} else {
							if (!lUnitEntity.isVisible()) {
								lUnitEntity.setVisible(true);
							}
						}
					}
				}
			}
		}
	}

	private boolean isInsideFog(AGameBaseEntity pGameBaseEntity,
			List<float[]> pVisibleRectanglesCoordinates) {
		for (float[] pVisibleRectangleCoordinatesSet : pVisibleRectanglesCoordinates) {
			if (pGameBaseEntity.getX() == pVisibleRectangleCoordinatesSet[0]
					&& pGameBaseEntity.getY() == pVisibleRectangleCoordinatesSet[1]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void exploreCell(int pPlayerIndex, float pX, float pY) {
		if (!this.gExploredBoardCells.containsKey(pPlayerIndex)) {
			this.gExploredBoardCells.put(pPlayerIndex,
					new LinkedList<float[]>());
		}
		if (!this.isExplored(pPlayerIndex, pX, pY)
				&& this.isValidStartCoordinate(pX, pY)) {
			this.gExploredBoardCells.get(pPlayerIndex).add(
					new float[] { pX, pY });
		}
	}

	private boolean isValidStartCoordinate(float pX, float pY) {
		float lCellSideLength = this.getController().getView()
				.getCellSideLength();
		return pX >= 0 && pY >= 0
				&& pX + lCellSideLength <= this.getBoard().getWidth()
				&& pY + lCellSideLength <= this.getBoard().getHeight();
	}

	@Override
	public void exploreCells(int pPlayerIndex,
			List<float[]> pVisibleFogRectanglesAbsoluteCoordinates) {
		for (float[] lVisibleRectangleAbsoluteCoordinatesSet : pVisibleFogRectanglesAbsoluteCoordinates) {
			this.exploreCell(pPlayerIndex,
					lVisibleRectangleAbsoluteCoordinatesSet[0],
					lVisibleRectangleAbsoluteCoordinatesSet[1]);
		}
	}
}
