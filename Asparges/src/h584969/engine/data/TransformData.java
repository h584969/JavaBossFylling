package h584969.engine.data;

import h584969.util.Vector2;

public class TransformData {
	private Vector2 position;
	private Vector2 scale;
	float rotation;
	public TransformData() {
		this.position = new Vector2();
		this.scale = new Vector2(1.0f,1.0f);
		this.rotation = 0.0f;
	}
	
	public TransformData translate(float x, float y) {
		position.setX(position.getX() + x);
		position.setY(position.getY() + y);
		return this;
	}
	public TransformData scale(float x, float y) {
		scale.setX(scale.getX()*x);
		scale.setY(scale.getY()*y);
		return this;
	}
	public TransformData rotate(float a) {
		rotation += a;
		return this;
	}
	public TransformData setPosition(float x, float y) {
		position.setX(x);
		position.setY(y);
		return this;
	}
	public TransformData setScale(float x, float y) {
		scale.setX(x);
		scale.setY(y);
		return this;
	}
	public TransformData setRotation(float a) {
		rotation = a;
		return this;
	}
	public TransformData setPosition(Vector2 position) {
		this.position = position;
		return this;
	}
	public TransformData setScale(Vector2 scale) {
		this.scale = scale;
		return this;
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
	
	public TransformData createCopy() {
		return new TransformData().setPosition(position.getX(),position.getY()).setScale(scale.getX(),scale.getY()).setRotation(rotation);
	}
	
}
