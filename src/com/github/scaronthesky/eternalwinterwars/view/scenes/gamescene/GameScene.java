package com.github.scaronthesky.eternalwinterwars.view.scenes.gamescene;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.Constants;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Board;
import com.github.scaronthesky.eternalwinterwars.view.entities.board.Mark;
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
		this.setOnSceneTouchListener(this);
	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pTouchEvent) {
		this.getController().handleGameSceneTouch(pTouchEvent);
		return true;
	}

	public void setBoard(Board pBoard) {
		if (pBoard != null) {
			pBoard.detachSelf();
		}
		this.gBoard = pBoard;
		this.attachChild(pBoard);
	}

	public Board getBoard() {
		return this.gBoard;
	}

	@Override
	public void mark(UnitEntity pSource, List<float[]> pStartCoordinates) {
		this.removeMark();
		this.gMark = new Mark(this.getController(), pSource, this.gBoard,
				pStartCoordinates, Color.RED, 0.7f, 0.3f, 1f);
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
		final String lAttackKey = this.getAttackKey(pUnitEntity.getX(),
				pGameBaseEntity.getX(), pUnitEntity.getY(),
				pGameBaseEntity.getY());
		final float lAttackDuration = pUnitEntity.getAnimationProperties(
				lAttackKey).getAccumulatedDuration();
		DelayModifier lDelayModifier = new DelayModifier(lAttackDuration,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						pUnitEntity.animate(lAttackKey);
						if (pUnitEntity.hasRangeSprite()) {
							GameScene.this.attachChild(pUnitEntity
									.getRangeSprite());
							pUnitEntity
									.getRangeSprite()
									.registerEntityModifier(
											new MoveModifier(
													lAttackDuration,
													pUnitEntity
															.getRangeSprite()
															.getX(),
													pGameBaseEntity.getX()
															+ pGameBaseEntity
																	.getWidth()
															/ 2
															- pUnitEntity
																	.getRangeSprite()
																	.getWidth()
															/ 2,
													pUnitEntity
															.getRangeSprite()
															.getY(),
													pGameBaseEntity.getY()
															+ pGameBaseEntity
																	.getHeight()
															/ 2
															- pUnitEntity
																	.getRangeSprite()
																	.getHeight()
															/ 2));
						}
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

	public void attachChildOnMidLayer(IEntity pEntity) {
		this.attachChildOnLayer(pEntity, 2);
	}

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

	}
}
