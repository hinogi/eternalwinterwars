package com.github.scaronthesky.eternalwinterwars.view.hud.gamehud;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.input.touch.TouchEvent;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.view.hud.ControllerHUD;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class GameHUD extends ControllerHUD {
	private static final float SLIDE_DURATION = 1f;
	private ImageButtonSprite gButtonSettings;
	private ImageButtonSprite gButtonMenu;
	private ImageButtonSprite gButtonSlide;
	private Entity gSlideInBar;
	private CoinEntity gCoinEntity;
	private MoveYModifier gMoveYModifier;

	/**
	 * Creates an instance of {@link GameHUD}
	 * 
	 * @param model
	 *            {@link Model} reference
	 */
	public GameHUD(IController pController) {
		super(pController);
	}

	@Override
	public void initialize() {
		this.attachChild(this.getSlideInBar());
		this.attachChild(this.getCoinEntity());
	}

	public Entity getSlideInBar() {
		if (this.gSlideInBar == null) {
			this.gSlideInBar = new Entity();
			this.gSlideInBar.attachChild(this.getButtonSettings());
			this.gSlideInBar.attachChild(this.getButtonMenu());
			this.gSlideInBar.attachChild(this.getButtonSlideBar());
			this.gSlideInBar.setY(-(this.getButtonSettings().getHeight() + this
					.getButtonMenu().getHeight()));
		}
		return this.gSlideInBar;
	}

	public ImageButtonSprite getButtonSlideBar() {
		if (this.gButtonSlide == null) {
			this.gButtonSlide = new ImageButtonSprite(0,
					this.gButtonMenu.getHeight()
							+ this.gButtonSettings.getHeight(),
					this.gButtonSettings.getWidth() / 4,
					this.gButtonSettings.getWidth() / 4, this.getController()
							.getView().getResourceManager()
							.getTiledTextureRegionButton(), this
							.getController().getView().getResourceManager()
							.getTextureRegionButtonSlideBar(), this, this
							.getController().getMainActivity()
							.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY) {
					GameHUD.this.slideBar(SLIDE_DURATION);
					return super.onAreaTouched(pSceneTouchEvent,
							pTouchAreaLocalX, pTouchAreaLocalY);
				}
			};
			this.gButtonSlide.setX(this.gButtonSlide.getX()
					- this.gButtonSlide.getWidth() / 2
					+ this.gButtonMenu.getWidth() / 2);
		}
		return this.gButtonSlide;
	}

	public void slideBar(float pDuration) {
		if (this.gMoveYModifier == null || this.gMoveYModifier.isFinished()) {
			this.gMoveYModifier = new MoveYModifier(pDuration,
					this.gSlideInBar.getY(),
					this.gSlideInBar.getY() == 0 ? -(this.getButtonSettings()
							.getHeight() + this.getButtonMenu().getHeight())
							: 0);
			this.gSlideInBar.registerEntityModifier(this.gMoveYModifier);
		}
	}

	public ImageButtonSprite getButtonSettings() {
		if (this.gButtonSettings == null) {
			this.gButtonSettings = new ImageButtonSprite(0, 0, this
					.getController().getMainActivity().getSmoothCamera()
					.getWidth() * 0.2f, this.getController().getMainActivity()
					.getSmoothCamera().getHeight() * 0.2f, this.getController()
					.getView().getResourceManager()
					.getTiledTextureRegionButton(), this.getController()
					.getView().getResourceManager()
					.getTextureRegionButtonSettings(), this, this
					.getController().getMainActivity()
					.getVertexBufferObjectManager());
		}
		return this.gButtonSettings;
	}

	public ImageButtonSprite getButtonMenu() {
		if (this.gButtonMenu == null) {
			this.gButtonMenu = new ImageButtonSprite(0, this
					.getButtonSettings().getHeight(), this.getController()
					.getMainActivity().getSmoothCamera().getWidth() * 0.2f,
					this.getController().getMainActivity().getSmoothCamera()
							.getHeight() * 0.2f,
					this.getController().getView().getResourceManager()
							.getTiledTextureRegionButton(), this
							.getController().getView().getResourceManager()
							.getTextureRegionButtonMenu(), this, this
							.getController().getMainActivity()
							.getVertexBufferObjectManager());
		}
		return this.gButtonMenu;
	}

	public CoinEntity getCoinEntity() {
		if (this.gCoinEntity == null) {
			this.gCoinEntity = new CoinEntity(this.getController());
			this.gCoinEntity.setX(this.getController().getMainActivity()
					.getSmoothCamera().getWidth()
					- this.gCoinEntity.getWidth());
		}
		return this.gCoinEntity;
	}
}
