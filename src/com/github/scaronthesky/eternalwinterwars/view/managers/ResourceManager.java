package com.github.scaronthesky.eternalwinterwars.view.managers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.controller.IController;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class ResourceManager extends AManager {
	// -----------------------------------------------------
	// Image-Resources
	// -----------------------------------------------------
	private Map<String, ITextureRegion> gCellTextureRegions;
	private Map<String, ITiledTextureRegion> gGameBaseEntityTiledTextureRegions;
	private ITextureRegion gTextureRegionSplashBackground;
	private ITextureRegion gTextureRegionMenuBackground;
	private ITextureRegion gTextureRegionButtonSettings;
	private ITextureRegion gTextureRegionButtonMenu;
	private ITextureRegion gTextureRegionCoinEntity;
	private ITextureRegion gTextureRegionButtonSlideBar;
	private ITextureRegion gTextureRegionParticleSnow;
	private ITextureRegion gTextureRegionControlBase;
	private ITextureRegion gTextureRegionControlKnob;
	private ITextureRegion gTextureRegionAttack;
	private ITextureRegion gTextureRegionCancel;
	private ITextureRegion gTextureRegionArrow;
	private ITextureRegion gTextureRegionStone;
	private ITiledTextureRegion gTiledTextureRegionButton;
	private ITiledTextureRegion gTiledTextureRegionKnight;
	private ITiledTextureRegion gTiledTextureRegionMarksman;
	private ITiledTextureRegion gTiledTextureRegionArtillery;
	private ITiledTextureRegion gTiledTextureRegionCavallery;
	private ITiledTextureRegion gTiledTextureRegionBlood;

	// -----------------------------------------------------
	// Font-Resources
	// -----------------------------------------------------
	private Font gFontButton;
	private Font gFontCoinEntity;
	private Font gFontDamage;

	/**
	 * Creates an instance of {@link ResourceManager}
	 * 
	 * @param pController
	 *            {@link Controller} reference
	 */
	public ResourceManager(IController pController) {
		super(pController);
	}

	@Override
	public void preLoad() {
		this.gTextureRegionSplashBackground = this.loadImageResource(1024, 600,
				"gfx/splashscreens/splashscreen_v2.png");
	}

	@Override
	public void load() {
		this.gCellTextureRegions = new HashMap<String, ITextureRegion>();
		this.gGameBaseEntityTiledTextureRegions = new HashMap<String, ITiledTextureRegion>();
		this.addImageResourcesIterative(1920, 1080, "gfx/backgrounds");
		this.addImageResourcesIterative(64, 64, "gfx/buildings");
		this.addImageResourcesIterative(64, 64, "gfx/mountains");
		this.addImageResourcesIterative(64, 64, "gfx/rivers");
		this.addImageResourcesIterative(64, 64, "gfx/trees");
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.gTextureRegionArrow = this.loadImageResource(64, 64,
				"effects/Arrow_v1.png");
		this.gTextureRegionStone = this.loadImageResource(64, 64,
				"effects/Stone_v1.png");
		this.gTextureRegionMenuBackground = this.loadImageResource(1920, 1080,
				"backgrounds/bg_v3.png");
		this.gTextureRegionButtonSettings = this.loadImageResource(80, 80,
				"settings.png");
		this.gTextureRegionButtonMenu = this.loadImageResource(120, 80,
				"menu.png");
		this.gTextureRegionCoinEntity = this.loadImageResource(100, 100,
				"coin.png");
		this.gTextureRegionButtonSlideBar = this.loadImageResource(64, 64,
				"slidebar_icon.png");
		this.gTextureRegionControlBase = this.loadImageResource(128, 128,
				"onscreen_control_base.png");
		this.gTextureRegionControlKnob = this.loadImageResource(64, 64,
				"onscreen_control_knob.png");
		this.gTextureRegionParticleSnow = this.loadImageResource(32, 32,
				"snowflake.png");
		this.gTextureRegionAttack = this
				.loadImageResource(64, 64, "attack.png");
		this.gTextureRegionCancel = this
				.loadImageResource(64, 64, "cancel.png");
		this.gTiledTextureRegionButton = this.loadTiledImageResource(370, 600,
				"Menu/button_tile.png", 1, 3);
		this.addTiledImageResource(256, 384, 4, 6, "Knight_v1.png",
				"units/knights");
		this.addTiledImageResource(256, 384, 4, 6, "Marksmen_v1.png",
				"units/marksmen");
		this.addTiledImageResource(256, 384, 4, 6, "Artillery_v1.png",
				"units/artillery");
		this.addTiledImageResource(256, 384, 4, 6, "Cavallery_v1.png",
				"units/cavallery");
		// XXX TODO
		this.gTiledTextureRegionKnight = this.loadTiledImageResource(256, 384,
				"units/knights/Knight_v1.png", 4, 6);
		this.gTiledTextureRegionMarksman = this.loadTiledImageResource(256,
				384, "units/marksmen/Marksmen_v1.png", 4, 6);
		this.gTiledTextureRegionArtillery = this.loadTiledImageResource(256,
				384, "units/artillery/Artillery_v1.png", 4, 6);
		this.gTiledTextureRegionCavallery = this.loadTiledImageResource(256,
				384, "units/cavallery/Cavallery_v1.png", 4, 6);
		this.gTiledTextureRegionBlood = this.loadTiledImageResource(384, 64,
				"effects/Blood_v2.png", 6, 1);
		this.gFontButton = this.loadFont((int) (512 * this.getController()
				.getMainActivity().getSmoothCamera().getWidth() / 720),
				(int) (512 * this.getController().getMainActivity()
						.getSmoothCamera().getHeight() / 1280), "arial",
				Typeface.BOLD, this.getController().getMainActivity()
						.getSmoothCamera().getHeight() / 20, Color.WHITE);
		this.gFontCoinEntity = this.loadFont((int) (512 * this.getController()
				.getMainActivity().getSmoothCamera().getWidth() / 720),
				(int) (512 * this.getController().getMainActivity()
						.getSmoothCamera().getHeight() / 1280), "arial",
				Typeface.BOLD, this.getController().getMainActivity()
						.getSmoothCamera().getHeight() / 20, Color.WHITE);
		this.gFontDamage = this.loadFont((int) (512 * this.getController()
				.getMainActivity().getSmoothCamera().getWidth() / 720),
				(int) (512 * this.getController().getMainActivity()
						.getSmoothCamera().getHeight() / 1280), "arial",
				Typeface.BOLD_ITALIC, this.getController().getMainActivity()
						.getSmoothCamera().getHeight() / 20, Color.RED);
	}

	private void addTiledImageResource(int pAtlasWidth, int pAtlasHeight,
			int pColumns, int pRows, String pTextureName, String pDirectoryName) {
		BitmapTextureAtlas lTextureAtlas = new BitmapTextureAtlas(this
				.getController().getMainActivity().getTextureManager(),
				pAtlasWidth, pAtlasHeight,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ITiledTextureRegion pTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(lTextureAtlas, this.getController()
						.getMainActivity().getAssets(), pDirectoryName + "/"
						+ pTextureName, 0, 0, pColumns, pRows);
		lTextureAtlas.load();
		this.gGameBaseEntityTiledTextureRegions.put(pTextureName,
				pTiledTextureRegion);
	}

	/**
	 * Iterates over the files in an asset directory and adds an image for each
	 * file (IMAGES MUST HAVE THE SAME SIZE!)
	 * 
	 * @param pAtlasWidth
	 *            the image's width
	 * @param pAtlasHeight
	 *            the image's height
	 * @param pAssetDirectoryName
	 *            target asset directory
	 */
	private void addImageResourcesIterative(int pAtlasWidth, int pAtlasHeight,
			String pAssetDirectoryName) {
		String[] lFileNames;
		try {
			lFileNames = this.getController().getMainActivity().getAssets()
					.list(pAssetDirectoryName);
			for (String lFileName : lFileNames) {
				if (lFileName.toLowerCase().endsWith(".png")) {
					this.addImageResource(pAtlasWidth, pAtlasHeight, lFileName,
							pAssetDirectoryName);
				}
			}
		} catch (IOException lIOException) {
			lIOException.printStackTrace();
		}
	}

	/**
	 * Loads and adds an ITextureRegion into the textureRegions - Map
	 * 
	 * @param pAtlasWidth
	 *            the image's width
	 * @param pAtlasHeight
	 *            the image's height
	 * @param pTextureName
	 *            target asset path and map key
	 */
	private void addImageResource(int pAtlasWidth, int pAtlasHeight,
			String pTextureName, String pDirectoryName) {
		this.gCellTextureRegions
				.put(pTextureName, this.loadImageResource(pAtlasWidth,
						pAtlasHeight, pDirectoryName + "/" + pTextureName));
	}

	/**
	 * Creates an image from asset path
	 * 
	 * @param pAtlasWidth
	 *            the image's width
	 * @param pAtlasHeight
	 *            the image's height
	 * @param pTextureName
	 *            target asset path
	 * @return {@link ITextureRegion}
	 */
	private ITextureRegion loadImageResource(int pAtlasWidth, int pAtlasHeight,
			String pTextureName) {
		BitmapTextureAtlas lTextureAtlas = new BitmapTextureAtlas(this
				.getController().getMainActivity().getEngine()
				.getTextureManager(), pAtlasWidth, pAtlasHeight,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ITextureRegion lTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(lTextureAtlas, this.getController()
						.getMainActivity(), pTextureName, 0, 0);
		lTextureAtlas.load();
		return lTextureRegion;
	}

	/**
	 * Creates a tiled image from asset path
	 * 
	 * @param pAtlasWidth
	 *            the image's width
	 * @param pAtlasHeight
	 *            the image's height
	 * @param pTextureName
	 *            target asset path
	 * @return {@link ITiledTextureRegion}
	 */
	private ITiledTextureRegion loadTiledImageResource(int pAtlasWidth,
			int pAtlasHeight, String pTextureName, int pColumns, int pRows) {
		BitmapTextureAtlas lTextureAtlas = new BitmapTextureAtlas(this
				.getController().getMainActivity().getTextureManager(),
				pAtlasWidth, pAtlasHeight,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ITiledTextureRegion pTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(lTextureAtlas, this.getController()
						.getMainActivity().getAssets(), pTextureName, 0, 0,
						pColumns, pRows);
		lTextureAtlas.load();
		return pTiledTextureRegion;
	}

	/**
	 * Creates a {@link Font}
	 * 
	 * @param pAtlasWidth
	 *            the fonts maximal width
	 * @param pAtlasHeight
	 *            the fonts maximal height
	 * @param pFamilyName
	 *            font family
	 * @param pStyle
	 *            font style
	 * @param pSize
	 *            font size
	 * @param pColor
	 *            font color
	 * @return {@link Font}
	 */
	private Font loadFont(int pAtlasWidth, int pAtlasHeight,
			String pFamilyName, int pStyle, float pSize, Color pColor) {
		BitmapTextureAtlas lTextureAtlas = new BitmapTextureAtlas(this
				.getController().getMainActivity().getEngine()
				.getTextureManager(), pAtlasWidth, pAtlasHeight,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font lFont = new Font(this.getController().getMainActivity()
				.getEngine().getFontManager(), lTextureAtlas, Typeface.create(
				pFamilyName, pStyle), pSize, true, pColor);
		this.getController().getMainActivity().getEngine().getTextureManager()
				.loadTexture(lTextureAtlas);
		this.getController().getMainActivity().getEngine().getFontManager()
				.loadFont(lFont);
		return lFont;
	}

	public ITiledTextureRegion getTiledTextureRegionButton() {
		return this.gTiledTextureRegionButton;
	}

	public ITiledTextureRegion getTiledTextureRegionKnight() {
		return this.gTiledTextureRegionKnight;
	}

	public ITiledTextureRegion getTiledTextureRegionMarksman() {
		return this.gTiledTextureRegionMarksman;
	}

	public ITiledTextureRegion getTiledTextureRegionArtillery() {
		return this.gTiledTextureRegionArtillery;
	}

	public ITiledTextureRegion getTiledTextureRegionCavallery() {
		return this.gTiledTextureRegionCavallery;
	}

	public Font getFontButton() {
		return this.gFontButton;
	}

	public Font getFontCoinEntity() {
		return this.gFontCoinEntity;
	}

	public Font getFontDamage() {
		return this.gFontDamage;
	}

	public Map<String, ITextureRegion> getCellTextureRegions() {
		return this.gCellTextureRegions;
	}

	public ITextureRegion getTextureRegionButtonSettings() {
		return this.gTextureRegionButtonSettings;
	}

	public ITextureRegion getTextureRegionButtonMenu() {
		return this.gTextureRegionButtonMenu;
	}

	public ITextureRegion getTextureRegionSplashBackground() {
		return this.gTextureRegionSplashBackground;
	}

	public ITextureRegion getTextureRegionMenuBackground() {
		return this.gTextureRegionMenuBackground;
	}

	public ITextureRegion getTextureRegionCoinEntity() {
		return this.gTextureRegionCoinEntity;
	}

	public ITextureRegion getTextureRegionButtonSlideBar() {
		return this.gTextureRegionButtonSlideBar;
	}

	public ITextureRegion getTextureRegionAttack() {
		return this.gTextureRegionAttack;
	}

	public ITextureRegion getTextureRegionCancel() {
		return this.gTextureRegionCancel;
	}

	public ITextureRegion getTextureRegionParticleSnow() {
		return this.gTextureRegionParticleSnow;
	}

	public ITextureRegion getTextureRegionControlBase() {
		return this.gTextureRegionControlBase;
	}

	public ITextureRegion getTextureRegionControlKnob() {
		return this.gTextureRegionControlKnob;
	}

	public ITiledTextureRegion getTiledTextureRegionBlood() {
		return this.gTiledTextureRegionBlood;
	}

	public ITextureRegion getTextureRegionArrow() {
		return this.gTextureRegionArrow;
	}

	public ITextureRegion getTextureRegionStone() {
		return this.gTextureRegionStone;
	}

	public ITextureRegion getTextureRegionCastle(int pPlayerIndex) {
		switch (pPlayerIndex) {
		case 0:
			return this.gCellTextureRegions.get("castle_v6.png");
		case 1:
			return this.gCellTextureRegions.get("castle_v5.png");
		default:
			return null;
		}
	}
}
