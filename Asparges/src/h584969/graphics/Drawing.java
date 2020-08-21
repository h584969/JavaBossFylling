package h584969.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import h584969.engine.EntityManager;

public class Drawing extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 200;
	public static final int HEIGHT = 150;
	public static final int SCALE = 4;
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		Graphics2D g2d = (Graphics2D)g;
		
		EntityManager.DRAWING.drawSprites(g2d);
		
	}
}
