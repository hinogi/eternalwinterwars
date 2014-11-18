package com.github.scaronthesky.eternalwinterwars.controller.mapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.andengine.util.color.Color;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.controller.constants.Constants;
import com.github.scaronthesky.eternalwinterwars.model.buildings.Building;
import com.github.scaronthesky.eternalwinterwars.model.buildings.Castle;
import com.github.scaronthesky.eternalwinterwars.model.players.Player;
import com.github.scaronthesky.eternalwinterwars.model.units.Archer;
import com.github.scaronthesky.eternalwinterwars.model.units.Catapult;
import com.github.scaronthesky.eternalwinterwars.model.units.Cavalry;
import com.github.scaronthesky.eternalwinterwars.model.units.Knight;
import com.github.scaronthesky.eternalwinterwars.model.units.Unit;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.BuildingEntity;
import com.github.scaronthesky.eternalwinterwars.view.entities.game.UnitEntity;
import com.github.scaronthesky.eternalwinterwars.view.util.builders.GameBaseEntityBuilder;

public class BaseGameEntityMapper {
	private IController gController;
	private Map<Integer, Map<Unit, UnitEntity>> gUnitMap;
	private Map<Integer, Map<Building, BuildingEntity>> gBuildingMap;

	public BaseGameEntityMapper(IController pController) {
		this.gController = pController;
		this.gUnitMap = new HashMap<Integer, Map<Unit, UnitEntity>>();
		this.gBuildingMap = new HashMap<Integer, Map<Building, BuildingEntity>>();
	}

	public List<Unit> getAllUnits(Player pOwner) {
		return this.getAllUnits(pOwner.getIndex());
	}

	public List<Unit> getAllUnits(int pPlayerIndex) {
		List<Unit> lAllUnits = new LinkedList<Unit>();
		if (this.gUnitMap.containsKey(pPlayerIndex)) {
			for (Unit lUnit : this.gUnitMap.get(pPlayerIndex).keySet()) {
				lAllUnits.add(lUnit);
			}
		}
		return lAllUnits;
	}

	public List<Building> getAllBuildings(Player pOwner) {
		return this.getAllBuildings(pOwner.getIndex());
	}

	public List<Building> getAllBuildings(int pPlayerIndex) {
		List<Building> lAllBuildings = new LinkedList<Building>();
		if (this.gBuildingMap.containsKey(pPlayerIndex)) {
			for (Building lBuilding : this.gBuildingMap.get(pPlayerIndex)
					.keySet()) {
				lAllBuildings.add(lBuilding);
			}
		}
		return lAllBuildings;
	}

	public void mapUnit(Unit pUnit) {
		this.mapUnit(pUnit.getOwner().getIndex(), pUnit);
	}

	public void mapUnit(int pPlayerIndex, Unit pUnit) {
		if (pUnit instanceof Knight) {
			this.mapUnit(pPlayerIndex, pUnit, GameBaseEntityBuilder
					.createKnight(this.gController, this.gController.getView()
							.getCellSideLength(),
							Constants.PLAYER_COLORS[pPlayerIndex], Color.WHITE));
		} else if (pUnit instanceof Archer) {
			this.mapUnit(pPlayerIndex, pUnit, GameBaseEntityBuilder
					.createMarksman(this.gController, this.gController
							.getView().getCellSideLength(),
							Constants.PLAYER_COLORS[pPlayerIndex], Color.WHITE));
		} else if (pUnit instanceof Catapult) {
			this.mapUnit(pPlayerIndex, pUnit, GameBaseEntityBuilder
					.createArtillery(this.gController, this.gController
							.getView().getCellSideLength(),
							Constants.PLAYER_COLORS[pPlayerIndex], Color.WHITE));
		} else if (pUnit instanceof Cavalry) {
			this.mapUnit(pPlayerIndex, pUnit, GameBaseEntityBuilder
					.createCavallery(this.gController, this.gController
							.getView().getCellSideLength(),
							Constants.PLAYER_COLORS[pPlayerIndex], Color.WHITE));
		}
	}

