package h584969.engine.modules.physics;

import h584969.engine.IEntityMessage;

public class TransformMessage implements IEntityMessage{
	
	long id;
	
	float posx;
	float posy;
	float scalex;
	float scaley;
	float rotation;
	
	
	public TransformMessage(long id,float posx, float posy, float scalex, float scaley, float rotation) {
		this.id = id;
		this.posx = posx;
		this.posy = posy;
		this.scalex = scalex;
		this.scaley = scaley;
		this.rotation = rotation;
	}
	
	@Override
	public int getId() {
		return PhysicsModule.TRANSFORM;
	}
}
