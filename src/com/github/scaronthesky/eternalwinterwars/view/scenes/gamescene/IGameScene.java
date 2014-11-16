package com.github.scaronthesky.eternalwinterwars.view.scenes.gamescene;

import java.util.List;

import com.github.scaronthesky.eternalwinterwars.view.entities.board.Mark;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.AGameBaseEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.hud.gamehud.CoinEntity;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public interface IGameScene {

	/**
	 * Changes the active player, shows animations etc.
	 * 
	 * @param pPlayerIndex
	 */
	public void changePlayer(int pPlayerIndex);

	/**
	 * Marks an area of Cells
	 * 
	 * @param pSource
	 *            {@link UnitEntity} which is clicked
	 * @param pStartCoordinates
	 *            cells to mark (pX/pY left upper corner)
	 */
	public void mark(UnitEntity pSource, List<float[]> pStartCoordinates);

	/**
	 * Removes the {@link Mark}
	 */
	public void removeMark();

	/**
	 * Moves a unit on the screen
	 * 
	 * @param pUnit
	 *            target Unit
	 * @param pTargetCoordinates
	 *            target coordinates
	 */
	public void move(UnitEntity pUnit, List<float[]> pTargetCoordinates);

	/**
	 * Starts an attack
	 * 
	 * @param pUnit
	 *            attacking {@link UnitEntity}
	 * @param pGameBaseEntity
	 *            attacked {@link UnitEntity} or {@link BuildingEntity}
	 * @param pDamageDone
	 *            damage done to the defending unit
	 * @param pAttackedUnitOrBuildingKilled
	 *            true = the attacked Unit or Building has to be removed after
	 *            the attack
	 */
	public void attack(UnitEntity pUnitEntity, AGameBaseEntity pGameBaseEntity,
			int pDamageDone, boolean pAttackedUnitOrBuildingKilled);

	/**
	 * Creates a Unit on the screen
	 * 
	 * @param pUnit
	 *            Unit to create
	 * @param pX
	 *            the Units X
	 * @param pY
	 *            the Units Y
	 */
	public void create(UnitEntity pUnit, float pX, float pY);

	/**
	 * Adds income to the actual Player's income-TextField
	 * 
	 * @param pAppearCoordinates
	 *            source locations for coins
	 * @param pNewCoinCount
	 *            new amount of coins to write to the {@link CoinEntity}
	 */
	public void addIncome(List<float[]> pAppearCoordinates, int pNewCoinCount);

	/**
	 * f.e. shows a start animation
	 */
	public void start();

	/**
	 * f.e. shows a finishing animation
	 */
	public void finish();

	/**
	 * Shows a dialogue, which allows the selection of attack or cancel after
	 * unit-movement
	 * 
	 * @param pSourceUnitEntity
	 *            {@link UnitEntity} - Source
	 */
	public void showAttackOrCancelDialogue(UnitEntity pSourceUnitEntity);
}
