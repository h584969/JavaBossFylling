package h584969;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import h584969.engine.EntityManager;
import h584969.engine.modules.drawing.ChunkDrawer;
import h584969.engine.modules.drawing.SetCameraMessage;
import h584969.engine.modules.drawing.SetCameraOffsetMessage;
import h584969.engine.modules.drawing.SetDrawingListenerMessage;
import h584969.engine.modules.drawing.SpriteDrawer;
import h584969.engine.modules.physics.SetPositionMessage;
import h584969.graphics.Drawing;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private long entityID = 1L;
	
	private Drawing drawing;
	private boolean running;
	
	
	public Main() {
		drawing = Drawing.instance;
		
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addKeyListener(new Input());
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
		running = true;
		EntityManager.start();
		addGround();
		addPlayer();
		
	}
	
	private void run() {
		while(running) {
			Input.poll();

			this.repaint();

			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		terminate();
	}
	
	public void addPlayer() {
		EntityManager.PHYSICS.createNewData(entityID);
		//EntityManager.PHYSICS.sendMessage(new SetPositionMessage(entityID, Drawing.WIDTH/2,Drawing.HEIGHT/2));
		EntityManager.DRAWING.createNewData(entityID);
		EntityManager.DRAWING.sendMessage(new SetCameraMessage(entityID));
		EntityManager.DRAWING.sendMessage(new SetCameraOffsetMessage(Drawing.WIDTH/2, Drawing.HEIGHT/2));
		EntityManager.PLAYER_CONTROLLER.createNewData(entityID);
		entityID++;
	}
	public void addGround() {
		EntityManager.PHYSICS.createNewData(entityID);
		EntityManager.DRAWING.createNewData(entityID);
		EntityManager.DRAWING.sendMessage(new SetDrawingListenerMessage(entityID, new ChunkDrawer("assets/chunks/abc.def")));
		entityID++;
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
