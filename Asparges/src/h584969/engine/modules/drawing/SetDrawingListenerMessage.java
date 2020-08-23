package h584969.engine.modules.drawing;

import h584969.engine.IEntityMessage;

public class SetDrawingListenerMessage implements IEntityMessage {
	
	long id;
	IDrawingListener listener;
	
	public SetDrawingListenerMessage(long id, IDrawingListener listener) {
		this.id = id;
		this.listener = listener;
	}
	
	@Override
	public int getId() {
		return 0;
	}
}
