package h584969.engine.data;

import java.awt.Color;

import h584969.util.Vector2;

public class RenderData {
	Color color;
	private Vector2 size;
	private Vector2 offset;
	public RenderData() {
		color = Color.WHITE;
		size = new Vector2();
		offset = new Vector2();
	}
	public Color getColor() {
		return color;
	}
	public RenderData setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Vector2 getSize() {
		return size;
	}
	public RenderData setSize(float x, float y) {
		size = new Vector2(x,y);
		return this;
	}
	public RenderData setSize(Vector2 size) {
		this.size = size;
		return this;
	}

	public Vector2 getOffset() {
		return offset;
	}
	public RenderData setOffset(float x, float y) {
		offset = new Vector2(x,y);
		return this;
	}
	public RenderData setOffset(Vector2 offset) {
		this.offset = offset;
		return this;
	}
}
