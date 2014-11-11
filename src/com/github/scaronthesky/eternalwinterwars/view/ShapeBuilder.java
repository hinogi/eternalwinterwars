package com.github.scaronthesky.eternalwinterwars.view;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public abstract class ShapeBuilder {
	/**
	 * Creates a {@link Rectangle}
	 * 
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pVertexBufferObjectManager
	 * @param pColor
	 * @param pAlpha
	 * @return
	 */
	public static Rectangle createRectangle(float pX, float pY, float pWidth,
			float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager, Color pColor,
			float pAlpha) {
		Rectangle lRect = new Rectangle(pX, pY, pWidth, pHeight,
				pVertexBufferObjectManager);
		lRect.setColor(pColor);
		lRect.setAlpha(pAlpha);
		return lRect;
	}
}
