package h584969.engine.modules.drawing;

import java.awt.Graphics2D;

import h584969.graphics.Drawing;

public interface IDrawingListener {
	public void draw(final Graphics2D g2d, final Drawing drawing);
}
