package reddikhaien.util;

public class Sprite {
	private final int width;
	private final int height;
	private final int x;
	private final int y;
	
	public Sprite(int x, int y, int w, int h) {
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
}
