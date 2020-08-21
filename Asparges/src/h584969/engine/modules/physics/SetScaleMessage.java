package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class SetScaleMessage implements IEntityMessage {
	long id;
	float x;
	float y;
	
	public SetScaleMessage(long id, float x, float y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getId() {
		return PhysicsModule.SET_SCALE;
	}

}
