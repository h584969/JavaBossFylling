package h584969.util;

public class Vector2 {
	private float x,y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Vector2() {
		this(0.0f,0.0f);
	}
	
	public Vector2 add(Vector2 o) {
		return new Vector2(this.x + o.x,this.y + o.y);
	}
	public Vector2 sub(Vector2 o) {
		return new Vector2(this.x - o.x,this.y - o.y);
	}
	public Vector2 scale(float a) {
		return new Vector2(this.x*a,this.y*a);
	}
	
	
	public float dot(Vector2 o) {
		return this.x*o.x + this.y*o.y;
	}
	
	public float magnitudeSQR() {
		return this.dot(this);
	}
	public float magnitude() {
		return (float)Math.sqrt(magnitudeSQR());
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
