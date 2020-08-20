package h584969;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import h584969.engine.EntityManager;
import h584969.graphics.Drawing;
import h584969.util.Vector2;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	
	private Drawing drawing;
	private boolean running;
	
	
	public Main() {
		drawing = new Drawing();
		
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				running = false;
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		this.setLayout(new BorderLayout());
		this.add(drawing);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void start() {
		EntityManager.start();
		EntityManager.CreateEntity(EntityManager.TRANSFORM,EntityManager.RENDER,EntityManager.PHYSICS);
		EntityManager.PHYSICS.setVelocity(1L, new Vector2(1,0.0f));
		running = true;
	}
	
	private void run() {
		while(running) {
			EntityManager.tick();
			this.repaint();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		terminate();
	}
	private void terminate() {
		EntityManager.terminate();
		dispose();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
		main.run();
	}

}
