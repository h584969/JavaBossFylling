package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class RotateMessage implements IEntityMessage {
	long id;
	float rotation;
	
	public RotateMessage(long id, float rotation) {
		this.id = id;
		this.rotation = rotation;
	}
	
	@Override
	public int getId() {
		return PhysicsModule.ROTATE;
	}

}
