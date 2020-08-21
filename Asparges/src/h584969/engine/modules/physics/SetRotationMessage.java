package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class SetRotationMessage implements IEntityMessage {

	long id;
	float rotation;
	public SetRotationMessage(long id, float rotation){
		this.id = id;
		this.rotation = rotation;
	}
	
	@Override
	public int getId() {
		return PhysicsModule.SET_ROTATION;
	}

}
