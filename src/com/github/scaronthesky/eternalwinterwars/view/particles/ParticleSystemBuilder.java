package com.github.scaronthesky.eternalwinterwars.view.particles;

import org.andengine.entity.Entity;
import org.andengine.entity.particle.BatchedPseudoSpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.AccelerationParticleInitializer;
import org.andengine.entity.particle.initializer.RotationParticleInitializer;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.opengl.GLES20;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.constants.Constants;

/**
 * @author Manuel Seiche
 * @since 21.10.2014
 * 
 */
public abstract class ParticleSystemBuilder {
	public static BatchedPseudoSpriteParticleSystem buildLinearSnowParticleSystem(
			IController pController) {
		float lWidth = pController.getView().getSceneManager().getGameScene()
				.getBoard().getWidth();
		float lHeight = pController.getView().getSceneManager().getGameScene()
				.getBoard().getHeight();
		int lParticleCount = calculateParticleCount(lWidth, lHeight);
		ITextureRegion lTextureRegionSnow = pController.getView()
				.getResourceManager().getTextureRegionParticleSnow();
		BatchedPseudoSpriteParticleSystem lSnowParticleSystem = new BatchedPseudoSpriteParticleSystem(
				new RectangleParticleEmitter(lWidth / 2, lHeight / 2, lWidth,
						lHeight), lParticleCount / 50, lParticleCount / 20,
				lParticleCount, lTextureRegionSnow, pController
						.getMainActivity().getVertexBufferObjectManager());
		lSnowParticleSystem
				.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE);
		lSnowParticleSystem
				.addParticleInitializer(new VelocityParticleInitializer<Entity>(
						-3, 3, 20, 40));
		lSnowParticleSystem
				.addParticleInitializer(new AccelerationParticleInitializer<Entity>(
						-3, 3, -3, -5));
		lSnowParticleSystem
				.addParticleInitializer(new RotationParticleInitializer<Entity>(
						0.0f, 360.0f));
		lSnowParticleSystem
				.addParticleInitializer(new ExpireParticleInitializer<Entity>(
						10f));
		lSnowParticleSystem
				.addParticleInitializer(new ScaleParticleInitializer<Entity>(
						0.2f, calculateBaseParticleScale(pController
								.getMainActivity().getSmoothCamera()
								.getHeight(), lTextureRegionSnow)));
		lSnowParticleSystem
				.addParticleModifier(new AlphaParticleModifier<Entity>(6f, 10f,
						1.0f, 0.0f));
		return lSnowParticleSystem;
	}

	private static int calculateParticleCount(float pWidth, float pHeight) {
		return (int) (pWidth * pHeight / Constants.PARTICLE_CALC_PIXELS_PER_SNOWFLAKE);
	}

	private static float calculateBaseParticleScale(float pHeight,
			ITextureRegion pTextureRegion) {
		return pHeight / 45 / pTextureRegion.getHeight();
	}
}
