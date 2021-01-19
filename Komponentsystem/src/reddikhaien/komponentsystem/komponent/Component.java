package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;

public abstract class Component {
	private GameObject object;
	
	
	public Component(GameObject object) {
		this.object = object;
	}
	
	
	/**
	 * Returnerer spill
	 * @return
	 */
	public GameObject getGameObject() {
		return object;
	}
	
	public Class<? extends Component>[] getDepencies(){ return null; }
	
}
