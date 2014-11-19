package com.github.scaronthesky.eternalwinterwars.view.scenes.menuscene;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.hud.AnimatedButtonSpriteMenuItem;
import com.github.scaronthesky.eternalwinterwars.view.managers.HUDManager.HUDType;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;
import com.github.scaronthesky.eternalwinterwars.view.scenes.AControllerMenuScene;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class MenuScene extends AControllerMenuScene {
	private static final int MN_START = 0;
	private static final int MN_EDITOR = 1;
	private static final float BUTTON_WIDTH_FACTOR = 0.5f;
	private static final float BUTTON_HEIGHT_FACTOR = 0.2f;
	private List<AnimatedButtonSpriteMenuItem> gMenuButtons;

	/**
	 * Creates an instance of {@link MenuScene}
	 * 
	 * @param pController
	 */
	public MenuScene(IController pController) {
		super(pController, pController.getMainActivity().getEngine()
				.getCamera());
	}

	@Override
	public void initialize() {
		this.createBackground();
		this.gMenuButtons = new ArrayList<AnimatedButtonSpriteMenuItem>();
		this.gMenuButtons.add(this.createButton(MN_START, this.getController()
				.getView().getResourceManager()
				.getTextureRegionButtonSettings()));
		this.gMenuButtons.add(this.createButton(MN_EDITOR, this.getController()
				.getView().getResourceManager()
				.getTextureRegionButtonSettings()));
		this.addMenuItems();
		this.setOnMenuItemClickListener(this);
		this.buildAnimations();
	}

	/**
	 * Adds all buttons as MenuItems
	 */
	private void addMenuItems() {
		for (AnimatedButtonSpriteMenuItem lMenuButton : this.gMenuButtons) {
			this.addMenuItem(lMenuButton);
		}
	}

	/**
	 * Creates the {@link SpriteBackground} for this Scene
	 */
	private void createBackground() {
		this.setBackground(new SpriteBackground(new Sprite(0, 0, this
				.getController().getMainActivity().getEngine().getCamera()
				.getWidth(), this.getController().getMainActivity().getEngine()
				.getCamera().getHeight(), this.getController().getView()
				.getResourceManager().getTextureRegionMenuBackground(), this
				.getController().getMainActivity()
				.getVertexBufferObjectManager())));
	}

	/**
	 * Creates an {@link AnimatedButtonSpriteMenuItem}
	 * 
	 * @param pId
	 *            MenuItemID
	 * @param pTextureRegionIcon
	 *            Icon shown on the button
	 * @return {@link AnimatedButtonSpriteMenuItem}
	 */
	private AnimatedButtonSpriteMenuItem createButton(int pId,
			ITextureRegion pTextureRegionIcon) {
		AnimatedButtonSpriteMenuItem lMenuButton = new AnimatedButtonSpriteMenuItem(
				pId, this.getController().getView().getResourceManager()
						.getTiledTextureRegionButton(), pTextureRegionIcon,
				this.getController().getMainActivity()
						.getVertexBufferObjectManager());
		lMenuButton.setWidth(this.getController().getMainActivity().getEngine()
				.getCamera().getWidth()
				* BUTTON_WIDTH_FACTOR);
		lMenuButton.setHeight(this.getController().getMainActivity()
				.getEngine().getCamera().getHeight()
				* BUTTON_HEIGHT_FACTOR);
		return lMenuButton;
	}

	@Override
	public boolean onMenuItemClicked(
			org.andengine.entity.scene.menu.MenuScene pMenuScene,
			IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MN_START:
			this.getController().getView().getSceneManager()
					.setActualSceneType(SceneType.GAME);
			this.getController().getView().getHUDManager()
					.attachHUD(HUDType.GAME);
			this.getController().startTest();
			return true;
		case MN_EDITOR:
			this.getController().startEditing();
			this.getController().getView().getSceneManager()
					.setActualSceneType(SceneType.EDITOR);
			return true;
		default:
			return false;
		}
	}
}
