package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class SetPositionMessage implements IEntityMessage {
	public long id;
	public float x,y;
	public SetPositionMessage(long id, float x, float y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getId() {
		return PhysicsModule.SET_POSITION;
	}
}
