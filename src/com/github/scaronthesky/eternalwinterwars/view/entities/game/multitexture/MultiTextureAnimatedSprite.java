package com.github.scaronthesky.eternalwinterwars.view.entities.game.multitexture;

import java.util.Map;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationProperties;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class MultiTextureAnimatedSprite extends AnimatedSprite {
	private final Map<String, AnimationProperties> gAnimations;
	private String gActualAnimationId;

	public MultiTextureAnimatedSprite(float pX, float pY, float pWidth,
			float pHeight, Map<String, AnimationProperties> pAnimations,
			String pStartAnimationId,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pAnimations.get(pStartAnimationId)
				.getTiledTextureRegion(), pVertexBufferObjectManager);
		this.gAnimations = pAnimations;
		this.gActualAnimationId = pStartAnimationId;
	}

	public void animate() {
		AnimationProperties lAnimation = this.gAnimations
				.get(this.gActualAnimationId);
		this.animate(lAnimation.getTileDuration(), lAnimation.getStartTile(),
				lAnimation.getEndTile(), lAnimation.isLoop());
		this.setFlipped(lAnimation.isFlipHorizontal(),
				lAnimation.isFlipVertical());
	}

	public void setActualAnimation(String pAnimationId) {
		this.gActualAnimationId = pAnimationId;
		ITiledTextureRegion lActualTiledTextureRegion = this.gAnimations.get(
				pAnimationId).getTiledTextureRegion();
		if (this.getTiledTextureRegion() != lActualTiledTextureRegion) {
			this.setTiledTextureRegion(lActualTiledTextureRegion);
		}
	}

	public String getActualTiledTextureId() {
		return this.gActualAnimationId;
	}

}
