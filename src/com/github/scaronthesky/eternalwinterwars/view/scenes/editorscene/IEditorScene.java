package com.github.scaronthesky.eternalwinterwars.view.scenes.editorscene;

import org.andengine.entity.scene.Scene;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public interface IEditorScene {
	public void repaint(String[][] pKeys, int pColumns, int pRows);

	public void drawSprite(String pKey, int pColumn, int pRow);

	public void removeSprite(int pColumn, int pRow);

	public void removeAllSprites(int pColumns, int pRows);

	public void setColumnsAndRows(int pColumns, int pRows);

	public void setBackground(String pKey, int pColumns, int pRows);

	public void setSpriteSideLength(float pSpriteSideLength);

	public String getActualKey();

	public float getSpriteSideLength();

	/**
	 * @return Scene-Instance of implementing class
	 */
	public Scene getInstance();
}
