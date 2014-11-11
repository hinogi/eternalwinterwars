package com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class AnimationProperties {
	private ITiledTextureRegion gTiledTextureRegion;
	private long[] gTileDuration;
	private int gStartTile;
	private int gEndTile;
	private boolean gLoop;
	private boolean gFlipHorizontal;
	private boolean gFlipVertical;

	/**
	 * Creates an instance of {@link AnimationProperties}
	 * 
	 * @param pTiledTextureRegion
	 *            the {@link TiledTextureRegion} to animate
	 * @param pTileDuration
	 *            the duration for each tile
	 * @param pStartTile
	 *            first tile to show
	 * @param pEndTile
	 *            last tile to show
	 * @param pLoop
	 *            true = loop animation
	 */
	public AnimationProperties(ITiledTextureRegion pTiledTextureRegion,
			long[] pTileDuration, int pStartTile, int pEndTile, boolean pLoop,
			boolean pFlipHorizontal, boolean pFlipVertical) {
		this.gTiledTextureRegion = pTiledTextureRegion;
		this.gTileDuration = pTileDuration;
		this.gStartTile = pStartTile;
		this.gEndTile = pEndTile;
		this.gLoop = pLoop;
		this.gFlipHorizontal = pFlipHorizontal;
		this.gFlipVertical = pFlipVertical;
	}

	public long getAccumulatedDuration() {
		long lSum = 0;
		for (int lTileIndex = 0; lTileIndex < this.gTileDuration.length; lTileIndex++) {
			lSum += this.gTileDuration[lTileIndex];
		}
		return lSum;
	}

	public ITiledTextureRegion getTiledTextureRegion() {
		return this.gTiledTextureRegion;
	}

	public void setTiledTextureRegion(ITiledTextureRegion pTiledTextureRegion) {
		this.gTiledTextureRegion = pTiledTextureRegion;
	}

	public long[] getTileDuration() {
		return this.gTileDuration;
	}

	public void setTileDuration(long[] pTileDuration) {
		this.gTileDuration = pTileDuration;
	}

	public int getStartTile() {
		return this.gStartTile;
	}

	public void setStartTile(int startTile) {
		this.gStartTile = startTile;
	}

	public int getEndTile() {
		return this.gEndTile;
	}

	public void setEndTile(int endTile) {
		this.gEndTile = endTile;
	}

	public boolean isLoop() {
		return this.gLoop;
	}

	public void setLoop(boolean loop) {
		this.gLoop = loop;
	}

	public boolean isFlipHorizontal() {
		return this.gFlipHorizontal;
	}

	public void setFlipHorizontal(boolean pFlipHorizontal) {
		this.gFlipHorizontal = pFlipHorizontal;
	}

	public boolean isFlipVertical() {
		return this.gFlipVertical;
	}

	public void setFlipVertical(boolean pFlipVertical) {
		this.gFlipVertical = pFlipVertical;
	}
}
