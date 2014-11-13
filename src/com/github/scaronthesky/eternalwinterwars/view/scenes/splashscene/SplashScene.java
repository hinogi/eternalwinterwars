package com.github.scaronthesky.eternalwinterwars.view.scenes.splashscene;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.IModifier;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;
import com.github.scaronthesky.eternalwinterwars.view.scenes.AControllerScene;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class SplashScene extends AControllerScene {

	public SplashScene(IController pController) {
		super(pController);
	}

	@Override
	public void initialize() {
		this.createBackground();
	}

	public void loadAndChangeScene(final SceneType pNewScene) {
		DelayModifier lDelayModifier = new DelayModifier(5,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						SplashScene.this.getController().getIOService().load();
						SplashScene.this.getController().getView()
								.getResourceManager().load();
						SplashScene.this.getController().getView()
								.getHUDManager().load();
						SplashScene.this.getController().getView()
								.getSceneManager().load();
						SplashScene.this.getController().getView()
								.getSoundManager().load();
					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						SplashScene.this.getController().getView()
								.getSceneManager()
								.setActualSceneType(pNewScene);
					}
				});
		this.registerEntityModifier(lDelayModifier);
	}

	private void createBackground() {
		Sprite lBackgroundSprite = new Sprite(0, 0, this.getController()
				.getMainActivity().getEngine().getCamera().getWidth(), this
				.getController().getMainActivity().getEngine().getCamera()
				.getHeight(), this.getController().getView()
				.getResourceManager().getTextureRegionSplashBackground(), this
				.getController().getMainActivity()
				.getVertexBufferObjectManager());
		this.setBackground(new SpriteBackground(lBackgroundSprite));
	}
}
