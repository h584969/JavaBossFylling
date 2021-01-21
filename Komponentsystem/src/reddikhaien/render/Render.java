package reddikhaien.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import reddikhaien.komponentsystem.ComponentSystem.Entry;
import reddikhaien.world.World;

public class Render extends Canvas{
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static final long serialVersionUID = 1L;

	private static final Color[] Colors = new Color[] {
			Color.CYAN,
			Color.GREEN,
			Color.BLUE.brighter(),
			Color.RED,
	};
	
	private Entry drawEntry;
	private Entry preDrawEntry;
	
	private Graphics curGraphics;
	
	private BufferStrategy buffer;
	
	private float camOffx, camOffy;
	
	
	public Render() {
		camOffx = 0.0f;
		camOffy = 0.0f;
		drawEntry = null;
		preDrawEntry = null;
		curGraphics = null;
		buffer = null;
	}
	
	public void setCameraOffset(float offx, float offy) {
		this.camOffx = offx;
		this.camOffy = offy;
	}
	
	public void drawSpriteInWorld(int spriteID, float x, float y) {
		curGraphics.setColor(Colors[spriteID]);
		curGraphics.fillRect((int)((x - camOffx)*100), (int)((y - camOffy)*100), 100, 100);
	}
	
	
	public void SetEntry(Entry drawEntry, Entry preDrawEntry) {
		this.drawEntry = drawEntry;
		this.preDrawEntry = preDrawEntry;
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH,HEIGHT);
	}
	
	public void draw(World world ) {
		
		if (buffer == null) {
			createBufferStrategy(3);
			buffer = getBufferStrategy();
		}
		
		curGraphics = buffer.getDrawGraphics();
		
		
		
		curGraphics.setColor(Color.BLACK);
		curGraphics.fillRect(0, 0, WIDTH, HEIGHT);
		preDrawEntry.execute();
		
		if (world != null) world.draw(this);
		
		drawEntry.execute();
		curGraphics.dispose();
		
		curGraphics = null;
	
		buffer.show();
	}
}
