package com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
import com.github.scaronthesky.eternalwinterwars.view.scenes.AControllerScene;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class EditorScene extends AControllerScene
		implements
			IEditorScene,
			IOnSceneTouchListener {
	public static final float DEFAULT_SPRITE_SIDE_LENGTH_HEIGHT_FACTOR = 0.1f;
	private Sprite[][] gSprites;
	private float gSpriteSideLength;
	private String gActualKey;

	public EditorScene(IController pController) {
		super(pController);
	}

	@Override
	public void initialize() {
		this.setSpriteSideLength(this.getController().getMainActivity()
				.getEngine().getCamera().getHeight()
				* DEFAULT_SPRITE_SIDE_LENGTH_HEIGHT_FACTOR);
		this.setOnSceneTouchListener(this);
	}

	@Override
	public void repaint(String[][] pKeys, int pColumns, int pRows) {
		for (int lColumn = 0; lColumn < pColumns; lColumn++) {
			for (int lRow = 0; lRow < pRows; lRow++) {
				Sprite lSprite = this.gSprites[lColumn][lRow];
				if (lSprite != null) {
					lSprite.setWidth(this.gSpriteSideLength);
					lSprite.setHeight(this.gSpriteSideLength);
					lSprite.setX(lColumn * this.gSpriteSideLength);
					lSprite.setY(lRow * this.gSpriteSideLength);
				}
			}
		}
	}

	@Override
	public void drawSprite(String pKey, int pColumn, int pRow) {
		Sprite lSprite = new Sprite(pColumn * this.gSpriteSideLength, pRow
				* this.gSpriteSideLength, this.gSpriteSideLength,
				this.gSpriteSideLength, this.getController().getView()
						.getResourceManager().getTextureRegions().get(pKey),
				this.getController().getMainActivity()
						.getVertexBufferObjectManager());
		this.gSprites[pColumn][pRow] = lSprite;
		this.attachChild(lSprite);
	}

	@Override
	public void removeSprite(int pColumn, int pRow) {
		this.detachChild(this.gSprites[pColumn][pRow]);
		this.gSprites[pColumn][pRow] = null;
	}

	@Override
	public void removeAllSprites(int pColumns, int pRow) {
		this.gSprites = new Sprite[pColumns][pRow];
		this.detachChildren();
	}

	@Override
	public void setColumnsAndRows(int pColumns, int pRows) {
		this.detachChildren();
		this.gSprites = new Sprite[pColumns][pRows];
	}

	@Override
	public void setBackground(String pKey, int pColumns, int pRows) {
		this.setBackground(new SpriteBackground(new Sprite(0, 0, pColumns
				* this.gSpriteSideLength, pRows * this.gSpriteSideLength, this
				.getController().getView().getResourceManager()
				.getTextureRegions().get(pKey), this.getController()
				.getMainActivity().getVertexBufferObjectManager())));
	}

	@Override
	public void setSpriteSideLength(float pSpriteSideLength) {
		this.gSpriteSideLength = pSpriteSideLength;
	}

	@Override
	public String getActualKey() {
		// TODO XXX JUST TEST
		return "3";
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		return this.getController().handleEditorSceneTouch(pSceneTouchEvent);
	}

	@Override
	public float getSpriteSideLength() {
		return this.gSpriteSideLength;
	}
}
