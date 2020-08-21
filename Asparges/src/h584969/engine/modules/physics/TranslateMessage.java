package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class TranslateMessage implements IEntityMessage{
	long id;
	float x;
	float y;
	
	public TranslateMessage(long id, float x, float y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return PhysicsModule.TRANSLATE;
	}

}
