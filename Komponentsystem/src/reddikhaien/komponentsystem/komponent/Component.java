package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;

public abstract class Component {
	private GameObject object;
	public Component(GameObject object) {
		this.object = object;
	}
	public GameObject getGameObject() {
		return object;
	}
}
