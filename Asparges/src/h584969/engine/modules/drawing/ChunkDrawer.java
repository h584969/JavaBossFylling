package h584969.engine.modules.drawing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import h584969.graphics.Drawing;

class ChunkInfo{
	static final int SIZE = 32;
	byte[][] data = new byte[SIZE][SIZE];
}

public class ChunkDrawer implements IDrawingListener{
	
	public ChunkDrawer(String chunkPath) {
		//File file = new File(chunkPath);
	}
	
	@Override
	public void draw(Graphics2D g2d, Drawing drawing) {
		final BufferedImage img = drawing.getImage(Drawing.GRASS_SPRITE_INDEX);
		synchronized (img) {
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					AffineTransform t = g2d.getTransform();
					g2d.translate(x*16, y*16);
					g2d.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
					g2d.setTransform(t);
				}
			}
		}
	}
	
}
