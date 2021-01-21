package reddikhaien.render;

import javax.swing.JFrame;

public class Screen extends JFrame{
	private static final long serialVersionUID = 1L;

	private Render graphics;
	private Input input;
	public Screen() {
		super("tittel");
		graphics = new Render();
		input = new Input();
		
		this.setVisible(false);
		
		this.add(graphics);
		this.addKeyListener(input);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void pollInput() {
		input.poll();
	}
	
	public Render getCanvasGraphics() {
		return graphics;
	}
	
}
