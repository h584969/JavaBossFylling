package reddikhaien.world.item;

import reddikhaien.komponentsystem.GameObject;

public interface IConsumable {
	
	
	/**
	 * blir kalt når gitt item blir brukt
	 * f. eks. blir kalt når noen spiser mat.
	 * @param object
	 * @return sann om den ble brukt, feil ellers
	 */
	boolean consume(GameObject object);
	
}
