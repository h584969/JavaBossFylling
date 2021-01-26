package reddikhaien.util;

import reddikhaien.render.Render;
import reddikhaien.render.Texture;

public class Sprite {
	
	private final Texture texture;
	private final int width;
	private final int height;
	private final int x;
	private final int y;
	
	public Sprite(Texture texture,int x, int y, int w, int h) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void drawSpriteToScreen(Render r, int px, int py) {
		int sw = Render.WIDTH;
		int sh = Render.HEIGHT;
	}
}
