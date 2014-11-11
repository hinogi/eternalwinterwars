package com.github.scaronthesky.eternalwinterwars.view.managers.effects;

import java.util.List;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveModifier;
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
public class IncomeAddEffect extends AEffect {

	public IncomeAddEffect(final IController pController, final float pX,
			final float pY, final Scene pParentScene, final float pDuration,
			final ITextureRegion pTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager,
			final List<float[]> pAppearCoordinates, final float pWidth,
			final float pHeight, final int pNewCoinCount) {
		super(pController, pX, pY, pParentScene, pDuration,
				new IEntityModifierListener() {
					private List<Sprite> iSprites;

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						iSprites = new EffectServer(pController, pX, pY,
								pParentScene).createSprites(pAppearCoordinates,
								pWidth, pHeight, pTextureRegion,
								pVertexBufferObjectManager);
						for (Sprite iSprite : iSprites) {
							iSprite.registerEntityModifier(new MoveModifier(
									pDuration, iSprite.getX(), pX, iSprite
											.getY(), pY));
						}
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						for (Sprite iSprite : iSprites) {
							pParentScene.detachChild(iSprite);
						}
						pController.getView().getHUDManager().getGameHUD()
								.getCoinEntity().getTextScore()
								.setText(String.valueOf(pNewCoinCount));
					}
				});
	}
}
