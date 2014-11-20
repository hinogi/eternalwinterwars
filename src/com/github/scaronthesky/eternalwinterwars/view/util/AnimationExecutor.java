package com.github.scaronthesky.eternalwinterwars.view.util;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;

import com.github.scaronthesky.eternalwinterwars.view.entities.game.multitexture.MultiTextureAnimatedSprite;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects.AnimationProperties;


public abstract class AnimationExecutor {
	public static synchronized void execute(AnimatedSprite pAnimatedSprite,
			AnimationProperties pAnimationProperties) {
		pAnimatedSprite.setFlippedHorizontal(pAnimationProperties
				.isFlipHorizontal());
		pAnimatedSprite.setFlippedVertical(pAnimationProperties
				.isFlipVertical());
		IAnimationListener lAnimationListener = new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
			}
		};
		pAnimatedSprite.animate(pAnimationProperties.getTileDuration(),
				pAnimationProperties.getStartTile(),
				pAnimationProperties.getEndTile(),
				pAnimationProperties.isLoop(), lAnimationListener);
	}

	public static synchronized void execute(
			MultiTextureAnimatedSprite pAnimatedSprite,
			AnimationProperties pAnimationProperties) {
		pAnimatedSprite.setFlippedHorizontal(pAnimationProperties
				.isFlipHorizontal());
		pAnimatedSprite.setFlippedVertical(pAnimationProperties
				.isFlipVertical());
		pAnimatedSprite.animate(pAnimationProperties.getTileDuration(),
				pAnimationProperties.getStartTile(),
				pAnimationProperties.getEndTile(),
				pAnimationProperties.isLoop());
	}
}
