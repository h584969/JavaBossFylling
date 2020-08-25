package h584969.engine.modules.drawing;

import h584969.engine.IEntityMessage;

public class SetCameraMessage implements IEntityMessage{
	long id;
	
	public SetCameraMessage(long id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		
		return DrawingModule.SET_CAMERA;
	}
}
