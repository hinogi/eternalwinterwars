package com.github.scaronthesky.eternalwinterwars.view.managers;

import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.texture.region.ITiledTextureRegion;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.Constants;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationProperties;

/**
 * @author Manuel Seiche aka Hakenadu
 * @since 17.11.2014
 * 
 */
public class AnimationPropertiesManager extends AManager {
	private Map<String, AnimationProperties> gAnimationPropertiesKnight;
	private Map<String, AnimationProperties> gAnimationPropertiesMarksman;
	private Map<String, AnimationProperties> gAnimationPropertiesArtillery;
	private Map<String, AnimationProperties> gAnimationPropertiesCavallery;

	public AnimationPropertiesManager(IController pController) {
		super(pController);
	}

	public Map<String, AnimationProperties> getAnimationPropertiesKnight() {
		if (this.gAnimationPropertiesKnight == null) {
			this.gAnimationPropertiesKnight = this
					.fillAnimationPropertiesMapRegular(this.getController()
							.getView().getResourceManager()
							.getTiledTextureRegionKnight());
		}
		return this.gAnimationPropertiesKnight;
	}

	public Map<String, AnimationProperties> getAnimationPropertiesMarksman() {
		if (this.gAnimationPropertiesMarksman == null) {
			this.gAnimationPropertiesMarksman = this
					.fillAnimationPropertiesMapRegular(this.getController()
							.getView().getResourceManager()
							.getTiledTextureRegionMarksman());
		}
		return this.gAnimationPropertiesMarksman;
	}

	public Map<String, AnimationProperties> getAnimationPropertiesArtillery() {
		if (this.gAnimationPropertiesArtillery == null) {
			this.gAnimationPropertiesArtillery = this
					.fillAnimationPropertiesMapRegular(this.getController()
							.getView().getResourceManager()
							.getTiledTextureRegionArtillery());
		}
		return this.gAnimationPropertiesArtillery;
	}

	public Map<String, AnimationProperties> getAnimationPropertiesCavallery() {
		if (this.gAnimationPropertiesCavallery == null) {
			this.gAnimationPropertiesCavallery = this
					.fillAnimationPropertiesMapRegular(this.getController()
							.getView().getResourceManager()
							.getTiledTextureRegionCavallery());
		}
		return this.gAnimationPropertiesCavallery;
	}

	private Map<String, AnimationProperties> fillAnimationPropertiesMapRegular(
			ITiledTextureRegion pTiledTextureRegion) {
		Map<String, AnimationProperties> lAnimationProperties = new HashMap<String, AnimationProperties>();
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_UP,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 0, 3, true, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_DOWN,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 4, 7, true, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_RIGHT,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 8, 11, true, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_MOVE_LEFT,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 8, 11, true, true, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_ATTACK_UP,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 12, 15, false, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_ATTACK_DOWN,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 16, 19, false, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_ATTACK_RIGHT,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 20, 23, false, false, false));
		lAnimationProperties.put(Constants.ANIMATION_KEY_ATTACK_LEFT,
				new AnimationProperties(pTiledTextureRegion, new long[] { 150,
						150, 150, 150 }, 20, 23, false, true, false));
		return lAnimationProperties;
	}

	@Override
	public void preLoad() {
		// Do nothing
	}

	@Override
	public void load() {
		this.gAnimationPropertiesKnight = this.getAnimationPropertiesKnight();
		this.gAnimationPropertiesMarksman = this
				.getAnimationPropertiesMarksman();
		this.gAnimationPropertiesArtillery = this
				.getAnimationPropertiesArtillery();
		this.gAnimationPropertiesCavallery = this
				.getAnimationPropertiesCavallery();
	}
}
