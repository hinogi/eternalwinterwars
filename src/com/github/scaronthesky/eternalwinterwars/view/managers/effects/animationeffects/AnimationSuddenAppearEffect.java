package com.github.scaronthesky.eternalwinterwars.view.managers.effects.animationeffects;

import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.AnimationExecutor;
import com.github.scaronthesky.eternalwinterwars.view.managers.effects.AEffect;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class AnimationSuddenAppearEffect extends AEffect {

	public AnimationSuddenAppearEffect(IController pController, final float pX,
			final float pY, final Scene pParentScene, float pDuration,
			final float pWidth, final float pHeight,
			final AnimationProperties pAnimationProperties,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pController, pX, pY, pParentScene, pDuration,
				new IEntityModifierListener() {
					AnimatedSprite iAnimatedSprite;

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						this.iAnimatedSprite = new AnimatedSprite(pX, pY,
								pWidth, pHeight,
								pAnimationProperties.getTiledTextureRegion(),
								pVertexBufferObjectManager);
						pParentScene.attachChild(this.iAnimatedSprite);
						AnimationExecutor.execute(this.iAnimatedSprite,
								pAnimationProperties);
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						pParentScene.detachChild(this.iAnimatedSprite);

					}
				});
	}
}
