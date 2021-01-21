package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;

public class Transform extends Component{

	private float px, py;
	private float rotation;
	private float sx, sy;
	
	public Transform(GameObject object) {
		super(object);
		this.px = 0;
		this.py = 0;
		this.rotation = 0;
		this.sx = 0;
		this.sy = 0;
	}
	
	public void translate(float x, float y) {
		this.px += x;
		this.py += y;
	}
	public void rotate(float amt) {
		rotation += amt;
	}
	public void scale(float x, float y) {
		this.sx += x;
		this.sy += y;
	}

	public float getPx() {
		return px;
	}

	public void setPx(float px) {
		this.px = px;
	}

	public float getPy() {
		return py;
	}

	public void setPy(float py) {
		this.py = py;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getSx() {
		return sx;
	}

	public void setSx(float sx) {
		this.sx = sx;
	}

	public float getSy() {
		return sy;
	}

	public void setSy(float sy) {
		this.sy = sy;
	}
	
	public float sqrDistanceTo(float px, float py) {
		return (this.px - px)*(this.px - px) + (this.py - py)*(this.py - py);
	}
}
