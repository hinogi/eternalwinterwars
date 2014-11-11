package com.github.scaronthesky.eternalwinterwars.view.managers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

import com.github.scaronthesky.eternalwinterwars.R;
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
	private Map<String, ITextureRegion> gTextureRegions;
	private Map<String, String> gKeyMap;
	private ITextureRegion gTextureRegionSplashBackground;
	private ITextureRegion gTextureRegionMenuBackground;
	private ITextureRegion gTextureRegionButtonSettings;
	private ITextureRegion gTextureRegionButtonMenu;
	private ITextureRegion gTextureRegionCoinEntity;
	private ITextureRegion gTextureRegionButtonSlideBar;
	private ITextureRegion gTextureRegionParticleSnow;
	private ITiledTextureRegion gTiledTextureRegionButton;
	private ITiledTextureRegion gTiledTextureRegionTestUnitMove;
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
		this.gKeyMap = this.loadKeysFromProperties();
		this.gTextureRegions = new HashMap<String, ITextureRegion>();
		this.addImageResourcesIterative(1920, 1080, "gfx/backgrounds");
		this.addImageResourcesIterative(64, 64, "gfx/buildings");
		this.addImageResourcesIterative(64, 64, "gfx/mountains");
		this.addImageResourcesIterative(64, 64, "gfx/rivers");
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.gTextureRegionMenuBackground = this.loadImageResource(1920, 1080,
				"backgrounds/bg_v3.png");
		this.gTextureRegionButtonSettings = this.loadImageResource(80, 80,
				"settings.png");
		this.gTextureRegionButtonMenu = this.loadImageResource(120, 80,
				"menu.png");
		this.gTextureRegionCoinEntity = this.loadImageResource(80, 80,
				"settings.png");
		this.gTextureRegionButtonSlideBar = this.loadImageResource(80,80,
				"settings.png");
		this.gTextureRegionParticleSnow = this.loadImageResource(80, 80,
				"settings.png");
		this.gTiledTextureRegionButton = this.loadTiledImageResource(371, 600,
				"button.png", 1, 3);
		this.gTiledTextureRegionTestUnitMove = this.loadTiledImageResource(256,
				384, "units/knights/Knight_v1.png", 4, 6);
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

	private Map<String, String> loadKeysFromProperties() {
		this.gKeyMap = new HashMap<String, String>();
		Properties lProperties = new Properties();
		try {
			lProperties.load(this.getController().getMainActivity()
					.getResources().openRawResource(R.raw.image));
			for (Object lObject : lProperties.keySet()) {
				String lKey = (String) lObject;
				this.gKeyMap.put(lKey, (String) lProperties.get(lKey));
			}
		} catch (IOException lIOException) {
			lIOException.printStackTrace();
		}
		return this.gKeyMap;
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
			String pTextureName) {
		this.gTextureRegions
				.put(this.getKeyFromTextureName(pTextureName), this
						.loadImageResource(pAtlasWidth, pAtlasHeight,
								pTextureName));
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
		this.gTextureRegions.put(this.getKeyFromTextureName(pTextureName), this
				.loadImageResource(pAtlasWidth, pAtlasHeight, pDirectoryName
						+ "/" + pTextureName));
	}

	/**
	 * @param pTextureName
	 *            the image's name
	 * @return the specific image key read from image.properties
	 */
	private String getKeyFromTextureName(String pTextureName) {
		for (String lKey : this.gKeyMap.keySet()) {
			if (this.gKeyMap.get(lKey).equals(pTextureName)) {
				return lKey;
			}
		}
		return null;
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

	public ITiledTextureRegion getTiledTextureRegionTestUnitMove() {
		return this.gTiledTextureRegionTestUnitMove;
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

	public Map<String, ITextureRegion> getTextureRegions() {
		return this.gTextureRegions;
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

	public ITextureRegion getTextureRegionParticleSnow() {
		return this.gTextureRegionParticleSnow;
	}

	public ITiledTextureRegion getTiledTextureRegionBlood() {
		return this.gTiledTextureRegionBlood;
	}
}
