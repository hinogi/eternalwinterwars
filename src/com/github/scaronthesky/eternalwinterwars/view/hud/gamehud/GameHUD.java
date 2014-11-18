package com.github.scaronthesky.eternalwinterwars.view.hud.gamehud;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.input.touch.TouchEvent;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.model.Model;
import com.github.scaronthesky.eternalwinterwars.view.constants.Constants;
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
	private AnalogOnScreenControl gCameraControlEntity;

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
		this.attachChild(this.getCameraControlEntity());
	}

	private AnalogOnScreenControl getCameraControlEntity() {
		if (this.gCameraControlEntity == null) {
			float lSideLength = this.gButtonSettings.getWidth();
			this.gCameraControlEntity = new AnalogOnScreenControl(0, this
					.getController().getMainActivity().getSmoothCamera()
					.getHeight(), this.getController().getMainActivity()
					.getSmoothCamera(), this.getController().getView()
					.getResourceManager().getTextureRegionControlBase(), this
					.getController().getView().getResourceManager()
					.getTextureRegionControlKnob(), 0.1f, this.getController()
					.getMainActivity().getVertexBufferObjectManager(),
					new IAnalogOnScreenControlListener() {

						@Override
						public void onControlChange(
								BaseOnScreenControl pBaseOnScreenControl,
								float pValueX, float pValueY) {
							GameHUD.this
									.getController()
									.getMainActivity()
									.getSmoothCamera()
									.setCenter(
											GameHUD.this.getController()
													.getMainActivity()
													.getSmoothCamera()
													.getCenterX()
													+ pValueX
													* Constants.CAMERA_CONTROL_MOVE_MULTIPLICATOR,
											GameHUD.this.getController()
													.getMainActivity()
													.getSmoothCamera()
													.getCenterY()
													+ pValueY
													* Constants.CAMERA_CONTROL_MOVE_MULTIPLICATOR);
						}

						@Override
						public void onControlClick(
								AnalogOnScreenControl pAnalogOnScreenControl) {

						}
					});
			this.gCameraControlEntity.getControlBase().setBlendFunction(
					GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			this.gCameraControlEntity.getControlBase().setAlpha(0.3f);
			this.gCameraControlEntity.getControlKnob().setAlpha(0.5f);
			this.gCameraControlEntity.getControlBase().setScaleCenter(0, 128);
			this.gCameraControlEntity.getControlBase().setWidth(
					this.gButtonSettings.getWidth());
			this.gCameraControlEntity.getControlBase().setHeight(lSideLength);
			this.gCameraControlEntity.getControlKnob()
					.setWidth(lSideLength / 2);
			this.gCameraControlEntity.getControlKnob().setHeight(
					lSideLength / 2);
			this.gCameraControlEntity.refreshControlKnobPosition();
			this.attachChild(this.gCameraControlEntity);
			this.setChildScene(this.gCameraControlEntity);
		}
		return this.gCameraControlEntity;
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
			boolean lSlideOut = this.gSlideInBar.getY() == 0;
			this.gMoveYModifier = new MoveYModifier(pDuration,
					this.gSlideInBar.getY(), lSlideOut ? -(this
							.getButtonSettings().getHeight() + this
							.getButtonMenu().getHeight()) : 0);
			this.gSlideInBar.registerEntityModifier(this.gMoveYModifier);
			this.gCameraControlEntity.getControlBase().registerEntityModifier(
					new MoveYModifier(pDuration, this.gCameraControlEntity
							.getControlBase().getY(),
							lSlideOut ? this.gCameraControlEntity
									.getControlBase().getY()
									+ this.gCameraControlEntity
											.getControlBase().getHeight()
									: this.gCameraControlEntity
											.getControlBase().getY()
											- this.gCameraControlEntity
													.getControlBase()
													.getHeight()));
			this.gCameraControlEntity.getControlKnob().registerEntityModifier(
					new MoveYModifier(pDuration, this.gCameraControlEntity
							.getControlKnob().getY(),
							lSlideOut ? this.gCameraControlEntity
									.getControlKnob().getY()
									+ this.gCameraControlEntity
											.getControlBase().getHeight()
									: this.gCameraControlEntity
											.getControlKnob().getY()
											- this.gCameraControlEntity
													.getControlBase()
													.getHeight()));
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
			this.gCoinEntity.setText("0");
		}
		return this.gCoinEntity;
	}
}
