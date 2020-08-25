package h584969.engine.modules.drawing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import h584969.graphics.Drawing;

public class SpriteDrawer implements IDrawingListener {
	
	private int spriteIndex = Drawing.PLAYER_SPRITE_INDEX;
	
	public SpriteDrawer(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	
	@Override
	public void draw(final Graphics2D g2d, final Drawing drawing) {
		BufferedImage img = drawing.getImage(spriteIndex);
		g2d.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
	}
	
	public int getSpriteIndex() {
		return spriteIndex;
	}
	
	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
}
