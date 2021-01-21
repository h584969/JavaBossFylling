package reddikhaien.komponentsystem.event;

import reddikhaien.render.Render;

public interface IPreDrawable extends IEvent {
	void preDraw(Render r);
}
