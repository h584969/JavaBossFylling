package h584969.engine.modules.drawing;

import h584969.engine.IEntityMessage;

public class SetCameraOffsetMessage implements IEntityMessage {

	float x;
	float y;
	
	public SetCameraOffsetMessage(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public int getId() {
		
		return DrawingModule.SET_CAMERA_OFFSET;
	}

}
