package com.github.scaronthesky.eternalwinterwars.view;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.DisplayMetrics;

import com.github.scaronthesky.eternalwinterwars.controller.Controller;
import com.github.scaronthesky.eternalwinterwars.view.managers.SceneManager.SceneType;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class MainActivity extends SimpleBaseGameActivity {
	private Controller gController;
	private SmoothCamera gCamera;

	@Override
	public EngineOptions onCreateEngineOptions() {
		DisplayMetrics lMetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(lMetrics);
		int lDeviceWidth = lMetrics.heightPixels;
		int lDeviceHeight = lMetrics.widthPixels;
		this.gCamera = new SmoothCamera(0, 0, lDeviceHeight, lDeviceWidth,
				lDeviceWidth / 2, lDeviceWidth / 2, 1);
		this.gCamera.setBoundsEnabled(true);
		EngineOptions lEngineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						lDeviceHeight, lDeviceWidth), this.gCamera);
		lEngineOptions.getAudioOptions().setNeedsMusic(true);
		lEngineOptions.getAudioOptions().setNeedsSound(true);
		return lEngineOptions;
	}

	@Override
	protected void onCreateResources() {
		this.gController = new Controller(this);
		this.gController.getView().instantiateManagers();
		this.gController.getView().getSceneManager()
				.setActualSceneType(SceneType.SPLASH);
		this.gController.getView().getSceneManager().getSplashScene()
				.loadAndChangeScene(SceneType.MENU);
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		return this.gController.getView().getSceneManager().getActualScene();
	}

	public SmoothCamera getSmoothCamera() {
		return this.gCamera;
	}
}
