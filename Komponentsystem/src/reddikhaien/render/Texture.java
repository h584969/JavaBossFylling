package reddikhaien.render;

public class Texture {
	
	private final int[] data;
	private final int width;
	private final int height;
	
	public Texture(int[] data, int width, int height) {
		this.data = data;
		this.width = width;
		this.height = height;
	}

	public int[] getData() {
		return data;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
