package reddikhaien.komponentsystem.event;

import reddikhaien.render.Render;

public interface IDrawable extends IEvent{
	
	
	void draw(Render graphics);
}
