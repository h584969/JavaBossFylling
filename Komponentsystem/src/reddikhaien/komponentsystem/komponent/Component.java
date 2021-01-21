package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;

public abstract class Component {
	private GameObject object;
	
	
	public Component(GameObject object) {
		this.object = object;
	}
	
	/**
	 * blir kalt når spillobjektet er ferdig konstruert.
	 */
	public void init() {}
	
	
	/**
	 * Returnerer spillobjektet som denne komponenten er koblet til
	 * @return
	 */
	public GameObject getGameObject() {
		return object;
	}
	
	public Class<? extends Component>[] getDepencies(){ return null; }
	
}