	public void mapUnit(int pPlayerIndex, Unit pUnit, UnitEntity pUnitEntity) {
		if (!this.gUnitMap.containsKey(pPlayerIndex)) {
			this.gUnitMap.put(pPlayerIndex, new HashMap<Unit, UnitEntity>());
		}
		this.gUnitMap.get(pPlayerIndex).put(pUnit, pUnitEntity);
	}

	public void mapBuilding(Building pBuilding) {
		this.mapBuilding(pBuilding.getOwner().getIndex(), pBuilding);
	}

	public void mapBuilding(int pPlayerIndex, Building pBuilding) {
		if (pBuilding instanceof Castle) {
			this.mapBuilding(pPlayerIndex, pBuilding, GameBaseEntityBuilder
					.createCastleEntity(this.gController, this.gController
							.getView().getCellSideLength(),
							Constants.PLAYER_COLORS[pPlayerIndex], Color.WHITE,
							this.gController.getView().getResourceManager()
									.getTextureRegionCastle(pPlayerIndex)));
		}
	}

	public void mapBuilding(int pPlayerIndex, Building pBuilding,
			BuildingEntity pBuildingEntity) {
		if (!this.gBuildingMap.containsKey(pPlayerIndex)) {
			this.gBuildingMap.put(pPlayerIndex,
					new HashMap<Building, BuildingEntity>());
		}
		this.gBuildingMap.get(pPlayerIndex).put(pBuilding, pBuildingEntity);
	}

	public Unit getUnit(UnitEntity pUnitEntity) {
		Unit lUnit = null;
		for (int lPlayerIndex : this.gUnitMap.keySet()) {
			if (lUnit == null) {
				lUnit = this.getUnit(lPlayerIndex, pUnitEntity);
			} else {
				break;
			}
		}
		return lUnit;
	}

	public Unit getUnit(Player pOwner, UnitEntity pUnitEntity) {
		return this.getUnit(pOwner.getIndex(), pUnitEntity);
	}

	public Unit getUnit(int pPlayerIndex, UnitEntity pUnitEntity) {
		for (Unit lUnit : this.gUnitMap.get(pPlayerIndex).keySet()) {
			if (this.gUnitMap.get(pPlayerIndex).get(lUnit) == pUnitEntity) {
				return lUnit;
			}
		}
		return null;
	}

	public UnitEntity getUnitEntity(Unit pUnit) {
		return this.getUnitEntity(pUnit.getOwner().getIndex(), pUnit);
	}

	public UnitEntity getUnitEntity(int pPlayerIndex, Unit pUnit) {
		return this.gUnitMap.get(pPlayerIndex).get(pUnit);
	}

	public Building getBuilding(BuildingEntity pBuildingEntity) {
		Building lBuilding = null;
		for (int lPlayerIndex : this.gBuildingMap.keySet()) {
			if (lBuilding == null) {
				lBuilding = this.getBuilding(lPlayerIndex, pBuildingEntity);
			} else {
				break;
			}
		}
		return lBuilding;
	}

	public Building getBuilding(Player pOwner, BuildingEntity pBuildingEntity) {
		return this.getBuilding(pOwner.getIndex(), pBuildingEntity);
	}

	public Building getBuilding(int pPlayerIndex, BuildingEntity pBuildingEntity) {
		for (Building lBuilding : this.gBuildingMap.get(pPlayerIndex).keySet()) {
			if (this.gBuildingMap.get(pPlayerIndex).get(lBuilding) == pBuildingEntity) {
				return lBuilding;
			}
		}
		return null;
	}

	public BuildingEntity getBuildingEntity(Building pBuilding) {
		return this.getBuildingEntity(pBuilding.getOwner().getIndex(),
				pBuilding);
	}

	public BuildingEntity getBuildingEntity(int pPlayerIndex, Building pBuilding) {
		return this.gBuildingMap.get(pPlayerIndex).get(pBuilding);
	}
}
