package com.github.scaronthesky.eternalwinterwars.view.managers.effects;

import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.github.scaronthesky.eternalwinterwars.controller.IController;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class MonoTextureSuddenAppearEffect extends AEffect {

	public MonoTextureSuddenAppearEffect(final IController pController,
			final float pX, final float pY, final Scene pParentScene,
			float pDuration, final ITextureRegion pTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pController, pX, pY, pParentScene, pDuration,
				new IEntityModifierListener() {
					Sprite iSprite;

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						this.iSprite = new Sprite(pX, pY, pTextureRegion,
								pVertexBufferObjectManager);
						pParentScene.attachChild(this.iSprite);
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						pParentScene.detachChild(this.iSprite);
					}
				});
	}
}
