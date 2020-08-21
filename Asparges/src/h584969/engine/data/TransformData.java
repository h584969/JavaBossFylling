package h584969.engine.data;

import h584969.engine.data.packet.IPacket;
import h584969.util.Vector2;

public class TransformData implements IPacket<TransformData> {

	private Vector2 position = new Vector2();
	private Vector2 scale = new Vector2(1.0f,1.0f);
	private float rotation = 0.0f;
	private boolean dirty = true;
	@Override
	public TransformData createCopy() {
		return new TransformData().setPosition(new Vector2(position.getX(),position.getY())).setScale(new Vector2(scale.getX(),scale.getY())).setRotation(rotation);
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getScale() {
		return scale;
	}

	public float getRotation() {
		return rotation;
	}

	public TransformData setPosition(Vector2 position) {
		this.position = position;
		return this;
	}

	public TransformData setScale(Vector2 scale) {
		this.scale = scale;
		return this;
	}

	public TransformData setRotation(float rotation) {
		this.rotation = rotation;
		return this;
	}
	
	public void markDirty() {
		dirty = true;
	}
	public void markClean() {
		dirty = false;
	}

	@Override
	public boolean isChanged() {
		
		return dirty;
	}

}
