package h584969.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import h584969.engine.EntityManager;

public class Drawing extends JPanel {
	
	//=================================
	//TODO rydee opp her
	//=================================
	
	public static final Drawing instance = new Drawing();
	
	private static final long serialVersionUID = 1L;
	
	public static final int PLAYER_SPRITE_INDEX = instance.loadImage("assets/textures/player.png");
	public static final int PLAYER_LEFT_SPRITE_INDEX = instance.loadImage("assets/textures/player_left.png");
	public static final int GRASS_SPRITE_INDEX = instance.loadImage("assets/textures/grass.png");
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	public static final int SCALE = 3;
	
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private long curTime = 0L;
	private long oldTime = Calendar.getInstance().getTimeInMillis();
	
	
	public BufferedImage getImage(int index) {
		synchronized (images) {
			return images.get(index);
		}
	}
	
	
	public int loadImage(final String path) {
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			images.add(image);
			return images.size()-1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		curTime = Calendar.getInstance().getTimeInMillis();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform t = g2d.getTransform();
		g2d.scale(SCALE, SCALE);
		EntityManager.DRAWING.drawSprites(g2d,this,(curTime - oldTime));
		oldTime = curTime;
		g2d.setTransform(t);
	}
}
