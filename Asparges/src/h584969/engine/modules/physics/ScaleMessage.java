package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class ScaleMessage implements IEntityMessage{
	
	long id;
	float x;
	float y;
	
	public ScaleMessage(long id, float x, float y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getId() {
		return PhysicsModule.SCALE;
	}
}
