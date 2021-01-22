package reddikhaien.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteManager {
	private static HashMap<String, int[]> textures = new HashMap<>();
	private static HashMap<SpriteKey, Integer> mapping = new HashMap<>();
	private static ArrayList<Sprite> sprites = new ArrayList<>();
	
	
	public static int getSprite(String name, int tileX, int tileY, int tileWidth, int tileHeight) {
		SpriteKey key = new SpriteKey(name, tileX, tileY, tileWidth, tileHeight);
		
		if (!mapping.containsKey(key)) {
			sprites.add(new Sprite(tileX,tileY,tileWidth,tileHeight));
		}
		return 0;
	}
	
	private static final int[] getTexture(String name) {
		if (textures.containsKey(name)) {
			return textures.get(name);
		}
		
		
		
		try {
			File file = new File("res/img/" + name + ".png");
			
			BufferedImage image =  ImageIO.read(file);
			
			int width = image.getWidth();
			int height = image.getHeight();
			int[] data = ((DataBufferInt) image.getData().getDataBuffer()).getData();
		
			return data;
		} catch (IOException e) {
			System.err.println("Feilet å laste inn " + name);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	private static class SpriteKey{
		private final String name;
		private final int tilex;
		private final int tiley;
		private final int tilew;
		private final int tileh;
		public SpriteKey(String name, int x, int y, int w, int h) {
			this.name = name;
			this.tilex = x;
			this.tiley = y;
			this.tilew = w;
			this.tileh = h;
		}
		
		@Override
		public int hashCode() {
			int h = 23;
			h *= 31 + name.hashCode();
			h *= 31 + tilex;
			h *= 31 + tiley;
			h *= 31 + tilew;
			h *= 31 + tileh;
			
			return h;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (o instanceof SpriteKey) {
				SpriteKey k = (SpriteKey)o;
				return k.tilex == this.tilex && 
						k.tiley == this.tiley && 
						k.tilew == this.tilew && 
						k.tileh == this.tileh &&
						k.name.equals(this.name);
			}
			
			return false;
		}
	}
}
