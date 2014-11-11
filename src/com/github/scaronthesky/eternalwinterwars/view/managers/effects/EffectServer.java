package com.github.scaronthesky.eternalwinterwars.view.managers.effects;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.scaronthesky.eternalwinterwars.controller.IController;

/**
 * @author Manuel Seiche
 * @since 22.10.2014
 */
public class EffectServer {
	private IController gController;
	private float gX;
	private float gY;
	private Scene gParentScene;

	public EffectServer(IController pController, float pX, float pY,
			Scene pParentScene) {
		this.gController = pController;
		this.gX = pX;
		this.gY = pY;
		this.gParentScene = pParentScene;
	}

	public List<Sprite> createSprites(List<float[]> pAppearCoordinates,
			float pWidth, float pHeight, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		List<Sprite> lSprites = new ArrayList<Sprite>();
		for (float[] lAppearCoordinateSet : pAppearCoordinates) {
			lSprites.add(new Sprite(lAppearCoordinateSet[0],
					lAppearCoordinateSet[1], pWidth, pHeight, pTextureRegion,
					pVertexBufferObjectManager));
		}
		return lSprites;
	}
}
